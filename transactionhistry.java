package bank;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.*;
public class transactionhistry {
static JSONObject idno;
	public static   void histry(String id) throws Exception{
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
				    done();
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
	
	public static void done() {
		System.out.println(" PRESS 1 FOR DEPOSIT HISTRY");
		System.out.println(" PRESS 2 FOR WITHDRAWN HISTRY");
		System.out.println(" PRESS 3 FOR TRANSFER HISTRY");
		System.out.println(" PRESS 4 FOR TOTAL HISTRY");
		System.out.println(" PRESS 5 FOR LAST N TRANSACTIONS");
		
	}

	public static void main(String[] args) throws Exception{
		histry("1");
		
	}
		
	}


