package models.query;

import java.util.ArrayList;
import java.util.Date;

import client.Client;
import models.mapItems.Coordinate;
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
	
	public Query(String startLoc, String midLoc, String endLoc, String userName, String queryTime) {
		this.startLocationName = startLoc;
		this.endLocationName = endLoc;
		this.midLocationName = midLoc;
		this.userName = userName;
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
	
	public ArrayList<Path> getResultPath() {
		if (resultPath.isEmpty()) {
			execute();
		}
		return resultPath;
	}
}
