/*
 * Assignment 1, program that calculates the sum and average of numbers entered by the user;
 * my 1st method (using method hasNextInt and method hasNextDouble)
 */

package L2;

import java.util.ArrayList;
import java.util.Scanner;

public class L2_A1_1
{
	private static ArrayList<Integer> integers = new ArrayList<Integer>(); // an array list used for storing integers entered by the user
	private static ArrayList<Double> decimals = new ArrayList<Double>(); // an array list used for storing decimals entered by the user
	private static int count; // store how many numbers entered
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter numbers or type \"end\" to terminate input:");
		
		// terminate input when "end" is typed by the user
		while (!input.hasNext("end"))
		{
			if (input.hasNextInt())
				integers.add(input.nextInt());
			else if (input.hasNextDouble())
				decimals.add(input.nextDouble());
			else
				System.out.print("  Error! \"" + input.next() + "\" is an illegal input. Please enter a number instead or type \"end\" to terminate input: ");
		} // end while
		
		System.out.println();
		integers.trimToSize();
		decimals.trimToSize();
		count = integers.size() + decimals.size();
		OutputResult(); // call the specified method to output the result after processing the numbers entered
		
		input.close(); // close "input" of class Scanner to avoid resource leak or any other problems
	} // end main
		
	/**
	 * Output the result after processing the numbers entered.
	 */
	private static void OutputResult()
	{
		int countForNumbersEachLine = 0; // store how many numbers have been printed in a line
		double sum = 0, average;
		
		if (count != 0)
		{
			System.out.println("Number(s) entered:");
			System.out.print("  Type int:\n" + "  ");
			
			if (integers.size() != 0)
			{				
				for (int integer : integers)
				{
					sum += integer;
					
					System.out.print("  " + integer);
					
					// make sure that there are no more than 5 numbers each line
					if (++countForNumbersEachLine % 5 == 0)
						System.out.print("\n  ");
				} // end for
			}
			else
				System.out.print("  (None.)");
			
			countForNumbersEachLine = 0; // initialise the variable "countForNumbersEachLine" for neatly printing numbers entered of type double
			
			System.out.print("\n  Type double:\n" + "  ");
			
			if (decimals.size() != 0)
			{
				for (double decimal : decimals)
				{
					sum += decimal;
					
					System.out.print("  " + decimal);
					
					// make sure that there are no more than 5 numbers each line
					if (++countForNumbersEachLine % 5 == 0)
						System.out.print("\n  ");
				} // end for
			}
			else
				System.out.print("  (None.)");
			
			average = sum / count;
			
			System.out.printf("\nThere is/are %d number(s) in all.\n", count);
			System.out.println("Sum: " + sum);
			System.out.printf("Average (2 decimal places kept): %.2f\n", average);
		}
		else
			System.out.println("Error! No number entered!");
	} // end method OutputResult
} // end class L2_A1_1