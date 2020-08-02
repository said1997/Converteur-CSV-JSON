package TODProject;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReadJsonTest {

	 private File f;
	private ReadJson test;
	JSONObject JsonObject = new JSONObject( "{\"Nom étudiant\": [\"tebboune\",\"zizi\",\"bachir\",\"taher\"],"
			+ "\"age\": "
			+ "[\"25\",\"26\",\"27\"],"
			+ "\"dommaine\": [\"La blanche\",\"chomeur\",\"droit\",\"catholique\"],}  ");
	

	@Before
	public void CreationFichierTest() {
		f = new File("CsvTest.csv");
	}
	
	@After
	public void ClearFichierTest(){
		f.delete();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testReadinvalideformat() throws FileNotFoundException, NullPointerException, JSONException {
		new ReadJson("test.txt");
		 
	}
	
	@Test(expected=FileNotFoundException.class)
	public void fileToStringTest () throws FileNotFoundException{
		ReadJson.fileToString("Une_chaine_decaracteres");
	}
	
	
	@Test(expected=NullPointerException.class)
	public void RemplirStructJsonTest () throws FileNotFoundException{
		
		 Map<String,Object> StructJson = new HashMap<String, Object> ();
		test=null;
		 test.RemplirStructJson(JsonObject, StructJson);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void addToStructJsonTest () throws FileNotFoundException{
		Object C = "\"Nom étudiant\":[\"tebboune\",\"zizi\",\"bachir\",\"taher\"]" ;
		Map<String,Object> StructJson = new HashMap<String, Object> ();
		test=null;
		
		 test.RemplirStructJson(JsonObject, StructJson);
		 
		 test.addToStructJson("Nom étudiant",C,StructJson);
		 
		 
		
	}
	
	
	
	

}
