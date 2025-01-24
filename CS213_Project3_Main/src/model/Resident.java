/**
 *
 */
package model;

/**
 * Subclass to Student. Used for student having residing status as NJ.
 * @author 
 */
public class Resident extends Student
{
	/**cost per credit*/
	protected static final double COST_PER_CREDIT_RESIDENT = 404.0;
	/** resident credit*/
	private static double FT_RESIDENT_CREDIT_FEE = 12536.0;
	/**Financial aid*/
	private boolean financial_aid_given;

	/**
	 *
	 * @param profile Name and Major
	 * @param credit_hours credit hours
	 * @param tuition_due tuition amount owed
	 * @param total_payment payments made
	 * @param last_payment_date last payment date
	 * @param full_time_student boolean full time true; part time false
	 * @param financial_aid_given boolean financial aid given or not
	 * @param residingState residing state NJ
	 */
	public Resident(Profile profile, int credit_hours, double tuition_due,
					double total_payment, Date last_payment_date, boolean full_time_student, String residingState, boolean financial_aid_given)
	{
		super(profile, credit_hours, tuition_due, total_payment, last_payment_date, full_time_student, residingState);
		this.financial_aid_given = financial_aid_given;
	}

	/**
	 * sets financial aid true/false
	 * @param given boolean to set the aid status
	 */
	public void setFinancialAidGiven(boolean given)
	{
		this.financial_aid_given = given;
	}

	/**
	 * returns the status of financial aid given or not
	 * @return the status of financial aid given or not
	 */
	public boolean getFinancialAidGiven()
	{
		return this.financial_aid_given;
	}

	/**
	 * updates tuition by applying fin aid
	 * @param amount amount of aid applied
	 */
	public void setTuitionAfterAid(double amount)
	{
		this.tuition_due -= amount;
	}

	/**
	 * Calculates tuition due for resident students.
	 */
	@Override
	public void tuitionDue()
	{
		double tuition;

		if(this.full_time_student)
		{
			tuition = FT_RESIDENT_CREDIT_FEE + FT_UNIVERSITY_FEE;

			if (this.credit_hours > ADDITIONAL_COSTS_BEYOND_CREDITS)
			{
				tuition +=
						(this.credit_hours - ADDITIONAL_COSTS_BEYOND_CREDITS)
								* COST_PER_CREDIT_RESIDENT;
			}
		}
		else
		{
			tuition = (this.credit_hours * COST_PER_CREDIT_RESIDENT)
					+ PT_UNIVERSITY_FEE;
		}

		tuition -= this.total_payment;
		this.tuition_due = tuition;
	}

	/**
	 * to string method.
	 */
	@Override
	public String toString()
	{
		return (super.toString() + ":resident");
	}
}
