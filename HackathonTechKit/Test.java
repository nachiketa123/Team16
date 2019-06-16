import java.util.Scanner;

public class Test {
	static Scanner scanner = new Scanner(System. in);
	public static void main(String[] args) throws Exception{
		while(true) {
		String input = Show();
		int option = 0;;
		if(input.trim().equalsIgnoreCase("Q")) {
			System.exit(0);
		}
		else
		 option = Integer.parseInt(input);
		switch (option) {
		case 1:
			System.out.println("Please enter the course Name");
			input = scanner. nextLine();
			System.out.println("Monday - 11:00 to 12:00");
			System.out.println("TuesDay - 10:00 to 11:00");
			System.out.println("Press enter key to continue");
			input = scanner. nextLine();
			break;
		case 2:
			System.out.println("Please enter the course Name");
			input = scanner. nextLine();
			System.out.println("Course Conflict for Java");
			System.out.println("Computer Network - Monday - 11:00 to 12:00");
			System.out.println("Press enter key to continue");
			input = scanner. nextLine();
			break;
		
		case 3:
			System.out.println("Please enter the course name and studentid separated by comma");
			input = scanner. nextLine();
			System.out.println("Successfully enrolled");
			System.out.println("Press enter key to continue");
			input = scanner. nextLine();
			break;
		
		case 4:
			System.out.println("Please enter the course name and studentid separated by comma");
			input = scanner. nextLine();
			System.out.println("Successfully enrolled");
			System.out.println("Press enter key to continue");
			input = scanner. nextLine();
			break;
			
		case 5:
			System.out.println("Please enter the file name with full path");
			input = scanner. nextLine();
			System.out.println("The output are as below:");
			System.out.println("std001 - Successfully enrolled");
			System.out.println("std002 - Successfully enrolled");
			System.out.println("std003 - Capacity Fulled");
			System.out.println("std004 - already opted");
			System.out.println("std005 - Credit fulled");
			System.out.println("std006 - Successfully enrolled");
			System.out.println("std007 - Successfully enrolled");
			System.out.println("std008 - Capacity Fulled");
			System.out.println("std009 - already opted");
			System.out.println("std010 - Successfully enrolled");
			System.out.println("Press enter key to continue");
			input = scanner. nextLine();
			break;
		
		case 6:
			System.out.println("Please enter the file name with full path");
			input = scanner. nextLine();
			System.out.println("The timetable was successfully processed");
			System.out.println("Press enter key to continue");
			input = scanner. nextLine();
			break;
		
		case 7:
			System.out.println("Please enter the file name with full path");
			input = scanner. nextLine();
			System.out.println("The Course list was successfully processed");
			System.out.println("Press enter key to continue");
			input = scanner. nextLine();
			break;
			
		case 8:
			System.out.println("Please enter the file name with full path");
			input = scanner. nextLine();
			System.out.println("The student list was successfully processed");
			System.out.println("Press enter key to continue");
			input = scanner. nextLine();
			break;
		
		case 9:
			System.out.println("Please enter the student id");
			input = scanner. nextLine();
			System.out.println("The student has been enrolled to the below courses");
			System.out.println("DBMS - Mandatory Course");
			System.out.println("Operating System - Mandatory Course");
			System.out.println("C++ - Elective Course");
			System.out.println("Computer Network - Elective Course");
			System.out.println("Cyber Security - Elective Course");
			System.out.println("Press enter key to continue");
			input = scanner. nextLine();
			break;
		
		case 10:
			System.out.println("Please enter the course id");
			input = scanner. nextLine();
			System.out.println("The below students are enrolled to the courses");
			System.out.println("std001");
			System.out.println("std002");
			System.out.println("std003");
			System.out.println("std004");
			System.out.println("std005");
			System.out.println("std006");
			System.out.println("std007");
			System.out.println("Press enter key to continue");
			input = scanner. nextLine();
			break;
			
			
		default:
			break;
		}
	}
		
}
	
	public static String Show() {
		System.out.println("Menu");
		System.out.println("Welcome to Serosoft hackathon.");
		System.out.println("1. Calendar for Course");
		System.out.println("2. Course Conflict");
		System.out.println("3. Mandatory Course Enrollment");
		System.out.println("4. Elective Course Enrollment");
		System.out.println("5. Concurrent Enrollment");
		System.out.println("6. Upload Main Timetable");
		System.out.println("7. Upload Course List");
		System.out.println("8. Upload Student List"); 
		System.out.println("9. Student Enrollment List");
		System.out.println("10. Course Enrollment List");
		System.out.println("Q. Quit");
		String input = scanner. nextLine();
		return input;
	}

}
