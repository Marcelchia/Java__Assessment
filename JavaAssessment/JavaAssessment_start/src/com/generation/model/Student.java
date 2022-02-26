package com.generation.model;

import java.util.Collections;
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
		// key is the course ID, value is passed course object
		public final Map<String,Course> passedCourse = new HashMap<>();


		// constructor
    public Student(String id, String name, String email, String birthDate )
    {
        super( id, name, email, birthDate );
    }

    public void enrollToCourse( Course course )
    {
			//add a default grade (0) into the enrolledCourseGrade HashMap
			double grade = 0.0;
			//add the course to the enrolledCourse Hashmap
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
					// if above enrolled course is passed, add to passedCourse HashMap
					if (courseGrade >= PASS_MIN_GRADE) {
						passedCourse.put(courseId,enrolledCourse.get(courseId));
						}
					} // end of for loop
						//for loop to print
				for(String key : passedCourse.keySet()){
					Course course = passedCourse.get(key);
					System.out.println(course);
				} // end of for loop
			} // end of else
		} // end of method



    public Course findCourseById( String courseId )
    {
			return enrolledCourse.get(courseId);
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
