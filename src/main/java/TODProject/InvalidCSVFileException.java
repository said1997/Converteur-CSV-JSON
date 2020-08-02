package TODProject;
    /** 
    * Class d'exception qui est appelé lorsque le fichier CSV choisit par l'utilisateur 
    * est incorrect
    */
public class InvalidCSVFileException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCSVFileException() {

		super("Le fichier choisit contient une syntaxe CSV fausse");
	}
}