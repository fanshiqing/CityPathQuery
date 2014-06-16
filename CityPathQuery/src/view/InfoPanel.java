package view;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InfoPanel extends JScrollPane{
	
	private JTextArea innerArea;

	public InfoPanel() {
		super();
		
		//setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		/*getViewport().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				repaint();
				innerArea.repaint();
			}
		});*/
		
		innerArea = new JTextArea();
		
		setViewportView(innerArea);
	}
	
	public void printString(String string) {
		innerArea.append(string);
	}
	
	public void clear() {
		innerArea.setText("");
	}
	
	public void setEditable(boolean b) {
		innerArea.setEditable(b);
	}
}
