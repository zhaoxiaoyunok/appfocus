package com.appfocusbase.demo.model;

import java.util.List;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "local_user")
public class LocalUser {
	@DatabaseField(generatedId = true)
	private int id;	
	
	@DatabaseField(columnName = "uid")
	private String uid;

	// 登录用户名 length=20数据字段的长度是20
	@DatabaseField(columnName = "name", width = 20)
	private String name;

	// 用户密码
	@DatabaseField(columnName = "password")
	private String password;

	// 年龄一般是数值,用type = "INTEGER"规范一下.
	@DatabaseField(columnName = "age", dataType = DataType.INTEGER)
	private int age;

	// 创建时间
	@DatabaseField(columnName = "create_time")
	private String createTime;
	
	// 包含实体的存储，指定外键
	@DatabaseField(canBeNull = true,foreign = true, columnName = "stock", foreignAutoRefresh = true)
	private Stock stock;

	// 包含List的存储，指定外键
	@DatabaseField(canBeNull = true,foreign = true, columnName = "stocks", foreignAutoRefresh = true)
	private List<Stock> stocks;
	
	// 有些字段您可能不希望保存到数据库中,不用@Column注释就不会映射到数据库.
	@DatabaseField
	private String remark;



	
	public LocalUser() {
	
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public List<Stock> getStocks() {
		return stocks;
	}


	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Stock getStock() {
		return stock;
	}


	public void setStock(Stock stock) {
		this.stock = stock;
	}

}
