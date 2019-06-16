package in.serosoft.hackathon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Enrollment extends JFrame implements ActionListener{
	protected static final String DB = "serosoft";
	private JTextField id;
	private JLabel msg;
	private Connection con;
	private Statement st;
	private JLabel lblEnterYourStudent;
	private JButton submit,finalsubmit;
	private JPanel panel;
	private JLabel totalcr;
	private JLabel mandatory;
	private int credit;
	private JPanel miniPanel;
	private JScrollPane miniScroll;
	private JLabel choose_upto_10;
	private JRadioButton []rb;
	private Map<String,Boolean> groupToBoolean;
	String query = null;
	
	public Enrollment(Connection con) {
		
		this.con=con;
		this.setVisible(true);
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setLayout(null);
		
		lblEnterYourStudent = new JLabel("Enter Your Student Id");
		lblEnterYourStudent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnterYourStudent.setBounds(60, 23, 248, 34);
		getContentPane().add(lblEnterYourStudent);
		
		id = new JTextField();
		id.setFont(new Font("Tahoma", Font.PLAIN, 18));
		id.setBounds(304, 23, 167, 34);
		id.setToolTipText("Enter Your Student Id");
		getContentPane().add(id);
		id.setColumns(10);
		
		msg = new JLabel();
		msg.setHorizontalAlignment(SwingConstants.CENTER);
		msg.setBounds(150, 66, 443, 14);
		getContentPane().add(msg);
		
		//radiobutton array initializing
		rb = new JRadioButton[new Course().getCourseName().size()];
	
		//mapGroupToBoolean
		groupToBoolean = new HashMap<String,Boolean>();
		
		submit = new JButton("SUBMIT");
		submit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		submit.setBounds(481, 23, 112, 36);
		getContentPane().add(submit);
		
		finalsubmit = new JButton("ENROLL");
		finalsubmit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		double width,height;
		Dimension d = getContentPane().getSize();
		width = d.getWidth();
		height = d.getHeight();
		finalsubmit.setBounds((int)(width/2-100), (int)height-20,100, 40);
		getContentPane().add(finalsubmit);
		finalsubmit.setVisible(false);
		
		//setting click listener on final submit
		finalsubmit.addActionListener(this);
		
		
		panel = new JPanel();
		panel.setBounds(1, 1, 660, 276);
		
		JScrollPane scrollPane=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setLayout(null);
		scrollPane.setBounds(60, 144, 660, 276);
		
	
		
		
		credit = new Course().getTotalCreditForMandatory();
		totalcr = new JLabel("TOTAL CREDIT: ");
		totalcr.setBounds(300, 55, 150, 28);
		totalcr.setText("TOTAL CREDIT: "+credit);
		getContentPane().add(totalcr);
		
		mandatory = new JLabel();
		mandatory.setBounds(137,91,129,29);
		mandatory.setText("Mandatory Courses");
		getContentPane().add(mandatory);
		
		miniPanel = new JPanel();
		getContentPane().add(miniPanel);
		miniPanel.setBounds(1,1,296,48);
		
		miniScroll = new JScrollPane(miniPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		miniPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		miniScroll.setBounds(320,80,400,50);
		ArrayList<String> mandatoryList = new Course().getMandatoryCourseList();
		for(int i=0,x = 500,y =70;i<mandatoryList.size();++i)
		{
			
			String currentCourse = mandatoryList.get(i);
			//System.out.println("Current COurse :" + currentCourse);
			JLabel courseName = new JLabel();
			courseName.setBounds(x,y,100,50);
			courseName.setText(currentCourse + "\t Credit = " + new Course().getCreditForCourse(currentCourse) +",");
			miniPanel.add(courseName);
			x+=100;
			
		}

		getContentPane().add(miniScroll);
		
		choose_upto_10 = new JLabel("You have to choose one subject from each group such that your credits are >=10. ");
		choose_upto_10.setBounds(150,121,473,29);
		getContentPane().add(choose_upto_10);
		viewCourseList();
		getContentPane().add(scrollPane);
		scrollPane.setVisible(false);
		
		hideAll();
		
 
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkExistance())
				{
					unSetMessage();
					scrollPane.setVisible(true);
					unhideAll();					
				}
				else
				{
					scrollPane.setVisible(false);
					hideAll();
				}
			}

		});
		
		

		setTitle("REGISTRATION");
		setForeground(Color.WHITE);
		unSetMessage();
	}

	private void viewCourseList() {
		// TODO Auto-generated method stub
		ArrayList<String> course_group = new Course().getCourseGroup();
		int []creditPerGroup = new int[course_group.size()] ;//for storing credit helps in updating JLabel
		Map<String,Integer> totalCreditPerGroup = new HashMap<String,Integer>(); 
		final Map<String,Integer> tempCreditPerGroup = new HashMap<String,Integer>(); 
		ArrayList<String> groupWiseCourse;
		Map<String,ArrayList<String>> mapGroupToCourseName = new Course().getMappedGroupList();
		Map<String,Integer> mapCourseToCredit = new Course().getMappedCreditList();
		ArrayList<String> courseList = new Course().getCourseName();
		int x = 0,y = 0,X=0,Y=0;//X and Y are for prefferedsize function
		for(String groupName : course_group)
		{
			x=0;
			JLabel group = new JLabel(groupName);
			group.setBounds(x,y,100,50);
			panel.add(group);
			groupWiseCourse = mapGroupToCourseName.get(groupName);
			ButtonGroup bg = new ButtonGroup();
			for(String course : groupWiseCourse)
			{
				x+=200; //increment x coordinate for GUI
				X+=x;   //will be used for GUI
//				System.out.println(courseList.indexOf(course));
				rb[courseList.indexOf(course)] = new JRadioButton(course + " (Credit: "+ mapCourseToCredit.get(course) +")");
//				rb[courseList.indexOf(course)].setText();
				creditPerGroup[course_group.indexOf(groupName)] += mapCourseToCredit.get(course);
							
			
				rb[courseList.indexOf(course)].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(rb[courseList.indexOf(course)].isSelected())
						{
							groupToBoolean.put(groupName, true);
							int i = mapCourseToCredit.get(course);
							updateCredit(i,tempCreditPerGroup.get(groupName),totalCreditPerGroup.get(groupName));
							tempCreditPerGroup.put(groupName,totalCreditPerGroup.get(groupName) - i);
//							j = i;
						}						
					}
				});
				rb[courseList.indexOf(course)].setBounds(x,y,200,50);
				panel.add(rb[courseList.indexOf(course)]);
				bg.add(rb[courseList.indexOf(course)]);
								
			}
			y+=60;//increment y coordinate for GUI
			Y+=y;//increment y coordinate for GUI
			
			totalCreditPerGroup.put(groupName, creditPerGroup[course_group.indexOf(groupName)]);
			tempCreditPerGroup.put(groupName,0);
		}
		panel.setPreferredSize(new Dimension(X,Y));
	}

	public boolean checkExistance()
	{
		boolean flag = false;
		int count = 0;
		try {
				if(id.getText().equals(""))
				{
					setMessage("Invalid Input");
					return false;
				}
				else
				{
					unSetMessage();
					st=con.createStatement();
					String query="SELECT COUNT(*) FROM STUDENT WHERE STUD_ID = '" + id.getText() +"' ;";
					ResultSet res=st.executeQuery(query);
					while(res.next())
					{
						count=res.getInt("COUNT(*)");
					}
					if(count == 0)
					{
//						System.out.println("No Entry");
						setMessage("NO ENTRY FOUND WITH ID "+id.getText());
						flag = false;
					}
					else
					{
						unSetMessage();
						flag = true;
					}
				}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public void setMessage(String str)
	{
		msg.setText(str);
		msg.setVisible(true);
	}
	public void unSetMessage()
	{
		msg.setVisible(false);
		
	}
	private void unhideAll() {
		// TODO Auto-generated method stub
		totalcr.setVisible(true);
		mandatory.setVisible(true);
		miniScroll.setVisible(true);
		choose_upto_10.setVisible(true);
		
		
		//but hide the upper portion including textfield and submit button
		lblEnterYourStudent.setVisible(false);
		submit.setVisible(false);
		id.setVisible(false);
	}

	private void hideAll() {
		// TODO Auto-generated method stub
		totalcr.setVisible(false);
		mandatory.setVisible(false);
		miniScroll.setVisible(false);
		choose_upto_10.setVisible(false);
	}
	public void updateCredit(int x,int y,int total)
	{
		credit+=x;
		if(y!=0)
			credit-=(total-y);
		//System.out.println("total: "+total+ " x="+x + " y=" +y  +" credit:"+credit);
		if(credit>=10 && checkIfAllGroupSelected())
		{
			totalcr.setForeground(Color.BLUE);
			finalsubmit.setVisible(true);
		}
		else
		{
			totalcr.setForeground(Color.RED);
			finalsubmit.setVisible(false);
		}
		totalcr.setText("TOTAL CREDIT: "+credit);
	}
	
	public boolean checkIfAllGroupSelected()
	{
		boolean flag = true;
		for(JRadioButton radiobutton : rb)
		{
			ArrayList<String> groups = new Course().getCourseGroup();
			for(String groupName : groups)
			{
				if(groupToBoolean.get(groupName) == null)
				{
					flag = false;
					break;
				}
				else
				if(groupToBoolean.get(groupName) == false)
				{
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ArrayList<String> courseList = new Course().getCourseName();
		ArrayList<String> selectedCourse = new ArrayList(new Course().getMandatoryCourseList());
		if(arg0.getActionCommand().equals("ENROLL"))
		{
			try {
				st=con.createStatement();
				query = "CREATE TABLE IF NOT EXISTS ENROLLMENT(STUDENT_ID CHAR(15),COURSE_NAME CHAR(50));";
				st.execute(query);
				
				for(String courseName : courseList)
				{
					if(rb[courseList.indexOf(courseName)] != null)
					if(rb[courseList.indexOf(courseName)].isSelected())
					{
						selectedCourse.add(courseName);
					}
				}
				for(String courseName : selectedCourse)
				{
					query = "INSERT INTO ENROLLMENT VALUES('"+ id.getText() +"', '"+ courseName +"');";
					st.executeUpdate(query);
				}
				System.out.print("Enrollment successfull");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
