package avis;

/**
 * @author Yannick LUCET
 * @author Tom VEILLARD 
 */
public class Avis {

	/**
	 * La note donn�e par le membre
	 * 
	 * @see Avis#Avis(float, String, Item, Member)
	 */
	private float note;
	
	/**
	 * Le commentaire associ� � l'avis
	 * 
	 * @see Avis#Avis(float, String, Item, Member)
	 * @see Avis#getNote()
	 */
	private String commentaire;
	
	/**
	 * L'Item concern� par l'avis
	 * 
	 * @see Avis#Avis(float, String, Item, Member)
	 */
	
	private Item item;
	
	/**
	 * Le membre qui a d�pos� l'avis
	 * 
	 * @see Avis#Avis(float, String, Item, Member)
	 */
	private Member member;
	
	/**
	 * Constructeur Avis
	 * <br>
	 * Initialise les attributs d'un avis
	 * 
	 * @param note
	 * 			La note de l'avis � cr�er
	 * @param commentaire
	 * 			le commentaire de l'avis � cr�er
	 * @param item
	 * 			L'item concern� par l'avis
	 * @param member
	 * 			Le membre qui a d�pos� l'avis
	 */
	public Avis(float note, String commentaire, Item item, Member member) {
		this.note = note;
		this.commentaire = commentaire;
		this.item = item;
		this.member = member;
	}
	
	/**
	 *Retourne la note de d�pos�e par le membre sur cet avis.
	 * 
	 * @return
	 * 			Note associ�e � l'avis
	 */
	public float getNote(){
		return note;
	}
	
	/**
	 * Test si l'item en parametre corespond � l'item de l'avis
	 * 
	 * @param item
	 * 			Item � tester
	 * @return
	 * 			True si l'item correspond
	 */
	public boolean itemEquals (Item item){
		if(this.item.equals(item))
			return true;

		return false;
	}
	
	/**
	 * Met � jour la note et le commentaire de l'avis
	 * 
	 * @param note
	 * 			Nouvelle note
	 * @param commentaire
	 * 			Nouveau commentaire
	 */
	public void update (float note, String commentaire){
		this.note = note;
		this.commentaire = commentaire;
	}
	
	public String toString(){
		return "Note : " + note +
				"\nCommentaire : " + commentaire;
	}
}
