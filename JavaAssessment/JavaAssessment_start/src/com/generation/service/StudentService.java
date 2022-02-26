package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentService
{
	private int grade;

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

	public void showSummary()
	{
		//TODO implement

		for (String i : students.keySet()) {
			System.out.println(students.get(i));
		}

	}

	public void enrollToCourse( String studentId, Course course )
	{
		// If student exists
		if ( students.containsKey( studentId ) )
		{
			// Retrieve an object (student) instance by studentId key from students hashmap (studentId, student object)
			Student currentStudent = students.get(studentId);
			currentStudent.enrollToCourse( course );
		}
	}


}
