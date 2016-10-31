package com.appfocusbase.demo.db.dao;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.appfocusbase.db.DatabaseHelperAndbaseDemo;
import com.appfocusbase.demo.db.model.DbSampleUser;
import com.j256.ormlite.dao.Dao;


public class DbSampleUserDao
{
	private Context context;
	private Dao<DbSampleUser, Integer> userDaoOpe;
	private DatabaseHelperAndbaseDemo helper;

	public DbSampleUserDao(Context context)
	{
		this.context = context;
		try
		{
			helper = DatabaseHelperAndbaseDemo.getHelper(context);
			userDaoOpe = helper.getDao(DbSampleUser.class);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 增加一个用户
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void add(DbSampleUser user) 
	{
		/*//事务操作
		TransactionManager.callInTransaction(helper.getConnectionSource(),
				new Callable<Void>()
				{

					@Override
					public Void call() throws Exception
					{
						return null;
					}
				});*/
		try
		{
			userDaoOpe.create(user);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	public DbSampleUser get(int id)
	{
		try
		{
			return userDaoOpe.queryForId(id);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	
	public List<DbSampleUser> getAll( )
	{
		try
		{
			return userDaoOpe.queryForAll();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
		
	
}
