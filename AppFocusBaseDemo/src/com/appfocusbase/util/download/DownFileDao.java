/*
 * Copyright (C) 2015 www.lyyj.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.appfocusbase.util.download;

import java.sql.SQLException;

import com.appfocusbase.db.DatabaseHelperAndbaseDemo;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



// TODO: Auto-generated Javadoc
/**
 * The Class DownFileDao.
 */
public class DownFileDao{
	private static Context mContext;
	private Dao<DownFile, Integer> userDaoOp;
	private DatabaseHelperAndbaseDemo helper;
	// 单例
	/** The m down file dao. */
	public static DownFileDao mDownFileDao = null;
	
	public DownFileDao(Context context)
	{
		this.mContext = context;
		try
		{
			helper = DatabaseHelperAndbaseDemo.getHelper(context);
			userDaoOp = helper.getDao(DownFile.class);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}




	/**
	 * Gets the single instance of DownFileDao.
	 *
	 * @param context the context
	 * @return single instance of DownFileDao
	 */
	public static DownFileDao getInstance(Context context) {
		    mContext = context;
			if (mDownFileDao == null) {
				mDownFileDao = new DownFileDao(context);
			}
			return mDownFileDao;
	}
	
	/**
	 * 获取已经下载的文件的信息.
	 *
	 * @param path the path
	 * @return the down file
	 */
	public DownFile getDownFile(String path){
		try {
		QueryBuilder<DownFile, Integer> queryBuilder =
				userDaoOp.queryBuilder();
		// the 'password' field must be equal to "qwerty"
		queryBuilder.where().eq("downUrl", path);
		// prepare our sql statement
		PreparedQuery<DownFile> preparedQuery = queryBuilder.prepare();
			return userDaoOp.queryForFirst(preparedQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
//		try {
//			 db = openHelper.getReadableDatabase();
//			 String where = "DOWNURL = ?";
//			 String[] whereValue = {path};
//			 cursor = db.query("FILEDOWN",null,where,whereValue,null,null,null);
//			 if(cursor.moveToFirst()){
//				mDownFile = new DownFile();
//				mDownFile.set_ID(getIntColumnValue("_ID",cursor));
//				mDownFile.setName(getStringColumnValue("NAME",cursor));
//				mDownFile.setDescription(getStringColumnValue("DESCRIPTION",cursor));
//				mDownFile.setPakageName(getStringColumnValue("PAKAGENAME",cursor));
//				mDownFile.setDownUrl(getStringColumnValue("DOWNURL",cursor));
//				mDownFile.setDownPath(getStringColumnValue("DOWNPATH",cursor));
//				mDownFile.setState(getIntColumnValue("STATE",cursor));
//				mDownFile.setDownLength(getIntColumnValue("DOWNLENGTH",cursor));
//				mDownFile.setTotalLength(getIntColumnValue("TOTALLENGTH",cursor));
// 				mDownFile.setSuffix(getStringColumnValue("DOWNSUFFIX",cursor));				
//			 }
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}finally{
//			//closeDatabase(cursor,db);
//		}
//		return mDownFile;
	}
	
	/**
	 * 保存线程已经下载的文件信息.
	 *
	 * @param mDownFile the m down file
	 * @return the long
	 */
	public synchronized long  save(DownFile downFile){
//		SQLiteDatabase db = null;
//		long row = 0;
//		try{
//			db = openHelper.getWritableDatabase();
//			ContentValues cv = new ContentValues();
//			cv.put("NAME", mDownFile.getName());
//			cv.put("DESCRIPTION", mDownFile.getDescription());
//			cv.put("PAKAGENAME", mDownFile.getPakageName());
//			cv.put("DOWNURL", mDownFile.getDownUrl());
//			cv.put("DOWNPATH", mDownFile.getDownPath());
//			cv.put("STATE",mDownFile.getState());
//			cv.put("DOWNLENGTH", mDownFile.getDownLength());
//			cv.put("TOTALLENGTH", mDownFile.getTotalLength());
//			cv.put("DOWNSUFFIX", mDownFile.getSuffix());
//			row = db.insert("FILEDOWN", null, cv);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeDatabase(null,db);
//		}
//		return row;
		try {
			return userDaoOp.create(downFile);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 实时更新线程已经下载的文件长度.
	 *
	 * @param mDownFile the m down file
	 * @return the long
	 */
	public synchronized long update(DownFile downFile){
 		long row = -1;
//		SQLiteDatabase db = null;
//		try {
//			db = openHelper.getWritableDatabase();
//			String where = "DOWNURL = ? ";
//			String[] whereValue = {mDownFile.getDownUrl()};
//			ContentValues cv = new ContentValues();
//			cv.put("STATE",mDownFile.getState());
//			cv.put("DOWNLENGTH", mDownFile.getDownLength());
//			cv.put("TOTALLENGTH", mDownFile.getTotalLength());
//			row = db.update("FILEDOWN",cv, where, whereValue);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeDatabase(null, db);
//		}

 		try {
// 	 		UpdateBuilder<DownFile, Integer> queryBuilder =
// 					userDaoOp.updateBuilder();
// 			// the 'password' field must be equal to "qwerty"
// 			queryBuilder.where().eq("downUrl", downFile.getDownUrl());
// 			// prepare our sql statement
// 			PreparedUpdate<DownFile> preparedQuery = queryBuilder.prepare();
			row = userDaoOp.update(downFile);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return row;
	}
		
	/**
	 * 删除对应的下载记录.
	 *
	 * @param path the path
	 * @return the long
	 */
	public synchronized long delete(String path){
		long row = -1;
//		SQLiteDatabase db = null;
//		try {
//			db = openHelper.getWritableDatabase();
//			String where = "DOWNURL = ? ";
//			String[] whereValue = {path};
//			row = db.delete("FILEDOWN", where, whereValue);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeDatabase(null, db);
//		}
 		try {
 	 		DeleteBuilder<DownFile, Integer> queryBuilder =
 					userDaoOp.deleteBuilder();
 			// the 'password' field must be equal to "qwerty"
 			queryBuilder.where().eq("downUrl", path);
 			// prepare our sql statement
 			PreparedDelete<DownFile> preparedQuery = queryBuilder.prepare();
			row = userDaoOp.delete(preparedQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	
}
