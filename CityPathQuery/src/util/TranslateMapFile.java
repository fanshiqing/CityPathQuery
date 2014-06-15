package util;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import models.mapItems.Coordinate;
import models.mapItems.Location;
import models.mapItems.Map;
import models.mapItems.PathUnit;

public class TranslateMapFile {
	
	
	public static ArrayList<Location> translateLocation(String filename) {
		File inFile = new File(filename);
		Scanner input = null;
		try {
			input = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("file not exists");
			e.printStackTrace();
		}				
		
		ArrayList<Location> locationList = new ArrayList<Location>();
		
		while (input.hasNextLine()) {
			String newLine = input.nextLine();
			
			Scanner newLineScanner = new Scanner(newLine);
			
			int x = newLineScanner.nextInt();
			int y = newLineScanner.nextInt();
			String name = newLineScanner.next();
			
			Location newLocation = new Location(new Coordinate(x,y), name);
			locationList.add(newLocation);
			
			newLineScanner.close();
		}
		
		input.close();
		
		return locationList;
	}
	
	public static ArrayList<PathUnit> translatePathUnit(String filename) {
		File inFile = new File(filename);
		Scanner input = null;
		try {
			input = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("file not exists");
			e.printStackTrace();
		}				
		
		ArrayList<PathUnit> pathUnitList = new ArrayList<PathUnit>();
		
		while (input.hasNextLine()) {
			String newLine = input.nextLine();
			
			Scanner newLineScanner = new Scanner(newLine);
			
			int x1 = newLineScanner.nextInt();
			int y1 = newLineScanner.nextInt();
			int x2 = newLineScanner.nextInt();
			int y2 = newLineScanner.nextInt();
			
			PathUnit newPathUnit = new PathUnit(new Coordinate(x1, y1), new Coordinate(x2, y2));
			pathUnitList.add(newPathUnit);
			
			newLineScanner.close();
		}
		
		input.close();
		
		return pathUnitList;
	}
	
	/**
	 * 计算一个点和路段的切点坐标
	 * @return
	 */
	private static Coordinate calRoadEntry (PathUnit pathUnit, Coordinate coor) {
		double x,y,x0,y0,x1,y1,x2,y2;
		x0 = pathUnit.getStartPoint().getX();
		y0 = pathUnit.getStartPoint().getY();
		x1 = pathUnit.getEndPoint().getX();
		y1 = pathUnit.getEndPoint().getY();
		x2 = coor.getX();
		y2 = coor.getY();
		if(y0==y1)
		{
			x=x2;
			y=y0;
		}
		else if(x0==x1)
		{
			y=y2;
			x=x0;
		}
		else {
			double x3,y3,k1,k2;
			x3=x2+(y0-y1);
			y3=y2-(x0-x1);
			k1=(y0-y1)/(x0-x1);
			k2=(y2-y3)/(x2-x3);
			x=(k1*x0-k2*x2+y2-y0)/(k1-k2);
			y=y0+(x-x0)*k1;
		}
		//如果切点在路段之内
		if ((x0 <= x && x <= x1 && y0 <= y && y <= y1) 
				|| (x1 <= x && x <= x0 && y1 <= y && y <= y0) ) {
			return new Coordinate(x, y);
		}
		return null;
	}

	private static void addRoadEntry(Map map) {
		ArrayList<Location> locatioList = map.getLocationList();
		ArrayList<PathUnit> pathUnitList = map.getPathUnitList();
		for (Location p : locatioList) {
			double minDistance = Double.MAX_VALUE;
			PathUnit pathUnitToCut = null;
			for (PathUnit q : pathUnitList) {
				Coordinate roadEntry = calRoadEntry(q, p.getCoordinate());
				if (roadEntry != null 
						&& roadEntry.getDistance(p.getCoordinate()) < minDistance
						) {
					minDistance = roadEntry.getDistance(p.getCoordinate());
					System.out.println("roadEntry :  " + roadEntry.getX() + "," + roadEntry.getY() + "  distance : " + minDistance);
					pathUnitToCut = q;
 				}
			}
			//将该pathUnit切为两段
			Coordinate roadEntry = calRoadEntry(pathUnitToCut, p.getCoordinate());
			if (!p.getCoordinate().isEqual(roadEntry)) {
				
				PathUnit cut1 = new PathUnit(pathUnitToCut.getStartPoint(), roadEntry);
				pathUnitToCut.setStartPoint(roadEntry);
				pathUnitList.add(cut1);
				p.setRoadEntry(roadEntry);
			}
		}
	}
	
	public static Map translateMap() {
		ArrayList<PathUnit> pathUnitList = translatePathUnit("map_path_unit");
		ArrayList<Location> locationList = translateLocation("map_location");
		
		Map map = new Map(locationList, pathUnitList);
		
		addRoadEntry(map);
		
		return map;
	}
	
	public static void printMap(Map map) {
		System.out.println("Path Units are :");
		for (PathUnit pathUnit : map.getPathUnitList()) {
			Coordinate start = pathUnit.getStartPoint();
			Coordinate end = pathUnit.getEndPoint();
			System.out.println(start.getX() + "," + start.getY() + "," + end.getX() + "," + end.getY());
		}
		System.out.println("\nLocations are :");
		for (Location location : map.getLocationList()) {
			Coordinate p = location.getCoordinate();
			String name = location.getLocationName();
			System.out.println(p.getX() + "  " + p.getY() + "  " + name);
		}
	}
	
	public static void main(String[] args) {		
		Map map = translateMap();
		printMap(map);
		
		PathUnit p = new PathUnit(new Coordinate(0, 0), new Coordinate(3, 4));
		Location l = new Location(new Coordinate(3, 0), "");
		Coordinate c = calRoadEntry(p, l.getCoordinate());
		System.out.println("" + c.getX() + "," + c.getY());
	}
	
}
