import java.io.Serializable;

public class Message implements Serializable {
    protected String accountNum;
    protected String status; // successful or failed 
    protected String text;

    public Message(){
        this.accountNum = "Undefined";
        this.status = "Undefined";
        this.text = "Undefined";
    }

    public Message(String accountNum, String status){
         setType(accountNum);
         setStatus(status);
         this.status = status;
     //    setText(text);
    }

   /* public Message(String accountNum, String text){
        setType(accountNum);
      setText(text);
        this.status = "Undefined";
   }*/
    
    void setType(String accountNum){
    		this.accountNum = accountNum;

    }

    public void setStatus(String status){
    	
    	this.status = status;
    }

    public void setText(String text){
    	this.text = text;
    }

    public String getType(){
    	return accountNum;
    }

    public String getStatus(){
    	return status;
    }

    public String getText(){
    	return text;
    }

}

