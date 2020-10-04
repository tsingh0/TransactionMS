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
		
		for(int i=0; i<accounts.length;i++) {
			
			if(accounts[i].equals(account)) {
				return i;
			}
		
		}
	
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
		
		if (find(account) != -1) {

			accounts[find(account)] = accounts[size - 1];
			accounts[size - 1] = null;

			size--;

			return true;
		}

		return false;
	} 
	
	/**
	 * 
	 * @param account
	 * @param amount
	 * @return
	 */
	public boolean deposit(Account account, double amount) {
		
		int index = find(account);
		
		if(index!=-1) {
			accounts[index].setBalance(amount+ accounts[index].getBalance());
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param account
	 * @param amount
	 * @return
	 */
	public int withdrawal(Account account, double amount) {
		
		int index = find(account);
		
		if(index==-1) {
			return -1;  //account doesnt exist
		}
			
		else if(accounts[index].getBalance()-amount <0) {
			
			return 1;   //insufficient funds
		}else {
			
			accounts[index].setBalance(accounts[index].getBalance()-amount);
			return 0;   //withdrawl successful
			
		}
		
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
	
	//delete this after
	public void printArr() {
		for(int i = 0; i< accounts.length; i++) {
			if(accounts[i]!=null) {
			System.out.println(accounts[i].toString());
			}else {
				System.out.println(accounts[i]);
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("Working?");
		
		AccountDatabase test = new AccountDatabase();
		
		Checking c1 = new Checking(new Profile("Chandler","Bing"),100,new Date(15,11,1995),true);
		Checking c2 = new Checking(new Profile("Ross","Geller"),100,new Date(20,10,1997),true);
		Checking c3 = new Checking(new Profile("Monica","Bing"),100,new Date(15,11,1995),true);
		Checking c4 = new Checking(new Profile("Joey","Tribiani"),100,new Date(15,11,1995),true);
		Checking c5 = new Checking(new Profile("Rachel","Green"),100,new Date(15,11,1995),true);
		Saving s5 = new Saving(new Profile("Rachel","Green"),100,new Date(15,11,1995),true);
		
		
		
		test.add(c1);
		test.add(c2);
		test.add(c3);
		test.add(c4);
		test.add(c5);
		test.add(s5);

		
		test.printArr();
		
		System.out.println(test.find(s5));
		System.out.println(test.remove(c2));
		
		
		test.printArr();
		
		System.out.println(test.remove(new Saving(new Profile("Rachel","Green"),100,new Date(15,11,1995),true)));
		test.printArr();
		
		test.deposit(c1,100);
		
		test.printAccounts();
		
		test.withdrawal(c1,200);
		test.printAccounts();
		//delete this

	}
	
} 


	

