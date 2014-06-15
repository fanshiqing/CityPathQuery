package models.mapItems;

import java.util.ArrayList;

import models.query.Advice;

/**
 * 描述：路径类，一个路径是由多个路段和一个开始点和结束点组成的集合，注意路径的开始点或结束点不一定<br>
 * 是某个路段的开始或结束点，因为一个路段可以只有一部分在路径中
 * @author 董剑峰
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
		//遍历pathUnitList计算路径的分数
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
		//起点终点
		result.append("起点：" + startLocName + "      终点：" + endLocName + "\n");
		
		//打分
		double score = 0;
		int numOfUsedScore = 0; //非0的打分个数
		for (PathUnit p : pathUnitList) {
			double s = p.getAverageScore();
			if (s > 0) {
				numOfUsedScore ++;
				score += s;
			}
			
		}
		score = score / numOfUsedScore;
		result.append("综合评分 ：" + score + "\n");
		
		//评论
		result.append("其他用户评论：\n");
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
