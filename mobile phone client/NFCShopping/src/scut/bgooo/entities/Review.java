package scut.bgooo.entities;

import java.util.Date;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Review implements KvmSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Id; // ���۵�id
	private String Text; // ���۵�����
	private int UserId;
	private User User;// ���۵��û�
	private int ProductId;
	private Product Product;// ���۵Ĳ�Ʒ
	// �û����� ��ע��������ʵ�����ֵ�10������
	private int Rating;// �û����� ��ע��������ʵ�����ֵ�10������
	// �û����� ��ע��������ʵ�����ֵ�10�����룬��ʾ���ֻ���ʱҪ����10
	private String CreatedAt;// ���۵�����
	private String EntityKey;

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		Object res = null;
		switch (arg0) {
		case 0:
			res = this.Id;
			break;
		case 1:
			res = this.UserId;
			break;
		case 2:
			res = this.ProductId;
			break;
		case 3:
			res = this.Text;
			break;
		case 4:
			res = this.Rating;
			break;
		case 5:
			res = this.CreatedAt;
			break;
		case 6:
			res = this.Product;
			break;
		case 7:
			res = this.User;
			break;
		case 8:
			res = this.EntityKey;
			break;
		default:
			break;
		}
		return res;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "reviewID";
			break;
		case 1:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "userID";
			break;
		case 2:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "productID";
			break;
		case 3:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "comment";
			break;
		case 4:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "rating";
			break;
		case 5:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "createAt";
			break;
		case 6:
			arg2.type = PropertyInfo.OBJECT_CLASS;
			arg2.name = "Product";
			break;
		case 7:
			arg2.type = PropertyInfo.OBJECT_CLASS;
			arg2.name = "User";
			break;
		case 8:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "EntityKey";
		default:
			break;
		}
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		if (arg1 == null)
			return;
		switch (arg0) {
		case 0:
			this.Id = Integer.valueOf(arg1.toString());
			break;
		case 1:
			this.UserId = Integer.valueOf(arg1.toString());
			break;
		case 2:
			this.ProductId = Integer.valueOf(arg1.toString());
			break;
		case 3:
			this.Text = arg1.toString();
			break;
		case 4:
			this.Rating = Integer.valueOf(arg1.toString());
			break;
		case 5:
			this.CreatedAt = arg1.toString();
			break;
		case 6:
			this.Product = (Product) arg1;
			break;
		case 7:
			this.User = (User) arg1;
			break;
		case 8:
			this.EntityKey = arg1.toString();
		default:
			break;
		}
	}
}