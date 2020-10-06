package transaction;
/**
 * Profile class holds the first and last name of an account holder.
 * Profile is an object that holds a first and last name of a user and is passed into
 * every type of account constructor. Profile class contains a Profile constructor, a 
 * toString() method, getLname() method which gets the last name associated to a profile.
 * and a .equals() method which checks for equality between two profiles.
 * 
 * @author Kacper Murdzek, Taranvir Singh
 *
 */
public class Profile {
	private String fname;
	private String lname;
	/*
	 * Profile Constructor requires a String first and last name.
	 * evokes instance variables that belong to the Profile class.
	 * @param String first name of user, String last name of user
	 */
	public Profile(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}
	/**
	 * Method that formats the Profile object as a String
	 * @return Profile formatted as a String
	 */
	public String toString() {
		return fname + " " + lname;
	}
	/**
	 * Method thats gets the last name of the profile
	 * @return Instance of the profiles last name.
	 */
	public String getLname() {
		return this.lname;
	}
	/**
	 * Method that checks the equality of two Profiles
	 * @param Object that is to be compared
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Profile) {
			Profile comparison = (Profile) obj;
			return this.fname.equals(comparison.fname) && this.lname.equals(comparison.lname);
		}
		return false;
	}
}
