//2018.10.31, program that creates and manipulates 4 Point objects

package L7.L7_1;

public class Point_Test
{
	public static void main( String[] args ) throws Exception
	{
		Point<Integer> point1 = new Point<Integer>( 3, 3 ); //create a Point object and assign it to "point1"
		Point<Float> point2 = new Point<Float>( 4.2f, 4.3f ); //create a Point object and assign it to "point2"
		Point<Double> point3 = new Point<Double>( 5.3, 1.3 ); //create a Point object and assign it to "point3"
		Point<Long> point4 = new Point<Long>( 98l, 102l ); //create a Point object and assign it to "point4"
		
		//call the specified methods in class Point to get the point (x,y)
		System.out.println( "Point 1: (" + point1.getX() + "," + point1.getY() + ")" );
		System.out.println( "Point 2: (" + point2.getX() + "," + point2.getY() + ")" );
		System.out.println( "Point 3: (" + point3.getX() + "," + point3.getY() + ")" );
		System.out.println( "Point 4: (" + point4.getX() + "," + point4.getY() + ")" );
	} //end main
} //end Point_Test