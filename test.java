package bank;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class test {

	public static void main(String[] args) throws Exception{
		

//JSONArray mn=jk.getJSONArray("accounts");

/*Iterator<Object> it=mn.iterator();
while(it.hasNext())
{
	JSONObject kl=(JSONObject) it.next();
	
	if(kl.getString("name").equals("gnanaprakash"))
	{
	kl.put("name","david");
    }
	System.out.print(kl)
	
}*/
		JSONObject s=new JSONObject();
		s.put("id", "3");
JSONParser parser = new JSONParser();
JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("ji.json"));
JSONArray solutions = (JSONArray)jsonObject.get("accounts");
for (int i=0; i < solutions.size(); i++){
    JSONObject itemArr = (JSONObject)solutions.get(i);
   if(itemArr.get("id").toString().equals("3")){
    
    	itemArr.put("NAME", "raj");
    	JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("ko.json"));
		JSONArray solutions = (JSONArray)jsonObject.get("accounts");
		
		for (int i=0; i < solutions.size(); i++){
		    JSONObject itemArr = (JSONObject)solutions.get(i);
		    if(itemArr.get("id").toString().equals("3")){
		    JSONArray sol = (JSONArray)itemArr.get("acc");
		    for (int j=0; j< sol.size(); j++){
		   System.out.print(sol.get(j));
		    }
		}
		}
		
		 FileWriter file = new FileWriter("ko.json");
	      file.write(jsonObject.toJSONString());
			         file.close();
    	
   
}


FileWriter file1 = new FileWriter("ji.json");
file1.write(jsonObject.toJSONString());
file1.close();
//System.out.println(itemArr);

}
}
}
