/**
 *
 */
package controller;

import java.time.format.DateTimeFormatter;
import model.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import java.time.LocalDate;

/**
 * Main controller class that handles all the operation
 * @author 
 */
public class MainController
{
    /**
     * Constructor
     */
    public MainController()
    {}

    /** Max credit for a full time student*/
    public static int FT_MAX_CREDITS_LIMIT = 24;
    /** Min credit limit for every student */
    public static int MIN_CREDITS_LIMIT_FOR_ALL = 3;
    /** Min limit for full time status */
    public static int FT_MIN_CREDITS_LIMIT = 12;
    /**Zero Credits*/
    private static int ZERO_CREDITS = 0;
    /**Place holder date for student object*/
    Date place_holder_date = new Date("0/0/0000");
    /**Place holder for tuition due*/
    double place_holder_tuitionDue = 0.00;
    /**Place holder for total payment*/
    double place_holder_totalPayment = 0.00;
    /**Creating an object called roster of Roster class*/
    Roster roster = new Roster();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="BA"
    private RadioButton BA; // Value injected by FXMLLoader

    @FXML // fx:id="CS"
    private RadioButton CS; // Value injected by FXMLLoader

    @FXML // fx:id="CT"
    private RadioButton CT; // Value injected by FXMLLoader

    @FXML // fx:id="EE"
    private RadioButton EE; // Value injected by FXMLLoader

    @FXML // fx:id="FALSE"
    private RadioButton FALSE; // Value injected by FXMLLoader

    @FXML // fx:id="IT"
    private RadioButton IT; // Value injected by FXMLLoader

    @FXML // fx:id="ME"
    private RadioButton ME; // Value injected by FXMLLoader

    @FXML // fx:id="NY"
    private RadioButton NY; // Value injected by FXMLLoader

    @FXML // fx:id="TRUE"
    private RadioButton TRUE; // Value injected by FXMLLoader

    @FXML // fx:id="ai_student"
    private Tab ai_student; // Value injected by FXMLLoader

    @FXML // fx:id="amount_of_tuition_aid_given"
    private TextField amount_of_tuition_aid_given; // Value injected by FXMLLoader

    @FXML // fx:id="amount_of_tuition_paid"
    private TextField amount_of_tuition_paid; // Value injected by FXMLLoader

    @FXML // fx:id="an_student"
    private Tab an_student; // Value injected by FXMLLoader

    @FXML // fx:id="ar_student"
    private Tab ar_student; // Value injected by FXMLLoader

    @FXML // fx:id="at_student"
    private Tab at_student; // Value injected by FXMLLoader

    @FXML // fx:id="button_for_add_international_student"
    private Button button_for_add_international_student; // Value injected by FXMLLoader

    @FXML // fx:id="button_for_add_non_resident_student"
    private Button button_for_add_non_resident_student; // Value injected by FXMLLoader

    @FXML // fx:id="button_for_add_resident_student"
    private Button button_for_add_resident_student; // Value injected by FXMLLoader

    @FXML // fx:id="button_for_add_tristate_student"
    private Button button_for_add_tristate_student; // Value injected by FXMLLoader

    @FXML // fx:id="button_to_give_financial_aid"
    private Button button_to_give_financial_aid; // Value injected by FXMLLoader

    @FXML // fx:id="button_to_pay_tuition"
    private Button button_to_pay_tuition; // Value injected by FXMLLoader

    @FXML // fx:id="button_to_remove_student"
    private Button button_to_remove_student; // Value injected by FXMLLoader

    @FXML // fx:id="button_to_set_study_abroad_status"
    private Button button_to_set_study_abroad_status; // Value injected by FXMLLoader

    @FXML // fx:id="command_A"
    private Tab command_A; // Value injected by FXMLLoader

    @FXML // fx:id="command_C"
    private Button command_C; // Value injected by FXMLLoader

    @FXML // fx:id="command_F"
    private Tab command_F; // Value injected by FXMLLoader

    @FXML // fx:id="command_P"
    private RadioButton command_P; // Value injected by FXMLLoader

