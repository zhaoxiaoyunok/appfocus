package com.lyyj.activity.demo.gridview.horizontal.model;import java.io.Serializable;import java.util.List;public class MoveItems implements Serializable {	private static final long serialVersionUID = -7224258990836336830L;	private List<MoveItem> allItems;	private List<MoveItem> existItems;	public List<MoveItem> getAllItems() {		return allItems;	}	public void setAllItems(List<MoveItem> allItems) {		this.allItems = allItems;	}	public List<MoveItem> getExistItems() {		return existItems;	}	public void setExistItems(List<MoveItem> existItems) {		this.existItems = existItems;	}	public MoveItems(List<MoveItem> allItems, List<MoveItem> existItems) {		super();		this.allItems = allItems;		this.existItems = existItems;	}	public MoveItems() {		super();	}}