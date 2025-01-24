/**
 * Package name albumData
 */
package albumData;

import java.util.Calendar;

/**
 * This class represents date of the album.
 * It also checks if it is valid and also implements a compareTo function.
 * Contains tear, month, and day.
 * 
 * @author 
 */
public class Date implements Comparable<Date>
{
	/** Refers to the year in a date*/
	private int year;
	/** Refers to the month in a date*/
	private int month;
	/** Refers to the day in a date*/
	private int day;

	/**
	 * This constructor creates a Date object with the input date.
	 * @param date date in a string format
 	 */
	public Date(String date)
	{
		String[] dateComponents = date.split("/");
		this.year = Integer.parseInt(dateComponents[2]);
		this.month = Integer.parseInt(dateComponents[0]);
		this.day = Integer.parseInt(dateComponents[1]);
	}

	/**
	 * 	This constructor creates a Date object with the current system date.
	 */
	public Date()
	{
		Calendar today = Calendar.getInstance();
		
		this.year = today.get(today.YEAR);
		this.month = today.get(today.MONTH + 1);
		this.day = today.get(today.DATE);
	}

	/**
	 * Checks if the date is within the specified constraints.
	 * @return true if the date is valid.
	 */
	public boolean isValid()
	{
		if (this.year < EARLIEST_YEAR || this.year > LATEST_YEAR)
		{
			return false;
		}
		
		if (this.month < EARLIEST_MONTH || this.month > LATEST_MONTH)
		{
			return false;
		}
		
		if (this.day < EARLIEST_DATE)
		{
			return false;
		}

		String inputDay = String.valueOf(this.day);
		String inputMonth = String.valueOf(this.month);
		String inputYear = String.valueOf(this.year);
		Date inputDate = new Date(inputMonth + "/" + inputDay + "/" + inputYear);
		Date todaysDate = new Date();

		if (todaysDate.compareTo(inputDate) == -1)
		{
			return false;
		}
		
		boolean isLeapYear = checkIfLeapYear(this.year);
		int numberOfFebruaryDays = 28;
		
		if (isLeapYear)
		{
			numberOfFebruaryDays = 29;
		}
		
		int numberOfDaysInLargerMonths = 31;
		int numberOfDaysInSmallerMonths = 30;
		
		if (this.month == FEBRUARY)
			if (day > numberOfFebruaryDays)
				return false;

		if (this.month == JANUARY || this.month == MARCH || this.month == MAY ||
				this.month == JULY || this.month == AUGUST || this.month == OCTOBER ||
				this.month == DECEMBER)
		{
			if (this.day > numberOfDaysInLargerMonths)
				return false;
		}
			
		if (this.month == APRIL || this.month == JUNE || this.month == SEPTEMBER
				|| this.month == NOVEMBER)
		{
			if (this.day > numberOfDaysInSmallerMonths)
				return false;
		}
		
		return true;
	}

	/**
	 *  Compares the two input dates and mentions whether they are equal or not.
	 * @param date date object to compare with.
	 * @return integer 0 if the dates are equal. 1, if the first date object is more recent than argument date object. -1, elsewhere.
	 */
	@Override
	public int compareTo(Date date)
	{
		int yearToCompare = date.year;
		int monthToCompare = date.month;
		int dayToCompare = date.day;

		int differenceBetweenYears = this.year - yearToCompare;
		int differenceBetweenMonths = this.month - monthToCompare;
		int differenceBetweenDays = this.day - dayToCompare;

		if (differenceBetweenYears > 0)
			return 1;
		else if (differenceBetweenYears < 0)
			return -1;

		if (differenceBetweenMonths > 0)
			return 1;
		else if (differenceBetweenMonths < 0)
			return -1;

		if (differenceBetweenDays > 0)
			return 1;
		else if (differenceBetweenDays < 0)
			return -1;

		return 0;
	}

	/**
	 * Returns a string representation of a date object in "mm/dd/yyyy"
	 * @return Date represented as "mm/dd/yyyy" as a string.
	 */
	@Override
	public String toString()
	{
		String dayOfDate = Integer.toString(this.day);
		String monthOfDate = Integer.toString(this.month);
		String yearOfDate = Integer.toString(this.year);

		return monthOfDate + "/" + dayOfDate + "/" + yearOfDate;
	}

