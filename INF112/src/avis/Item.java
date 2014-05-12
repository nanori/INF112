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
		this.avis.addlast(avis);
	}
	
	public float getMoyenne(){
		float moy=0;
		for(int i=0; i<avis.size(); i++){
			moy+=avis.get(i).getNote();
		}
		moy = moy / avis.size();
		
		return moy;
	}
	
	public String toString(){
		String retour="Titre : " + titre + "\n" +
					"Genre : " + genre + "\n";
		
		return retour;
}
