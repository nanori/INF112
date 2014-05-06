package avis;

import java.util.Collection;
import java.util.LinkedList;

public abstract class Item {

	/**
	 * ATTRIBUTS
	 */
	private String titre;
	private String genre;
	private LinkedList<Avis> avis;
	private SocialNetwork socialNetwork = null;

	/**
	 * CONSTRUCTEUR
	 */
	public Item (String titre, String genre){
		this.titre=titre;
		this.genre=genre;
	}
	
	/**
	 * METHODES
	 */
	public boolean exists(String titre) {
		if(this.titre.trim().equalsIgnoreCase(titre))
			return true;
		
		return false;
	}

	public void addReviewToCollection(Avis avis) {
	}

}
