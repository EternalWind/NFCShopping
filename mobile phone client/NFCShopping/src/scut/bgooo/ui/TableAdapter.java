package scut.bgooo.ui;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TableAdapter extends BaseAdapter {

	private Context context;
	private List<TableRow> table;

	public TableAdapter(Context context, List<TableRow> table) {
		this.context = context;
		this.table = table;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return table.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return table.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TableRow tableRow = table.get(position);
		return new TableRowView(this.context, tableRow);
	}

	class TableRowView extends LinearLayout{

		public TableRowView(Context context,TableRow tableRow) {
			super(context);
			// TODO Auto-generated constructor stub
			this.setOrientation(LinearLayout.HORIZONTAL);
			for (int i = 0; i < tableRow.getSize(); i++) {//�����Ԫ��ӵ���
				TableCell tableCell = tableRow.getCellValue(i);
				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
						tableCell.width, tableCell.height);//���ո�Ԫָ���Ĵ�С���ÿռ�
				layoutParams.setMargins(0, 0, 1, 1);//Ԥ����϶����߿�
				if (tableCell.type == TableCell.STRING) {//�����Ԫ���ı�����
					TextView textCell = new TextView(context);
					textCell.setLines(1);
					textCell.setGravity(Gravity.CENTER);
					textCell.setBackgroundColor(Color.BLACK);//������ɫ
					textCell.setText(String.valueOf(tableCell.value));
					addView(textCell, layoutParams);
				} else if (tableCell.type == TableCell.IMAGE) {//�����Ԫ��ͼ������
					ImageView imgCell = new ImageView(context);
					imgCell.setBackgroundColor(Color.BLACK);//������ɫ
					imgCell.setImageResource((Integer) tableCell.value);
					addView(imgCell, layoutParams);
				}
			}
			this.setBackgroundColor(Color.WHITE);//������ɫ�����ÿ�϶��ʵ�ֱ߿�
			
			
		}
		
	}
}
