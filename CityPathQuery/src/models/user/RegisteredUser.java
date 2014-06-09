package models.user;

import java.util.ArrayList;

import models.mapItems.Path;
import models.query.Advice;
import models.query.Query;

/**
 * 描述：
 * @author 董剑峰
 *
 */
public class RegisteredUser extends User{
	private UserInfo userInfo;
	private ArrayList<Query> queryHistory;
	private ArrayList<Advice> adviceHistory;
	

	/**
	 *
	 */
	public RegisteredUser(UserInfo userInfo, ArrayList<Query> queryHistory, ArrayList<Advice> adviceHistory) {
		this.userInfo = userInfo;
		this.queryHistory = queryHistory;
		this.adviceHistory = adviceHistory;
	}
	
	/**
	 * 在用户确认发表评论时被调用，向服务器提交发表评论请求，若服务器响应成功<br>
	 * 则向服务器请求新的评论历史并更新本地历史
	 * @param advice
	 * @return 返回结果成功/失败
	 */
	boolean addAdvie (Advice advice) {
		boolean result = false;
		
		
		return result;
	}
	

	/**
	 * 在用户为某一条评论点赞时调用，向服务器发送点赞评论的请求
	 * @param AdviceID
	 * @return 返回结果成功/失败
	 */
	boolean likeAdvice (int AdviceID) {
		boolean result = false;
		
		
		return result;
	}
	
	
	/**
	 * 在用户为路径打分时被调用，向服务器发送路径打分请求
	 * @param path
	 * @param score
	 * @return
	 */
	boolean scorePath (Path path,int score){
		boolean result = false;
		
		
		return result;
	}
	
	/**
	 * 在用户修改用户信息时调用，向服务器发送修改用户信息请求
	 * @param userInfo
	 * @return
	 */
	boolean changeUserInfo (UserInfo userInfo){
		boolean result = false;
		
		
		return result;
	}

	
	/**
	 * 删除评论，需要检查评论的发表者是否为自己，若是则向服务器发删除评论请求
	 * @param advice
	 * @return
	 */
	boolean deleteAdvice (Advice advice){
		boolean result = false;
		
		
		return result;
	}
	
	
	/**
	 * 修改评论，需要检查评论的发表者是否为自己，若是则向服务器发修改评论请求
	 * @param advice
	 * @return
	 */
	boolean changeAdvice (Advice advice){
		boolean result = false;
		
		
		return result;
	}
	
	
	/**
	 * 登出
	 * @return
	 */
	boolean logout() {
		boolean result = false;
		
		
		return result;
	}
	
	//getter and setter
	int getID () {
		return userInfo.getID();
	}
	
	//auto getter and setter
}
