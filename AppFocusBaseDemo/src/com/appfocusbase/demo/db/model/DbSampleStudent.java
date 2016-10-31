package com.appfocusbase.demo.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_student")
public class DbSampleStudent extends BaseDaoEnabled<DbSampleStudent, Integer>
{
	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private String name;
	
	public DbSampleStudent()
	{
	}

	

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}



	@Override
	public String toString() {
		return "DbSampleStudent [id=" + id + ", name=" + name + "]";
	}

}
