package avis;

public class Film extends Item {

	/**
	 * ATTRIBUTS
	 */
	private String realisateur;
	private String scenariste;
	private int duree;

	/**
	 * CONSTRUCTEUR
	 */
	public Film(String titre, String genre, String realisateur, String scenariste, int duree) {
		super(titre, genre);
		this.duree = duree;
		this.scenariste = scenariste;
		this.realisateur=realisateur;
	}

	/**
	 * METHODES
	 */
	public String toString(){
		String retour=super.toString() +
					"Realisateur : " + realisateur + "\n" +
					"Scenariste : " + scenariste + "\n" +
					"Duree : " + duree + "\n";
		
		return retour;
	}
}
