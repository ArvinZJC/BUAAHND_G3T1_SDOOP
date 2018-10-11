//2018.10.10, Assignment 1, class Birthday that contains information of the birth year, month, and day

package L4.L4_A1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Birthday
{
	private int year, month, day, currentYear, currentMonth, currentDay;
	
	Birthday( int year, int month, int day )
	{
		currentYear = getCurrentYear(); //call the specified method to get the current year used to simply validate the birth year
		currentMonth = getCurrentMonth(); //call the specified method to get the current year used to simply validate the birth month
		currentDay = getCurrentDay(); //call the specified method to get the current year used to simply validate the birth day
		setYear( year ); //call the specified method to set the birth year
		setMonth( month ); //call the specified method to set the birth month
		setDay( day ); //call the specified method to set the birth day
	} //end constructor Birthday

	/**
	 * Set the birth year.
	 * @param year the birth year to be set
	 */
	private void setYear( int year )
	{		
		if( year >= 1 && year <= currentYear )
			this.year = year;
		else
			this.year = -1;
	} //end method setYear

	/**
	 * Set the birth month.
	 * @param month the birth month to be set
	 */
	private void setMonth( int month )
	{
		if( month >= 1 && month <= 12 )
		{
			if( year == currentYear && month > currentMonth )
				this.month = -1;
			else
				this.month = month;
		}
		else
			this.month = -1;
	} //end method setMonth
	
	/**
	 * Set the birth day.
	 * @param day the birth day to be set
	 */
	private void setDay( int day )
	{
		switch( month )
		{
		//months with 31 days
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if( day >= 1 && day <= 31 )
			{
				if( year == currentYear && month == currentMonth && day > currentDay )
					this.day = -1;
				else
					this.day = day;
			}
			else
				this.day = -1;
			break;
			
		case 2:
			//execute if it is a leap year with February having 29 days
			if( ( year % 4 == 0 && year % 100 != 0 ) || year % 400 == 0 )
			{
				if( day >= 1 && day <= 29 )
				{
					if( year == currentYear && month == currentMonth && day > currentDay )
						this.day = -1;
					else
						this.day = day;
				}
				else
					this.day = -1;
			}
			//execute if it is not a leap year with February having 28 days
			else
			{
				if( day >= 1 && day <= 28 )
				{
					if( year == currentYear && month == currentMonth && day > currentDay )
						this.day = -1;
					else
						this.day = day;
				}
				else
					this.day = -1;
			} //end if...else
			break;
			
		//months with 30 days
		case 4:
		case 6:
		case 9:
		case 11:
			if( day >= 1 && day <= 30 )
			{
				if( year == currentYear && month == currentMonth && day > currentDay )
					this.day = -1;
				else
					this.day = day;
			}
			else
				this.day = -1;
			break;
			
		default:
			if( day >= 1 && day <= 31 )
				this.day = day;
			else
				this.day = -1;
		} //end switch-case
		this.day = day;
	} //end method setDay

	/**
	 * Get the current year.
	 * @return the current year
	 */
	private int getCurrentYear()
	{
		SimpleDateFormat yearFormat = new SimpleDateFormat( "yyyy" );
        Date currentDate = new Date();
        
        try
        {
        	return Integer.parseInt( yearFormat.format( currentDate ) );
        }
        catch( NumberFormatException e )
        {
        	e.printStackTrace();
        	return -1;
        } //end try...catch
	} //end method getCurrentYear

	/**
	 * Get the current month.
	 * @return the current month
	 */
	private int getCurrentMonth()
	{
		SimpleDateFormat monthFormat = new SimpleDateFormat( "MM" );
        Date currentDate = new Date();
        
        try
        {
        	return Integer.parseInt( monthFormat.format( currentDate ) );
        }
        catch( NumberFormatException e )
        {
        	e.printStackTrace();
        	return -1;
        } //end try...catch
	} //end method getCurrentMonth
	
	/**
	 * Get the current day.
	 * @return the current day
	 */
	private int getCurrentDay()
	{
		SimpleDateFormat dayFormat = new SimpleDateFormat( "dd" );
        Date currentDate = new Date();
        
        try
        {
        	return Integer.parseInt( dayFormat.format( currentDate ) );
        }
        catch( NumberFormatException e )
        {
        	e.printStackTrace();
        	return -1;
        } //end try...catch
	} //end method getCurrentDay

	/**
	 * Get the birthday (D/M/Y).
	 * @return the birthday (D/M/Y)
	 */
	protected String getBirthday()
	{
		if( year == -1 || month == -1 || day == -1 )
			return "(Error! Illegal input or exception thrown during the process of getting the current date.)";
		else
			return day + "." + month + "." + year;
	} //end method getBirthday
} //end class Birthday