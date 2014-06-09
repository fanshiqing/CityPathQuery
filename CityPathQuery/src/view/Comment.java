package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JList;

public class Comment extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comment frame = new Comment();
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
	public Comment() {
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
		
		JLabel lblNewLabel = new JLabel("\u72B6\u6001\uFF1A\u5DF2\u767B\u5F55   \u7528\u6237\u540D\uFF1Aaaa  ");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel label_5 = new JLabel("  \u53D1\u8868\u8BC4\u4EF7");
		label_5.setFont(new Font("宋体", Font.BOLD, 16));
		panel_4.add(label_5);
		
		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(0,190));
		contentPane.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.WEST);
		
		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(0,30));
		panel_5.add(panel_7, BorderLayout.NORTH);
		panel_7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel(" \u8BC4\u8BBA\u5185\u5BB9");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 15));
		panel_7.add(lblNewLabel_1);
		
		JPanel panel_8 = new JPanel();
		panel_8.setPreferredSize(new Dimension(140,0));
		panel_5.add(panel_8, BorderLayout.EAST);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0\u56FE\u7247");
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 14));
		panel_8.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u6DFB\u52A0\u89C6\u9891");
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 14));
		panel_8.add(btnNewButton_1);
		
		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(0,50));
		panel_5.add(panel_9, BorderLayout.SOUTH);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 10));
		
		JButton button = new JButton("\u786E\u8BA4");
		button.setFont(new Font("宋体", Font.BOLD, 14));
		panel_9.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.setFont(new Font("宋体", Font.BOLD, 14));
		panel_9.add(button_1);
		
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
		
		JList list = new JList();
		panel_10.add(list, BorderLayout.CENTER);
		
		JPanel panel_14 = new JPanel();
		contentPane.add(panel_14, BorderLayout.EAST);
		
		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
	}
}
