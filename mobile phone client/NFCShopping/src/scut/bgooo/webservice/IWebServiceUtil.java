package scut.bgooo.webservice;

import java.util.List;
import java.util.Vector;

import scut.bgooo.entities.Review;
import scut.bgooo.entities.Discount;
import scut.bgooo.entities.Paging;
import scut.bgooo.entities.Product;
import scut.bgooo.entities.Suggestion;
import scut.bgooo.entities.User;

public interface IWebServiceUtil {
	
	
	public boolean AddReview(Review review);
	
	public boolean AddSuggestion(Suggestion suggestion);
	
	/**
	 * <p>
	 * ��¼�жϽӿ�
	 * 
	 * @return ����User����
	 * */
	public User login(String userName, String password);
	
	public User regist(String userName,String password,int gencer);

	/**
	 * <p>
	 * ͨ���û���id��ȡ���û�����Ϣ
	 * <p>
	 * ����ͨ�����۵�ʱ��鿴�û�����Ϣ
	 * 
	 * @param id
	 *            �û���id
	 * @return ����User����
	 * */
	public User getUser(int id);

	/**
	 * <p>
	 * ��ȡ���µ�20���Ż���Ϣ�ӿ�
	 * 
	 * @return �Ż���Ϣ�б�
	 * 
	 * */
	public List<Discount> getDiscounts();

	/**
	 * <p>
	 * ���ݴ���ķ�ҳ�����ȡ�Ż���Ϣ�ӿ�
	 * 
	 * @return �Ż���Ϣ�б�
	 * */
	public List<Discount> getDiscounts(Paging page);
	
	

	/**
	 * <p>
	 * �����Żݵ�id��ȡ�Żݵ���ϸ��Ϣ
	 * 
	 * @param id
	 *            �Ż�id
	 * @return �Ż���Ϣ
	 * */
	public Discount getDiscount(int id);

	/**
	 * <p>
	 * ͨ����Ʒid��ȡ��Ʒ����
	 * 
	 * @param id
	 *            ��Ʒ��barcode ��������
	 * 
	 * @return ������Ʒ�Ķ���
	 * 
	 * */
	public Product getProductByBarcode(String barcode);

	/**
	 * <p>
	 * ��ȡ�����µ�20�������б�
	 * 
	 * @return ���������б�
	 * 
	 * */
	public Vector<Review> getReviewsByMe();

	/**
	 * <p>
	 * ��ȡ����Paging��ȡ�����б�
	 * 
	 * @param Paging
	 *            Paging ����
	 * @return ���������б�
	 * 
	 * */
	public Vector<Review> getReviewsByMe(Paging page);

	/**
	 * 
	 * <p>
	 * ͨ����Ʒid��ȡ��Ʒ������20������
	 * 
	 * @param id
	 *            ��Ʒ��barcode ��������
	 * @return �����б�
	 */
	public Vector<Review> getReviewsByProductId(int id);

	/**
	 * 
	 * <p>
	 * ͨ����Ʒid��Paging�����ȡ��Ʒ������
	 * 
	 * @param barcode
	 *            ��Ʒ��barcode ��������
	 * @param page
	 *            Paging ��ҳ����
	 * @return �����б�
	 */
	public Vector<Review> getReviewsByProductId(int id, Paging page);

	/**
	 * 
	 * <p>
	 * ͨ����Ʒ��id��ȡ��Ʒ������
	 * 
	 * @param id
	 *            ��Ʒ��barcode ��������
	 * @param page
	 *            Paging ��ҳ����
	 * @return �����б�
	 */
	public List<String> getAttributes(String barcode);

}
