import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ServerSocket servSock = new ServerSocket(7777);
		System.out.println("ServerSocket awaiting connections...");
		ObjectOutputStream objectOutputStream = null;
		String textUpper;
		// this first while loop is used to create a new socket every time the client wants to make a new connection
		//double while loop is used to keep the connection open if the client wants to disconnect and reconnect 
		while(true) {
			try {
				
				Socket socket = servSock.accept(); // waits to accept a socket object
				System.out.println("Connection from " + socket + "!");
				
				//input stream stuff
				InputStream inputStream = socket.getInputStream(); // getting the input stream 
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				
				//output stream stuff
				
			
				List<Message> messages = (List<Message>) objectInputStream.readObject();
				System.out.println("Received [" + messages.size() + "] messages from: " + socket);
				
				//System.out.println(listOfMessages.get(0).setStatus("success"));
				//messages.forEach(msg -> printMessage(msg));
				
				printMessage(messages.get(0));
				
				 if(messages.get(0).getStatus().equalsIgnoreCase("true")) {
					 
		    		 // if the user types logout then this will send a logout message to th server 
		 //   		 messages.get(messages.size() - 1).setType(msg); //sets the type of message to logout
		    		 messages.get(0).setText("In use");
		    	//     objectOutputStream.writeObject(messages); // sends message to server 
		    	     
		    	     //will be looking for server response
	/*	    	     System.out.println("Server Response: ");
		    	     messages = (List<Message>) objectInputStream.readObject();
		    	     printMessage(messages.get(messages.size() - 1));*/		
		    		
		    	 }
				
				
				//if(messages.get(0).getType().equalsIgnoreCase("login")) {
				 
					messages.get(0).setStatus("true");
					
					OutputStream outputStream = socket.getOutputStream();
					objectOutputStream = new ObjectOutputStream(outputStream);
					objectOutputStream.writeObject(messages);
			//	}
				
				//this while loop is used to send data back and forth 
				while(true){
					
					//listen 
					 System.out.println("Client Response: ");
		    	     messages = (List<Message>) objectInputStream.readObject();
		    	     printMessage(messages.get(messages.size() - 1));
		    	     
					//if logout break loop close server
		    	     if( messages.get(messages.size() - 1).getType().equalsIgnoreCase("logout")) {
						
						messages.get(messages.size() - 1).setStatus("success");
						objectOutputStream.writeObject(messages);
						
						break;
					}
					 //talk
		    	     
		    	 //    textUpper = messages.get(messages.size() - 1).getText();
		    	//     messages.get(messages.size() - 1).setText(textUpper.toUpperCase());
		    	     System.out.println("Sending Message Objects");
		    	     objectOutputStream.writeObject(messages);
					}
				
				
				 System.out.println("Closing sockets.");
			     servSock.close();
			     socket.close();
			
				
				
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}

	}
	
	   private static void printMessage(Message msg){
		   
		    System.out.println("\nType: " + msg.getType());
	        System.out.println("Status: " + msg.getStatus());
	     //   System.out.println("Text: " + msg.getText());
	    }


}
