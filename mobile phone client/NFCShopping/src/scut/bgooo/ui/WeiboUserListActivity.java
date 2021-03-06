package scut.bgooo.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import scut.bgooo.ui.R;
import scut.bgooo.utility.INFCActivity;
import scut.bgooo.utility.Task;
import scut.bgooo.utility.TaskHandler;
import scut.bgooo.weibo.WeiboUserItem;
import scut.bgooo.weibo.WeiboUserManager;
import weibo4android.User;
import weibo4android.Weibo;
import weibo4android.WeiboException;
import weibo4android.http.AccessToken;
import weibo4android.http.RequestToken;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WeiboUserListActivity extends Activity implements INFCActivity {

	CommonsHttpOAuthConsumer httpOauthConsumer;
	OAuthProvider httpOauthprovider;

	private Button mClearList;
	private Button mDelUser;
	private Button mAddUser;
	private ListView mUserList;
	private WeiboUserManager dataHelper;
	private List<WeiboUserItem> mList;
	// public Weibo mWeibo;
	private RequestToken mRequestToken;
	private AccessToken mAccessToken;
	private int defaultUser = -1;// 默认用户
	public static WeiboUserItem defaultUserInfo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webuser);
		
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);

		mClearList = (Button) findViewById(R.id.clear);
		mDelUser = (Button) findViewById(R.id.del);
		mAddUser = (Button) findViewById(R.id.add);
		mUserList = (ListView) findViewById(R.id.user);

		dataHelper = new WeiboUserManager(this);// 打开数据库　一直到这个activity销毁时才关闭
		mList = dataHelper.GetUserList(false);
		if (mList.isEmpty()) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"您尚未绑定用户,请添加用户绑定", Toast.LENGTH_SHORT);
			toast.show();
			Log.d("NFC", "AA");
		} else {
			MyAdapter myAdapter = new MyAdapter(this, mList);
			mUserList.setAdapter(myAdapter);
			mUserList.setClickable(true);
		}

		mAddUser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ConnectivityManager conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo netMobile = conn
						.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				NetworkInfo wifi = conn
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				if (false == netMobile.isConnectedOrConnecting()&&false==wifi.isConnectedOrConnecting()) {
					Toast.makeText(getApplicationContext(), "请检查网络配置",
							Toast.LENGTH_SHORT).show();
				} else {
					try {
						Weibo weibo = new Weibo();
						mRequestToken = weibo.getOAuthRequestToken();
						String url = mRequestToken.getAuthorizationURL();
						Bundle bundle = new Bundle();
						bundle.putString("URL", url);
						Intent intent = new Intent(WeiboUserListActivity.this,
								VerifierWebViewActivity.class);
						intent.putExtra("URL", bundle);
						startActivity(intent);
					} catch (WeiboException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

		mClearList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dataHelper.ClearUserInfo(mList);
				mList = dataHelper.GetUserList(true);

				MyAdapter myAdapter = new MyAdapter(WeiboUserListActivity.this,
						mList);
				mUserList.setAdapter(myAdapter);
				Toast toast = Toast.makeText(getApplicationContext(),
						"您尚未绑定用户,请添加用户绑定", Toast.LENGTH_SHORT);
				toast.show();
				defaultUser = -1;
			}
		});

		mDelUser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (defaultUser >= 0) {
					WeiboUserItem user = mList.get(defaultUser);
					dataHelper.DelUserInfo(user.GetUserId());
					mList = dataHelper.GetUserList(true);
					if (0 == mList.size()) {
						Toast toast = Toast.makeText(getApplicationContext(),
								"您尚未绑定用户,请添加用户绑定", Toast.LENGTH_SHORT);
						toast.show();
					} else if (mList.size() >= 1) {
						Toast toast = Toast.makeText(getApplicationContext(),
								"请选择默认用户", Toast.LENGTH_SHORT);
						toast.show();
					}
					MyAdapter myadapter = new MyAdapter(
							WeiboUserListActivity.this, mList);
					mUserList.setAdapter(myadapter);
					defaultUser = -1;
				} else {
					Toast toast = Toast.makeText(getApplicationContext(),
							"请选择默认用户", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

		TaskHandler.allActivity.put(this.getClass().getSimpleName(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Log.d("NFC", "onNewIntent");
		Bundle bundle = intent.getExtras();
		String pin = bundle.getString("PIN");
		try {
			mAccessToken = mRequestToken.getAccessToken(pin);
		} catch (WeiboException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// DataHelper dataHelper = new DataHelper(this);
		// 判断是否在数据库中已经存在这个用户　
		if (dataHelper.HaveUserInfo(Long.toString(mAccessToken.getUserId()))) {
			Toast toast = Toast.makeText(getApplicationContext(), "该用户已经绑定",
					Toast.LENGTH_SHORT);
			toast.show();
			return;
		}

		// 如果不存在，则生成一个USERINFO对象再存入数据库中
		WeiboUserItem userInfo = new WeiboUserItem();
		userInfo.SetAccessSecret(mAccessToken.getTokenSecret());
		userInfo.SetAccessToken(mAccessToken.getToken());
		userInfo.SetUserId(Long.toString(mAccessToken.getUserId()));

		HashMap<String, WeiboUserItem> hm = new HashMap<String, WeiboUserItem>();
		hm.put("weiuser", userInfo);
		Task task = new Task(Task.GET_USER_INFORMATION, hm);
		TaskHandler.addTask(task);
		// try {
		// User weiboUser = mWeibo.verifyCredentials();
		// userInfo.SetLocation(weiboUser.getLocation());
		// userInfo.SetUserName(weiboUser.getScreenName());
		// URL url = weiboUser.getProfileImageURL();// 用记头像的URL地址
		// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setRequestMethod("GET");
		// conn.setConnectTimeout(5000);
		// InputStream inPutStream = conn.getInputStream();
		// // 获取到图片的二进制数据
		// byte[] data = readInputStream(inPutStream);
		// userInfo.SetIcon(data);
		// userInfo.SetDefault(true);// 把最新验证的用户设为默认
		// if (mList.size() != 0) {
		// dataHelper.UpdateDefault(mList.get(defaultUser));
		// }
		// // dataHelper.SaveUserInfo(userInfo);//把更新的userinfo对象存入数据库
		// if (dataHelper.SaveUserInfo(userInfo) == -1) {
		// Toast toast = Toast.makeText(getApplicationContext(), "写入数据失败",
		// Toast.LENGTH_SHORT);
		// toast.show();
		// }
		// // 再更新适配器
		// mList = dataHelper.GetUserList(false);
		// MyAdapter myAdapter = new MyAdapter(this, mList);
		// mUserList.setAdapter(myAdapter);
		//
		// } catch (WeiboException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Log.d("NFC", "成功绑定");

	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		// 构造一个ByteArrayOutputStream
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 设置一个缓冲区
		byte[] buffer = new byte[1024];
		int len = 0;
		// 判断输入流长度是否等于-1，即非空
		while ((len = inStream.read(buffer)) != -1) {
			// 把缓冲区的内容写入到输出流中，从0开始读取，长度为len
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		return outStream.toByteArray();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (mList.size() != 0) {
			if (defaultUser != -1) {
				defaultUserInfo = mList.get(defaultUser);
			} else {
				// 没有defaultUser的情况下，将第一条记录设置为默认
				defaultUser = 0;
				defaultUserInfo = mList.get(0);
				defaultUserInfo.SetDefault(true);
				dataHelper.UpdateUserInfo(defaultUserInfo);
				Toast.makeText(getApplicationContext(),
						"已默认设置" + defaultUserInfo.GetUserName(), 2000).show();
			}
		} else {
			defaultUserInfo = null;
		}
		dataHelper.Close();
		Log.d("NFC", "关闭数据库");
		super.onDestroy();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub		
		super.onRestart();

	}

	private class MyAdapter extends BaseAdapter {

		private Context mContext; // 运行上下文
		private List<WeiboUserItem> mListItems; // 商品信息集合
		private LayoutInflater mListContainer; // 视图容器

		public MyAdapter(Context context, List<WeiboUserItem> listItems) {
			mContext = context;
			mListItems = listItems;
			mListContainer = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return mList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return mList.get(arg0).GetId();
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			final int selectID = arg0;
			ViewHolder viewHolder = null;
			if (arg1 == null) {
				viewHolder = new ViewHolder();
				// 获取list_item布局文件的视图
				arg1 = mListContainer.inflate(R.layout.weibouseritem, null);
				// 获取控件对象
				viewHolder.mUserIcon = (ImageView) arg1
						.findViewById(R.id.usericon);
				viewHolder.mUserName = (TextView) arg1
						.findViewById(R.id.username);
				viewHolder.mUserLocaton = (TextView) arg1
						.findViewById(R.id.userlocation);
				viewHolder.mCheckBox = (CheckBox) arg1
						.findViewById(R.id.checkBox1);
				// 设置控件集到arg1
				arg1.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) arg1.getTag();
			}
			if (mListItems.size() != 0) {
				final WeiboUserItem user = (WeiboUserItem) getItem(selectID);
				byte[] data = user.GetIcon();
				Bitmap userIcon = BitmapFactory.decodeByteArray(data, 0,
						data.length);
				viewHolder.mUserIcon.setImageBitmap(userIcon);
				viewHolder.mUserName.setText(user.GetUserName());
				viewHolder.mUserLocaton.setText(user.GetLocationg());
				if (user.IsDefault()) {
					defaultUser = selectID;
					Log.e("default", defaultUser + "");
					viewHolder.mCheckBox.setChecked(true);
				} else {
					viewHolder.mCheckBox.setChecked(false);
				}
				viewHolder.mCheckBox.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (user.IsDefault()) {
							user.SetDefault(false);
							dataHelper.UpdateDefault(user);
						} else {
							for (WeiboUserItem item : mListItems) {
								if (item.equals(user)) {
									// 将该用户设置为默认账户
									defaultUserInfo = user;
									user.SetDefault(true);
									dataHelper.UpdateUserInfo(user);
								} else {
									// 将其他的用户设为非默认账户
									item.SetDefault(false);
									dataHelper.UpdateDefault(item);
								}
								notifyDataSetChanged();
								Toast toast = Toast.makeText(
										getApplicationContext(), "您选择"
												+ user.GetUserName() + "为默认用户",
										Toast.LENGTH_SHORT);
								toast.show();
							}
						}
					}
				});

			}
			return arg1;

		}

		private class ViewHolder {
			public ImageView mUserIcon;// 用户图片用
			public TextView mUserName;// 用户昵称
			public TextView mUserLocaton;// 用户注册地址
			public CheckBox mCheckBox;// 复选框用
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		WeiboUserItem weiuser = (WeiboUserItem) param[0];
		if (mList.size() != 0) {
			dataHelper.UpdateDefault(mList.get(defaultUser));
		}
		dataHelper.SaveUserInfo(weiuser);// 把更新的userinfo对象存入数据库
		mList = dataHelper.GetUserList(false);
		MyAdapter myAdapter = new MyAdapter(this, mList);
		mUserList.setAdapter(myAdapter);
		Log.d("NFC", "更新注册后UI");
	}

}
