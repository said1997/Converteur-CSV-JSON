package TODProject;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.undo.CannotRedoException;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class WriteCSVTest {

	private File f;

	@Before
	public void CreationFichierTest() {
		f = new File("CsvTest.csv");
	}
	
	@After
	public void ClearFichierTest(){
		f.delete();
	}
	
	@Test(expected=NullPointerException.class)
	public void testCreatingvalideformatcsv() throws NullPointerException, JSONException, IllegalArgumentException, IOException {
		
			WriteCSV.writeCSV(null,88,15);
		 
	} 
	
}
