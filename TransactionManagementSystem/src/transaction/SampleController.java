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

	@FXML
	void setToolTime(MouseEvent event) {
		tooltip.setShowDelay(Duration.seconds(0));
	}

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

	@FXML
	void fnameField(ActionEvent event) {
	}

	@FXML
	String typedFname() {
		return fName.getText().trim();
	}

	@FXML
	String typedLname() {
		return lName.getText().trim();
	}

	@FXML
	double typedBalance() {
		try {
			double inputBalance = Double.parseDouble(balance.getText());
			if (inputBalance < 0) {
				// bad input
				return -1;
			}
			return inputBalance;
		} catch (NumberFormatException e) {

			return -1;
		}

	}

	@FXML
	double typedAmount() {
		try {
			double inputAmount = Double.parseDouble(amount.getText());
			if (inputAmount < 0) {
				// bad input
				return -1;
			}
			return inputAmount;
		} catch (NumberFormatException e) {

			return -1;
		}
	}

	@FXML
	int typedYear() {
		try {
			int inputYear = Integer.parseInt(year.getText());
			return inputYear;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	@FXML
	int typedDay() {
		try {
			int inputDay = Integer.parseInt(day.getText());
			return inputDay;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	@FXML
	int typedMonth() {
		try {
			int inputMonth = Integer.parseInt(month.getText());
			return inputMonth;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	@FXML
	// maybe adding an Alert that an account was added successfully would be helpful
	void AccountCreator(ActionEvent event) {
		if (typedMonth() != -1 && typedDay() != -1 && typedYear() != -1 && typedBalance() != -1
				&& (typedFname().isEmpty() == false && typedLname().isEmpty() == false)) {

			if (checkedCheckingAccount()) {
				Profile setup = new Profile(typedFname(), typedLname());
				Date date = new Date(typedMonth(), typedDay(), typedYear());
				if (date.isValid() && typedBalance() > 0) {
					Checking acc = new Checking(setup, typedBalance(), date, directDepositChecked());
					if (database.add(acc) == true) {
						output.appendText("Account opened and added to the database\n");
					} else {
						output.appendText("Account is already in the database.\n");
					}
					// clear();
				} else {
					output.appendText(
							month.getText() + "/" + day.getText() + "/" + year.getText() + " is not a valid date!\n");
					/*
					 * Alert alert = new Alert(AlertType.WARNING); alert.setTitle("Error");
					 * alert.setHeaderText("Unavailable to make an Account with data provided.");
					 * alert.setContentText("Please check information provided again.");
					 * alert.showAndWait();
					 */
				}

			} else if (checkedSavingsAccount()) {
				Profile setup = new Profile(typedFname(), typedLname());
				Date date = new Date(typedMonth(), typedDay(), typedYear());
				if (date.isValid() && typedBalance() > 0) {
					Saving acc = new Saving(setup, typedBalance(), date, loyalCustomerChecked());
					if (database.add(acc) == true) {
						output.appendText("Account opened and added to the database\n");
					} else {
						output.appendText("Account is already in the database.\n");
					}
					// clear();
				} else {
					output.appendText(
							month.getText() + "/" + day.getText() + "/" + year.getText() + " is not a valid date!\n");
					/*
					 * Alert alert = new Alert(AlertType.WARNING); alert.setTitle("Error");
					 * alert.setHeaderText("Unavailable to make an account with data provided.");
					 * alert.setContentText("Please check information provided again.");
					 * alert.showAndWait();
					 */
				}

			} else if (checkedMoneyMarketAccount()) {
				Profile setup = new Profile(typedFname(), typedLname());
				Date date = new Date(typedMonth(), typedDay(), typedYear());
				if (date.isValid() && typedBalance() > 0) {
					MoneyMarket acc = new MoneyMarket(setup, typedBalance(), date, 0);
					if (database.add(acc)) {
						output.appendText("Account opened and added to the database\n");
					} else {
						output.appendText("Account is already in the database.\n");
					}
					// clear();
				} else {
					output.appendText(
							month.getText() + "/" + day.getText() + "/" + year.getText() + " is not a valid date!\n");
					/*
					 * Alert alert = new Alert(AlertType.WARNING); alert.setTitle("Error");
					 * alert.setHeaderText("Unavailable to make an account with data provided.");
					 * alert.setContentText("Please check information provided again.");
					 * alert.showAndWait();
					 */
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Please select an account type.");
				// alert.setContentText("Please enter a number.");
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

			if (typedMonth() == -1 || typedDay() == -1 || typedYear() == -1 || typedBalance() == -1) {
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

	@FXML
	// maybe adding an Alert that an account was removed successfully would be
	// helpful
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
		/*
		 * if(!removedFromDatabase) { //account wasn't removed from the database Alert
		 * alert = new Alert(AlertType.WARNING); alert.setTitle("Error");
		 * alert.setHeaderText("Unavailable to remove account."); alert.
		 * setContentText("Information does not match any user in the database. Please try again"
		 * ); alert.showAndWait(); }
		 */
	}

	@FXML
	void importDatabaseFromFile(ActionEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Source File for the Import");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		// File sourceFile = fileChooser.showOpenDialog(stage);
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
							boolean added = database.add(new Checking(newProfile, ammount, dateCreated, directDeposit));

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
							boolean added = database
									.add(new Saving(newSavingsProfile, savingsAmmount, dateCreated, isLoyal));

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
							boolean added = database.add(new MoneyMarket(newMoneyMarketProfile, moneyMarketAmmount,
									dateCreated, withdrawals));
							scanner.nextLine();
						}
						break;
					default:
						// System.out.println("Command " + "'" + first + "" + second + "' not
						// supported!");
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

			// e.printStackTrace();
		} catch (FileNotFoundException e1) {

			// e1.printStackTrace();
		}

		// have to write code to actually read the file

	}

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
					document.write("C" + "," + toWrite.getHolder().toString() + "," + toWrite.getBalance() + ","
							+ toWrite.getDateOpen() + "," + toWrite.isDirectDeposit());

				} else if (accounts[i] instanceof Saving) {
					Saving toWrite = (Saving) accounts[i];
					document.write("S" + "," + toWrite.getHolder().toString() + "," + toWrite.getBalance() + ","
							+ toWrite.getDateOpen() + "," + toWrite.isLoyal());

				} else if (accounts[i] instanceof MoneyMarket) {
					MoneyMarket toWrite = (MoneyMarket) accounts[i];
					document.write("M" + "," + toWrite.getHolder().toString() + "," + toWrite.getBalance() + ","
							+ toWrite.getDateOpen() + "," + toWrite.getWithdrawals());
				}
				document.write("\n");
			}
			document.flush();
			document.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@FXML
	boolean checkedCheckingAccount() {
		// when checking is checked we want to make loyalCutomer field not clickable
		if (checking.isSelected()) {
			return true;
		}
		return false;
	}

	@FXML
	boolean checkedCheckingAccount2() {
		// when checking is checked we want to make loyalCutomer field not clickable
		if (checkings2.isSelected()) {
			return true;
		}
		return false;
	}

	@FXML
	boolean checkedSavingsAccount() {
		if (savings.isSelected()) {
			return true;
		}
		return false;
	}

	@FXML
	boolean checkedSavingsAccount2() {
		if (savings2.isSelected()) {
			return true;
		}
		return false;
	}

	@FXML
	boolean checkedMoneyMarketAccount() {
		if (moneyMarket.isSelected()) {
			return true;
		}
		return false;
	}

	@FXML
	boolean checkedMoneyMarketAccount2() {
		if (moneyMarket2.isSelected()) {
			return true;
		}
		return false;
	}

	@FXML
	boolean directDepositChecked() {
		if (directDeposit.isSelected()) {
			return true;
		}
		return false;
	}

	@FXML
	boolean loyalCustomerChecked() {
		if (loyalCustomer.isSelected()) {
			return true;
		}
		return false;
	}

	@FXML
	void checkedCheck(ActionEvent event) {
		// when Savings is checked we want to make direct deposit not clickable
		loyalCustomer.setDisable(true);
		loyalCustomer.setSelected(false);
		directDeposit.setDisable(false);

	}

	@FXML
	void checkedSavings(ActionEvent event) {
		// when Savings is checked we want to make direct deposit not clickable
		loyalCustomer.setDisable(false);
		directDeposit.setDisable(true);
		directDeposit.setSelected(false);

	}

	@FXML
	void checkedMoneyMarket(ActionEvent event) {
		// when MoneyMarket is checked we want to make neither loyal customer or direct
		// deposit clickable
		loyalCustomer.setDisable(true);
		loyalCustomer.setSelected(false);
		directDeposit.setDisable(true);
		directDeposit.setSelected(false);
	}

	@FXML
	void clear() {
		fName.clear();
		lName.clear();
		month.clear();
		day.clear();
		year.clear();
		balance.clear();
	}

	@FXML
	void printLastName(ActionEvent event) {
		output.appendText(database.printByLastName());
	}

	@FXML
	void printDatabase(ActionEvent event) {
		output.appendText(database.printAccounts());
	}

	@FXML
	void printByDate(ActionEvent event) {
		output.appendText(database.printByDateOpen());
	}

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
				// alert.setContentText("Please enter a number.");
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
				// alert.setContentText("Please enter a number.");
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

	@FXML
	void clearTextArea(ActionEvent event) {
		output.clear();
	}
}
