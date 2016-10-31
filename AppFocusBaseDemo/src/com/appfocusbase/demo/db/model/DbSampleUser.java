package com.appfocusbase.demo.db.model;

import java.util.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_user")
public class DbSampleUser 
{
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(columnName = "name")
	private String name;

	@ForeignCollectionField
	private Collection<DbSampleArticle> articles;

	public Collection<DbSampleArticle> getArticles()
	{
		return articles;
	}

	public void setArticles(Collection<DbSampleArticle> articles)
	{
		this.articles = articles;
	}

	public DbSampleUser()
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
	public String toString()
	{
		return "User [id=" + id + ", name=" + name + ", articles=" + articles
				+ "]";
	}

	


	
}
