/**
 * Package name studentData
 */
package studentData;

/**
 * Subclass of NonResident. Used for student with NY, CT as residing state.
 * @author 
 */
public class TriState extends NonResident
{
	private static final double NY_TUITION_DISCOUNT = 4000.0;
	private static final double CT_TUITION_DISCOUNT = 5000.0;
	/**
	 * 
	 * @param profile profile
	 * @param credit_hours credits
	 * @param tuition_due tuition due
	 * @param total_payment total_payment
	 * @param last_payment_date last date of payment
	 * @param state state - NY or CT
	 * @param full_time_student boolean full time status
	 */
	public TriState(Profile profile, int credit_hours, double tuition_due, 
			double total_payment, Date last_payment_date, boolean full_time_student, String state)
	{
		super(profile, credit_hours, tuition_due, total_payment, last_payment_date,
				full_time_student, state);
	}

	/**
	 * Calculate tristate student tuition.
	 */
	@Override
	public void tuitionDue()
	{
		super.tuitionDue();
		if(this.full_time_student) {
			if ((this.residingState).equals("NY")) {
				this.tuition_due -= NY_TUITION_DISCOUNT;
			} else if ((this.residingState).equals("CT")) {
				this.tuition_due -= CT_TUITION_DISCOUNT;
			}
		}
	}
	
	/**
	 * to string
	 */
	@Override
	public String toString()
	{
		return (super.toString() + "(tri-state):" + this.residingState);
	}

}
