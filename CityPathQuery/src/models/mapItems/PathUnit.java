package models.mapItems;

import java.util.ArrayList;

import client.Client;
import models.query.Advice;

/**
 * 描述：是一段直线的路，逻辑上是地图中路的最小单位
 * @author 董剑峰
 *
 */
public class PathUnit {
	
	private int ID;
	private Coordinate startPoint;
	private Coordinate endPoint;
	private int scoredTimes; //受到的打分次数
	private double averageScore;//平均分数
	private ArrayList<Advice> advices; //得到的所有评论
	
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
	 * 从服务器获得最新的评分和评论信息
	 */
	public void update(){
		this.averageScore = Client.getGradeByPathUnitID(ID);
		
		//更新评论
		this.advices = Client.getCommentsByPathUnitID(ID);
	}
	
	
	/**
	 * 
	 * @return 返回路径长度
	 */
	double getDistance() {
		return startPoint.getDistance(endPoint);
	}

	public String getName() {
		return "    子路段:" + ID;
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
