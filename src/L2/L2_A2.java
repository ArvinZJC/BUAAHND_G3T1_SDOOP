//2018.09.20, Assignment 2, program that processes 10 random numbers picked from 1 to 101 (101 excluded) with 2 decimal points

package L2;

public class L2_A2
{
	public static void main( String[] args )
	{
		int counterForBiggerNumbers = 0; //store how many numbers in the 10 random numbers are bigger than the average number
		double sum = 0, average;
		double[] randomNumbers = new double[ 10 ]; //an array storing 10 random numbers picked from 1 to 101 (101 excluded) with 2 decimal points
		
		System.out.println( "10 random numbers picked from 1 to 101 (101 excluded) with 2 decimal points:" );
		
		//loop to store and process 10 random numbers picked from 1 to 101 (101 excluded) with 2 decimal points
		for( int counter = 0; counter < 10; counter++ )
		{
			randomNumbers[ counter ] = 1 + (int)( Math.random() * 100 * 100 ) / 100.0;
			sum += randomNumbers[ counter ];
			
			System.out.printf( "  %.2f", randomNumbers[ counter ] );
		} //end for
		
		average = sum / 10;
		
		System.out.printf( "\nAverage (2 decimal places kept): %.2f\n", average );
		System.out.println( "Number(s) in the 10 random numbers bigger than the average number:" );
		
		//loop to compare each element of the array named randomNumbers with the average number
		for( double randomNumber : randomNumbers )
		{
			//print the element if it is bigger than the average number
			if( randomNumber > average )
			{
				counterForBiggerNumbers++;
				
				System.out.printf( "  %.2f", randomNumber );
			} //end if
		}
		
		//execute if there is no number bigger than the average number
		if( counterForBiggerNumbers == 0 )
			System.out.print( "  (None.)" );
	} //end main
} //end class L2_A2