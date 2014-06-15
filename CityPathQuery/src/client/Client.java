package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import models.mapItems.AbstractMap;
import models.mapItems.Map;
import models.query.Advice;
import models.query.Query;
import util.TranslateMapFile;
import util.User;

/**
 * �ͻ��˳���
 * �Զ���Ľӿ�CommType�����˿ͻ���--������֮��ͨ�ŵ���Ϣ����
 * @author FanShiqing
 *
 */
public class Client implements CommProtocol{
	
	private static Socket socket;								// �������ͨ�ŵ��׽���
	private static ObjectOutputStream toServer;					// ���͵��������Ķ���
	private static ObjectInputStream fromServer;				// �������Է������Ķ���
	
	private static Client client;
	private static User user;
	private static Map map;
	private static AbstractMap abstractMap;
	
	static {
		client = new Client();
	}
	
	public static void clientInit(String userName) {
		map = TranslateMapFile.translateMap();
		abstractMap = new AbstractMap(map);
		user = new User(userName, null, null, null);
	}
	
	public static User getUser() {
		if (user == null) {
			user = new User("default", null, null, null);
		}
		return user;
	}
	
	/*public static void setUser(User arg) {
		user = arg;
	}*/
	
	public static Map getMap() {
		if (map == null) {
			map = TranslateMapFile.translateMap();
			abstractMap = new AbstractMap(map);
		}
		return map;
	}
	
	public static AbstractMap getAbstractMap() {
		if (abstractMap == null) {
			map = TranslateMapFile.translateMap();
			abstractMap = new AbstractMap(map);
		}
		return abstractMap;
	}
	
	/**
	 * ����һ���ͻ������ʾ��
	 * һ�����еĿͻ��˵����в������������ж����static���͵�client����
	 * @return
	 */
	public static Client getInstance() {
		return client;
	}
	
	/**
	 * 
	 */
	public Client() {
		try {
			socket = new Socket("172.26.18.105", 8000);		// �������ӵ�server���׽���
			toServer = new ObjectOutputStream(socket.getOutputStream());
			//toServer.flush();
			fromServer = new ObjectInputStream(socket.getInputStream());
		} 
		catch(IOException ex) {
			System.out.println("Failed to connect to server\n");
			ex.printStackTrace();
		}
		// Connect to server successfully
		System.out.println("Connect to server successfully!\n");
	}
	
	/**
	 * ��ȡ��server���Ͷ�������������
	 * @return
	 */
	public static ObjectOutputStream getToServer() {
		return toServer;
	}
	/**
	 * �ر�client��server��TCP����
	 * @return �ɹ��ر�socketʱ����true�����򷵻�false
	 */
	public static boolean closeConnection() {
		
		/**
		 * ע�⣺����ֶ��ͷ�toServer,fromServer����Դ����ô������server
		 *      ��socket reset�쳣����C���Բ�ͬ�����ǿ��Է��İ��������ա���Դ
		 *      ���չ�������Java���������ջ��ƣ���������ע�͵����ֶ��ͷ���Դ�Ĵ���
		 *      
		 */
		/*
		try {
			// �رյ���������TCP������Դ
			toServer.close();
			fromServer.close();
			
			//socket.close();  // �رյĻ��������java.net.SocketException: Connection reset�쳣
			return true;
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return false;
		}
		*/
		return true;
	}
	
