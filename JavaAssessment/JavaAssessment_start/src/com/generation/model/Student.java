package com.generation.model;

import java.util.HashMap;
import java.util.Map;

public class Student
    extends Person
    implements Evaluation
{
		// attributes
    float PASS_MIN_GRADE = 3.0f;
		private double grade;
		// key is the course ID, value is enrolled course object
		public final Map<String,Course> enrolledCourse = new HashMap<>();
		//key is the course ID, value is the grade
		public final Map<String, Double> enrolledCourseGrade = new HashMap<>();


		// constructor
    public Student(String id, String name, String email, String birthDate )
    {
        super( id, name, email, birthDate );
    }

    public void enrollToCourse( Course course )
    {

			double grade = 0.0;
			//add the course to the enrolledCourse Hashmap
			//add a default grade (0) into the enrolledCourseGrade HashMap
			enrolledCourse.put(course.getCode(), course);
			enrolledCourseGrade.put(course.getCode(), grade);
    }


	// getter and setter Grade

	//get the individual course grade from enrolledCourseGrade HashMap of each student instance
	public double getGrade (String courseId)
	{
		grade = enrolledCourseGrade.get(courseId);
		return grade;

	}

	public void setGrade(String courseId, double grade)
	{
		//check the course code in the enrolledCourseGrade

		enrolledCourseGrade.put(courseId, grade);

	}


    public void findPassedCourses()
		{
			if(enrolledCourseGrade.size()==0)
			{
				System.out.println("No course is graded yet");
			}else {
				// for loop for graded course
				System.out.println("You have passed the following courses : ");
				for (String courseId : enrolledCourseGrade.keySet()) {
					double courseGrade = enrolledCourseGrade.get(courseId);
					if (courseGrade >= PASS_MIN_GRADE) {
						System.out.println("Course ID: " + courseId + ", Course Grade: " + courseGrade);
					}
				}
			}
		}

    public Course findCourseById( String courseId )
    {
        //TODO
       return null;
    }

    @Override
    public Map<String, Course> getEnrolledCourses()
    {
        return this.enrolledCourse;
    }

    @Override
    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }

}
