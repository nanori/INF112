package avis;

import java.util.LinkedList;

/**
 * @author Yannick LUCET
 * @author Tom VEILLARD
 */

public abstract class Item {
	
	/**
	 * Titre de l'item
	 * 
	 * @see Item#Item(String, String)
	 * @see Item#exists(String)
	 */
	private String titre;
	
	/**
	 * Genre de l'item
	 * 
	 * @see Item#Item(String, String)
	 */
	private String genre;
	
	/**
	 * Liste contenant les avis sur l'item
	 */
	private LinkedList<Avis> avis;
	
	/**
	 * Constructeur Item
	 * <br>
	 * A la construction d'un item, la liste avis est instanciée avec une liste vide.
	 * 
	 * @param titre
	 * 			Titre de l'item à créer
	 * @param genre
	 * 			Genre de l'item créer
	 */
	public Item (String titre, String genre){
		this.avis = new LinkedList<Avis>();
		this.titre = titre.trim();
		this.genre = genre;
	}
		
	/**
	 * Test si le titre en parametre correspond au titre de l'item.
	 * <br>
	 * Les espaces avant et apres le titre en parametre sont ignorés. La methode n'est pas sensible à la casse 
	 * 
	 * 
	 * @param titre
	 * 			Titre de l'item à tester
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
	 * Ajoute un avis à la LinkedList avis
	 * 
	 * @param avis
	 * 			Avis à ajouter
	 */
	public void addReviewToCollection(Avis avis) {
		this.avis.addLast(avis);
	}
	
	/**
	 * Calcule et retourne la moyenne des notes sur l'item
	 * 
	 * @return
	 * 			Moyenne des notes de l'item
	 */
	public float getMoyenne(){
		float moy=0;
		float tmpTab [];
		float sommeNotes = 0;
		float sommePonderations = 0;
		for(int i=0; i<avis.size(); i++){
			tmpTab=avis.get(i).getNote();
			sommeNotes += tmpTab[0]*tmpTab[1];
			//System.err.println("tmpTab[0] = " + tmpTab[0] + " tmpTab[1] " + tmpTab[1]);
			sommePonderations += tmpTab[1];
			//System.err.println("sommeNotes = " + sommeNotes + " sommePonderations " + sommePonderations);
		}
		moy = sommeNotes / sommePonderations;
		
		return moy;
	}
	
	public String toString(){
		String retour="Titre : " + titre + "\n" +
					"Genre : " + genre + "\n" + 
					"Note Moyenne : " + getMoyenne() + "\n";
					
		
		return retour;
	}
}
