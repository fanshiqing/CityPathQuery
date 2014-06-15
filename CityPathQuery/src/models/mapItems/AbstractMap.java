package models.mapItems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * ���������㷨�ĳ����ͼ�࣬����ʵ�ĵ�ͼ��ȡ·����Ϣ�õ�������ִ��·�������㷨
 * @author Jeff
 *
 */
public class AbstractMap {
	private Map map;
	private ArrayList<CrossPoint> crossPoints; //crosspoint��
	
	public AbstractMap(Map map) {
		this.map = map;
		ArrayList<PathUnit> pathUnitList = map.getPathUnitList();
		crossPoints = new ArrayList<CrossPoint>();
		for (PathUnit p : pathUnitList) {
			insertPathUnit(p);
		}
	}
	
	/**
	 * ���һ�������Ӧ��CrossPoint��
	 * @param coordinate
	 * @return ����ж�Ӧ�����������CrossPoint���У��������crosspoint�����򴴽�
	 * �µ�crosspoint ����,���������crossPoint����
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
	 * ��һ��·�μ�������ͼ
	 * ���俪ʼ��ͽ��������crosspoint�����ֱ�Ϊ������ھ���Ϣ
	 * @param pathUnit
	 * @return
	 */
	public boolean insertPathUnit(PathUnit pathUnit) {
		CrossPoint start,end;
		start = getCrossPointByCoordinate(pathUnit.getStartPoint());
		end = getCrossPointByCoordinate(pathUnit.getEndPoint());
		return (start.addNeighbor(end, pathUnit) && end.addNeighbor(start, pathUnit));
	}
	
	//����Ϊ·�������㷨
	
	/**
	 * ��������ҵ���һ������������crossPoint
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
	
	//̰���㷨+�������
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
	
	
	
	//dijkstra�㷨���ݽṹ�����Լ�ʵ�ֲ���
	
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
	 * ��dijkstra�㷨�����source������������·��
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
		
		//���������/Ŀ�ĵ㵽�����·���е㣬��Ϊ·����ʼ��/������
		
		
		result.setStartPoint(startCoor);
		result.setEndPoint(endCoor);
		return result;
	}
	
	/**
	 * ͨ�������ص����·��
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
	 * ��¼���ھӵ���Ϣ�����������ھӵ�·�κ��ھӽڵ�
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
	
	//�ý���������
	private Coordinate coordinate;
	//�ھӱ���¼�˽������ھ�
	private ArrayList<CrossPoint> neighborList;
	//��¼��ĳ���ھӶ�Ӧ��·�Σ�Ϊ�������ʱ��ȡ·����Ϣ
	private HashMap<CrossPoint, PathUnit> neighborPathUnitHash;
	
	public CrossPoint(Coordinate coordinate) {
		this.coordinate = coordinate;
		neighborPathUnitHash = new HashMap<CrossPoint, PathUnit>();
		neighborList = new ArrayList<CrossPoint>();
	}
	
	
	
	/**
	 * ����������ھӽڵ��·�μ����ھ�·�ι�ϣ�����ؼ�����
	 * @param neighbor
	 * @param pathUnit
	 * @return
	 */
	public boolean addNeighbor(CrossPoint neighbor , PathUnit pathUnit) {
		//���neighbor�Ƿ��ظ�,���ظ�������ھӱ�
		if (neighborList.add(neighbor) == false) {
			return false;
		}
		//��·����Ϣ�����ھ�·�α�
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