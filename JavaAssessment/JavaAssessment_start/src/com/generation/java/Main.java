package com.generation.java;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;

public class Main
{
    public static void main( String[] args )
            throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner( System.in );
        int option;
        do
        {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch ( option )
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
                    enrollCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
                case 7:
                    showPassedCourses( studentService, scanner );
                    break;
            }
        }
        while ( option != 8 );
    }
		//case 4
		private static void enrollCourse( StudentService studentService, CourseService courseService, Scanner scanner )
		{
			System.out.println("Insert student ID");
			String studentId = scanner.next();
			Student student = studentService.findStudent(studentId);
			//instantise StudentService (student) & retrieve specific student object with studentId
			if (student == null) {      //student object doesn't exist
				System.out.println("Invalid Student ID");
				return;
			}
			System.out.println(student);
			System.out.println("Insert course ID");
			String courseId = scanner.next();
			Course course = courseService.getCourse(courseId);

			if (course == null) {
				System.out.println("Invalid Course ID");
				return;
			}
			System.out.println(course);
			courseService.enrollStudent(courseId, student);

			studentService.enrollToCourse(studentId, course);
			System.out.println("Student with ID: " + studentId + " enrolled successfully to " + courseId);

		}
		//case 6
    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }
		//case 5
    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
			studentService.showSummary();
    }
		//case 3
    private static void gradeStudent( StudentService studentService, Scanner scanner )
		{

			System.out.println( "Enter student ID: " );
			String studentId = scanner.next();
			//returning Student Object
			Student currentStudent = studentService.findStudent(studentId);
			if(currentStudent== null){
				System.out.println("Student ID not found");
			} else {
				// array of enrolled course
				Map<String, Course> enrolledCourses = currentStudent.getEnrolledCourses();
				if (enrolledCourses.size() == 0) {
					// if no course enrolled
					System.out.println("Please enroll to a course before grading");
				} else { // grade

					//display course enrolled
					for (String i : currentStudent.enrolledCourse.keySet()) {
						System.out.println(currentStudent.enrolledCourse.get(i));
					}

					//Get input from user to give you the Course ID
					System.out.println("Enter Course code to be grade: ");
					String courseId = scanner.next();

					if (enrolledCourses.containsKey(courseId)) {

						System.out.println("Enter grade to the course: ");
						double grade = scanner.nextDouble();

						currentStudent.setGrade(courseId, grade);

						System.out.println("grade inputted");
					} else {
						System.out.println("Course ID is not in your enrolled course");
					}
				}  // end of else on whether course has been graded
			} // end of else if current student exist
		}  // end of case 3


		//case 2
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
		//case 1
    private static void registerStudent( StudentService studentService, Scanner scanner ) throws ParseException {
       Student student = PrinterHelper.createStudentMenu( scanner );
       studentService.subscribeStudent( student );
    }
		//case 7
    private static void showPassedCourses(StudentService studentService, Scanner scanner )
		{
			System.out.println( "Enter student ID: " );
			String studentId = scanner.next();

			//returning Student Object
			Student student = studentService.findStudent(studentId);
			if(student == null)
			{
				System.out.println("Student ID is not found ");
			}else {
				System.out.println("You have passed the following courses ");
				student.findPassedCourses();
			}
		}
}
