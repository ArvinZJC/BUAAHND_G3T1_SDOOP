/*
 * 2018.09.20, an extension of Assignment 1, program that calculates the sum and average of numbers entered by the user
 * my 2nd method (using regular expressions, method Integer.parseInt() and method Double.parseDouble())
 */

package L2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class L2_A1_2
{
	private static ArrayList< String > integers = new ArrayList< String >(); //an array list used for storing integers entered by the user
	private static ArrayList< String > decimals = new ArrayList< String >(); //an array list used for storing decimals entered by the user
	private static int counter; //store how many numbers entered
	
	public static void main( String[] args )
	{
		Scanner input = new Scanner( System.in );
		String userInput;
		
		System.out.println( "Enter numbers or type \"end\" to terminate input:" );
		
		//terminate input when "end" is typed by the user
		while( ! input.hasNext( "end" ) )
		{
			userInput = input.next();
			
			//call the specified method to check the input from the user
			switch( CheckInput( userInput ) )
			{
			case 0:
				integers.add( userInput );
				break;
				
			case 1:
				decimals.add( userInput );
				break;
				
			case 2:
				System.out.print( "  Error! \"" + userInput + "\" is an illegal input. Please enter a number instead or type \"end\" to terminate input: " );
			} //end switch-case
		} //end while
		
		System.out.println();
		
		integers.trimToSize();
		decimals.trimToSize();
		counter = integers.size() + decimals.size();
		OutputResult(); //call the specified method to output the result after processing the numbers entered
		
		input.close();
	} //end main
	
	/**
	 * Check the input from the user.
	 * @param userInput the input to be checked
	 * @return 0 (if it is an integer) or 1 (if it is a decimal) or 2 (if it is neither an integer nor a decimal)
	 */
	private static int CheckInput( String userInput )
	{
		/*
		 * match a number of type int
		 * the input can start with spaces and can also end up with spaces
		 */
		Pattern integerPattern = Pattern.compile( "\\s*-?\\d+\\s*" );
		Matcher integerMatcher = integerPattern.matcher( userInput );
		
		if( integerMatcher.matches() )
			return 0;
		else
		{
			/*
			 * match a number of type double
			 * the input can start with spaces and can also end up with spaces
			 */
			Pattern decimalPattern = Pattern.compile( "\\s*-?\\d+\\.\\d+\\s*" );
			Matcher decimalMatcher = decimalPattern.matcher( userInput );
			
			if( decimalMatcher.matches() )
				return 1;
			else
				return 2;
		} //end if...else
	} //end method CheckInput
	
	/**
	 * Output the result after processing the numbers entered.
	 */
	private static void OutputResult()
	{
		int integer;
		int counterForNumbersEachLine = 0; //store how many numbers have been printed in a line
		double decimal, sum = 0, average;
		
		if( counter != 0 )
		{
			System.out.println( "Number(s) entered:" );
			System.out.print( "  Type int:\n" + "  " );
			
			if( integers.size() != 0 )
			{				
				for( String item : integers )
				{
					integer = Integer.parseInt( item );
					sum += integer;
					
					System.out.print( "  " + integer );
					
					//make sure that there are no more than 5 numbers each line
					if( ++counterForNumbersEachLine % 5 == 0 )
						System.out.print( "\n  " );
				} //end for
			}
			else
				System.out.print( "  (None.)" );
			
			counterForNumbersEachLine = 0; //initialise the variable "counterForNumbersEachLine" for neatly printing numbers entered of type double
			
			System.out.print( "\n  Type double:\n" + "  " );
			
			if( decimals.size() != 0 )
			{
				for( String item : decimals )
				{
					decimal = Double.parseDouble( item );
					sum += decimal;
					
					System.out.print( "  " + decimal );
					
					//make sure that there are no more than 5 numbers each line
					if( ++counterForNumbersEachLine % 5 == 0 )
						System.out.print( "\n  " );
				} //end for
			}
			else
				System.out.print( "  (None.)" );
			
			average = sum / counter;
			
			System.out.printf( "\nThere is/are %d number(s) in all.\n", counter );
			System.out.println( "Sum: " + sum );
			System.out.printf( "Average (2 decimal places kept): %.2f\n", average );
		}
		else
			System.out.println( "Error! No number entered!" );
	} //end method OutputResult
} //end class L2_A1_2