import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Client {

//	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

		//will keep track of the number of messages in the list 
		 
	public Client( String account,String num) throws IOException, ClassNotFoundException {	 
//	public static void main(String[] args) throws IOException, ClassNotFoundException {
	//	 Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
		 
		 Socket socket = new Socket("localhost",  7777 );
		 System.out.println("Connected to ");
		 
		 
		 //output stream stuff
		 OutputStream outputStream = socket.getOutputStream();
		 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		 
		 List<Message> messages = new ArrayList<>();
		 messages.add(new Message());
		 messages.get(0).setType(account);// put the account number here
		 
		 System.out.println("Sending Message Objects");
	     objectOutputStream.writeObject(messages);
		 
	    
	     //input stream stuff
		 InputStream inputStream = socket.getInputStream();
		 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
	     messages = (List<Message>) objectInputStream.readObject();
	     
	     System.out.println("Received [" + messages.size() + "] messages from: " + socket);
	    // messages.forEach(msg -> printMessage(msg));
	     
	     printMessage(messages.get(0));
	    
	//     while(true) {
	    	//will create a new message and add it to the list of messages
	//    	 messages.add(new Message());
	    	 
	    	 
	   // 	 String msg = sc.next();
	    	 if(messages.get(0).getText().equalsIgnoreCase("In use")) {
	 
	    		 // if the user types logout then this will send a logout message to th server 
	 //   		 messages.get(messages.size() - 1).setType(msg); //sets the type of message to logout
	    		 System.out.println("Acount in use");
	    	//     objectOutputStream.writeObject(messages); // sends message to server 
	    	     
	    	     //will be looking for server response
/*	    	     System.out.println("Server Response: ");
	    	     messages = (List<Message>) objectInputStream.readObject();
	    	     printMessage(messages.get(messages.size() - 1));*/
	    		
	    		
	    	 }
	    	 
	    	 //clients talks
	    //	 messages.get(messages.size() - 1).setType("text");
	   // 	 messages.get(messages.size() - 1).setText(msg);
	    	// objectOutputStream.writeObject(messages); // sends message to server
	    
	    	 //client listens 
	/*    	 System.out.println("Server Response: ");
    	     messages = (List<Message>) objectInputStream.readObject();
    	     printMessage(messages.get(messages.size() - 1));*/
	     
	    
	     
	  
	    
	  //   System.out.println("Sending Message Objects");
	 //    objectOutputStream.writeObject(messages);
	    	 if(num.equals("close")) {
	    		 setFalse(socket);
	    	 }
	}

	 private static void printMessage(Message msg){
		 	
		    System.out.println("Type: " + msg.getType());
	        System.out.println("Status: " + msg.getStatus());
	        System.out.println("Text: " + msg.getText());
	    }
	 
	 public void setFalse(Socket socket) throws IOException {
		 System.out.println("Closing socket");
	     socket.close();
	 }
}

