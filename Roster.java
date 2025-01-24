/**
 * Package name studentData
 */
package studentData;

/**
 *
 * Roster class to track data of all the students and their related tuition and academic info.
 * @author 
 */
public class Roster 
{
	/**An Array to store the data of students*/
	private Student[] roster;
	/**Keep track of the number of students in the roster */
	private int size;
	/***/
	private static int ARRAY_SIZE_INCREMENTS = 4;
	
	/**
	 * This constructor initializes the array roster and sets size to 0.
	 */
	public Roster()
	{
		this.roster = new Student[ARRAY_SIZE_INCREMENTS];
		this.size = 0;
	}

	/**
	 * returns the roster array of all the students.
	 * @return gets the roster array
	 */
	public Student[] getRoster()
	{
		return this.roster;
	}

	/**
	 * get the size of the roster
	 * @return size variable of the roster
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * This checks and finds whether the input student is already
	 * present in the roster or not.
	 *
	 * @param student the Student object from the user input
	 * @return i (index of the element in the array) If found or Else -1.
	 */
	public int find(Student student) 
	{
		for (int i = 0; i < roster.length && roster[i] != null; i++)
		{
			if ((student.getProfile()).equals(roster[i].getProfile()))
			{
				return i;
			}	
		}

		return -1;
	}

	/**
	 * This helper method increases the capacity of the array 4, when it's full.
	 */
	private void grow() 
	{
		Student[] newRoster = new Student[roster.length + ARRAY_SIZE_INCREMENTS];

		for (int i = 0 ; i < roster.length ; i++)
		{
			newRoster[i] = roster[i];
		}

		roster = newRoster;
	}
	
	/**
	 * This method takes in a student of the type Student from the user and adds 
	 * it to the roster.
	 * 
	 * @param student Student object of the received input from user.
	 * @return true upon completion
	 */
	public boolean add(Student student) 
	{
		if (size % 4 == 0 && size != 0)
		{
			grow();
		}

		if (student == null)
		{
			return false;
		}
			
		roster[size++] = student;
		
		return true;
	}
	
	/**
	 * This method takes an Student object named student and finds it in the roster 
	 * and removes it.
	 * 
	 * @param student Student object of the received input from user.
	 * @return true If the operation is completed or Else false, if album not 
	 * 		   found in collection.
	 */
	public boolean remove(Student student) 
	{
		int index = find(student);
		if (index == -1)
		{
			return false;
		}
			
		Student[] temp_album_array = new Student[roster.length];
		
		for (int i = 0, j = 0; i < roster.length; i++) 
        {
            if (i != index) 
            {
                temp_album_array[j++] = roster[i];
           	}
        }
		
		size--;
		roster = temp_album_array;

		return true;
	}
		
	/**
	 * prints the roster
	 */
	public void printRoster()
	{
		if (size == 0)
		{
			System.out.println("Student roster is empty!");
			return;
		}

		System.out.println("* list of students in the roster **");
		
		print_array(roster);
		
		System.out.println("* end of roster **");
	}
	
	/**
	 * This helper method helps to iterate over the array and print the elements 
	 * present in the array.
	 * 
	 * @param arr Is the albums array where the collection is stored.
	 */
	private void print_array(Student[] arr)
	{
		for(int i = 0; i < arr.length && arr[i] != null; i++)
		{
			System.out.println(arr[i].toString());
		}
	}
	
	/**
	 * prints the roster by name
	 */
	public void printRosterByName()
	{
		if (size == 0)
		{
			System.out.println("Student roster is empty!");
			return;
		}

		System.out.println("* list of students ordered by name **");
		
		Student[] temp_array2 = new Student[roster.length];
		
		for (int i = 0 ; i < roster.length ; i++)
		{
			temp_array2[i] = roster[i];
		}
		
		int array_size = temp_array2.length;  
		  
		for(int i = 0; i < array_size - 1; i++)   
		{  
			for (int j = i + 1; j < array_size; j++)   
			{  
				if (temp_array2[i] != null && temp_array2[j] != null)
				{
					String student_name_1 = temp_array2[i].getProfile().getName();
					String student_name_2 = temp_array2[j].getProfile().getName();
					
					if(student_name_1.compareTo(student_name_2) > 0)   
					{  
						Student temp = temp_array2[i];  
						temp_array2[i] = temp_array2[j];  
						temp_array2[j] = temp;  
					} 
				} 
			} 
		}
		
		print_array(temp_array2);
		
		System.out.println("* end of roster **");
	}
	
	/**
	 * This helper method helps sort the albums in the collection by following 
	 * the ascending order using the selection sort algorithm.
	 * 
	 * @param temp_array1 Is the albums array where the collection is stored.
	 */
	private void selection_sort_for_PT(Student[] temp_array1)
	{
		int array_size = temp_array1.length;
		 
        for (int i = 0; i < array_size - 1; i++)
        {
            int min_index = i;
            
            for (int j = i + 1; j < array_size; j++)
            {
            	if (temp_array1[j] != null && temp_array1[min_index] != null)
            	{
            		Date date1 = temp_array1[j].getLastPaymentDate();
                	Date date2 = temp_array1[min_index].getLastPaymentDate();
                	
            		if ((date1).compareTo(date2) == -1)
                    {
                		min_index = j;
                    }
            	}
            }
            
            Student temp = temp_array1[min_index];
            temp_array1[min_index] = temp_array1[i];
            temp_array1[i] = temp;
        }
        
        print_array(temp_array1);
	}
	
	/**
	 * This method prints the collection that is the albums array sorted by release dates.
	 */
	public void printRosterByDate()
	{
		if (size  == 0) 
		{
			System.out.println("Student roster is empty!");
			return;
		}
		
		System.out.println("* list of students made payments ordered by payment date **");
		
		Student[] temp_array1 = new Student[roster.length];
		Date temp_date = new Date("0/0/0000");

		for (int i = 0, j = 0 ; i < roster.length && roster[i] != null; i++)
		{
			if ((roster[i].getLastPaymentDate()).compareTo(temp_date) != 0)
			{
				temp_array1[j++] = roster[i];
			}
		}
		
		selection_sort_for_PT(temp_array1);
		
		System.out.println("* end of roster **");
	}
}
