package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Query extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	private String userName;				// 登录的用户名

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Query frame = new Query(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Query(String userName) {
		super();				// 首先调用父类的构造方法
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 660, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0,50));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);
		
		//JLabel lblNewLabel = new JLabel("\u72B6\u6001\uFF1A\u672A\u767B\u5F55   \u7528\u6237\u540D\uFF1ANULL  ");
		JLabel lblNewLabel = new JLabel("状态：已登录   用户名:" + userName);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		//JButton button_2 = new JButton("\u767B\u9646");
		JButton button_2 = new JButton("退出");
		button_2.setHorizontalAlignment(SwingConstants.CENTER);
		button_2.setPreferredSize(new Dimension(60,21));
		button_2.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_4.add(button_2);
		
		//JButton button_3 = new JButton("\u6CE8\u518C");
		JButton button_3 = new JButton("关于");
		button_3.setHorizontalAlignment(SwingConstants.CENTER);
		button_3.setPreferredSize(new Dimension(60,21));
		button_3.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_4.add(button_3);
		
		JPanel panel_15 = new JPanel();
		panel_15.setPreferredSize(new Dimension(50,20));
		panel_4.add(panel_15);
		
		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(0,160));
		contentPane.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.WEST);
		
		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(0,30));
		panel_5.add(panel_7, BorderLayout.NORTH);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8, BorderLayout.EAST);
		
		JPanel panel_9 = new JPanel();
		panel_5.add(panel_9, BorderLayout.SOUTH);
		
		textField = new JTextField();
		panel_5.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_10.setPreferredSize(new Dimension(280,0));
		contentPane.add(panel_10, BorderLayout.WEST);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11, BorderLayout.WEST);
		
		JPanel panel_12 = new JPanel();
		panel_12.setPreferredSize(new Dimension(30,0));
		panel_10.add(panel_12, BorderLayout.EAST);
		
		JPanel panel_13 = new JPanel();
		panel_10.add(panel_13, BorderLayout.CENTER);
		panel_13.setLayout(null);
		
		JLabel label = new JLabel("出发地点：");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(25, 33, 70, 15);
		panel_13.add(label);
		
		JLabel label_1 = new JLabel("目 的 地：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(25, 69, 70, 15);
		panel_13.add(label_1);
		
		JLabel label_2 = new JLabel("途    径：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(25, 106, 70, 15);
		panel_13.add(label_2);
		
		JLabel label_3 = new JLabel("最长距离：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(25, 144, 70, 15);
		panel_13.add(label_3);
		
		JLabel label_4 = new JLabel("最低星级：");
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		label_4.setBounds(25, 181, 70, 15);
		panel_13.add(label_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(97, 29, 119, 23);
		panel_13.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(97, 65, 119, 23);
		panel_13.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(97, 101, 119, 23);
		panel_13.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(97, 138, 119, 23);
		panel_13.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(97, 174, 119, 23);
		panel_13.add(textField_5);
		
		//JButton button = new JButton("\u67E5\u8BE2");
		JButton button = new JButton("查询");
		button.setFont(new Font("宋体", Font.BOLD, 15));
		button.setBounds(42, 217, 70, 23);
		panel_13.add(button);
		
		//JButton button_1 = new JButton("\u91CD\u7F6E");
		JButton button_1 = new JButton("重置");
		button_1.setFont(new Font("宋体", Font.BOLD, 15));
		button_1.setBounds(121, 217, 70, 23);
		panel_13.add(button_1);
		
		JPanel panel_14 = new JPanel();
		contentPane.add(panel_14, BorderLayout.EAST);
		
		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
		
		this.setLocationRelativeTo(null); 					// 设置界面的相对位置
	}
	
	/**
	 * 设置用户名
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}












