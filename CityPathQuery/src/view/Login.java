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
import javax.swing.JTextField;

import client.Client;


public class Login {

	private JFrame frame;
	private JTextField jtfUserName;
	private JTextField jtfPasswd;
	
	private static String userNameTip = "�������û���";
	private static String passwdTip = "����������";
	
	private String userName;	// �û���
	private String passwd;		// ����

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);	// ������м���ʾ
		
		jtfUserName = new JTextField();
		jtfUserName.setBounds(159, 83, 135, 28);
		frame.getContentPane().add(jtfUserName);
		jtfUserName.setColumns(10);
		
		jtfPasswd = new JTextField();
		jtfPasswd.setColumns(10);
		jtfPasswd.setBounds(159, 121, 135, 28);
		frame.getContentPane().add(jtfPasswd);
		
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
		
		jtfPasswd.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// ��ȡ����ʱ
			{
				jtfPasswd.setText("");
			}
			public void focusLost(FocusEvent e)		// ����ʧȥʱ
			{
				if(jtfPasswd.getText().trim().length() == 0)
					jtfPasswd.setText(passwdTip);
			}
		});
		
		// ��¼��ť��ȡ����ť��Ӽ����¼�
		jbtLogin.addActionListener(new LoginListener());
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
	public String getPasswd() {
		return passwd;
	}
	
	/**
	 * �ڲ��ࣺ��¼��ť�ļ����¼�
	 * @author FanShiqing
	 *
	 */
	private class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			userName = jtfUserName.getText().trim();
			passwd = jtfPasswd.getText().trim();
			if(userName.equals(userNameTip) || userName.equals("")) {
				JOptionPane.showMessageDialog(null, "�û�������Ϊ�գ�");
				return ;
			}
			if(passwd.equals(passwdTip) || passwd.equals("")) {
				JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
				return ;
			}
			else {
				System.out.println("�û������������Ϊ��...");
				// ��������������TCP����
				Client client = new Client();
//				if(client.connToServer())
//					System.out.println("�ɹ���������������TCP����...");
//				else
//					System.out.println("��������������ʧ��...");
				
				// ������������û����������Լ����Ч��
				return ;
			}
		}
	}
}
