package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;


public class Register extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
		setBounds(100, 100, 400, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u8BF7\u586B\u5199\u4EE5\u4E0B\u4FE1\u606F");
			label.setBounds(109, 25, 198, 42);
			label.setFont(new Font("宋体", Font.BOLD, 20));
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u7528 \u6237 \u540D\uFF1A");
			label.setBounds(73, 81, 79, 24);
			label.setFont(new Font("宋体", Font.PLAIN, 15));
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u5BC6    \u7801\uFF1A");
			label.setBounds(73, 115, 79, 24);
			label.setFont(new Font("宋体", Font.PLAIN, 15));
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u91CD\u590D\u5BC6\u7801\uFF1A");
			label.setBounds(73, 149, 85, 24);
			label.setFont(new Font("宋体", Font.PLAIN, 15));
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u90AE    \u7BB1\uFF1A");
			label.setBounds(74, 185, 90, 24);
			label.setFont(new Font("宋体", Font.PLAIN, 15));
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u5E38\u4F4F\u57CE\u5E02\uFF1A");
			label.setBounds(73, 222, 81, 24);
			label.setFont(new Font("宋体", Font.PLAIN, 15));
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			textField.setBounds(163, 80, 137, 23);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(164, 114, 137, 23);
			textField_1.setColumns(10);
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(164, 149, 137, 23);
			textField_2.setColumns(10);
			contentPanel.add(textField_2);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(163, 183, 137, 23);
			textField_3.setColumns(10);
			contentPanel.add(textField_3);
		}
		{
			textField_4 = new JTextField();
			textField_4.setBounds(164, 219, 137, 23);
			textField_4.setColumns(10);
			contentPanel.add(textField_4);
		}
		{
			JButton button = new JButton("\u6CE8\u518C");
			button.setBounds(109, 263, 70, 23);
			button.setFont(new Font("宋体", Font.BOLD, 15));
			contentPanel.add(button);
		}
		{
			JButton button = new JButton("\u53D6\u6D88");
			button.setBounds(201, 263, 70, 23);
			button.setFont(new Font("宋体", Font.BOLD, 15));
			contentPanel.add(button);
		}
	}

}
