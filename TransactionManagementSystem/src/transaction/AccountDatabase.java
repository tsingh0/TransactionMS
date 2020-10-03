package transaction;

public class AccountDatabase {
	private Account[] accounts = new Account[5];
	private int size = 0;
	
	/**
	 * 
	 * @param account
	 * @return
	 */
	private int find(Account account) {
	
		return -1;
	}
	
	/**
	 * 
	 */
	private void grow() {
		
		Account[] doubledArray = new Account[accounts.length*2];
		for(int i = 0; i<accounts.length;i++) {
			doubledArray[i]= accounts[i];
		}
		accounts = doubledArray;
		
	}
	
	/**
	 * 
	 * @param account
	 * @return
	 */
	public boolean add(Account account) { 
		for(int i = 0; i < accounts.length; i++) {	
			if(accounts[i]!=null && accounts[i].equals(account)) {
				System.out.println("Account is already in the database.");
				break;
			}
			else if(accounts[i]==null) {
				accounts[i] = account;
				size++;
				System.out.println("Account opened and added to the database.");
				break;
			} 
		
		}
		if(size==accounts.length) {
			grow();
		}
	
		return false;
	}
	/**
	 * 
	 * @param account
	 * @return
	 */
	public boolean remove(Account account) { 
		return false;
	} 
	
	/**
	 * 
	 * @param account
	 * @param amount
	 * @return
	 */
	public boolean deposit(Account account, double amount) {
		return false;
	}
	
	/**
	 * 
	 * @param account
	 * @param amount
	 * @return
	 */
	public int withdrawal(Account account, double amount) {
		return size; 
	}
	/**
	 * 
	 */
	private void sortByDateOpen() {
		
	} 
	/**
	 * 
	 */
	private void sortByLastName() { 
		
	}
	/**
	 * 
	 */
	public void printByDateOpen() {
		
	}
	/**
	 * 
	 */
	public void printByLastName(){
		
	} 
	/**
	 * 
	 */
	public void printAccounts() {
			for(int i = 0; i< accounts.length; i++) {
				if(accounts[i]!=null) {
				System.out.println(accounts[i].toString());
				}
			}
		}
	
	
	} 
	

