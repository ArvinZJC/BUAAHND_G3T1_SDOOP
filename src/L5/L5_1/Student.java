// class Student that contains a student's name and score with built-in generic interface Comparable used

package L5.L5_1;

public class Student implements Comparable<Student>
{
	private String name;
	private double score;
	
	Student(String name, double score)
	{
		this.name = name;
		this.score = score;
	} // end constructor Student
	
	/**
	 * Help to sort Student objects in ascending order by the score.
	 */
	@Override
	public int compareTo(Student student)
	{
		return Double.compare(this.score, student.score); // return 0 (if they are numerically equal) or a value less than 0 (if the 1st one is numerically less than the 2nd one) or a value greater than 0 (if the 1st one is numerically greater than the 2nd one)
	} // end method compareTo
	
	/**
	 * Print the information contained in a Student object in the specified format.
	 */
	@Override
	public String toString()
	{
		return String.format("Name: %s  Score: %.1f", name, score);
	} // end method toString
} // end class Student