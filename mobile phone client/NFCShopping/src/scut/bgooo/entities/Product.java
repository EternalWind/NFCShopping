package scut.bgooo.entities;

/**
 * ��Ʒ��Ϣʵ����
 * */
public class Product implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;    //��Ʒid
	private String barcode; // ��Ʒ����������
	private String Name;   //��Ʒ��
	private int AverageRating;   //��Ʒƽ���÷�
	private float Price;  //��Ʒ�۸�

	public int getId() {
		return Id;
	}

	public String getName() {
		return Name;
	}

	public int getAverageRating() {
		return AverageRating;
	}

	public float getPrice() {
		return Price;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBarcode() {
		return barcode;
	}
}