	/**
	 * �ͻ������˳�ϵͳʱ���ñ�������֪server
	 */
	public static void exitSystem() {
		try {
			/**
			 * ��srever�����˳���Ϣ
			 */
			toServer.writeObject(String.valueOf(EXIT_SYSTEM_REQUEST));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		try {
			/**
			 * ��server��ȡ��Ӧ��Ϣ
			 */
			fromServer.readObject();
			for(int i = 0;i < 100000;i ++) {
				/**
				 * wait some time to close
				 */
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * �����������ȡ����¼��Ϣ
	 */
	public static void sendLoginCancleMsg() {
		try {
			toServer.writeObject(String.valueOf(LOGIN_CANCLE));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * ��server����ȡ��ע����Ϣ
	 */
	public static void sendRegisterCancleMsg() {
		try {
			toServer.writeObject(String.valueOf(REGISTER_CANCLE));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * �ͻ�������������͵�¼����
	 * @param userName �û���
	 * @param password ��¼����
	 */
	public static void sendLoginRequest(String userName, String password) {
		try {
			/*
			 * ͨ��Э��˵����client������server����ͨ����Ϣ���ͣ�
			 * 			     �����η����û���������
			 */
			toServer.writeObject(String.valueOf(LOGIN_REQUEST));	// ��intת����String�����ٷ���
			toServer.writeObject(userName);							// �����û���
			toServer.writeObject(password);							// ��������
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * client ����server���ص��Լ��ĵ�¼�������Ӧ 
	 */
	public static int resolveLoginResponse() {
		int result;
		try {
			result = Integer.parseInt((String)fromServer.readObject());
		}
		catch(Exception ex) {
			result = LOGIN_EXCEPTINO;
			ex.printStackTrace();
		}
		switch(result) {
		case LOGIN_USER_NOT_EXIST:		// �û���������
			JOptionPane.showMessageDialog(null, "��������û��������ڣ�", "��¼����", JOptionPane.ERROR_MESSAGE);
			
			break;
		case LOGIN_PASSWORD_WRONG: 		// �������
			JOptionPane.showMessageDialog(null, "��������û������벻ƥ�䣡", "��¼����", JOptionPane.ERROR_MESSAGE);
			break;
		case LOGIN_SUCCESS:				// ��¼�ɹ�
			System.out.println("��¼�ɹ���");
			break;
		case LOGIN_EXCEPTINO:			// ��¼�쳣
			JOptionPane.showMessageDialog(null, "��¼ʱ�����쳣�����Ժ����ԣ�", "��¼����", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			// here should never be reached
			assert(false);
		}
		
		return result;
	}
	
	/**
	 * ʹ���������ע����������û���ʱ���ñ��������������û����Ƿ��Ѿ���ʹ��
	 * @param userName
	 * @return ���û���userName��ʹ��ʱ����true�����򷵻�false
	 */
	public static int isUserNameUsedCheck(String userName) {
		int result = -1;
		try {
			/*
			 * ͨ��Э��˵����client������server����ͨ����Ϣ���ͣ�
			 * 			     �����η����û���������
			 */
			toServer.writeObject(String.valueOf(REGISTER_IS_USERNAME_USED));	// ��intת����String�����ٷ���
			toServer.writeObject(userName);										// �����û���
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
		try {
			/*
			 * ͨ��Э��˵����client���ղ�����server���صĴ�����
			 */
			result = Integer.parseInt((String)fromServer.readObject());
			switch(result) {
			case REGISTER_USERNAME_IS_OCCUPIED:									// ��ע����û����ѱ�ռ��
				JOptionPane.showMessageDialog(null, "����ע����û����ѱ�ռ�ã�", "ע�����", JOptionPane.ERROR_MESSAGE);
				break;
			case REGISTER_USERNAME_IS_NEW:										// ��ע����û���δ��ռ��
				break;
			case REGISTER_EXCEPTION:
				JOptionPane.showMessageDialog(null, "ע��ʱ�����쳣�����Ժ����ԣ�", "ע�����", JOptionPane.ERROR_MESSAGE);
				break;
			default:
				// here should never be reached
				assert(false);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * <b>��������</b>ע���µ��û�
	 * <b>Э������</b>��client�ȷ���REGISTER_NEW_USERͨ�ű�־λ���������η���
	 * 					�û��������롢���䡢��ס���У�֮��ȴ�����server����Ӧ���
	 * @param userName	
	 * @param password
	 * @param email
	 * @param homeCity
	 * @return �����û�ע��ɹ�ʱ����true�����򷵻�false
	 */
	public static boolean registerNewUser(String userName, String password, String email, String homeCity) {
		int result = -1;
		try {
			toServer.writeObject(String.valueOf(REGISTER_NEW_USER));	// ��intת����String�����ٷ���
			toServer.writeObject(userName);								// �����û���
			toServer.writeObject(password);								// ��������
			toServer.writeObject(email);								// ��������
			toServer.writeObject(homeCity);								// ���ͳ�פ����
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		try {
			result = Integer.parseInt((String)fromServer.readObject());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		if(result == REGISTER_SUCCESS) {		// ע��ɹ�
			return true;
		}
		else {									// ע��ʧ��
			return false;
		}
			
	}
	
	/**
	 * �����ݿ�����µ����ۼ�¼
	 * @param userName	�û���
	 * @param startLocationName ��ʼ�ص�����
	 * @param endLocationName	��ֹ�ص�����
	 * @param midLocationName   ;���ص�����
	 * @return ����server���²�ѯ��ʷ�ɹ�ʱ����true�����򷵻�false
	 */
	public static boolean insertNewQueryToServer(String userName, String startLocationName, String endLocationName, String midLocationName) {
		int result = -1;
		assert(userName != null);
		try {
			toServer.writeObject(String.valueOf(QUERY_ADD_NEW_QUERY));
			toServer.writeObject(userName);
			toServer.writeObject(startLocationName);
			toServer.writeObject(endLocationName);
			toServer.writeObject(midLocationName);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		/**
		 * ����server����Ӧ
		 */
		try {
			result = Integer.parseInt((String)fromServer.readObject());
			if(result == QUERY_ADD_NEW_QUERY_SUCCESS) {				// ���²�ѯ��¼�ɹ�
				return true;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}	
		
		return false;			// ���²�ѯ��¼ʧ��			
	}
	
	/**
	 * �����û�����ѯ���ѯ��ʷ
	 * @param userName
	 * @return һ������ѯ���ɵ�ArryList
	 */
	public static ArrayList<Query> getQueriesListByUserName(String userName) {
		ArrayList<Query> result = new ArrayList<Query>();
		try {
			toServer.writeObject(String.valueOf(QUERY_SELECT_QUERY_LIST_BY_USERNAME));
			toServer.writeObject(userName);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
		try {
			/**
			 * ����server���ؽ��,�������صĽ��������ת����ArrayList<Query>����
			 */
			@SuppressWarnings("unchecked")
			Vector<Vector<Object>> vector = (Vector<Vector<Object>>)fromServer.readObject();
			for(int i = 0;i < vector.size();i ++) {
				Vector<Object> t = vector.get(i);
				System.out.println("userName: " + t.get(1) + ", from: " + t.get(2) + ", to: " + t.get(3) 
									+ ", pass: " + t.get(4) + ", query time: " + t.get(6));
				Query query = new Query((String)t.get(1),			// userName
										(String)t.get(2),			// start location name
										(String)t.get(3),			// end location name
										(String)t.get(4),			// mid location namem
										(String)t.get(6));			// query time(history)
				result.add(query);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * ����getQueriesListByUserName(String userName)�Ĳ��Ժ���
	 */
	private void test_getQueriesListByUserName() {
		ArrayList<Query> result = Client.getQueriesListByUserName("fsq");
		System.out.println(result.size());
		
		for(int i = 0;i < result.size();i ++) {
			Query query = result.get(i);
			System.out.println("user name: " + query.getUserName()
							   + ", from: " + query.getStartLocationName()
							   + ", to: " + query.getEndLocationName()
							   + ", pass: " + query.getMidLocationName()
							   + ", queryTime: " + query.getQueryTime());
		}
	}
	
	/**
	 * ����·��ID��ѯ�������ƽ������
	 * @param pathUnitID
	 * @return 
	 */
	public static double getGradeByPathUnitID(int pathUnitID) {
		double result = -1.0;
		try {
			/**
			 * ������server���������ź���·��ID
			 */
			toServer.writeObject(String.valueOf(GRADE_GET_GRADE_BY_PATHUNITID));
			toServer.writeObject(String.valueOf(pathUnitID));		
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		try {
			/**
			 * ���ܷ��������صĴ�����
			 */
			result = Double.parseDouble((String)fromServer.readObject());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * ���û���ĳ��·������ʱ�����ñ���������server���¸�·�ε�����
	 * @param pathUnitID ���۵�·��ID
	 * @param grade ���δ��
	 * @return server�˸��³ɹ�ʱ����true�����򷵻�false
	 */
	public static boolean updateGradeByPathUnitID(int pathUnitID, double grade) {
		try {
			/**
			 * ������server���������ź���·��ID����������
			 */
			toServer.writeObject(String.valueOf(GRADE_UPDATE_GRADE_BY_PATHUNITID));
			toServer.writeObject(String.valueOf(pathUnitID));
			toServer.writeObject(String.valueOf(grade));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
		try {
			/**
			 * ���ܷ��������صĴ�����
			 */
			if(Integer.parseInt((String)fromServer.readObject()) == GRADE_UPDATE_GRADE_SUCCESS) {
				return true;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * ����·��ID��ѯ��·���ϵ���������
	 * @param pathUnitID
	 * @return
	 */
	public static ArrayList<Advice> getCommentsByPathUnitID(int pathUnitID) {
		ArrayList<Advice> result = new ArrayList<Advice>();
		try {
			/**
			 * ������server���������ź���·��ID
			 */
			toServer.writeObject(String.valueOf(COMMENT_QUERY_BY_PATHUNITID));
			toServer.writeObject(String.valueOf(pathUnitID));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		try {
			/**
			 * ���շ��������صĴ�����
			 */
			@SuppressWarnings("unchecked")
			Vector<Vector<Object>> vector = (Vector<Vector<Object>>)fromServer.readObject();
			for(int i = 0;i < vector.size();i ++) {
				Vector<Object> t = vector.get(i);
				
				// ��ȡ�����ֶ�
				int commentID = ((Integer)t.get(0)).intValue();
				String userName = (String)t.get(1);
				int pathUnitID_temp = ((Integer)t.get(2)).intValue();
				String commentTime = (String)t.get(3);
				int likedTimes = ((Integer)t.get(4)).intValue();
				String contentText = (String)t.get(5);
				System.out.println("commentID: " + commentID + ", userName: " + userName + ", pathUnitID: " + pathUnitID_temp
									+ ", commentTime: " + commentTime + ", likedTimes: " + likedTimes + ", contentText: " + contentText);
				Advice advice = new Advice(commentID, userName, pathUnitID_temp, commentTime, likedTimes, contentText);
				result.add(advice);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * �����û�����ѯ����ʷ�����б�
	 * @param userName
	 * @return
	 */
	public static ArrayList<Advice> getCommentsByUserName(String userName) {
		ArrayList<Advice> result = new ArrayList<Advice>();
		try {
			/**
			 * ������server���������ź����û���
			 */
			toServer.writeObject(String.valueOf(COMMENT_QUERY_BY_USERNAME));
			toServer.writeObject(userName);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		try {
			/**
			 * ���շ��������صĴ�����
			 */
			@SuppressWarnings("unchecked")
			Vector<Vector<Object>> vector = (Vector<Vector<Object>>)fromServer.readObject();
			for(int i = 0;i < vector.size();i ++) {
				Vector<Object> t = vector.get(i);
				
				// ��ȡ�����ֶ�
				int commentID = ((Integer)t.get(0)).intValue();
				String userName_temp = (String)t.get(1);
				int pathUnitID = ((Integer)t.get(2)).intValue();
				String commentTime = (String)t.get(3);
				int likedTimes = ((Integer)t.get(4)).intValue();
				String contentText = (String)t.get(5);
				System.out.println("commentID: " + commentID + ", userName: " + userName_temp + ", pathUnitID: " + pathUnitID
									+ ", commentTime: " + commentTime + ", likedTimes: " + likedTimes + ", contentText: " + contentText);
				Advice advice = new Advice(commentID, userName_temp, pathUnitID, commentTime, likedTimes, contentText);
				result.add(advice);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * ��server�ύ���µ�[�ı����͵�]���ۣ�server������µ����۲������ݿ��У������ز���ִ�н��
	 * @param userName
	 * @param pathUnitID
	 * @param contentText
	 * @return ���µ����۸��µ����ݿ�����ɹ�ʱ����true�����򷵻�false
	 */
	public static boolean insertNewTextComment(String userName, int pathUnitID, String contentText) {
		try {
			/**
			 * ���η��Ͳ������͡��û��������۵�·��ID���ı����͵���������
			 */
			toServer.writeObject(String.valueOf(COMMENT_ADD_TEXT_COMMENT));
			toServer.writeObject(userName);
			toServer.writeObject(String.valueOf(pathUnitID));
			toServer.writeObject(contentText);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
		try {
			if(Integer.parseInt((String)fromServer.readObject()) == COMMENT_ADD_SUCCESS) {
				return true;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		// ִ�и���ʧ��
		return false;
	}
	
	/**
	 * ������������Ĳ��Ժ���������ڣ����û����������������ȷ�Բ���
	 */
	public void test() {
		test_getQueriesListByUserName();
	}
	/**
	 * ���ڱ�����������Ĳ���
	 * @param args
	 */
	public static void main(String[] args) {
		if(Client.insertNewTextComment("fsq", 4, "this is my test comment!"))
			System.out.println("add new comment successfully!");
		/*
		ArrayList<Query> result = Client.getQueriesListByUserName("fsq");
		System.out.println(result.size());
		
		for(int i = 0;i < result.size();i ++) {
			Query query = result.get(i);
			System.out.println("user name: " + query.getUserName()
							   + ", from: " + query.getStartLocationName()
							   + ", to: " + query.getEndLocationName()
							   + ", pass: " + query.getMidLocationName()
							   + ", queryTime: " + query.getQueryTime());
		}
		return ;
		*/
	}
	
}









