package avis;
/**
 * @author Yannick LUCET
 * @author Tom VEILLARD
 */
public class Film extends Item {

	/**
	 * Nom du r�alisateur du film
	 */
	private String realisateur;
	
	/**
	 * Nom du scenariste du film
	 */
	private String scenariste;
	
	/**
	 * Dur�e du film en minute
	 */
	private int duree;

	/**
	 * Initialise les attributs
	 * 
	 * @param titre
	 * 			Titre du film
	 * @param genre
	 * 			Genre du film
	 * @param realisateur
	 * 			Nom du r�alisateur du film
	 * @param scenariste
	 * 			Nom du scenariste du film
	 * @param duree
	 * 			Dur�e du film en minute
	 */
	public Film(String titre, String genre, String realisateur, String scenariste, int duree) {
		super(titre, genre);
		this.duree = duree;
		this.scenariste = scenariste;
		this.realisateur=realisateur;
	}

	/**
	 * Retoune un string decrivant le film
	 * 
	 * @return
	 * 			string decrivant le film
	 */
	public String toString(){
		String retour=super.toString() +
					"Realisateur : " + realisateur + "\n" +
					"Scenariste : " + scenariste + "\n" +
					"Duree : " + duree + "\n";
		
		return retour;
	}
}
