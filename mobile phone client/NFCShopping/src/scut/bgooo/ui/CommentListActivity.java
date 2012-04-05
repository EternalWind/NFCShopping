//package scut.bgooo.ui;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Random;
//import java.util.Vector;
//
//import scut.bgooo.concern.ConcernItem;
//import scut.bgooo.db.ConcernManager;
//import scut.bgooo.entities.Product;
//import scut.bgooo.entities.Review;
//import scut.bgooo.entities.SecCategory;
//import scut.bgooo.entities.User;
//import scut.bgooo.webservice.WebServiceUtil;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.nfc.NfcAdapter;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.CompoundButton.OnCheckedChangeListener;
//import android.widget.ListView;
//import android.widget.RatingBar;
//import android.widget.TextView;
//
//public class CommentListActivity extends Activity {
//
//	private static final String TAG = CommentListActivity.class.getSimpleName();
//
//	protected static final int SUCCESS = 0;
//
//	private ConcernManager mConcernManager = null;
//
//	private ArrayList<HashMap<String, Object>> mTempitems = null;
//	private ListView mListView;
//	private Button mFinishButton;
//	private Button mCommentButton;
//	private Button mShareButton;
//	private TextView mNameTextView;
//	private TextView mPriceTextView;
//	private RatingBar mRatingBar;
//	private CheckBox mCheckBox;
//	
//	private View mProcess;
//	
//	private ConcernItem item;
//	private Product mProduct = null;
//	private Vector<Review> reviews;
//
//	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.commentlist);
//
//		mProduct = (Product) getIntent().getSerializableExtra("product");
//
//		mTempitems = new ArrayList<HashMap<String, Object>>();
//
//		for (int i = 0; i < 10; i++) {
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("Name", "��" + i + "����");
//			map.put("Comment", "��" + i + "����");
//			map.put("Rating", 4.0);
//			mTempitems.add(map);
//		}
//		
//		mProcess = this.findViewById(R.id.progress);
//		
//		mFinishButton = (Button) findViewById(R.id.btFinish);
//		mFinishButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				finish();
//			}
//		});
//
//		mCommentButton = (Button) findViewById(R.id.btComment);
//		mCommentButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(CommentListActivity.this,
//						CommentActivity.class);
//				startActivity(intent);
//			}
//		});
//
//		mShareButton = (Button) findViewById(R.id.btShare);
//		mShareButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//
//		mListView = (ListView) findViewById(R.id.comment_listview);
//
//		mPriceTextView = (TextView) findViewById(R.id.tvproductCost);
//		mNameTextView = (TextView) findViewById(R.id.tvProductname);
//		mRatingBar = (RatingBar) findViewById(R.id.indicator_ratingbar);
//		mCheckBox = (CheckBox) findViewById(R.id.cbStar);
//
//		mConcernManager = new ConcernManager(this);
//		resolveIntent(getIntent());
//
//		mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView,
//					boolean isChecked) {
//				// TODO Auto-generated method stub
//				if (isChecked) {
//					// �ղظü�¼
//					item.setIsCollected((short) 1);
//				} else {
//					// ȡ���ղظü�¼
//					item.setIsCollected((short) 0);
//				}
//				// �������ݿ������
//				mConcernManager.addConcernItem(item);
//				Log.d(TAG, "checked changed");
//			}
//		});
//		
//		DownloadInfo();
//	}
//
//	@Override
//	public void onNewIntent(Intent intent) {
//		setIntent(intent);
//		resolveIntent(intent);
//	}
//
//	void resolveIntent(Intent intent) {
//		// Parse the intent
//		String action = intent.getAction();
//
////		item = (ConcernItem) intent.getSerializableExtra("ConcernItem");
////
////		mPriceTextView.setText(String.valueOf(item.getPrice()));
////		mNameTextView.setText(item.getName());
////		mRatingBar.setRating((float) item.getRating());
////
////		Log.d(TAG, "is collected" + item.getIsCollected());
////		if (item.getIsCollected() == 0) {
////			mCheckBox.setChecked(false);
////		} else {
////			mCheckBox.setChecked(true);
////		}
//	}
//
//	private void DownloadInfo() {
//		Thread thread = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				reviews = WebServiceUtil.getInstance().getReviewsByProductId(5);
//
//				Message message = new Message();
//				// if (reviews) {
//				// message.arg1 = FAILE;
//				// } else {
//				// message.arg1 = SUCCESS;
//				// message.obj = mProduct;
//				// }
//				message.arg1 = SUCCESS;
//				message.obj = reviews;
//				handler.sendMessage(message);
//			}
//		});
//
//		thread.start();
//		thread = null;
//	}
//
//	final Handler handler = new Handler() {
//
//		@Override
//		public void handleMessage(Message msg) {
//			// TODO Auto-generated method stub
//			super.handleMessage(msg);
//			switch (msg.arg1) {
//			case SUCCESS:
//				CommentAdapter ma = new CommentAdapter(
//						CommentListActivity.this, reviews);
//				mListView.setAdapter(ma);// ���������
//				mProcess.setVisibility(View.GONE);
//				break;
//			}
//
//		}
//	};
//}
//
//class CommentAdapter extends BaseAdapter {
//
//	Vector<Review> items;
//	Context context;
//
//	public CommentAdapter(Context context, Vector<Review> items) {
//		this.context = context;
//		this.items = items;
//	}
//
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return items.size() + 1;
//	}
//
//	@Override
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		if (position == this.getCount() - 1) {
//			return null;
//		} else {
//			return items.get(position - 1);
//		}
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		if (position == this.getCount() - 1) {
//			View moreitem = LayoutInflater.from(context).inflate(
//					R.layout.moreitemsview, null);
//			TextView tv = (TextView) moreitem.findViewById(R.id.tvItemContent);
//			tv.setText("����");
//
//			return moreitem;
//		} else {
//			View commentitem = null;
//
//			if (convertView != null
//					&& convertView.findViewById(R.id.tvUsername) != null) {
//				Log.d("getview", "doGetView-------get TextView-----------"
//						+ position);
//				commentitem = convertView;
//			} else {
//				Log.d("getview", "doGetView-------new TextView-----------"
//						+ position);
//
//				commentitem = LayoutInflater.from(context).inflate(
//						R.layout.commentitem, null);
//			}
//			vh.tvCreateAt = (TextView) commentitem
//					.findViewById(R.id.tvCreatedDate);
//			vh.rbRating = (RatingBar) commentitem
//					.findViewById(R.id.indicator_ratingbar);
//			vh.tvComment = (TextView) commentitem.findViewById(R.id.tvComment);
//			vh.tvUserName = (TextView) commentitem
//					.findViewById(R.id.tvUsername);
//
//			vh.tvCreateAt
//					.setText(items.get(position).getProperty(6).toString());
//			vh.tvUserName.setText(((User) items.get(position).getProperty(8))
//					.getProperty(2).toString());
//			vh.tvComment.setText(items.get(position).getProperty(4).toString());
//			vh.rbRating.setRating(Float.valueOf(items.get(position)
//					.getProperty(5).toString()));
//
//			return commentitem;
//		}
//	}
//
//	private static class ViewHolder {
//		TextView tvUserName;
//		TextView tvComment;
//		TextView tvCreateAt;
//		RatingBar rbRating;
//	}
//
//	private ViewHolder vh = new ViewHolder();
//
//}