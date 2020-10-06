package transaction;
/**
 * Savings Account is an Account subclass that gives loyal customers a promotional interest rate of 0.35%
 * Savings Accounts have a monthly fee of $5 and are waived if the account balance is greater than or equal to $300.00.
 * Saving Account Class extends the Account Class.
 * @author Kacper Murdzek, Taranvir Singh
 *
 */
public class Saving extends Account {
	private boolean isLoyal;
	/**
	 * Savings Constructor creates a Savings Account Object
	 * @param holder Profile with first and last name of account user
	 * @param balance double that holds the Account balance
	 * @param dateOpen date that the Account was created
	 * @param isLoyal boolean if the account holder is loyal
	 */
	public Saving(Profile holder, double balance, Date dateOpen, boolean isLoyal) {
		super(holder, balance, dateOpen);
		this.isLoyal = isLoyal;
	}
	/**
	 * Monthly Interest function calculates the monthly interest for the account
	 * @return monthlyInterest returns the monthly interest for the account
	 */
	@Override
	public double monthlyInterest() {

		
		return this.getBalance()*(0.0025/12);
	}
	/**
	 * Monthly fee returns the fee amount for the month
	 * @return fee for the month
	 */
	@Override
	public double monthlyFee() {
		
		if(this.isLoyal==true || this.getBalance()>=300) {
			return 0;
		}
		return 5;
	}
	/**
	 * Checks if the objects are both Saving Accounts and returns if they are equal
	 * @param obj Object to be compared with Saving Account
	 * @return boolean, true if the Accounts equal each other, false if they do not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Saving) {
			Saving comparison = (Saving) obj;
			return this.getHolder().equals(comparison.getHolder());
		}
		return false;
	}
	/**
	 * Makes a String of the Saving Account
	 * @return Saving Account as a string
	 */
	@Override
	public String toString() {
		
		if(this.isLoyal==true) {
			return "*Savings"+super.toString()+"*special Savings account*";
		}
		
		return "*Savings"+super.toString();

	}

}
