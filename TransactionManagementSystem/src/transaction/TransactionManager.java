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
		try {	
			while(fileScan.hasNext()) {
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
						 
					 		break;
					 	case 'S':
					 
					 		break;
					 	case 'M':
					 		
					 		break;
					 	default:
					 		System.out.println("Command "+"'"+first+""+second+"' not supported!");
					 }
					
					break;
				case 'D':
					 second = choice.charAt(1);
					 switch(second) {
					 	case 'C':
						 
					 		break;
					 	case 'S':
					 
					 		break;
					 	case 'M':
					 		
					 		break;
					 	default:
					 		System.out.println("Command "+"'"+first+""+second+"' not supported!");
					 }
					
					break;
				case 'W':
					second = choice.charAt(1);
					 switch(second) {
					 	case 'C':
						 
					 		break;
					 	case 'S':
					 
					 		break;
					 	case 'M':
					 		
					 		break;
					 	default:
					 		System.out.println("Command "+"'"+first+""+second+"' not supported!");
					 }
					
					break;
				case 'P':
					 second = choice.charAt(1);
					 switch(second) {
					 	case 'C':
						 
					 		break;
					 	case 'S':
					 
					 		break;
					 	case 'M':
					 		
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
				
			
			}
		}catch(InputMismatchException e) {
			e.printStackTrace();
		}
			fileScan.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		database.printAccounts();
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
