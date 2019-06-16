package in.serosoft.hackathon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Course {

	
	private static String DB="SEROSOFT";
	private static String TABLE="COURSE";
	private Connection con;
	private Statement st;
	private String query;
	private ArrayList<String> course_group;
	private ArrayList<String >course_name;
	private ArrayList<String >course_code;
	private ArrayList<String >type;
	private ArrayList<Integer>credit;
	private ArrayList<String>period;
	private Map<String,ArrayList<String>> mapGroupToCourses;
	private Map<String,Integer> mapCourseToCredit;
	
	public Course()	//constructor
	{
		ResultSet res;
		try {
			course_group = new ArrayList<String>();
			course_name =  new ArrayList<String>();
			course_code =  new ArrayList<String>();
			type = new ArrayList<String>();
			credit = new ArrayList<Integer>();
			period = new ArrayList<String>();
			mapGroupToCourses = new HashMap<>();
			mapCourseToCredit = new HashMap<>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/"+Course.DB,"root","");
			st=con.createStatement();
		
			query="SELECT DISTINCT COURSE_GROUP FROM COURSE WHERE TYPE <> 'MANDATORY' ORDER BY RIGHT(course_code,LOCATE(' ',course_code) + 1) ASC";
			res=st.executeQuery(query);
			while(res.next())
			{
				course_group.add(res.getString(1));
			}
			
			query="SELECT DISTINCT COURSE_NAME FROM COURSE ORDER BY RIGHT(course_code,LOCATE(' ',course_code) + 1) ASC";
			res=st.executeQuery(query);
			while(res.next())
			{
				course_name.add(res.getString(1));
			}
			
			query="SELECT DISTINCT COURSE_CODE FROM COURSE ORDER BY RIGHT(course_code,LOCATE(' ',course_code) + 1) ASC";
			res=st.executeQuery(query);
			while(res.next())
			{
				course_code.add(res.getString(1));
			}
			
			query="SELECT TYPE FROM COURSE ORDER BY RIGHT(course_code,LOCATE(' ',course_code) + 1) ASC";
			res=st.executeQuery(query);
			while(res.next())
			{
				type.add(res.getString(1));
			}
			
			query="SELECT CREDIT FROM COURSE ORDER BY RIGHT(course_code,LOCATE(' ',course_code) + 1) ASC";
			res=st.executeQuery(query);
			while(res.next())
			{
				credit.add(res.getInt(1));
			}
			
			query="SELECT PERIOD FROM COURSE ORDER BY RIGHT(course_code,LOCATE(' ',course_code) + 1) ASC";
			res=st.executeQuery(query);
			while(res.next())
			{
				period.add(res.getString(1));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<String> getCourseName()
	{
		return course_name;
	}
	
	public ArrayList<String> getCourseGroup()
	{
		return course_group;
	}
	
	public ArrayList<Integer> getCredit() {
		return credit;
	}


	int getTotalCreditForMandatory()
	{
		int totalcredit = 0;
		String query="SELECT SUM(CREDIT) FROM COURSE WHERE TYPE = 'MANDATORY';";
		ResultSet res;
		try {
			res = st.executeQuery(query);
			while(res.next())
			{
				totalcredit = res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalcredit;
	}
	
	ArrayList<String> getMandatoryCourseList()
	{
		ArrayList<String> mandatory = new ArrayList<>();
		String query = "SELECT COURSE_NAME FROM COURSE WHERE TYPE = 'MANDATORY';";
		ResultSet res;
		try {
			res = st.executeQuery(query);
			while(res.next())
			{
				mandatory.add(res.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mandatory;
	}
	
	int getCreditForCourse(String course)
	{
		int credit = 0;
		String query = "SELECT CREDIT FROM COURSE WHERE COURSE_NAME = '"+ course +"';";
		//System.out.println(course);
		ResultSet res;
		try {
			res = st.executeQuery(query);
			while(res.next())
			{
				credit = res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return credit;
	}
	
	Map<String,ArrayList<String>> getMappedGroupList()
	{
		for(String group : course_group)
		{
			ArrayList<String> coursesPerGroup = new ArrayList<String>();
			String query = "SELECT COURSE_NAME FROM COURSE WHERE COURSE_GROUP = '"+ group +"';";
			ResultSet res;
			try {
				res = st.executeQuery(query);
				while(res.next())
				{
					coursesPerGroup.add(res.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mapGroupToCourses.put(group,coursesPerGroup);
		}
		return mapGroupToCourses;
	}
	public Map<String, Integer> getMappedCreditList() {
		// TODO Auto-generated method stub
		for(String course : course_name)
		{
			Integer credit = 0;
			String query = "SELECT CREDIT FROM COURSE WHERE COURSE_NAME = '"+ course +"';";
			ResultSet res;
			try {
				res = st.executeQuery(query);
				while(res.next())
				{
					credit = res.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mapCourseToCredit.put(course, credit);
		}
		return mapCourseToCredit;
	}

}
