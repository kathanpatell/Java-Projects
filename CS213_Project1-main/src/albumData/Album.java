/**
 * Package name albumData
 */
package albumData;

/**
 * Contains title, genre, artist, release date and availability for an album.
 * @author 
 *
 */
public class Album {
	
	/** Title for the album*/
	private String title; 
	/** Name of the Artist who made the Album*/
	private String artist; 
	/** Genre of the Album falls into*/
	private Genre genre; 
	/** Release Date of the Album*/
	private Date releaseDate; 
	/** And Availability of the album in Collection*/
	private boolean isAvailable; 

	/**
	 * Constructor for album object.
	 * Creates an Album object with the supplied values.
	 * @param title The title of the album
	 * @param artist Artist name who made the album
	 * @param genre Category this album falls into
	 * @param releaseDate Album release date
	 * @param isAvailable Album availability in the collection
	 */
	public Album(String title, String artist, Genre genre, Date releaseDate, Boolean isAvailable)
	{
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.isAvailable = isAvailable;
	}

	/**
	 * setter method to set isAvailable to true/false.
	 * @param availibility set value True if availability or Else false
	 */
	public void setIsAvailable(boolean availibility)
	{
		this.isAvailable = availibility;
	}
	
	/**
	 * getter method to get the title.
	 * @return title as a string
	 */
	public String getTitle()
	{
		return this.title;
	}

	/**
	 * getter method to get the artist.
	 * @return artist as a string
	 */
	public String getArtist()
	{
		return this.artist;
	}

	/**
	 * getter method to get the Genre
	 * @return genre as a string
	 */
	public String getGenre()
	{
		return this.genre.toString();
	}

	/**
	 * getter method to get the release date.
	 * @return releaseDate as a string
	 */
	public String getReleaseDate()
	{
		return this.releaseDate.toString();
	}

	/**
	 * getter method to get availability.
	 * @return boolean describing the availability.
	 */
	public boolean getAvailability()
	{
		return this.isAvailable;
	}
	
	/**
	 * Method to check if two albums are equal.
	 * if title and artists are same then the album are equal.
	 * @param obj Object type input obj
	 * @return true, if the albums have the same title and artists. False otherwise.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (! (obj instanceof Album))
			return false;

		Album objAlbum = (Album) obj;

		return this.title.equals(objAlbum.title)
				&& this.artist.equals(objAlbum.artist);
	}

	/**
	 * Converts the Album to a string, describing all the components
	 * @return string representing all the components of album.
	 */
	@Override
	public String toString()
	{
		return this.title + "::" + this.artist + "::" + this.genre.toString()
				+ "::" + this.releaseDate.toString() + "::" +
				availability(this.isAvailable);
	}

	/**
	 * helper method to print availability in toString method.
	 * @param isItAvailable boolean value of isAvailable.
	 * @return String representation of availability.
	 */
	private static String availability(boolean isItAvailable)
	{
		String available = "is available";
		String notAvailable = "is not available";
		if (isItAvailable)
			return available;
		else
			return notAvailable;
	}

}
