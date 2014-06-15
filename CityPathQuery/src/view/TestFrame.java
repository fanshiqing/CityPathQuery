package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.TranslateMapFile;
import models.mapItems.*;



// for my test
public class TestFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AbstractMap absMap;
	public MapPanel mapPanel;
	Map map;
	
	public TestFrame () {
		map = TranslateMapFile.translateMap();
		mapPanel = new MapPanel(map);
		add(mapPanel);
		absMap = new AbstractMap(map);
	}
	
	public static void main(String[] args) {
		TestFrame frame = new TestFrame();
		frame.setTitle("Map");
		//frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 768);
		frame.setVisible(true);
		
		//Coordinate startCoor = new Coordinate(150, 200);
		//Coordinate endCoor = new Coordinate(500, 520);
		Path path = frame.absMap.findPath(frame.map.getLocationByName(" ≥Ã√"), frame.map.getLocationByName("–£ ∑π›"));
		frame.mapPanel.paintPath(path);
	}
}
	
