package transaction;

/**
 * Date Class holds date that user account was created. Date class implements
 * Comparable class and is used to compare two dates. Date class also has
 * capability of validating a date.
 * 
 * @author Kacper Murdzek, Taranvir Singh
 */
public class Date implements Comparable<Date> {
	private int year;
	private int month;
	private int day;

	/**
	 * Date constructor creates a Date object
	 * 
	 * @param month month of the date
	 * @param day   day of the date
	 * @param year  year of the date
	 */
	public Date(int month, int day, int year) {
		this.year = year;
		this.day = day;
		this.month = month;
	}

	/**
	 * Compares two dates and returns 1 if the first date is greater than the
	 * second, returns 0 if the first date equals the second, returns -1 if the
	 * first date is less than the second.
	 * 
	 * @param date Date object being compared
	 * @return int comparison result
	 * 
	 */
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

	/**
	 * Returns the date as a String
	 * 
	 * @return String date
	 */
	@Override
	public String toString() {
		return this.month + "/" + this.day + "/" + this.year;
	}

	/**
	 * Checks to see if the Date is a real date.
	 * 
	 * @return validation true if the date is valid, false if not
	 */
	public boolean isValid() {

		switch (this.month) {

		case Month.JAN:
			if (this.day <= Month.DAYS_ODD) {
				return true;
			}
			break;
		case Month.FEB:

			if (this.day <= Month.DAYS_FEB) {
				return true;
			} else if (this.day == (Month.DAYS_FEB + 1)) {

				if (((this.year % Month.QUADRENNIAL == 0) && (this.year % Month.CENTENNIAL != 0))
						|| (this.year % Month.QUATERCENTENNIAL == 0)) {
					return true;
				}
				return false;
			} else
				break;
		case Month.MAR:
			if (this.day <= Month.DAYS_ODD) {
				return true;
			}
			break;
		case Month.APR:
			if (this.day <= Month.DAYS_EVEN) {
				return true;
			}
			break;
		case Month.MAY:
			if (this.day <= Month.DAYS_ODD) {
				return true;
			}
			break;
		case Month.JUN:
			if (this.day <= Month.DAYS_EVEN) {
				return true;
			}
			break;
		case Month.JUL:
			if (this.day <= Month.DAYS_ODD) {
				return true;
			}
			break;
		case Month.AUG:
			if (this.day <= Month.DAYS_ODD) {
				return true;
			}
			break;
		case Month.SEP:
			if (this.day <= Month.DAYS_EVEN) {
				return true;
			}
			break;
		case Month.OCT:
			if (this.day <= Month.DAYS_ODD) {
				return true;
			}
			break;
		case Month.NOV:
			if (this.day <= Month.DAYS_EVEN) {
				return true;
			}
			break;
		case Month.DEC:
			if (this.day <= Month.DAYS_ODD) {
				return true;
			}
			break;

		default:
			return false;
		}
		return false;
	}

	/**
	 * Getter method returns the dates year
	 * 
	 * @return int year
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * Getter method returns the dates month
	 * 
	 * @return int month
	 */
	public int getMonth() {
		return this.month;
	}

	/**
	 * Getter method returns the dates day
	 * 
	 * @return int day
	 */
	public int getDay() {
		return this.day;
	}

}
