package com.lyyj.activity.demo.gridview.gappsdraggridview;

public interface DragGridBaseAdapter {
	/**
	 * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿?
	 * @param oldPosition
	 * @param newPosition
	 */
	public void reorderItems(int oldPosition, int newPosition);
	
	
	/**
	 * ï¿½ï¿½ï¿½ï¿½Ä³ï¿½ï¿½itemï¿½ï¿½ï¿½ï¿½
	 * @param hidePosition
	 */
	public void setHideItem(int hidePosition);
	

}
