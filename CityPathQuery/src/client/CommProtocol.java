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
	public static final int COMM_TYPE_REGISTER = 1;				// ע��
	public static final int COMM_TYPE_LOGIN = 2;				// ��¼
	public static final int COMM_TYPE_LOGOUT = 3;				// �˳���¼
	public static final int COMM_TYPE_QUERY = 4;				// ��ѯ
	
	/**
	 * ��¼����ʹ��
	 * Client�����¼����ʱserver�˵���Ϣ��Ӧ����
	 */
	public static final int LOGIN_USER_NOT_EXIST = 5;			// ָ���û�������
	public static final int LOGIN_PASSWORD_WRONG = 6;			// ���벻��ȷ
	public static final int LOGIN_SUCCESS = 7;					// ��¼�ɹ�
	public static final int LOGIN_EXCEPTINO = -1;				// server����client��¼����ʱ�����쳣
	public static final int LOGIN_CANCLE = 9;					// ȡ����¼
	
	/**
	 * ע�����
	 * <b>��;����</b>��client��ע�����û�ʱ����û����Ƿ��Ѿ���ע���
	 */
	public static final int REGISTER_IS_USERNAME_USED = 10;		// client��ע�����û�ʱ����û����Ƿ��Ѿ���ע���
	public static final int REGISTER_USERNAME_IS_OCCUPIED = 11;	// ��ע����û����Ѿ���ռ��
	public static final int REGISTER_USERNAME_IS_NEW = 12;		// ��ע����û���δ��ռ��
	public static final int REGISTER_EXCEPTION = 13;			// server�ڴ���ע���û�����������ʱ�����쳣
	public static final int REGISTER_CANCLE = 14;				// clientȡ��ע�����û�
	public static final int REGISTER_NEW_USER = 15;				// client��ע���µ��û������������Ѿ������Ϸ��Լ�飩
	public static final int REGISTER_SUCCESS = 16;				// ע�����û��ɹ�
	public static final int REGISTER_FAILED = 17;				// ע�����û�ʧ��
}
