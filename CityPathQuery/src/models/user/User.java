package models.user;


import models.mapItems.Path;

import models.query.Query;


/**
 * 描述：这个类是抽象的用户类，具体在其两个子类中实现。
 * @author 董剑峰
 *
 */
public abstract class User {
	
	
	
	
	/**
	 * 该方法向服务器提交一个查询请求
	 * @param query
	 * @return
	 */
	public Path queryPath(Query query) {
		Path resultPath = new Path();
		
		return resultPath;
	}
}
