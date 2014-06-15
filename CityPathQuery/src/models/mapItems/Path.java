package models.mapItems;

import java.util.ArrayList;

import models.query.Advice;

/**
 * ������·���࣬һ��·�����ɶ��·�κ�һ����ʼ��ͽ�������ɵļ��ϣ�ע��·���Ŀ�ʼ�������㲻һ��<br>
 * ��ĳ��·�εĿ�ʼ������㣬��Ϊһ��·�ο���ֻ��һ������·����
 * @author ������
 *
 */
public class Path {
	private ArrayList<PathUnit> pathUnitList;
	private Coordinate startPoint;
	private Coordinate endPoint;
	private String startLocName;
	private String endLocName;
	
	public Path() {
		pathUnitList = new ArrayList<PathUnit>();
	}
	
	double getScore () {
		double score = -1;
		//����pathUnitList����·���ķ���
		return score;
	}
	
	public String getName() {
		return startLocName + "-->" + endLocName;
	}
	
	double getLength() {
		return startPoint.getDistance(endPoint);
	}

	public ArrayList<PathUnit> getPathUnitList() {
		return pathUnitList;
	}

	public void setStartPoint(Coordinate startPoint) {
		this.startPoint = startPoint;
	}

	public void setEndPoint(Coordinate endPoint) {
		this.endPoint = endPoint;
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		//����յ�
		result.append("��㣺" + startLocName + "      �յ㣺" + endLocName + "\n");
		
		//���
		double score = 0;
		int numOfUsedScore = 0; //��0�Ĵ�ָ���
		for (PathUnit p : pathUnitList) {
			double s = p.getAverageScore();
			if (s > 0) {
				numOfUsedScore ++;
				score += s;
			}
			
		}
		score = score / numOfUsedScore;
		result.append("�ۺ����� ��" + score + "\n");
		
		//����
		result.append("�����û����ۣ�\n");
		for (PathUnit p : pathUnitList) {
			ArrayList<Advice> adviceList = p.getAdvices();
			for (Advice ad : adviceList) {
				result.append("    " + ad.getUserName() + " : " + ad.getTextContent() + "\n");
			}
		}
		
		return result.toString();
	}

	public void setStartLocName(String startLocName) {
		this.startLocName = startLocName;
	}

	public void setEndLocName(String endLocName) {
		this.endLocName = endLocName;
	}
	
	
	
}
