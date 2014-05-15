package avis;

/**
 * @author Yannick LUCET
 * @author Tom VEILLARD 
 */
public class Avis {

	/**
	 * La note donnée par le membre
	 * 
	 * @see Avis#Avis(float, String, Item, Member)
	 */
	private float note;
	
	/**
	 * Le commentaire associé à l'avis
	 * 
	 * @see Avis#Avis(float, String, Item, Member)
	 * @see Avis#getNote()
	 */
	private String commentaire;
	
	/**
	 * L'Item concerné par l'avis
	 * 
	 * @see Avis#Avis(float, String, Item, Member)
	 */
	
	private Item item = null;
	
	/**
	 * Le membre qui a déposé l'avis
	 * 
	 * @see Avis#Avis(float, String, Item, Member)
	 */
	private Member member = null;
	
	/**
	 * Constructeur Avis
	 * <br>
	 * Initialise les attributs d'un avis
	 * 
	 * @param note
	 * 			La note de l'avis à créer
	 * @param commentaire
	 * 			le commentaire de l'avis à créer
	 * @param item
	 * 			L'item concerné par l'avis
	 * @param member
	 * 			Le membre qui a déposé l'avis
	 */
	public Avis(float note, String commentaire, Item item, Member member) {
		this.note = note;
		this.commentaire = commentaire;
		this.item = item;
		this.member = member;
	}
	
	/**
	 *Retourne la note de déposée par le membre sur cet avis
	 * 
	 * @return
	 * 			Note associée à l'avis
	 */
	public float getNote(){
		return note;
	}
}
