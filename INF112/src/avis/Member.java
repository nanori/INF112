package avis;

import java.util.LinkedList;
/**
 * @author Yannick
 *
 */
public class Member {

	/**
	 * Le pseudo du membre
	 */
	private String pseudo;
	
	/**
	 * Mot de passe du membre
	 */
	private String password;
	
	/**
	 * Profil du membre
	 */
	private String profil;
	
	/**
	 * Le SocialNetwork
	 */
	private SocialNetwork socialNetwork = null;
	
	/**
	 * Liste des avis déposés par le membre
	 */
	private LinkedList <Avis> avis;
	
	/**
	 * Initialise les attributs
	 * 
	 * @param pseudo
	 * 			Pseudo du membre
	 * @param password
	 * 			Mot de passe du membre
	 * @param profil
	 * 			Profil du membre
	 * @param socialNetwork
	 * 			Le SocialNetwork
	 */
	public Member(String pseudo, String password, String profil, SocialNetwork socialNetwork){
		this.pseudo=pseudo;
		this.password=password;
		this.profil=profil;
		this.socialNetwork = socialNetwork;
		avis = new LinkedList<Avis>();
	}
	
	
	/**
	 * Test si le couple pseudo mot de passe corespond au membre.<br>
	 * Les espaces avant et apres le titre en parametre sont ignorés. La methode n'est pas sensible à la casse.
	 * @param pseudo
	 * 			Pseudo à tester
	 * @param password
	 * 			Mot de passe à tester
	 * 
	 * @return
	 * 			Vrai si le couple pseudo mot de passe correspond au membre. <br>
	 * 			Faux si non
	 */
	public boolean exists(String pseudo, String password) {
		
		if(this.pseudo.trim().equalsIgnoreCase(pseudo) && this.password==password){
			return true;
		}else{ 
			return false;
		}
	}
	
	/**
	 * Verifie si le pseudo est celui du membre.
	 * 
	 * @param pseudo
	 * 			Pseudo à tester
	 * @return
	 * 			Vrai si le pseudo correspond au membre. <br>
	 * 			Faux si non
	 */
	public boolean exists(String pseudo){
		return this.pseudo.equalsIgnoreCase(pseudo.trim());
	}

	/**
	 * Ajoute un avis à la linkedList avis
	 * 
	 * @param avis
	 * 			Avis à ajouter
	 */
	public void addReviewToCollection(Avis avis) {
		this.avis.addLast(avis);
	}
	
	
	public String toString(){
		String retour="Pseudo : " + pseudo + "\n" +
				"Profil : "+ profil + "\n";
		
		return retour;
	}

}
