package com.generation.utils;

import com.generation.model.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PrinterHelper
{
	public static final String FORMAT ="mm/dd/yyyy";

	public static void showMainMenu(){
		System.out.println( "|-------------------------------|" );
		System.out.println( "| Welcome to StudentGen         |" );
		System.out.println( "|-------------------------------|" );
		System.out.println( "| Select 1 option:              |" );
		System.out.println( "| . 1 Register Student          |" );
		System.out.println( "| . 2 Find Student              |" );
		System.out.println( "| . 3 Grade Student             |" );
		System.out.println( "| . 4 Enroll Student to Course  |" );
		System.out.println( "| . 5 Show Students Summary     |" );
		System.out.println( "| . 6 Show Courses Summary      |" );
		System.out.println( "| . 7 Show Pass Courses         |" );
		System.out.println( "| . 8 Exit                      |" );
		System.out.println( "|-------------------------------|" );
	}

	public static Student createStudentMenu(Scanner scanner )
		throws ParseException {
		System.out.println("|-------------------------------------|");
		System.out.println("| . 1 Register Student                |");
		System.out.println("|-------------------------------------|");
		System.out.println("| Enter student name:                 |");
		String name = scanner.next();
		System.out.println("| Enter student ID:                   |");
		String id = scanner.next();
		System.out.println("| Enter student email:                |");
		String email = scanner.next();


		boolean isWrong = true;
		Scanner sc = new Scanner(System.in);
		String birthDate;


		do {
			System.out.println("Please enter date in dd/mm/yyyy format");
			birthDate = sc.next();
			if (checkDate(birthDate)) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				dateFormat.setLenient(false);
				try {
					dateFormat.parse(birthDate);
					isWrong = false;
				} catch (Exception e) {
					System.out.println("Enter the date in the correct dd/mm/yyyy format");
				}
			}
		} while (isWrong);


		// create new student object
		Student student = new Student(id, name, email, birthDate);
		System.out.println("Student Successfully Registered! ");
		System.out.println(student);
		return student;
	}



	static boolean checkDate(String date) {
		String pattern = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
		boolean flag = false;
		if (date.matches(pattern)) {
			flag = true;
		}
		return flag;
	}

}
