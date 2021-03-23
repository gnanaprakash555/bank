package bank;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import org.json.simple.JSONArray;
public class account {
	static String d;
	 static void menu()
	    {
	        System.out.println("PRESS 1 FOR VIEW PROFILE");
	        System.out.println("PRESS 2 FOR DEPOSIT");
	        System.out.println("PRESS 3 FOR WITHDRAW");
	        System.out.println("PRESS 4  update");
	        System.out.println("PRESS 5  TRANSACTION HISTORY");
	        System.out.println("press 6 fr transfer");
	        System.out.println("PRESS 0 FOR EXIT");
	        
	    }
	
	
	public static void main(String[] args) throws Exception{
		Scanner sc=new Scanner(System.in);
		JSONParser parser = new JSONParser();
	      JSONObject json = (JSONObject) parser.parse(new FileReader("ji.json"));
	      JSONArray solutions = (JSONArray)json.get("accounts");
	      System.out.println("ENTER ID NO");
	      String h=sc.next();
	      System.out.println("PASSWORD");
	      String p=sc.next();
	      for (int i=0; i < solutions.size(); i++){
	          JSONObject itemArr = (JSONObject)solutions.get(i);
	         if(itemArr.get("id").toString().equals(h)){
	        	 d=(String)itemArr.get("PASSWORD"); 
	     	}
	         }
			if(p.equals(d))
			{
		int amt,opt;
		String age,dob,adharno,phno,emailid;
		String nam;
		transactionhistry f=new transactionhistry();
		transaction d=new transaction();
		dataacessprovider b=new dataacessprovider();
		do
		{
			menu();
			opt=sc.nextInt();
			switch(opt) {
			case 1:
				
				b.display(h);
				
				break;
				
			case 2:
				System.out.println("enter the amount to be depoited");
				amt=sc.nextInt();
				b.deposit(amt,h);
				break;
			case 3:
				System.out.println("enter the amount to be withdraw");
				amt=sc.nextInt();
				b.withdraw(amt,h);
				break;
			case 4:
				b.update(h);
				break;
			
			case 5:
				f.histry(h);
				break;
			case 6:
				System.out.println("enter the amount to be transfer");
				amt=sc.nextInt();
				d.transfer(amt,h);
				break;
			}
		}while(opt!=0);
System.exit(1);
			}	
		
		else {
			System.out.println("incorrect password");
			
		}
			System.exit(1);
		}
		
		
	//enter
		
}

