package scut.bgooo.concern;

public class ConcernItem  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ��������ݿ��ID��
	 * */
	private int Id;
	/**
	 * �ӷ�������ȡ������ƷID��
	 * */
	private int ProductId;
	/**
	 * �ӷ�������ȡ������Ʒ��Name
	 * */
	private String Name;
	/**
	 * �ӷ�������ȡ������Ʒ����Type
	 * */
	private int Type;
	/**
	 * �ӷ�������ȡ������Ʒ�۸�
	 * */
	private float Price;
	/**
	 * �ӷ�������ȡ������Ʒ������
	 * */
	private int Rating;
	/**
	 * ��¼��ӵ�ʱ��
	 * */
	private long Timestamp;
	/**
	 * ��Ǹļ�¼�Ƿ��ղ�
	 * */
	private short IsCollected;

	/**
	 * ����Ǵ����ݿ��ȡitem�Ĺ��캯����Ҫ����id
	 * */
	public ConcernItem(int id, int productId, String name, int type,
			float price, int rating, long timestamp, short iscollected) {
		Id = id;
		ProductId = productId;
		Name = name;
		Type = type;
		Rating = rating;
		Price = price;
		Timestamp = timestamp;
		IsCollected = iscollected;
	}
	
	public ConcernItem(long timestamp){
		Id = 0;
		Timestamp = timestamp;
	}

	public int getId() {
		return Id;
	}

	public int getProductId() {
		return ProductId;
	}

	public String getName() {
		return Name;
	}

	public int getType() {
		return Type;
	}

	public int getRating() {
		return Rating;
	}

	public float getPrice() {
		return Price;
	}

	public long getTimestamp() {
		return Timestamp;
	}

	public short getIsCollected() {
		return IsCollected;
	}
	
	public void setIsCollected(short collected){
		IsCollected=collected;
	}
}
