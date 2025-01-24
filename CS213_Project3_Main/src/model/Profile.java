/**
 * Package name studentData
 */
package model;

/**
 * Profile object uniquely identifies a student with their major in the roster.
 * @author 
 */
public class Profile
{
	/**Name of the Student*/
	private String name;
	/**Major of the Student*/
	private Major major;

	/**
	 * Create profile object
	 * @param name Name
	 * @param major Major
	 */
	public Profile(String name, Major major)
	{
		this.name = name;
		this.major = major;
	}

	/**
	 * Getter to return the name
	 * @return get name
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Getter to return the major
	 * @return major
	 */
	public String getMajor()
	{
		return this.major.toString();
	}

	/**
	 * Equals method
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj == this)
		{
			return true;
		}
		if (!(obj instanceof Profile))
		{
			return false;
		}

		Profile objProfile = (Profile) obj;

		return this.name.equals(objProfile.name) &&
				this.major.equals(objProfile.major);
	}

	/**
	 * to string method
	 */
	@Override
	public String toString()
	{
		return this.name + ":" + this.major;
	}
}
