package com.generation.model;

import com.generation.service.CourseService;
import com.generation.service.StudentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student
	extends Person
	implements Evaluation
{
	//attributes
	private double average;
	private double grade;
	//private final List<Course> courses = new ArrayList<>();     //empty array for courses

	private final Map<String,Course> approvedCourses = new HashMap<>();
	//empty HashMap for approvedCourses

	//NEW - key is course ID, value is enrolled course object
	public final Map<String,Course> enrolledCourse = new HashMap<>();

	//key is the course ID, value is the grade
	public final Map<String, Double> enrolledCourseGrade = new HashMap<>();

	//constructor
	public Student( String id, String name, String email, Date birthDate )
	{
		super( id, name, email, birthDate );
	}

	public void enrollToCourse( Course course )
	{
		double grade = 0.0;
		//TODO implement this method
		//add the course to the enrolledCourse Hashmap
		//add a default grade (0) into the enrolledCourseGrade HashMap
		enrolledCourse.put(course.getCode(), course);
		enrolledCourseGrade.put(course.getCode(), grade);
	}

	//setGrade
	public void setGrade(String courseId, double grade)
	{
		//check the course code in the enrolledCourseGrade

		enrolledCourseGrade.put(courseId, grade);
		System.out.println("Print grade for:"  + courseId + " Grade: " + enrolledCourseGrade.get(courseId));
	}


	//get the individual course grade from enrolledCourseGrade HashMap of each student instance
	public double getGrade (String courseId)
	{
		grade = enrolledCourseGrade.get(courseId);
		return grade;

	}


	public void showAllGrades() {
		for (String courseId : enrolledCourseGrade.keySet()) {
			System.out.println("Course ID: " + courseId);

			double courseGrade = enrolledCourseGrade.get(courseId); // Get value
			System.out.println("Course Grade: " + courseGrade);
		}
	}

	public void registerApprovedCourse( Course course )
	{
		approvedCourses.put( course.getCode(), course );
	}

	public boolean isCourseApproved( String courseCode )
	{
		//TODO implement this method
		return true;

	}


	public List<Course> findPassedCourses( Course course )
	{
		//TODO implement this method
		return null;
	}

	public boolean isAttendingCourse( String courseCode )
	{
		//TODO implement this method
		return false;
	}

	@Override
	public double getAverage()
	{

		return average;
	}

	@Override
	public List<Course> getApprovedCourses()
	{
		//TODO implement this method
		return null;
	}

	@Override
	public String toString()
	{
		return "Student {" + super.toString() + "}";
	}
}
