package com.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.StringEscapeUtils;

import com.model.User;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBHelper implements DBConfig{
	public static Connection getConnection() {
		MysqlDataSource mds=new MysqlDataSource();//����MySql����Դ
		mds.setDatabaseName(databaseName);//�������ݿ�����
		mds.setUser(username);
		mds.setPassword(password);
		try {
		     return mds.getConnection();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * �û����Ƿ����
	 */
	public static boolean exists(String username) {
		QueryRunner runner=new QueryRunner();
		String sql="select id from tb_user where username='"+username+"';";
		Connection conn=getConnection();
		ResultSetHandler<List<Object>> rsh=new ColumnListHandler();
		try {
			List<Object> result=runner.query(conn, sql, rsh);
			if(result.size()>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return false;
	}
	/*
	 * �û����������Ƿ���ȷ
	 */
	public static boolean check(String username,char[] password) {
		username=StringEscapeUtils.escapeSql(username);//���û�������û���ת��
		QueryRunner runner=new QueryRunner();
		String sql="select password from tb_user where username='"+username+"';";
		Connection conn=getConnection();
		ResultSetHandler<Object> rsh=new ScalarHandler();
		try {
			String result=(String)runner.query(conn, sql, rsh);
			char[] queryPassword=result.toCharArray();//����ѯ��������ת�����ַ�����
			if(Arrays.equals(password,queryPassword))
			{
				Arrays.fill(password,'0');
				Arrays.fill(queryPassword,'0');
				return true;
			}
			else
			{
				Arrays.fill(password, '0');
				Arrays.fill(queryPassword,'0');
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return false;
		}
	/*
	 * �����û������ע����Ϣ
	 */
	public static  boolean sava(User user) {
		QueryRunner runner=new QueryRunner();
		String sql="insert into tb_user(username,password,email) values(?,?,?);";
		Connection conn=getConnection();
		Object[] params= {user.getUsername(),user.getPassword(),user.getEmail()};
		try {
			int result=runner.update(conn, sql, params);
			if(result>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return false;
		}
	/*
	 * ��������
	 */
	public List<User> mysort(){
		QueryRunner runner=new QueryRunner();
		String sql="select * from tb_user order by level DESC,time DESC;";
		Connection conn=getConnection();
		//ResultSetHandler<List<User>> rsh=new ColumnListHandler();
		try {
			List<User> result=runner.query(conn, sql, new BeanListHandler<User>(User.class));
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
	}
	
	
	
	
	
	
}
