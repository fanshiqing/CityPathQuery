package view;
/** 
 * ע���û��ĵ�¼����
 * Modified: 
 * 		@author FanShiqing
 * 		�����¼������
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.Client;
import client.CommProtocol;

public class Login {

	private JFrame frame;
	private JTextField jtfUserName;				// �û���
	private JPasswordField jpfPasswd;			// ����
	
	private static String userNameTip = "�������û���";
	private static String passwdTip = "      ";	// 6���ո�
	
	private String userName;					// �û���
	private String password;					// ����

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		// Set the frame to be visible
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 280);
		/*
		 *	���ô��ڵĹر���Ϊ
		 * 	EXIT_ON_CLOSE���ûᵼ�¹ر��Ӵ���ʱ���״���ͬʱ�ر�
		 *  ���Ը���DISPOSE_ON_CLOSE
		 */
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);					// ������м���ʾ
		frame.setTitle("����·����ѯ������ϵͳ����SE_TEAM22");	// ���õ�¼�������
		
		jtfUserName = new JTextField();
		jtfUserName.setBounds(159, 83, 135, 28);
		frame.getContentPane().add(jtfUserName);
		jtfUserName.setColumns(10);
		
		jpfPasswd = new JPasswordField();
		jpfPasswd.setColumns(10);
		jpfPasswd.setBounds(159, 121, 135, 28);
		frame.getContentPane().add(jpfPasswd);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("����", Font.PLAIN, 15));
		label.setBounds(88, 90, 61, 22);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		label_1.setFont(new Font("����", Font.PLAIN, 15));
		label_1.setBounds(87, 129, 61, 22);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u8BF7  \u767B  \u5F55");
		label_2.setFont(new Font("����", Font.BOLD, 24));
		label_2.setBounds(122, 16, 160, 49);
		frame.getContentPane().add(label_2);
		
		JButton jbtLogin = new JButton("\u767B\u9646");
		jbtLogin.setFont(new Font("����", Font.BOLD, 15));
		jbtLogin.setBounds(108, 172, 72, 23);
		frame.getContentPane().add(jbtLogin);
		
		JButton jbtCancle = new JButton("\u53D6\u6D88");
		jbtCancle.setFont(new Font("����", Font.BOLD, 15));
		jbtCancle.setBounds(213, 172, 72, 23);
		frame.getContentPane().add(jbtCancle);
		
		/**
		 * The following 
		 * @authored by FanShiqing
		 * 
		 */
		// �ı�����ӽ����¼�
		jtfUserName.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// ��ȡ����ʱ
			{
				jtfUserName.setText("");
			}
			public void focusLost(FocusEvent e)		// ����ʧȥʱ
			{
				if(jtfUserName.getText().trim().length() == 0)
					jtfUserName.setText(userNameTip);
			}
		});
		
		jpfPasswd.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// ��ȡ����ʱ
			{
				jpfPasswd.setText("");
			}
			public void focusLost(FocusEvent e)		// ����ʧȥʱ
			{
				// Do nothing
				//if(new String(jpfPasswd.getPassword()).length() == 0)
				//	jpfPasswd.setText(passwdTip);	// 6���ո�
			}
		});
		
		/**
		 *  ��¼��ť��ȡ����ť��Ӽ����¼�
		 */
		jbtLogin.addActionListener(new LoginListener());
		jbtCancle.addActionListener(new CancleListener());
	}
	
	/**
	 * �رյ�¼����
	 */
	private void closeFrame() {
		frame.setVisible(false);
	}
	/**
	 * �����û�������û���
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * �����û����������
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * ��֤�û���������ɹ��󣬵�¼�ɹ�����ת��������
	 * 
	 */
	private void landOn() {
		Client.clientInit(userName);
		
		Query queryFrame = new Query(this.userName);
		queryFrame.setVisible(true);				// ���ò�ѯ���ڿɼ�
		
		frame.setVisible(false);					// ���ñ����ڲ��ɼ�
		Welcome.closeFrame(); 						// �ر�ϵͳ��ӭ����
	}
	/**
	 * �ڲ��ࣺ��¼��ť�ļ����¼�
	 * @author FanShiqing
	 *
	 */
	private class LoginListener implements ActionListener, CommProtocol {
		public void actionPerformed(ActionEvent e) {
			userName = jtfUserName.getText().trim();
			password = new String(jpfPasswd.getPassword());
			if(userName.equals(userNameTip) || userName.equals("")) {
				JOptionPane.showMessageDialog(null, "�û�������Ϊ��!", "��������", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			if(password.equals(passwdTip) || password.equals("")) {
				JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�", "��������", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			else {
				Client.sendLoginRequest(userName, password);				// ��server�����û����������Ѽ����½���
				if(Client.resolveLoginResponse() == LOGIN_SUCCESS) {
					landOn();
				}
				return ;
			}
		}
	}
	
	/**
	 * �ڲ��ࣺȡ����¼��ť�ļ����¼�
	 * @author FanShiqing
	 *
	 */
	private class CancleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int m = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�˳���¼ϵͳ��", "��Ϣ", JOptionPane.YES_NO_OPTION);
			if(m == JOptionPane.YES_OPTION) {	// ȷ���˳���¼ϵͳ����������server����ȡ����¼��Ϣ��Ȼ��رյ�server��socket���ӣ��ͷ���Դ
				Client.sendLoginCancleMsg();
				Client.closeConnection();	
				closeFrame(); 					// �رյ�¼����
			}
		}
	}
}










