import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AtmLogin extends JFrame implements ActionListener{

	JFrame atm; // will be the frame tha t holds all the panels 
	
	JTextField pinGuess;  // the users pin guess
	JTextField acctNum;  // users accoun number input 
	

	
	JButton[] numButtons = new JButton[10]; // this array will hold the keypad buttons 
	
	
	JButton enter, delete;
	
	JPanel key_pad;
	JPanel back_panel;
	
	JLabel labelA;//account number label
	JLabel labelP;//pin label
	
	boolean acctEnter = false;
	
	String pin;
	int attempts;
	
	Font myFont = new Font("Arial",Font.BOLD,30);
	
	BankTeller bT;
	Client client;
	
	AtmLogin(){
		atm = new JFrame("ATM");
		atm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		atm.setSize(420, 650);
		atm.setLayout(null);
		
		//create a label for the account input box
		labelA = new JLabel();
		labelA.setText("Account Number:");
		labelA.setFont(myFont);
		labelA.setForeground(new Color(0xFFFFFFF));
		labelA.setBounds(50,50,300, 50);
		
		//this is the account text field 
		acctNum = new JTextField();
		acctNum.setBounds(50, 90, 300, 50);
		acctNum.setFont(myFont);
		acctNum.setForeground(new Color(0xFFFFFFF));
		acctNum.setBackground(new Color(0x003EFF));
		acctNum.setCaretColor(Color.white);
		acctNum.setEditable(false);
		
		//create label for the pin input box
		labelP = new JLabel();
		labelP.setText("Pin:");
		labelP.setFont(myFont);
		labelP.setForeground(new Color(0xFFFFFFF));
		labelP.setBounds(50,155 ,300, 50);
		
		//this is the pin text field 
		pinGuess = new JTextField();
		pinGuess.setBounds(50, 195 , 300, 50);
		pinGuess.setFont(myFont);
		pinGuess.setForeground(new Color(0xFFFFFFF));
		pinGuess.setBackground(new Color(0x003EFF));
		pinGuess.setCaretColor(Color.white);
		pinGuess.setEditable(false);
		
		enter = new JButton("Enter");  // will signal that the user doesn't have an more input
		delete = new JButton("Delete");  // will delete the last character
		
		
		for(int i =0;i<10;i++) { //this for loop will instantiate all the buttons on the keypad
			numButtons[i] = new JButton(String.valueOf(i));
			numButtons[i].addActionListener(this); //connects buttons to action listener
			numButtons[i].setFont(myFont); // sets buttons font
			numButtons[i].setFocusable(false); // remove weird box 
		}

		enter.addActionListener(this);
		enter.setFocusable(false);	
		
		delete.addActionListener(this);
		delete.setFocusable(false);
		
		//This panel will be the keypad
		key_pad = new JPanel();
		key_pad.setBounds(50, 250, 300, 300);
		key_pad.setBackground(new Color(0x0000000));
		key_pad.setLayout(new GridLayout(4,4,10,10));
	
		//this is the background for the Atm
		back_panel = new JPanel();
		back_panel.setBounds(0, 0, 420, 650);
		back_panel.setBackground(new Color(0x0000000));
		
		
		//adding all the buttons to the keypad key_pad
		key_pad.add(numButtons[1]);
		key_pad.add(numButtons[2]);
		key_pad.add(numButtons[3]);
		key_pad.add(numButtons[4]);
		key_pad.add(numButtons[5]);
		key_pad.add(numButtons[6]);
		key_pad.add(numButtons[7]);
		key_pad.add(numButtons[8]);
		key_pad.add(numButtons[9]);
		key_pad.add(delete);
		key_pad.add(numButtons[0]);
		key_pad.add(enter);
		
		//adding thing to the frame 
		atm.add(key_pad);
		atm.add(labelA);
		atm.add(acctNum);
		atm.add(labelP);
		atm.add(pinGuess);
		atm.add(back_panel);
		atm.setVisible(true);
		
		
	
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
		for(int i = 0;i < 10; i++) { // if a number button is clicked show in text field
			
			if(e.getSource() == numButtons[i] && acctEnter == true) {	//if the account number is entered then user can enter pin
		
				pinGuess.setText(pinGuess.getText().concat(String.valueOf(i)));	
			}
			
			else if(e.getSource() == numButtons[i] ) {	// t
				
				acctNum.setText(acctNum.getText().concat(String.valueOf(i)));	
				
			}
			
		}
			//enter button will check to see if pin is correct 
		if(e.getSource() == enter) {  
			
			if(acctEnter == true) {
				pin = pinGuess.getText();
			
				
				/*
				 * this is were we will check the pin that we received from the server 
				 * 
				 * 
				 * 
				 */
				//if statement will check to see if pin is correct
				if(pin.equals("1234")) { //this line will check if the pin equals the accounts pin
										// and account number equals the files account number 
					//pin.equals("1234")
					atm.setVisible(false); //will turn off the ATM keypad and go to transactions 
					new AtmTransact(bT, client); //activates transactions
					
				}
				else {
					
					// will remove everything from text field if the pin is wrong 
					JOptionPane.showMessageDialog(null, "Try again.");
					pinGuess.setText("");
					attempts++;
					
					if(attempts == 4) {
						
						atm.setVisible(false);
						JOptionPane.showMessageDialog(null, "Too many Attempts GoodBye");
						System.exit(0);
					}
				}
			}
			
			else {
				//gets the string from the textbox
				
				try {
					client = new Client(acctNum.getText(), "none");
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				bT = new BankTeller(acctNum.getText());
				
				acctEnter = true;
			}
								
	}
		
			if(e.getSource() == delete) {//will delete once character for every time the delete button is pushed 
				
				if(acctEnter == true) { //if account number was entered goes to pin
					
					String string = pinGuess.getText();
					pinGuess.setText("");
				
					for(int i = 0 ; i < string.length() - 1; i++) {
				
						pinGuess.setText(pinGuess.getText()+string.charAt(i));
					}
				}
				
				else {
					
					String string = acctNum.getText();
					acctNum.setText("");
				
					for(int i = 0 ; i < string.length() - 1; i++) {
					
						acctNum.setText(acctNum.getText()+string.charAt(i));
					}
				}
			}
	}

}
