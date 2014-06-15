package models.mapItems;

import java.util.ArrayList;

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
	
	public Path() {
		pathUnitList = new ArrayList<PathUnit>();
	}
	
	double getScore () {
		double score = -1;
		//����pathUnitList����·���ķ���
		return score;
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
	
	
}
