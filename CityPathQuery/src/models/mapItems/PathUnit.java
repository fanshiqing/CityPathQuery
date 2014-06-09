package models.mapItems;

import java.util.ArrayList;

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
	
	
	/**
	 * 
	 * @return 返回路径长度
	 */
	double getDistance() {
		double distance = -1;
		
		//通过起点与终点计算距离
		
		return distance;
	}
	
	
}
