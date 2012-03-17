package scut.bgooo.concern;

import java.util.List;

import scut.bgooo.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ConcernItemAdapter extends BaseAdapter {

	private Context mContext; // ����������
	private List<ConcernItem> mItems; // ��Ʒ��Ϣ����

	public ConcernItemAdapter(Context context, List<ConcernItem> items) {
		this.mContext = context;
		this.mItems = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mItems.get(position).getId();
	}

	/**
	 * ÿһ��listitem��Ķ���
	 * 
	 * @author �ʸ�
	 * 
	 */
	private class ViewHolder {
		public ImageView mImageView;// ͼƬ��
		public TextView mGoodsNmae;// ��ʵ��Ʒ��
		public TextView mGoodsPrice;// ��ʵ��Ʒ�۸�
		public RatingBar mGoodScore;// ������
	}

	private ViewHolder vh = new ViewHolder();

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View concernitem = null;
		if (convertView != null) {
			concernitem = convertView;
		} else {
			concernitem = LayoutInflater.from(mContext).inflate(
					R.layout.productitem, null);
		}

		vh.mImageView = (ImageView) concernitem.findViewById(R.id.goods_image);
		vh.mGoodScore = (RatingBar) concernitem.findViewById(R.id.score);
		vh.mGoodsNmae = (TextView) concernitem.findViewById(R.id.name);
		vh.mGoodsPrice = (TextView) concernitem.findViewById(R.id.price);

		ConcernItem item = mItems.get(position);

		vh.mImageView.setBackgroundColor(R.drawable.logo);
		vh.mGoodScore.setRating(item.getRating());
		vh.mGoodsNmae.setText(item.getName());
		vh.mGoodsPrice.setText(String.valueOf(item.getPrice()));
		return concernitem;
	}

}
