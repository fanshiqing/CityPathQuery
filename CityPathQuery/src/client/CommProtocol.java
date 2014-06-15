/**
	 *  �ýӿڶ��˿ͻ���--������֮��ͨ��Э���һЩ����
	 *  ÿ��ʹ����Щ�����������ʵ������ӿ�
	 *  @author FanShiqing
	*/
package client;
public interface CommProtocol {
	
	/**
	 * Client-Serverͨ����Ϣ����
	 * Ҫ��ÿ�η��;������Ϣ֮ǰ��client��Ҫ����������Ϣ����֮һ��
	 * �����Ļ���������֪��Ӧ�����ν���ʲô���ݲ��������Ӧ��
	 */
	//public static final int COMM_TYPE_REGISTER = 1;					// ע��
	//public static final int COMM_TYPE_LOGIN = 2;					// ��¼
	//public static final int COMM_TYPE_LOGOUT = 3;					// �˳���¼
	//public static final int COMM_TYPE_QUERY = 4;					// ��ѯ
	
	/**
	 * Client�����¼����ʱserver�˵���Ϣ��Ӧ����
	 */
	public static final int LOGIN_REQUEST = 1;						// client�����¼����
	public static final int LOGIN_USER_NOT_EXIST = 2;				// ָ���û�������
	public static final int LOGIN_PASSWORD_WRONG = 3;				// ���벻��ȷ
	public static final int LOGIN_SUCCESS = 4;						// ��¼�ɹ�
	public static final int LOGIN_EXCEPTINO = 5;					// server����client��¼����ʱ�����쳣
	public static final int LOGIN_CANCLE = 6;						// ȡ����¼
	
	/**
	 * ע�����
	 * <b>��;����</b>��client��ע�����û�ʱ����û����Ƿ��Ѿ���ע���
	 */
	public static final int REGISTER_IS_USERNAME_USED = 11;			// client��ע�����û�ʱ����û����Ƿ��Ѿ���ע���
	public static final int REGISTER_USERNAME_IS_OCCUPIED = 12;		// ��ע����û����Ѿ���ռ��
	public static final int REGISTER_USERNAME_IS_NEW = 13;			// ��ע����û���δ��ռ��
	public static final int REGISTER_EXCEPTION = 14;				// server�ڴ���ע���û�����������ʱ�����쳣
	public static final int REGISTER_CANCLE = 15;					// clientȡ��ע�����û�
	public static final int REGISTER_NEW_USER = 16;					// client��ע���µ��û������������Ѿ������Ϸ��Լ�飩
	public static final int REGISTER_SUCCESS = 17;					// ע�����û��ɹ�
	public static final int REGISTER_FAILED = 18;					// ע�����û�ʧ��
	
	/**
	 * db_comment���۱���صĲ���Э��
	 */
	public static final int COMMENT_QUERY_BY_PATHUNITID = 21;		// ����·��ID��ѯ����·�ε������б�
	public static final int COMMENT_QUERY_BY_USERNAME = 22;			// �����û�����ѯ���û�����������
	public static final int COMMENT_ADD_TEXT_COMMENT = 23;			// ����ı����͵�����
	public static final int COMMENT_ADD_PICTURE_COMMENT = 24;		// ���ͼƬ���͵�����
	public static final int COMMENT_ADD_VEDIO_COMMENT = 25;			// �����Ƶ���͵�����
	
	/**
	 * db_grades·����ֱ�Ĳ���Э��
	 */
	public static final int GRADE_GET_GRADE_BY_PATHUNITID = 31;		// ����·��IDȡ��·�ε�ƽ���÷�
	public static final int GRADE_GET_GRADE_EXCEPTION = 32;		// ��ѯ·�ε÷�ʱ�����쳣
	public static final int GRADE_UPDATE_GRADE_BY_PATHUNITID = 33;	// �û���·�δ�ֺ�������ݿ�
	public static final int GRADE_UPDATE_GRADE_FAILED = 34;		// ����·�ε÷�ʱ�����쳣
	public static final int GRADE_UPDATE_GRADE_SUCCESS = 35;		// ����·�ε÷�ʱ�����쳣
	
	/**
	 * db_queries��Ĳ���Э��
	 * 
	 */
	public static final int QUERY_ADD_NEW_QUERY = 41;				// client�ύ�µĲ�ѯ��¼
	public static final int QUERY_ADD_NEW_QUERY_SUCCESS = 42;		// server�˸��²�ѯ��ʷ�ɹ�
	public static final int QUERY_ADD_NEW_QUERY_FAILED = 43;		// server�˸��²�ѯ��ʷ�ɹ�
	public static final int QUERY_SELECT_QUERY_LIST_BY_USERNAME = 44;		// server�˸��²�ѯ��ʷ�ɹ�
	
}
