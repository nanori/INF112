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
	private LinkedList <Avis> avis;
	
	/**
	 * CONSTRUCTEUR
	 */
	
	public Member(String pseudo, String password, String profil, SocialNetwork socialNetwork){
		this.pseudo=pseudo;
		this.password=password;
		this.profil=profil;
		this.socialNetwork = socialNetwork;
		avis = new LinkedList<Avis>();
	}
	
	/**
	 * METHODES
	 */
	
	public boolean exists(String pseudo, String password) {
		
		if(this.pseudo.trim().equalsIgnoreCase(pseudo) && this.password==password){
			return true;
		}else{ 
			return false;
		}
	}
	
	public boolean exists(String pseudo){
		return this.pseudo.trim().equalsIgnoreCase(pseudo);
	}

	public void addReviewToCollection(Avis avis) {
		this.avis.addLast(avis);
	}
	
	public String toString(){
		String retour="Pseudo : " + pseudo + "\n" +
				"Profil : "+ profil + "\n";
		
		return retour;
	}

}
