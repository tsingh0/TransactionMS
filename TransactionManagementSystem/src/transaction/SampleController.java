package transaction;
import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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

public class SampleController {

		private AccountDatabase database = new AccountDatabase();
	    @FXML
	    private RadioButton checking, savings, moneyMarket;

	    @FXML
	    private CheckBox loyalCustomer, directDeposit;

	    @FXML
	    private Button createAccount, deleteAccount, importDatabase, exportDatabase;

	    @FXML
	    private ToggleGroup toggleGroup11,toggleGroup1,toggleGroup111;
	    
	    @FXML
	    private TextArea output;

	    @FXML
	    private TextField firstName, lastName, balanceDeposit, balance, fName, lName, year, day, month;
	   
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
	    		if(inputBalance<0) {
	    			//bad input 
	    			return -1;
	    		}
	    		return inputBalance;
	    	}catch(NumberFormatException e){
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
	    	}catch(NumberFormatException e){
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
	    	}catch(NumberFormatException e){
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
	    	}catch(NumberFormatException e){
	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Error");
	    		alert.setHeaderText("Non numeric data has been entered for field Month.");
	    		alert.setContentText("Please enter a number.");
	    		alert.showAndWait();
	    		return -1;   		
	    	}
	    }
	    @FXML
	    //maybe adding an Alert that an account was added successfully would be helpful
	    void AccountCreator(ActionEvent event) {
	    	if(checkedCheckingAccount()) {
	    		Profile setup = new Profile(typedFname(),typedLname());
	    		Date date = new Date(typedMonth(), typedDay(), typedYear());
	    		if(date.isValid()&&typedBalance()>0) {		
		    		Checking acc = new Checking(setup,typedBalance(), date, directDepositChecked());
		    		database.add(acc);
		    		System.out.println(acc.toString());
	    		}else {
		    		Alert alert = new Alert(AlertType.WARNING);
		    		alert.setTitle("Error");
		    		alert.setHeaderText("Unavailable to make an Account with data provided.");
		    		alert.setContentText("Please check information provided again.");
		    		alert.showAndWait();   		
	    		}
	    		
	    	}else if(checkedSavingsAccount()) {
	    		Profile setup = new Profile(typedFname(),typedLname());
	    		Date date = new Date(typedMonth(), typedDay(), typedYear());
	    		if(date.isValid()&&typedBalance()>0) {		
		    		Saving acc = new Saving(setup,typedBalance(), date, loyalCustomerChecked());
		    		database.add(acc);
		    		System.out.println(acc.toString());
	    		}else {
		    		Alert alert = new Alert(AlertType.WARNING);
		    		alert.setTitle("Error");
		    		alert.setHeaderText("Unavailable to make an Account with data provided.");
		    		alert.setContentText("Please check information provided again.");
		    		alert.showAndWait();   		
	    		}
	    		
	    	}else if(checkedMoneyMarketAccount()) {		    	
		    	Profile setup = new Profile(typedFname(),typedLname());
		    	Date date = new Date(typedMonth(), typedDay(), typedYear());
		    	if(date.isValid()&&typedBalance()>0) {		
			    	MoneyMarket acc = new MoneyMarket(setup,typedBalance(), date, 0);
			    	database.add(acc);
			    	System.out.println(acc.toString());
		    	}else {
			    	Alert alert = new Alert(AlertType.WARNING);
			    	alert.setTitle("Error");
			    	alert.setHeaderText("Unavailable to make an Account with data provided.");
			    	alert.setContentText("Please check information provided again.");
			    	alert.showAndWait();   		
		    	}		    		
	    	}
	    	
	    }
	    @FXML 
	    //maybe adding an Alert that an account was removed successfully would be helpful
	    void AccountDeleter(ActionEvent event) {
	    	Profile setup = new Profile(typedFname(),typedLname());
	    	boolean removedFromDatabase = false;
	    	if(checkedCheckingAccount()) {
	    		removedFromDatabase = database.remove(new Checking(setup, 0.0, new Date(0,0,0), false));
	    		
	    	}else if(checkedMoneyMarketAccount()){
	    		removedFromDatabase = database.remove(new MoneyMarket(setup, 0.0, new Date(0,0,0), 0));
	    		
	    	}else if(checkedSavingsAccount()) {
	    		removedFromDatabase = database.remove(new Saving(setup, 0.0, new Date(0,0,0), false));
	    	}
	    	if(!removedFromDatabase) { 		//account wasn't removed from the database
		    	Alert alert = new Alert(AlertType.WARNING);
		    	alert.setTitle("Error");
		    	alert.setHeaderText("Unavailable to remove account.");
		    	alert.setContentText("Information does not match any user in the database. Please try again");
		    	alert.showAndWait();
	    	}
	    }
	    
	    @FXML
	    void importDatabaseFromFile(ActionEvent event) {
	    	DirectoryChooser chooser = new DirectoryChooser();
	    	FileChooser fileChooser = new FileChooser();
	    	chooser.setTitle("Open Source File for the Import");
	    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
	    			new ExtensionFilter("All Files", "*.*"));
	    	Stage stage = new Stage();
	    	File sourceFile = fileChooser.showOpenDialog(stage);
	    	//have to write code to actually read the file 
	    	
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
	    	//when checking is checked we want to make loyalCutomer field not clickable
	    	if(checking.isSelected()) {
	    		return true;
	    	}
	    	return false;
	    }
	    @FXML
	    boolean checkedSavingsAccount() {
	    	if(savings.isSelected()) {
	    		return true;
	    	}
	    	return false;
	    }
	    @FXML
	    boolean checkedMoneyMarketAccount() {
	    	if(moneyMarket.isSelected()) {
	    		return true;
	    	}
	    	return false;
	    }
	    @FXML
	    boolean directDepositChecked() {
	    	if(directDeposit.isPressed()) {
	    		return true;
	    	}
	    	return false;
	    }
	    @FXML
	    boolean loyalCustomerChecked() {
	    	if(loyalCustomer.isPressed()) {
	    		return true;
	    	}
	    	return false;
	    }
	    
	    @FXML
	    void checkedCheck(ActionEvent event) {
	    	//when Savings is checked we want to make direct deposit not clickable
	    	loyalCustomer.setDisable(true);
	    	loyalCustomer.setSelected(false);
	    	directDeposit.setDisable(false);
	    	
	    }
	    @FXML
	    void checkedSavings(ActionEvent event) {
	    	//when Savings is checked we want to make direct deposit not clickable
	    	loyalCustomer.setDisable(false);
	    	directDeposit.setDisable(true);
	    	directDeposit.setSelected(false);
	    	
	    }
	    @FXML
	    void checkedMoneyMarket(ActionEvent event) {
	    	//when MoneyMarket is checked we want to make neither loyal customer or direct deposit clickable
	    	loyalCustomer.setDisable(true);
	    	loyalCustomer.setSelected(false);
	    	directDeposit.setDisable(true);
	    	directDeposit.setSelected(false);
	    }

    }


