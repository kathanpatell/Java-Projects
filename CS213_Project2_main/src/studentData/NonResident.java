/**
 * Package name studentData
 */
package studentData;

/**
 * Subclass to Student. Used for students from everywhere except in-state.
 * @author 
 */
public class NonResident extends Student
{
	/**Cost per credit for non resident*/
	protected static final double COST_PER_CREDIT_NON_RESIDENT = 966.0;
	/**Tuition fee for non resident*/
	protected static final double FT_NON_RESIDENT_CREDIT_FEE = 29737.0;
	
	/**
	 * Create student object of type non resident
	 * @param profile Name and major
	 * @param credit_hours credit hours
	 * @param tuition_due payment owed
	 * @param total_payment total payment made
	 * @param last_payment_date last payment date
	 * @param full_time_student True or false
	 * @param residingState state student stays
	 */
	public NonResident(Profile profile, int credit_hours, double tuition_due, 
			double total_payment, Date last_payment_date, boolean full_time_student, String residingState)
	{
		super(profile, credit_hours, tuition_due, total_payment, last_payment_date, full_time_student, residingState);
	}

	/**
	 * calculates tuition due for Non resident
	 */
	@Override
	public void tuitionDue()
	{
		double tuition;
		if(this.full_time_student)
		{
			tuition = FT_NON_RESIDENT_CREDIT_FEE + FT_UNIVERSITY_FEE;

			if (this.credit_hours > ADDITIONAL_COSTS_BEYOND_CREDITS)
			{
				tuition +=
						(this.credit_hours - ADDITIONAL_COSTS_BEYOND_CREDITS)
								* COST_PER_CREDIT_NON_RESIDENT;
			}
		}

		else
		{
			tuition = (this.credit_hours * COST_PER_CREDIT_NON_RESIDENT)
					+ PT_UNIVERSITY_FEE;
		}
		tuition -= this.total_payment;

		this.tuition_due = tuition;
	}
	/**
	 * To string method
	 */
	@Override
	public String toString()
	{
		return (super.toString() + ":non-resident");
	}
	
	
}
