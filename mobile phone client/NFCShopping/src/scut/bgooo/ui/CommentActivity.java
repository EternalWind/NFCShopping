package scut.bgooo.ui;

import java.io.File;

import java.util.HashMap;
import java.util.Map;

import scut.bgooo.utility.INFCActivity;
import scut.bgooo.utility.Task;
import scut.bgooo.utility.TaskHandler;

import java.util.Vector;

import scut.bgooo.concern.ConcernItem;
import scut.bgooo.entities.Discount;
import scut.bgooo.entities.DiscountItem;
import scut.bgooo.entities.Product;
import scut.bgooo.entities.Review;
import scut.bgooo.webservice.WebServiceUtil;

import weibo4android.Weibo;
import weibo4android.WeiboException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class CommentActivity extends Activity implements INFCActivity {

	private EditText mComment;
	private Button mShareCommitButton;
	private Button mCommitButton;
	private TextView mProductName;
	private ConcernItem mItem;
	private RatingBar mRatingbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment);

		mItem = (ConcernItem) getIntent().getSerializableExtra("mItem");

		mComment = (EditText) findViewById(R.id.etComment);
		mShareCommitButton = (Button) findViewById(R.id.btShareAndCommit);
		mCommitButton = (Button) findViewById(R.id.btCommit);
		mRatingbar = (RatingBar) findViewById(R.id.ratingbar);
		// mWeibo = (Weibo)bundle.get("WEIBO");

		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret",
				Weibo.CONSUMER_SECRET);

		mShareCommitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Weibo mWeibo = new Weibo();
				if (WeiboUserListActivity.defaultUserInfo == null) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"��ȷ��΢���û�", Toast.LENGTH_LONG);
					toast.show();
					return;
				} else {
					Log.i("token",
							WeiboUserListActivity.defaultUserInfo.GetAToken());
					Log.i("token",
							WeiboUserListActivity.defaultUserInfo.GetASecret());
					String commitStr = "�Ҷ�" + "#" + mItem.getName() + "#��������\n"
							+ mComment.getText().toString() + "�Ҹ�����"
							+ mRatingbar.getRating() + "��------#YY����#";

					HashMap<String, String> m = new HashMap<String, String>();
					m.put("COMMIT", commitStr);
					Task task = new Task(Task.SEND_COMMENT_WEIBO, m);
					TaskHandler.addTask(task);
					finish();
				}

			}
		});

		mCommitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Review review = new Review();
				review.setProperty(2, 2);// ��ʱ��û�������û��߼�
				review.setProperty(3, mItem.getProductId());
				review.setProperty(4, mComment.getText().toString());
				review.setProperty(5, mRatingbar.getRating() * 10); // ע��Ҫ��������ת��ΪString���ܴ�����̨
				if (WebServiceUtil.getInstance().AddReview(review)) {
					Toast.makeText(getApplicationContext(), "�ύ�ɹ�", 2000)
							.show();
				} else {
					Toast.makeText(getApplicationContext(), "�ύʧ��", 2000)
							.show();
				}

			}
		});

		TaskHandler.allActivity.put(CommentActivity.class.getSimpleName(),
				CommentActivity.this);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		String result = (String) param[0];
		if (result.equals("OK")) {
			Toast toast = Toast.makeText(getApplicationContext(), "΢�����ͳɹ�",
					Toast.LENGTH_LONG);
			toast.show();
		}

	}

}
