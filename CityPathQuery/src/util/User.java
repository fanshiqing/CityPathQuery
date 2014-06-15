package util;

import java.io.Serializable;

/**
 * ��utilΪ���ڴ�Ź�������ļ��У�
 * <b>ע���û�</b>�ĸ�����Ϣ��
 * <b>˵��</b>�� ʵ��Serializable�ӿ���Ϊ��ʵ��TCP�ϵĶ�����
 * @author FanShiqing
 * @version 1.0
 */
public class User implements Serializable{
	
	//private int ID;				// ע���û����
	private String userName;		// �û���
	private String password;	// ����
	private String email;		// ע������
	private String homeCity;	// ����ס�ĳ���
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * �û���Ĺ��캯��
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
