package transaction;

import java.io.File;
import java.text.DecimalFormat;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Transaction Manager handles the user inputs and is responsible for the the outputs of the program.
 * Transaction Manager has the ability to open a Checking, Saving, or Money Market account. As well as
 * close an existing account, deposit funds into an existing account, withdraw funds from an existing account,
 * and print the list of the accounts or account statements. The TransactionManager class reads input from 
 * a file that has the following commands.
 * 
 * To open an account: Command 'O' following the account type 'C' - checking 'S' - savings 'M' - money market
 * 
 * OC John Doe 500 1/1/2020 false //boolean whether or not checking account has direct deposit
 * OS John Doe 500 1/1/2020 true //boolean whether or not the user is loyal
 * OM John Doe 500 1/1/2020 
 * 
 * To close an account: Command 'C' following the account type 'C' - checking 'S' - savings 'M' - money market
 * CC John Doe
 * CS John Doe
 * CM John Doe
 * 
 * To deposit into an account: Command 'D' following the account type 'C' - checking 'S' - savings 'M' - money market
 * DC John Doe 100
 * DS John Doe 100
 * DM John Doe 100
 * 
 * To withdraw from an account: Command 'W' following the account type 'C' - checking 'S' - savings 'M' - money market
 * WC John Doe 100
 * WS John Doe 100
 * WM John Doe 100
 * 
 * 'P' commands print the list of accounts or print account statements
 * 'PA' - prints the list of accounts in the database
 * 'PD' - calculates the monthly interest with fees, and prints the account statements, sorted by 
 * 		  dates opened in ascending order.
 * 'PN' - same with 'PD', but sorted by last name in ascending order
 * 
 * 'Q' - stops program execution
 * 
 * Input file should be entered into the console after "Enter the input file :" is displayed , 
 * input file should be a ".txt" file in the project folder. "test1.txt" is the sample test file
 * provided in the project folder.
 * 
 * @author kacpermurdzek
 *
 */
