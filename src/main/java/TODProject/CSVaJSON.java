package TODProject;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.Scanner;
 /** 
   * La class CSVaJSON s'occupe de la lecture ensuite de la conversion d'un fichier CSV a un fichier JSON
   */
public class CSVaJSON {
	private String CSVFile;

	public String getCSVFile() {
		return CSVFile;
	}

	public void setCSVFile(String cSVFile) {
		CSVFile = cSVFile;
	}
	/**
	 * cette methode prend en parametre le chemin d'un fichier et le transforme en une chaine de caractere
	 * @param pathFichier
	 * @exception FileNotFoundException
	 */
	public static String fileToString(String pathFichier) throws FileNotFoundException {
        File NomFichier= new File (pathFichier);
        String ContenuFichier = "";
        Scanner in = new Scanner(NomFichier);
        
        while(in.hasNext()) {
            ContenuFichier += in.nextLine();
        }
        in.close();
        return ContenuFichier;
    }
	/** 
    * Cette methode qui prend en parametre le chemin d'un fichier s'occupe de le convertir du format CSV a JSON
    * à condition que le fichier CSV de depart soit correct
    * @param CSVFile path of file
    */
	public void ConvertToJSON(String CSVFile) throws IOException, CsvValidationException, InvalidCSVFileException {
		CSVReader reader = null;
		String [] line;
		String data ="";
		String columns[];
		int colnumber = 0;
		String tokens[];
		String ResultaEnStr = "";
		if(!CSVFile.endsWith(".csv")) {
			CSVFile= CSVFile+ ".csv";
		}
		reader = new CSVReader(new FileReader(CSVFile));
		
		String Fsortie = CSVFile.toString().substring(0, CSVFile.toString().lastIndexOf(".")) + ".json"; 
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(Fsortie)));
        
        line = reader.readNext();
        for ( String e : line) {
        	data += e+ ",";
        }
       

        columns = data.split(",");
        colnumber = columns.length;
        data = "";
        
        line = reader.readNext();
        for ( String e : line) {
        	data += e+ ",";
        }
       
        ResultaEnStr += "[";
        writer.write("["); 
        writer.flush();
       // data = "";
        while(true) {
        	tokens = data.split(",");


        	if(tokens.length == colnumber) {
        		ResultaEnStr += "{";
        		writer.write("{");
        		writer.flush();
        		for(int i = 0;i < colnumber;++i) {
        			if (tokens[i].matches("^-?[0-9]*\\.?[0-9]*$")) {
        				ResultaEnStr += "\"" + columns[i] + "\": " + tokens[i];
        				writer.write("\"" + columns[i] + "\": " + tokens[i]);
        				writer.flush();
        				if (i < colnumber - 1) writer.write(", ");
        			}
                    else { 
                    	ResultaEnStr += "\"" + columns[i] + "\": \"" + tokens[i] + "\"";
                        writer.write("\"" + columns[i] + "\": \"" + tokens[i] + "\"");
                        if (i < colnumber - 1) writer.write(", ");
                        	writer.write(", ");
                        	ResultaEnStr += ", ";
                        }
                    }
        		
                if((line = reader.readNext()) != null){
                	data = "";
                    for ( String e : line) {
                    	data += e+ ",";
                    }
                   
                	ResultaEnStr += "}, ";
                    writer.write("},");
                    writer.newLine();
                }
                else{
                	
                	ResultaEnStr += "}]";
                    writer.write("}]");
            		writer.flush();
                    writer.newLine();

                    break;
                }
                
			}
			else if(tokens.length != colnumber) {
        		throw new InvalidCSVFileException();
        	}
        }
	} 


}
