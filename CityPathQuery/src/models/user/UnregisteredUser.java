package models.user;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import models.connection.*;
import models.query.*;
import models.mapItems.*;

/**
 * 描述：未注册用户类，只能进行注册，登录，查询动作
 * @author 董剑峰
 *
 */
public class UnregisteredUser extends User{
	Connection connection;
	
	public UnregisteredUser(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * 这个函数向服务器提交注册请求
	 * @param registerInfo
	 * @return 注册结果成功/失败
	 */
	public boolean register(UserInfo registerInfo) {
		
		connection.sendRegisterReq(registerInfo);
		
		Boolean b = (Boolean) connection.recvObjectFromServer(Boolean.class);
		
		return b;
	}
		
	
	/**
	 * 这个函数向服务器提交登录请求
	 * @param name
	 * @param password
	 * @return 登录结果
	 */
	public RegisteredUser login(String name,String password){
		
		connection.sendLoginReq(name, password);
		
		//从服务器获取评论历史和查询历史
		UserInfo userInfo = (UserInfo) connection.recvObjectFromServer(UserInfo.class);
		
		ArrayList<Query> queryHistory = (ArrayList<Query>) connection.recvObjectFromServer(ArrayList.class);
		ArrayList<Advice> adviceHistory = (ArrayList<Advice>) connection.recvObjectFromServer(ArrayList.class);
		
		RegisteredUser newUser = new RegisteredUser(userInfo, queryHistory, adviceHistory);
		
		return newUser;
	}

}
