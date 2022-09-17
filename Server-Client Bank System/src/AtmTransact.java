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

public class AtmTransact extends JFrame implements ActionListener{

JFrame frame;
	
	JButton withdrawl, deposite, blanace, submit, exit;
	
	JButton[] actButtons = new JButton[3];
	
	JTextField textBox_W = new JTextField();
	JTextField textBox_D = new JTextField();
	JTextField textBox_B = new JTextField();
	
	JPanel panel;
	String  money;
	
	JPanel back_panel;
	
	JLabel label = new JLabel();
	JLabel cblabel = new JLabel();
	
	Font myFont = new Font("Arial",Font.BOLD,30);
	
	BankTeller bT = new BankTeller();
	Client client;
	
	public AtmTransact(BankTeller oldBT, Client oldC){
		
		bT = oldBT;
		client = oldC;
		
		frame = new JFrame("ATM");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 750);
		frame.setLayout(null);
		
		
		
		textBox_W.setBounds(50, 50 , 300, 50);
		textBox_W.setFont(myFont);
		textBox_W.setForeground(new Color(0xFFFFFFF));
		textBox_W.setBackground(new Color(0x003EFF));
		textBox_W.setCaretColor(Color.white);
		textBox_W.setVisible(false);
		
		textBox_D.setBounds(50, 200 , 300, 50);
		textBox_D.setFont(myFont);
		textBox_D.setForeground(new Color(0xFFFFFFF));
		textBox_D.setBackground(new Color(0x003EFF));
		textBox_D.setCaretColor(Color.white);
		textBox_D.setVisible(false);
		
		textBox_B.setBounds(50, 350 , 300, 50);
		textBox_B.setFont(myFont);
		textBox_B.setForeground(new Color(0xFFFFFFF));
		textBox_B.setBackground(new Color(0x003EFF));
		textBox_B.setCaretColor(Color.white);
		textBox_B.setVisible(false);
		
		cblabel.setBounds(50, 350 , 300, 50);
		cblabel.setFont(myFont);
		cblabel.setForeground(new Color(0xFFFFFFF));
		cblabel.setBackground(new Color(0x003EFF));
		cblabel.setVisible(false);
		
		withdrawl = new JButton("Withdrwawl");
		withdrawl.setBounds(50, 110 , 300, 50);
		
		deposite = new JButton("Deposite");
		deposite.setBounds(50, 260 , 300, 50);
		
		blanace = new JButton("Check Balance");
		blanace.setBounds(50, 410, 300, 50);
		
		
		submit = new JButton("Sumbit");
		submit.setBounds(50, 500, 300, 50);
		submit.setFont(myFont);
		submit.addActionListener(this);
		
		exit = new JButton("EXIT");
		exit.setBounds(50, 560, 300, 50);
		exit.setFont(myFont);
		exit.addActionListener(this);
		
		actButtons[0] = withdrawl;
		actButtons[1] = deposite;
		actButtons[2] = blanace;
		
		for(int i = 0;i < 3;i++) {
			actButtons[i].addActionListener(this);
			actButtons[i].setFont(myFont);
			actButtons[i].setFocusable(false);
		}
		
		back_panel = new JPanel();
		back_panel.setBounds(0, 0, 450, 750);
		back_panel.setBackground(new Color(0x0000000));
		
		frame.add(actButtons[0]);
		frame.add(textBox_W);
		frame.add(actButtons[1]);
		frame.add(textBox_D);
		frame.add(actButtons[2]);
		frame.add(cblabel);
		frame.add(submit);
		frame.add(exit);
		frame.add(back_panel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

			
			if(e.getSource() == actButtons[0]) {	// withdraw
				textBox_W.setVisible(true);

				
				textBox_D.setVisible(false);
				cblabel.setVisible(false);
				
			}
			
			if(e.getSource() == actButtons[1]) {	 //deposite
				textBox_D.setVisible(true);
				textBox_W.setVisible(false);
				cblabel.setVisible(false);
				
			}
			
			//change this one to get and show a balance 
			if(e.getSource() == actButtons[2]) {	 //checking balance
				
				cblabel.setText(bT.checkBalance("Checking"));
				cblabel.setVisible(true);
				textBox_W.setVisible(false);
				textBox_D.setVisible(false);
				
			}
			
			if(e.getSource() == submit) {
				
				 String w = textBox_W.getText();
				String d = textBox_D.getText();
				
				if(w.length() > 0 && d.length() > 0) {
					JOptionPane.showMessageDialog(null, "Invalid Transaction cannont do two transactions at the same time!!");
					
				}
				else {
					if(w.length() > 0) {
						bT.withdraw("Checking", Double.valueOf(w));
					}
					else if(d.length() > 0) {
						bT.deposit("Checking", Double.valueOf(d));
					}
				}
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
