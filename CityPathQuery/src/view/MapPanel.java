package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.mapItems.Location;
import models.mapItems.Map;
import models.mapItems.Path;
import models.mapItems.PathUnit;

/**
 * 绘制Map的Panel,其构造参数需要传入Map
 * 调用公共接口paintPath来在地图中绘制一条路径
 * @author Jeff
 *
 */
public class MapPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Map map;
	
	private Path currentPath;
	
	public MapPanel(Map map) {
		this.map = map;
		currentPath = new Path();
	}
	
	public MapPanel() {
		super();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		paintMap(g, map);
		paintPath(g, currentPath);
		
	}
	
	private void paintMap(Graphics g, Map map) {
		Color originColor = g.getColor();
		g.setColor(Color.white);
		paintPathUnits(g, map.getPathUnitList());
		g.setColor(Color.black);
		paintLocation(g, map.getLocationList());
		g.setColor(originColor);
	}
	
	private void paintPathUnits(Graphics g, ArrayList<PathUnit> pathUnitList) {
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
		for (PathUnit pathUnit : pathUnitList) {
			int x1 = (int) pathUnit.getStartPoint().getX();
			int y1 = (int) pathUnit.getStartPoint().getY();
			int x2 = (int) pathUnit.getEndPoint().getX();
			int y2 = (int) pathUnit.getEndPoint().getY();			
            g2.draw(new Line2D.Float(x1, y1, x2, y2));
		}
	}
	
	private void paintLocation(Graphics g, ArrayList<Location> locationList) {
		for (Location location : locationList) {
			int x = (int) location.getCoordinate().getX();
			int y = (int) location.getCoordinate().getY();
			String name = location.getLocationName();
			g.drawRect(x, y, 3, 3);
			g.drawString(name, x + 10, y + 10);
			
		}
	}
	
	private void paintPath(Graphics g, Path path) {
		// Graphics g = getGraphics();
		ArrayList<PathUnit> pathUnits = path.getPathUnitList();
		Color originColor = g.getColor();

		// test
		/*for (PathUnit p : pathUnits) {
			System.out.println("start point : " + p.getStartPoint().getX()
					+ "  " + p.getStartPoint().getY() + "  "
					+ p.getEndPoint().getX() + "  " + p.getEndPoint().getY());
		}*/

		g.setColor(Color.red);
		paintPathUnits(g, pathUnits);
		g.setColor(originColor);
	}
	 
	public void paintPath(Path path) {
		currentPath = path;
		repaint();
	}
	 
}