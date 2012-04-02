package scut.bgooo.webservice;

import java.io.IOException;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import scut.bgooo.entities.Review;
import scut.bgooo.entities.Discount;
import scut.bgooo.entities.Paging;
import scut.bgooo.entities.Product;
import scut.bgooo.entities.User;

public class WebServiceUtil implements IWebServiceUtil {

	private static String TAG = WebServiceUtil.class.getName();

	private static final String NAMESPACE = "http://tempuri.org/";
	private static String URL = "http://192.168.1.107:8080/NFCShopping/ShopWebService.asmx";

	private static final String REGIST = "Regist";
	private static final String ADDSUGGESTION = "AddSuggestion";
	private static final String GETUSER = "GetUser";
	private static final String LOGIN = "Login";

	@Override
	public User login(String userName, String password) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setProperty(1, userName);
		user.setProperty(2, password);
		PropertyInfo pi = getPropertyInfo(user);
		SoapObject rpc = getSoapObject(Method.LOGIN);
		rpc.addProperty(pi);//���Զ������������������
		HttpTransportSE ht = new HttpTransportSE(URL);
		ht.debug = true;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false;//ע�⣺��������Ƕ�dotnetwebserviceЭ���֧��,���dotnet��webservice ��ָ��rpc��ʽ����true����Ҫ��false
		envelope.setOutputSoapObject(rpc);
		envelope.addMapping(NAMESPACE, "User", user.getClass());// ������ʱ���룬����namespace��webservice��ָ���ģ�
																// name�Ƿ��������͵����ƣ�
																// claszz���Զ����������
		/**
		 * ����ķ���ֵ
		 * 
		 * */
		User result = null;
		try {
			ht.call(NAMESPACE + REGIST, envelope);
			result = (User) envelope.getResponse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Discount> getDiscounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Discount> getDiscounts(Paging page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Discount getDiscount(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> getCommentsByMe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> getCommentsByMe(Paging page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> getComments(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> getComments(String barcode, Paging page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAttributes(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User regist(String userName, String password, int gender) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setProperty(1, userName);
		user.setProperty(2, password);
		user.setProperty(3, gender);
		PropertyInfo pi = getPropertyInfo(user);
		SoapObject rpc = getSoapObject(Method.REGIST);
		rpc.addProperty(pi);//���Զ������������������
		HttpTransportSE ht = new HttpTransportSE(URL);
		ht.debug = true;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false;//ע�⣺��������Ƕ�dotnetwebserviceЭ���֧��,���dotnet��webservice ��ָ��rpc��ʽ����true����Ҫ��false
		envelope.setOutputSoapObject(rpc);
		envelope.addMapping(NAMESPACE, "User", user.getClass());// ������ʱ���룬����namespace��webservice��ָ���ģ�
																// name�Ƿ��������͵����ƣ�
																// claszz���Զ����������
		/**
		 * ����ķ���ֵ
		 * */
		User result = null;
		try {
			ht.call(NAMESPACE + REGIST, envelope);
			result = (User) envelope.getResponse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private PropertyInfo getPropertyInfo(Object object) {
		PropertyInfo pi = new PropertyInfo();
		if (object instanceof User) {
			pi.setName("user");
			pi.setValue((User) object);
			pi.setType(((User) object).getClass());
		}
		return pi;
	}

	private SoapObject getSoapObject(int methodID) {
		SoapObject rpc = null;
		switch (methodID) {
		case Method.REGIST:
			rpc = new SoapObject(NAMESPACE, REGIST);
			break;
		case Method.LOGIN:
			rpc = new SoapObject(NAMESPACE, LOGIN);
			break;
		case Method.ADDSUGGESTION:
			rpc = new SoapObject(NAMESPACE, ADDSUGGESTION);
			break;
		case Method.GETUSER:
			rpc = new SoapObject(NAMESPACE, GETUSER);
			break;
		}
		
		return rpc;
	}

	// private Object getResult(){
	// httpTransport.debug = true;
	// envelope.bodyOut = rpc;
	// envelope.dotNet = false;
	// }
}
