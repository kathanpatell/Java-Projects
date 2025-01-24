/**
 * Package name studentData
 */
package studentData;

/**
 *
 * Subclass to Nonresident. Used for student with international status.
 * @author 
 */
public class International extends NonResident
{
	private static final double ADDITIONAL_INTERNATIONAL_FEE = 2650.0;
	/***/
	private boolean study_abroad_status;
	
	/**
	 * Creates the Internation Student object
	 * @param profile Name and Major
	 * @param credit_hours Credit hours
	 * @param tuition_due Paymnet Owed
	 * @param total_payment Total payment made
	 * @param last_payment_date last payment date
	 * @param study_abroad_status True or False based on study abroad
	 * @param full_time_student full time student boolean
	 */
	public International(Profile profile, int credit_hours, double tuition_due, 
			double total_payment, Date last_payment_date, boolean full_time_student, 
			boolean study_abroad_status)
	{
		super(profile, credit_hours, tuition_due, total_payment,
				last_payment_date, full_time_student, "INTERNATIONAL");
		this.study_abroad_status = study_abroad_status;
	}
	
	/**
	 * setter method to set study_abroad_status to true/false.
	 * @param status set value True if study_abroad or Else false
	 */
	public void setStudyAbroad(boolean status)
	{
		this.study_abroad_status = status;
	}
	
	/**
	 * getter method that returns study abroad status
	 * @return Study abroad status
	 */
	public boolean getStudyAbroadStatus()
	{
		return this.study_abroad_status;
	}

	/**
	 * Sets credit hours
	 */
	public void setCreditHours()
	{
		final int MAX_CREDITS_INTERNATIONAL = 12;
		this.credit_hours = MAX_CREDITS_INTERNATIONAL;
	}

	/**
	 * Calculate tuition for international students.
	 */
	@Override
	public void tuitionDue()
	{
		double tuition;
		if (!getStudyAbroadStatus()) //IF FALSE then the tuition would be 29737 + 3268 + 2650 = 35665
		{
		
			tuition = FT_NON_RESIDENT_CREDIT_FEE + FT_UNIVERSITY_FEE + ADDITIONAL_INTERNATIONAL_FEE - this.total_payment;
			
			if (this.credit_hours > ADDITIONAL_COSTS_BEYOND_CREDITS)
			{
				tuition += (this.credit_hours - ADDITIONAL_COSTS_BEYOND_CREDITS) * COST_PER_CREDIT_NON_RESIDENT;
			}
		}
		else {
			tuition = (FT_UNIVERSITY_FEE + ADDITIONAL_INTERNATIONAL_FEE) - this.total_payment;
		}

		this.tuition_due = tuition;
	}

	
	/**
	 * To String Method for international
	 */
	@Override
	public String toString()
	{	
		if (getStudyAbroadStatus())
		{
			return (super.toString() + ":international:study abroad");
		}
		return (super.toString() + ":international");
	}
}
