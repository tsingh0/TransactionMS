package transaction;

import java.text.DecimalFormat;

/**
 * AccountDatabase Class performs operations to keep database of Accounts in
 * check. Has capabilities to add to the database, remove from the database,
 * find an account within the database, deposit money into an account,
 * withdrawal from an account, and sort the accounts. Account Database does the
 * load of the work for keeping accounts in check.
 * 
 * @author Kacper Murdzek, Taranvir Singh
 *
 */
public class AccountDatabase {
	private Account[] accounts = new Account[5];
	private int size = 0;

	/**
	 * Find method searches the Account array for the matching Account, if the
	 * account is found it returns the index, if not found method returns -1.
	 * 
	 * @param account to be found
	 * @return int associated with the index
	 */
	private int find(Account account) {

		for (int i = 0; i < getAccounts().length; i++) {

			if (getAccounts()[i] != null) {

				if (getAccounts()[i].equals(account)) {
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

		Account[] doubledArray = new Account[getAccounts().length + 5];
		for (int i = 0; i < getAccounts().length; i++) {
			doubledArray[i] = getAccounts()[i];
		}
		accounts = doubledArray;

	}

	/**
	 * Add method adds an Account object to the Account Database array.
	 * 
	 * @param account to be added
	 * @return if the account was added return true, if not then returns false
	 */
	public boolean add(Account account) {

		for (int i = 0; i < getAccounts().length; i++) {
			if (getAccounts()[i] != null && getAccounts()[i].equals(account)) {
				return false;
			} else if (getAccounts()[i] == null) {
				getAccounts()[i] = account;
				size = getSize() + 1;
				break;
			}

		}
		if (getSize() == getAccounts().length) {
			grow();
		}

		return true;
	}

	/**
	 * Remove method removes an Account object from the Account Database array.
	 * 
	 * @param account to be removed
	 * @return if the account was removed return true, if not then returns false
	 */
	public boolean remove(Account account) {

		if (find(account) != -1) {

			getAccounts()[find(account)] = getAccounts()[getSize() - 1];
			getAccounts()[getSize() - 1] = null;

			size = getSize() - 1;

			return true;
		}

		return false;
	}

	/**
	 * Deposit method increases the balance in the account by specified value
	 * 
	 * @param account to be deposited to
	 * @param amount  to be deposited
	 * @return if the deposit is successful return true, if not then return false
	 */
	public boolean deposit(Account account, double amount) {

		int index = find(account);

		if (index != -1) {
			getAccounts()[index].credit(amount);
			return true;
		}

		return false;
	}

	/**
	 * Withdrawal method decreases the balance in the account by specified value
	 * 
	 * @param account to be withdrawal from
	 * @param amount  to withdrawal
	 * @return if the withdrawal is successful return true, if not then return false
	 */
	public int withdrawal(Account account, double amount) {

		int index = find(account);

		if (index == -1) {
			return -1; // account doesn't exist
		}

		else if (getAccounts()[index].getBalance() - amount < 0) {

			return 1; // insufficient funds
		} else {

			if (getAccounts()[index] instanceof MoneyMarket) {
				MoneyMarket temp = (MoneyMarket) getAccounts()[index];
				temp.setWithdrawals(1);
			}

			getAccounts()[index].debit(amount);
			return 0; // withdrawal successful
		}

	}

	/**
	 * SortByDateOpen method sorts the array of accounts using insertion sort by
	 * dates ranging from least recent to most recent
	 */
	private void sortByDateOpen() {

		for (int i = 1; i < getAccounts().length; ++i) {
			Account key = getAccounts()[i];
			int sorted = i - 1;
			if (key != null) {
				while (sorted >= 0 && key.getDateOpen().compareTo(getAccounts()[sorted].getDateOpen()) < 0) {
					getAccounts()[sorted + 1] = getAccounts()[sorted];
					sorted = sorted - 1;
				}
				getAccounts()[sorted + 1] = key;
			}
		}

	}

	/**
	 * SortByLastName method sorts the array of accounts using insertion sort by
	 * last name from lexicographically least to greatest order.
	 */
	private void sortByLastName() {
		for (int i = 1; i < getAccounts().length; ++i) {
			Account key = getAccounts()[i];
			int sorted = i - 1;
			if (key != null) {
				while (sorted >= 0
						&& key.getHolder().getLname().compareTo(getAccounts()[sorted].getHolder().getLname()) < 0) {
					getAccounts()[sorted + 1] = getAccounts()[sorted];
					sorted = sorted - 1;
				}
				getAccounts()[sorted + 1] = key;
			}
		}
	}

	/**
	 * PrintByDateOpen method prints the account array by the date that the accounts
	 * were opened least recent to most recent order.
	 */
	public String printByDateOpen() {
		String output = "";
		DecimalFormat df = new DecimalFormat("#,###,##0.00");

		sortByDateOpen();

		if (getAccounts()[0] == null) {
			output += "Database is empty.\n";
		} else {

			output += ("\n--Printing statements by date opened--\n");

			for (int i = 0; i < getAccounts().length; i++) {
				if (getAccounts()[i] != null) {

					output += "\n" + getAccounts()[i].toString();

					double monthInterest = getAccounts()[i].monthlyInterest();

					getAccounts()[i].setBalance(getAccounts()[i].getBalance() + getAccounts()[i].monthlyInterest()
							- getAccounts()[i].monthlyFee());

					output += (String.format("\n-interest: $ " + df.format(monthInterest)));
					output += (String.format("\n-fee: $ " + df.format(getAccounts()[i].monthlyFee())));
					output += (String.format("\n-new balance: $ " + df.format(getAccounts()[i].getBalance())) + "\n");
				}
			}

			output += ("--end of printing--\n");

		}

		return output;
	}

	/**
	 * PrintByLastName method prints the account array by last name from
	 * lexicographically least to greatest order.
	 */
	public String printByLastName() {

		String output = "";
		DecimalFormat df = new DecimalFormat("#,###,##0.00");
		sortByLastName();

		if (getAccounts()[0] == null) {
			output += "Database is empty.\n";
		} else {

			output += "\n--Printing statements by last name--\n";

			for (int i = 0; i < getAccounts().length; i++) {
				if (getAccounts()[i] != null) {

					output += "\n" + getAccounts()[i].toString();

					double monthInterest = getAccounts()[i].monthlyInterest();

					getAccounts()[i].setBalance(getAccounts()[i].getBalance() + getAccounts()[i].monthlyInterest()
							- getAccounts()[i].monthlyFee());

					output += (String.format("\n-interest: $ " + df.format(monthInterest)));
					output += (String.format("\n-fee: $ " + df.format(getAccounts()[i].monthlyFee())));
					output += (String.format("\n-new balance: $ " + df.format(getAccounts()[i].getBalance())) + "\n");
				}
			}

			output += "--end of printing--\n";

		}

		return output;

	}

	/**
	 * PrintAccounts methods prints the accounts within the AccountDatabase array
	 */
	public String printAccounts() {
		String output = "";
		if (getAccounts()[0] == null) {
			output += "Database is empty.\n";
		} else {

			output += "--Listing accounts in the database--\n";

			for (int i = 0; i < getAccounts().length; i++) {
				if (getAccounts()[i] != null) {
					output += getAccounts()[i].toString() + "\n";
				}
			}
			output += "--end of listing--\n";
		}

		return output;
	}

	/**
	 * Gets the amount of accounts in the AccountDatabase
	 * 
	 * @return int size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Gets the accounts in the database
	 * 
	 * @return array of the accounts
	 */
	public Account[] getAccounts() {
		return accounts;
	}

}
