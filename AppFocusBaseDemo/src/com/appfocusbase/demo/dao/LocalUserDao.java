package com.appfocusbase.demo.dao;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.appfocusbase.db.DatabaseHelperAndbaseDemo;
import com.appfocusbase.demo.model.LocalUser;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 名称：CalorieDao.java 
 * 描述：本地数据库在sd中
 * @author zhaoqp
 * 
 * @version v1.0
 */
public class LocalUserDao{
	private Context context;
	private Dao<LocalUser, Integer> userDaoOp;
	private DatabaseHelperAndbaseDemo helper;

	public LocalUserDao(Context context)
	{
		this.context = context;
		try
		{
			helper = DatabaseHelperAndbaseDemo.getHelper(context);
			userDaoOp = helper.getDao(LocalUser.class);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 增加�?��用户
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void add(LocalUser user) 
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
			userDaoOp.create(user);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}


	/**
	 * 增加�?��用户
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void add(List<LocalUser> ysdts) 
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
			userDaoOp.create(ysdts);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}
	
	
	public LocalUser get(int id)
	{
		try
		{
			return userDaoOp.queryForId(id);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	
	public List<LocalUser> getAll( )
	{
		try
		{
			return userDaoOp.queryForAll();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int deleteAll( )
	{
		try
		{
			return userDaoOp.delete(userDaoOp.queryForAll());
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	

	
	public List<LocalUser> getAllByDate( String todayYMD)
	{
		try
		{
			// get our query builder from the DAO
			QueryBuilder<LocalUser, Integer> queryBuilder =
					userDaoOp.queryBuilder();
			// the 'password' field must be equal to "qwerty"
			queryBuilder.where().eq("create_time", todayYMD);
			// prepare our sql statement
			PreparedQuery<LocalUser> preparedQuery = queryBuilder.prepare();
			return userDaoOp.query(preparedQuery);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	
	public List<LocalUser> getAllSpecialOrderLimitOffset( String order,boolean ascending,int limit,int offset)
	{
		try
		{
			// get our query builder from the DAO
			QueryBuilder<LocalUser, Integer> queryBuilder =
					userDaoOp.queryBuilder();
			// the 'password' field must be equal to "qwerty"
			//queryBuilder.where().eq(" strftime('%Y-%m-%d',create_time) = ? ", todayYMD);
			queryBuilder.orderBy(order, ascending);
			queryBuilder.limit(limit);
			queryBuilder.offset(offset);
			// prepare our sql statement
			PreparedQuery<LocalUser> preparedQuery = queryBuilder.prepare();
			return userDaoOp.query(preparedQuery);
			 
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

		
	public int deleteAllByDate( String todayYMD)
	{
		try
		{
			// get our query builder from the DAO
			DeleteBuilder<LocalUser, Integer> queryBuilder =
					userDaoOp.deleteBuilder();
			// the 'password' field must be equal to "qwerty"
			queryBuilder.where().eq("create_time", todayYMD);
			// prepare our sql statement
			PreparedDelete<LocalUser> preparedQuery = queryBuilder.prepare();
			return userDaoOp.delete(preparedQuery);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	public void update(LocalUser u) {
		try {
			userDaoOp.update(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
			

	public LocalUser getDataByUid(int uid) {
		try
		{
			// get our query builder from the DAO
			QueryBuilder<LocalUser, Integer> queryBuilder =
					userDaoOp.queryBuilder();
			// the 'password' field must be equal to "qwerty"
			queryBuilder.where().eq("uid", uid);
			// prepare our sql statement
			PreparedQuery<LocalUser> preparedQuery = queryBuilder.prepare();
			return userDaoOp.queryForFirst(preparedQuery);
			 
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public int deleteDataByUid(int uid) {
		try
		{
			// get our query builder from the DAO
			DeleteBuilder<LocalUser, Integer> queryBuilder =
					userDaoOp.deleteBuilder();
			// the 'password' field must be equal to "qwerty"
			queryBuilder.where().eq("uid", uid);
			// prepare our sql statement
			PreparedDelete<LocalUser> preparedQuery = queryBuilder.prepare();
			return userDaoOp.delete(preparedQuery);
			 
			 
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}	
}
