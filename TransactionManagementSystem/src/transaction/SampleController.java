package transaction;

import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

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
	void fnameField(ActionEvent event) {
	}

	@FXML
	String typedFname() {
		return fName.getText();
	}

	@FXML
	String typedLname() {
		return lName.getText();
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
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Non numeric data has been entered for field Balance.");
			alert.setContentText("Please enter a number.");
			alert.showAndWait();
			return -1;
		}

	}

	@FXML
	int typedYear() {
		try {
			int inputYear = Integer.parseInt(year.getText());
			return inputYear;
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Non numeric data has been entered for field Year.");
			alert.setContentText("Please enter a number.");
			alert.showAndWait();
			return -1;
		}
	}

	@FXML
	int typedDay() {
		try {
			int inputDay = Integer.parseInt(day.getText());
			return inputDay;
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Non numeric data has been entered for field Day.");
			alert.setContentText("Please enter a number.");
			alert.showAndWait();
			return -1;
		}
	}

	@FXML
	int typedMonth() {
		try {
			int inputMonth = Integer.parseInt(month.getText());
			return inputMonth;
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Non numeric data has been entered for field Month.");
			alert.setContentText("Please enter a number.");
			alert.showAndWait();
			return -1;
		}
	}

	@FXML
	// maybe adding an Alert that an account was added successfully would be helpful
	void AccountCreator(ActionEvent event) {
		if (typedMonth() != -1 && typedDay() != -1 && typedYear() != -1 && typedBalance() != -1) {

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
					clear();
				} else {

					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Error");
					alert.setHeaderText("Unavailable to make an Account with data provided.");
					alert.setContentText("Please check information provided again.");
					alert.showAndWait();

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
					clear();
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Error");
					alert.setHeaderText("Unavailable to make an Account with data provided.");
					alert.setContentText("Please check information provided again.");
					alert.showAndWait();
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
					clear();
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Error");
					alert.setHeaderText("Unavailable to make an Account with data provided.");
					alert.setContentText("Please check information provided again.");
					alert.showAndWait();
				}
			}
		}
	}

	@FXML
	// maybe adding an Alert that an account was removed successfully would be
	// helpful
	void AccountDeleter(ActionEvent event) {
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
		
		//FileChooser fileChooser = new FileChooser();
		//fileChooser.setTitle("Open Source File for the Import");
		//fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
		//		new ExtensionFilter("All Files", "*.*"));	
		//Stage stage = new Stage();
		File sourceFile = new File("database.txt");
		try {
		//File sourceFile = fileChooser.showOpenDialog(stage);	
			Scanner scanner = new Scanner(sourceFile);
			try {
			scanner.useDelimiter("\\s*,\\s*");
			
			while(scanner.hasNext()) {
				String choice = scanner.next();
				char first = choice.charAt(0);
				System.out.println(choice);
				Date dateCreated;

				switch (first) {
					case 'C':
						String fname = scanner.next();
						System.out.println(fname);
						String lname = scanner.next();
						System.out.println(lname);
						Profile newProfile = new Profile(fname, lname);

						double ammount = scanner.nextDouble();

						String date = scanner.next();
						dateCreated = makeDate(date);

						boolean directDeposit = false;
						String isDirectDeposit = scanner.next();
						if(isDirectDeposit.equals("true")) {
							directDeposit = true;
						}else {
							directDeposit = false;
						}

						if (dateCreated.isValid() == false) {
							System.out.println(dateCreated + " is not a valid date!");
							break;
						} else {
							boolean added = database.add(new Checking(newProfile, ammount, dateCreated, directDeposit));
						}
						break;

					case 'S':
						String savingsFname = scanner.next();
						System.out.println(savingsFname);
						String savingsLname = scanner.next();
						System.out.println(savingsLname);
						Profile newSavingsProfile = new Profile(savingsFname, savingsLname);

						double savingsAmmount = scanner.nextDouble();
						System.out.println(savingsAmmount);
						String savingsDate = scanner.next();
						System.out.println(savingsDate);
						dateCreated = makeDate(savingsDate);
						
						String isLoyalString = scanner.next();
						boolean isLoyal = false;
						if(isLoyalString.equals("true")) {
							isLoyal = true;
						}else {
							isLoyal = false;
						}
						System.out.println(isLoyal);
						System.out.println("bit" +dateCreated.toString() + dateCreated.isValid());
						if (dateCreated.isValid() == false) {
							System.out.println(dateCreated + " is not a valid date!");
							break;
						} else {
							boolean added = database.add(new Saving(newSavingsProfile, savingsAmmount, dateCreated, isLoyal));
						}
						break;
					case 'M':
						String moneyMarketFname = scanner.next();
						String moneyMarketLname = scanner.next();
						Profile newMoneyMarketProfile = new Profile(moneyMarketFname, moneyMarketLname);

						double moneyMarketAmmount = scanner.nextDouble();

						String moneyMarketDate = scanner.next();
						dateCreated = makeDate(moneyMarketDate);
						int withdrawals = scanner.nextInt();
						if (dateCreated.isValid() == false) {
							System.out.println(dateCreated + " is not a valid date!");
							break;
						} else {
							boolean added = database.add(new MoneyMarket(newMoneyMarketProfile, moneyMarketAmmount, dateCreated, withdrawals));
						}
						break;
					default:
						//System.out.println("Command " + "'" + first + "" + second + "' not supported!");
						//scanner.nextLine();
					}
				}
			scanner.close();
			}catch(InputMismatchException e) {
				scanner.close();
			}
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
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

		}
	}


	@FXML
	void withdrawalMaker(ActionEvent event) {
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
}
	
