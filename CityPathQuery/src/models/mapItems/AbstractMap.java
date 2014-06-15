package models.mapItems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 用于搜索算法的抽象地图类，由真实的地图提取路段信息得到，可以执行路径搜索算法
 * @author Jeff
 *
 */
public class AbstractMap {
	private Map map;
	private ArrayList<CrossPoint> crossPoints; //crosspoint表
	
	public AbstractMap(Map map) {
		this.map = map;
		ArrayList<PathUnit> pathUnitList = map.getPathUnitList();
		crossPoints = new ArrayList<CrossPoint>();
		for (PathUnit p : pathUnitList) {
			insertPathUnit(p);
		}
	}
	
	/**
	 * 获得一个坐标对应的CrossPoint，
	 * @param coordinate
	 * @return 如果有对应的坐标存在于CrossPoint表中，返回这个crosspoint，否则创建
	 * 新的crosspoint 对象,并将其加入crossPoint表中
	 */
	private CrossPoint getCrossPointByCoordinate(Coordinate coordinate) {
		for (CrossPoint p : crossPoints) {
			if (coordinate.isEqual(p.getCoordinate())) {
				return p;
			}
		}
		CrossPoint newPoint = new CrossPoint(coordinate);
		crossPoints.add(newPoint);
		return newPoint;
	}
	
	/**
	 * 将一个路段加入抽象地图
	 * 将其开始点和结束点加入crosspoint表，并分别为其添加邻居信息
	 * @param pathUnit
	 * @return
	 */
	public boolean insertPathUnit(PathUnit pathUnit) {
		CrossPoint start,end;
		start = getCrossPointByCoordinate(pathUnit.getStartPoint());
		end = getCrossPointByCoordinate(pathUnit.getEndPoint());
		return (start.addNeighbor(end, pathUnit) && end.addNeighbor(start, pathUnit));
	}
	
	//以下为路径查找算法
	
	/**
	 * 这个函数找到离一个坐标点最近的crossPoint
	 * @param coordinate
	 * @return
	 */
	private CrossPoint findNearestCrossPoint(Coordinate coordinate) {
		CrossPoint result = null;
		double minDistance = Double.MAX_VALUE;
		for (CrossPoint p : crossPoints) {
			double distance = coordinate.getDistance(p.getCoordinate());
			if (distance < minDistance) {
				minDistance = distance;
				result = p;
			}
		}
		return result;
	}
	
	//贪心算法+深度优先
	/*private Path findPath(CrossPoint start, CrossPoint end, HashMap<CrossPoint, Boolean> isVisited,
			ArrayList<Path> resultPathList) {
		//Path result = new Path();
		final CrossPoint p = end;
		Comparator<CrossPoint> compareCrosspoint = new Comparator<CrossPoint>() {
			@Override
			public int compare(CrossPoint arg0, CrossPoint arg1) {
				Double distance0 = arg0.getCoordinate().getDistance(p.getCoordinate());
				Double distance1 = arg1.getCoordinate().getDistance(p.getCoordinate());
				return distance0.compareTo(distance1);
			}
		};
		Collections.sort(start.getNeighborList(), compareCrosspoint);
		for (CrossPoint neighbor : start.getNeighborList()) {
			
			if (neighbor != end && isVisited.get(neighbor) == false) {
				isVisited.put(neighbor, true);
				Path path = findPath(neighbor, end, isVisited, resultPathList);
				if (path != null) {
					path.getPathUnitList().add(start.getPathUnitByNeighbor(neighbor));
					return path;
				}
			}
			else if (neighbor == end){
				Path path = new Path();
				isVisited.put(neighbor, true);
				path.getPathUnitList().add(start.getPathUnitByNeighbor(neighbor));
				resultPathList.add(path);
				return path;
			}
		}
		return null;
	}*/
	
	
	
	//dijkstra算法数据结构定义以及实现部分
	
	class Vertex implements Comparable<Vertex>
	{
	    public CrossPoint point;
	    public ArrayList<Edge> adjacencies;
	    public double minDistance = Double.POSITIVE_INFINITY;
	    public Vertex previous;
	    public Vertex(CrossPoint point) { 
	    	this.point = point;
	    	this.adjacencies = new ArrayList<AbstractMap.Edge>();
	    }
	    
	    public int compareTo(Vertex other)
	    {
	        return Double.compare(minDistance, other.minDistance);
	    }
	}

	class Edge
	{
	    public final Vertex target;
	    public final double weight;
	    public Edge(Vertex argTarget, double argWeight)
	    { target = argTarget; weight = argWeight; }
	}

