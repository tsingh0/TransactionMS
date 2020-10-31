package transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * SampleController Class handles the GUI functionality of the Transaction Manager.
 * The class handles any event that is done on the GUI, as well as creates accounts,
 * removes accounts,deposits/withdrawals to an account, imports/ exports databases,
 * and prints the account database. Sample controller class throws alerts if there
 * is missing data and also appends confirmation of account actions in the output text
 * area on the bottom of the GUI.
 * 
 * @author Kacper Murdzek, Taranvir Singh
 *
 */
public class SampleController {

	DecimalFormat df = new DecimalFormat("#,###,##0.00");
	
	private AccountDatabase database = new AccountDatabase();
	@FXML
	private RadioButton checking, savings, moneyMarket, checkings2, savings2, moneyMarket2;

	@FXML
	private CheckBox loyalCustomer, directDeposit;

	@FXML
	private Button createAccount, deleteAccount, importDatabase, exportDatabase;

	@FXML
	private ToggleGroup toggleGroup11, toggleGroup1, toggleGroup111;

	@FXML
	private TextArea output;

	@FXML
	private TextField firstName, lastName, amount, balanceDeposit, balance, fName, lName, year, day, month;

	@FXML
	private MenuItem printLast, printAccounts;

	@FXML
	private Tooltip tooltip;

	/**
	 * Creates an Account dependent on user input on ActionEvent
	 * When a user inputs account information, AccountCreator method
	 * takes the information and makes an account. If the information
	 * provided is invalid, an alert window will pop up. Otherwise,
	 * the output text area will confirm account creation.
	 * 
	 * @param event button click
	 */
	@FXML
	void AccountCreator(ActionEvent event) {
		if (typedMonth() != -1 && typedDay() != -1 && typedYear() != -1 && typedBalance() != -1
				&& (typedFname().isEmpty() == false && typedLname().isEmpty() == false)) {
			if (checkedCheckingAccount()) {
				Profile setup = new Profile(typedFname(), typedLname());
				Date date = new Date(typedMonth(), typedDay(), typedYear());
				if (date.isValid() && typedBalance() >= 0) {
					Checking acc = new Checking(setup, typedBalance(), date, directDepositChecked());
					if (database.add(acc) == true) {
						output.appendText("Account opened and added to the database\n");
					} else {
						output.appendText("Account is already in the database.\n");
					}
				} else {
					output.appendText(
							month.getText() + "/" + day.getText() + "/" + year.getText() + " is not a valid date!\n");

				}

			} else if (checkedSavingsAccount()) {
				Profile setup = new Profile(typedFname(), typedLname());
				Date date = new Date(typedMonth(), typedDay(), typedYear());
				if (date.isValid() && typedBalance() >= 0) {
					Saving acc = new Saving(setup, typedBalance(), date, loyalCustomerChecked());
					if (database.add(acc) == true) {
						output.appendText("Account opened and added to the database\n");
					} else {
						output.appendText("Account is already in the database.\n");
					}
					
				} else {
					output.appendText(
							month.getText() + "/" + day.getText() + "/" + year.getText() + " is not a valid date!\n");
				}

			} else if (checkedMoneyMarketAccount()) {
				Profile setup = new Profile(typedFname(), typedLname());
				Date date = new Date(typedMonth(), typedDay(), typedYear());
				if (date.isValid() && typedBalance() >= 0) {
					MoneyMarket acc = new MoneyMarket(setup, typedBalance(), date, 0);
					if (database.add(acc)) {
						output.appendText("Account opened and added to the database\n");
					} else {
						output.appendText("Account is already in the database.\n");
					}
				} else {
					output.appendText(
							month.getText() + "/" + day.getText() + "/" + year.getText() + " is not a valid date!\n");
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Please select an account type.");
				alert.showAndWait();
			}
		} else {
			if (typedFname().isEmpty() == true || typedLname().isEmpty() == true) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Some fields are empty");
				alert.setContentText("Please enter your first and last name");
				alert.showAndWait();
			}

			if (typedMonth() == -1 || typedDay() == -1 || typedYear() == -1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Non numeric data has been entered in the Date field.");
				alert.setContentText("Please enter a number.");
				alert.showAndWait();
			}

			if (typedBalance() == -1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Non numeric data has been entered for field Balance.");
				alert.setContentText("Please enter a number.");
				alert.showAndWait();
			}
		}
	}

