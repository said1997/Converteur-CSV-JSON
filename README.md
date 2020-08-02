Projet Convertisseur JSON/CSV
Said Mohamed Seghir, Ben mallem Amir Faycal

Dans le cadre du projet du semestre 1 de la première année de master ce convertisseur JSON CSV, CSV JSON a ete crée.

 Mode d'emploi : afin de commencer à utiliser l'outil il suffira de taper sur votre terminal " java -jar /target SaidAmir-1.0-SNAPSHOT-jar-with-dependencies.jar" 
si vous vous trouvez dans le dossier parent de target, il vous sera demandé alors le chemin du fichier que vous souhaiter convertir à la seule condition que celui-ci soit au format CSV où JSON et il sera converti .

Manuel technique : 

A) La class configuration qui permet à l'utilisateur d'entrée ces préférences pour à conversion.La class ReadJson hérite se permet la lecture d'un fichier JSON et ce a travers 3 méthodes :

1- RemplirStructJson(JSONObject JsonObjects, MapString, Object> StructJson):	elle est utilisée afin de remplir la hashmap qui est utilisée dans 	le 		constructeur de la classe, cette hashmap associe à chaque clé un objet.
2- addToStructJson(String Cle, Object Contenu, MapString, Object> StructJson):	 cette méthode Vérifie l'existence de sous-objets dans un objet afin de leur 	générer une clé aléatoire.
3- associateCleObject(Object Contenu, String Cle, Map<String, Object>			StructJson):
	Cette méthode associe à chaque objet une clé.

B) La class WriterCSV  s'occupe d'écrire le résultat en CSV dans un fichier CSV préalablement défini par l'utilisateur ainsi que le nombre de lignes et de colonnes de ce fichier.

C) La class CSVaJSON est là class qui s'occupe de convertir un fichier CSV après l'avoir lu en fichier JSON pour cela elle possède deux méthodes : 
1- fileToString(String pathFichier): cette méthode a pour rôle de prendre le 	chemin d'un fichier et de retourner une chaine de caractères qui stocke le 		contenu de ce fichier.
2-  ConvertToJSON(String CSVFile), la méthode ConvertToJSON prend en paramètre le chemin du fichier à convertir et effectue la conversion.
