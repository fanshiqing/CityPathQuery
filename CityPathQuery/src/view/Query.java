package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import client.Client;
import models.mapItems.AbstractMap;
import models.mapItems.Map;
import models.mapItems.Path;
import util.TranslateMapFile;


public class Query extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	private JButton jbtQuery;		// ��ѯ��ť
	private JButton jbtReset;		// ���ð�ť
	private JButton jbtExit;		// �˳���ť
	private JButton jbtAbout;		// ���ڰ�ť
	
	private MapPanel mapPanel;	//��ͼpanel
	
	private String userName;				// ��¼���û���

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
		super();				// ���ȵ��ø���Ĺ��췽��
		
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
		JLabel lblNewLabel = new JLabel("״̬���ѵ�¼   �û���:" + userName);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		//JButton button_2 = new JButton("\u767B\u9646");
		jbtExit= new JButton("�˳�");
		jbtExit.setHorizontalAlignment(SwingConstants.CENTER);
		jbtExit.setPreferredSize(new Dimension(60,21));
		jbtExit.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_4.add(jbtExit);
		
		//JButton button_3 = new JButton("\u6CE8\u518C");
		jbtAbout = new JButton("����");
		jbtAbout.setHorizontalAlignment(SwingConstants.CENTER);
		jbtAbout.setPreferredSize(new Dimension(60,21));
		jbtAbout.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_4.add(jbtAbout);
		
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
		
		JLabel label = new JLabel("�����ص㣺");
		label.setFont(new Font("����", Font.PLAIN, 14));
		label.setBounds(25, 33, 70, 15);
		panel_13.add(label);
		
		JLabel label_1 = new JLabel("Ŀ �� �أ�");
		label_1.setFont(new Font("����", Font.PLAIN, 14));
		label_1.setBounds(25, 69, 70, 15);
		panel_13.add(label_1);
		
		JLabel label_2 = new JLabel(";    ����");
		label_2.setFont(new Font("����", Font.PLAIN, 14));
		label_2.setBounds(25, 106, 70, 15);
		panel_13.add(label_2);
		
		JLabel label_3 = new JLabel("����룺");
		label_3.setFont(new Font("����", Font.PLAIN, 14));
		label_3.setBounds(25, 144, 70, 15);
		panel_13.add(label_3);
		
		JLabel label_4 = new JLabel("����Ǽ���");
		label_4.setFont(new Font("����", Font.PLAIN, 14));
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
		jbtQuery = new JButton("��ѯ");
		jbtQuery.setFont(new Font("����", Font.BOLD, 15));
		jbtQuery.setBounds(42, 217, 70, 23);
		panel_13.add(jbtQuery);
		
		//JButton button_1 = new JButton("\u91CD\u7F6E");
		jbtReset = new JButton("����");
		jbtReset.setFont(new Font("����", Font.BOLD, 15));
		jbtReset.setBounds(121, 217, 70, 23);
		panel_13.add(jbtReset);
		
		JPanel panel_14 = new JPanel();
		contentPane.add(panel_14, BorderLayout.EAST);
		
		
		Map map = Client.getMap();
		mapPanel = new MapPanel(map);
		contentPane.add(mapPanel, BorderLayout.CENTER);
		
		
		this.setLocationRelativeTo(null); 					// ���ý�������λ��
		
		/*
		 * Ϊ������ť��Ӽ����¼�
		 */
		jbtExit.addActionListener(new ExitListener());
		jbtAbout.addActionListener(new AboutListener());
		jbtQuery.addActionListener(new QueryListener());
		jbtReset.addActionListener(new ResetListener());
	}
	
	/**
	 * �����û���
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * �ڲ���
	 * ʵ�ֵ���˳���ťʱ����Ϊ������ȷ���˳��Ի���
	 * @author FanShiqing
	 *
	 */
	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "ȷ���˳���ϵͳ��", "��Ϣ", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				System.exit(0);					// �˳�ϵͳ
			}
		}
	}
	
	/**
	 * �ڲ���
	 * ʵ�ֵ�����ڰ�ťʱ����Ϊ���������ڱ�ϵͳ��һЩ��Ϣ
	 * @author FanShiqing
	 *
	 */
	private class AboutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String message = "System Developer:  ��ʿ�죬�����壬����������ΰܲ\n"
	 				+ "System Version    : Version 1.1\n"
	 				+ "Contact  us      :   Nanjing University, Xianlin Campus,\n"
	 				+ "       163 Xianlin Avenue, Qixia District, Nanjing 210023, China\n"
	 				+ "E-mail Address   : sqfan6@gmail.com";
			JOptionPane.showMessageDialog(null, message, "���ڱ�ϵͳ", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * �ڲ���
	 * ʵ�ֵ����ѯ��ťʱ����Ϊ����server����·����ѯ����
	 * @author FanShiqing
	 */
	private class QueryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Query
			String locNameStart = textField_1.getText();
			String locNameEnd = textField_2.getText();
			String locNameMid = textField_3.getText();
			//AbstractMap absMap = Client.getAbstractMap();
			
			//Client.clientInit("user");
			
			
			models.query.Query query = new models.query.Query(locNameStart, locNameMid, locNameEnd, Client.getUser().getUserName());
			if (!query.execute()) {
				JOptionPane.showMessageDialog(null, "����ص㲻�Ϸ���");
			}
			else {
				//�������������ʷ����
				
				mapPanel.paintPath(query.getResultPath());
			}
			
		}
	}
	/**
	 * �ڲ���
	 * ʵ�ֵ�����ð�ťʱ����Ϊ�����ø����ı���
	 * @author FanShiqing
	 */
	private class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}












