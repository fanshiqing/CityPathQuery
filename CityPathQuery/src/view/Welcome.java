package view;

/**
 * ϵͳ���еĳ�ʼ����
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
	
	private JButton jbtLandOn;			// ��¼��ť
	private JButton jbtRegister;		// ע�ᰴť
	private JButton jbtExit;			// �˳���ť
	private JButton jbtAbout;			// ���ڰ�ť
	
	private static final long serialVersionUID = 1L;
	
	public Welcome() {
		Initialize();
		frame.setVisible(true);		// ���ô��ڿɼ�
	}
	
	/**
	 * ��ʼ����ӭ����
	 */
	private void Initialize() {
		frame = new JFrame();
		frame.setTitle("����·����ѯ�뽨��ϵͳ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(460, 360);
		frame.setLocationRelativeTo(null);
		
		jbtLandOn = new JButton("��¼");
		jbtRegister = new JButton("ע��");
		jbtExit = new JButton("�˳�");
		jbtAbout = new JButton("����");
		
		// ���ð�ť�Ĺ�����ʾ�ı�
		jbtLandOn.setToolTipText("����ϵͳ��¼����");
		jbtRegister.setToolTipText("�������û�ע�����");
		jbtExit.setToolTipText("�˳���ϵͳ");
		jbtAbout.setToolTipText("���ڱ�ϵͳ");
		
		jbtLandOn.setLayout(null);
		jbtLandOn.setBounds(20, 20, 40, 10);
		
		// ���ð�ť�����
		JPanel jplButtons = new JPanel();
		jplButtons.setLayout(new GridLayout(1, 4, 20, 1));			// 1 * 3 ���ŷ���������ť
		jplButtons.add(jbtLandOn);
		jplButtons.add(jbtRegister);
		jplButtons.add(jbtExit);
		jplButtons.add(jbtAbout);
		
		//ImageIcon bgIcon = new ImageIcon("/images/welcome_bg.jpg");
		ImageIcon bgIcon = new ImageIcon(this.getClass().getResource("/images/welcome_bg.gif"));
		bgIcon.setDescription("����·����ѯ�뽨��ϵͳ--SE_TEAN22");
		JLabel jlblBg = new JLabel(bgIcon);
		
		
		frame.setLayout(new BorderLayout());
		frame.add(jlblBg, BorderLayout.CENTER);
		frame.add(jplButtons, BorderLayout.SOUTH);
		
		// Ϊ�ĸ���ť��Ӽ����¼�
		jbtLandOn.addActionListener(new LoginListener());
		jbtRegister.addActionListener(new RegisterListener());
		jbtExit.addActionListener(new ExitListener());
		jbtAbout.addActionListener(new AboutListener());
	}
	/**
	 * �رմ���
	 */
	public static void closeFrame() {
		frame.setVisible(false);
	}
	/**
	 * �ڲ���
	 * ʵ�ֵ����¼��ťʱ����Ϊ���򿪵�¼����
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
	 * �ڲ���
	 * ʵ�ֵ���˳���ťʱ����Ϊ������ȷ���˳��Ի���
	 * @author FanShiqing
	 *
	 */
	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "ȷ���˳���ϵͳ��", "��Ϣ", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				closeFrame();					// �رմ���
				System.exit(0);
			}
			else {
				return ;
			}
			
		}
	}
	
	/**
	 * �ڲ���
	 * ʵ�ֵ��ע�ᰴťʱ����Ϊ����ע�����
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
			 String message = "System Developer:  ��ʿ�죬�����壬����������ΰܲ\n"
			 				+ "System Version    : Version 1.1\n"
			 				+ "Contact  us      :   Nanjing University, Xianlin Campus,\n"
			 				+ "       163 Xianlin Avenue, Qixia District, Nanjing 210023, China\n"
			 				+ "E-mail Address   : sqfan6@gmail.com";
			 JOptionPane.showMessageDialog(null, message, "���ڱ�ϵͳ", JOptionPane.INFORMATION_MESSAGE);
		 }
	}
	
	
	/**
	 * ���ڱ���Ĳ���
	 * @param args
	 */
	public static void main(String[] args) {
		new Welcome();
	}
}
