package transaction;

/**
 * Money Market is an Account subclass that Cannot waive fees if the number of
 * withdrawals exceed 6. Money Markets monthly fees are $12 and are waived if
 * the account balance is greater than $2,500 Money Market Class extends the
 * Account Class
 * 
 * @author Kacper Murdzek, Taranvir Singh
 *
 */
public class MoneyMarket extends Account {
	private int withdrawals;

	/**
	 * Money Market Constructor creates a Money Market Object
	 * 
	 * @param holder      Profile with first and last name of account user
	 * @param balance     double that holds the Account balance
	 * @param dateOpen    date that the Account was created
	 * @param withdrawals int for the current number of withdrawals that the account
	 *                    has
	 */
	public MoneyMarket(Profile holder, double balance, Date dateOpen, int withdrawals) {
		super(holder, balance, dateOpen);
		this.withdrawals = withdrawals;
	}

	/**
	 * Monthly Interest function calculates the monthly interest for the account
	 * 
	 * @return monthlyInterest returns the monthly interest for the account
	 */
	@Override
	public double monthlyInterest() {

		return this.getBalance() * (0.0065 / 12);
	}

	/**
	 * Monthly fee returns the fee amount for the month
	 * 
	 * @return fee for the month
	 */
	@Override
	public double monthlyFee() {
		if (this.withdrawals <= 6 && this.getBalance() >= 2500) {
			return 0;
		}
		return 12;
	}

	/**
	 * Checks if the objects are both Money Market Accounts and returns if they are
	 * equal
	 * 
	 * @param obj Object to be compared with Money Market Account
	 * @return boolean, true if the Accounts equal each other, false if they do not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MoneyMarket) {
			MoneyMarket comparison = (MoneyMarket) obj;
			return this.getHolder().equals(comparison.getHolder());
		}
		return false;
	}

	/**
	 * Makes a String of the Money Market Account
	 * 
	 * @return Money Market Account as a string
	 */
	@Override
	public String toString() {

		if (this.withdrawals == 1) {
			return "*Money Market" + super.toString() + "*" + this.withdrawals + " withdrawal*";
		} else
			return "*Money Market" + super.toString() + "*" + this.withdrawals + " withdrawals*";

	}

	/**
	 * Getter method to access the accounts withdrawal amount
	 * 
	 * @return withdrawal amount
	 */
	public int getWithdrawals() {
		return this.withdrawals;
	}

	/**
	 * Sets the withdrawals of the Money Market Object
	 * 
	 * @param withdrawal amount to set the withdrawals to
	 */
	public void setWithdrawals(int withdrawal) {
		this.withdrawals = this.withdrawals + withdrawal;
	}

}
