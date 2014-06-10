package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * �ͻ��˳���
 * @author FanShiqing
 *
 */
public class Client {
	
	private Socket socket;								// �������ͨ�ŵ��׽���
	private ObjectOutputStream toServer;				// ���͵��������Ķ���
	private ObjectInputStream fromServer;				// �������Է������Ķ���
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
	 * �ͻ��˽�����������������
	 * @return true:���ӳɹ�
	 * 			false:����ʧ��
	 */
	
	
	/**
	 * for test only
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client();
		
		return ;
	}
	
}
