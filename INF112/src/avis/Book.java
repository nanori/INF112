package avis;

/**
 * @author Yannick LUCET
 * @author Tom VEILLARD
 */
public class Book extends Item {

	/**
	 * Nom de l'auteur du livre
	 */
	private String auteur;

	/**
	 * Nombre de pages du livre
	 */
	private int nbPages;

	/**
	 * Initialise les attributs
	 * 
	 * @param titre
	 *            Titre du livre
	 * @param genre
	 *            Genre du livre
	 * @param auteur
	 *            Nom de l'auteur
	 * @param nbPages
	 *            Nombre de pages du livre
	 */
	public Book(String titre, String genre, String auteur, int nbPages) {
		super(titre, genre);
		this.auteur = auteur;
		this.nbPages = nbPages;
	}

	public String toString() {
		String retour =
				super.toString() + "Auteur : " + auteur + "\n"
						+ "Nombre pages : " + nbPages + "\n";

		return retour;
	}
}
