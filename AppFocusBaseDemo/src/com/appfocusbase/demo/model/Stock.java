package com.appfocusbase.demo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "stock")
public class Stock {

	// ID @Id主键,int类型,数据库建表时此字段会设为自增长
	
	@DatabaseField(generatedId = true)
	private int id;	
	
	@DatabaseField(columnName = "stock_id")
	public String stock_id;
	
	@DatabaseField(columnName = "text1")
	public String text1;
	
	@DatabaseField(columnName = "text2")
	public String text2;
	
	@DatabaseField(columnName = "text3")
	public String text3;
	
	@DatabaseField(columnName = "text4")
	public String text4;
	
	@DatabaseField(columnName = "text5")
	public String text5;
	
	@DatabaseField(columnName = "text6")
	public String text6;
	
	@DatabaseField(columnName = "uid")
	public String uid;


	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getText3() {
		return text3;
	}

	public void setText3(String text3) {
		this.text3 = text3;
	}

	public String getText4() {
		return text4;
	}

	public void setText4(String text4) {
		this.text4 = text4;
	}

	public String getText5() {
		return text5;
	}

	public void setText5(String text5) {
		this.text5 = text5;
	}

	public String getText6() {
		return text6;
	}

	public void setText6(String text6) {
		this.text6 = text6;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStock_id() {
		return stock_id;
	}

	public void setStock_id(String stock_id) {
		this.stock_id = stock_id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Stock() {
	 
	}



}
