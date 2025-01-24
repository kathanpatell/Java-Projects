/**
 * Package name albumData
 */
package albumData;

/**
 * This class defines the array data structure to hold album collections and provide 
 * the operations to manage the collection.
 * Contains albums array that contains collection, and numAlbums that is a number of 
 * albums present in the albums array.
 * 
 * @author 
 */
public class Collection 
{
	/** An Array of type Album, to store all the albums in the collection*/
	private Album[] albums;
	/** A counter to count the number of albums in the collection*/
	private int numAlbums; 

	/**
	 * This constructor initializes the array albums and sets numAlbums to 0.
	 */
	public Collection()
	{
		this.albums = new Album[4];
		this.numAlbums = 0;
	}
	
	/**
	 * This method returns the albums array
	 * 
	 * @return albums 
	 */
	public Album[] getAlbumArray()
	{
		return albums;
	}
	
	/**
	 * This checks and finds whether the input album is already
	 * present in the collection or not.
	 *
	 * @param album the Album object from the user input
	 * @return i (index of the element in the array) If found or Else -1.
	 */
	public int find(Album album) 
	{
		for (int i = 0; i < albums.length; i++)
		{
			if (album.equals(albums[i]))
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
		Album[] newAlbums = new Album[albums.length + 4];

		for (int i = 0 ; i < albums.length ; i++)
		{
			newAlbums[i] = albums[i];
		}

		albums = newAlbums;
	}
	
	/**
	 * This method takes in a album of the type Album from the user and adds 
	 * it to the collection.
	 * 
	 * @param album Album object of the received input from user.
	 * @return true upon completion
	 */
	public boolean add(Album album) 
	{
		if (numAlbums % 4 == 0 && numAlbums != 0)
		{
			grow();
		}

		if (album == null)
			return false;
		albums[numAlbums++] = album;
		
		return true;
	}
	
	/**
	 * This method takes an Album object named album and finds it in the collection 
	 * and removes it.
	 * 
	 * @param album Album object of the received input from user.
	 * @return true If the operation is completed or Else false, if album not 
	 * 		   found in collection.
	 */
	public boolean remove(Album album) 
	{
		int index = find(album);
		if (index == -1)
		{
			return false;
		}
			
		Album[] temp_album_array = new Album[albums.length];
		
		for (int i = 0, j = 0; i < albums.length; i++) 
        	{
            		if (i != index) 
            		{
                		temp_album_array[j++] = albums[i];
           		}
        	}
		
		numAlbums--;
		albums = temp_album_array;

		return true;
	}
	
	/**
	 * This method sets the availability of the input album when lending out after 
	 * finding it in the collection.
	 * 
	 * @param album Album object of the received input from user.
	 * @return true IF operation completed or Else false, if element not available 
	 *         or not in the collection.
	 */
	public boolean lendingOut(Album album) 
	{
		int index = find(album);
		if (index == -1)
		{
			return false;
		}
			
		if(albums[index].getAvailability())
		{
			albums[index].setIsAvailable(false);
			return true;
		}
		else
		{
			return false;
		}		
	}
	
	/**
	 * This method sets the availability of the input album when returning it back 
	 * to collection after finding it.
	 * 
	 * @param album Album object of the received input from user.
	 * @return true If operation completed or Else false, if already available or 
	 *         not in the collection.
	 */
	public boolean returnAlbum(Album album) 
	{
		int index = find(album);
		if (index == -1)
		{
			return false;
		}
			
		if (!albums[index].getAvailability())
		{
			albums[index].setIsAvailable(true);
			return true;
		}
		else
		{
			return false;
		}	
	}
	
	/**
	 * This method prints the collection that is the albums array.
	 */
	public void print() 
	{
		if (numAlbums  == 0) {
			System.out.println("The collection is empty!");
			return;
		}
		System.out.println("*List of Albums in Collection");
		
		print_array(albums);
		
		System.out.println("*End of List");
	}
	
	/**
	 * This helper method helps to iterate over the array and print the elements 
	 * present in the array.
	 * 
	 * @param arr Is the albums array where the collection is stored.
	 */
	private void print_array(Album[] arr)
	{
		for(int i = 0; i < arr.length && arr[i] != null; i++)
		{
			System.out.println(arr[i].toString());
		}
	}
	
	/**
	 * This helper method helps sort the albums in the collection by following 
	 * the ascending order using the selection sort algorithm.
	 * 
	 * @param temp_array1 Is the albums array where the collection is stored.
	 */
	private void selection_sort_for_PD(Album[] temp_array1)
	{
		int array_size = temp_array1.length;
		 
        for (int i = 0; i < array_size - 1; i++)
        {
            int min_index = i;
            
            for (int j = i + 1; j < array_size; j++)
            {
            	if (temp_array1[j] != null && temp_array1[min_index] != null)
            	{
            		Date date1 = new Date(temp_array1[j].getReleaseDate());
                	Date date2 = new Date(temp_array1[min_index].getReleaseDate());
                	
            		if ((date1).compareTo(date2) == -1)
                    {
                		min_index = j;
                    }
            	}
            }
            
            Album temp = temp_array1[min_index];
            temp_array1[min_index] = temp_array1[i];
            temp_array1[i] = temp;
        }
        
        print_array(temp_array1);
	}
	
	/**
	 * This method prints the collection that is the albums array sorted by release dates.
	 */
	public void printByReleaseDate()
	{
		if (numAlbums  == 0) {
			System.out.println("The collection is empty!");
			return;
		}
		System.out.println("*Album Collection by Release Dates");
		
		Album[] temp_array1 = new Album[albums.length];

		for (int i = 0 ; i < albums.length ; i++)
		{
			temp_array1[i] = albums[i];
		}
		
		selection_sort_for_PD(temp_array1);
		
		System.out.println("*End of List");
	}
	
	/**
	 * This method prints the collection that is the albums array sorted by genre.
	 */
	public void printByGenre()
	{
		if (numAlbums  == 0) {
			System.out.println("The collection is empty!");
			return;
		}
		
		System.out.println("*Album Collection by Genre");
		
		Album[] temp_array2 = new Album[albums.length];
		
		for (int i = 0 ; i < albums.length ; i++)
		{
			temp_array2[i] = albums[i];
		}
		
		int array_size = temp_array2.length;  
		  
		for(int i = 0; i < array_size - 1; i++)   
		{  
			for (int j = i + 1; j < array_size; j++)   
			{  
				if (temp_array2[i] != null && temp_array2[j] != null)
				{
					String genre1 = temp_array2[i].getGenre();
					String genre2 = temp_array2[j].getGenre();
					
					if(genre1.compareTo(genre2) > 0)   
					{  
						Album temp = temp_array2[i];  
						temp_array2[i] = temp_array2[j];  
						temp_array2[j] = temp;  
					} 
				} 
			} 
		}
		
		print_array(temp_array2);
		
		System.out.println("*End of List");
	}
}
