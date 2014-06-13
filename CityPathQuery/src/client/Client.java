package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * 客户端程序
 * 自定义的接口CommType给出了客户端--服务器之间通信的消息类型
 * @author FanShiqing
 *
 */
public class Client implements CommProtocol{
	
	private static Socket socket;								// 与服务器通信的套接字
	private static ObjectOutputStream toServer;					// 发送到服务器的对象
	private static ObjectInputStream fromServer;				// 接收来自服务器的对象
	
	private static Client client;
	
	static {
		client = new Client();
	}
	
	/**
	 * 返回一个客户端类的示例
	 * 一个运行的客户端的所有操作均共享本类中定义的static类型的client对象
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
			socket = new Socket("localhost", 8000);		// 创建连接到server的套接字
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
	 * 获取向server发送对象的输出流对象
	 * @return
	 */
	public static ObjectOutputStream getToServer() {
		return toServer;
	}
	/**
	 * 关闭client到server的TCP连接
	 * @return 成功关闭socket时返回true，否则返回false
	 */
	public static boolean closeConnection() {
		try {
			// 关闭到服务器的TCP连接资源
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
	 * 向服务器发送取消登录消息
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
	 * 客户端向服务器发送登录请求
	 * @param userName 用户名
	 * @param password 登录密码
	 */
	public static void sendLoginRequest(String userName, String password) {
		try {
			/*
			 * 通信协议说明：client首先向server发送通信消息类型，
			 * 			     再依次发送用户名，密码
			 */
			toServer.writeObject(String.valueOf(COMM_TYPE_LOGIN));	// 将int转换成String类型再发送
			toServer.writeObject(userName);							// 发送用户名
			toServer.writeObject(password);							// 发送密码
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * client 解析server返回的自己的登录请求的响应 
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
		case LOGIN_USER_NOT_EXIST:		// 用户名不存在
			JOptionPane.showMessageDialog(null, "您输入的用户名不存在！", "登录错误", JOptionPane.ERROR_MESSAGE);
			
			break;
		case LOGIN_PASSWORD_WRONG: 		// 密码错误
			JOptionPane.showMessageDialog(null, "您输入的用户名密码不匹配！", "登录错误", JOptionPane.ERROR_MESSAGE);
			break;
		case LOGIN_SUCCESS:				// 登录成功
			System.out.println("登录成功！");
			break;
		case LOGIN_EXCEPTINO:			// 登录异常
			JOptionPane.showMessageDialog(null, "登录时出现异常，请稍后再试！", "登录错误", JOptionPane.ERROR_MESSAGE);
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
