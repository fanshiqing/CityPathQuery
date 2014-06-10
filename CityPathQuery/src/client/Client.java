package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 客户端程序
 * @author FanShiqing
 *
 */
public class Client {
	
	private Socket socket;								// 与服务器通信的套接字
	private ObjectOutputStream toServer;				// 发送到服务器的对象
	private ObjectInputStream fromServer;				// 接收来自服务器的对象
	/**
	 * 
	 */
	public Client() {
		try {
			socket = new Socket("localhost", 8000);		// 创建连接到server的套接字
			toServer = new ObjectOutputStream(socket.getOutputStream());
			//toServer.flush();
			fromServer = new ObjectInputStream(socket.getInputStream());
		} 
		catch(IOException ex) {
			System.out.println("Failed to connect to server\n");
			ex.printStackTrace();
		}
		// Connect to server successfully
		System.out.println("Connect to server successfully!\n");
	}
	/**
	 * 客户端建立到服务器的连接
	 * @return true:连接成功
	 * 			false:连接失败
	 */
	
	
	/**
	 * for test only
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client();
		
		return ;
	}
	
}
