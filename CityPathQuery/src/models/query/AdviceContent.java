package models.query;

import java.net.URL;
import java.util.ArrayList;

import models.mapItems.Location;

/**
 * ÃèÊö£ºÆÀ¼ÛÄÚÈİ
 * @author ¶­½£·å
 *
 */
public class AdviceContent {
	String text;
	ArrayList<URL> videos;
	ArrayList<URL> pictures;
	
	public AdviceContent(String text) {
		// TODO Auto-generated constructor stub
		this.text = text;
		videos = new ArrayList<URL>();
		pictures = new ArrayList<URL>();
	}

	public String getText() {
		return text;
	}
	
	
}
