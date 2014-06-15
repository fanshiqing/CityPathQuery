package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import models.mapItems.Location;
import models.mapItems.Map;
import models.mapItems.Path;
import models.mapItems.PathUnit;


public class MapPanel extends JScrollPane {
	private InnerPanel innerPanel;

	
	public MapPanel(Map map) {
		super();
		innerPanel = new InnerPanel(map);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		getViewport().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				repaint();
				innerPanel.repaint();
			}
		});
		
		setViewportView(innerPanel);
	}
	
	public void paintPath(ArrayList<Path> pathList) {
		repaint();
		innerPanel.paintPath(pathList);
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
	}
	
}


/**
 * 绘制Map的Panel,其构造参数需要传入Map
 * 调用公共接口paintPath来在地图中绘制一条路径
 * @author Jeff
 *
 */
class InnerPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Map map;
	
	private ArrayList<Path> pathList;
	
	public InnerPanel(Map map) {
		this.map = map;
		pathList = new ArrayList<Path>();
		setPreferredSize(new Dimension(1024, 768));
	}
	
	public InnerPanel() {
		super();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		paintMap(g, map);
		paintPath(g, pathList);
		
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
	
	private void paintPath(Graphics g, ArrayList<Path> pathList) {
		// Graphics g = getGraphics();
		for (Path p : pathList) {
			ArrayList<PathUnit> pathUnits = p.getPathUnitList();
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
		
	}
	 
	public void paintPath(ArrayList<Path> pathList) {
		if (pathList != null) {
			this.pathList = pathList;
			repaint();
		}
	}
	
	public void paintPathUnit(PathUnit pathUnit) {
		Path path = new Path();
		path.getPathUnitList().add(pathUnit);
		pathList.clear();
		pathList.add(path);
		repaint();
	}
}