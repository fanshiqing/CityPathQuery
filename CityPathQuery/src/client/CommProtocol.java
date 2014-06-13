/**
	 *  该接口定了客户端--服务器之间通信协议的一些常量
	 *  每个使用这些常量的类必须实现这个接口
	 *  @author FanShiqing
	*/
package client;
public interface CommProtocol {
	
	/**
	 * Client-Server通信消息类型
	 * 要求每次发送具体的消息之前，client需要发送以下消息类型之一，
	 * 这样的话服务器便知道应当依次接受什么数据并做如何响应。
	 */
	public static final int COMM_TYPE_REGISTER = 1;				// 注册
	public static final int COMM_TYPE_LOGIN = 2;				// 登录
	public static final int COMM_TYPE_LOGOUT = 3;				// 退出登录
	public static final int COMM_TYPE_QUERY = 4;				// 查询
	
	/**
	 * Client发起登录请求时server端的消息响应类型
	 */
	public static final int LOGIN_USER_NOT_EXIST = 5;			// 指定用户不存在
	public static final int LOGIN_PASSWORD_WRONG = 6;			// 密码不正确
	public static final int LOGIN_SUCCESS = 7;					// 登录成功
	public static final int LOGIN_EXCEPTINO = -1;				// server处理client登录请求时发生异常
	public static final int LOGIN_CANCLE = 9;					// 取消登录
}
