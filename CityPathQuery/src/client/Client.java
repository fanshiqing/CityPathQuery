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
			socket = new Socket("114.212.134.143", 8000);		// 创建连接到server的套接字
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
			
			//socket.close();  // 关闭的话，会出现java.net.SocketException: Connection reset异常
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
	 * 向server发送取消注册消息
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
			toServer.writeObject(String.valueOf(LOGIN_REQUEST));	// 将int转换成String类型再发送
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
	 * 使用情况：在注册界面输入用户名时调用本函数检查输入的用户名是否已经被使用
	 * @param userName
	 * @return 当用户名userName被使用时返回true，否则返回false
	 */
	public static int isUserNameUsedCheck(String userName) {
		int result = -1;
		try {
			/*
			 * 通信协议说明：client首先向server发送通信消息类型，
			 * 			     再依次发送用户名，密码
			 */
			toServer.writeObject(String.valueOf(REGISTER_IS_USERNAME_USED));	// 将int转换成String类型再发送
			toServer.writeObject(userName);										// 发送用户名
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
		try {
			/*
			 * 通信协议说明：client接收并解析server返回的处理结果
			 */
			result = Integer.parseInt((String)fromServer.readObject());
			switch(result) {
			case REGISTER_USERNAME_IS_OCCUPIED:									// 欲注册的用户名已被占用
				JOptionPane.showMessageDialog(null, "您欲注册的用户名已被占用！", "注册错误", JOptionPane.ERROR_MESSAGE);
				break;
			case REGISTER_USERNAME_IS_NEW:										// 欲注册的用户名未被占用
				break;
			case REGISTER_EXCEPTION:
				JOptionPane.showMessageDialog(null, "注册时出现异常，请稍后再试！", "注册错误", JOptionPane.ERROR_MESSAGE);
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
	 * <b>功能描述</b>注册新的用户
	 * <b>协议描述</b>：client先发送REGISTER_NEW_USER通信标志位，接着依次发送
	 * 					用户名、密码、邮箱、居住城市，之后等待接受server的相应结果
	 * @param userName	
	 * @param password
	 * @param email
	 * @param homeCity
	 * @return 当新用户注册成功时返回true，否则返回false
	 */
	public static boolean registerNewUser(String userName, String password, String email, String homeCity) {
		int result = -1;
		try {
			toServer.writeObject(String.valueOf(REGISTER_NEW_USER));	// 将int转换成String类型再发送
			toServer.writeObject(userName);								// 发送用户名
			toServer.writeObject(password);								// 发送密码
			toServer.writeObject(email);								// 发送邮箱
			toServer.writeObject(homeCity);								// 发送常驻城市
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
		if(result == REGISTER_SUCCESS) {		// 注册成功
			return true;
		}
		else {									// 注册失败
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
