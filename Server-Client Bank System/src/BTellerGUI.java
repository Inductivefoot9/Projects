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


public class BTellerGUI extends JFrame implements ActionListener {

			JFrame frame;
			
			JButton withdrawl, deposit, chBlanace, submit, exit, crdBalance, sBalance, resPin, transfer;
			
			JButton[] actButtons = new JButton[7];
			
			JTextField textBox_W = new JTextField(); //Withdraw 
			JTextField textBox_D = new JTextField(); //Deposit
			JTextField textBox_RP = new JTextField(); //reset pin
			JTextField textBox_ATN = new JTextField(); //account number text box
			JTextField textBox_Amt = new JTextField(); //Amount text box
			
			JPanel panel;
			JPanel back_panel;
			
			String  money;
			
			
			JLabel header, accountNum, amount;
			JLabel cblabel = new JLabel();
			JLabel crblabel = new JLabel();
			JLabel sblabel = new JLabel();
			
			Font myFont = new Font("Arial",Font.BOLD,30);
			
			BankTeller bT = new BankTeller();
			Client client;
			
			public BTellerGUI(BankTeller oldBT, Client oldc) {
				bT = oldBT;
				client = oldc;
				
				frame = new JFrame("Bank Teller");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(800, 800);
				frame.setLayout(null);
				
				header = new JLabel("Bank Teller");
				header.setFont(myFont);
				header.setForeground(new Color(0xFFFFFFF));
				header.setBackground(new Color(0xFFFF00));
				header.setBounds(50, 50,300, 50);
				header.setVisible(true);
				
				textBox_W.setBounds(400, 110 , 300, 50);
				textBox_W.setFont(myFont);
				textBox_W.setForeground(new Color(0xFFFFFFF));
				textBox_W.setBackground(new Color(0x003EFF));
				textBox_W.setCaretColor(Color.white);
				textBox_W.setVisible(false);
				
				withdrawl = new JButton("Withdraw");
				withdrawl.setBounds(50, 110 , 300, 50);
				
				textBox_D.setBounds(400, 170 , 300, 50);
				textBox_D.setFont(myFont);
				textBox_D.setForeground(new Color(0xFFFFFFF));
				textBox_D.setBackground(new Color(0x003EFF));
				textBox_D.setCaretColor(Color.white);
				textBox_D.setVisible(false);
				
				deposit = new JButton("Deposite");
				deposit.setBounds(50, 170 , 300, 50);
				
				cblabel.setBounds(400, 230 , 300, 50);
				cblabel.setFont(myFont);
				cblabel.setForeground(new Color(0xFFFFFFF));
				cblabel.setBackground(new Color(0x003EFF));
				cblabel.setVisible(false);
				
				chBlanace = new JButton("Check Balance");
				chBlanace.setBounds(50, 230, 300, 50);
				
				crblabel.setBounds(400, 290 , 300, 50);
				crblabel.setFont(myFont);
				crblabel.setForeground(new Color(0xFFFFFFF));
				crblabel.setBackground(new Color(0x003EFF));
				crblabel.setVisible(false);
				
				crdBalance = new JButton("Credit Balance");
				crdBalance.setBounds(50, 290 , 300, 50);
				
				sblabel.setBounds(400, 350, 300, 50);
				sblabel.setFont(myFont);
				sblabel.setForeground(new Color(0xFFFFFFF));
				sblabel.setBackground(new Color(0x003EFF));
				sblabel.setVisible(false);
				
				sBalance = new JButton("Savings Balance");
				sBalance .setBounds(50, 350 , 300, 50);
				
				textBox_RP.setBounds(400, 410 , 300, 50);
				textBox_RP.setFont(myFont);
				textBox_RP.setForeground(new Color(0xFFFFFFF));
				textBox_RP.setBackground(new Color(0x003EFF));
				textBox_RP.setCaretColor(Color.white);
				textBox_RP.setVisible(false);
				
				resPin = new JButton("Reset Pin");
				resPin.setBounds(50, 410 , 300, 50);
				
				transfer = new JButton("Trasnfer");
				transfer.setBounds(50, 470 , 300, 50);
				
				accountNum = new JLabel();
				accountNum.setText("Account Number:");
				accountNum.setFont(myFont);
				accountNum.setForeground(new Color(0xFFFFFFF));
				accountNum.setBounds(50,530,300, 50);
				
				textBox_ATN.setBounds(50, 590 , 300, 50);
				textBox_ATN.setFont(myFont);
				textBox_ATN.setForeground(new Color(0xFFFFFFF));
				textBox_ATN.setBackground(new Color(0x003EFF));
				textBox_ATN.setCaretColor(Color.white);
				textBox_ATN.setVisible(false);
				
				amount = new JLabel();
				amount.setText("Amount:");
				amount.setFont(myFont);
				amount.setForeground(new Color(0xFFFFFFF));
				amount.setBounds(400,530,300, 50);
				
				textBox_Amt.setBounds(400, 590, 300, 50);
				textBox_Amt.setFont(myFont);
				textBox_Amt.setForeground(new Color(0xFFFFFFF));
				textBox_Amt.setBackground(new Color(0x003EFF));
				textBox_Amt.setCaretColor(Color.white);
				textBox_Amt.setVisible(false);
				
				submit = new JButton("Sumbit");
				submit.setBounds(50, 700, 300, 50);
				submit.setFont(myFont);
				submit.addActionListener(this);
				
				exit = new JButton("EXIT");
				exit.setBounds(400, 700, 300, 50);
				exit.setFont(myFont);
				exit.addActionListener(this);
				
				actButtons[0] = withdrawl;
				actButtons[1] = deposit;
				actButtons[2] = chBlanace;
				actButtons[3] = crdBalance;
				actButtons[4] = sBalance;
				actButtons[5] = resPin;
				actButtons[6] = transfer;
				for(int i = 0;i < 7 ;i++) {
					
					actButtons[i].addActionListener(this);
					actButtons[i].setFont(myFont);
					actButtons[i].setFocusable(false);
				}
				
				back_panel = new JPanel();
				back_panel.setBounds(0, 0, 800, 800);
				back_panel.setBackground(new Color(0x0000000));
				
				frame.add(header);
				
				frame.add(actButtons[0]);//withdrawal stuff
				frame.add(textBox_W);
				
				frame.add(actButtons[1]); //deposit stuff
				frame.add(textBox_D);
				
				frame.add(actButtons[2]);//checking balance stuff
				frame.add(cblabel);
				
				frame.add(submit);
				frame.add(exit);
				
				frame.add(actButtons[3]);//credit balance stuff
				frame.add(crblabel);
				
				frame.add(actButtons[4]); //savings balance stuff
				frame.add(sblabel);
				
				frame.add(actButtons[5]);//reset pin stuff
				frame.add(textBox_RP);

				frame.add(actButtons[6]);//transfer stuff
				frame.add(accountNum);
				frame.add(amount);
				frame.add(textBox_Amt);
				frame.add(textBox_ATN);
				
				frame.add(back_panel);		
				
				frame.setVisible(true);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String withdrawl;
				String deposite;
				int pinNum = 0;
					
					if(e.getSource() == actButtons[0]) {	//Withdraw button
						
						textBox_W.setVisible(true);
						withdrawl = textBox_W.getText();
						
						textBox_D.setVisible(false);
						cblabel.setVisible(false);
						sblabel.setVisible(false);
						crblabel.setVisible(false);
						textBox_RP.setVisible(false);
						textBox_Amt.setVisible(false);
						textBox_ATN.setVisible(false);
	
				
						
					}
					
					if(e.getSource() == actButtons[1]) {	//Deposit button
						
						textBox_D.setVisible(true);
						
						textBox_W.setVisible(false);
						cblabel.setVisible(false);
						sblabel.setVisible(false);
						crblabel.setVisible(false);
						textBox_RP.setVisible(false);
						textBox_Amt.setVisible(false);
						textBox_ATN.setVisible(false);
						
						deposite = textBox_D.getText();
					}
					
					//change this one to get and show a balance 
					if(e.getSource() == actButtons[2]) {	//checking balance button 
						
						cblabel.setText(bT.checkBalance("Checking"));
						cblabel.setVisible(true);
						
						
						textBox_W.setVisible(false);
						textBox_D.setVisible(false);
						sblabel.setVisible(false);
						crblabel.setVisible(false);
						textBox_RP.setVisible(false);
						textBox_Amt.setVisible(false);
						textBox_ATN.setVisible(false);
					}
					if(e.getSource() == actButtons[3]) {	//credit balance button
						
						crblabel.setText(bT.checkBalance("Credit"));
						crblabel.setVisible(true);
						
						cblabel.setVisible(false);
						sblabel.setVisible(false);
						textBox_W.setVisible(false);
						textBox_D.setVisible(false);
						textBox_RP.setVisible(false);
						textBox_Amt.setVisible(false);
						textBox_ATN.setVisible(false);
					}
					
					if(e.getSource() == actButtons[4]) {	//savings balance button
						
						sblabel.setText(bT.checkBalance("Savings"));
						sblabel.setVisible(true);
						
						cblabel.setVisible(false);
						textBox_W.setVisible(false);
						textBox_D.setVisible(false);
						crblabel.setVisible(false);
						textBox_RP.setVisible(false);
						textBox_Amt.setVisible(false);
						textBox_ATN.setVisible(false);
						
					}
					if(e.getSource() == actButtons[5]) {	//reset pin button
						
						textBox_RP.setVisible(true);
											
						
						cblabel.setVisible(false);
						textBox_W.setVisible(false);
						textBox_D.setVisible(false);
						crblabel.setVisible(false);
						sblabel.setVisible(false);
						textBox_Amt.setVisible(false);
						textBox_ATN.setVisible(false);
						
						
					}
					
					if(e.getSource() == actButtons[6]) { //Transfer button	
						
						textBox_Amt.setVisible(true);
						textBox_ATN.setVisible(true);
						
						textBox_RP.setVisible(false);
						cblabel.setVisible(false);
						textBox_W.setVisible(false);
						textBox_D.setVisible(false);
						crblabel.setVisible(false);
						sblabel.setVisible(false);
						
						
						
					}
					if(e.getSource() == submit) {
						
						String w = textBox_W.getText();
						String d = textBox_D.getText();
						
						
						if(textBox_RP.getText().length() > 0 ){
							
							bT.resetPIN(textBox_RP.getText());
							
						}
						
						// if someone try's to deposit and withdraw at the same time 
						if(w.length() > 0 && d.length() > 0) {
							JOptionPane.showMessageDialog(null, "Invalid Transaction cannont witdrwal and deposite at the same time!!");
							
						}
						
						
						else {
							// this is where the withdraw take place 
							if(w.length() > 0) {
								String[] commands = {"Checking", "Savings", "Credit"};
								
							 int choice;
							 
								do {
									 choice = JOptionPane.showOptionDialog(null,
											 "Select a an Account", 
											 "Account", 
											 JOptionPane.YES_NO_CANCEL_OPTION, 
											 JOptionPane.QUESTION_MESSAGE, 
											 null, 
											 commands, 
									    	 commands[commands.length - 1]);
								 
									
									 switch (choice) {
									 	case 0: bT.withdraw("Checking", Double.valueOf(w)); break;
									 	case 1: bT.withdraw("Savings", Double.valueOf(w)); break;
									 	case 2: bT.withdraw("Credit", Double.valueOf(w)); break;
				
									 	
									 	default:  // do nothing
									 }
									 break;
								 } while (choice != commands.length-1);
							
							}
							
							// this is where the deposit takes place
							else if(d.length() > 0) {
								
									String[] commands = {"Checking", "Savings", "Credit"};
									
								 int choice;
								 
									do {
										 choice = JOptionPane.showOptionDialog(null,
												 "Select a an Account", 
												 "Account", 
												 JOptionPane.YES_NO_CANCEL_OPTION, 
												 JOptionPane.QUESTION_MESSAGE, 
												 null, 
												 commands, 
										    	 commands[commands.length - 1]);
									 
										
										 switch (choice) {
										 	case 0: bT.deposit("Checking", Double.valueOf(d)); break;
										 	case 1: bT.deposit("Savings", Double.valueOf(d)); break;
										 	case 2: bT.deposit("Credit", Double.valueOf(d)); break;
					
										 	
										 	default:  // do nothing
										 }
										 break;
									 } while (choice != commands.length-1);
							
							}
							
							
						}
						
						textBox_W.setText("");
						textBox_D.setText("");
						
					}
					
					if(e.getSource() == exit) {
						try {
							bT.saveAccounts();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.exit(0);
					}
			}
}

