package avis;

public class Avis {

	/**
	 * ATTRIBUTS
	 */
	private float note;
	private String commentaire;
	private Item item = null;
	private Member member = null;
	
	/**
	 * CONSTRUCTEUR
	 */
	public Avis(float note, String commentaire, Item item, Member member) {
		super();
		this.note = note;
		this.commentaire = commentaire;
		this.item = item;
		this.member = member;
	}
	
	/**
	 * METHODES
	 */

}
