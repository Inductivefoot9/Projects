import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BTLogin implements ActionListener {

	JFrame login;
	
	JLabel labelA;//account number label
	JLabel labelP;//pin label
	JLabel labelN;//user name label 
	
	JTextField pinGuess;  // the users pin guess
	JTextField acctNum;  // users account number input
	JTextField acctName;
	
	JButton enter;
	
	boolean acctEnter = false;
	
	JPanel back_panel;
	
	Font myFont = new Font("Arial",Font.BOLD,30);
	
	BankTeller bT;
	Client client;
	
	public BTLogin() {
		
		login = new JFrame("Login");
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setSize(420, 550);
		login.setLayout(null);
		
		//create a label for the account input box
		labelA = new JLabel();
		labelA.setText("Account Number:");
		labelA.setFont(myFont);
		labelA.setForeground(new Color(0xFFFFFFF));
		labelA.setBounds(50,50,300, 50);
		
		acctNum = new JTextField();
		acctNum.setBounds(50, 90, 300, 50);
		acctNum.setFont(myFont);
		acctNum.setForeground(new Color(0xFFFFFFF));
		acctNum.setBackground(new Color(0x003EFF));
		acctNum.setCaretColor(Color.white);
		acctNum.setEditable(true);
		
		labelN = new JLabel();
		labelN.setText("First and Last Name:");
		labelN.setFont(myFont);
		labelN.setForeground(new Color(0xFFFFFFF));
		labelN.setBounds(50,155 ,300, 50);
		
		acctName = new JTextField();
		acctName.setBounds(50, 195 , 300, 50);
		acctName.setFont(myFont);
		acctName.setForeground(new Color(0xFFFFFFF));
		acctName.setBackground(new Color(0x003EFF));
		acctName.setCaretColor(Color.white);
		acctName.setEditable(false);
		
		labelP = new JLabel();
		labelP.setText("Password:");
		labelP.setFont(myFont);
		labelP.setForeground(new Color(0xFFFFFFF));
		labelP.setBounds(50,255 ,300, 50);
		
		pinGuess = new JTextField();
		pinGuess.setBounds(50, 295 , 300, 50);
		pinGuess.setFont(myFont);
		pinGuess.setForeground(new Color(0xFFFFFFF));
		pinGuess.setBackground(new Color(0x003EFF));
		pinGuess.setCaretColor(Color.white);
		pinGuess.setEditable(false);
		
		enter = new JButton("Enter");
		enter.addActionListener(this);
		enter.setBounds(50, 355, 300, 50);
		enter.setFont(myFont);
		enter.setFocusable(false);	
		
		back_panel = new JPanel();
		back_panel.setBounds(0, 0, 420, 650);
		back_panel.setBackground(new Color(0x0000000));
		
		//login.add(key_pad);
		login.add(labelA);
		login.add(acctNum);
		login.add(labelP);
		login.add(pinGuess);
		login.add(acctName);
		login.add(labelN);
		login.add(enter);
		login.add(back_panel);
		login.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String pin; //for the password
		String accountNumber = null;
		
		if(e.getSource() == enter) {  
			
			if(acctEnter == true) {
				pin = pinGuess.getText();
				
				if(pin.equals(bT.account.getPassword()) && acctName.getText().equalsIgnoreCase(bT.account.getUsername())) { //this line will check if the pin equals the accounts pin
															// and account number equals the files account number 
				
					login.setVisible(false); //will turn off the ATM keypad and go to transactions 
					new  BTellerGUI(bT, client); //activates transactions
					
				}
				else {
					
					// will remove everything from text field if the pin is wrong 
					JOptionPane.showMessageDialog(null, "Try again.");
					pinGuess.setText("");
					/*attempts++;
					
					if(attempts == 4) {
						
						login.setVisible(false);
						JOptionPane.showMessageDialog(null, "Too many Attempts GoodBye");
						System.exit(0);
					}*/
				}
			}
			
			
			
			else {
				
				//gets the string from the textbox
				accountNumber = acctNum.getText();
				
				try {
					client = new Client(acctNum.getText(), "none");
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				bT = new BankTeller(accountNumber);
				
				acctEnter = true;
				
				pinGuess.setEditable(true);
				acctName.setEditable(true);
			}
								
	}
		
	}

}
