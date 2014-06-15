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
			socket = new Socket("172.26.18.105", 8000);		// 创建连接到server的套接字
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
		
		/**
		 * 注意：如果手动释放toServer,fromServer的资源，那么会引起server
		 *      的socket reset异常，与C语言不同，我们可以放心把垃圾回收、资源
		 *      回收工作留给Java的垃圾回收机制，所以这里注释掉了手动释放资源的代码
		 *      
		 */
		/*
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
		*/
		return true;
	}
	
	/**
	 * 客户端在退出系统时调用本函数告知server
	 */
	public static void exitSystem() {
		try {
			/**
			 * 向srever发送退出消息
			 */
			toServer.writeObject(String.valueOf(EXIT_SYSTEM_REQUEST));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		try {
			/**
			 * 从server读取响应消息
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
	 * 向数据库插入新的评论记录
	 * @param userName	用户名
	 * @param startLocationName 起始地点名字
	 * @param endLocationName	终止地点名字
	 * @param midLocationName   途径地点名字
	 * @return 当向server更新查询历史成功时返回true，否则返回false
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
		 * 接受server的响应
		 */
		try {
			result = Integer.parseInt((String)fromServer.readObject());
			if(result == QUERY_ADD_NEW_QUERY_SUCCESS) {				// 更新查询记录成功
				return true;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}	
		
		return false;			// 更新查询记录失败			
	}
	
	/**
	 * 根据用户名查询其查询历史
	 * @param userName
	 * @return 一条条查询构成的ArryList
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
			 * 接受server返回结果,并将返回的结果的类型转换成ArrayList<Query>类型
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
	 * 方法getQueriesListByUserName(String userName)的测试函数
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
	 * 根据路段ID查询其所获的平均分数
	 * @param pathUnitID
	 * @return 
	 */
	public static double getGradeByPathUnitID(int pathUnitID) {
		double result = -1.0;
		try {
			/**
			 * 依次向server发送握手信号与路段ID
			 */
			toServer.writeObject(String.valueOf(GRADE_GET_GRADE_BY_PATHUNITID));
			toServer.writeObject(String.valueOf(pathUnitID));		
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		try {
			/**
			 * 接受服务器返回的处理结果
			 */
			result = Double.parseDouble((String)fromServer.readObject());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 当用户给某个路段评分时，调用本函数请求server更新该路段的评分
	 * @param pathUnitID 评价的路段ID
	 * @param grade 本次打分
	 * @return server端更新成功时返回true，否则返回false
	 */
	public static boolean updateGradeByPathUnitID(int pathUnitID, double grade) {
		try {
			/**
			 * 依次向server发送握手信号与路段ID、本次评分
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
			 * 接受服务器返回的处理结果
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
	 * 根据路段ID查询该路段上的所有评论
	 * @param pathUnitID
	 * @return
	 */
	public static ArrayList<Advice> getCommentsByPathUnitID(int pathUnitID) {
		ArrayList<Advice> result = new ArrayList<Advice>();
		try {
			/**
			 * 依次向server发送握手信号与路段ID
			 */
			toServer.writeObject(String.valueOf(COMMENT_QUERY_BY_PATHUNITID));
			toServer.writeObject(String.valueOf(pathUnitID));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		try {
			/**
			 * 接收服务器返回的处理结果
			 */
			@SuppressWarnings("unchecked")
			Vector<Vector<Object>> vector = (Vector<Vector<Object>>)fromServer.readObject();
			for(int i = 0;i < vector.size();i ++) {
				Vector<Object> t = vector.get(i);
				
				// 提取各个字段
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
	 * 根据用户名查询其历史评论列表
	 * @param userName
	 * @return
	 */
	public static ArrayList<Advice> getCommentsByUserName(String userName) {
		ArrayList<Advice> result = new ArrayList<Advice>();
		try {
			/**
			 * 依次向server发送握手信号与用户名
			 */
			toServer.writeObject(String.valueOf(COMMENT_QUERY_BY_USERNAME));
			toServer.writeObject(userName);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		try {
			/**
			 * 接收服务器返回的处理结果
			 */
			@SuppressWarnings("unchecked")
			Vector<Vector<Object>> vector = (Vector<Vector<Object>>)fromServer.readObject();
			for(int i = 0;i < vector.size();i ++) {
				Vector<Object> t = vector.get(i);
				
				// 提取各个字段
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
	 * 向server提交的新的[文本类型的]评论，server将会把新的评论插入数据库中，并返回操作执行结果
	 * @param userName
	 * @param pathUnitID
	 * @param contentText
	 * @return 当新的评论更新到数据库操作成功时返回true，否则返回false
	 */
	public static boolean insertNewTextComment(String userName, int pathUnitID, String contentText) {
		try {
			/**
			 * 依次发送操作类型、用户名、评论的路段ID、文本类型的评论内容
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
		// 执行更新失败
		return false;
	}
	
	/**
	 * 本类各个方法的测试函数调用入口，仅用户本类各个方法的正确性测试
	 */
	public void test() {
		test_getQueriesListByUserName();
	}
	/**
	 * 用于本类各个方法的测试
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









