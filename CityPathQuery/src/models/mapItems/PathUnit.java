package models.mapItems;

import java.util.ArrayList;

import models.query.Advice;

/**
 * ��������һ��ֱ�ߵ�·���߼����ǵ�ͼ��·����С��λ
 * @author ������
 *
 */
public class PathUnit {
	
	private int ID;
	private Coordinate startPoint;
	private Coordinate endPoint;
	private int scoredTimes; //�ܵ��Ĵ�ִ���
	private double averageScore;//ƽ������
	private ArrayList<Advice> advices; //�õ�����������
	
	private static int counter = 0;
	
	public PathUnit (Coordinate startPoint, Coordinate endPoint){
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.scoredTimes = 0;
		this.averageScore = 0;
		this.advices = new ArrayList<Advice>();
		this.ID = counter;
		counter ++;
	}
	
	
	/**
	 * 
	 * @return ����·������
	 */
	double getDistance() {
		return startPoint.getDistance(endPoint);
	}


	public Coordinate getStartPoint() {
		return startPoint;
	}


	public void setStartPoint(Coordinate startPoint) {
		this.startPoint = startPoint;
	}


	public Coordinate getEndPoint() {
		return endPoint;
	}


	public void setEndPoint(Coordinate endPoint) {
		this.endPoint = endPoint;
	}
	
	
}
