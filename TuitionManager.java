/**
 * package name studentData
 */
package studentData;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * class that is the centeral hub of all the computation requested by user.
 * @author 
 */
public class TuitionManager
{
	/** Max credit for a full time student*/
	public static int FT_MAX_CREDITS_LIMIT = 24;
	/** Min credit limit for every student */
	public static int MIN_CREDITS_LIMIT_FOR_ALL = 3;
	/** Min limit for full time status */
	public static int FT_MIN_CREDITS_LIMIT = 12;
	/** Program Status indicator*/
	private int program_run_status = 1;
	/** Program Status check indicator*/
	private int status_check = 1;
	/** String array for storing input from console*/
	private String[] input_from_console;
	/** Size of the input_from_console*/
	private int num_of_input_from_console;
	/***/
	private static int ZERO_CREDITS = 0;
	/***/
	Date place_holder_date = new Date("0/0/0000");
	/***/
	double place_holder_tuitionDue = 0.00;
	/***/
	double place_holder_totalPayment = 0.00;
	/***/
	Roster roster = new Roster();
	
	/**
	 * This constructor initializes the array input_from_console.
	 */
	public TuitionManager()
	{
		this.input_from_console = new String[5];
		this.num_of_input_from_console = 0;
	}
	
	/**
	 * This helper method increases the capacity of the array by 5, when it's full.
	 */
	private void grow() 
	{
		String[] new_input_from_console = new String[input_from_console.length + 5];

		for (int i = 0 ; i < input_from_console.length ; i++)
		{
			new_input_from_console[i] = input_from_console[i];
		}

		input_from_console = new_input_from_console;
	}
	
	/**
	 * This helper method prints the starting statement of the program
	 */
	private void start_tuition_manager()
	{
		System.out.println("Tuition Manager Started Running...");
	}
	
	/**
	 * This helper method prints the ending statement of the program
	 */
	private void stop_tuition_manager() 
	{
		System.out.println("");
		System.out.println("Tuition Manager Terminated.");
	}
	
	/**
	 * 
	 * method to get commands inputted by the user and send them for execution.
	 * @param input_from_user It is the user input from the console.
	 */
	private void get_commands_to_operate(String[] input_from_user) 
	{
		boolean tokenFlag;
		String[] tempArrayForData = new String[5];
		int i = 0;
		
		while(status_check != -1)
		{
			for (int j = 0; j < tempArrayForData.length; j++)
			{
				tempArrayForData[j] = "-";
			}
			
			tokenFlag = false;
			StringTokenizer st = new StringTokenizer(input_from_user[i],",");
			int index = 0;
	
			while (st.hasMoreTokens())
			{
				tokenFlag = true;
				tempArrayForData[index] = st.nextToken();
				index++;
			}

			if(tokenFlag)
			{
				commandsToOperate(tempArrayForData);
			}
			
			i++;
		}
	}

	/**
	 * Checks if major is valid
	 * @param input_major input major by user
	 * @return Major if valid, null otherwise.
	 */
	private Major isValidMajor(String input_major)
	{
		String majorArray[] = {"CS", "IT", "BA", "EE", "ME"};
		String majorAsString = input_major;
		Major major = null;
		
		for (int i = 0; i < majorArray.length; i++)
		{
			if (majorAsString.equalsIgnoreCase(majorArray[i]))
			{
				major = Major.valueOf(majorArray[i]);
			}
		}
		
		return major;
	}
	
	/**
	 * checks if input state is valid
	 * @param input_state user input state
	 * @return state where student resides
	 */
	private String isValidState(String input_state)
	{
		String stateArray[] = {"CT", "NY"};
		
		for (int i = 0; i < stateArray.length; i++)
		{
			if (input_state.equalsIgnoreCase(stateArray[i]))
			{
				return stateArray[i];
			}
		}
		
		return null;
	}
	
