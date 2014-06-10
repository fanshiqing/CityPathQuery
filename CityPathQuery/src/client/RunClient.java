package client;

import java.awt.EventQueue;

import view.Login;

/**
 * 客户端程序执行入口
 * @author FanShiqing
 *
 */
public class RunClient {
	
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
}
