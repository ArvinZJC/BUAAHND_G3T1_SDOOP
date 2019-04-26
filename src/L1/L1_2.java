// program that uses StringBuffer and StringBuilder objects

package L1;

public class L1_2
{
	public static void main(String[] args)
	{
		StringBuffer test1 = new StringBuffer("Practise StringBuffer");
		StringBuilder test2 = new StringBuilder("Practise ");
		
		test2.append("StringBuilder");
		System.out.println("Value of the StringBuffer object: " + test1.toString());
		System.out.println("Value of the StringBilder object: " + test2.toString());
	} // end main
} // end class L1_2