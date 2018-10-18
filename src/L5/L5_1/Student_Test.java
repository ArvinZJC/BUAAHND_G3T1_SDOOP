//2018.10.17, program that creates and manipulates 3 Student objects

package L5.L5_1;

import java.util.Arrays;

public class Student_Test
{
	public static void main( String[] args )
	{
		Student[] students = { new Student( "Bob", 90.0 ), new Student( "Jack", 89.0 ), new Student( "Mary", 99.0 ) }; //create 3 Student objects
		
		Arrays.sort( students );
		
		for( Student student : students )
			System.out.println( student );
	} //end main
} //end class Student_Test