	/**
	 * checks full time status of student
	 * @param credit user input credit
	 * @return true if full time, false otherwise.
	 */
	private boolean isStudentFullTime(int credit)
	{
		if (credit >= MIN_CREDITS_LIMIT_FOR_ALL && credit <= FT_MAX_CREDITS_LIMIT)
		{
			if(credit >= FT_MIN_CREDITS_LIMIT)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * checks validity of credits
	 * @param credits user input credits
	 * @return code that contains whether credits are valid or not.
	 */
	private int isCreditsValid(int credits)
	{
		if(credits > FT_MAX_CREDITS_LIMIT)
		{
			return 98761;
		}
		else if(credits < MIN_CREDITS_LIMIT_FOR_ALL && credits >= ZERO_CREDITS)
		{
			return 98762;
		}
		else if(credits < ZERO_CREDITS)
		{
			return 98763;
		}
		
		return 98764;
	}
	
	/**
	 * check code from isCreditsValid
	 * @param check_code code from isCreditsValid
	 * @return returns -1 and prints error message for invalid credits. Returns 1 for valid credits
	 */
	private int code_check(int check_code)
	{
		switch(check_code)
		{
			case 98761:
				System.out.println("Credit hours exceed the maximum 24.");
				return -1;
			case 98762:
				System.out.println("Minimum credit hours is 3.");
				return -1;
			case 98763:
				System.out.println("Credit hours cannot be negative.");
				return -1;
		}
		
		return 1;
	}
	
	/**
	 * checks if its a valid number
	 * @param str string from user
	 * @return true if numeric
	 */
	private boolean isNumeric(String str) 
	{ 
		try 
		{  
		    Integer.parseInt(str);  
		    return true;
		} 
		catch(NumberFormatException e)
		{  
		    return false;  
		}  
	}
	
	/**
	 * check if input is complete or not
	 * @param tempArray user input command
	 * @return true if valid, false otherwise
	 */
	private boolean check_inputs_completeness(String[] tempArray)
	{
		if(tempArray[1].equals("-") && tempArray[2].equals("-") && tempArray[3].equals("-"))
		{
			System.out.println("Missing data in command line.");
			return false;
		}
		else if(tempArray[2].equals("-") && tempArray[3].equals("-"))
		{
			System.out.println("Missing data in command line.");
			return false;
		}
		else if(tempArray[3].equals("-"))
		{
			System.out.println("Credit hours missing.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * check if input is complete or not
	 * @param tempArray user input command
	 * @return true if valid, false otherwise
	 */
	private boolean check_inputs_completeness2(String[] tempArray)
	{
		if(tempArray[1].equals("-") && tempArray[2].equals("-") && tempArray[3].equals("-") && tempArray[4].equals("-"))
		{
			System.out.println("Missing data in command line.");
			return false;
		}
		else if(tempArray[2].equals("-") && tempArray[3].equals("-") && tempArray[4].equals("-"))
		{
			System.out.println("Missing data in command line.");
			return false;
		}
		else if(tempArray[3].equals("-") && tempArray[4].equals("-"))
		{
			System.out.println("Credit hours missing.");
			return false;
		}
		else if (tempArray[4].equals("-"))
		{
			System.out.println("Missing data in command line.");
			return false;
		}
		
		return true;
	}

	/**
	 * check if input is complete or not
	 * @param tempArray user input command
	 * @return true if valid, false otherwise
	 */
	private boolean check_inputs_completeness3(String[] tempArray)
	{
		if(tempArray[1].equals("-") && tempArray[2].equals("-"))
		{
			System.out.println("Missing data in command line.");
			return false;
		}
		else if(tempArray[2].equals("-"))
		{
			System.out.println("Missing data in command line.");
			return false;
		}
		
		return true;
	}
	
	/**
	 *  add a resident student to the roster
	 * @param tempArrayForData user input
	 */
	private void addNewResidentStudent(String[] tempArrayForData)
	{
		if (!check_inputs_completeness(tempArrayForData))
		{
			return;
		}
		
		Major major = isValidMajor(tempArrayForData[2]);
		
		if(major == null)
		{
			System.out.println("'" + tempArrayForData[2] + "' is not a valid major.");
			return;
		}
		
		String name = tempArrayForData[1];
		
		if (!isNumeric(tempArrayForData[3]))
		{
			System.out.println("Invalid credit hours.");
			return;
		}
	
		int credits = Integer.parseInt(tempArrayForData[3]);
		boolean student_status = isStudentFullTime(credits);
		
		int check_code = isCreditsValid(credits);
		
		if (code_check(check_code) == -1)
		{
			return;
		}
		
		boolean aid = false;
		String residingState = "NJ";
		Profile profile = new Profile(name, major);
		Student student = new Resident(profile, credits,
				place_holder_tuitionDue, place_holder_totalPayment,
				place_holder_date, student_status, residingState, aid);
		
		if(roster.find(student) == -1)
		{
			roster.add(student);
			System.out.println("Student added.");
		}
		else
		{
			System.out.println("Student is already in the roster.");
		}
	}
	
	/**
	 * add a non resident student
	 * @param tempArrayForData user input
	 */
	private void addNewNonResidentStudent(String[] tempArrayForData)
	{
		boolean input_check = check_inputs_completeness(tempArrayForData);
		if (input_check == false)
		{
			return;
		}
		
		Major major = isValidMajor(tempArrayForData[2]);
		
		if(major == null)
		{
			System.out.println("'" + tempArrayForData[2] + "' is not a valid major.");
			return;
		}
		
		String name = tempArrayForData[1];
		boolean testing = isNumeric(tempArrayForData[3]);
		
		if (testing == false)
		{
			System.out.println("Invalid credit hours.");
			return;
		}
	
		int credits = Integer.parseInt(tempArrayForData[3]);
		boolean student_status = isStudentFullTime(credits);
		
		int check_code = isCreditsValid(credits);
		
		if (code_check(check_code) == -1)
		{
			return;
		}
		
		Profile profile = new Profile(name, major);
		Student student = new NonResident(profile, credits,
				place_holder_tuitionDue, place_holder_totalPayment,
				place_holder_date, student_status, "NR");
		
		if(roster.find(student) == -1)
		{
			roster.add(student);
			System.out.println("Student added.");
		}
		else
		{
			System.out.println("Student is already in the roster.");
		}
	}
	
	/**
	 * add a tristate student
	 * @param tempArrayForData user input
	 */
	private void addNewTriStateStudent(String[] tempArrayForData)
	{
		boolean input_check = check_inputs_completeness2(tempArrayForData);
		if (input_check == false)
		{
			return;
		}
		
		Major major = isValidMajor(tempArrayForData[2]);
		if(major == null)
		{
			System.out.println("'" + tempArrayForData[2] + "' is not a valid major.");
			return;
		}
		
		String name = tempArrayForData[1];
		boolean testing = isNumeric(tempArrayForData[3]);
		
		if (testing == false)
		{
			System.out.println("Invalid credit hours.");
			return;
		}
	
		int credits = Integer.parseInt(tempArrayForData[3]);
		boolean student_status = isStudentFullTime(credits);
		
		int check_code = isCreditsValid(credits);
		
		if (code_check(check_code) == -1)
		{
			return;
		}
		
		String state = isValidState(tempArrayForData[4]);
		
		if(state == null)
		{
			System.out.println("Not part of the tri-state area.");
			return;
		}
		
		Profile profile = new Profile(name, major);
		Student student = new TriState(profile, credits, place_holder_tuitionDue, place_holder_totalPayment,
				place_holder_date, student_status, state);
		
		if(roster.find(student) == -1)
		{
			roster.add(student);
			System.out.println("Student added.");
		}
		else
		{
			System.out.println("Student is already in the roster.");
		}
	}
	
	/**
	 * add a new international student
	 * @param tempArrayForData user input
	 */
	private void addNewInternationalStudent(String[] tempArrayForData)
	{
		boolean input_check = check_inputs_completeness2(tempArrayForData);
		if (input_check == false)
		{
			return;
		}
		
		Major major = isValidMajor(tempArrayForData[2]);
		
		if(major == null)
		{
			System.out.println("'" + tempArrayForData[2] + "' is not a valid major.");
			return;
		}
		
		String name = tempArrayForData[1];
		boolean testing = isNumeric(tempArrayForData[3]);
		
		if (testing == false)
		{
			System.out.println("Invalid credit hours.");
			return;
		}
	
		int credits = Integer.parseInt(tempArrayForData[3]);
		boolean student_status = true;
		
		if (credits < 12 && credits >= 0)
		{
			if (credits < 3)
			{
				System.out.println("Minimum credit hours is 3.");
				return;
			}
			
			System.out.println("International students must enroll at least 12 credits.");
			return;
		}
		
		int check_code = isCreditsValid(credits);
		
		if (code_check(check_code) == -1)
		{
			return;
		}
		
		boolean study_abroad = Boolean.parseBoolean(tempArrayForData[4]);
		Profile profile = new Profile(name, major);
		Student student = new International(profile, credits, place_holder_tuitionDue, place_holder_totalPayment, place_holder_date, student_status, study_abroad);
		
		if(roster.find(student) == -1)
		{
			roster.add(student);
			System.out.println("Student added.");
		}
		else
		{
			System.out.println("Student is already in the roster.");
		}
	}
	
	/**
	 * remove the student from roster
	 * @param tempArrayForData user input to remove a student
	 */
	private void removeStudentFromRoster(String[] tempArrayForData)
	{
		boolean input_check = check_inputs_completeness3(tempArrayForData);
		if (input_check == false)
		{
			return;
		}
		
		String name = tempArrayForData[1];
		Major major = isValidMajor(tempArrayForData[2]);
		
		if(major == null)
		{
			System.out.println("'" + tempArrayForData[2] + "' is not a valid major.");
			return;
		}
		
		Profile profile = new Profile(name, major);
		final int place_holder1 = 0;
		Student student = new Student(profile, place_holder1,
				place_holder_tuitionDue, place_holder_totalPayment,
				place_holder_date, true, "-");
		
		if(roster.remove(student))
		{
			System.out.println("Student removed from the roster.");
		}
		else
		{
			System.out.println("Student is not in the roster.");
		}
	}

	/**
	 * Helper method to apply financial aid to the given FT resident student.
	 * @param commands input commands to apply financial aid
	 */
	private void applyFinancialAid(String[] commands)
	{
		final double MAX_FINANCIAL_AID = 10000.0;
		final double MIN_FINANCIAL_AID = 0.0;
		String amountAsString = commands[3];
		if (amountAsString.equals("-"))
		{
			System.out.println("Missing the amount.");
			return;
		}

		double amount = Double.parseDouble(amountAsString);
		if ((amount < MIN_FINANCIAL_AID) || (amount > MAX_FINANCIAL_AID))
		{
			System.out.println("Invalid amount.");
			return;
		}

		String name = commands[1];
		Major major = isValidMajor(commands[2]);

		if(major == null)
		{
			System.out.println("'" + commands[2] + "' is not a valid major.");
			return;
		}

		Profile profile = new Profile(name, major);
		final int place_holder1 = 0;
		Student tempStudent = new Student(profile, place_holder1,
				place_holder_tuitionDue, place_holder_totalPayment,
				place_holder_date, true, "-");

		final int studentNotFound = -1;
		int indexOfStudent = roster.find(tempStudent);
		if (indexOfStudent == studentNotFound)
		{
			System.out.println("Student not in the roster.");
			return;
		}

		Student student = roster.getRoster()[indexOfStudent];
		if (!student.getStudentStatus())
		{
			System.out.println("Parttime student doesn't qualify for the award.");
			return;
		}

		if (!(student.getResidingState()).equals("NJ"))
		{
			System.out.println("Not a resident student.");
			return;
		}

		Resident residentStudent = (Resident) student;

		if (residentStudent.getFinancialAidGiven())
		{
			System.out.println("Awarded once already.");
			return;
		}

		residentStudent.setTuitionAfterAid(amount);
		residentStudent.setFinancialAidGiven(true);
		
		System.out.println("Tuition updated.");
	}

	/**
	 * sets the study abroad for the given student
	 * @param commands user inout
	 */
	private void setStudyAbroadStatus(String[] commands)
	{
		String name = commands[1];
		Major major = isValidMajor(commands[2]);

		if(major == null)
		{
			System.out.println("'" + commands[2] + "' is not a valid major.");
			return;
		}

		Profile profile = new Profile(name, major);
		final int place_holder1 = 0;
		Student tempStudent = new Student(profile, place_holder1,
				place_holder_tuitionDue, place_holder_totalPayment,
				place_holder_date, true, "-");

		final int studentNotFound = -1;
		int indexOfStudent = roster.find(tempStudent);
		if (indexOfStudent == studentNotFound)
		{
			System.out.println("Couldn't find the international student.");
			return;
		}

		Student student = roster.getRoster()[indexOfStudent];
		if(!(student.getResidingState()).equals("INTERNATIONAL"))
		{
			System.out.println("Couldn't find the international student.");
			return;
		}

		final int PAYMENT_FOR_INTERNATIONAL = 0;
		student.setPaymentDate(place_holder_date);
		student.setPayment(PAYMENT_FOR_INTERNATIONAL);

		International internationalStudent = (International) student;
		final int MAX_CREDIT_INTERNATIONAL = 12;
		if (student.getCreditHours() > MAX_CREDIT_INTERNATIONAL)
		{
			internationalStudent.setCreditHours();
		}
		internationalStudent.setStudyAbroad(Boolean.parseBoolean(commands[3]));
		internationalStudent.tuitionDue();
		
		System.out.println("Tuition updated.");
	}

	/**
	 * calculates the tuition for all the students in the roster
	 */
	private void calculateTotalTuitionDue()
	{
		Student[] allStudents = roster.getRoster();

		for (int i = 0; i < allStudents.length && allStudents[i] != null; i++)
		{
				// For null entries
				if (allStudents[i] == null)
				{
					break;
				}
				//Resident
				if ((allStudents[i].getResidingState()).equals("NJ"))
				{
					Resident resident = (Resident) allStudents[i];
					//System.out.println(resident.toString());
					resident.tuitionDue();
				}
				//International
				else if ((allStudents[i].getResidingState()).equals("INTERNATIONAL"))
				{
					International internationalStudent = (International) allStudents[i];
					//System.out.println(internationalStudent.toString());
					internationalStudent.tuitionDue();
				}
				// Non Resident
				else if ((allStudents[i].getResidingState()).equals("NR")) {
					NonResident nonResident = (NonResident) allStudents[i];
					nonResident.tuitionDue();
				}
				// NY TriState Non Resident
				else if ((allStudents[i].getResidingState()).equals("NY")) {
					TriState triState = (TriState) allStudents[i];
					triState.tuitionDue();
				}
				// CT TriState Non Resident
				else if ((allStudents[i].getResidingState()).equals("CT")) {
					TriState triState = (TriState) allStudents[i];
					triState.tuitionDue();
				}
		}
		
		System.out.println("Calculation completed.");

	}

	/**
	 * helper method to make a payment for a given student
	 * @param commands input values and payment to make
	 */
	private void makePayment(String[] commands)
	{
		String name = commands[1];
		Major major = isValidMajor(commands[2]);
		if (commands[3].equals("-"))
		{
			System.out.println("Payment amount missing.");
			return;
		}

		double amount = Double.parseDouble(commands[3]);
		final int MIN_VALID_AMOUNT = 0;
		if (amount <= MIN_VALID_AMOUNT)
		{
			System.out.println("Invalid amount.");
			return;
		}

		if (commands[4].equals("-"))
		{
			System.out.println("Payment date invalid.");
			return;
		}

		Date date = new Date(commands[4]);
		if (!date.isValid())
		{
			System.out.println("Payment date invalid.");
			return;
		}
		

		Profile profile = new Profile(name, major);
		final int place_holder1 = 0;
		Student tempStudent = new Student(profile, place_holder1,
				place_holder_tuitionDue, place_holder_totalPayment,
				place_holder_date, true, "-");

		final int studentNotFound = -1;
		int indexOfStudent = roster.find(tempStudent);
		if (indexOfStudent == studentNotFound)
		{
			System.out.println("Student is not in the roster.");
			return;
		}

		Student student = roster.getRoster()[indexOfStudent];
		double tuition = student.getTuitionDue();

		if (amount > tuition)
		{
			System.out.println("Amount is greater than amount due.");
			return;
		}
		double currTotalPayments = student.getTotalPayment();
		double newTotalPayments = currTotalPayments + amount;
		student.setPayment(newTotalPayments);
		student.setTuitionDue(tuition - amount);
		student.setPaymentDate(date);

		System.out.println("Payment applied.");
	}

	/**
	 * parses the command to execute then executes the required commands
	 * @param commands user input to execute
	 */
	private void commandsToOperate(String[] commands)
	{
		String user_input = commands[0];
		
		if (user_input.equals("AR"))
			addNewResidentStudent(commands);

		else if (user_input.equals("AN"))
			addNewNonResidentStudent(commands);

		else if (user_input.equals("AT"))
			addNewTriStateStudent(commands);

		else if (user_input.equals("AI"))
			addNewInternationalStudent(commands);

		else if (user_input.equals("R"))
			removeStudentFromRoster(commands);

		else if (user_input.equals("C"))
			calculateTotalTuitionDue();

		else if (user_input.equals("T"))
			makePayment(commands);

		else if (user_input.equals("S"))
			setStudyAbroadStatus(commands);

		else if (user_input.equals("F"))
			applyFinancialAid(commands);

		else if (user_input.equals("P"))
			roster.printRoster();

		else if (user_input.equals("PN"))
			roster.printRosterByName();

		else if (user_input.equals("PT"))
			roster.printRosterByDate();

		else if(user_input.equals("emptyLine"))
			System.out.println();

		else if(user_input.equals("Q"))
			status_check = -1;

		else {
			System.out.println("Command " + "'" + user_input
					+ "'" + " not supported!");
		}

	}
	
	/**
	 * This method reads continuously from the console and processes it by calling 
	 * commandToOperate method.
	 * This method also calls on start_tuition_manager and stop_tuition_manager 
	 * helper methods.
	 */
	public void run() 
	{
		start_tuition_manager();
		
		while(program_run_status != -1)
		{
			Scanner myObj = new Scanner(System.in);

			while(myObj.hasNext())
			{
				String input = myObj.nextLine();
				
				if(num_of_input_from_console % 5 == 0 && num_of_input_from_console != 0)
				{
					grow();
				}
				
				if(input.isEmpty())
				{
					input_from_console[num_of_input_from_console] = "emptyLine";
					num_of_input_from_console++;
				}
				else
				{
					input_from_console[num_of_input_from_console] = input;
					num_of_input_from_console++;
				}

				if(input.equals("Q"))
				{
					program_run_status = -1;
					break;
				}
			}
			
			myObj.close();
		}
		
		get_commands_to_operate(input_from_console);
		
		stop_tuition_manager();
	}
}
