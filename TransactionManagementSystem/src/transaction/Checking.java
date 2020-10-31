package transaction;

/**
 * Checking is an Account subclass that waives monthly fees if the account has
 * direct deposit Checking Accounts have an annual interest rate of 0.05% and a
 * monthly fee of $25 but is waived if the balance is $1,500. Checking class
 * extends the Account class.
 * 
 * @author Kacper Murdzek, Taranvir Singh
 *
 */
public class Checking extends Account {
	private boolean directDeposit;

	/**
	 * Checking Constructor creates a Checking Account Object
	 * 
	 * @param holder        Profile with first and last name of account user
	 * @param balance       double that holds the Account balance
	 * @param dateOpen      date that the Account was created
	 * @param directDeposit boolean that determines whether the Checking Account has
	 *                      direct deposit
	 */
	public Checking(Profile holder, double balance, Date dateOpen, boolean directDeposit) {

		super(holder, balance, dateOpen);
		this.directDeposit = directDeposit;
	}

	/**
	 * Monthly Interest function calculates the monthly interest for the account
	 * 
	 * @return monthlyInterest returns the monthly interest for the account
	 */
	@Override
	public double monthlyInterest() {

		return this.getBalance() * (0.0005 / 12);

	}

	/**
	 * Monthly fee returns the fee amount for the month
	 * 
	 * @return fee for the month
	 */
	@Override
	public double monthlyFee() {

		if (this.isDirectDeposit() == true || this.getBalance() >= 1500) {
			return 0;
		}

		return 25;
	}

	/**
	 * Checks if the objects are both Checking Accounts and returns if they are
	 * equal
	 * 
	 * @param obj Object to be compared with Checking Account
	 * @return boolean, true if the Accounts equal each other, false if they do not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Checking) {
			Checking comparison = (Checking) obj;
			return this.getHolder().equals(comparison.getHolder());
		}
		return false;
	}

	/**
	 * Makes a String of the Checking Account
	 * 
	 * @return Checking Account as a string
	 */
	@Override
	public String toString() {

		if (this.isDirectDeposit() == true) {
			return "*Checking" + super.toString() + "*direct deposit account*";
		}

		return "*Checking" + super.toString();

	}
	
	/**
	 * Gets the instance of the Accounts directDeposit value
	 * 
	 * @return boolean directDeposit instance value
	 */
	public boolean isDirectDeposit() {
		return directDeposit;
	}

}
