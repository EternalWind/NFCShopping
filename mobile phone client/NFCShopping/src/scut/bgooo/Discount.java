package scut.bgooo;

/**
 *
 * �Ż�ʵ���࣬��Ӧ���Ż���Ϣ
 * @author Leeforall
 * @since 2012��3��16��
 */
public class Discount {

	private int Id;
	private int ProductId;
	private String Description;
	private String StartDate;
	private String EndDate;
	private float Discount;
	private String ImageUrl;

	public String getStartDate() {
		return StartDate;
	}

	public int getId() {
		return Id;
	}

	public int getProductId() {
		return ProductId;
	}

	public String getDescription() {
		return Description;
	}

	public String getEndDate() {
		return EndDate;
	}

	public float getDiscount() {
		return Discount;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

}