    @FXML // fx:id="command_PN"
    private RadioButton command_PN; // Value injected by FXMLLoader

    @FXML // fx:id="command_PT"
    private RadioButton command_PT; // Value injected by FXMLLoader

    @FXML // fx:id="command_R"
    private Tab command_R; // Value injected by FXMLLoader

    @FXML // fx:id="command_S"
    private Tab command_S; // Value injected by FXMLLoader

    @FXML // fx:id="command_T"
    private Tab command_T; // Value injected by FXMLLoader

    @FXML // fx:id="ct_tristate_state1"
    private RadioButton ct_tristate_state1; // Value injected by FXMLLoader

    @FXML // fx:id="major"
    private ToggleGroup major; // Value injected by FXMLLoader

    @FXML // fx:id="number_of_credits_taken_command_A"
    private TextField number_of_credits_taken_command_A; // Value injected by FXMLLoader

    @FXML // fx:id="ny_tristate_state1"
    private RadioButton ny_tristate_state1; // Value injected by FXMLLoader

    @FXML // fx:id="payment_date"
    private DatePicker payment_date; // Value injected by FXMLLoader

    @FXML // fx:id="printCommands"
    private ToggleGroup printCommands; // Value injected by FXMLLoader

    @FXML // fx:id="result"
    private TextArea result; // Value injected by FXMLLoader

    @FXML // fx:id="studyAbroad"
    private ToggleGroup studyAbroad; // Value injected by FXMLLoader

    @FXML // fx:id="text_field_command_A"
    private TextField text_field_command_A; // Value injected by FXMLLoader

    @FXML // fx:id="text_field_command_F"
    private TextField text_field_command_F; // Value injected by FXMLLoader

    @FXML // fx:id="text_field_command_R"
    private TextField text_field_command_R; // Value injected by FXMLLoader

    @FXML // fx:id="text_field_command_S"
    private TextField text_field_command_S; // Value injected by FXMLLoader

    @FXML // fx:id="text_field_command_T"
    private TextField text_field_command_T; // Value injected by FXMLLoader

    @FXML // fx:id="tristate"
    private ToggleGroup tristate; // Value injected by FXMLLoader

    @FXML // fx:id="view_button_for_print_statements"
    private Button view_button_for_print_statements; // Value injected by FXMLLoader

    /**
     *  Method for appending the missing data thing to TextArea in the GUI
     */
    private void missing_data_output()
    {
        result.appendText("Missing data.\n");
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
        if(tempArray[1].isEmpty() && tempArray[3].isEmpty())
        {
            result.appendText("Missing data.\n");
            return false;
        }
        else if (tempArray[1].isEmpty())
        {
            result.appendText("Enter the name of the student.\n");
            return false;
        }
        else if(tempArray[3].isEmpty())
        {
            result.appendText("Credit hours missing.\n");
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
        if(tempArray[1].isEmpty())
        {
            result.appendText("Missing data.\n");
            return false;
        }

        return true;
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
                result.appendText("Credit hours exceed the maximum 24.\n");
                return -1;
            case 98762:
                result.appendText("Minimum credit hours is 3.\n");
                return -1;
            case 98763:
                result.appendText("Credit hours cannot be negative.\n");
                return -1;
        }

        return 1;
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

        Major major = Major.valueOf(tempArrayForData[2]);

        String name = tempArrayForData[1];

        if (!isNumeric(tempArrayForData[3]))
        {
            result.appendText("Invalid credit hours.\n");
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
            result.appendText("Student added.\n");
        }
        else
        {
            result.appendText("Student is already in the roster.\n");
        }
    }

    /**
     * add a non resident student
     * @param tempArrayForData user input
     */
    private void addNewNonResidentStudent(String[] tempArrayForData)
    {
        if(!check_inputs_completeness(tempArrayForData))
        {
            return;
        }

        Major major = Major.valueOf(tempArrayForData[2]);

        String name = tempArrayForData[1];

        if(!isNumeric(tempArrayForData[3]))
        {
            result.appendText("Invalid credit hours.\n");
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
            result.appendText("Student added.\n");
        }
        else
        {
            result.appendText("Student is already in the roster.\n");
        }
    }

