package avis;

/**
 * @author Yannick LUCET
 * @author Tom VEILLARD
 */
public class Avis {

	/**
	 * Note donnée par le membre
	 * 
	 * @see Avis#getNote()
	 * @see Avis#update(float, String)
	 */
	private float note;

	/**
	 * Commentaire associé à l'avis
	 * 
	 * @see Avis#update(float, String)
	 */
	private String commentaire;

	/**
	 * L'Item concerné par l'avis
	 * 
	 * @see Avis#itemEquals(Item)
	 */
	private Item item;

	/**
	 * Le membre qui a déposé l'avis
	 */
	private Member member;

	/**
	 * Pondération de la note en fonction du karma du membre qui l'a déposé
	 * @see Avis#updatePonderation()
	 */
	private float ponderation;

	/**
	 * Constructeur Avis <br>
	 * Initialise les attributs d'un avis
	 * 
	 * @param note
	 *            La note de l'avis à créer
	 * @param commentaire
	 *            le commentaire de l'avis à créer
	 * @param item
	 *            L'item concerné par l'avis
	 * @param member
	 *            Le membre qui a déposé l'avis
	 */
	public Avis(float note, String commentaire, Item item, Member member) {
		this.note = note;
		this.commentaire = commentaire;
		this.item = item;
		this.member = member;
		ponderation = member.getKarma();
	}

	/**
	 * Retourne la note et sa pondération déposée par le membre sur cet avis.
	 * 
	 * @return Tableau contenant la note et sa pondération associée à l'avis
	 */
	public float[] getNote() {
		float r[] = { note, ponderation };
		return r;
	}

	/**
	 * Test si l'item en parametre correspond à l'item de l'avis
	 * 
	 * @param item
	 *            Item à tester
	 * @return True si l'item correspond
	 */
	public boolean itemEquals(Item item) {
		if (this.item.equals(item))
			return true;

		return false;
	}

	/**
	 * Met à jour la note et le commentaire de l'avis
	 * 
	 * @param note
	 *            Nouvelle note
	 * @param commentaire
	 *            Nouveau commentaire
	 */
	public void update(float note, String commentaire) {
		this.note = note;
		this.commentaire = commentaire;
	}

	/**
	 * Met à jour la pondération de la note laissée par un Membre sur l'Avis
	 */
	public void updatePonderation() {
		this.ponderation = member.getKarma();
	}

	public String toString() {
		return "Note : " + note + "\nCommentaire : " + commentaire;
	}
}
