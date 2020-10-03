package transaction;

public class Profile {
	private String fname;
	private String lname;
	public Profile(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}
	public String toString() {
		return fname + " " + lname;
	}
	public String getLname() {
		return this.lname;
	}
	public boolean equals(Object obj) {
		if(obj instanceof Profile) {
			Profile comparison = (Profile) obj;
			return this.fname.equals(comparison.fname) && this.lname.equals(comparison.lname);
		}
		return false;
	}
}
