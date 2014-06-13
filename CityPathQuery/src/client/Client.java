package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

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
			socket = new Socket("localhost", 8000);		// �������ӵ�server���׽���
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
			socket.close();
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
			toServer.writeObject(String.valueOf(COMM_TYPE_LOGIN));	// ��intת����String�����ٷ���
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
	 * for test only
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client();
		
		return ;
	}
	
}