	/**
	 * 用dijkstra算法计算出source到其他点的最短路径
	 * @param source
	 */
	private void computePaths(Vertex source) {
		source.minDistance = 0.;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Edge e : u.adjacencies) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);
					v.minDistance = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}
		
	private Path findPath(CrossPoint start, CrossPoint end){
		Path path = new Path();
		//path.setStartPoint(start.getCoordinate());
		//path.setEndPoint(end.getCoordinate());
		
		HashMap<CrossPoint, Vertex> hashMapCV = new HashMap<CrossPoint, AbstractMap.Vertex>();
		
		ArrayList<Vertex> vertices = new ArrayList<AbstractMap.Vertex>();
		for (CrossPoint p : crossPoints) {
			Vertex v = new Vertex(p);
			vertices.add(v);
			hashMapCV.put(p, v);
		}
		for (Vertex v : vertices) {
			CrossPoint point = v.point;
			for (CrossPoint neighbor : point.getNeighborList()) {
				PathUnit pathUnit = point.getPathUnitByNeighbor(neighbor);
				Vertex neighborV = hashMapCV.get(neighbor);
				Edge edge = new Edge(neighborV, pathUnit.getDistance());
				v.adjacencies.add(edge);
			}
		}
		
		Vertex source = hashMapCV.get(start);
		computePaths(source);
		Vertex target = hashMapCV.get(end);
		for (Vertex v = target;v.previous != null; v = v.previous) {
			CrossPoint pathUnitStart = v.point;
			CrossPoint pathUnitEnd = v.previous.point;
			PathUnit pathUnit = pathUnitStart.getPathUnitByNeighbor(pathUnitEnd);
			path.getPathUnitList().add(pathUnit);
		}
		return path;
	}
	
	
	public Path findPath(Coordinate startCoor, Coordinate endCoor) {
		CrossPoint start,end;
		start = findNearestCrossPoint(startCoor);
		end = findNearestCrossPoint(endCoor);

		Path result = findPath(start, end);
		
		//计算出发点/目的点到最近道路的切点，作为路径开始点/结束点
		
		
		result.setStartPoint(startCoor);
		result.setEndPoint(endCoor);
		return result;
	}
	
	/**
	 * 通过两个地点查找路径
	 * @param loc1
	 * @param loc2
	 * @return
	 */
	public Path findPath(Location loc1, Location loc2) {
		return findPath(loc1.getRoadEntry(), loc2.getRoadEntry());
	}
	
	public Path findPath(String locName1, String locName2) {
		Location loc1 = map.getLocationByName(locName1);
		Location loc2 = map.getLocationByName(locName2);
		if (loc1 == null || loc2 == null) {
			return null;
		}
		Path result = findPath(loc1, loc2);
		if (result != null) {
			result.setStartLocName(locName1);
			result.setEndLocName(locName2);
		}
		return result;
	}
	
}

class CrossPoint {
	
	
	/**
	 * 记录了邻居的信息，包括到达邻居的路段和邻居节点
	 * @author Jeff
	 *
	 */
	/*class NeighborInfo {
		CrossPoint neighbor;
		PathUnit pathUnit;
		
		public NeighborInfo(CrossPoint neighbor, PathUnit pathUnit) {
			this.neighbor = neighbor;
			this.pathUnit = pathUnit;
		}
	}*/
	
	//该交叉点的坐标
	private Coordinate coordinate;
	//邻居表，记录了交叉点的邻居
	private ArrayList<CrossPoint> neighborList;
	//记录了某个邻居对应的路段，为方便查找时获取路段信息
	private HashMap<CrossPoint, PathUnit> neighborPathUnitHash;
	
	public CrossPoint(Coordinate coordinate) {
		this.coordinate = coordinate;
		neighborPathUnitHash = new HashMap<CrossPoint, PathUnit>();
		neighborList = new ArrayList<CrossPoint>();
	}
	
	
	
	/**
	 * 这个函数将邻居节点和路段加入邻居路段哈希表，返回加入结果
	 * @param neighbor
	 * @param pathUnit
	 * @return
	 */
	public boolean addNeighbor(CrossPoint neighbor , PathUnit pathUnit) {
		//检查neighbor是否重复,不重复则加入邻居表
		if (neighborList.add(neighbor) == false) {
			return false;
		}
		//将路段信息加入邻居路段表
		neighborPathUnitHash.put(neighbor, pathUnit);
		return true;
	}
	
	public PathUnit getPathUnitByNeighbor(CrossPoint neighbor) {
		return neighborPathUnitHash.get(neighbor);
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public ArrayList<CrossPoint> getNeighborList() {
		return neighborList;
	}
	
	
	
}