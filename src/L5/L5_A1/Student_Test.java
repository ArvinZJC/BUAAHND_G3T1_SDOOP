//2018.10.18, program that creates and manipulates 5 Student objects

package L5.L5_A1;

import java.util.Arrays;

public class Student_Test
{
	public static void main( String[] args )
	{
		Student student1 = new Student( "1001", "Tom", 15, 62.0, 56.0, 78.5 ); //create a Student object and assign it to "student1"
		Student student2 = new Student( "1021", "Cathy", 16, 89.5, 73.0, 59.0 ); //create a Student object and assign it to "student2"
		Student student3 = new Student( "1033", "Bom", 16, 76.0, 69.0, 69.0 ); //create a Student object and assign it to "student3"
		Student student4 = new Student( "1029", "John", 15, 62.0, 77.5, 73.5 ); //create a Student object and assign it to "student4"
		Student student5 = new Student( "1012", "Kimi", 17, 50.0, 92.5, 91.0 ); //create a Student object and assign it to "student5"
		Student[] students = { student1, student2, student3, student4, student5 };
		
		Arrays.sort( students );
		
		System.out.printf( "%-6s%-7s%-5s%-8s%-6s%-9s%s\n", "ID", "Name", "Age", "English", "Math", "Chinese", "Total Score" );
		
		for( Student student : students )
			System.out.println( student );
	} //end main
} //end class Student_Test