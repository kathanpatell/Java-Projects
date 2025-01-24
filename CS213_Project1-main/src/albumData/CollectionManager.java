/**
 * Package name albumData
 */
package albumData;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class is the User interface that handles the input and output.
 * Contains collection object, program_run_status, outputValuesToPrint, indexToReferForOutput
 * , copyOfCollectionAtEachPrint, and indexToReferForCollection.
 * 
 * @author 
 */
public class CollectionManager 
{
	/** Creating an object of type Collection*/
	Collection collection = new Collection();
	/** Program Status indicator*/
	private int program_run_status = 1;
	/** String array for printing the output values*/
	String[] outputValuesToPrint = new String[100];
	/** A counter to keep track for output*/
	int indexToReferForOutput = 0;
	/** An array of type collection to store the output*/
	Collection[] copyOfCollectionAtEachPrint = new Collection[100];
	/** A counter to keep track for collection*/
	int indexToReferForCollection = 0;
	
	/**
	 * This helper method prints the starting statement of the program
	 */
	private void start_collection_manager()
	{
		System.out.println("Collection Manager Started Running...");
	}
	
	/**
	 * This helper method prints the ending statement of the program
	 */
	private void stop_collection_manager() 
	{
		System.out.println("Collection Manager Terminated.");
	}

	/**
	 * Helper method to capture the collection at each P, PG, PD command.
	 * @param collection collection object that needs to be captures.
	 */
	private void addCollectionToCopyArray(Collection collection)
	{
		int length = copyOfCollectionAtEachPrint.length;
		if (indexToReferForCollection == length)
		{
			Collection[] tempArray = new Collection[length + 50];

			for (int i = 0; i < length; i++)
			{
				tempArray[i] = copyOfCollectionAtEachPrint[i];
			}
			copyOfCollectionAtEachPrint = tempArray;
		}

		Collection tempCollection = new Collection();
		Album[] collectionAlbum = collection.getAlbumArray();

		for (int i = 0; i < collectionAlbum.length; i++)
		{
			tempCollection.add(collectionAlbum[i]);
		}
		copyOfCollectionAtEachPrint[indexToReferForCollection++] = tempCollection;
	}
	
	/**
	 * Helper method to store all the output lines at one location and to print later.
	 * @param outputLine Takes the output for an input command and store it to print later
	 */
	private void addLineToOutput(String outputLine)
	{
		int length = outputValuesToPrint.length;
		
		if (indexToReferForOutput == length)
		{
			String[] tempArray = new String[length + 50];

			for (int i = 0; i < length; i++)
			{
				tempArray[i] = outputValuesToPrint[i];
			}
			outputValuesToPrint = tempArray;
		}

		outputValuesToPrint[indexToReferForOutput++] = outputLine;
	}
	
	/**
	 * This helper method helps to the create an Album object of the user input 
	 * after verifying the requirements, so that it can be added to the collection.
	 * This helper method also calls on the add(Album album) method in the collection 
	 * class for adding.
	 * 
	 * @param tempArrayForData It is the user input stored in an array.
	 */
	private void addNewAlbum(String[] tempArrayForData)
	{
		String title = tempArrayForData[1];
		String artist = tempArrayForData[2];
		boolean isAvailable = true;

		Date date = new Date(tempArrayForData[4]);
		
		if(!date.isValid())
		{
			addLineToOutput("Invalid Date!");
			return;
		}

		String unknown = "Unknown";
		Genre genre = Genre.valueOf(unknown);
		String genreArray[] = {"Pop", "Classical", "Country", "Jazz"};
		String genreAsString = tempArrayForData[3];

		for (int i = 0; i < genreArray.length; i++)
		{
			if (genreAsString.equalsIgnoreCase(genreArray[i]))
			{
				genre = Genre.valueOf(genreArray[i]);
			}
		}

		Album album = new Album(title, artist, genre, date, isAvailable);

		if (collection.find(album) == -1)
		{
			collection.add(album);
			addLineToOutput(album + " >> " + "added.");
		}
		else
		{
			addLineToOutput(album + " >> " + "is already in the collection.");
		}
	}

	/**
	 * helper method to delete album
	 * @param tempArrayForData array containing album data
	 */
	private void deleteAlbum(String[] tempArrayForData)
	{
		String title = tempArrayForData[1];
		String artist = tempArrayForData[2];
		Date date = new Date();
		boolean isAvailable = true;
		Genre genre = Genre.Unknown;

		Album dummyAlbum = new Album(title, artist, genre, date, isAvailable);
		
		if (collection.remove(dummyAlbum))
		{
			addLineToOutput(title + "::" + artist + " >> deleted.");
		}
		else
		{
			addLineToOutput(title + "::" + artist + " >> is not in the collection.");
		}
	}

