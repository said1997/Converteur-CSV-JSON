package TODProject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;



public enum App {
	Main;
		

	
	private static Scanner sc;

	public static void main( String[] args ) throws IOException, FileNotFoundException, NullPointerException,JSONException,CSVValidationException, IllegalArgumentException, InvalidCSVFileException {
		
		sc = new Scanner(System.in);
		System.out.println("entrez un fichier json a convertir en csv like \"/home/user/eclipse-java-neon-workspace/prijet/project/SaidAmir/test.json\" :");
		String fichier_in = sc.nextLine();
		
		while(fichier_in.isEmpty()) {
			fichier_in = sc.nextLine();
		}

		System.out.println("entrer un fichier de sortir csv");
		String fichier_out = sc.nextLine();
		if (fichier_out.isEmpty()) 
			fichier_out = fichier_in;

		 if(fichier_in.endsWith(".json")){ 
			 ReadJson fichierJson = new ReadJson(fichier_in);
			 WriteCSV.writeCSV(fichier_out,fichierJson.getNbColomnes(), fichierJson.getNbLignes());
			
			 //CSVaJSON c = new CSVaJSON();
			
			 //c.ConvertToJSON(fichier_out);
			}
			else throw new FileNotFoundException(); 
		
	}
	
}
