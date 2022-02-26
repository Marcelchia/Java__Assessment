package com.generation;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main
{

	public static void main( String[] args )
		throws ParseException
	{
		StudentService studentService = new StudentService();
		CourseService courseService = new CourseService();
		Scanner scanner = new Scanner( System.in );
		int option = 0;
		do
		{
			PrinterHelper.showMainMenu();
			option = scanner.nextInt();
			switch ( option )           //call the method to run selection at menu
			{
				case 1:
					registerStudent( studentService, scanner );
					break;
				case 2:
					findStudent( studentService, scanner );
					break;
				case 3:
					gradeStudent( studentService, scanner );
					break;
				case 4:
					enrollStudentToCourse( studentService, courseService, scanner );
					break;
				case 5:
					showStudentsSummary( studentService, scanner );     //see below for showStudentsSummary method
					break;
				case 6:
					showCoursesSummary( courseService, scanner );
					break;
				case 7:
					studentPassCourses (studentService, scanner);
					break;
				case 8:
					showAverageGrade (courseService, scanner);
					break;
			}
		}
		while ( option != 9 );
	}

	//show average grade for each course //Case 8
	private static void showAverageGrade (CourseService courseService, Scanner scanner)
	{
		//instantise CourseService (courseService) & called showAverage method in CourseService class
		courseService.showAverage();
	}

	//show student passed courses // Case 7
	private static void studentPassCourses (StudentService studentService, Scanner scanner)
	{
		System.out.println( "Enter student ID: " );
		String studentId = scanner.next();

		//returning Student Object
		Student student = studentService.findStudent(studentId);
		//findstudent returns the Student object based on passing in studentId

		//After Student instance object retrieved assigned as 'student'
		//using for-each loop, read the enrolledCourseGrade HashMap attached to this student object
		//thru for-each loop & keySet method to obtain the Key (courseId) & with the Key get.method to retrieve value
		//the value is the course grade given (default grade = 0)
		//display course enrolled
		for (String key : student.enrolledCourseGrade.keySet()) {
			if (student.enrolledCourseGrade.get(key) > 2) {
				System.out.println(student.enrolledCourse.get(key) + " Grade: " + student.enrolledCourseGrade.get(key));
			}
		}
	}

	//Show Course Summary //Case 6
	private static void showCoursesSummary( CourseService courseService, Scanner scanner )
	{
		//instantise CourseService (courseService and call showSummary method from CourseService class
		courseService.showSummary();
	}

	//Show Students Summary //Case 5
	private static void showStudentsSummary( StudentService studentService, Scanner scanner )
	{
		//instantise StudentService (studentService) and call showSummary method from StudentService class
		studentService.showSummary();
	}

	//Enroll Students to Course // Case 4
	private static void enrollStudentToCourse( StudentService studentService, CourseService courseService, Scanner scanner ) {
		System.out.println("Insert student ID");
		String studentId = scanner.next();
		Student student = studentService.findStudent(studentId);
		//instantise StudentService (student) & retrieve specific student object with studentId
		if (student == null) {      //student object doesn't exist
			System.out.println("Invalid Student ID");
			return;
		}
		System.out.println(student);        //student object found & print
		System.out.println("Insert course ID");
		String courseId = scanner.next();
		Course course = courseService.getCourse(courseId);
		//instantise CourseService (course) & retrieve from courses HashMap for specific value based on courseId
		if (course == null) {       //courses doesn't found courseId match in the HashMap key
			System.out.println("Invalid Course ID");
			return;
		}
		System.out.println(course);
		courseService.enrollStudent(courseId, student);
		//add courseId and add student to arrayList in a Hashmap - enrolledStudents in CourseService class
		studentService.enrollToCourse(studentId, course);
		System.out.println("Student with ID: " + studentId + " enrolled successfully to " + courseId);

	}

	//Grade Student //Case 3
	private static void gradeStudent( StudentService studentService, Scanner scanner )
	{
		//TODO grade student
		System.out.println( "Enter student ID: " );
		String studentId = scanner.next();

		//returning Student Object
		Student student = studentService.findStudent(studentId);

		//display course enrolled
		for (String i : student.enrolledCourse.keySet()) {
			System.out.println(student.enrolledCourse.get(i));
		}

		//Get input from user to give you the Course ID
		System.out.println( "Enter Course code to be grade: " );
		String courseId = scanner.next();

		System.out.println( "Enter grade to the course: " );
		double grade = scanner.nextDouble();

		student.setGrade(courseId, grade);

	}





	//Find Student //Case 2
	private static void findStudent( StudentService studentService, Scanner scanner )
	{
		System.out.println( "Enter student ID: " );
		String studentId = scanner.next();
		Student student = studentService.findStudent( studentId );
		if ( student != null )
		{
			System.out.println( "Student Found: " );
			System.out.println( student );
		}
		else
		{
			System.out.println( "Student with Id = " + studentId + " not found" );
		}
	}

	//Register Student //Case 1
	private static void registerStudent( StudentService studentService, Scanner scanner )
		throws ParseException
	{
		Student student = PrinterHelper.createStudentMenu( scanner );
		studentService.subscribeStudent( student );
	}
}
