package transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TransactionManager {

	public static void run() {
		
		AccountDatabase database= new AccountDatabase();
		
		System.out.println("Enter the input file : ");
		Scanner scanner = new Scanner(System.in);
		String file = scanner.next();
		scanner.close();
		try {
			File input = new File(file);
			Scanner fileScan = new Scanner(input);	
			System.out.println("Transaction process starts...");
			
			while(fileScan.hasNext()) {
				try {
				String choice = fileScan.next();
				char first = choice.charAt(0);
				char second;
				
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
					 		Date dateCreated = makeDate(date);
					 		
					 		boolean directDeposit = fileScan.nextBoolean(); 					 		
					 		database.add(new Checking(newProfile,ammount,dateCreated,directDeposit));

					 		break;
					 		
					 	case 'S':
					 		String savingsFname = fileScan.next();
					 		String savingsLname = fileScan.next();
					 		Profile newSavingsProfile = new Profile(savingsFname, savingsLname);
					 		
					 		double savingsAmmount = fileScan.nextDouble();
					 		
					 		String savingsDate = fileScan.next();
					 		Date savingsDateCreated = makeDate(savingsDate);
					 		
					 		 boolean isLoyal = fileScan.nextBoolean(); 					 		
					 		 database.add(new Saving(newSavingsProfile,savingsAmmount,savingsDateCreated,isLoyal));
					 
					 		break;
					 	case 'M':
					 		String moneyMarketFname = fileScan.next();
					 		String moneyMarketLname = fileScan.next();
					 		Profile newMoneyMarketProfile = new Profile(moneyMarketFname, moneyMarketLname);
					 		
					 		double moneyMarketAmmount = fileScan.nextDouble();

					 		String moneyMarketDate = fileScan.next();
					 		Date moneyMarketDateCreated = makeDate(moneyMarketDate);
					 		int withdrawals = fileScan.nextInt(); 					 		
					 		database.add(new MoneyMarket(newMoneyMarketProfile,moneyMarketAmmount,moneyMarketDateCreated,withdrawals));
													 		
					 		break;
					 	default:
					 		System.out.println("Command "+"'"+first+""+second+"' not supported!");
					 }
					
					break;
				case 'C':
					 second = choice.charAt(1);
					 switch(second) {
					 	case 'C':
					 	String checkingFname = fileScan.next();
					 	String checkingLname = fileScan.next();
					 	Profile remover = new Profile(checkingFname, checkingLname); 
					 	database.remove(new Checking(remover, 0.0, null, false));
					 		break;
					 	case 'S':
						 	String savingFname = fileScan.next();
						 	String savingLname = fileScan.next();
						 	Profile savingRemover = new Profile(savingFname, savingLname); 
						 	database.remove(new Saving(savingRemover, 0.0, null, false));
					 		break;
					 	case 'M':
						 	String moneyMarketFname = fileScan.next();
						 	String moneyMarketLname = fileScan.next();
						 	Profile moneyMarketRemover = new Profile(moneyMarketFname, moneyMarketLname); 
						 	database.remove(new MoneyMarket(moneyMarketRemover, 0.0, null, 0));
					 		break;
					 	default:
					 		System.out.println("Command "+"'"+first+""+second+"' not supported!");
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
						 	database.deposit(new Checking(depositor,0.0, null , false), checkingDepositAmount);
					 		break;
					 	case 'S':
						 	String savingFname = fileScan.next();
						 	String savingLname = fileScan.next();
						 	Profile savingDepositor = new Profile(savingFname, savingLname); 
						 	int savingDepositAmount = fileScan.nextInt();
						 	database.deposit(new Saving(savingDepositor,0.0, null , false), savingDepositAmount);
					 
					 		break;
					 	case 'M':
						 	String moneyMarketFname = fileScan.next();
						 	String moneyMarketLname = fileScan.next();
						 	Profile moneyMarketDepositor = new Profile(moneyMarketFname, moneyMarketLname); 
						 	int moneyMarketDepositAmount = fileScan.nextInt();
						 	database.deposit(new MoneyMarket(moneyMarketDepositor,0.0, null , 0), moneyMarketDepositAmount);
					 		
					 		break;
					 	default:
					 		System.out.println("Command "+"'"+first+""+second+"' not supported!");
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
						 	database.withdrawal(new Checking(withdrawler,0.0, null , false), checkingWithdrawalAmount);
						 
					 		break;
					 	case 'S':
					 	 	String savingFname = fileScan.next();
						 	String savingLname = fileScan.next();
						 	Profile savingWithdrawal = new Profile(savingFname, savingLname); 
						 	int savingWithdrawalAmount = fileScan.nextInt();
						 	database.withdrawal(new Saving(savingWithdrawal,0.0, null , false), savingWithdrawalAmount);
					 
					 		break;
					 	case 'M':
						 	String moneyMarketFname = fileScan.next();
						 	String moneyMarketLname = fileScan.next();
						 	Profile moneyMarketWithdrawal = new Profile(moneyMarketFname, moneyMarketLname); 
						 	int moneyMarketWithdrawalAmount = fileScan.nextInt();
						 	database.withdrawal(new MoneyMarket(moneyMarketWithdrawal,0.0, null , 0), moneyMarketWithdrawalAmount);
					 		break;
					 	default:
					 		System.out.println("Command "+"'"+first+""+second+"' not supported!");
					 }
					
					break;
				case 'P':
					 second = choice.charAt(1);
					 switch(second) {
					 	case 'A':
					 		database.printAccounts();
					 		break;
					 	case 'D':
					 
					 		break;
					 	case 'N':
					 		
					 		break;
					 	default:
					 		System.out.println("Command "+"'"+first+""+second+"' not supported!");
					 }
					
					break;
				case 'Q':
					System.out.println("Transaction processing completed.");
					
					break;
				default:
					
				}
				}catch(InputMismatchException e) {
					System.out.println("Input datatype mismatch");
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
