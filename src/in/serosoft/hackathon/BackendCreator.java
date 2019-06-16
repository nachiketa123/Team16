package in.serosoft.hackathon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BackendCreator {
	
	private Connection con;
	private Statement st;
	
	private String dbname;
	public BackendCreator(String dbname)
	{
		this.dbname=dbname;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/","root","");
			st=con.createStatement();
			String query="USE "+ dbname +";";
			st.executeQuery(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public Connection getCon() {
		return con;
	}
	public void createDatabase()
	{
		try {
			String query="CREATE DATABASE IF NOT EXISTS "+ dbname + ";";
			st.executeUpdate(query);
			System.out.println("database created successfully");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void createTableStudent(String table,String database)
	{
		try
		{
			String query="USE "+ dbname +";";
			st.executeQuery(query);
			query="CREATE TABLE IF NOT EXISTS "+ table + "(stud_id varchar(20) PRIMARY KEY,name varchar(20),city longtext);";
			st.executeUpdate(query);
			System.out.println("table "+ table +" created successfully..");
		}catch(Exception sql)
		{
			sql.printStackTrace();
		}
	}	
		public void createTableCourse(String table,String database)
		{
			try
			{
				String query="USE "+ dbname +";";
				st.executeQuery(query);
				query="CREATE TABLE IF NOT EXISTS "+ table + "(course_code varchar(20) PRIMARY KEY,course_name varchar(30),credit int,type varchar(20),period varchar(20),course_group varchar(20));";
				st.executeUpdate(query);
				System.out.println("table "+ table +" created successfully..");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void createTableTimeTable(String table,String database)
		{
			try
			{
				String query="USE "+ dbname +";";
				st.executeQuery(query);
				query="CREATE TABLE IF NOT EXISTS "+ table + "(days varchar(20),10_11 longtext,11_12 longtext,12_01 longtext,01_02 longtext,02_03 longtext,03_04 longtext);";
				st.executeUpdate(query);
				System.out.println("table "+ table +" created successfully..");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
}
	
	


