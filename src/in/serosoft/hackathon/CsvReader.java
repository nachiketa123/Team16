package in.serosoft.hackathon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class CsvReader {

	private BufferedReader br;
	private Connection con;
	private Statement st;
	public void readCSV(String dbname,String table,String path) 
	{
		String query;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbname,"root","");
			st=con.createStatement();
			query="TRUNCATE "+table +";";
			st.executeUpdate(query);
			query="LOAD DATA INFILE "+ path +" " + 
					"INTO TABLE "+ table + " " +
					"FIELDS TERMINATED BY ',' " +
					"LINES TERMINATED BY '\n' " +
					"IGNORE 1 ROWS ;";
			st.executeUpdate(query);
			System.out.println("table data inserted successfully");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
