package transaction;

import java.io.File;
import java.text.DecimalFormat;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TransactionManager {

	public static void run() {
		
		DecimalFormat df = new DecimalFormat("#,###,##0.00");
		AccountDatabase database= new AccountDatabase();
		
		System.out.println("Enter the input file : ");
		Scanner scanner = new Scanner(System.in);
		String file = scanner.next();
		scanner.close();
		try {
			File input = new File(file);
			Scanner fileScan = new Scanner(input);	
			System.out.println("Transaction processing starts.....");
			
			while(fileScan.hasNext()) {
				try {
				String choice = fileScan.next();
				char first = choice.charAt(0);
				char second;
				Date dateCreated;
				
				switch(first) {
				case 'O':
					 second = choice.charAt(1);
					 switch(second) {
					 	case 'C':
					 		String fname = fileScan.next();
					 		String lname = fileScan.next();
					 		Profile newProfile = new Profile(fname, lname);
					 		
					 		double ammount = fileScan.nextDouble();
					 		
					 		String date = fileScan.next();
					 		dateCreated = makeDate(date);
					 		
					 		boolean directDeposit = fileScan.nextBoolean(); 		
					 		
					 		if(dateCreated.isValid()==false) {
					 			System.out.println(dateCreated+" is not a valid date!");
					 			break;
					 		}else {
					 		database.add(new Checking(newProfile,ammount,dateCreated,directDeposit));
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
					 		 
					 		if(dateCreated.isValid()==false) {
					 			System.out.println(dateCreated+" is not a valid date!");
					 			break;
					 		}else {
					 		 database.add(new Saving(newSavingsProfile,savingsAmmount,dateCreated,isLoyal));
					 		}
					 		break;
					 	case 'M':
					 		String moneyMarketFname = fileScan.next();
					 		String moneyMarketLname = fileScan.next();
					 		Profile newMoneyMarketProfile = new Profile(moneyMarketFname, moneyMarketLname);
					 		
					 		double moneyMarketAmmount = fileScan.nextDouble();

					 		String moneyMarketDate = fileScan.next();
					 		dateCreated = makeDate(moneyMarketDate);
					 	
					 		//int withdrawals = fileScan.nextInt(); 	
					 		if(dateCreated.isValid()==false) {
					 			System.out.println(dateCreated+" is not a valid date!");
					 			break;
					 		}else {
					 		database.add(new MoneyMarket(newMoneyMarketProfile,moneyMarketAmmount,dateCreated,0));
					 		}					 		
					 		break;
					 	default:
					 		
					 		/*if(dateCreated.isValid()==false) {
					 			System.out.println(dateCreated+" is not a valid date!");
					 		}else*/
					 		System.out.println("Command "+"'"+first+""+second+"' not supported!");
					 		fileScan.nextLine();
					 }
					
					break;
				case 'C':
					 second = choice.charAt(1);
					 switch(second) {
					 	case 'C':
					 	String checkingFname = fileScan.next();
					 	String checkingLname = fileScan.next();
					 	Profile remover = new Profile(checkingFname, checkingLname); 
					 	boolean removeC = database.remove(new Checking(remover, 0.0, null, false));
					 	if(removeC==true) {
					 		System.out.println("Account closed and removed from the database.");
					 	}else {
					 		System.out.println("Account does not exist.");
					 	}
					 		break;
					 	case 'S':
						 	String savingFname = fileScan.next();
						 	String savingLname = fileScan.next();
						 	Profile savingRemover = new Profile(savingFname, savingLname); 
						 	boolean removeS = database.remove(new Saving(savingRemover, 0.0, null, false));
						 	if(removeS==true) {
						 		System.out.println("Account closed and removed from the database.");
						 	}else {
						 		System.out.println("Account does not exist.");
						 	}
						 	break;
					 	case 'M':
						 	String moneyMarketFname = fileScan.next();
						 	String moneyMarketLname = fileScan.next();
						 	Profile moneyMarketRemover = new Profile(moneyMarketFname, moneyMarketLname); 
						 	boolean removeM =database.remove(new MoneyMarket(moneyMarketRemover, 0.0, null, 0));
						 	if(removeM==true) {
						 		System.out.println("Account closed and removed from the database.");
						 	}else {
						 		System.out.println("Account does not exist.");
						 	}
						 	break;
					 	default:
					 		System.out.println("Command "+"'"+first+""+second+"' not supported!");
					 		fileScan.nextLine();
					 }
					
					break;
				case 'D':
					 second = choice.charAt(1);
					 switch(second) {
					 	case 'C':
						 	String checkingFname = fileScan.next();
						 	String checkingLname = fileScan.next();
						 	Profile depositor = new Profile(checkingFname, checkingLname); 
						 	int checkingDepositAmount = fileScan.nextInt();
						 	boolean depositedC = database.deposit(new Checking(depositor,0.0, null , false), checkingDepositAmount);
						 	if(depositedC != true) {
						 		System.out.println("Account does not exist.");
						 	}else {
						 	System.out.println(df.format(checkingDepositAmount)+ " deposited to account.");
						 	}
					 		break;
					 	case 'S':
						 	String savingFname = fileScan.next();
						 	String savingLname = fileScan.next();
						 	Profile savingDepositor = new Profile(savingFname, savingLname); 
						 	int savingDepositAmount = fileScan.nextInt();
						 	boolean depositedS =database.deposit(new Saving(savingDepositor,0.0, null , false), savingDepositAmount);
						 	
						 	if(depositedS!=true) {
						 		System.out.println("Account does not exist.");
						 	}else {
							 	System.out.println(df.format(savingDepositAmount)+" deposited to account.");

						 	}
						 	
					 		break;
					 	case 'M':
						 	String moneyMarketFname = fileScan.next();
						 	String moneyMarketLname = fileScan.next();
						 	Profile moneyMarketDepositor = new Profile(moneyMarketFname, moneyMarketLname); 
						 	int moneyMarketDepositAmount = fileScan.nextInt();
						 	boolean depositedM=database.deposit(new MoneyMarket(moneyMarketDepositor,0.0, null , 0), moneyMarketDepositAmount);
					 		
						 	if(depositedM!=true) {
						 		System.out.println("Account does not exist.");
						 	}else {
						 	System.out.println(df.format(moneyMarketDepositAmount)+" deposited to account.");
						 	}
					 		break;
					 	default:
					 		System.out.println("Command "+"'"+first+""+second+"' not supported!");
					 		fileScan.nextLine();
					 }
					
					break;
				case 'W':
					second = choice.charAt(1);
					 switch(second) {
					 	case 'C':
						 	String checkingFname = fileScan.next();
						 	String checkingLname = fileScan.next();
						 	Profile withdrawler= new Profile(checkingFname, checkingLname); 
						 	int checkingWithdrawalAmount = fileScan.nextInt();
						 	int withdrawnC = database.withdrawal(new Checking(withdrawler,0.0, null , false), checkingWithdrawalAmount);
						 	if(withdrawnC == -1) {
						 		System.out.println("Account does not exist.");
						 	}
						 	else if(withdrawnC ==1) {
						 		System.out.println("Insufficient funds.");
						 	}else {
						 		System.out.println(df.format(checkingWithdrawalAmount)+" withdrawn from account.");
						 	}
					 		break;
					 	case 'S':
					 	 	String savingFname = fileScan.next();
						 	String savingLname = fileScan.next();
						 	Profile savingWithdrawal = new Profile(savingFname, savingLname); 
						 	int savingWithdrawalAmount = fileScan.nextInt();
						 	int withdrawnS = database.withdrawal(new Saving(savingWithdrawal,0.0, null , false), savingWithdrawalAmount);
						 	if(withdrawnS == -1) {
						 		System.out.println("Account does not exist.");
						 	}
						 	else if(withdrawnS ==1) {
						 		System.out.println("Insufficient funds.");
						 	}else {
						 		System.out.println(df.format(savingWithdrawalAmount)+" withdrawn from account.");
						 	}
						 	
					 		break;
					 	case 'M':
						 	String moneyMarketFname = fileScan.next();
						 	String moneyMarketLname = fileScan.next();
						 	Profile moneyMarketWithdrawal = new Profile(moneyMarketFname, moneyMarketLname); 
						 	int moneyMarketWithdrawalAmount = fileScan.nextInt();
						 	MoneyMarket temp = new MoneyMarket(moneyMarketWithdrawal,0.0, null , 0);
						 	int withdrawnM = database.withdrawal(temp, moneyMarketWithdrawalAmount);
					 		
						 	if(withdrawnM == -1) {
						 		System.out.println("Account does not exist.");
						 	}
						 	else if(withdrawnM ==1) {
						 		System.out.println("Insufficient funds.");
						 	}else {
						 		
						 		System.out.println(df.format(moneyMarketWithdrawalAmount)+" withdrawn from account.");
						 	}
						 	
						 	break;
					 	default:
					 		System.out.println("Command "+"'"+first+""+second+"' not supported!");
					 		fileScan.nextLine();
					 }
					
					break;
				case 'P':
					 second = choice.charAt(1);
					 switch(second) {
					 	case 'A':
					 		//System.out.println("--Listing accounts in the database--");
					 		database.printAccounts();
					 		//System.out.println("--end of listing--");
					 		break;
					 	case 'D':
					 		database.printByDateOpen();
					 		break;
					 	case 'N':
					 		database.printByLastName();
					 		break;
					 	default:
					 		System.out.println("Command "+"'"+first+""+second+"' not supported!");
					 		fileScan.nextLine();
					 }
					
					break;
				case 'Q':
					System.out.println("Transaction processing completed");
					
					break;
				default:
					if(choice.length()>1) {
						second = choice.charAt(1);
						System.out.println("Command "+"'"+first+""+second+"' not supported!");
					}else
					System.out.println("Command '"+first+"' not supported!");
					fileScan.nextLine();
					
				}
				}catch(InputMismatchException e) {
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

	private static Date makeDate(String date) {
		StringTokenizer dateToken = new StringTokenizer(date, "/", false);
		Date dateForm = null;
		while(dateToken.hasMoreTokens()) {
			String monthString = dateToken.nextToken();
			int month = Integer.parseInt(monthString);
			String dayString = dateToken.nextToken();
			int day = Integer.parseInt(dayString);
			String yearString = dateToken.nextToken();
			int year = Integer.parseInt(yearString);
			dateForm = new Date (month, day , year);
		}
		
		return dateForm;
	}

}
