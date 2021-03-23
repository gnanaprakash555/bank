package bank;
import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;



public class process {
	int amount;
	  
	public void display() throws Exception
	{
		
		
			
		}
	static void done()
	{

        System.out.println("PRESS 1 FOR updating name");
        System.out.println("PRESS 2 FOR updating age");
        System.out.println("PRESS 3 FOR updating dob");
        System.out.println("PRESS 4 for updating aadharno");
        System.out.println("press 5 fr updating phone no");
        System.out.println("press 6 fr emailid");
        System.out.println("press 7 fr password");
	}
	public void update() throws Exception
	{Scanner sc=new Scanner(System.in);
		done();
		int opt=sc.nextInt();
		JSONParser jsonParser = new JSONParser();
	    JSONObject jo = (JSONObject) jsonParser.parse(new FileReader("ji.json")); 
	     JSONObject getSth =(JSONObject) jo.get("id2");
		switch(opt)
		{
		case 1:
			 System.out.print("enter name");
			 String name=sc.next();
              getSth.put("NAME",name);
		       FileWriter file = new FileWriter("ji.json");
		       file.write(jo.toString());
		       file.flush();
	         break;
		case 2:
			 System.out.print("enter age");
			 String age=sc.next();
			getSth.put("AGE",age);
		    FileWriter file1 = new FileWriter("ji.json");
	         file1.write(jo.toJSONString());
	         file1.close();
	         break;
		case 3:
			 System.out.print("enter dob");
			 String dob=sc.next();
			getSth.put("DOB",dob);
		    FileWriter file2 = new FileWriter("ji.json");
	         file2.write(jo.toJSONString());
	         file2.close();
	         break;
		case 4:
			 System.out.print("enter aadhar");
			 String adhar=sc.next();
			getSth.put("AADHAR",adhar);
		    FileWriter file3 = new FileWriter("ji.json");
	         file3.write(jo.toJSONString());
	         file3.close();
	         break;
		case 5:
			 System.out.print("enter phno");
			 String ph=sc.next();
			getSth.put("PHNO",ph);
		    FileWriter file4= new FileWriter("ji.json");
	         file4.write(jo.toJSONString());
	         file4.close();
	         break;
		case 6:
			 System.out.print("enter email");
			 String email=sc.next();
			getSth.put("EMAIL",email);
		    FileWriter file5 = new FileWriter("ji.json");
	         file5.write(jo.toJSONString());
	         file5.close();
	         break;
		case 7:
			 System.out.print("enter new password");
			 String pass=sc.next();
			getSth.put("PASSWORD",pass);
		    FileWriter file6 = new FileWriter("ji.json");
	         file6.write(jo.toJSONString());
	         file6.close();
	         break;
		
		}
	}
	
	
	
		
		public void deposit(int amt) throws Exception{
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			String m=String.valueOf(formatter.format(date)); 
			JSONParser jsonParser = new JSONParser();
	          JSONObject jo = (JSONObject) jsonParser.parse(new FileReader("ji.json"));
	              JSONObject getSth =(JSONObject) jo.get("id2");
	           Long id = (Long) getSth.get("AMOUNT");
	             getSth.put("AMOUNT",id+amt);
	       @SuppressWarnings("resource")
	       FileWriter file = new FileWriter("ji.json");
	       file.write(jo.toString());
	       file.flush();
	       JSONParser jsonParser1 = new JSONParser();
	       JSONObject jk = (JSONObject) jsonParser1.parse(new FileReader("ji.json")); 
           JSONObject getsth =(JSONObject) jk.get("id2");
           JSONObject getsth1 =(JSONObject) getsth.get("transaction");
           String id1 = (String) getsth1.get("history");
           String s=amt+" deposited "+m;
           String p=id1+"-->"+s;             
          getsth1.put("history",p);
       FileWriter file1 = new FileWriter("ji.json");
       file1.write(jk.toString());
       file1.flush();

			}
public void withdraw(int amt) throws Exception{
	JSONParser jsonParser = new JSONParser();
   JSONObject jo = (JSONObject) jsonParser.parse(new FileReader("ji.json"));
       JSONObject getSth =(JSONObject) jo.get("id2");
     Long id = (Long) getSth.get("AMOUNT");
 if(id>amt)
 {
	 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String m=String.valueOf(formatter.format(date)); 
getSth.put("AMOUNT",id-amt);

 FileWriter file = new FileWriter("ji.json");
 file.write(jo.toString());
 file.flush();
 JSONParser jsonParser1 = new JSONParser();
 JSONObject jk = (JSONObject) jsonParser1.parse(new FileReader("ji.json")); 
 JSONObject getsth =(JSONObject) jk.get("id2");
 JSONObject getsth1 =(JSONObject) getsth.get("transaction");
 String id1 = (String) getsth1.get("history");
 String s=amt+" withdrawn "+m;
 String p=id1+"-->"+s;             
getsth1.put("history",p);
FileWriter file1 = new FileWriter("ji.json");
file1.write(jk.toString());
file1.flush();
 }
 else {
System.out.print("insufficient amount");
	
}
}
		
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
