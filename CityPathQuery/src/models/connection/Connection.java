package models.connection;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import models.user.UserInfo;

/**
 * �������������֮��������࣬������������˽������ӣ����ø����ӷ���������Ҫ��������˷��͵��������յ��ظ��󷵻�
 * @author ������
 *
 */
public class Connection {
	
	private Socket socket;
	
	public static String serverAddr = "127.0.0.1";
	public static int serverPort = 7898;
	
	public Connection() {
		
		//��������
		try {
			socket = new Socket(serverAddr, serverPort);
		} catch (UnknownHostException e) {
			System.out.println("unknowhost");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("wrong socket");
			e.printStackTrace();
		}
		
		//��ȡ���������
//		try {
//			in = new DataInputStream(socket.getInputStream());
//			out =new DataOutputStream(socket.getOutputStream());
//		}
//		catch (IOException e){
//			System.out.println("can't get stream");
//			e.printStackTrace();
//		}
	}
	
	private int sendTypeToServer(int type){
		DataOutputStream out;
		try {
			out =new DataOutputStream(socket.getOutputStream());
			out.writeInt(type);
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return -1;
	}
	
	private int sendObjecctToServer(Serializable obj){
		ObjectOutputStream out;
		try {
			out =new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(obj);
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public Object recvObjectFromServer(Class<?> type){
		
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(socket.getInputStream());
			Object recvObj = in.readObject();
			while (!type.isInstance(recvObj)) {
				System.out.println("warning recv data wrong!!!!!");
				recvObj = in.readObject();
			}
			return in;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	
	
	public int sendRegisterReq(UserInfo userInfo) {
		sendTypeToServer(ConnType.TYPE_REGISTER);
		sendObjecctToServer(userInfo);
		
		return 1;
	}
	
	public int sendLoginReq(String userName, String passWord) {
		
		sendTypeToServer(ConnType.TYPE_LOGIN);
		sendObjecctToServer(userName);
		sendObjecctToServer(passWord);
		
		return 1;
	}
}
