package bank;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import org.json.simple.JSONArray;

public class accountcreation {
	static  String name;
	static  String age;
	static  String aadharno;
	static  String dob;
	static  String phoneno;
	static  String emailid;
	static  String password;
	static String ifsc;
	int amount;
	
	 static public void addAccount() throws Exception
	{
		Scanner sc=new Scanner(System.in);
		int m=0;
		 JSONParser parser = new JSONParser();
	      JSONObject json = (JSONObject) parser.parse(new FileReader("ji.json"));
	      JSONArray solutions = (JSONArray)json.get("accounts");
	      System.out.println("enter id no");
	      String h=sc.next();
	      for (int i=0; i < solutions.size(); i++){
	          JSONObject itemArr = (JSONObject)solutions.get(i);
	         if(itemArr.get("id").toString().equals(h)){
	        	 m++;
	     	}
	         }
	         
		if(m==0)
		{
		System.out.println("enter following datails");
		JSONObject jsonObject = new JSONObject();
		System.out.println("enter name");
		name=sc.next();
	      jsonObject.put("NAME", name);
	      jsonObject.put("id", h);
	      System.out.println("enter age");
			age=sc.next();
	      jsonObject.put("AGE", age);
	      System.out.println("enter dob");
			dob=sc.next();
	      jsonObject.put("DATE OF BIRTH", dob);
	      System.out.println("enter aadharno");
			aadharno=sc.next();
	      jsonObject.put("AADHAR", aadharno);
	      System.out.println("enter phoneno");
			phoneno=sc.next();
	      jsonObject.put("PHNO",phoneno);
	      System.out.println("enter emailid");
			emailid=sc.next();
	      jsonObject.put("EMAIL",emailid);
	      System.out.println("enter password");
			password=sc.next();
	      jsonObject.put("PASSWORD",password);
	      System.out.println("enter IFSC");
	      ifsc=sc.next();
	      jsonObject.put("IFSC",ifsc);
	      jsonObject.put("AMOUNT", 0);
	      JSONArray array=new JSONArray();
	      jsonObject.put("transactions",array);
	      
	      solutions.add(jsonObject);
	      FileWriter file = new FileWriter("ji.json");
	      file.write(json.toJSONString());
			         file.close();
			     System.out.println("Account created successfully");
		}
		else {
			System.out.println("Account already exist");
		}
		}
	public static void delete() throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter id no");
	      String h=sc.next();
	      JSONParser parser = new JSONParser();
	      JSONObject json = (JSONObject) parser.parse(new FileReader("ji.json"));
	      JSONArray solutions = (JSONArray)json.get("accounts");
	      for (int i=0; i < solutions.size(); i++){
	          JSONObject itemArr = (JSONObject)solutions.get(i);
	         if(itemArr.get("id").toString().equals(h)){
	        	 solutions.remove(i);
	        	 }
	         }
	      FileWriter file = new FileWriter("ji.json");
	      file.write(json.toJSONString());
			         file.close();
	      System.out.println("Account deleted successfully");
	         
	}
	
	public static void main(String[] args) throws Exception {
	addAccount();

	}

}
