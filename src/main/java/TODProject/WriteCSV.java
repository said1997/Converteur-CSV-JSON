package TODProject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
/**
* Cette classe permet l'ecriture d'un fichier au format CSV
*/
public class WriteCSV extends Configuration {
	/**
	* Ecrit un fichier en CSV en prenant en parametre son chemin le nombre de lignes et de colonnes
	* @param CheminFichier
	* @param nbColonnes
	* @param nbLignes
	* @exception IOException 
	* @exception IllegalArgumentException
	* @exception NullPointerException
	*/
	public static void writeCSV(String CheminFichier, int nbColonnes, int nbLignes) throws IOException, IllegalArgumentException, NullPointerException {
		if(CheminFichier == null) throw new NullPointerException ();
		if(!CheminFichier.endsWith(".csv")) {
			CheminFichier = CheminFichier + ".csv";
		}
		File f = new File(CheminFichier);
		if(f.exists()) {
			System.err.println("Le chemin du fchier" + CheminFichier + " existe deja");
			throw new IllegalArgumentException ();
		}
		int i;
		ArrayList <String> liste;
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(CheminFichier));
		CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
		
		for(int j = 0; j < nbLignes; j++) {
			liste = new ArrayList <String> ();
			for(i = 0; i < nbColonnes; i++) {
				liste.add(Fichier[i][j]);
			}
			csvPrinter.printRecord(liste);
		}
		 System.out.println("fichier_cree_avec_succes");
		csvPrinter.close(true);
	}

	
}



