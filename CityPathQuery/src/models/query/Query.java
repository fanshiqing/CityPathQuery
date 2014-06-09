package models.query;

import java.util.Date;

import models.mapItems.Coordinate;
import models.mapItems.Path;

/**
 * 描述：
 * @author 董剑峰
 *
 */
public class Query {
	private int ID;
	private int userID;
	private Coordinate startPoint;
	private Coordinate endPoint;
	private Date queryTime; //查询时间
	
	private double scoreLimit;	//查询路径分数限制，取值为0到5
	private double distanceLimit;//查询路径最大长度限制，取值为正
	
	private Path resultPath; //最终查询结果
	
	
}
