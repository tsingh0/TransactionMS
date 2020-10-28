package transaction;
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
import javafx.stage.Stage;

public class SampleController {


	    @FXML
	    private RadioButton checking, savings, moneyMarket;

	    @FXML
	    private CheckBox loyalCustomer;

	    @FXML
	    private CheckBox directDeposit;

	    @FXML
	    private Button createAccount;

	    @FXML
	    private Button deleteAccount;

	    @FXML
	    private ToggleGroup toggleGroup11,toggleGroup1,toggleGroup111;
	    
	    @FXML
	    private TextArea output;

	    @FXML
	    private TextField firstName, lastName, balanceDeposit, balance, fName, lName, year, day, month;
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
	    		return inputBalance;
	    	}catch(NumberFormatException e){
	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Error");
	    		alert.setHeaderText("Non numeric data has been entered for field Balance");
	    		alert.setContentText("Please enter a number");
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
	    		alert.setHeaderText("Non numeric data has been entered for field Year");
	    		alert.setContentText("Please enter a number");
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
	    		alert.setHeaderText("Non numeric data has been entered for field Day");
	    		alert.setContentText("Please enter a number");
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
	    		alert.setHeaderText("Non numeric data has been entered for field Month");
	    		alert.setContentText("Please enter a number");
	    		alert.showAndWait();
	    		return -1;   		
	    	}
	    }
	    @FXML
	    void AccountCreator(ActionEvent event) {
	    	if(checkedChecking()) {
	    		Profile setup = new Profile(typedFname(),typedLname());
	    		Date date = new Date(typedMonth(), typedDay(), typedYear());
	    		Checking acc = new Checking(setup,typedBalance(), date, directDepositChecked());
	    		System.out.println(acc.toString());
	    	}
	    	
	    }
	    @FXML
	    boolean checkedChecking() {
	    	//when checking is checked we want to make loyalCutomer field not clickable
	    	if(checking.isSelected()) {
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
	    void checkedSavings(ActionEvent event) {
	    	//when Savings is checked we want to make direct deposit not clickable
	    	
	    }
	    @FXML
	    void checkedMoneyMarket(ActionEvent event) {
	    	//when MoneyMarket is checked we want to make neither loyal customer or direct deposit clickable
	    	
	    }

    }


