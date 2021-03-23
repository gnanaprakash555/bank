package bank;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.*;
import java.text.SimpleDateFormat;
public class FileDataAccess implements IDataAccess{
	int amount;
	static String s;
	static JSONObject idno;
	public void display(String s) throws Exception
	
	{
		Scanner sc=new Scanner(System.in);
		 JSONParser parser = new JSONParser();
	      JSONObject json = (JSONObject) parser.parse(new FileReader("ji.json"));
	      JSONArray solutions = (JSONArray)json.get("accounts");
	      for (int i=0; i < solutions.size(); i++){
	          JSONObject itemArr = (JSONObject)solutions.get(i);
	         if(itemArr.get("id").toString().equals(s)){
	        	 System.out.println(itemArr);
	     	}
	         }
	     
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
        System.out.println("press 8 FOR updating IFSC");
        
	}
	public void update(String s) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		
		JSONParser parser = new JSONParser();
	      JSONObject json = (JSONObject) parser.parse(new FileReader("ji.json"));
	      JSONArray solutions = (JSONArray)json.get("accounts");
	      for (int i=0; i < solutions.size(); i++){
	          JSONObject itemArr = (JSONObject)solutions.get(i);
	         if(itemArr.get("id").toString().equals(s)){
	        	 done();
	     		int opt=sc.nextInt();
	     
		switch(opt)
		{
		case 1:
			 System.out.print("enter name");
			 String name=sc.next();
              itemArr.put("NAME",name);
		       FileWriter file = new FileWriter("ji.json");
		       file.write(json.toString());
		       file.flush();
	         break;
		case 2:
			 System.out.print("enter new age");
			 String age=sc.next();
			itemArr.put("AGE",age);
		    FileWriter file1 = new FileWriter("ji.json");
	         file1.write(json.toJSONString());
	         file1.close();
	         break;
		case 3:
			 System.out.print("enter dob");
			 String dob=sc.next();
			itemArr.put("DOB",dob);
		    FileWriter file2 = new FileWriter("ji.json");
	         file2.write(json.toJSONString());
	         file2.close();
	         break;
		case 4:
			 System.out.print("enter new  aadhar");
			 String adhar=sc.next();
			itemArr.put("AADHAR",adhar);
		    FileWriter file3 = new FileWriter("ji.json");
	         file3.write(json.toJSONString());
	         file3.close();
	         break;
		case 5:
			 System.out.print("enter new phno");
			 String ph=sc.next();
			itemArr.put("PHNO",ph);
		    FileWriter file4= new FileWriter("ji.json");
	         file4.write(json.toJSONString());
	         file4.close();
	         break;
		case 6:
			 System.out.print("enter new email");
			 String email=sc.next();
			itemArr.put("EMAIL",email);
		    FileWriter file5 = new FileWriter("ji.json");
	         file5.write(json.toJSONString());
	         file5.close();
	         break;
		case 7:
			 System.out.print("enter new password");
			 String pass=sc.next();
			itemArr.put("PASSWORD",pass);
		    FileWriter file6 = new FileWriter("ji.json");
	         file6.write(json.toJSONString());
	         file6.close();
	         break;
		case 8:
			 System.out.print("enter new ifsc");
			 String ifsc=sc.next();
			itemArr.put("IFSC",ifsc);
		    FileWriter file7 = new FileWriter("ji.json");
	         file7.write(json.toJSONString());
	         file7.close();
	         break;
		}
	         }
		}
	     
	}
	
	
	
		
		public void deposit(int amt,String s) throws Exception
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			String m=String.valueOf(formatter.format(date)); 
			JSONParser parser = new JSONParser();
		      JSONObject json = (JSONObject) parser.parse(new FileReader("ji.json"));
		      JSONArray solutions = (JSONArray)json.get("accounts");
		      for (int i=0; i < solutions.size(); i++){
		          JSONObject itemArr = (JSONObject)solutions.get(i);
		         if(itemArr.get("id").toString().equals(s)){
		        	 long damt=(long)itemArr.get("AMOUNT");
		        	 damt+=amt;
		        	 itemArr.put("AMOUNT",damt);
		        	 JSONObject jsonObject = new JSONObject();
				      jsonObject.put("TYPE","deposit");
				      jsonObject.put("AMOUNT",amt);
				      jsonObject.put("DATE",m);
				      JSONArray sol = (JSONArray)itemArr.get("transactions");
			        	 sol.add(jsonObject);
		        	 FileWriter file = new FileWriter("ji.json");
			         file.write(json.toJSONString());
			         file.close();
			         break;
		         }
		      }
		      System.out.print("DEPOSITED "+amt+" SUCCESSFULLY");
	   
			}
public void withdraw(int amt,String s) throws Exception{
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Date date = new Date();
	String m=String.valueOf(formatter.format(date)); 
	JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(new FileReader("ji.json"));
    JSONArray solutions = (JSONArray)json.get("accounts");
    for (int i=0; i < solutions.size(); i++){
        JSONObject itemArr = (JSONObject)solutions.get(i);
       if(itemArr.get("id").toString().equals(s)){
      	 long damt=(long)itemArr.get("AMOUNT");
 if(damt>amt)
 {
	 damt-=amt;
	 itemArr.put("AMOUNT",damt);
	 JSONObject jsonObject = new JSONObject();
     jsonObject.put("TYPE","withdraw");
     jsonObject.put("AMOUNT",amt);
     jsonObject.put("DATE",m);
     JSONArray sol = (JSONArray)itemArr.get("transactions");
   	 sol.add(jsonObject);
	 FileWriter file = new FileWriter("ji.json");
    file.write(json.toJSONString());
    file.close();
    System.out.print("WITHDRAWN "+amt+" SUCCESSFULLY");
    break;
    
 }
 else {
System.out.print("insufficient amount");
	
}
}
    }}
