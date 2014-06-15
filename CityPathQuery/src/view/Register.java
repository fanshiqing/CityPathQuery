package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import client.Client;
import client.CommProtocol;


public class Register extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private String userName;				// �û���
	private String password;				// ����
	private String email;					// ����
	private String homeCity;				// ��פ����
	
	private JTextField jtfUserName;			// �û���
	private JPasswordField jpfPassword;		// ����
	private JPasswordField jpfPassword2;	// �ظ�����
	private JTextField jtfEmail;			// ����
	private JTextField jtfHomeCity;			// ��פ����
	
	// ��ť
	private JButton jbtRegister;			// ע�ᰴť
	private JButton jbtCancle;				// ȡ��ע�ᰴť
	
	// ��ǩ
	private JLabel jlblTitle;
	private JLabel jlblUserName;
	private JLabel jlblPassword;
	private JLabel jlblPassword2;
	private JLabel jlblEmail;
	private JLabel jlblHomeCity;
	
	
	private String userNameTip = "�������û���";
	private String defaultEmail = "sqfan6@gmail.com";
	private String defaultHomeCity = "Nanjing";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Register dialog = new Register();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Register() {
		this.setTitle("����·�����鼰��ѯϵͳ����ע�����");	// ���ô��ڱ���
		
		setBounds(100, 100, 400, 360);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		jlblTitle = new JLabel("������������Ϣ");
		jlblTitle.setBounds(109, 25, 198, 42);
		jlblTitle.setFont(new Font("����", Font.BOLD, 20));
		contentPanel.add(jlblTitle);
		
		jlblUserName = new JLabel("�� �� ����");
		jlblUserName.setBounds(73, 81, 79, 24);
		jlblUserName.setFont(new Font("����", Font.PLAIN, 15));
		contentPanel.add(jlblUserName);
		
		jlblPassword = new JLabel("��    �룺");
		jlblPassword.setBounds(73, 115, 79, 24);
		jlblPassword.setFont(new Font("����", Font.PLAIN, 15));
		contentPanel.add(jlblPassword);
		
		jlblPassword2 = new JLabel("�ظ����룺");
		jlblPassword2.setBounds(73, 149, 85, 24);
		jlblPassword2.setFont(new Font("����", Font.PLAIN, 15));
		contentPanel.add(jlblPassword2);
		
		jlblEmail = new JLabel("��    �䣺");
		jlblEmail.setBounds(74, 185, 90, 24);
		jlblEmail.setFont(new Font("����", Font.PLAIN, 15));
		contentPanel.add(jlblEmail);
		
		jlblHomeCity = new JLabel("��פ���У�");
		jlblHomeCity.setBounds(73, 222, 81, 24);
		jlblHomeCity.setFont(new Font("����", Font.PLAIN, 15));
		contentPanel.add(jlblHomeCity);
		
		jtfUserName = new JTextField();
		jtfUserName.setBounds(163, 80, 137, 23);
		contentPanel.add(jtfUserName);
		jtfUserName.setColumns(10);
		
		jpfPassword = new JPasswordField();
		jpfPassword.setBounds(164, 114, 137, 23);
		jpfPassword.setColumns(10);
		contentPanel.add(jpfPassword);
		
		jpfPassword2 = new JPasswordField();
		jpfPassword2.setBounds(164, 149, 137, 23);
		jpfPassword2.setColumns(10);
		contentPanel.add(jpfPassword2);
		
		jtfEmail = new JTextField(defaultEmail);
		jtfEmail.setBounds(163, 183, 137, 23);
		jtfEmail.setColumns(10);
		contentPanel.add(jtfEmail);
		
		jtfHomeCity = new JTextField(defaultHomeCity);
		jtfHomeCity.setBounds(164, 219, 137, 23);
		jtfHomeCity.setColumns(10);
		contentPanel.add(jtfHomeCity);
		
		jbtRegister = new JButton("ע��");
		jbtRegister.setBounds(109, 263, 70, 23);
		jbtRegister.setFont(new Font("����", Font.BOLD, 15));
		contentPanel.add(jbtRegister);
		
		jbtCancle = new JButton("ȡ��");
		jbtCancle.setBounds(201, 263, 70, 23);
		jbtCancle.setFont(new Font("����", Font.BOLD, 15));
		contentPanel.add(jbtCancle);
		
		this.setLocationRelativeTo(null);				// ������ʾλ�þ���
		/**
		 * �ı�����ӽ����¼�
		 */
		// <�û���>�ı�����ӽ����¼�
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
		
		// <����>(��һ������)�ı��򽹵��¼�
		jpfPassword.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// ��ȡ����ʱ
			{
				jpfPassword.setText("");
			}
			public void focusLost(FocusEvent e)		// ����ʧȥʱ
			{
				// Do nothing
			}
		});
		
		// <����>(�ڶ�������)�ı��򽹵��¼�
		jpfPassword2.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// ��ȡ����ʱ
			{
				jpfPassword2.setText("");
			}
			public void focusLost(FocusEvent e)		// ����ʧȥʱ
			{
				// Do nothing 
			}
		});
		
		
		// <����>�ı�����ӽ����¼�
		jtfEmail.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// ��ȡ����ʱ
			{
				jtfEmail.setText("");
			}
			public void focusLost(FocusEvent e)		// ����ʧȥʱ
			{
				if(jtfEmail.getText().trim().length() == 0)
					jtfEmail.setText(defaultEmail);
			}
		});
		
		// <��פ����>�ı�����ӽ����¼�
		jtfHomeCity.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// ��ȡ����ʱ
			{
				jtfHomeCity.setText("");
			}
			public void focusLost(FocusEvent e)		// ����ʧȥʱ
			{
				if(jtfHomeCity.getText().trim().length() == 0)
					jtfHomeCity.setText(defaultHomeCity);
			}
		});
		
		/**
		 * Ϊע�ᰴť��Ӽ����¼�
		 */
		jbtRegister.addActionListener(new RegisterListener());
		jbtCancle.addActionListener(new CancleListener());
		
	}
	
	/**
	 * ��ת����¼����
	 */
	private void goToLoginFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		this.setVisible(false);		// �ر�ע�����
	}
	
	/**
	 * �ر�ע�ᴰ��
	 */
	private void closeRegisterFrame() {
		this.setVisible(false);
	}
	
	/**
	 * �ڲ���
	 * <b>��������</b>������ʵ�ֵ��ע�ᰴťʱ����Ϊ
	 * @author FanShiqing
	 *
	 */
	private class RegisterListener implements ActionListener, CommProtocol {
		public void actionPerformed(ActionEvent e) {
			/**
			 * ���û������
			 */
			userName = jtfUserName.getText().trim();
			if(userName.equals("") || userName.equals(userNameTip)) {
				JOptionPane.showMessageDialog(null, "�û�������Ϊ��!", "��������", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			// ����û����Ƿ��Ѿ���ע��
			if(Client.isUserNameUsedCheck(userName) == REGISTER_USERNAME_IS_OCCUPIED)	// �û����Ѿ���ռ�� 
				return ;
			
			/**
			 * �������������Ƿ�һ�¼��
			 */
			password = new String(jpfPassword.getPassword());
			String password2 = new String(jpfPassword2.getPassword());
			if(password.equals("")) {
				JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�", "ע�����", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			if(!password.equals(password2)) {
				JOptionPane.showMessageDialog(null, "������������벻һ�£�", "ע�����", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			
			/**
			 * ���{����}�Ƿ�Ϊ�� 
			 */
			email = jtfEmail.getText().trim();
			if(email.equals("")) {
				JOptionPane.showMessageDialog(null, "���䲻��Ϊ�գ�", "ע�����", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			/**
			 * ���{��פ����}�Ƿ�Ϊ�� 
			 */
			homeCity = jtfHomeCity.getText().trim();
			if(homeCity.equals("")) {
				JOptionPane.showMessageDialog(null, "����������Ϊ�գ�", "ע�����", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			
			if(Client.registerNewUser(userName, password, email, homeCity)) {
				int result = JOptionPane.showConfirmDialog(null, "���û�ע��ɹ����Ƿ���ת����¼���棿",
															"ע��ɹ�", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {		// ��ת����¼����
					goToLoginFrame();
				}
				else {										// ����رձ�ע�ᴰ�ڣ����ػ�ӭ����
					closeRegisterFrame();
				}
			}
		}
	}
	
	/**
	 * �ڲ���
	 * <b>��������</b>:���ȡ����ť�ļ����¼�
	 * @author FanShiqing
	 *
	 */
	private class CancleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int m = JOptionPane.showConfirmDialog(null, "��ȷ��ȡ��ע����", "��Ϣ", JOptionPane.YES_NO_OPTION);
			if(m == JOptionPane.YES_OPTION) {	// ȷ��ȡ��ע�ᣬ��������server����ȡ����¼��Ϣ��Ȼ��رյ�server��socket���ӣ��ͷ���Դ
				Client.sendRegisterCancleMsg();
				Client.closeConnection();
				closeRegisterFrame();			// �رձ�ע�ᴰ��
				System.out.println("�ɹ�ȡ��ע�����û�");
			}
		}
	}
	
	
}







