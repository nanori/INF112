package avis;

import java.util.LinkedList;
/**
 * @author Yannick LUCET
 * @author Tom VEILLARD
 */
public class Member {

	/**
	 * Pseudo du membre
	 * 
	 * @see Member#exists(String)
	 * @see Member#exists(String, String)
	 */
	private String pseudo;
	
	/**
	 * Mot de passe du membre
	 * 
	 * @see Member#exists(String, String)
	 */
	private String password;
	
	/**
	 * Profil du membre
	 */
	private String profil;
	
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
	 */
	public Member(String pseudo, String password, String profil){
		this.pseudo=pseudo;
		this.password=password;
		this.profil=profil;
		avis = new LinkedList<Avis>();
	}
	
	/**
	 * Test si le couple pseudo mot de passe correspond au membre.<br>
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
	
		if(this.pseudo.equalsIgnoreCase(pseudo.trim()) && this.password.equals(password)){
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
	 * Ajoute un avis à la fin de la linkedList avis
	 * 
	 * @param avis
	 * 			Avis à ajouter
	 */
	public void addReviewToCollection(Avis avis) {
		this.avis.addLast(avis);
	}
	
	/**
	 * Renvoi l'avis de l'item passé en parametre
	 * 
	 * @param item
	 * 			Item de réference
	 * @return
	 * 			Avis concerné par l'item ou null si l'item n'a pas été noté par ce membre
	 */
	public Avis getReview(Item item){
		int i=0;
		
		while (i<avis.size()){
			if (avis.get(i).itemEquals(item))
				return avis.get(i);
			
			i++;
		}
		
		return null;
	}
	
	public String toString(){
		String retour="Pseudo : " + pseudo + "\n" +
				"Profil : "+ profil + "\n";
		
		return retour;
	}

}
