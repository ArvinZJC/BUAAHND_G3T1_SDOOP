// class that gets and sets a point

package L7.L7_1;

// a generic class
public class Point<T extends Number>
{
	private T x, y;
	
	Point(T x, T y)
	{
		this.x = x;
		this.y = y;
	} // end constructor Point
	
	public T getX()
	{
		return x;
	} // end method getX
	
	public T getY()
	{
		return y;
	} // end method getY
} // end class Point