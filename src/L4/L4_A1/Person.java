// Assignment 1, class Person that contains information of a person's name, gender, and telephone number with all the features of class Birthday

package L4.L4_A1;

public class Person extends Birthday
{
	private String name, gender, tel;
	
	Person(String name, int gender, String tel, int year, int month, int day)
	{
		super(year, month, day);
		setName(name); // call the specified method to set the person's name
		setGender(gender); // call the specified method to set the person's gender
		setTel(tel); // call the specified method to set the person's telephone number
	} // end constructor Person
	
	/**
	 * Set the person's name.
	 * @param name the person's name to be set
	 */
	private void setName(String name)
	{
		this.name = name;
	} // end method setName
	
	/**
	 * Set the person's gender.
	 * @param gender the person's gender to be set
	 */
	private void setGender(int gender)
	{
		switch (gender)
		{
		case 1:
			this.gender = "Male";
			break;
			
		case 2:
			this.gender = "Female";
			break;
			
		default:
			this.gender = "(Error! Illegal input.)";
		} // end switch-case
	} // end method setGender
	
	/**
	 * Set the person's telephone number.
	 * @param tel the person's telephone number to be set
	 */
	private void setTel(String tel)
	{
		this.tel = tel;
	} // end method setTel
	
	/**
	 * Get the person's name.
	 * @return the person's name
	 */
	protected String getName()
	{
		return name;
	} // end method getName
	
	/**
	 * Get the person's gender.
	 * @return the person's gender
	 */
	protected String getGender()
	{
		return gender;
	} // end method getGender
	
	/**
	 * Get the person's telephone number.
	 * @return the person's telephone number
	 */
	protected String getTel()
	{
		return tel;
	} // end method getTel
} // end class Person