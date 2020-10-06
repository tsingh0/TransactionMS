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

		for (int i = 0; i < accounts.length; i++) {

			if (accounts[i] != null) {

				if (accounts[i].equals(account)) {
					return i;
				}
			}

		}

		return -1;
	}

	/**
	 * 
	 */
	private void grow() {

		Account[] doubledArray = new Account[accounts.length * 2];
		for (int i = 0; i < accounts.length; i++) {
			doubledArray[i] = accounts[i];
		}
		accounts = doubledArray;

	}

	/**
	 * 
	 * @param account
	 * @return
	 */
	public boolean add(Account account) {
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i] != null && accounts[i].equals(account)) {
				System.out.println("Account is already in the database.");
				break;
			} else if (accounts[i] == null) {
				accounts[i] = account;
				size++;
				System.out.println("Account opened and added to the database.");
				break;
			}

		}
		if (size == accounts.length) {
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

		if (index != -1) {
			accounts[index].credit(amount);
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

		if (index == -1) {
			return -1; // account doesn't exist
		}

		else if (accounts[index].getBalance() - amount < 0) {

			return 1; // insufficient funds
		} else {

			if (accounts[index] instanceof MoneyMarket) {
				MoneyMarket temp = (MoneyMarket) accounts[index];
				temp.setWithdrawals(1);
			}

			accounts[index].debit(amount);
			return 0; // withdrawl successful
		}

	}

	/**
	 * 
	 */
	private void sortByDateOpen() {

		for (int i = 1; i < accounts.length; ++i) {
			Account key = accounts[i];
			int sorted = i - 1;
			if (key != null) {
				while (sorted >= 0 && key.getDateOpen().compareTo(accounts[sorted].getDateOpen()) < 0) {
					accounts[sorted + 1] = accounts[sorted];
					sorted = sorted - 1;
				}
				accounts[sorted + 1] = key;
			}
		}

	}

	/**
	 * 
	 */
	private void sortByLastName() {
		for (int i = 1; i < accounts.length; ++i) {
			Account key = accounts[i];
			int sorted = i - 1;
			if (key != null) {
				while (sorted >= 0
						&& key.getHolder().getLname().compareTo(accounts[sorted].getHolder().getLname()) < 0) {
					accounts[sorted + 1] = accounts[sorted];
					sorted = sorted - 1;
				}
				accounts[sorted + 1] = key;
			}
		}
	}

	/**
	 * 
	 */
	public void printByDateOpen() {

		sortByDateOpen();

		if (accounts[0] == null) {
			System.out.println("Database is empty.");
		} else {

			System.out.println("\n--Printing statments by last name--");

			for (int i = 0; i < accounts.length; i++) {
				if (accounts[i] != null) {
					double balance = (accounts[i].getBalance() + accounts[i].monthlyInterest()
							- accounts[i].monthlyFee());

					System.out.println("\n"+accounts[i].toString());
					System.out.printf("-interest: $ %.2f", accounts[i].monthlyInterest());
					System.out.printf("\n-fee: $ %.2f", accounts[i].monthlyFee());
					System.out.printf("\n-new balance: $ %.2f\n", balance);
				}
			}

			System.out.println("--end of printing--");

		}

	}

	/**
	 * 
	 */
	public void printByLastName() {

		sortByLastName();

		if (accounts[0] == null) {
			System.out.println("Database is empty.");
		} else {

			System.out.println("\n--Printing statments by last name--");

			for (int i = 0; i < accounts.length; i++) {
				if (accounts[i] != null) {
					double balance = (accounts[i].getBalance() + accounts[i].monthlyInterest()
							- accounts[i].monthlyFee());

					System.out.println("\n"+accounts[i].toString());
					System.out.printf("-interest: $ %.2f", accounts[i].monthlyInterest());
					System.out.printf("\n-fee: $ %.2f", accounts[i].monthlyFee());
					System.out.printf("\n-new balance: $ %.2f\n", balance);
				}
			}

			System.out.println("--end of printing--");

		}

	}

	/**
	 * 
	 */
	public void printAccounts() {

		if (accounts[0] == null) {
			System.out.println("Database is empty.");
		} else {

			System.out.println("--Listing accounts in the database--");

			for (int i = 0; i < accounts.length; i++) {
				if (accounts[i] != null) {
					System.out.println(accounts[i].toString());
				}
			}
			System.out.println("--end of listing--");
		}
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * AccountDatabase test = new AccountDatabase();
	 * 
	 * Checking c1 = new Checking(new Profile("Chandler", "Bing"), 100, new Date(15,
	 * 1, 1985), true); Checking c2 = new Checking(new Profile("Ross", "Geller"),
	 * 100, new Date(20, 10, 1997), true); Checking c3 = new Checking(new
	 * Profile("Monica", "Bing"), 100, new Date(15, 11, 1966), true); Checking c4 =
	 * new Checking(new Profile("Joey", "Tribiani"), 100, new Date(15, 12, 1995),
	 * true); Checking c5 = new Checking(new Profile("Rachel", "Green"), 100, new
	 * Date(15, 11, 1995), true); Saving s5 = new Saving(new Profile("Rachel",
	 * "Green"), 100, new Date(15, 11, 1995), true);
	 * 
	 * test.add(c1); test.add(c2); test.add(c3); test.add(c4); test.add(c5);
	 * test.add(s5);
	 * 
	 * test.printAccounts(); System.out.println();
	 * 
	 * test.deposit(c1, 100); test.withdrawal(c2, 100); test.withdrawal(c3, 50);
	 * test.withdrawal(c4, 200);
	 * 
	 * test.printAccounts(); }
	 */

}
