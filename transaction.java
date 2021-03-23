package bank;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class transaction {
	
static String s;
	static public void transfer(int amt,String a) throws Exception{
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
	public static void main(String[] args) throws Exception {
		
	}

}
