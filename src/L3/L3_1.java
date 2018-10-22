//2018.09.26, program that uses binary search in a programmer-declared method to find the specified key in a list

package L3;

import java.util.Arrays;

public class L3_1
{
	public static void main( String[] args )
	{
		int[] list = { 23, 1, 46, 102, -98, -1, -7, 45, 0, 79, 66 };
		
		Arrays.sort( list ); //sort the array named list in ascending order
		
		System.out.println( "The list after sorting in ascending order:" );
		
		for( int number : list )
			System.out.print( "  " + number );
		
		System.out.println( "\nThe key: 2" );
		
		OutputResult( BinarySearch( list, 2 ) ); //first, call the specified method to use binary search to find 2 in the array named list; then, call the specified method to output the searching result
		
		System.out.println( "The key: -7" );
		
		OutputResult( BinarySearch( list, -7 ) ); //first, call the specified method to use binary search to find -7 in the array named list; then, call the specified method to output the searching result
	} //end main
	
	/**
	 * Use binary search to find the specified key in a list.
	 * (ATTENTION: The integers in the list must be in ascending order and not be repeating.)
	 * @param list the key to be found in the array named list
	 * @param key the integer to be found
	 * @return the index of the key in the array named list or -1 (if the key is not found)
	 */
	private static int BinarySearch( int[] list, int key )
	{
		int low = 0, high = list.length - 1, middle;
		
		//all the integers in the list have been searched when high < low
		while( high >= low )
		{
			middle = ( low + high ) / 2;
			
			if( key < list[ middle ] )
				high = middle - 1; //perhaps key ∈ [low, middle - 1]
			else if( key > list[ middle ] )
				low = middle + 1; //perhaps key ∈ [middle + 1, high]
			else
				return middle; //the key is found and the index value is equal to the value of the variable "middle"
		} //end while
		
		return -1; //the key is not found
	} //end method BinarySearch
	
	/**
	 * Output the searching result.
	 * @param index the value received after using binary search
	 */
	private static void OutputResult( int index )
	{
		if( index == -1 )
			System.out.println( "  Not found in the list." );
		else
			System.out.println( "  Index in the list: " + index );
	} //end method OutputResult
} //end class L3_1