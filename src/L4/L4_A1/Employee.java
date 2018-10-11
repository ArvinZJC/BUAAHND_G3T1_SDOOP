//2018.10.10, Assignment 1, class Employee that contains information of an employee's ID and salary with all the features of class Person

package L4.L4_A1;

public class Employee extends Person
{
	private String id;
	private double salary;
	
	Employee( String id, String name, int gender, String tel, int year, int month, int day, double salary )
	{
		super( name, gender, tel, year, month, day );
		setId( id ); //call the specified method to set the employee's ID
		setSalary( salary ); //call the specified method to set the employee's salary
	} //end constructor Employee
	
	/**
	 * Set the employee's ID.
	 * @param id the employee's ID to be set
	 */
	private void setId( String id ) { this.id = id; } //end method setId
	
	/**
	 * Set the employee's salary.
	 * @param salary the employee's salary to be set
	 */
	private void setSalary( double salary ) { this.salary = salary; } //end method setSalary
	
	/**
	 * Get the employee's ID.
	 * @return the employee's ID
	 */
	public String getId() { return id; } //end method getId
	
	/**
	 * Get the employee's salary.
	 * @return the employee's salary
	 */
	public double getSalary() { return salary; } //end method getSalary
} //end class Employee