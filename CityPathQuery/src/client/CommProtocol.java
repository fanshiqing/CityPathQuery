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
	//public static final int COMM_TYPE_REGISTER = 1;					// 注册
	//public static final int COMM_TYPE_LOGIN = 2;					// 登录
	//public static final int COMM_TYPE_LOGOUT = 3;					// 退出登录
	//public static final int COMM_TYPE_QUERY = 4;					// 查询
	
	/**
	 * Client发起登录请求时server端的消息响应类型
	 */
	public static final int LOGIN_REQUEST = 1;						// client发起登录请求
	public static final int LOGIN_USER_NOT_EXIST = 2;				// 指定用户不存在
	public static final int LOGIN_PASSWORD_WRONG = 3;				// 密码不正确
	public static final int LOGIN_SUCCESS = 4;						// 登录成功
	public static final int LOGIN_EXCEPTINO = 5;					// server处理client登录请求时发生异常
	public static final int LOGIN_CANCLE = 6;						// 取消登录
	
	/**
	 * 注册界面
	 * <b>用途描述</b>：client在注册新用户时检查用户名是否被已经被注册过
	 */
	public static final int REGISTER_IS_USERNAME_USED = 11;			// client在注册新用户时检查用户名是否被已经被注册过
	public static final int REGISTER_USERNAME_IS_OCCUPIED = 12;		// 新注册的用户名已经被占用
	public static final int REGISTER_USERNAME_IS_NEW = 13;			// 新注册的用户名未被占用
	public static final int REGISTER_EXCEPTION = 14;				// server在处理注册用户名查重请求时发送异常
	public static final int REGISTER_CANCLE = 15;					// client取消注册新用户
	public static final int REGISTER_NEW_USER = 16;					// client端注册新的用户（各个表项已经经过合法性检查）
	public static final int REGISTER_SUCCESS = 17;					// 注册新用户成功
	public static final int REGISTER_FAILED = 18;					// 注册新用户失败
	
	/**
	 * db_comment评论表相关的操作协议
	 */
	public static final int COMMENT_QUERY_BY_PATHUNITID = 21;		// 根据路径ID查询该条路段的评论列表
	public static final int COMMENT_QUERY_BY_USERNAME = 22;			// 根据用户名查询该用户的所有评论
	public static final int COMMENT_ADD_TEXT_COMMENT = 23;			// 添加文本类型的评论
	public static final int COMMENT_ADD_PICTURE_COMMENT = 24;		// 添加图片类型的评论
	public static final int COMMENT_ADD_VEDIO_COMMENT = 25;			// 添加视频类型的评论
	
	/**
	 * db_grades路径打分表的操作协议
	 */
	public static final int GRADE_GET_GRADE_BY_PATHUNITID = 31;		// 根据路段ID取该路段的平均得分
	public static final int GRADE_GET_GRADE_EXCEPTION = 32;		// 查询路段得分时发生异常
	public static final int GRADE_UPDATE_GRADE_BY_PATHUNITID = 33;	// 用户对路段打分后更新数据库
	public static final int GRADE_UPDATE_GRADE_FAILED = 34;		// 更新路段得分时发送异常
	public static final int GRADE_UPDATE_GRADE_SUCCESS = 35;		// 更新路段得分时发送异常
	
	/**
	 * db_queries表的操作协议
	 * 
	 */
	public static final int QUERY_ADD_NEW_QUERY = 41;				// client提交新的查询记录
	public static final int QUERY_ADD_NEW_QUERY_SUCCESS = 42;		// server端更新查询历史成功
	public static final int QUERY_ADD_NEW_QUERY_FAILED = 43;		// server端更新查询历史成功
	public static final int QUERY_SELECT_QUERY_LIST_BY_USERNAME = 44;		// server端更新查询历史成功
	
}
