// program that finds the specified key in a list with a programmer-declared method which consumes more resource compared to binary search

package L3;

public class L3_2
{
	public static void main(String[] args)
	{
		int startIndex1 = 0, startIndex2 = 0, result1, result2;
		int[] list = {23, 1, 46, 102, -98, -7, -7, 45, 0, -7, 79, 66};
		
		System.out.println("The list:");
		
		for (int number : list)
			System.out.print("  " + number);
		
		System.out.println("\nThe key: 2\n  Index(es) in the list:");
		
		// loop to try to find all the indexes of the value 2 in the list
		while (startIndex1 <= list.length - 1)
		{
			result1 = Search(list, 2, startIndex1); // call the specified method to find 2 in the array named list
			
			if (result1 == -1)
			{
				System.out.println("    (Not found any other in the list.)");
				
				break;
			}
			else
			{
				System.out.println("    " + result1);
				
				startIndex1 = result1 + 1;
			} // end if...else
		} // end while
		
		System.out.println("\nThe key: -7\n  Index(es) in the list:");
		
		// loop to try to find all the indexes of the value 2 in the list
		while (startIndex2 <= list.length - 1)
		{
			result2 = Search(list, -7, startIndex2); // call the specified method to find -7 in the array named list
			
			if (result2 == -1)
			{
				System.out.println("    (Not found any other in the list.)");
				
				break;
			}
			else
			{
				System.out.println("    " + result2);
				
				startIndex2 = result2 + 1;
			} // end if...else
		} // end while
	} // end main
	
	/**
	 * Find the specified key in a list.
	 * @param list the key to be found in the array named list
	 * @param key the integer to be found
	 * @param startIndex the start index for searching
	 * @return the index of the key in the array named list or -1 (if the key is not found) or -2 (if the start index is larger than the largest index)
	 */
	private static int Search(int[] list, int key, int startIndex)
	{
		if (startIndex > list.length - 1)
			return -2;
		else
		{
			for (int count = startIndex; count < list.length; count++)
			{
				if (list[count] == key)
					return count;
			} // end for
			
			return -1;
		} // end if...else
	} // end method Search
} // end class L3_2