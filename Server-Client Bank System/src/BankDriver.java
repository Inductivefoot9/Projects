import java.io.IOException;
import javax.swing.*;

public class BankDriver {

	public static void main(String[] args) throws IOException {
		int choice;
		
		String[] commands = {"New Customer",
			 	"ATM",
			 	"Bank Teller",
			 	};
		
		
			 choice = JOptionPane.showOptionDialog(null,
					 "Select a command", 
					 "DVD Collection", 
					 JOptionPane.YES_NO_CANCEL_OPTION, 
					 JOptionPane.QUESTION_MESSAGE, 
					 null, 
					 commands, 
			    	 commands[commands.length - 1]);
		 
			 //case 4 and 5 were added after
			 switch (choice) {
			 	case 0: new NewCustForm(); break;
			 	case 1: new AtmLogin();; break;
			 	case 2: new BTLogin();; break;
			 
			 	
			 	default:  // do nothing
			 }
			 
		 
		
		//new NewCustForm();
	   // new AtmLogin();
		//new BTLogin();
	    
	}

}
