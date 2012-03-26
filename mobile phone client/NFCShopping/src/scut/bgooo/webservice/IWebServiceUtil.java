package scut.bgooo.webservice;

import java.util.List;

import scut.bgooo.entities.Comment;
import scut.bgooo.entities.Discount;
import scut.bgooo.entities.Paging;
import scut.bgooo.entities.Product;
import scut.bgooo.entities.User;

public interface IWebServiceUtil {
	
	/**
	 * <p>
	 * ��¼�жϽӿ�
	 * 
	 * @return ����User����
	 * */
	public User login(String userName, String password);

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
	public Product getProductById(String barcode);

	/**
	 * <p>
	 * ��ȡ�����µ�20�������б�
	 * 
	 * @return ���������б�
	 * 
	 * */
	public List<Comment> getCommentsByMe();

	/**
	 * <p>
	 * ��ȡ����Paging��ȡ�����б�
	 * 
	 * @param Paging
	 *            Paging ����
	 * @return ���������б�
	 * 
	 * */
	public List<Comment> getCommentsByMe(Paging page);

	/**
	 * 
	 * <p>
	 * ͨ����Ʒid��ȡ��Ʒ������20������
	 * 
	 * @param id
	 *            ��Ʒ��barcode ��������
	 * @return �����б�
	 */
	public List<Comment> getComments(int id);

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
	public List<Comment> getComments(String barcode, Paging page);

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
