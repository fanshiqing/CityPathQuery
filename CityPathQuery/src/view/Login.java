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
import javax.swing.JTextField;

import client.Client;


public class Login {

	private JFrame frame;
	private JTextField jtfUserName;
	private JTextField jtfPasswd;
	
	private static String userNameTip = "请输入用户名";
	private static String passwdTip = "请输入密码";
	
	private String userName;	// 用户名
	private String passwd;		// 密码

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
		frame.setLocationRelativeTo(null);	// 输入框中间显示
		
		jtfUserName = new JTextField();
		jtfUserName.setBounds(159, 83, 135, 28);
		frame.getContentPane().add(jtfUserName);
		jtfUserName.setColumns(10);
		
		jtfPasswd = new JTextField();
		jtfPasswd.setColumns(10);
		jtfPasswd.setBounds(159, 121, 135, 28);
		frame.getContentPane().add(jtfPasswd);
		
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
		
		jtfPasswd.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)	// 获取焦点时
			{
				jtfPasswd.setText("");
			}
			public void focusLost(FocusEvent e)		// 焦点失去时
			{
				if(jtfPasswd.getText().trim().length() == 0)
					jtfPasswd.setText(passwdTip);
			}
		});
		
		// 登录按钮、取消按钮添加监听事件
		jbtLogin.addActionListener(new LoginListener());
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
	public String getPasswd() {
		return passwd;
	}
	
	/**
	 * 内部类：登录按钮的监听事件
	 * @author FanShiqing
	 *
	 */
	private class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			userName = jtfUserName.getText().trim();
			passwd = jtfPasswd.getText().trim();
			if(userName.equals(userNameTip) || userName.equals("")) {
				JOptionPane.showMessageDialog(null, "用户名不能为空！");
				return ;
			}
			if(passwd.equals(passwdTip) || passwd.equals("")) {
				JOptionPane.showMessageDialog(null, "密码不能为空！");
				return ;
			}
			else {
				System.out.println("用户名、密码均不为空...");
				// 建立到服务器的TCP连接
				Client client = new Client();
//				if(client.connToServer())
//					System.out.println("成功建立到服务器的TCP连接...");
//				else
//					System.out.println("到服务器的连接失败...");
				
				// 向服务器发送用户名、密码以检查有效性
				return ;
			}
		}
	}
}
