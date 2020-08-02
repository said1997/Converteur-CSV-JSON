package TODProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import com.opencsv.exceptions.CsvValidationException;

public class CSVaJSONtest {

	@Test (expected = InvalidCSVFileException.class)
	public void FileNonValide() throws  CsvValidationException, InvalidCSVFileException, IOException {

		CSVaJSON c = new CSVaJSON();
		c.ConvertToJSON("/Users/tokarev/Desktop/t.csv");
		
	}
	@Test (expected = FileNotFoundException.class)
	public void FileNotFound() throws CsvValidationException, IOException, InvalidCSVFileException {
		CSVaJSON c = new CSVaJSON();
		c.ConvertToJSON("");
		
	}
	@Test
	public  void CompareRes() throws CsvValidationException, IOException, InvalidCSVFileException {
		String s1 = "";
		String s2 = "";
		CSVaJSON c = new CSVaJSON();
		c.ConvertToJSON("/Users/tokarev/Desktop/essai.csv");
		s1 =  c.fileToString("/Users/tokarev/Desktop/essai.json");
		s2 = c.fileToString("/Users/tokarev/Desktop/expected.json");
		assertEquals(s1,s2);
	}
	
	@Test
	public  void Noteq() throws CsvValidationException, IOException, InvalidCSVFileException {
		String s1 = "";
		String s2 = "";
		CSVaJSON c = new CSVaJSON();
		c.ConvertToJSON("/Users/tokarev/Desktop/essai.csv");
		s1 =  CSVaJSON.fileToString("/Users/tokarev/Desktop/essai.json");
		s2 = CSVaJSON.fileToString("/Users/tokarev/Desktop/t.json");
		assertNotEquals(s1,s2);
	}
	
	

}
