package avis;

import java.util.Collection;

public class Member {

	/**
	 * ATTRIBUTS
	 */
	private String pseudo;
	private String password;
	private String profil;
	private SocialNetwork socialNetwork = null;
	private Collection avis;
	
	/**
	 * CONSTRUCTEUR
	 */
	
	/**
	 * METHODES
	 */
	public boolean exists(String pseudo, String password) {

		return false;
	}

	public void addReviewToCollection(Avis avis) {
	}

}