    /**
     * add a tristate student
     * @param tempArrayForData user input
     */
    private void addNewTriStateStudent(String[] tempArrayForData)
    {
        if (!check_inputs_completeness(tempArrayForData))
        {
            return;
        }

        Major major = Major.valueOf(tempArrayForData[2]);

        String name = tempArrayForData[1];

        if (!isNumeric(tempArrayForData[3]))
        {
            result.appendText("Invalid credit hours.\n");
            return;
        }

        int credits = Integer.parseInt(tempArrayForData[3]);
        boolean student_status = isStudentFullTime(credits);

        int check_code = isCreditsValid(credits);

        if (code_check(check_code) == -1)
        {
            return;
        }

        String state = tempArrayForData[4];

        Profile profile = new Profile(name, major);
        Student student = new TriState(profile, credits, place_holder_tuitionDue, place_holder_totalPayment,
                place_holder_date, student_status, state);

        if(roster.find(student) == -1)
        {
            roster.add(student);
            result.appendText("Student added.\n");
        }
        else
        {
            result.appendText("Student is already in the roster.\n");
        }
    }

    /**
     * add a new international student
     * @param tempArrayForData user input
     */
    private void addNewInternationalStudent(String[] tempArrayForData)
    {
        if (!check_inputs_completeness(tempArrayForData))
        {
            return;
        }

        Major major = Major.valueOf(tempArrayForData[2]);

        String name = tempArrayForData[1];

        if (!isNumeric(tempArrayForData[3]))
        {
            result.appendText("Invalid credit hours.\n");
            return;
        }

        int credits = Integer.parseInt(tempArrayForData[3]);
        boolean student_status = true;

        if (credits < 12 && credits >= 0)
        {
            if (credits < 3)
            {
                result.appendText("Minimum credit hours is 3.\n");
                return;
            }

            result.appendText("International students must enroll at least 12 credits.\n");
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
            result.appendText("Student added.\n");
        }
        else
        {
            result.appendText("Student is already in the roster.\n");
        }
    }

    /**
     * remove the student from roster
     * @param tempArrayForData user input to remove a student
     */
    private void removeStudentFromRoster(String[] tempArrayForData)
    {
        if (!check_inputs_completeness2(tempArrayForData))
        {
            return;
        }

        String name = tempArrayForData[1];
        Major major = Major.valueOf(tempArrayForData[2]);

        Profile profile = new Profile(name, major);
        final int place_holder1 = 0;
        Student student = new Student(profile, place_holder1,
                place_holder_tuitionDue, place_holder_totalPayment,
                place_holder_date, true, "-");

        if(roster.remove(student))
        {
            result.appendText("Student removed from the roster.\n");
        }
        else
        {
            result.appendText("Student is not in the roster.\n");
        }
    }

    /**
     * helper method to make a payment for a given student
     * @param commands input values and payment to make
     */
    private void makePayment(String[] commands)
    {
        if(commands[1].isEmpty() & commands[3].isEmpty())
        {
            result.appendText("Enter the name of the student and the amount to be paid.\n");
            return;
        }

        if(commands[1].isEmpty())
        {
            result.appendText("Enter the name of the student.\n");
            return;
        }

        String name = commands[1];
        Major major = Major.valueOf(commands[2]);
        if (commands[3].isEmpty())
        {
            result.appendText("Payment amount missing.\n");
            return;
        }

        if (!isNumeric(commands[3]))
        {
            result.appendText("Invalid amount.\n");
            return;
        }

        double amount = Double.parseDouble(commands[3]);
        final int MIN_VALID_AMOUNT = 0;
        if (amount <= MIN_VALID_AMOUNT)
        {
            result.appendText("Invalid amount.\n");
            return;
        }

        Date date = new Date(commands[4]);

        if (!date.isValid())
        {
            result.appendText("Payment date invalid.\n");
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
            result.appendText("Student is not in the roster.\n");
            return;
        }

        Student student = roster.getRoster()[indexOfStudent];
        double tuition = student.getTuitionDue();

        if (amount > tuition)
        {
            result.appendText("Amount is greater than amount due.\n");
            return;
        }

        double currTotalPayments = student.getTotalPayment();
        double newTotalPayments = currTotalPayments + amount;
        student.setPayment(newTotalPayments);
        student.setTuitionDue(tuition - amount);
        student.setPaymentDate(date);

        result.appendText("Payment applied.\n");
    }

