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
	
	private String userName;				// 用户名
	private String password;				// 密码
	private String email;					// 邮箱
	private String homeCity;				// 常驻城市
	
	private JTextField jtfUserName;			// 用户名
	private JPasswordField jpfPassword;		// 密码
	private JPasswordField jpfPassword2;	// 重复密码
	private JTextField jtfEmail;			// 邮箱
	private JTextField jtfHomeCity;			// 常驻城市
	
	// 按钮
	private JButton jbtRegister;			// 注册按钮
	private JButton jbtCancle;				// 取消注册按钮
	
	// 标签
	private JLabel jlblTitle;
	private JLabel jlblUserName;
	private JLabel jlblPassword;
	private JLabel jlblPassword2;
	private JLabel jlblEmail;
	private JLabel jlblHomeCity;
	
	
	private String userNameTip = "请输入用户名";
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
		this.setTitle("城市路径建议及查询系统——注册界面");	// 设置窗口标题
		
		setBounds(100, 100, 400, 360);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		jlblTitle = new JLabel("请输入以下信息");
		jlblTitle.setBounds(109, 25, 198, 42);
		jlblTitle.setFont(new Font("宋体", Font.BOLD, 20));
		contentPanel.add(jlblTitle);
		
		jlblUserName = new JLabel("用 户 名：");
		jlblUserName.setBounds(73, 81, 79, 24);
		jlblUserName.setFont(new Font("宋体", Font.PLAIN, 15));
		contentPanel.add(jlblUserName);
		
		jlblPassword = new JLabel("密    码：");
		jlblPassword.setBounds(73, 115, 79, 24);
		jlblPassword.setFont(new Font("宋体", Font.PLAIN, 15));
		contentPanel.add(jlblPassword);
		
		jlblPassword2 = new JLabel("重复密码：");
		jlblPassword2.setBounds(73, 149, 85, 24);
		jlblPassword2.setFont(new Font("宋体", Font.PLAIN, 15));
		contentPanel.add(jlblPassword2);
		
		jlblEmail = new JLabel("邮    箱：");
		jlblEmail.setBounds(74, 185, 90, 24);
		jlblEmail.setFont(new Font("宋体", Font.PLAIN, 15));
		contentPanel.add(jlblEmail);
		
		jlblHomeCity = new JLabel("常驻城市：");
		jlblHomeCity.setBounds(73, 222, 81, 24);
		jlblHomeCity.setFont(new Font("宋体", Font.PLAIN, 15));
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
		
		jbtRegister = new JButton("注册");
		jbtRegister.setBounds(109, 263, 70, 23);
		jbtRegister.setFont(new Font("宋体", Font.BOLD, 15));
		contentPanel.add(jbtRegister);
		
		jbtCancle = new JButton("取消");
		jbtCancle.setBounds(201, 263, 70, 23);
		jbtCancle.setFont(new Font("宋体", Font.BOLD, 15));
		contentPanel.add(jbtCancle);
		
		this.setLocationRelativeTo(null);				// 设置显示位置居中
		/**
		 * 文本框添加焦点事件
		 */
		// <用户名>文本框添加焦点事件
		jtfUserName.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// 获取焦点时
			{
				jtfUserName.setText("");
			}
			public void focusLost(FocusEvent e)		// 焦点失去时
			{
				if(jtfUserName.getText().trim().length() == 0)
					jtfUserName.setText(userNameTip);
			}
		});
		
		// <密码>(第一次输入)文本框焦点事件
		jpfPassword.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// 获取焦点时
			{
				jpfPassword.setText("");
			}
			public void focusLost(FocusEvent e)		// 焦点失去时
			{
				// Do nothing
			}
		});
		
		// <密码>(第二次输入)文本框焦点事件
		jpfPassword2.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// 获取焦点时
			{
				jpfPassword2.setText("");
			}
			public void focusLost(FocusEvent e)		// 焦点失去时
			{
				// Do nothing 
			}
		});
		
		
		// <邮箱>文本框添加焦点事件
		jtfEmail.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// 获取焦点时
			{
				jtfEmail.setText("");
			}
			public void focusLost(FocusEvent e)		// 焦点失去时
			{
				if(jtfEmail.getText().trim().length() == 0)
					jtfEmail.setText(defaultEmail);
			}
		});
		
		// <常驻城市>文本框添加焦点事件
		jtfHomeCity.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// 获取焦点时
			{
				jtfHomeCity.setText("");
			}
			public void focusLost(FocusEvent e)		// 焦点失去时
			{
				if(jtfHomeCity.getText().trim().length() == 0)
					jtfHomeCity.setText(defaultHomeCity);
			}
		});
		
		/**
		 * 为注册按钮添加监听事件
		 */
		jbtRegister.addActionListener(new RegisterListener());
		jbtCancle.addActionListener(new CancleListener());
		
	}
	
	/**
	 * 跳转到登录界面
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
		
		this.setVisible(false);		// 关闭注册界面
	}
	
	/**
	 * 关闭注册窗口
	 */
	private void closeRegisterFrame() {
		this.setVisible(false);
	}
	
	/**
	 * 内部类
	 * <b>功能描述</b>：用于实现点击注册按钮时的行为
	 * @author FanShiqing
	 *
	 */
	private class RegisterListener implements ActionListener, CommProtocol {
		public void actionPerformed(ActionEvent e) {
			/**
			 * 新用户名检查
			 */
			userName = jtfUserName.getText().trim();
			if(userName.equals("") || userName.equals(userNameTip)) {
				JOptionPane.showMessageDialog(null, "用户名不能为空!", "友情提醒", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			// 检查用户名是否已经被注册
			if(Client.isUserNameUsedCheck(userName) == REGISTER_USERNAME_IS_OCCUPIED)	// 用户名已经被占用 
				return ;
			
			/**
			 * 两次输入密码是否一致检查
			 */
			password = new String(jpfPassword.getPassword());
			String password2 = new String(jpfPassword2.getPassword());
			if(password.equals("")) {
				JOptionPane.showMessageDialog(null, "密码不能为空！", "注册错误", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			if(!password.equals(password2)) {
				JOptionPane.showMessageDialog(null, "两次输入的密码不一致！", "注册错误", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			
			/**
			 * 检查{邮箱}是否为空 
			 */
			email = jtfEmail.getText().trim();
			if(email.equals("")) {
				JOptionPane.showMessageDialog(null, "邮箱不能为空！", "注册错误", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			/**
			 * 检查{常驻城市}是否为空 
			 */
			homeCity = jtfHomeCity.getText().trim();
			if(homeCity.equals("")) {
				JOptionPane.showMessageDialog(null, "城市名不能为空！", "注册错误", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			
			if(Client.registerNewUser(userName, password, email, homeCity)) {
				int result = JOptionPane.showConfirmDialog(null, "新用户注册成功，是否跳转到登录界面？",
															"注册成功", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {		// 跳转到登录界面
					goToLoginFrame();
				}
				else {										// 否则关闭本注册窗口，返回欢迎界面
					closeRegisterFrame();
				}
			}
		}
	}
	
	/**
	 * 内部类
	 * <b>功能描述</b>:点击取消按钮的监听事件
	 * @author FanShiqing
	 *
	 */
	private class CancleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int m = JOptionPane.showConfirmDialog(null, "您确定取消注册吗？", "消息", JOptionPane.YES_NO_OPTION);
			if(m == JOptionPane.YES_OPTION) {	// 确定取消注册，则首先向server发送取消登录消息，然后关闭到server的socket连接，释放资源
				Client.sendRegisterCancleMsg();
				Client.closeConnection();
				closeRegisterFrame();			// 关闭本注册窗口
				System.out.println("成功取消注册新用户");
			}
		}
	}
	
	
}







