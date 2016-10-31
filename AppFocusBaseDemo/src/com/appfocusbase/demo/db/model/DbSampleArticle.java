package com.appfocusbase.demo.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_article")
public class DbSampleArticle
{
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String title;
	@DatabaseField(canBeNull = true, foreign = true, columnName = "user_id", foreignAutoRefresh = true)
	private DbSampleUser user;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public DbSampleUser getUser()
	{
		return user;
	}

	public void setUser(DbSampleUser user)
	{
		this.user = user;
	}

	@Override
	public String toString()
	{
		return "Article [id=" + id + ", title=" + title + ", user=" + user
				+ "]";
	}

}
