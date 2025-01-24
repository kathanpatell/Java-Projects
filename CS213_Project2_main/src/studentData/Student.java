/**
 * Package name studentData
 */
package studentData;

/**
 * Student class is the superclass to Resident, Non-resident. Used to store information about a student.
 * @author 
 */
public class Student 
{
	protected static final int ADDITIONAL_COSTS_BEYOND_CREDITS = 16;
	protected static final double FT_UNIVERSITY_FEE = 3268.0;
	protected static final double PT_UNIVERSITY_FEE = 2614.4;
	/**Profile of the Student such as Name and Major*/
	protected Profile profile;
	/**Credit Hours taken by student*/
	protected int credit_hours;
	/**Tuition amount needed to be paid by student*/
	protected double tuition_due;
	/**Total payment paid by the student*/
	protected double total_payment;
	/**Last payment date*/
	protected Date last_payment_date;
	/**Full Time or Part Time Student*/
	protected boolean full_time_student;
	/**State identifier*/
	protected String residingState;
	
	/**
	 * 
	 * @param profile profile
	 * @param credit_hours credit hours
	 * @param tuition_due amount of tuition due
	 * @param total_payment total payments made
	 * @param last_payment_date last date of payment
	 * @param full_time_student boolean value for full time status
	 * @param residingState residing state
	 */
	public Student(Profile profile, int credit_hours, double tuition_due, 
			double total_payment, Date last_payment_date, boolean full_time_student, String residingState)
	{
		this.profile = profile;
		this.credit_hours = credit_hours;
		this.tuition_due = tuition_due;
		this.total_payment = total_payment;
		this.last_payment_date = last_payment_date;
		this.full_time_student = full_time_student;
		this.residingState = residingState;
	}

	/**
	 * return the residing state of the student.
	 * @return gets the residing state:  NJ,NY,CT,NR,INTERNATIONAL
	 */
	public String getResidingState()
	{
		return this.residingState;
	}

	/**
	 * get the Profile of a student
	 * @return get the Profile of a student
	 */
	public Profile getProfile()
	{
		return this.profile;
	}
	
	/**
	 * get credit hours
	 * @return credit hours
	 */
	public int getCreditHours()
	{
		return this.credit_hours;
	}
	
	/**
	 * get the tuition due
	 * @return amount of tuition due
	 */
	public double getTuitionDue()
	{
		return this.tuition_due;
	}
	
	/**
	 * get total payments made
	 * @return total payments made
	 */
	public double getTotalPayment()
	{
		return this.total_payment;
	}
	
	/**
	 * get last payment date
	 * @return last payment date
	 */
	public Date getLastPaymentDate()
	{
		return this.last_payment_date;
	}
	
	/**
	 * get student status
	 * @return true if full time, false otherwise.
	 */
	public boolean getStudentStatus()
	{
		return this.full_time_student;
	}

	/**
	 * Sets the last payment date.
	 * @param date date which is to be set
	 */
	public void setPaymentDate(Date date)
	{
		this.last_payment_date = date;
	}

	/**
	 * Sets the tuition to the given value.
	 * @param tuition tuition to set
	 */
	public void setTuitionDue(double tuition)
	{
		this.tuition_due = tuition;
	}

	/**
	 * set the total payment to the given value
	 * @param payment value to set
	 */
	public void setPayment(double payment)
	{
		this.total_payment = payment;
	}

	/**
	 * to string method
	 */
	@Override
	public String toString()
	{
		return (profile.toString() + ":" + this.credit_hours + " credit hours:tuition due:" 
				+ String.format("%,.2f",this.tuition_due) + ":total payment:"
				+ String.format("%,.2f", this.total_payment) + ":last payment date: "
				+ this.last_payment_date);
	}
	
	/**
	 * Do Nothing Method (Required)
	 */
	public void tuitionDue()
	{
		//Do Nothing (Required Method in this Class)
	}
}
