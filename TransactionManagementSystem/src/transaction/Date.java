package transaction;

//read directions for date class she was saying some fuck shit in lecture about it

public class Date implements Comparable<Date>{
	private int year;
	private int month;
	private int day;
	
	public Date(int day, int month, int year) {
		this.year = year;
		this.day = day;
		this.month =month;
	}
	public int compareTo(Date date) {
		return 0;
	}
	public String toString() {
		return this.month + "/" + this.day+ "/" + this.year ;
	}
	public boolean isValid() {
		return false;
	}


}
