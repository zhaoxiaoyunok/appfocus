package com.appfocusbase.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "user")
public class User {

	// ID @Id主键,int类型,数据库建表时此字段会设为自增长
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

	// 昵称
	@DatabaseField(columnName = "nick_name")
	public String nickName;

	// 年龄一般是数值,用type = "INTEGER"规范一下.
	@DatabaseField(columnName = "age", dataType = DataType.INTEGER)
	private int age;

	// 用户性别
	@DatabaseField(columnName = "sex")
	public String sex;

	// 用户邮箱
	// 假设您开始时没有此属性,程序开发中才想到此属性,也不用卸载程序.
	@DatabaseField(columnName = "email")
	private String email;

	// 头像地址
	@DatabaseField(columnName = "head_url")
	private String headUrl;

	// 创建时间
	@DatabaseField(columnName = "create_time")
	private String createTime;

	// 城市
	@DatabaseField(columnName = "city")
	private String city;

	// 简介
	@DatabaseField(columnName = "intro")
	private String intro;

	// 积分
	@DatabaseField(columnName = "point")
	private int point;

	// 用户权限,0表示管理员，1表示会员
	@DatabaseField(columnName = "rights")
	public int rights;

	// 用户问题
	@DatabaseField(columnName = "question")
	public String question;

	// 用户答案
	@DatabaseField(columnName = "answer")
	public String answer;

	// 登录次数
	@DatabaseField(columnName = "login_count")
	public int loginCount;

	// 有些字段您可能不希望保存到数据库中,不用@Column注释就不会映射到数据库.
	private String remark;

	// 登录授权
	@DatabaseField(columnName = "access_token")
	private String accessToken;

	// 是否为当前登录
	@DatabaseField(columnName = "is_login_user")
	private boolean isLoginUser;



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getRights() {
		return rights;
	}

	public void setRights(int rights) {
		this.rights = rights;
	}


	public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl){
        this.headUrl = headUrl;
    }

    public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
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

	public boolean isLoginUser() {
		return isLoginUser;
	}

	public void setLoginUser(boolean isLoginUser) {
		this.isLoginUser = isLoginUser;
	}

	public User() {
 
	}

}
