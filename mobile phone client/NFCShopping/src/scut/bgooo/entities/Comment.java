package scut.bgooo.entities;

import java.util.Date;

public class Comment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int Id;   //���۵�id
	private String Text;  // ���۵�����
	private User User;// ���۵��û�
	private Product Product;// ���۵Ĳ�Ʒ
	private float Rating;// �û�����
	private Date CreatedAt;// ���۵�����

	public Comment(int id, String text, Product product, int rating,
			Date createAt) {
		Id = id;
		Text = text;
		Product = product;
		Rating = rating;
		CreatedAt = createAt;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public Product getProduct() {
		return Product;
	}

	public void setProduct(Product product) {
		Product = product;
	}

	public float getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}

	public Date getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}

	public void setUser(User user) {
		User = user;
	}

	public User getUser() {
		return User;
	}

}
