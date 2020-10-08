package transaction;

/**
 * Abstract Class Account extends it's methods to Checking, Saving, and Money
 * Market Class. Account class is the root of the account types and has its own
 * constructor to create an account using a Profile, balance, and Date of
 * opening. Account class modifies the balance of any account object. Provides
 * the subclasses abstract methods for equals, monthlyInterest, and monthlyFee
 * 
 * @author Kacper Murdzek, Taranvir Singh
 *
 */
public abstract class Account {
	private Profile holder;
	private double balance;
	private Date dateOpen;

	/**
	 * Account constructor has capabilities of creating an account object, and
	 * extends its constructor class to Checking, Saving, and Money Market class.
	 * 
	 * @param holder   Account profile
	 * @param balance  Account balance amount
	 * @param dateOpen Account date open
	 */
	public Account(Profile holder, double balance, Date dateOpen) {
		this.holder = holder;
		this.balance = balance;
		this.dateOpen = dateOpen;
	}

	/**
	 * Debit method removes money from the Account instance balance
	 * 
	 * @param amount Amount being taken out of the account
	 */
	public void debit(double amount) {

		this.balance = this.balance - amount;

	}

	/**
	 * Credit method adds money to the Account instance balance
	 * 
	 * @param amount Amount being added to the account
	 */
	public void credit(double amount) {

		this.balance = this.balance + amount;
	}

	/**
	 * toString method formulates a String from the Account object
	 */
	public String toString() {
		return (String.format("*" + this.holder.toString() + "* $" + "%,.2f" + "*" + this.dateOpen.toString(),
				this.balance));
	}

	/**
	 * equals method is an abstract method that extends to the Account subclasses to
	 * test for equality of two Account objects
	 * 
	 * @return returns true if equal, false if not equal.
	 */
	public abstract boolean equals(Object obj);

	/**
	 * monthlyInterest method is an abstract method that extends to the Account
	 * subclasses and calculates the monthly interest
	 * 
	 * @return monthly interest amount
	 */
	public abstract double monthlyInterest();

	/**
	 * monthlyFee method is an abstract method that extends to the Account
	 * subclasses and calculates the monthly fee
	 * 
	 * @return monthly fee amount
	 */
	public abstract double monthlyFee();

	/**
	 * Getter method to get the account profile
	 * 
	 * @return profile associated with account
	 */
	public Profile getHolder() {
		return holder;
	}

	/**
	 * Getter method to get the account Date open
	 * 
	 * @return Date of account opening
	 */
	public Date getDateOpen() {
		return dateOpen;
	}

	/**
	 * Getter method to get the account balance
	 * 
	 * @return balance of the account
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Setter method to set the balance of an account
	 * 
	 * @param balance to be set
	 */

	public void setBalance(double balance) {
		this.balance = balance;

	}

}