	/**
	 * Deletes an account dependent on the first and last name of the user as well as account type.
	 * AccountDeleter takes the input provided by the user to delete an account, any additional
	 * data is discarded.
	 * 
	 * @param event button click
	 */
	@FXML
	void AccountDeleter(ActionEvent event) {
		if (typedFname().isEmpty() != true && typedLname().isEmpty() != true) {
			Profile setup = new Profile(typedFname(), typedLname());
			// boolean removedFromDatabase = false;
			if (checkedCheckingAccount()) {
				if (database.remove(new Checking(setup, 0.0, new Date(0, 0, 0), false)) == true) {
					output.appendText("Account closed and removed from the database.\n");
				} else {
					output.appendText("Account does not exist.\n");
				}

			} else if (checkedMoneyMarketAccount()) {
				if (database.remove(new MoneyMarket(setup, 0.0, new Date(0, 0, 0), 0)) == true) {
					output.appendText("Account closed and removed from the database.\n");
				} else {
					output.appendText("Account does not exist.\n");
				}

			} else if (checkedSavingsAccount()) {
				if (database.remove(new Saving(setup, 0.0, new Date(0, 0, 0), false)) == true) {
					output.appendText("Account closed and removed from the database.\n");
				} else {
					output.appendText("Account does not exist.\n");
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Please select an account type.");
				// alert.setContentText("Please enter a number.");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Some fields are empty");
			alert.setContentText("Please enter your first and last name");
			alert.showAndWait();
		}
	}

	/**
	 * Imports accounts from a ".txt" file based on user file selection.
	 * If the input is invalid the account is not created,
	 * otherwise accounts are properly created and stored in
	 * the database.
	 * 
	 * @param event button clicked
	 */
	@FXML
	void importDatabaseFromFile(ActionEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Source File for the Import");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		try {
			File sourceFile = fileChooser.showOpenDialog(stage);
			Scanner scanner = new Scanner(sourceFile);
			try {

				scanner.useDelimiter("\\s*,|\\R\\s*");

				while (scanner.hasNext()) {
					String choice = scanner.next();
					Date dateCreated;

					switch (choice) {
					case "C":
						String fname = scanner.next();
						String lname = scanner.next();
						Profile newProfile = new Profile(fname, lname);
						double ammount = scanner.nextDouble();
						String date = scanner.next();
						dateCreated = makeDate(date);
						boolean directDeposit = scanner.nextBoolean();

						if (dateCreated.isValid() == false) {
							break;
						} else {
							database.add(new Checking(newProfile, ammount, dateCreated, directDeposit));
						}
						break;

					case "S":
						String savingsFname = scanner.next();
						String savingsLname = scanner.next();
						Profile newSavingsProfile = new Profile(savingsFname, savingsLname);
						double savingsAmmount = scanner.nextDouble();
						String savingsDate = scanner.next();
						dateCreated = makeDate(savingsDate);

						boolean isLoyal = scanner.nextBoolean();
						if (dateCreated.isValid() == false) {
							break;
							
						} else {
							database.add(new Saving(newSavingsProfile, savingsAmmount, dateCreated, isLoyal));

						}
						break;
					case "M":
						String moneyMarketFname = scanner.next();
						String moneyMarketLname = scanner.next();
						Profile newMoneyMarketProfile = new Profile(moneyMarketFname, moneyMarketLname);
						double moneyMarketAmmount = scanner.nextDouble();
						String moneyMarketDate = scanner.next();
						dateCreated = makeDate(moneyMarketDate);
						int withdrawals = scanner.nextInt();
						if (dateCreated.isValid() == false) {
							break;
						} else {
							  database.add(new MoneyMarket(newMoneyMarketProfile, moneyMarketAmmount,dateCreated, withdrawals));
							scanner.nextLine();
						}
						break;
						
					default:
						scanner.nextLine();
					}

				}
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Success");
				alert.setHeaderText("File was successfully imported.");
				alert.setContentText("Congratulations!");
				alert.showAndWait();
				scanner.close();
			} catch (InputMismatchException e) {
				scanner.close();
			}

		} catch (NullPointerException e) {

			
		} catch (FileNotFoundException e1) {

			
		}

	}
	/**
	 * Exports a database to a file in the proper format based on user file designation.
	 * Takes the accounts in the database and formats them and outputs to a .txt file.
	 * 
	 * @param event button clicked
	 */
	@FXML
	void exportDatabaseToFile(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Source File for the Import");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File targetFile = chooser.showSaveDialog(stage);
		FileWriter document;
		try {
			document = new FileWriter(targetFile, true);
			Account[] accounts = database.getAccounts();

			for (int i = 0; i < database.getSize(); i++) {

				if (accounts[i] instanceof Checking) {
					Checking toWrite = (Checking) accounts[i];
					document.write("C" + "," + toWrite.getHolder().getFname() +"," + toWrite.getHolder().getLname() + "," + toWrite.getBalance() + ","
							+ toWrite.getDateOpen() + "," + toWrite.isDirectDeposit());

				} else if (accounts[i] instanceof Saving) {
					Saving toWrite = (Saving) accounts[i];
					document.write("S" + "," + toWrite.getHolder().getFname() + "," + toWrite.getHolder().getLname() + "," + toWrite.getBalance() + ","
							+ toWrite.getDateOpen() + "," + toWrite.isLoyal());

				} else if (accounts[i] instanceof MoneyMarket) {
					MoneyMarket toWrite = (MoneyMarket) accounts[i];
					document.write("M" + "," +toWrite.getHolder().getFname() + "," + toWrite.getHolder().getLname() + "," + toWrite.getBalance() + ","
							+ toWrite.getDateOpen() + "," + toWrite.getWithdrawals());
				}
				document.write("\n");
			}
			document.flush();
			document.close();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Success");
			alert.setHeaderText("File was successfully exported.");
			alert.setContentText("Congratulations!");
			alert.showAndWait();
		
		} catch (IOException e) {

		}
		catch (NullPointerException e) {

		}

	}
	/**
	 * Deposit is made to specified account based on user input.
	 * The input provided by the user is taken and used to match
	 * the account in the database to perform a deposit.
	 * 
	 * @param event button click
	 */
	@FXML
	void depositMaker(ActionEvent event) {
		if (typedAmount() != -1 && (firstName.getText().isEmpty() != true && lastName.getText().isEmpty() != true)) {
			Profile depositor = new Profile(firstName.getText(), lastName.getText());
			int depositAmount = Integer.parseInt(amount.getText());
			if (checkedCheckingAccount2()) {
				boolean deposited = database.deposit(new Checking(depositor, 0.0, null, false), depositAmount);
				if (deposited != true) {
					output.appendText("Account does not exist.\n");
				} else {
					output.appendText(df.format(depositAmount) + " deposited to account.\n");
				}
			} else if (checkedSavingsAccount2()) {
				boolean deposited = database.deposit(new Saving(depositor, 0.0, null, false), depositAmount);
				if (deposited != true) {
					output.appendText("Account does not exist.\n");
				} else {
					output.appendText(df.format(depositAmount) + " deposited to account.\n");
				}

			} else if (checkedMoneyMarketAccount2()) {
				boolean deposited = database.deposit(new MoneyMarket(depositor, 0.0, null, 0), depositAmount);
				if (deposited != true) {
					output.appendText("Account does not exist.\n");
				} else {
					output.appendText(df.format(depositAmount) + " deposited to account.\n");
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Please select an account type.");
				alert.showAndWait();
			}
		} else {
			if (firstName.getText().isEmpty() == true || lastName.getText().isEmpty() == true) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Fields are empty");
				alert.setContentText("Please fill in all fields");
				alert.showAndWait();
			}
			if (typedAmount() == -1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Non numeric data has been entered for field Amount.");
				alert.setContentText("Please enter a number.");
				alert.showAndWait();
			}
		}
	}

	/**
	 * Withdrawal is made to specified account based on user input.
	 * The input provided by the user is taken and used to match
	 * the account in the database to perform a withdrawal.
	 * 
	 * @param event button click
	 */
	@FXML
	void withdrawalMaker(ActionEvent event) {
		if (typedAmount() != -1 && firstName.getText().isEmpty() != true && lastName.getText().isEmpty() != true) {
			Profile withdrawler = new Profile(firstName.getText(), lastName.getText());
			int withdrawalAmount = Integer.parseInt(amount.getText());
			if (checkedCheckingAccount2()) {
				int withdrawn = database.withdrawal(new Checking(withdrawler, 0.0, null, false), withdrawalAmount);
				if (withdrawn == -1) {
					output.appendText("Account does not exist.\n");
				} else if (withdrawn == 1) {
					output.appendText("Insufficient funds.\n");
				} else {
					output.appendText(df.format(withdrawalAmount) + " withdrawn from account.\n");
				}

			} else if (checkedSavingsAccount2()) {
				int withdrawn = database.withdrawal(new Saving(withdrawler, 0.0, null, false), withdrawalAmount);
				if (withdrawn == -1) {
					output.appendText("Account does not exist.\n");
				} else if (withdrawn == 1) {
					output.appendText("Insufficient funds.\n");
				} else {
					output.appendText(df.format(withdrawalAmount) + " withdrawn from account.\n");
				}
			} else if (checkedMoneyMarketAccount2()) {
				int withdrawn = database.withdrawal(new MoneyMarket(withdrawler, 0.0, null, 0), withdrawalAmount);
				if (withdrawn == -1) {
					output.appendText("Account does not exist.\n");
				} else if (withdrawn == 1) {
					output.appendText("Insufficient funds.\n");
				} else {
					output.appendText(df.format(withdrawalAmount) + " withdrawn from account.\n");
				}

			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Please select an account type.");
				alert.showAndWait();
			}
		} else {
			if (firstName.getText().isEmpty() == true || lastName.getText().isEmpty() == true) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Some fields are empty");
				alert.setContentText("Please enter your first and last name");
				alert.showAndWait();
			}
			if (typedAmount() == -1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Non numeric data has been entered for field Amount.");
				alert.setContentText("Please enter a number.");
				alert.showAndWait();
			}
		}

	}

