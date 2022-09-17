import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*
;

public class NewCustForm  implements ActionListener {

JFrame frame;
	
//	JTextField accountNum;
	JTextField username;
	JTextField password;
	JTextField pin;
	
	JButton[] actButtons = new JButton[5];
	
	JButton Checking, saving, credit, remove, submit; 
	
	JLabel acctType,  acctField, nameField, passField, pinField , accountNum;
	
	JPanel back_panel;
	
	Font myFont = new Font("Arial",Font.BOLD,30);
	
	BankTeller bT = new BankTeller();
	int filecount = 0;
	Client client;
	
	public NewCustForm() throws IOException {
		// let teh server sen over the file  and will store the name as an int value here
		filecount =+ 1;	
		frame = new JFrame("New Customer Form");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setLayout(null);
		
		acctField = new JLabel("Account Number:");
		acctField.setFont(myFont);
		acctField.setForeground(new Color(0xFFFF00));
		acctField.setBounds(50, 50,300, 50);
		acctField.setVisible(true);
		
		accountNum = new JLabel("00000" + Integer.toString(filecount));
		accountNum.setBounds(400, 50, 300, 50);
		accountNum.setFont(myFont);
		accountNum.setForeground(new Color(0xFFFF00));
		accountNum.setBackground(new Color(0x003EFF));
//		accountNum.setCaretColor(Color.white);
		
		nameField = new JLabel("First and Last Name:");
		nameField.setFont(myFont);
		nameField.setForeground(new Color(0xFFFF00));
		nameField.setBounds(50, 110,300, 50);
		nameField.setVisible(true);
		
		username = new JTextField();
		username.setBounds(400, 110, 300, 50);
		username.setFont(myFont);
		username.setForeground(new Color(0xFFFFFFF));
		username.setBackground(new Color(0x003EFF));
		username.setCaretColor(Color.white);
		
		passField = new JLabel("Password:");
		passField.setFont(myFont);
		passField.setForeground(new Color(0xFFFF00));
		passField.setBounds(50, 170,300, 50);
		passField.setVisible(true);
		
		password = new JTextField();
		password.setBounds(400, 170, 300, 50);
		password.setFont(myFont);
		password.setForeground(new Color(0xFFFFFFF));
		password.setBackground(new Color(0x003EFF));
		password.setCaretColor(Color.white);
		
		pinField = new JLabel("Pin:");
		pinField.setFont(myFont);
		pinField.setForeground(new Color(0xFFFF00));
		pinField.setBounds(50, 230,300, 50);
		pinField.setVisible(true);
		
		pin = new JTextField();
		pin.setBounds(400, 230, 300, 50);
		pin.setFont(myFont);
		pin.setForeground(new Color(0xFFFFFFF));
		pin.setBackground(new Color(0x003EFF));
		pin.setCaretColor(Color.white);
		
		acctType = new JLabel();
		acctType.setFont(myFont);
		acctType.setForeground(new Color(0xFFFFFFF));
		acctType.setBounds(200, 350,300, 50);
		acctType.setVisible(false);
		
		Checking = new JButton("Checking");
		Checking.setBounds(200, 410 , 300, 50);
		
		saving = new JButton("Saving");
		saving.setBounds(200, 470 , 300, 50);
		
		credit = new JButton("Credit");
		credit.setBounds(200, 530, 300, 50);
		
		remove = new JButton("Remove");
		remove.setBounds(200, 590, 300, 50);
		
		submit = new JButton("Submit");
		submit.setBounds(200, 690, 300, 50);
		
		actButtons[0] = Checking;
		actButtons[1] = saving;
		actButtons[2] = credit;
		actButtons[3] = remove;
		actButtons[4] = submit;
				
		for(int i = 0;i < 5;i++) {
			actButtons[i].addActionListener(this);
			actButtons[i].setFont(myFont);
			actButtons[i].setFocusable(false);
		}
		
		back_panel = new JPanel();
		back_panel.setBounds(0, 0, 800, 800);
		back_panel.setBackground(new Color(0x0000000));
	
		frame.add(accountNum);
		frame.add(username);
		frame.add(password);
		frame.add(pin);
		
		frame.add(acctType);
		frame.add(acctField);
		frame.add(nameField);
		frame.add(passField);
		frame.add(pinField);
		
		frame.add(Checking);
		frame.add(saving);
		frame.add(credit);
		frame.add(remove);
		frame.add(submit);
		
		frame.add(back_panel);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String aType;
		String acctNum;
		String uName;
		String pass;
		String pinNum;
		
		if(e.getSource() == Checking ) {	//if the account number is entered then user can enter pin
			acctType.setText("Checking");
			acctType.setVisible(true);
			aType = "Checking";
		}
		if(e.getSource() == saving ) {	//if the account number is entered then user can enter pin
			acctType.setText("Saving");
			acctType.setVisible(true);
			aType = "saving";
		}
		if(e.getSource() == credit) {	//if the account number is entered then user can enter pin
			acctType.setText("Credit");
			acctType.setVisible(true);
			aType = "credit";
		}
		if(e.getSource() == remove ) {	//if the account number is entered then user can enter pin
			acctType.setVisible(false);
			aType = null;
	
		}
		if(e.getSource() == submit) {	//if the account number is entered then user can enter pin
			
			acctNum = "00000" + Integer.toString(filecount) ;
			uName = username.getText();
			pass = password.getText();
			pinNum = pin.getText();
			
			try {
				bT.creatAccount(filecount, uName, pass, pinNum, 0.0, 0.0, 0.0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			frame.setVisible(false);
			try {
				client = new Client(acctNum, "none");
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new BTellerGUI(bT, client);
	
		}
	}


}
