package com.appfocusbase.demo.db.activity;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.af.activity.AfActivity;
import com.af.view.ioc.AfIocView;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.db.DatabaseHelperAndbaseDemo;
import com.appfocusbase.demo.db.dao.DbSampleArticleDao;
import com.appfocusbase.demo.db.dao.DbSampleUserDao;
import com.appfocusbase.demo.db.model.DbSampleArticle;
import com.appfocusbase.demo.db.model.DbSampleStudent;
import com.appfocusbase.demo.db.model.DbSampleUser;
import com.appfocusbase.global.MyApplication;
import com.j256.ormlite.dao.Dao;
 
/**
 * 名称：DBActivity
 * 描述：本地数据库相关
 * @author kaka
 * @date 2011-12-13
 * @version
 * 参考文章
 * http://blog.csdn.net/lmj623565791/article/details/39122981
 */
public class DBActivity extends AfActivity {
	
	private MyApplication application;
	private  StringBuilder sb = new StringBuilder();

	private  DbSampleUserDao mUserDao;
	private  DbSampleArticleDao mArticleDao;
	@AfIocView(id = R.id.lyyj_tv_db_demo)	TextView tv  ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.lyyj_db_demo);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.db_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        
        mUserDao = new DbSampleUserDao(this);
        mArticleDao = new DbSampleArticleDao(this);        
        
		testAddArticle();
		testGetArticleById();
		testGetArticleWithUser();
		testListArticlesByUserId();
		testGetUserById();
			testAddStudent();
		testGetStudent();
		tv.setText(sb.toString());
      } 
    
    
    //增加文章及对应作者到数据库
	public void testAddArticle()
	{
		 //step1:增加三个作者
		DbSampleUser u = new DbSampleUser();
		u.setName("zhao");
		DbSampleUser u2 = new DbSampleUser();
		u2.setName("xiao");
		DbSampleUser u3 = new DbSampleUser();
		u3.setName("yun");			
		mUserDao.add(u);
		mUserDao.add(u2);
		mUserDao.add(u3);
		sb.append("testaddArticle已增加的三个作者如下："+"\n");
		List<DbSampleUser>  users = mUserDao.getAll();
		if((users != null)&&(users.size()>0)){			 
			for(DbSampleUser user:users){
				sb.append("users["+users.indexOf(user)+"]："+user.toString()+"\n");
			}
		}
		
		
		DbSampleArticle article = new DbSampleArticle();
		article.setTitle("第一篇文章");
		article.setUser(u);
		mArticleDao.add(article);
		DbSampleArticle article2 = new DbSampleArticle();
		article2.setTitle("第二篇文章");
		article2.setUser(u);
		mArticleDao.add(article2);
		DbSampleArticle article3 = new DbSampleArticle();
		article3.setTitle("第三篇文章");
		article3.setUser(u3);
		mArticleDao.add(article3);		
		
		sb.append("testaddArticle增加三篇文章如下："+"\n");
		List<DbSampleArticle>  articles = mArticleDao.getAll();
		if((articles != null)&&(articles.size()>0)){			 
			for(DbSampleArticle art:articles){
				sb.append("articles["+articles.indexOf(art)+"]："+art.toString()+"\n");
			}
		}
	}

	public void testGetArticleById()
	{
		DbSampleArticle article = new DbSampleArticleDao(this).get(1);
		sb.append("testGetArticleById测试通过ID查询文章并一并获得对应作者（一对一关系）"+"\n"+"文章作者:"+article.getUser().toString()+"\n"+"文章（id1）:"+article.toString()+"\n");
	}

	public void testGetArticleWithUser()
	{

		DbSampleArticle article = new DbSampleArticleDao(this).getArticleWithUser(1);
		sb.append("getArticleWithUser测试通过ID查询文章并一并获得对应作者（一对一关系,手动refresh）"+"\n"+"文章作者:"+article.getUser().toString()+"\n"+"文章（id1）:"+article.toString()+"\n");
 
	}

	public void testListArticlesByUserId()
	{
		List<DbSampleArticle> articles = new DbSampleArticleDao(this).listByUserId(1);
		sb.append("testListArticlesByUserId通过ID获取作者，并列出该作者的所有文章"+"\n");
		if((articles != null)&&(articles.size()>0)){			 
			for(DbSampleArticle art:articles){
				sb.append("articles["+articles.indexOf(art)+"]："+art.toString()+"\n");
			}
		}
	}

	public void testGetUserById()
	{
		DbSampleUser user = new DbSampleUserDao(this).get(1);
		sb.append("testGetUserById()通过ID获取作者，并列出该作者的所有文章(一对多)"+"\n");
		if(user == null) return;
		sb.append("作者信息："+user.toString()+"\n");
		Collection<DbSampleArticle> articles =  user.getArticles();
		if((articles != null)&&(articles.size()>0)){			 
			for(DbSampleArticle art:articles){
				sb.append("article[]："+art.toString()+"\n");
			}
		}
	}

	
	//当Bean继承BaseDaoEnabled时，可以使用bean.create(bean)；bean.update(bean)一类操作
	//另一种DAO使用方式
	public void testAddStudent() 
	{
		Dao dao;
		try {
			dao = DatabaseHelperAndbaseDemo.getHelper(this).getDao(DbSampleStudent.class);
			DbSampleStudent student = new DbSampleStudent();
			student.setDao(dao);
			student.setName("malata");
			student.create();
			sb.append("testAddStudent()用BaseDaoEnabled方式增加学生"+"\n"+"student:"+student+"\n");
			DbSampleStudent student2 = new DbSampleStudent();
			student2.setDao(dao);
			student2.setName("mobile");
			student2.create();
			sb.append("testAddStudent()用BaseDaoEnabled方式增加学生"+"\n"+"student2:"+student2+"\n");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testGetStudent() 
	{
		Dao dao;
		try {
			dao = DatabaseHelperAndbaseDemo.getHelper(this).getDao(DbSampleStudent.class);
			List<DbSampleStudent> students = dao.queryForAll();
			sb.append("testGetStudent()用BaseDaoEnabled方式增加的学生："+"\n");
			if((students != null)&&(students.size()>0)){			 
				for(DbSampleStudent stu:students){
					sb.append("students["+students.indexOf(stu)+"]："+stu.toString()+"\n");
				}
			}						

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  
}
