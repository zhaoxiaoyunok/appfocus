package com.appfocusbase.demo.db.dao;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.appfocusbase.db.DatabaseHelperAndbaseDemo;
import com.appfocusbase.demo.db.model.DbSampleArticle;
import com.appfocusbase.demo.db.model.DbSampleUser;
import com.j256.ormlite.dao.Dao;

public class DbSampleArticleDao
{
	private Dao<DbSampleArticle, Integer> articleDaoOpe;
	private DatabaseHelperAndbaseDemo helper;

	@SuppressWarnings("unchecked")
	public DbSampleArticleDao(Context context)
	{
		try
		{
			helper = DatabaseHelperAndbaseDemo.getHelper(context);
			articleDaoOpe = helper.getDao(DbSampleArticle.class);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 添加一个Article
	 * 
	 * @param article
	 */
	public void add(DbSampleArticle article)
	{
		try
		{
			articleDaoOpe.create(article);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 通过Id得到一个Article
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public DbSampleArticle getArticleWithUser(int id)
	{
		DbSampleArticle article = null;
		try
		{
			article = articleDaoOpe.queryForId(id);
			helper.getDao(DbSampleUser.class).refresh(article.getUser());

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return article;
	}

	/**
	 * 通过Id得到一篇文章
	 * 
	 * @param id
	 * @return
	 */
	public DbSampleArticle get(int id)
	{
		DbSampleArticle article = null;
		try
		{
			article = articleDaoOpe.queryForId(id);

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return article;
	}

	/**
	 * 通过UserId获取所有的文章
	 * 
	 * @param userId
	 * @return
	 */
	public List<DbSampleArticle> listByUserId(int userId)
	{
		try
		{
			/*QueryBuilder<Article, Integer> articleBuilder = articleDaoOpe
					.queryBuilder();
			QueryBuilder userBuilder = helper.getDao(User.class).queryBuilder();
			articleBuilder.join(userBuilder);
			
			
			Where<Article, Integer> where = queryBuilder.where();
			where.eq("user_id", 1);
			where.and();
			where.eq("name", "xxx");

			// 或者
			articleDaoOpe.queryBuilder().//
					where().//
					eq("user_id", 1).and().//
					eq("name", "xxx");
			//
			articleDaoOpe.updateBuilder().updateColumnValue("name","zzz").where().eq("user_id", 1);
			where.or(
					//
					where.and(//
							where.eq("user_id", 1), where.eq("name", "xxx")),
					where.and(//
							where.eq("user_id", 2), where.eq("name", "yyy")));*/

			return articleDaoOpe.queryBuilder().where().eq("user_id", userId)
					.query();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	

	
	public List<DbSampleArticle> getAll( )
	{
		try
		{
			return articleDaoOpe.queryForAll();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
		
	
}