    /**
     * Adding resident student to roster
     * @param event ActionEvent event
     */
    @FXML
    void add_student_1(ActionEvent event)
    {
        String[] user_input = new String[4];
        RadioButton major_selection = (RadioButton) major.getSelectedToggle();

        if (major_selection != null)
        {
            String student_name = text_field_command_A.getText();
            String number_of_credits = number_of_credits_taken_command_A.getText();
            String student_major = major_selection.getText();
            user_input[0] = "AR";
            user_input[1] = student_name;
            user_input[2] = student_major;
            user_input[3] = number_of_credits;

            addNewResidentStudent(user_input);
        }
        else
        {
            missing_data_output();
        }
    }

    /**
     * Adding non resident student to roster
     * @param event ActionEvent event
     */
    @FXML
    void add_student_2(ActionEvent event)
    {
        String[] user_input = new String[4];
        RadioButton major_selection = (RadioButton) major.getSelectedToggle();

        if (major_selection != null)
        {
            String student_name = text_field_command_A.getText();
            String number_of_credits = number_of_credits_taken_command_A.getText();
            String student_major = major_selection.getText();
            user_input[0] = "AN";
            user_input[1] = student_name;
            user_input[2] = student_major;
            user_input[3] = number_of_credits;

            addNewNonResidentStudent(user_input);
        }
        else
        {
            missing_data_output();
        }
    }

    /**
     * Adding tri-state student to roster
     * @param event ActionEvent event
     */
    @FXML
    void add_student_3(ActionEvent event)
    {
        String[] user_input = new String[5];
        RadioButton major_selection = (RadioButton) major.getSelectedToggle();
        RadioButton state_selection = (RadioButton) tristate.getSelectedToggle();

        if (state_selection != null & major_selection != null)
        {
            String state_selected = state_selection.getText();
            String student_name = text_field_command_A.getText();
            String number_of_credits = number_of_credits_taken_command_A.getText();
            String student_major = major_selection.getText();
            user_input[0] = "AT";
            user_input[1] = student_name;
            user_input[2] = student_major;
            user_input[3] = number_of_credits;
            user_input[4] = state_selected;

            addNewTriStateStudent(user_input);
        }
        else
        {
            missing_data_output();
        }
    }

    /**
     * Adding international student to roster
     * @param event ActionEvent event
     */
    @FXML
    void add_student_4(ActionEvent event)
    {
        String[] user_input = new String[5];
        RadioButton major_selection = (RadioButton) major.getSelectedToggle();
        RadioButton study_abroad_selection = (RadioButton) studyAbroad.getSelectedToggle();

        if (study_abroad_selection != null & major_selection != null)
        {
            String study_abroad_status = study_abroad_selection.getText();
            String student_name = text_field_command_A.getText();
            String number_of_credits = number_of_credits_taken_command_A.getText();
            String student_major = major_selection.getText();

            if(study_abroad_status.equals("True"))
            {
                number_of_credits = "12";
            }

            user_input[0] = "AI";
            user_input[1] = student_name;
            user_input[2] = student_major;
            user_input[3] = number_of_credits;
            user_input[4] = study_abroad_status;

            addNewInternationalStudent(user_input);
        }
        else
        {
            missing_data_output();
        }
    }

    /**
     * Method for calculating tuition due
     * @param event ActionEvent event
     */
    @FXML
    void calculate_tuition_due(ActionEvent event)
    {
        Student[] allStudents = roster.getRoster();
        int size_of_the_roster = roster.getSize();

        if (size_of_the_roster != 0)
        {
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

            result.appendText("Calculation completed.\n");
        }
        else
        {
            result.appendText("Student roster is Empty!\n");
        }
    }

