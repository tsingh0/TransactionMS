package transaction;

/**
 * Profile class holds the first and last name of an account holder. Profile is
 * an object that holds a first and last name of a user and is passed into every
 * type of account constructor. Profile class contains a Profile constructor, a
 * toString() method, getLname() method which gets the last name associated to a
 * profile. and a .equals() method which checks for equality between two
 * profiles.
 * 
 * @author Kacper Murdzek, Taranvir Singh
 *
 */
public class Profile {
	private String fname;
	private String lname;

	/**
	 * Profile Constructor requires a String first and last name. evokes instance
	 * variables that belong to the Profile class.
	 * 
	 * @param fname String first name of user
	 * @param lname String last name of user
	 */
	public Profile(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}

	/**
	 * Method that formats the Profile object as a String
	 * 
	 * @return String Profile formatted as a String
	 */
	@Override
	public String toString() {
		return fname + " " + lname;
	}

	/**
	 * Method thats gets the last name of the profile.
	 * 
	 * @return String Instance of the profiles last name.
	 */
	public String getLname() {
		return this.lname;
	}

	/**
	 * Method that checks the equality of two Profiles
	 * 
	 * @param obj Object that is to be compared
	 * @return boolean true if the Profiles are the same, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Profile) {
			Profile comparison = (Profile) obj;
			return this.fname.equals(comparison.fname) && this.lname.equals(comparison.lname);
		}
		return false;
	}

	/**
	 * Method that gets the first name of the profile.
	 * 
	 * @return String instance of the profiles first name.
	 */
	public String getFname() {
		return this.fname;
	}
}
