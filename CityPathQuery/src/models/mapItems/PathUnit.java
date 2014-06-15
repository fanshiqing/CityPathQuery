package models.mapItems;

import java.util.ArrayList;

import client.Client;
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
		this.averageScore = -1;
		this.advices = new ArrayList<Advice>();
		this.ID = counter;
		counter ++;
	}
	
	/**
	 * �ӷ�����������µ����ֺ�������Ϣ
	 */
	public void update(){
		this.averageScore = Client.getGradeByPathUnitID(ID);
		
		//��������
		this.advices = Client.getCommentsByPathUnitID(ID);
	}
	
	
	/**
	 * 
	 * @return ����·������
	 */
	double getDistance() {
		return startPoint.getDistance(endPoint);
	}

	public String getName() {
		return "    ��·��:" + ID;
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

	public double getAverageScore() {
		update();
		return averageScore;
	}

	public ArrayList<Advice> getAdvices() {
		update();
		return advices;
	}

	public int getID() {
		return ID;
	}
	
	
	
}
