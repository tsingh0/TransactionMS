package transaction;
import java.text.DecimalFormat;
/**
 * AccountDatabase Class performs operations to keep database of Accounts in check.
 * Has capabilities to add to the database, remove from the database, find an account within
 * the database, deposit money into an account, withdrawal from an account, and sort the accounts.
 * Account Database does the load of the work for keeping accounts in check.
 * @author Kacper Murdzek, Taranvir Singh
 *
 */
public class AccountDatabase {
	private Account[] accounts = new Account[5];
	private int size = 0;

	/**
	 * Find method searches the Account array for the matching Account,
	 * if the account is found it returns the index, if not found
	 * method returns -1.
	 * @param account to be found
	 * @return int associated with the index
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
	 * Grow method grows the Account array by 5.
	 */
	private void grow() {

		Account[] doubledArray = new Account[accounts.length + 5];
		for (int i = 0; i < accounts.length; i++) {
			doubledArray[i] = accounts[i];
		}
		accounts = doubledArray;

	}

	/**
	 * Add method adds an Account object to the Account Database array.
	 * @param account to be added
	 * @return if the account was added return true, if not then returns false
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
	 * Remove method removes an Account object from the Account Database array.
	 * @param account to be removed
	 * @return if the account was removed return true, if not then returns false
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
	 * Deposit method increases the balance in the account by specified value
	 * @param account to be deposited to
	 * @param amount to be deposited
	 * @return if the deposit is successful return true, if not then return false
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
	 * Withdrawal method decreases the balance in the account by specified value
	 * @param account to be withdrawal from
	 * @param amount to withdrawal
	 * @return if the withdrawal is successful return true, if not then return false
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
			return 0; // withdrawal successful
		}

	}

	/**
	 * SortByDateOpen method sorts the array of accounts using insertion sort by dates ranging from least recent to most recent
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
	 * SortByLastName method sorts the array of accounts using insertion sort by last name from lexicographically least to greatest order.
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
	 * PrintByDateOpen method prints the account array by the date
	 * that the accounts were opened least recent to most recent order.
	 */
	public void printByDateOpen() {

		DecimalFormat df = new DecimalFormat("#,###,##0.00");

		sortByDateOpen();

		if (accounts[0] == null) {
			System.out.println("Database is empty.");
		} else {

			System.out.println("\n--Printing statements by date opened--");

			for (int i = 0; i < accounts.length; i++) {
				if (accounts[i] != null) {

					System.out.println("\n" + accounts[i].toString());

					double monthInterest = accounts[i].monthlyInterest();

					accounts[i].setBalance(
							accounts[i].getBalance() + accounts[i].monthlyInterest() - accounts[i].monthlyFee());

					System.out.println(String.format("-interest: $ " + df.format(monthInterest)));
					System.out.println(String.format("-fee: $ " + df.format(accounts[i].monthlyFee())));
					System.out.println(String.format("-new balance: $ " + df.format(accounts[i].getBalance())));
				}
			}

			System.out.println("--end of printing--");

		}

	}

	/**
	 * PrintByLastName method prints the account array by last name from lexicographically least to greatest order.
	 */
	public void printByLastName() {

		DecimalFormat df = new DecimalFormat("#,###,##0.00");
		sortByLastName();

		if (accounts[0] == null) {
			System.out.println("Database is empty.");
		} else {

			System.out.println("\n--Printing statements by last name--");

			for (int i = 0; i < accounts.length; i++) {
				if (accounts[i] != null) {

					System.out.println("\n" + accounts[i].toString());

					double monthInterest = accounts[i].monthlyInterest();

					accounts[i].setBalance(
							accounts[i].getBalance() + accounts[i].monthlyInterest() - accounts[i].monthlyFee());

					System.out.println(String.format("-interest: $ " + df.format(monthInterest)));
					System.out.println(String.format("-fee: $ " + df.format(accounts[i].monthlyFee())));
					System.out.println(String.format("-new balance: $ " + df.format(accounts[i].getBalance())));
				}
			}

			System.out.println("--end of printing--");

		}

	}

	/**
	 * PrintAccounts methods prints the accounts within the AccountDatabase array
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

}
