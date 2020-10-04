package transaction;

//read directions for date class she was saying some fuck shit in lecture about it

public class Date implements Comparable<Date> {
	private int year;
	private int month;
	private int day;

	public Date(int day, int month, int year) {
		this.year = year;
		this.day = day;
		this.month = month;
	}

	public int compareTo(Date date) {

		if (this.year > date.year) {
			return 1;
		} else if (this.year < date.year) {
			return -1;
		} else if (this.month > date.month) {
			return 1;
		} else if (this.month < date.month) {
			return -1;
		} else if (this.day > date.day) {
			return 1;
		} else if (this.day < date.day) {
			return -1;
		} else
			return 0;

	}

	public String toString() {
		return this.month + "/" + this.day + "/" + this.year;
	}

	public boolean isValid() {
		
		
		return false;
	}

	public int getYear() {
		return this.year;
	}

	public int getMonth() {
		return this.month;
	}

	public int getDay() {
		return this.day;
	}

}
