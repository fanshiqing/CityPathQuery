package models.user;


import models.mapItems.Path;

import models.query.Query;


/**
 * ������������ǳ�����û��࣬������������������ʵ�֡�
 * @author ������
 *
 */
public abstract class User {
	
	/**
	 * �÷�����������ύһ����ѯ����
	 * @param query
	 * @return
	 */
	public Path queryPath(Query query) {
		Path resultPath = new Path();
		
		return resultPath;
	}
}
