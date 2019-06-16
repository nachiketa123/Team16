package in.serosoft.hackathon;

import java.sql.Connection;
import java.util.ArrayList;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String DBNAME="serosoft";
		final String TABLE_STUDENT="student";
		final String TABLE_COURSE="course";
		final String TABLE_TIMETABLE="timetable";
		Connection con;
		
		BackendCreator bc=new BackendCreator(DBNAME);
		bc.createDatabase();
		bc.createTableStudent(TABLE_STUDENT, DBNAME);
		bc.createTableCourse(TABLE_COURSE, DBNAME);
		bc.createTableTimeTable(TABLE_TIMETABLE, DBNAME);
		
		con=bc.getCon();
		
		String path="'F:\\\\Team16\\\\HackathonTechKit\\\\Student.csv'";
		CsvReader csv=new CsvReader();
		csv.readCSV(DBNAME,TABLE_STUDENT,path);
		
		path="'F:\\\\Team16\\\\HackathonTechKit\\\\Course.csv'";
		csv.readCSV(DBNAME,TABLE_COURSE,path);
		
		path="'F:\\\\Team16\\\\HackathonTechKit\\\\Timetable.csv'";
		csv.readCSV(DBNAME,TABLE_TIMETABLE,path);
		
		Enrollment student = new Enrollment(con);
		student.setVisible(true);
		
		/*
		ArrayList<String> x = new Course().getCourseName();
		for(String str : x)
		{
			System.out.println(str);
		}
		*/
	}

}
