package com.appfocusbase.demo.model;

import java.util.List;

import com.af.model.AfResult;
 

/**
 * 
 *
 */
public class ArticleListResult extends AfResult {

	private List<Article> items;

	public List<Article> getItems() {
		return items;
	}

	public void setItems(List<Article> items) {
		this.items = items;
	}
	
	

}
