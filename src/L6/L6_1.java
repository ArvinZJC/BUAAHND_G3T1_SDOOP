// program that uses interface Map to set and get the students' IDs and names

package L6;

import java.util.*;

public class L6_1
{
	public static void main(String[] args)
	{
		Map<String, String> students = new HashMap<>();
		Iterator<String> eachItem;
		
		students.put("01", "Amy");
		students.put("02", "Tom");
		students.put("03", "Arvin");
		students.put("04", "Mary");
		Set<String> ids = students.keySet(); // get the students' IDs as a set
		Collection<String> names = students.values(); // get the students' names as a collection
		
		eachItem = ids.iterator();
		System.out.println( "ID:" );
		
		// loop to print each ID
		while (eachItem.hasNext())
			System.out.println(eachItem.next());
		
		eachItem = names.iterator();
		System.out.println("\nName:");
		
		// loop to print each name
		while (eachItem.hasNext())
			System.out.println(eachItem.next());
	} // end main
} // end class L6_1