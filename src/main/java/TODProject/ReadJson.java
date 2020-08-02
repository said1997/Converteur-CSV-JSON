package TODProject;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
*La class ReadJSON permet la lecture du fichier qui prend en parametre le chamin du fichier
*/
public class ReadJson extends Configuration {
    //Lecture du fichier qui prend en parametre le chamin du fichier
	/**
    * Cette methode prend en parametre le chemin d'un fichier et renvoi le fichier  sous forme de String
    *@param pathFichier
    *@return ContenuFichier
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
    * contructeur
    * Remplissage de la hachemap avec Cle (nom de la colonne ) et Objet (contenu colonne)
    *@param CheminFichier
    *@exception FileNotFoundException
    *@exception NullPointerException
    @exception JSONException
    */
    //Remplissage de la hachemap Cle (nom de la colonne ) Objet (contenu colonne)
    @SuppressWarnings("unchecked")
	public ReadJson(String CheminFichier) throws FileNotFoundException, NullPointerException,JSONException {
        if (CheminFichier != null) 
        {
            if (!CheminFichier.endsWith(".json")) throw new IllegalArgumentException();
        }
        else throw new NullPointerException();
      //Debut de traitement :
        ArrayList<Object> LignesDeColonne; //contenu de la colonne de type liste d'Objets
        JSONObject JsonObject = new JSONObject(ReadJson.fileToString(CheminFichier));
        String NomColonne; //Cle (nom) de la colonne 
        Map<String,Object> StructJson = new HashMap<String, Object> (); //Pour stoquer le contenu du fichier cles (noms colonne), Objet(lignes colonne)
        
        RemplirStructJson(JsonObject, StructJson);
         
       //initiliser le nombre de colonnes
        nbColomnes = StructJson.size(); //nombre de colonnes (cles dans json)
        Configuration.Fichier = new String[nbColomnes][nbLignes];
    
        int colonne = 0, ligne ;
        int titre = 0;
        Iterator<String> is = StructJson.keySet().iterator();
    
        while(is.hasNext()) {
        	NomColonne = is.next(); //nom de la colonne objet
        	LignesDeColonne = (ArrayList<Object>) StructJson.get(NomColonne);
        	//liste du contenu de la colonne cle
        	Configuration.Fichier[colonne][titre] = new String(NomColonne); // pour mettre la cle de la colonne
        	ligne = 1;// ne pas ecrire sur le titre de la colonne (sauter une ligne en bas)
        	for(Object ContenuColonne : LignesDeColonne) { //pour inserer le contenu de colonnes (ligne par ligne)
        		Configuration.Fichier[colonne][ligne] = ContenuColonne.toString();
        		ligne++; //passer a ligne au desous
        	}
        	colonne++; // passer a la colomne gauche
        }
              
        
    }
    /**
    * Utilisé pour le remplissage de la hashmap dans le constructeur 
    *@param JSONObject contenu fichier apres parse
    *@param StructJson hashmap remplir
    *@exception JSONExceptions
    */
     protected void RemplirStructJson(JSONObject JsonObjects, Map<String, Object> StructJson) throws JSONException { 
            Object Contenu;
            String Cle;
			Iterator<String> iterator = JsonObjects.keys();
          
            while(iterator.hasNext()) { 
                Cle = iterator.next();
                   
                Contenu = JsonObjects.get(Cle); //retourne la liste de la cle ou une sousCle (liste de sous objects)
               
                if(Contenu.getClass() == JSONArray.class) {
                	
                	addToStructJson(Cle, Contenu, StructJson);
                }
                else if (Contenu.getClass()==JSONObject.class) {
                	 //si on a encore des sous objests on les traite
                	RemplirStructJson(JsonObjects.getJSONObject(Cle),StructJson); 
                } 
            }
            
     }
     
    /**
     * Vérifie si il existe des sous objet dans un objet et si oui elle associe une clé aléatoire à chaque sous objet
     *@param Cle
     *@param Contenu
     *@param StructJson
     *@exception JSONException
     */
     
        protected void addToStructJson(String Cle, Object Contenu, Map<String, Object> StructJson) throws JSONException { 
        	
             //traitement de chaque valeur du tableau
                JSONArray ListeJsonObject = (JSONArray) Contenu; //la liste des objects contenu dans la Cle (nom colonne)
               
                int i;
                for(i = 0; i < ListeJsonObject.length(); i++) {
                    if( ListeJsonObject.get(i).getClass() != JSONObject.class && ListeJsonObject.get(i).getClass() != JSONArray.class) { 
                        associateCleObject( ListeJsonObject.get(i), Cle, StructJson); //mettre lobjet dans Lastructure json avec la cle associee
                       
                    }
                    else if ( ListeJsonObject.get(i).getClass() == JSONArray.class){ 
                        JSONObject SubListeJsonObject = new JSONObject();
                        int k = 0;
                        String GenerateCle;
                        do { 
                        	GenerateCle = "PasDeNom" + k++; //generer une cle a linterieur d'une liste d'objets
                        }while(StructJson.containsKey(GenerateCle));
                        SubListeJsonObject.put(GenerateCle, ListeJsonObject.get(i));
                        //traiter La nouvelle sous liste d'objets
                        RemplirStructJson(SubListeJsonObject, StructJson); 
                    }
                    else if (ListeJsonObject.get(i).getClass() == JSONObject.class) {
                    	RemplirStructJson((JSONObject) ListeJsonObject.get(i), StructJson);
                    }
                    
                }
            
        }

        @SuppressWarnings("unchecked")
        /**
        * Cette  methode associe un objet à chaque clé de la hashmap
        * @param Contenu
        * @param Cle
        * @param StructJson
        *@exception JSONException
        */
		protected void associateCleObject(Object Contenu, String Cle, Map<String, Object> StructJson) throws JSONException {
            
           
            if(StructJson.containsKey(Cle)) { 
                //mettre la valeur associee a la cle
                ((ArrayList<Object>) StructJson.get(Cle)).add(Contenu);
               // int size = ((ArrayList<Object>) StructJson.get(Cle)).size(); 
                //if(nbLignes < size + 1) nbColomnes = size + 1;
            }
            else { 
            	//si la cle n'est pas encore ajoutee on ajoute la cle et le contenu associe a la cle 
               ArrayList<Object> liste = new ArrayList<Object>();
                liste.add(Contenu); 
                StructJson.put(Cle,liste);
            }
        }
        
}
