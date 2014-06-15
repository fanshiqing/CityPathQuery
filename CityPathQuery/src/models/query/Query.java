package models.query;

import java.util.ArrayList;

import client.Client;
import models.mapItems.Path;

/**
 * 描述：
 * @author 董剑峰
 *
 */
public class Query {
	private int ID;
	private String userName;
	
	private String startLocationName;
	private String midLocationName;
	private String endLocationName;
	private String queryTime; //查询时间
	
	private double scoreLimit;	//查询路径分数限制，取值为0到5
	
	private ArrayList<Path> resultPath;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStartLocationName() {
		return startLocationName;
	}

	public void setStartLocationName(String startLocationName) {
		this.startLocationName = startLocationName;
	}

	public String getMidLocationName() {
		return midLocationName;
	}

	public void setMidLocationName(String midLocationName) {
		this.midLocationName = midLocationName;
	}

	public String getEndLocationName() {
		return endLocationName;
	}

	public void setEndLocationName(String endLocationName) {
		this.endLocationName = endLocationName;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public double getScoreLimit() {
		return scoreLimit;
	}

	public void setScoreLimit(double scoreLimit) {
		this.scoreLimit = scoreLimit;
	}

	
	/**
	 * Query类构造函数
	 * @param userName
	 * @param startLocationName
	 * @param endLocationName
	 * @param midLocationName
	 * @param queryTime
	 */
	public Query(String userName, String startLocationName, String endLocationName, String midLocationName, String queryTime) {
		this.userName = userName;
		this.startLocationName = startLocationName;
		this.endLocationName = endLocationName;
		this.midLocationName = midLocationName;
		this.queryTime = queryTime;
		resultPath = new ArrayList<Path>();
	}
	
	public Query(String startLoc, String midLoc, String endLoc, String userName) {
		this.startLocationName = startLoc;
		this.endLocationName = endLoc;
		this.midLocationName = midLoc;
		this.userName = userName;
		resultPath = new ArrayList<Path>();
	}
	
	//执行路径查询，返回查询结果成功/失败，结果储存在
	public boolean execute() {
		Path resultPath1; //最终查询结果
		Path resultPath2; //含midloc的查询结果
		
		System.out.println("start :" + startLocationName + "   end : " + endLocationName + "  mid:" + midLocationName);
		
		if (midLocationName.equals("")) {
			resultPath1 = Client.getAbstractMap().findPath(startLocationName, endLocationName);
			resultPath.add(resultPath1);
		}
		else {
			
			resultPath1 = Client.getAbstractMap().findPath(startLocationName, midLocationName);
			resultPath2 = Client.getAbstractMap().findPath(midLocationName, endLocationName);
			resultPath.add(resultPath1);
			resultPath.add(resultPath2);
		}
		for (Path p : resultPath) {
			if (p == null) {
				return false;
			}
		}		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		if (queryTime == null) {
			result.append("查询时间 ：当前\n");
		}
		else {
			result.append("查询时间：" + queryTime + "\n");
		}
		for (Path p : getResultPath()) {
			int i = resultPath.indexOf(p);
			result.append("结果路径  " + i + ":\n");
			result.append(p.toString());
		}
		return result.toString();
	}
	
	public ArrayList<Path> getResultPath() {
		if (resultPath.isEmpty()) {
			execute();
		}
		return resultPath;
	}
}
