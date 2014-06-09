package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;


public class Login {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(159, 83, 135, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(159, 121, 135, 28);
		frame.getContentPane().add(textField_1);
		
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
		
		JButton button = new JButton("\u767B\u9646");
		button.setFont(new Font("宋体", Font.BOLD, 15));
		button.setBounds(108, 172, 72, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.setFont(new Font("宋体", Font.BOLD, 15));
		button_1.setBounds(213, 172, 72, 23);
		frame.getContentPane().add(button_1);
	}
}
