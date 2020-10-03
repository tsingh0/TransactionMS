package transaction;

public class AccountDatabase {
	private Account[] accounts;
	private int size;
	private int find(Account account) {
		return -1;
	} private void grow() { }
	public boolean add(Account account) { 
		return false;
	}
	public boolean remove(Account account) { 
		return false;
	} //return false if account doesn’t exist 
	public boolean deposit(Account account, double amount) {
		return false;
	}
	//return 0: withdrawal successful, 1: insufficient funds, -1 account doesn’t exist 
	public int withdrawal(Account account, double amount) {
		return size; 
		}
	private void sortByDateOpen() {
		
	} 
	private void sortByLastName() { 
		
	}
		public void printByDateOpen() { }
		public void printByLastName(){} 
		public void printAccounts() { }
	
	
	} 
	

