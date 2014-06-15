package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import models.mapItems.AbstractMap;
import models.mapItems.Map;
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
	
	
	static {
		client = new Client();
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
			socket = new Socket("114.212.134.143", 8000);		// �������ӵ�server���׽���
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
	 * for test only
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client();
		
		return ;
	}
	
}