	/**
	 * helper method to lend/return album to/from a friend.
	 * @param tempArrayForData array containing album data
	 */
	private void lendAndReturnAlbum(String[] tempArrayForData)
	{
		String operation = tempArrayForData[0];
		String title = tempArrayForData[1];
		String artist = tempArrayForData[2];
		Date date = new Date();
		boolean isAvailable = true;
		Genre genre = Genre.Unknown;

		Album dummyAlbum = new Album(title, artist, genre, date, isAvailable);

		int albumIndex = collection.find(dummyAlbum);
		if (albumIndex == -1)
		{
			if (operation.equals("L"))
			{
				addLineToOutput(title + "::" + artist + " >> is not available");
			}
			else if (operation.equals("R"))
			{
				addLineToOutput((title + "::" + artist + " >> return cannot be completed."));
			}	
		}
		else
		{
			Album[] albumArray = collection.getAlbumArray();
			
			if (operation.equals("L"))
			{
				if (collection.lendingOut(albumArray[albumIndex]))
				{
					addLineToOutput(title + "::" + artist + " >> lending out and set to not available.");
				}	
				else
				{
					addLineToOutput(title + "::" + artist + " >> is not available");
				}	
			}
			else
			{
				if (collection.returnAlbum(albumArray[albumIndex]))
				{
					addLineToOutput(title + "::" + artist + " >> returning and set to available.");
				}
				else
				{
					addLineToOutput((title + "::" + artist + " >> return cannot be completed."));
				}
			}
		}
	}

	/**
	 * This method checks the user input for the chosen option of operation by the user.
	 * And then calls upon the required methods from this class as well as from collection 
	 * class.
	 * 
	 * @param tempArrayForData It is the user input from the console.
	 */
	private void commandsToOperate(String[] tempArrayForData) 
	{
		String user_input = tempArrayForData[0];
		
		if (user_input.equals("A"))
		{
			addNewAlbum(tempArrayForData);
		}
		else if (user_input.equals("D"))
		{
			deleteAlbum(tempArrayForData);
		}
		else if (user_input.equals("L") || user_input.equals("R"))
		{
			lendAndReturnAlbum(tempArrayForData);
		}
		else if (user_input.equals("P") || user_input.equals("PD") || user_input.equals("PG"))
		{
			addCollectionToCopyArray(collection);
			addLineToOutput(user_input);
		}
		else if(user_input.equals("Q"))
		{
			program_run_status = -1;
		}
		else
		{
			addLineToOutput("Invalid Command!");
		}
	}

	/**
	 * helper method to print the output to the console
	 */
	private void printOutput()
	{
		int indexForCollectionArray = 0;
		int i = 0;
		String currentOutputLine = outputValuesToPrint[i];

		while(i < outputValuesToPrint.length && currentOutputLine != null)
		{
			if (currentOutputLine.equals("P"))
			{
				copyOfCollectionAtEachPrint[indexForCollectionArray++].print();
			}
			else if (currentOutputLine.equals("PG"))
			{
				copyOfCollectionAtEachPrint[indexForCollectionArray++].printByGenre();
			}
			else if (currentOutputLine.equals("PD"))
			{
				copyOfCollectionAtEachPrint[indexForCollectionArray++].printByReleaseDate();
			}
			else
			{
				System.out.println(currentOutputLine);
			}
				
			currentOutputLine = outputValuesToPrint[++i];
		}
	}
	
	/**
	 * This method reads continuously from the console and processes it by calling 
	 * commandToOperate method.
	 * This method also calls on start_collection_manager and stop_collection_manager 
	 * helper methods.
	 */
	public void run() 
	{
		start_collection_manager();
		boolean tokenFlag;
		String[] tempArrayForData = new String[5];
		for (int i = 0; i < tempArrayForData.length; i++)
		{
			tempArrayForData[i] = "-";
		}
			
		while(program_run_status != -1)
		{
			tokenFlag = false;
			Scanner myObj = new Scanner(System.in);
			StringTokenizer st = new StringTokenizer(myObj.nextLine(),",");
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
		}

		printOutput();
		stop_collection_manager();
	}
}
