package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class StudentService
{		//attributes
    private final Map<String, Student> students = new HashMap<>();

    public void subscribeStudent( Student student )
    {
        students.put( student.getId(), student );
    }


    public Student findStudent( String studentId )
		{
			if ( students.containsKey( studentId ) )
			{
				return students.get( studentId );
			}
			return null;
		}

	public void showSummary() {
		if (students.size() == 0) {
			System.out.println("No students yet, enroll some students");
		} else {  // show students
			for (String i : students.keySet()) {
				System.out.println(students.get(i));
				//instance object of current student
				Student currentStudent = students.get(i);
				// Access enrolled course and enrolled course grade hashmap
				Map<String, Course> enrolledCourses = currentStudent.getEnrolledCourses();
				Map<String, Double> enrolledCoursesGrade = currentStudent.enrolledCourseGrade;
				// if array is empty, no course print
				if (enrolledCourses.size() == 0) {
					System.out.println("No Enrolled Course yet, please enroll");
				} else {
					 //print enrolled course
					System.out.println("Enrolled Course");
					for (String j : enrolledCourses.keySet())
						System.out.println(enrolledCourses.get(j) +" Grade : " + enrolledCoursesGrade.get(j));
					}		// end of print enrolled course for loop
				}    //end of student for loop
			}  // end of else students

	}// end of show summary method



    public void enrollToCourse( String studentId, Course course )
		{
			// If student exists
			if ( students.containsKey( studentId ) )
			{
				// Retrieve an object (student) instance by studentId key from students hashmap (studentId, student object)
				Student currentStudent = students.get(studentId);
				currentStudent.enrollToCourse( course );
			}
		}  // end of method
} // end of class
