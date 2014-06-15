package view;
/** 
 * 注册用户的登录界面
 * Modified: 
 * 		@author FanShiqing
 * 		监听事件的添加
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
	private JTextField jtfUserName;				// 用户名
	private JPasswordField jpfPasswd;			// 密码
	
	private static String userNameTip = "请输入用户名";
	private static String passwdTip = "      ";	// 6个空格
	
	private String userName;					// 用户名
	private String password;					// 密码

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
		 *	设置窗口的关闭行为
		 * 	EXIT_ON_CLOSE设置会导致关闭子窗口时父亲窗口同时关闭
		 *  所以改用DISPOSE_ON_CLOSE
		 */
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);					// 输入框中间显示
		frame.setTitle("城市路径查询及建议系统——SE_TEAM22");	// 设置登录界面标题
		
		jtfUserName = new JTextField();
		jtfUserName.setBounds(159, 83, 135, 28);
		frame.getContentPane().add(jtfUserName);
		jtfUserName.setColumns(10);
		
		jpfPasswd = new JPasswordField();
		jpfPasswd.setColumns(10);
		jpfPasswd.setBounds(159, 121, 135, 28);
		frame.getContentPane().add(jpfPasswd);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(88, 90, 61, 22);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		label_1.setBounds(87, 129, 61, 22);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u8BF7  \u767B  \u5F55");
		label_2.setFont(new Font("宋体", Font.BOLD, 24));
		label_2.setBounds(122, 16, 160, 49);
		frame.getContentPane().add(label_2);
		
		JButton jbtLogin = new JButton("\u767B\u9646");
		jbtLogin.setFont(new Font("宋体", Font.BOLD, 15));
		jbtLogin.setBounds(108, 172, 72, 23);
		frame.getContentPane().add(jbtLogin);
		
		JButton jbtCancle = new JButton("\u53D6\u6D88");
		jbtCancle.setFont(new Font("宋体", Font.BOLD, 15));
		jbtCancle.setBounds(213, 172, 72, 23);
		frame.getContentPane().add(jbtCancle);
		
		/**
		 * The following 
		 * @authored by FanShiqing
		 * 
		 */
		// 文本框添加焦点事件
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
		
		jpfPasswd.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// 获取焦点时
			{
				jpfPasswd.setText("");
			}
			public void focusLost(FocusEvent e)		// 焦点失去时
			{
				// Do nothing
				//if(new String(jpfPasswd.getPassword()).length() == 0)
				//	jpfPasswd.setText(passwdTip);	// 6个空格
			}
		});
		
		/**
		 *  登录按钮、取消按钮添加监听事件
		 */
		jbtLogin.addActionListener(new LoginListener());
		jbtCancle.addActionListener(new CancleListener());
	}
	
	/**
	 * 关闭登录窗口
	 */
	private void closeFrame() {
		frame.setVisible(false);
	}
	/**
	 * 返回用户输入的用户名
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 返回用户输入的密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 验证用户名、密码成功后，登录成功，跳转到主界面
	 * 
	 */
	private void landOn() {
		Client.clientInit(userName);
		
		Query queryFrame = new Query(this.userName);
		queryFrame.setVisible(true);				// 设置查询窗口可见
		
		frame.setVisible(false);					// 设置本窗口不可见
		Welcome.closeFrame(); 						// 关闭系统欢迎界面
	}
	/**
	 * 内部类：登录按钮的监听事件
	 * @author FanShiqing
	 *
	 */
	private class LoginListener implements ActionListener, CommProtocol {
		public void actionPerformed(ActionEvent e) {
			userName = jtfUserName.getText().trim();
			password = new String(jpfPasswd.getPassword());
			if(userName.equals(userNameTip) || userName.equals("")) {
				JOptionPane.showMessageDialog(null, "用户名不能为空!", "友情提醒", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			if(password.equals(passwdTip) || password.equals("")) {
				JOptionPane.showMessageDialog(null, "密码不能为空！", "友情提醒", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			else {
				Client.sendLoginRequest(userName, password);				// 向server发送用户名和密码已检验登陆身份
				if(Client.resolveLoginResponse() == LOGIN_SUCCESS) {
					landOn();
				}
				return ;
			}
		}
	}
	
	/**
	 * 内部类：取消登录按钮的监听事件
	 * @author FanShiqing
	 *
	 */
	private class CancleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int m = JOptionPane.showConfirmDialog(null, "您确定要退出登录系统吗？", "消息", JOptionPane.YES_NO_OPTION);
			if(m == JOptionPane.YES_OPTION) {	// 确定退出登录系统，则首先向server发送取消登录消息，然后关闭到server的socket连接，释放资源
				Client.sendLoginCancleMsg();
				Client.closeConnection();	
				closeFrame(); 					// 关闭登录窗口
			}
		}
	}
}