public class TransactionManager {
	/**
	 * Run method is the program execution method.
	 */
	public static void run() {

		DecimalFormat df = new DecimalFormat("#,###,##0.00");
		AccountDatabase database = new AccountDatabase();

		System.out.println("Enter the input file : ");
		Scanner scanner = new Scanner(System.in);
		String file = scanner.next();
		scanner.close();
		try {
			File input = new File(file);
			Scanner fileScan = new Scanner(input);
			System.out.println("Transaction processing starts.....");

			while (fileScan.hasNext()) {
				try {
					String choice = fileScan.next();
					char first = choice.charAt(0);
					char second;
					Date dateCreated;

					switch (first) {
					case 'O':
						second = choice.charAt(1);
						switch (second) {
						case 'C':
							String fname = fileScan.next();
							String lname = fileScan.next();
							Profile newProfile = new Profile(fname, lname);

							double ammount = fileScan.nextDouble();

							String date = fileScan.next();
							dateCreated = makeDate(date);

							boolean directDeposit = fileScan.nextBoolean();

							if (dateCreated.isValid() == false) {
								System.out.println(dateCreated + " is not a valid date!");
								break;
							} else {
								database.add(new Checking(newProfile, ammount, dateCreated, directDeposit));
							}
							break;

						case 'S':
							String savingsFname = fileScan.next();
							String savingsLname = fileScan.next();
							Profile newSavingsProfile = new Profile(savingsFname, savingsLname);

							double savingsAmmount = fileScan.nextDouble();

							String savingsDate = fileScan.next();
							dateCreated = makeDate(savingsDate);

							boolean isLoyal = fileScan.nextBoolean();

							if (dateCreated.isValid() == false) {
								System.out.println(dateCreated + " is not a valid date!");
								break;
							} else {
								database.add(new Saving(newSavingsProfile, savingsAmmount, dateCreated, isLoyal));
							}
							break;
						case 'M':
							String moneyMarketFname = fileScan.next();
							String moneyMarketLname = fileScan.next();
							Profile newMoneyMarketProfile = new Profile(moneyMarketFname, moneyMarketLname);

							double moneyMarketAmmount = fileScan.nextDouble();

							String moneyMarketDate = fileScan.next();
							dateCreated = makeDate(moneyMarketDate);

							if (dateCreated.isValid() == false) {
								System.out.println(dateCreated + " is not a valid date!");
								break;
							} else {
								database.add(
										new MoneyMarket(newMoneyMarketProfile, moneyMarketAmmount, dateCreated, 0));
							}
							break;
						default:
							System.out.println("Command " + "'" + first + "" + second + "' not supported!");
							fileScan.nextLine();
						}

						break;
					case 'C':
						second = choice.charAt(1);
						switch (second) {
						case 'C':
							String checkingFname = fileScan.next();
							String checkingLname = fileScan.next();
							Profile remover = new Profile(checkingFname, checkingLname);
							boolean removeC = database.remove(new Checking(remover, 0.0, null, false));
							if (removeC == true) {
								System.out.println("Account closed and removed from the database.");
							} else {
								System.out.println("Account does not exist.");
							}
							break;
						case 'S':
							String savingFname = fileScan.next();
							String savingLname = fileScan.next();
							Profile savingRemover = new Profile(savingFname, savingLname);
							boolean removeS = database.remove(new Saving(savingRemover, 0.0, null, false));
							if (removeS == true) {
								System.out.println("Account closed and removed from the database.");
							} else {
								System.out.println("Account does not exist.");
							}
							break;
						case 'M':
							String moneyMarketFname = fileScan.next();
							String moneyMarketLname = fileScan.next();
							Profile moneyMarketRemover = new Profile(moneyMarketFname, moneyMarketLname);
							boolean removeM = database.remove(new MoneyMarket(moneyMarketRemover, 0.0, null, 0));
							if (removeM == true) {
								System.out.println("Account closed and removed from the database.");
							} else {
								System.out.println("Account does not exist.");
							}
							break;
						default:
							System.out.println("Command " + "'" + first + "" + second + "' not supported!");
							fileScan.nextLine();
						}

						break;
					case 'D':
						second = choice.charAt(1);
						switch (second) {
						case 'C':
							String checkingFname = fileScan.next();
							String checkingLname = fileScan.next();
							Profile depositor = new Profile(checkingFname, checkingLname);
							int checkingDepositAmount = fileScan.nextInt();
							boolean depositedC = database.deposit(new Checking(depositor, 0.0, null, false),
									checkingDepositAmount);
							if (depositedC != true) {
								System.out.println("Account does not exist.");
							} else {
								System.out.println(df.format(checkingDepositAmount) + " deposited to account.");
							}
							break;
						case 'S':
							String savingFname = fileScan.next();
							String savingLname = fileScan.next();
							Profile savingDepositor = new Profile(savingFname, savingLname);
							int savingDepositAmount = fileScan.nextInt();
							boolean depositedS = database.deposit(new Saving(savingDepositor, 0.0, null, false),
									savingDepositAmount);

							if (depositedS != true) {
								System.out.println("Account does not exist.");
							} else {
								System.out.println(df.format(savingDepositAmount) + " deposited to account.");

							}

							break;
						case 'M':
							String moneyMarketFname = fileScan.next();
							String moneyMarketLname = fileScan.next();
							Profile moneyMarketDepositor = new Profile(moneyMarketFname, moneyMarketLname);
							int moneyMarketDepositAmount = fileScan.nextInt();
							boolean depositedM = database.deposit(new MoneyMarket(moneyMarketDepositor, 0.0, null, 0),
									moneyMarketDepositAmount);

							if (depositedM != true) {
								System.out.println("Account does not exist.");
							} else {
								System.out.println(df.format(moneyMarketDepositAmount) + " deposited to account.");
							}
							break;
						default:
							System.out.println("Command " + "'" + first + "" + second + "' not supported!");
							fileScan.nextLine();
						}

						break;
					case 'W':
						second = choice.charAt(1);
						switch (second) {
						case 'C':
							String checkingFname = fileScan.next();
							String checkingLname = fileScan.next();
							Profile withdrawler = new Profile(checkingFname, checkingLname);
							int checkingWithdrawalAmount = fileScan.nextInt();
							int withdrawnC = database.withdrawal(new Checking(withdrawler, 0.0, null, false),
									checkingWithdrawalAmount);
							if (withdrawnC == -1) {
								System.out.println("Account does not exist.");
							} else if (withdrawnC == 1) {
								System.out.println("Insufficient funds.");
							} else {
								System.out.println(df.format(checkingWithdrawalAmount) + " withdrawn from account.");
							}
							break;
						case 'S':
							String savingFname = fileScan.next();
							String savingLname = fileScan.next();
							Profile savingWithdrawal = new Profile(savingFname, savingLname);
							int savingWithdrawalAmount = fileScan.nextInt();
							int withdrawnS = database.withdrawal(new Saving(savingWithdrawal, 0.0, null, false),
									savingWithdrawalAmount);
							if (withdrawnS == -1) {
								System.out.println("Account does not exist.");
							} else if (withdrawnS == 1) {
								System.out.println("Insufficient funds.");
							} else {
								System.out.println(df.format(savingWithdrawalAmount) + " withdrawn from account.");
							}

							break;
						case 'M':
							String moneyMarketFname = fileScan.next();
							String moneyMarketLname = fileScan.next();
							Profile moneyMarketWithdrawal = new Profile(moneyMarketFname, moneyMarketLname);
							int moneyMarketWithdrawalAmount = fileScan.nextInt();
							MoneyMarket temp = new MoneyMarket(moneyMarketWithdrawal, 0.0, null, 0);
							int withdrawnM = database.withdrawal(temp, moneyMarketWithdrawalAmount);

							if (withdrawnM == -1) {
								System.out.println("Account does not exist.");
							} else if (withdrawnM == 1) {
								System.out.println("Insufficient funds.");
							} else {

								System.out.println(df.format(moneyMarketWithdrawalAmount) + " withdrawn from account.");
							}

							break;
						default:
							System.out.println("Command " + "'" + first + "" + second + "' not supported!");
							fileScan.nextLine();
						}

						break;
					case 'P':
						second = choice.charAt(1);
						switch (second) {
						case 'A':
							database.printAccounts();
							break;
						case 'D':
							database.printByDateOpen();
							break;
						case 'N':
							database.printByLastName();
							break;
						default:
							System.out.println("Command " + "'" + first + "" + second + "' not supported!");
							fileScan.nextLine();
						}

						break;
					case 'Q':
						System.out.println("Transaction processing completed");

						break;
					default:
						if (choice.length() > 1) {
							second = choice.charAt(1);
							System.out.println("Command " + "'" + first + "" + second + "' not supported!");
						} else
							System.out.println("Command '" + first + "' not supported!");
						fileScan.nextLine();

					}
				} catch (InputMismatchException e) {
					System.out.println("Input data type mismatch.");
					fileScan.nextLine();
					continue;
				}

			}

			fileScan.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	/**
	 * MakeDate method creates a date object from a string
	 * and returns it.
	 * @param date string to be made a Date object
	 * @return date as a Date object
	 */
	private static Date makeDate(String date) {
		StringTokenizer dateToken = new StringTokenizer(date, "/", false);
		Date dateForm = null;
		while (dateToken.hasMoreTokens()) {
			String monthString = dateToken.nextToken();
			int month = Integer.parseInt(monthString);
			String dayString = dateToken.nextToken();
			int day = Integer.parseInt(dayString);
			String yearString = dateToken.nextToken();
			int year = Integer.parseInt(yearString);
			dateForm = new Date(month, day, year);
		}

		return dateForm;
	}

}
