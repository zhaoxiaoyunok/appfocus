package com.appfocusbase.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.appfocusbase.demo.db.model.DbSampleArticle;
import com.appfocusbase.demo.db.model.DbSampleStudent;
import com.appfocusbase.demo.db.model.DbSampleUser;
import com.appfocusbase.demo.model.LocalUser;
import com.appfocusbase.model.User;
import com.appfocusbase.util.download.DownFile;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

 

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseHelperAndbaseDemo extends OrmLiteSqliteOpenHelper {

	// name of the database file for your application -- change to something appropriate for your app
	private static final String DATABASE_NAME = "andbasedemo.db";
	// any time you make changes to your database objects, you may have to increase the database version
	private static final int DATABASE_VERSION = 2;
	
	private static DatabaseHelperAndbaseDemo instance;
	// the DAO object we use to access the SimpleData table
	private Map<String, Dao> daos = new HashMap<String, Dao>();
	 
/**
	public DatabaseHelperSample(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
	}
*/
	public DatabaseHelperAndbaseDemo(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	//	super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
	}
	/**
	 * This is called when the database is first created. Usually you should call createTable statements here to create
	 * the tables that will store your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelperAndbaseDemo.class.getName(), "onCreate");
		 	//TableUtils.createTable(connectionSource, LocalUser.class);
			//TableUtils.createTable(connectionSource, IMMessage.class);
			TableUtils.createTable(connectionSource, DownFile.class);
			TableUtils.createTable(connectionSource, User.class);		
//			TableUtils.createTable(connectionSource, Friend.class);	
			TableUtils.createTable(connectionSource, DbSampleUser.class);
			TableUtils.createTable(connectionSource, DbSampleArticle.class);
			TableUtils.createTable(connectionSource, DbSampleStudent.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelperAndbaseDemo.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
	 * the various data to match the new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelperAndbaseDemo.class.getName(), "onUpgrade");
			// TableUtils.dropTable(connectionSource, LocalUser.class, true);
			//TableUtils.dropTable(connectionSource, IMMessage.class, true);
			TableUtils.dropTable(connectionSource, DownFile.class, true);		
			TableUtils.dropTable(connectionSource, User.class, true);	
			//TableUtils.dropTable(connectionSource, Friend.class, true);	
			
			TableUtils.dropTable(connectionSource, DbSampleUser.class, true);		
			TableUtils.dropTable(connectionSource, DbSampleArticle.class, true);	
			TableUtils.dropTable(connectionSource, DbSampleStudent.class, true);	
			
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelperAndbaseDemo.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}


	/**
	 * 单例获取该Helper
	 * 
	 * @param context
	 * @return
	 */
	public static synchronized DatabaseHelperAndbaseDemo getHelper(Context context)
	{
		context = context.getApplicationContext();
		if (instance == null)
		{
			synchronized (DatabaseHelperAndbaseDemo.class)
			{
				if (instance == null)
					instance = new DatabaseHelperAndbaseDemo(context);
			}
		}

		return instance;
	}

	public synchronized Dao getDao(Class clazz) throws SQLException
	{
		Dao dao = null;
		String className = clazz.getSimpleName();

		if (daos.containsKey(className))
		{
			dao = daos.get(className);
		}
		if (dao == null)
		{
			dao = super.getDao(clazz);
			daos.put(className, dao);
		}
		return dao;
	}


	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		
		for (String key : daos.keySet())
		{
			Dao dao = daos.get(key);
			dao = null;
		}
	}
}
