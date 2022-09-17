
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class BankTeller {

 //   private static Long nextAccountNumber = 100000L;
   // private LinkedList<Account> accounts = new LinkedList<>();
	Account account;
	String filename;
    public BankTeller() {
    	account = new Account();
    }

    public BankTeller(String fileName) {
        filename = fileName;
    	try {
    		
            Scanner file = new Scanner(new File(fileName));
            while (file.hasNext()) {
                int accountNumber = Integer.valueOf( file.nextLine());
                String username = file.nextLine();
                String password = file.nextLine();
                String pin = file.nextLine();
                Double check = Double.valueOf(file.nextLine());
                Double  save = Double.valueOf(file.nextLine());
                Double credit = Double.valueOf(file.nextLine());
         //       System.out.println(accountNumber + " " + username + " " +password +" " + pin + " " +check + " " +save + " " +credit);
                account = new Account(accountNumber, username, password, pin, check, save, credit);
            }
            file.close();
           
        } catch (FileNotFoundException ex) {

        } catch (Exception ex) {
            
        }
    }

    public void saveAccounts() throws IOException {
       if(!(filename.equalsIgnoreCase(Integer.toString(account.getAccountNum())))){
    	  
    	   File file = new File(filename);
   				file.createNewFile();
    	   
       }
    	FileWriter file = new FileWriter( filename);
       
            file.write(account.getAccountNum() + "\n");
            file.write(account.getUsername() + "\n");
            file.write(account.getPassword() + "\n");
            file.write(account.getPin() + "\n");
            file.write(account.getChecking() + "\n");
            file.write(account.getSavings() + "\n");
            file.write(account.getCredit() + "\n");
            
        
        file.close();
    }

	public void  creatAccount(int accountNum, String user, String pass, String Pin, Double check, Double save, Double cred) throws IOException {
		filename = Integer.toString(accountNum);
		
		account = new Account( accountNum, user,  pass,  Pin,  check,  save, cred);
    }
	
    public void addNewAccount(String acctType, double deposite) {
        if (acctType.equalsIgnoreCase("Checking")) {
            account.setChecking(deposite);
        }
        if (acctType.equalsIgnoreCase("Credit")) {
            account.setCredit(deposite);
        }
        if (acctType.equalsIgnoreCase("Savings")) {
            account.setSavings(deposite);
        }
    }

  /*  public String addNewAccount(String username, String password, Integer pin, Double balance) {
        String accountNumber = "A" + nextAccountNumber.toString();
        while (getAccount(accountNumber) != null) {
            nextAccountNumber++;
            accountNumber = "A" + nextAccountNumber.toString();
        }
        Account account = new Account();
        accounts.add(account);
        nextAccountNumber++;
        return accountNumber;
    }*/

  /*  public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }*/

    public void deposit(String acctType, double amount) {
        
    	  if (acctType.equalsIgnoreCase("Checking")) {
    		  account.addChecking(amount);
          }
          if (acctType.equalsIgnoreCase("Credit")) {
        	  account.addCredit(amount);
          }
          if (acctType.equalsIgnoreCase("Savings")) {
        	  account.addSavings(amount);
          }
    	
    	
        
    }

    public void withdraw(String acctType, double amount) {
    	
    	 if (acctType.equalsIgnoreCase("Checking")) {
   		   account.setChecking(account.getChecking() - amount);
         }
         if (acctType.equalsIgnoreCase("Credit")) {
       	  account.setCredit(account.getCredit() - amount);
         }
         if (acctType.equalsIgnoreCase("Savings")) {
       	  account.setSavings(account.getSavings() - amount);
         }
    }

    	public void transfer(int acctNum, double amount) {
        int tAcctNum = acctNum;
        double dollar = amount;
        
    }

    public String checkBalance(String acctType) {
    	  String temp = null;
    	
    	  if (acctType.equalsIgnoreCase("Checking")) {
     		 temp = account.getChecking() + "";
     		return temp;
           }
          
    	  if (acctType.equalsIgnoreCase("Credit")) {
    
        	   temp = account.getCredit() + "";
         	return temp;
           }
           if (acctType.equalsIgnoreCase("Savings")) {
         	  
        	   temp =account.getSavings() +"";
         	return temp;
           }
		return null;
    }

    public void resetPIN(String pin) {
        account.setPin(pin);
    }

    public void resetPassword( String password) {
    
    	account.setPassword(password);
    }

    public void deleteAccount( int acctNum) {
       account.setAccountNum(acctNum);
    }

}