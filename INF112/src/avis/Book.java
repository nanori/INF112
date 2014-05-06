package avis;


public class Book extends Item {

	/**
	 * ATTRIBUTS
	 */
	private String auteur;
	private int nbPages;
	
	/**
	 * CONSTRUCTEUR
	 */
	public Book(String titre, String genre, String auteur, int nbPages) {
		super(titre, genre);
		this.auteur = auteur;
		this.nbPages = nbPages;
	}
	
	/**
	 * METHODES
	 */
}