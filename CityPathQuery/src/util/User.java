package util;

import java.io.Serializable;

/**
 * 包util为用于存放公共类的文件夹；
 * <b>注册用户</b>的个人信息类
 * <b>说明</b>： 实现Serializable接口是为了实现TCP上的对象传输
 * @author FanShiqing
 * @version 1.0
 */
public class User implements Serializable{
	
	//private int ID;				// 注册用户编号
	private String userName;		// 用户名
	private String password;	// 密码
	private String email;		// 注册邮箱
	private String homeCity;	// 常居住的城市
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户类的构造函数
	 * @param userName
	 * @param password
	 * @param email
	 * @param homeCity
	 */
	public User(String userName, String password, String email, String homeCity) {
		assert(userName != null);
		
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.homeCity = homeCity;
	}
	/*
	public int getID() {
		return ID;
	}
	*/
	/**
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomeCity() {
		return homeCity;
	}
	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}
	
	
}
