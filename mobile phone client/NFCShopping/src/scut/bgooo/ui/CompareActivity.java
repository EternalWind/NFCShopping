package scut.bgooo.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Toast;

public class CompareActivity extends Activity {

	private ListView mCompareList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compare);

		/*
		 * http://blog.csdn.net/hellogv/article/details/6075014
		 * �о�������ƵĹ���ʵ�֡��βο�������������������������
		 */
		mCompareList = (ListView) findViewById(R.id.lvCompare);
		ArrayList<TableRow> table = new ArrayList<TableRow>();
		TableCell[] titles = new TableCell[5];// ÿ��5����Ԫ
		int width = this.getWindowManager().getDefaultDisplay().getWidth()
				/ titles.length;
		// �������
		for (int i = 0; i < titles.length; i++) {
			titles[i] = new TableCell("����" + String.valueOf(i), width + 8 * i,
					LayoutParams.FILL_PARENT, TableCell.STRING);
		}
		table.add(new TableRow(titles));
		// ÿ�е�����
		TableCell[] cells = new TableCell[5];// ÿ��5����Ԫ
		for (int i = 0; i < cells.length - 1; i++) {
			cells[i] = new TableCell("No." + String.valueOf(i),
					titles[i].width, LayoutParams.FILL_PARENT, TableCell.STRING);
		}
		cells[cells.length - 1] = new TableCell(R.drawable.icon,
				titles[cells.length - 1].width, LayoutParams.WRAP_CONTENT,
				TableCell.IMAGE);
		// �ѱ�������ӵ����
		for (int i = 0; i < 12; i++)
			table.add(new TableRow(cells));
		TableAdapter tableAdapter = new TableAdapter(this, table);
		mCompareList.setAdapter(tableAdapter);
		mCompareList.setOnItemClickListener(new ItemClickEvent());
	}

	class ItemClickEvent implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Toast.makeText(CompareActivity.this,
					"ѡ�е�" + String.valueOf(arg2) + "��", 500).show();
		}
	}

}
