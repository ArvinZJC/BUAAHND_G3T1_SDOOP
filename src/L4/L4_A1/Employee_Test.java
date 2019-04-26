// Assignment 1, program that creates and manipulates 3 Employee objects

package L4.L4_A1;

import java.util.Calendar;

public class Employee_Test
{	
	public static void main(String[] args)
	{
		Calendar calendar = Calendar.getInstance(); // this is a different way to get the date compared to Birthday.java
		
		Employee employee1 = new Employee("Sample001", "Arvin", 1, "07011234567", 1992, 3, 24, 3000.00); //create an Employee object and assign it to "employee1"
		/*
		 * create an Employee object and assign it to "employee2";
		 * November does not have the 31st day
		 */
		Employee employee2 = new Employee("Sample002", "Mary", 2, "07010987654", 1993, 11, 31, 2700.00);
		/*
		 * create an Employee object and assign it to "employee3";
		 * a future date as the birthday is illegal
		 */
		Employee employee3 = new Employee("Sample003", "James", 0, "07017777777", calendar.get(Calendar.YEAR) + 1, 9, 20, 2200.00);
		
		PrintEmployeeInfo(employee1); // call the specified method to print information of the 1st employee
		System.out.println();
		PrintEmployeeInfo(employee2); // call the specified method to print information of the 2nd employee
		System.out.println();
		PrintEmployeeInfo(employee3); // call the specified method to print information of the 3rd employee
	} // end main
	
	/**
	 * Print information of the specified employee.
	 * @param employSelected an Employee object represent the specified employee
	 */
	private static void PrintEmployeeInfo(Employee employSelected)
	{
		System.out.println("ID: " + employSelected.getId()); // call the specified method in class Employee to get the employee's ID
		System.out.println("Name: " + employSelected.getName()); // call the specified method in class Person to get the employee's name
		System.out.println("Gender: " + employSelected.getGender()); // call the specified method in class Person to get the employee's gender
		System.out.println("Tel: " + employSelected.getTel()); // call the specified method in class Person to get the employee's telephone number
		System.out.println("Birthday (D/M/Y): " + employSelected.getBirthday()); // call the specified method in class Birthday to get the employee's birthday
		System.out.printf("Salary: %.2f\n", employSelected.getSalary()); // call the specified method in class Employee to get the employee's salary
	} // end method PrintEmployeeInfo
} // end class L4_1