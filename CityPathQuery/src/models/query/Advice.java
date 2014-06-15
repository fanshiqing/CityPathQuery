package models.query;

import java.util.Date;

/**
 * ����������
 * @author ������
 *
 */
public class Advice {
	private int adviceID;
	private String userName;
	private int pathUnitID;
	private String adviceTime;
	private int likedTimes;
	private AdviceContent content;
	
	/**
	 * �ӷ������˽��յ���Advice���ݹ��캯��
	 * @param adviceID
	 * @param userName
	 * @param pathUnitID
	 * @param adviceTime
	 * @param likedTimes
	 * @param contentText
	 */
	public Advice(int adviceID, String userName, int pathUnitID, String adviceTime, int likedTimes, String contentText) {
		this.adviceID = adviceID;
		this.userName = userName;
		this.pathUnitID = pathUnitID;
		this.adviceTime = adviceTime;
		this.likedTimes = likedTimes;
		this.content = new AdviceContent(contentText);
		
	}
	
	
	public Advice(String userName, int pathUnitID, String text) {
		this.userName = userName;
		this.pathUnitID = pathUnitID;
		this.content = new AdviceContent(text);
	}
	

	public String getTextContent() {
		return content.getText();
	}
	
	public AdviceContent getContent() {
		return content;
	}
	
	public String getUserName() {
		return userName;
	}
	
	
	
}
