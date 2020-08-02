package TODProject;

public class Configuration {
	
	 //le nombre de lignes
	protected int nbLignes = 5;
	
	//le nombre de valeurs par lignes qui sera definit selon le fichier d'entree
	
	protected int nbColomnes=0;
	
	//initialisation des valeurs du fichier en Json
	
	protected static String[][] Fichier = null;
	
	//les valeurs de ma liste separe par des "," colonnes et lignes "\n"
	
	public String toString() {
		int i, j;
		String s = "";
		for(i = 0; i < nbColomnes; i++) {
       	for(j = 0; j < nbLignes - 1; j++) {
       		s += Fichier[i][j] + ',';
       	}
       	s += Fichier[i][j] + '\n';
       }
		return s;
	}
	//retourne le nb de lignes
	public int getNbLignes() {
		return nbLignes;
	}
	
	//retourne le nb de colonnes
		public int getNbColomnes() {
			return nbColomnes;
		}
	//retourne la valeur de la ligne i et et colonne j
		public String get(int i, int j) throws IndexOutOfBoundsException {
			return Fichier[i][j];
		}
	//modifier une valeure dans le fichier i ligne j colonne
		public void set(String newString, int i, int j) {
	        
	        try {
	        	Fichier[i][j] = newString;
	        }catch (Exception e) {}
		}
		
		public String[][] getArrayCopy(){
			String[][] copy = new String[nbColomnes][nbLignes];
			int i,j;
			for(i = 0; i < nbColomnes; i++) {
				for(j = 0; j <nbLignes; j++) {
					copy[i][j] = (Fichier[i][j] == null)? "" : Fichier[i][j];
				}
			}
			return copy;
		}
		
		
}

