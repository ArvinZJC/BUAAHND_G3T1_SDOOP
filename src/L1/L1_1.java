// program that compares different Integer objects to indicate a feature

package L1;

public class L1_1
{
	public static void main(String[] args)
	{
		Integer a = -128, b = -128, c = 128, d = 128;
		
		// true when the value is in the range of -128 to 127
		System.out.printf("a == b: %b\n", a == b);
		System.out.printf("c == d: %b\n", c == d);
	} // end main
} // end class L1_1