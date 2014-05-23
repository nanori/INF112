package avis;

/**
 * @author Yannick LUCET
 * @author Tom VEILLARD
 */
public class Opinion {

	/**
	 * Opinion donnée par un Membre sur un Avis <br>
	 * TRUE si l'Opinion est positive <br>
	 * FALSE si l'Opinion est négative
	 * 
	 * @see Opinion#updateOpinion(boolean)
	 * @see Opinion#getOpinionMark()
	 */
	private boolean opinion;

	/**
	 * Avis sur lequel l'Opinion est donnée
	 * 
	 * @see Opinion#avisEquals(Avis)
	 */
	private Avis avis;

	/**
	 * Constructeur Opinion <br>
	 * Initialise les attribut d'une Opinion
	 * 
	 * @param opinion
	 *            Opinion donnée sur un Avis
	 * @param avis
	 *            Avis sur lequel l'Opinion est donnée
	 */
	public Opinion(boolean opinion, Avis avis) {
		this.opinion = opinion;
		this.avis = avis;
	}

	/**
	 * Change une Opinion donnée par un Membre sur un Avis
	 * 
	 * @param opinion
	 *            Nouvelle Opinion
	 */
	public void updateOpinion(boolean opinion) {
		this.opinion = opinion;
	}

	/**
	 * Test si l'Avis en parametre est l'Avis sur lequel l'Opinion est donnée
	 * 
	 * @param avis
	 *            Avis à tester
	 * @return True si l'Avis correspond
	 */
	public boolean avisEquals(Avis avis) {
		if (this.avis.equals(avis))
			return true;

		return false;
	}

	/**
	 * Retourne la note associée a l'Opinion
	 * 
	 * @return 1 si l'Opinion est positive -1 si l'Opinion est négative
	 */
	public int getOpinionMark() {
		if (this.opinion)
			return 1;

		return -1;
	}
}
