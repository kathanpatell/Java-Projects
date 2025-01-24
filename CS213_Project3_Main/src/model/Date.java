/**
 * Package name studentData
 */
package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		Date todaysDate = new Date(dtf.format(localDate));

		this.day = todaysDate.day;
		this.month = todaysDate.month;
		this.year = todaysDate.year;
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


		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		Date todaysDate = new Date(dtf.format(localDate));

		if (todaysDate.compareTo(inputDate) == -1 || todaysDate.compareTo(inputDate) == 0)
		{
			return false;
		}

		boolean isLeapYear = checkIfLeapYear(this.year);
		int numberOfFebruaryDays = 28;

		if (isLeapYear)
		{
			numberOfFebruaryDays = 29;
		}

		final int numberOfDaysInLargerMonths = 31;
		final int numberOfDaysInSmallerMonths = 30;

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
	 * Compares the two input dates and mentions whether they are equal or not.
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
		final int placeHolderDateValue = 0;
		if (this.day == placeHolderDateValue &&
				this.month == placeHolderDateValue && this.year == placeHolderDateValue)
		{
			return "--/--/----";
		}
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
		final int QUADRENNIAL = 4;
		final int CENTENNIAL = 100;
		final int QUATERCENTENNIAL = 400;
		if (year%QUADRENNIAL != 0)
			return false;

		if (year%CENTENNIAL != 0)
			return true;

		if (year%QUATERCENTENNIAL == 0)
			return true;

		return false;
	}



	/** earliest year*/
	private static final int EARLIEST_YEAR = 2021;
	/** lastest year*/
	private static final int LATEST_YEAR = 2021;
	/** earliest month*/
	private static final int EARLIEST_MONTH = 1;
	/** latest month*/
	private static final int LATEST_MONTH = 12;
	/** earliest month*/
	private static final int EARLIEST_DATE = 1;
	/** january */
	private static final int JANUARY = 1;
	/** february */
	private static final int FEBRUARY = 2;
	/** march */
	private static final int MARCH = 3;
	/** april */
	private static final int APRIL = 4;
	/** may */
	private static final int MAY = 5;
	/** june */
	private static final int JUNE = 6;
	/** july */
	private static final int JULY = 7;
	/** august */
	private static final int AUGUST = 8;
	/** september */
	private static final int SEPTEMBER = 9;
	/** october */
	private static final int OCTOBER = 10;
	/** november */
	private static final int NOVEMBER = 11;
	/** december */
	private static final int DECEMBER = 12;
}