public   void histry(String id) throws Exception{
    Scanner sc=new Scanner(System.in);
    int opt;
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(new FileReader("ji.json"));
    JSONArray solutions = (JSONArray)json.get("accounts");
    for (int i=0; i < solutions.size(); i++){
       JSONObject itemArr = (JSONObject)solutions.get(i);
    if(itemArr.get("id").toString().equals(id)){
       idno=itemArr;
       break;
      }}
   JSONArray s1 = (JSONArray)idno.get("transactions");
	 do {
		    done1();
			opt=sc.nextInt();
			switch(opt) {
			case 1:
				 for (int i=0; i < s1.size(); i++){
			          JSONObject itemArr = (JSONObject)s1.get(i);
			         if(itemArr.get("TYPE").toString().equals("deposit")){
			        	 System.out.println(itemArr);
			         }
			        
			         }
			        	 
				break;
			case 2:
				for (int i=0; i < s1.size(); i++){
			          JSONObject itemArr = (JSONObject)s1.get(i);
			         if(itemArr.get("TYPE").toString().equals("withdraw")){
			        	 System.out.println(itemArr);
			         }}
				break;
			
			case 3:
				for (int i=0; i < s1.size(); i++){
			          JSONObject itemArr = (JSONObject)s1.get(i);
			         if(itemArr.get("TYPE").toString().equals("transfer")){
			        	 System.out.println(itemArr);
			         }}
				break;
			case 4:
				for (int i=0; i < s1.size(); i++){
			          JSONObject itemArr = (JSONObject)s1.get(i);
			          System.out.println(itemArr);
			          }
				break;
			case 5:
				System.out.println("ENTER N");
				int n=sc.nextInt();
				int k=s1.size()-n;
				for (int i=s1.size()-1; i>=k; i--){
			          JSONObject itemArr = (JSONObject)s1.get(i);
			          System.out.println(itemArr);
			          }
				
				break;
			}
		}while(opt!=0);    

}

public static void done1() {
System.out.println(" PRESS 1 FOR DEPOSIT HISTRY");
System.out.println(" PRESS 2 FOR WITHDRAWN HISTRY");
System.out.println(" PRESS 3 FOR TRANSFER HISTRY");
System.out.println(" PRESS 4 FOR TOTAL HISTRY");
System.out.println(" PRESS 5 FOR LAST N TRANSACTIONS");

}
 public void transfer(int amt,String a) throws Exception{
	Scanner sc=new Scanner(System.in);
	JSONParser parser = new JSONParser();
	JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("ji.json"));
	JSONArray solutions = (JSONArray)jsonObject.get("accounts");
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Date date = new Date();
	String m=String.valueOf(formatter.format(date)); 
	System.out.println("enter receiving id");
	String b=sc.next();
	int i,j;
	for (i=0; i < solutions.size(); i++){
	    JSONObject itemArr = (JSONObject)solutions.get(i);
	    if(itemArr.get("id").toString().equals(b)){
	    	 s=(String)itemArr.get("IFSC");
	    	break;
	    }
	    }
	System.out.println("enter ifsc");
	String c=sc.next();
	if(c.equals(s)) {
	for (i=0; i < solutions.size(); i++){
	    JSONObject itemArr = (JSONObject)solutions.get(i);
	    if(itemArr.get("id").toString().equals(a)){
	    	long damt=(long)itemArr.get("AMOUNT");
        	 damt-=amt;
        	 JSONArray sol = (JSONArray)itemArr.get("transactions");
        	 JSONObject jsonObject1 = new JSONObject();
		      jsonObject1.put("TYPE","transfer");
		      jsonObject1.put("STATUS","debit");
		      jsonObject1.put("AMOUNT",amt);
		      jsonObject1.put("DATE",m);
		      jsonObject1.put("TO ID",b);
		      sol.add(jsonObject1);
        	 itemArr.put("AMOUNT",damt);
        	 for ( j=0; j < solutions.size(); j++){
     		    JSONObject itemArr1 = (JSONObject)solutions.get(j);
     		    if(itemArr1.get("id").toString().equals(b)){
     		    	long damt1=(long)itemArr1.get("AMOUNT");
   	        	 damt1+=amt;
   	        	JSONArray sol1 = (JSONArray)itemArr1.get("transactions");
	        	 JSONObject jsonObject2 = new JSONObject();
			      jsonObject2.put("TYPE","transfer");
			      jsonObject2.put("STATUS","credit");
			      jsonObject2.put("AMOUNT",amt);
			      jsonObject2.put("DATE",m);
			      jsonObject2.put("FROM ID",a);
			      sol1.add(jsonObject2);
   	        	 itemArr1.put("AMOUNT",damt1);
   	        	System.out.print(damt1);
   	        	 break;
     		    }
	   
        	 }
        	 FileWriter file = new FileWriter("ji.json");
   	      file.write(jsonObject.toJSONString());
   			         file.close();
	    }
	    
	}
	}
	else {
		System.out.print("ifsc does not match with id "+b);
	}
}
		
	public static void main(String[] args)throws Exception {
		
	
		}
	
	
	}