    /**
     * Method for executing print methods using different options
     *
     * @param event ActionEvent event
     */
    @FXML
    void execute_print_commands(ActionEvent event)
    {
        RadioButton view_selection = (RadioButton) printCommands.getSelectedToggle();

        if(view_selection != null)
        {
            String selected_view_option = view_selection.getText();
            String[] printRoster = null;
            if (selected_view_option.equals("View Roster"))
            {
                printRoster = roster.printRoster();
            }
            else if (selected_view_option.equals("View Roster by Student Names"))
            {
                printRoster = roster.printRosterByName();
            }
            else if (selected_view_option.equals("View Students who have paid tuition"))
            {
                printRoster = roster.printRosterByDate();
            }

            for (int l = 0;l < printRoster.length && printRoster[l] != null; l++)
            {
                result.appendText(printRoster[l]);
                result.appendText("\n");
            }
        }
        else
        {
            result.appendText("Select an Option First.\n");
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

        String name = commands[1];
        String amountAsString = commands[3];

        if(amountAsString.isEmpty() & name.isEmpty())
        {
            result.appendText("Enter the name of the student and the amount to be given.\n");
            return;
        }

        if (amountAsString.isEmpty())
        {
            result.appendText("Missing the amount.\n");
            return;
        }

        if(!isNumeric(amountAsString))
        {
            result.appendText("Invalid amount.\n");
            return;
        }

        double amount = Double.parseDouble(amountAsString);
        if ((amount < MIN_FINANCIAL_AID) || (amount > MAX_FINANCIAL_AID))
        {
            result.appendText("Invalid amount.\n");
            return;
        }

        Major major = Major.valueOf(commands[2]);

        if(name.isEmpty())
        {
            result.appendText("Enter the name of the student.\n");
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
            result.appendText("Student not in the roster.\n");
            return;
        }

        Student student = roster.getRoster()[indexOfStudent];
        if (!student.getStudentStatus())
        {
            result.appendText("Parttime student doesn't qualify for the award.\n");
            return;
        }

        if (!(student.getResidingState()).equals("NJ"))
        {
            result.appendText("Not a resident student.\n");
            return;
        }

        Resident residentStudent = (Resident) student;

        if (residentStudent.getFinancialAidGiven())
        {
            result.appendText("Awarded once already.\n");
            return;
        }

        residentStudent.setTuitionAfterAid(amount);
        residentStudent.setFinancialAidGiven(true);

        result.appendText("Tuition updated.\n");
    }

    /**
     * Method to set financial aid for a student
     *
     * @param event ActionEvent event
     */
    @FXML
    void give_financial_aid(ActionEvent event)
    {
        String[] user_input = new String[4];
        RadioButton major_selection = (RadioButton) major.getSelectedToggle();

        if(major_selection != null)
        {
            String student_name = text_field_command_F.getText();
            String student_major = major_selection.getText();
            String tuition_aid_given = amount_of_tuition_aid_given.getText();

            user_input[0] = "F";
            user_input[1] = student_name;
            user_input[2] = student_major;
            user_input[3] = tuition_aid_given;

            applyFinancialAid(user_input);
        }
        else
        {
            missing_data_output();
        }
    }

    /**
     * Method to pay tuition
     *
     * @param event ActionEvent event
     */
    @FXML
    void pay_tuition(ActionEvent event)
    {
        String[] user_input = new String[5];
        RadioButton major_selection = (RadioButton) major.getSelectedToggle();
        LocalDate my_date = payment_date.getValue();

        if (major_selection != null & my_date != null)
        {
            String student_name = text_field_command_T.getText();
            String student_major = major_selection.getText();
            String amount_being_paid = amount_of_tuition_paid.getText();
            String my_formatted_date = my_date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

            user_input[0] = "T";
            user_input[1] = student_name;
            user_input[2] = student_major;
            user_input[3] = amount_being_paid;
            user_input[4] = my_formatted_date;

            makePayment(user_input);
        }
        else
        {
            missing_data_output();
        }
    }

    /**
     * Method to remove a student from roster
     *
     * @param event ActionEvent event
     */
    @FXML
    void remove_student_from_roster(ActionEvent event)
    {
        String[] user_input = new String[3];
        RadioButton major_selection = (RadioButton) major.getSelectedToggle();

        if (major_selection != null)
        {
            String student_name = text_field_command_R.getText();
            String student_major = major_selection.getText();
            user_input[0] = "R";
            user_input[1] = student_name;
            user_input[2] = student_major;

            removeStudentFromRoster(user_input);
        }
        else
        {
            missing_data_output();
        }
    }

    /**
     * sets the study abroad for the given student
     * @param commands user inout
     */
    private void setStudyAbroadStatus(String[] commands)
    {
        String name = commands[1];
        Major major = Major.valueOf(commands[2]);

        if(name.isEmpty())
        {
            result.appendText("Enter the name of the student.\n");
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
            result.appendText("Couldn't find the international student.\n");
            return;
        }

        Student student = roster.getRoster()[indexOfStudent];
        if(!(student.getResidingState()).equals("INTERNATIONAL"))
        {
            result.appendText("Couldn't find the international student.\n");
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

        result.appendText("Tuition updated.\n");
    }

    /**
     * Method to set study abroad status for students.
     *
     * @param event ActionEvent event
     */
    @FXML
    void set_study_abraod_status(ActionEvent event)
    {
        String[] user_input = new String[4];
        RadioButton major_selection = (RadioButton) major.getSelectedToggle();
        RadioButton study_abroad_selection = (RadioButton) studyAbroad.getSelectedToggle();

        if (study_abroad_selection != null & major_selection != null)
        {
            String study_abroad_status = study_abroad_selection.getText();
            String student_name = text_field_command_S.getText();
            String student_major = major_selection.getText();
            user_input[0] = "S";
            user_input[1] = student_name;
            user_input[2] = student_major;
            user_input[3] = study_abroad_status;

            setStudyAbroadStatus(user_input);
        }
        else
        {
            missing_data_output();
        }
    }

    /**
     * Initializing method for GUI components from FXML file.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert BA != null : "fx:id=\"BA\" was not injected: check your FXML file 'MainView.fxml'.";
        assert CS != null : "fx:id=\"CS\" was not injected: check your FXML file 'MainView.fxml'.";
        assert CT != null : "fx:id=\"CT\" was not injected: check your FXML file 'MainView.fxml'.";
        assert EE != null : "fx:id=\"EE\" was not injected: check your FXML file 'MainView.fxml'.";
        assert FALSE != null : "fx:id=\"FALSE\" was not injected: check your FXML file 'MainView.fxml'.";
        assert IT != null : "fx:id=\"IT\" was not injected: check your FXML file 'MainView.fxml'.";
        assert ME != null : "fx:id=\"ME\" was not injected: check your FXML file 'MainView.fxml'.";
        assert NY != null : "fx:id=\"NY\" was not injected: check your FXML file 'MainView.fxml'.";
        assert TRUE != null : "fx:id=\"TRUE\" was not injected: check your FXML file 'MainView.fxml'.";
        assert ai_student != null : "fx:id=\"ai_student\" was not injected: check your FXML file 'MainView.fxml'.";
        assert amount_of_tuition_aid_given != null : "fx:id=\"amount_of_tuition_aid_given\" was not injected: check your FXML file 'MainView.fxml'.";
        assert amount_of_tuition_paid != null : "fx:id=\"amount_of_tuition_paid\" was not injected: check your FXML file 'MainView.fxml'.";
        assert an_student != null : "fx:id=\"an_student\" was not injected: check your FXML file 'MainView.fxml'.";
        assert ar_student != null : "fx:id=\"ar_student\" was not injected: check your FXML file 'MainView.fxml'.";
        assert at_student != null : "fx:id=\"at_student\" was not injected: check your FXML file 'MainView.fxml'.";
        assert button_for_add_international_student != null : "fx:id=\"button_for_add_international_student\" was not injected: check your FXML file 'MainView.fxml'.";
        assert button_for_add_non_resident_student != null : "fx:id=\"button_for_add_non_resident_student\" was not injected: check your FXML file 'MainView.fxml'.";
        assert button_for_add_resident_student != null : "fx:id=\"button_for_add_resident_student\" was not injected: check your FXML file 'MainView.fxml'.";
        assert button_for_add_tristate_student != null : "fx:id=\"button_for_add_tristate_student\" was not injected: check your FXML file 'MainView.fxml'.";
        assert button_to_give_financial_aid != null : "fx:id=\"button_to_give_financial_aid\" was not injected: check your FXML file 'MainView.fxml'.";
        assert button_to_pay_tuition != null : "fx:id=\"button_to_pay_tuition\" was not injected: check your FXML file 'MainView.fxml'.";
        assert button_to_remove_student != null : "fx:id=\"button_to_remove_student\" was not injected: check your FXML file 'MainView.fxml'.";
        assert button_to_set_study_abroad_status != null : "fx:id=\"button_to_set_study_abroad_status\" was not injected: check your FXML file 'MainView.fxml'.";
        assert command_A != null : "fx:id=\"command_A\" was not injected: check your FXML file 'MainView.fxml'.";
        assert command_C != null : "fx:id=\"command_C\" was not injected: check your FXML file 'MainView.fxml'.";
        assert command_F != null : "fx:id=\"command_F\" was not injected: check your FXML file 'MainView.fxml'.";
        assert command_P != null : "fx:id=\"command_P\" was not injected: check your FXML file 'MainView.fxml'.";
        assert command_PN != null : "fx:id=\"command_PN\" was not injected: check your FXML file 'MainView.fxml'.";
        assert command_PT != null : "fx:id=\"command_PT\" was not injected: check your FXML file 'MainView.fxml'.";
        assert command_R != null : "fx:id=\"command_R\" was not injected: check your FXML file 'MainView.fxml'.";
        assert command_S != null : "fx:id=\"command_S\" was not injected: check your FXML file 'MainView.fxml'.";
        assert command_T != null : "fx:id=\"command_T\" was not injected: check your FXML file 'MainView.fxml'.";
        assert ct_tristate_state1 != null : "fx:id=\"ct_tristate_state1\" was not injected: check your FXML file 'MainView.fxml'.";
        assert major != null : "fx:id=\"major\" was not injected: check your FXML file 'MainView.fxml'.";
        assert number_of_credits_taken_command_A != null : "fx:id=\"number_of_credits_taken_command_A\" was not injected: check your FXML file 'MainView.fxml'.";
        assert ny_tristate_state1 != null : "fx:id=\"ny_tristate_state1\" was not injected: check your FXML file 'MainView.fxml'.";
        assert payment_date != null : "fx:id=\"payment_date\" was not injected: check your FXML file 'MainView.fxml'.";
        assert printCommands != null : "fx:id=\"printCommands\" was not injected: check your FXML file 'MainView.fxml'.";
        assert result != null : "fx:id=\"result\" was not injected: check your FXML file 'MainView.fxml'.";
        assert studyAbroad != null : "fx:id=\"studyAbroad\" was not injected: check your FXML file 'MainView.fxml'.";
        assert text_field_command_A != null : "fx:id=\"text_field_command_A\" was not injected: check your FXML file 'MainView.fxml'.";
        assert text_field_command_F != null : "fx:id=\"text_field_command_F\" was not injected: check your FXML file 'MainView.fxml'.";
        assert text_field_command_R != null : "fx:id=\"text_field_command_R\" was not injected: check your FXML file 'MainView.fxml'.";
        assert text_field_command_S != null : "fx:id=\"text_field_command_S\" was not injected: check your FXML file 'MainView.fxml'.";
        assert text_field_command_T != null : "fx:id=\"text_field_command_T\" was not injected: check your FXML file 'MainView.fxml'.";
        assert tristate != null : "fx:id=\"tristate\" was not injected: check your FXML file 'MainView.fxml'.";
        assert view_button_for_print_statements != null : "fx:id=\"view_button_for_print_statements\" was not injected: check your FXML file 'MainView.fxml'.";
        result.setEditable(false);
        payment_date.setEditable(false);
    }
}
