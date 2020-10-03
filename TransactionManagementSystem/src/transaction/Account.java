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
	public void debit(double amount) {
		
	} //decrease the balance by amount 
	public void credit(double amount) { 
		
	}  
	public String toString() {
		return (this.holder.toString() + " " + this.balance + " " + this.dateOpen.toString());
	}
	
	public abstract boolean equals(Object obj);

	
	public abstract double monthlyInterest();
	public abstract double monthlyFee();
	
	public Profile getHolder() {
		return holder;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public Date getDateOpen() {
		return dateOpen;
	}

}