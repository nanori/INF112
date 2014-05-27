package avis;

/**
 * @author Yannick LUCET
 * @author Tom VEILLARD
 */
public class Avis {

	/**
	 * Note donn�e par le membre
	 * 
	 * @see Avis#getNote()
	 * @see Avis#update(float, String)
	 */
	private float note;

	/**
	 * Commentaire associ� � l'avis
	 * 
	 * @see Avis#update(float, String)
	 */
	private String commentaire;

	/**
	 * L'Item concern� par l'avis
	 * 
	 * @see Avis#itemEquals(Item)
	 */
	private Item item;

	/**
	 * Le membre qui a d�pos� l'avis
	 */
	private Member member;

	/**
	 * Pond�ration de la note en fonction du karma du membre qui l'a d�pos�
	 * @see Avis#updatePonderation()
	 */
	private float ponderation;

	/**
	 * Constructeur Avis <br>
	 * Initialise les attributs d'un avis
	 * 
	 * @param note
	 *            La note de l'avis � cr�er
	 * @param commentaire
	 *            le commentaire de l'avis � cr�er
	 * @param item
	 *            L'item concern� par l'avis
	 * @param member
	 *            Le membre qui a d�pos� l'avis
	 */
	public Avis(float note, String commentaire, Item item, Member member) {
		this.note = note;
		this.commentaire = commentaire;
		this.item = item;
		this.member = member;
		ponderation = member.getKarma();
	}

	/**
	 * Retourne la note et sa pond�ration d�pos�e par le membre sur cet avis.
	 * 
	 * @return Tableau contenant la note et sa pond�ration associ�e � l'avis
	 */
	public float[] getNote() {
		float r[] = { note, ponderation };
		return r;
	}

	/**
	 * Test si l'item en parametre correspond � l'item de l'avis
	 * 
	 * @param item
	 *            Item � tester
	 * @return True si l'item correspond
	 */
	public boolean itemEquals(Item item) {
		if (this.item.equals(item))
			return true;

		return false;
	}

	/**
	 * Met � jour la note et le commentaire de l'avis
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
	 * Met � jour la pond�ration de la note laiss�e par un Membre sur l'Avis
	 */
	public void updatePonderation() {
		this.ponderation = member.getKarma();
	}

	public String toString() {
		return "Note : " + note + "\nCommentaire : " + commentaire;
	}
}
