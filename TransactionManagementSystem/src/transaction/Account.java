package transaction;

public abstract class Account {
	private Profile holder;
	private double balance;
	private Date dateOpen;

	public Account(Profile holder, double balance, Date dateOpen) {
		this.holder = holder;
		this.balance = balance;
		this.dateOpen = dateOpen;
	}

	// decrease the balance by amount
	public void debit(double amount) {

		this.balance = this.balance - amount;

	}

	public void credit(double amount) {

		this.balance = this.balance + amount;
	}

	public String toString() {
		return (String.format("*"+this.holder.toString() + "* $" + "%,.2f" + "*" + this.dateOpen.toString(),this.balance));
	}

	public abstract boolean equals(Object obj);
	
	public abstract double monthlyInterest();

	public abstract double monthlyFee();

	public Profile getHolder() {
		return holder;
	}

	public Date getDateOpen() {
		return dateOpen;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;

	}

}