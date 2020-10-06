package transaction;

//read directions for date class she was saying some fuck shit in lecture about it

public class Date implements Comparable<Date> {
	private int year;
	private int month;
	private int day;

	public Date(int month, int day, int year) {
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
		case Month.Mar:
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
