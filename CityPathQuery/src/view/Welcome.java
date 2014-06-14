package view;

/**
 * 系统运行的初始界面
 * @author FanShiqing
 *
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Welcome extends JFrame{
	
	private static JFrame frame;
	
	private JButton jbtLandOn;			// 登录按钮
	private JButton jbtRegister;		// 注册按钮
	private JButton jbtExit;			// 退出按钮
	private JButton jbtAbout;			// 关于按钮
	
	private static final long serialVersionUID = 1L;
	
	public Welcome() {
		Initialize();
		frame.setVisible(true);		// 设置窗口可见
	}
	
	/**
	 * 初始化欢迎界面
	 */
	private void Initialize() {
		frame = new JFrame();
		frame.setTitle("城市路径查询与建议系统");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(460, 360);
		frame.setLocationRelativeTo(null);
		
		jbtLandOn = new JButton("登录");
		jbtRegister = new JButton("注册");
		jbtExit = new JButton("退出");
		jbtAbout = new JButton("关于");
		
		// 设置按钮的工具提示文本
		jbtLandOn.setToolTipText("进入系统登录界面");
		jbtRegister.setToolTipText("进入新用户注册界面");
		jbtExit.setToolTipText("退出本系统");
		jbtAbout.setToolTipText("关于本系统");
		
		jbtLandOn.setLayout(null);
		jbtLandOn.setBounds(20, 20, 40, 10);
		
		// 放置按钮的面板
		JPanel jplButtons = new JPanel();
		jplButtons.setLayout(new GridLayout(1, 4, 20, 1));			// 1 * 3 横排放置三个按钮
		jplButtons.add(jbtLandOn);
		jplButtons.add(jbtRegister);
		jplButtons.add(jbtExit);
		jplButtons.add(jbtAbout);
		
		//ImageIcon bgIcon = new ImageIcon("/images/welcome_bg.jpg");
		ImageIcon bgIcon = new ImageIcon(this.getClass().getResource("/images/welcome_bg.gif"));
		bgIcon.setDescription("城市路径查询与建议系统--SE_TEAN22");
		JLabel jlblBg = new JLabel(bgIcon);
		
		
		frame.setLayout(new BorderLayout());
		frame.add(jlblBg, BorderLayout.CENTER);
		frame.add(jplButtons, BorderLayout.SOUTH);
		
		// 为四个按钮添加监听事件
		jbtLandOn.addActionListener(new LoginListener());
		jbtRegister.addActionListener(new RegisterListener());
		jbtExit.addActionListener(new ExitListener());
		jbtAbout.addActionListener(new AboutListener());
	}
	/**
	 * 关闭窗口
	 */
	public static void closeFrame() {
		frame.setVisible(false);
	}
	/**
	 * 内部类
	 * 实现点击登录按钮时的行为：打开登录界面
	 * @author FanShiqing
	 *
	 */
	private class LoginListener implements ActionListener {
		 public void actionPerformed(ActionEvent e) {
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
	}
	
	/**
	 * 内部类
	 * 实现点击退出按钮时的行为：给出确认退出对话框
	 * @author FanShiqing
	 *
	 */
	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "确认退出本系统吗？", "消息", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				closeFrame();					// 关闭窗口
				System.exit(0);
			}
			else {
				return ;
			}
			
		}
	}
	
	/**
	 * 内部类
	 * 实现点击注册按钮时的行为：打开注册界面
	 * @author FanShiqing
	 *
	 */
	private class RegisterListener implements ActionListener {
		 public void actionPerformed(ActionEvent e) {
			 try {
				Register dialog = new Register();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			 } 
			 catch (Exception ex) {
				ex.printStackTrace();
			}
		 }
	}
	
	private class AboutListener implements ActionListener {
		 public void actionPerformed(ActionEvent e) {
			 String message = "System Developer:  樊士庆，董剑峰，高兴坤，王伟懿\n"
			 				+ "System Version    : Version 1.1\n"
			 				+ "Contact  us      :   Nanjing University, Xianlin Campus,\n"
			 				+ "       163 Xianlin Avenue, Qixia District, Nanjing 210023, China\n"
			 				+ "E-mail Address   : sqfan6@gmail.com";
			 JOptionPane.showMessageDialog(null, message, "关于本系统", JOptionPane.INFORMATION_MESSAGE);
		 }
	}
	
	
	/**
	 * 用于本类的测试
	 * @param args
	 */
	public static void main(String[] args) {
		new Welcome();
	}
}