	/**
	 * Helper method to determine whether the year is a leap year.
	 * @param year year part of the date
	 * @return true if the input is a leap year, false otherwise.
	 */
	private static boolean checkIfLeapYear(int year)
	{
		if (year%QUADRENNIAL != 0)
			return false;
		
		if (year%CENTENNIAL != 0)
			return true;

		if (year%QUATERCENTENNIAL == 0)
			return true;

		return false;
	}
	
	public static final int QUADRENNIAL = 4;
	public static final int CENTENNIAL = 100;
	public static final int QUATERCENTENNIAL = 400;
	/**Set to 1980 year as per requirement*/
	public static final int EARLIEST_YEAR = 1980;
	/**Set to 2021 year as it is now*/
	public static final int LATEST_YEAR = 2021;
	/**Set to January*/
	public static final int EARLIEST_MONTH = 1;
	/**Set to December*/
	public static final int LATEST_MONTH = 12;
	/**Set to 1st*/
	public static final int EARLIEST_DATE = 1;
	public static final int JANUARY = 1;
	public static final int FEBRUARY = 2;
	public static final int MARCH = 3;
	public static final int APRIL = 4;
	public static final int MAY = 5;
	public static final int JUNE = 6;
	public static final int JULY = 7;
	public static final int AUGUST = 8;
	public static final int SEPTEMBER = 9;
	public static final int OCTOBER = 10;
	public static final int NOVEMBER = 11;
	public static final int DECEMBER = 12;

	/**
	 * Testbed main method to check the isValid method in this class.
	 * 
	 * @param args For user input, if any.
	 */
	public static void main(String[] args)
	{
		Date testdate1  = new Date("08/15/1947");
		Date testdate2  = new Date("09/28/1979");
		Date testdate3  = new Date("01/32/2000");
		Date testdate4  = new Date("09/31/2010");
		Date testdate5  = new Date("02/28/2021");
		Date testdate6  = new Date("02/29/2020");
		Date testdate7  = new Date("12/31/2022");
		Date testdate8  = new Date("15/15/2019");
		Date testdate9  = new Date("09/01/2021");
		Date testdate10 = new Date("5/2/2015");
		
		System.out.println("Test Cases for isValid() method running...");
		System.out.println("");
		
		//Test Case 1: Here we check for the date's with year before 1980's
		System.out.println("Test Case 1  ---> Is 08/15/1947 a vaild date: " + testdate1.isValid());
		
		//Test Case 2:Here we again check for the date's with year before 1980's
		System.out.println("Test Case 2  ---> Is 09/28/1979 a vaild date: " + testdate2.isValid());
		
		//Test Case 3: Here we check for the months with their specified number of days i.e. 31 days
		System.out.println("Test Case 3  ---> Is 01/32/2000 a vaild date: " + testdate3.isValid());
		
		//Test Case 4: Here we check for the months with their specified number of days i.e. 30 days
		System.out.println("Test Case 4  ---> Is 09/31/2010 a vaild date: " + testdate4.isValid());
		
		//Test Case 5: Here we check for the months with their specified number of days i.e. 28 days in normal year
		System.out.println("Test Case 5  ---> Is 02/28/2021 a vaild date: " + testdate5.isValid());
		
		//Test Case 6: Here we check for the months with their specified number of days i.e. 29 days in leap year
		System.out.println("Test Case 6  ---> Is 02/29/2020 a vaild date: " + testdate6.isValid());
		
		//Test Case 7: Here we check for a date beyond today's date
		System.out.println("Test Case 7  ---> Is 12/31/2022 a vaild date: " + testdate7.isValid());
		
		//Test Case 8: Here we check for number of months in a year
		System.out.println("Test Case 8  ---> Is 15/15/2019 a vaild date: " + testdate8.isValid());
		
		//Test Case 9: Here we check for a perfect valid date i.e. Fall 2021 semester start date
		System.out.println("Test Case 9  ---> Is 09/01/2021 a vaild date: " + testdate9.isValid());
		
		//Test Case 10: Here we check for single digit dates in month and day.
		System.out.println("Test Case 10 ---> Is  5/2/2015  a vaild date: " + testdate10.isValid());
		
		System.out.println("");
		System.out.println("Completed all the Test Cases.");
	}
}