	/**
	 * MakeDate method creates a date object from a string and returns it.
	 * 
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

	/**
	 * Clears the output text area on button clicked.
	 * 
	 * @param event button click
	 */
	@FXML
	void clearTextArea(ActionEvent event) {
		output.clear();
	}
	
	/**
	 * Checks if the checking radio button is checked on the Create/Delete Account tab.
	 * 
	 * @return true or false dependent on if the radio button is checked.
	 */
	@FXML
	boolean checkedCheckingAccount() {
		if (checking.isSelected()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the checking radio button is checked on the Deposit/Withdrawal tab.
	 * 
	 * @return true or false dependent on if the radio button is checked.
	 */
	@FXML
	boolean checkedCheckingAccount2() {
		if (checkings2.isSelected()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the savings radio button is checked on the Create/Delete Account tab.
	 * 
	 * @return true or false dependent on if the radio button is checked.
	 */
	@FXML
	boolean checkedSavingsAccount() {
		if (savings.isSelected()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the savings radio button is checked on the Deposit/Withdrawal tab.
	 * 
	 * @return true or false dependent on if the radio button is checked.
	 */
	@FXML
	boolean checkedSavingsAccount2() {
		if (savings2.isSelected()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the money market radio button is checked on the Create/Delete Account tab.
	 * 
	 * @return true or false dependent on if the radio button is checked.
	 */
	@FXML
	boolean checkedMoneyMarketAccount() {
		if (moneyMarket.isSelected()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the money market radio button is checked on the Deposit/Withdrawal tab.
	 * 
	 * @return true or false dependent on if the radio button is checked.
	 */
	@FXML
	boolean checkedMoneyMarketAccount2() {
		if (moneyMarket2.isSelected()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the direct deposit button is checked.
	 * 
	 * @return true or false dependent on if the button is checked.
	 */
	@FXML
	boolean directDepositChecked() {
		if (directDeposit.isSelected()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the loyal customer button is checked.
	 * 
	 * @return true or false dependent on if the button is checked.
	 */
	@FXML
	boolean loyalCustomerChecked() {
		if (loyalCustomer.isSelected()) {
			return true;
		}
		return false;
	}
	
	/**
	 * When checking button is selected, unselects the loyal customer button and makes it not selectable.
	 * 
	 * @param event button clicked
	 */
	@FXML
	void checkedCheck(ActionEvent event) {
		
		loyalCustomer.setDisable(true);
		loyalCustomer.setSelected(false);
		directDeposit.setDisable(false);

	}
	/**
	 * When savings button is selected, unselects the direct deposit button and makes it not selectable.
	 * 
	 * @param event button clicked
	 */
	@FXML
	void checkedSavings(ActionEvent event) {
		
		loyalCustomer.setDisable(false);
		directDeposit.setDisable(true);
		directDeposit.setSelected(false);

	}
	/**
	 * When money market button is selected, unselects the direct deposit and loyal customer button and makes them not selectable.
	 * @param event button clicked
	 */
	@FXML
	void checkedMoneyMarket(ActionEvent event) {
		loyalCustomer.setDisable(true);
		loyalCustomer.setSelected(false);
		directDeposit.setDisable(true);
		directDeposit.setSelected(false);
	}
	
	/**
	 * Clears all the field on the GUI.
	 */
	@FXML
	void clear() {
		fName.clear();
		lName.clear();
		month.clear();
		day.clear();
		year.clear();
		balance.clear();
	}
	/**
	 * When print by last name option is clicked, the account database is printed to the output text area sorted by last name. 
	 * @param event button click
	 */
	@FXML
	void printLastName(ActionEvent event) {
		output.appendText(database.printByLastName());
	}

	/**
	 * When print database option is clicked, the account database is printed to the output text area.
	 * 
	 * @param event button clicked
	 */
	@FXML
	void printDatabase(ActionEvent event) {
		output.appendText(database.printAccounts());
	}
	/**
	 * When print by date option is clicked, the account database is printed to the output text area sorted by date opened.
	 * 
	 * @param event button clicked
	 */
	@FXML
	void printByDate(ActionEvent event) {
		output.appendText(database.printByDateOpen());
	}
	
	/**
	 * Shows what the button does when mouse hovers the button.
	 * 
	 * @param event button hover
	 */
	@FXML
	void setToolTime(MouseEvent event) {
		tooltip.setShowDelay(Duration.seconds(0));
	}
	
	/**
	 * 
	 * @param event
	 */
	@FXML
	void selectText(MouseEvent event) {
		fName.selectAll();
		lName.selectAll();
		month.selectAll();
		day.selectAll();
		year.selectAll();
		balance.selectAll();
		firstName.selectAll();
		lastName.selectAll();
		amount.selectAll();
	}

	/**
	 * Gets the String typed in the fname field.
	 * 
	 * @return string first name
	 */
	@FXML
	String typedFname() {
		return fName.getText().trim();
	}
	/**
	 * Gets the String typed in the lname field.
	 * 
	 * @return string last name
	 */
	@FXML
	String typedLname() {
		return lName.getText().trim();
	}
	
	/**
	 * Gets the double typed in the balance field.
	 * 
	 * @return double balance or -1 if balance is invalid.
	 */
	@FXML
	double typedBalance() {
		try {
			double inputBalance = Double.parseDouble(balance.getText());
			if (inputBalance < 0) {
				return -1;
			}
			return inputBalance;
		} catch (NumberFormatException e) {

			return -1;
		}

	}
	
	/**
	 * Gets the double typed in the amount field.
	 * 
	 * @return double amount or -1 if the amount is invalid.
	 */
	@FXML
	double typedAmount() {
		try {
			double inputAmount = Double.parseDouble(amount.getText());
			if (inputAmount < 0) {
				return -1;
			}
			return inputAmount;
		} catch (NumberFormatException e) {

			return -1;
		}
	}

	/**
	 * Gets the int year in the year field.
	 * 
	 * @return int year
	 */
	@FXML
	int typedYear() {
		try {
			int inputYear = Integer.parseInt(year.getText());
			return inputYear;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * Gets the int day in the day field.
	 * 
	 * @return int day
	 */
	@FXML
	int typedDay() {
		try {
			int inputDay = Integer.parseInt(day.getText());
			return inputDay;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * Gets the int month in the month field.
	 * 
	 * @return int month
	 */
	@FXML
	int typedMonth() {
		try {
			int inputMonth = Integer.parseInt(month.getText());
			return inputMonth;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

}
