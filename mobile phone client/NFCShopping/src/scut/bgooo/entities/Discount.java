package scut.bgooo.entities;

import java.util.Date;

/**
 * 
 * �Ż�ʵ���࣬��Ӧ���Ż���Ϣ
 * 
 * @author Leeforall
 * @since 2012��3��16��
 */
public class Discount implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id; // �Ż�ID
	private Product Product; // ��Ʒʵ��
	private String Description; // ����
	private Date StartDate; // ��ʼ����
	private Date EndDate; // ��������
	private float Discount; // �ۿ�
	private String ImageUrl; // ͼƬ·��

	public Date getStartDate() {
		return StartDate;
	}

	public int getId() {
		return Id;
	}

	public Product getProduct() {
		return Product;
	}

	public String getDescription() {
		return Description;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public float getDiscount() {
		return Discount;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

}
