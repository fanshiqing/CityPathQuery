package models.user;

import java.io.Serializable;

//
//



/**
 * 描述：这个类定义了用户信息的数据结构<br>
 * userID和name在创建时确定，无法修改，其余结构提供get和set函数
 * @author 董剑峰
 *
 */
public class UserInfo implements Serializable {
	private int ID;
	private String name;
	private String password;
	private String email;
	private String homeCity;
	
	
	
	/**
	 * 最小初始化函数，创建用户信息时必须有这三条信息
	 * @param userID
	 * @param name
	 * @param password
	 */
	public UserInfo(int userID,String name,String password){
		
		this.email = "";
		this.homeCity = "";
	}
	
	
	/**
	 * 一般初始化函数
	 * @param userID
	 * @param name
	 * @param password
	 * @param email 当没有值时，传入空字符串""
	 * @param homeCity 当没有值时，传入空字符串""
	 */
	public UserInfo(int userID,String name,String password,String email,String homeCity){
		
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
	public int getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
}
