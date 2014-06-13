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
	 * Client�����¼����ʱserver�˵���Ϣ��Ӧ����
	 */
	public static final int LOGIN_USER_NOT_EXIST = 5;			// ָ���û�������
	public static final int LOGIN_PASSWORD_WRONG = 6;			// ���벻��ȷ
	public static final int LOGIN_SUCCESS = 7;					// ��¼�ɹ�
	public static final int LOGIN_EXCEPTINO = -1;				// server����client��¼����ʱ�����쳣
	public static final int LOGIN_CANCLE = 9;					// ȡ����¼
}
