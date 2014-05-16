package avis;

import java.util.LinkedList;

/**
 * @author Yannick LUCET
 * @author Tom VEILLARD
 */

public abstract class Item {
	
	/**
	 * Le titre de l'item
	 * 
	 * @see Item#Item(String, String)
	 * @see Item#exists(String)
	 */
	private String titre;
	
	/**
	 * Le genre de l'item
	 * 
	 * @see Item#Item(String, String)
	 */
	private String genre;
	
	/**
	 * Liste contenant les avis sur l'item
	 */
	private LinkedList<Avis> avis;
	
	/**
	 * Le socialNetwork
	 */
	private SocialNetwork socialNetwork = null;

	
	/**
	 * Constructeur Item
	 * <br>
	 * A la construction d'un item, la liste avis est instanci�e avec une liste vide.
	 * 
	 * @param titre
	 * 			Le titre de l'item � cr�er
	 * @param genre
	 * 			le genre de l'item cr�er
	 */
	public Item (String titre, String genre){
		this.avis = new LinkedList<Avis>();
		this.titre=titre;
		this.genre=genre;
	}
	
		
	/**
	 * Test si le titre en parametre correspond au titre de l'item.
	 * <br>
	 * Les espaces avant et apres le titre en parametre sont ignor�s. La methode n'est pas sensible � la casse 
	 * 
	 * 
	 * @param titre
	 * 			Titre de l'item � tester
	 * @return 
	 * 			Vrai si les deux titres correspondent. <br>
	 * 			Faux si non
	 */
	public boolean exists(String titre) {
		if(this.titre.equalsIgnoreCase(titre.trim()))
			return true;
		
		return false;
	}

	/**
	 * Ajoute un avis � la LinkedList avis
	 * 
	 * @param avis
	 * 			Avis � ajouter
	 */
	public void addReviewToCollection(Avis avis) {
		this.avis.addLast(avis);
	}
	
	/**
	 * Calcul et retourne la moyenne des notes sur l'item
	 * 
	 * @return
	 * 			Moyenne des notes de l'item
	 */
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
					"Genre : " + genre + "\n" + 
					"Note Moyenne : " + getMoyenne() + "\n";
					
		
		return retour;
	}
}
