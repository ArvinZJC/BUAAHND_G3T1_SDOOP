//2018.10.18, class Student that contains a student's ID, name, age and scores with built-in generic interface Comparable used

package L5.L5_A1;

public class Student implements Comparable<Student>
{
	private String id, name;
	private int age;
	private double english, math, chinese, totalScore;
	
	Student( String id, String name, int age, double english, double math, double chinese )
	{
		this.id = id;
		this.name = name;
		this.age = age;
		this.english = english;
		this.math = math;
		this.chinese = chinese;
		totalScore = english + math + chinese;
	} //end constructor Student
	
	//override method compareTo to help to sort Student objects in descending order by the total value of "english", "math", and "chinese"
	@Override
	public int compareTo( Student student )
	{
		return -Double.compare( this.totalScore, student.totalScore ); //return 0 (if they are numerically equal) or a value less than 0 (if the 1st one is numerically GREATER than the 2nd one) or a value greater than 0 (if the 1st one is numerically LESS than the 2nd one)
	} //end method compareTo
	
	//override method toString to print the information contained in a Student object in the specified format
	@Override
	public String toString()
	{
		return String.format( "%-6s%-7s%-5d%-8.1f%-6.1f%-9.1f%.1f", id, name, age, english, math, chinese, totalScore );
	} //end method toString
} //end class Student