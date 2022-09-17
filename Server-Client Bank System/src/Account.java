import java.io.File;
import java.io.IOException;

public class Account{
	// Data Fields:
	private int accountNum; // Account Number
	private String username;	// Username of the Account
	private String password;	// Password of the Account
	private String pin;		// Pin on the Account
	private double checking ;	// Amount in Checking
	private double savings;		// Amount in Savings
	private double credit;		// Amount in Credit

	 
	public Account() {
		
		// TODO Auto-generated constructor stub
	}

	// Default Constructor
	public Account(Integer accNum, String user, String pass, String Pin, double check, double save, double cred) throws IOException {
		this.setAccountNum(accNum);
		this.setUsername(user);
		this.setPassword(pass);
		this.setPin(Pin);
		this.setChecking(check);		// only initialized by teller
		this.setSavings(save); 		// ""
		this.setCredit(cred);	// ""
		
		
		File f = new File( Integer.toString(accNum));
		f.createNewFile();
		
	}
	public Account( String user, String pass, String Pin, double check, double save, double cred) {
		this.setAccountNum(1);
		this.setUsername(user);
		this.setPassword(pass);
		this.setPin(Pin);
		this.setChecking(check);		// only initialized by teller
		this.setSavings(save); 		// ""
		this.setCredit(cred);		// ""
	}
	
	// Set Account Number
	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(Integer accountNum) {
		this.accountNum = accountNum;
	}

	// Username
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	// Password
	public String getPassword() {
		return password;
	}

	public void setPassword(String Password) {
		this.password = Password;
	}
	
	// Pin
	public String getPin() {
		return pin;
	}

	public void setPin(String Pin) {
		this.pin = Pin;
	}

	// Checking
	public double getChecking() {
		return checking;
	}

	public void setChecking(Double checking) {
		this.checking = checking;
	}
	
	public void addChecking (Double checking) {
		this.checking += checking;
	}
	
	// Checking
	public double getSavings() {
		return savings;
	}

	public void setSavings(Double savings) {
		this.savings = savings;
	}
	
	public void addSavings(Double savings) {
		this.savings += savings;
	}

	
	// Credit
	public double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}
	
	public void addCredit(Double credit) {
		this.credit += credit;
	}
	
	// toString
	public String toString() {
		return getAccountNum() + "," + getUsername() + "," + getPassword() +
				"," + getPin() + "," + getChecking() + "," + getSavings() +
				"," + getCredit();	// STUB: Remove this line.
	}
}