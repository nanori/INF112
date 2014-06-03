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
	 * 
	 */
	private String profil;

	/**
	 * Liste des avis d�pos�s par le membre
	 */
	private LinkedList<Avis> avis;

	/**
	 * Liste des opinions d�pos�s par le membre
	 */
	private LinkedList<Opinion> opinions;

	/**
	 * Karma du membre. <br>
	 * Flottant compris entre 0 et 2.<br>
	 * Plus les Opinions sur ses Avis sont positifs, plus le karma du membre est
	 * �lev�
	 */
	private float karma;

	/**
	 * Initialise les attributs
	 * 
	 * @param pseudo
	 *            Pseudo du membre
	 * @param password
	 *            Mot de passe du membre
	 * @param profil
	 *            Profil du membre
	 */
	public Member(String pseudo, String password, String profil) {
		this.pseudo = pseudo;
		this.password = password;
		this.profil = profil;
		avis = new LinkedList<Avis>();
		opinions = new LinkedList<Opinion>();
		karma = 1.0f;
	}

	/**
	 * Test si le couple pseudo mot de passe correspond au membre.<br>
	 * Les espaces avant et apres le titre en parametre sont ignor�s. La methode
	 * n'est pas sensible � la casse.
	 * 
	 * @param pseudo
	 *            Pseudo � tester
	 * @param password
	 *            Mot de passe � tester
	 * 
	 * @return Vrai si le couple pseudo mot de passe correspond au membre. <br>
	 *         Faux si non
	 */
	public boolean exists(String pseudo, String password) {

		if (this.pseudo.equalsIgnoreCase(pseudo.trim())
				&& this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Verifie si le pseudo est celui du membre.
	 * 
	 * @param pseudo
	 *            Pseudo � tester
	 * @return Vrai si le pseudo correspond au membre. <br>
	 *         Faux si non
	 */
	public int exists(String pseudo) {
		return pseudo.trim().compareToIgnoreCase(this.pseudo.trim());
	}

	/**
	 * Ajoute un avis � la fin de la linkedList avis
	 * 
	 * @param avis
	 *            Avis � ajouter
	 */
	public void addReviewToCollection(Avis avis) {
		this.avis.addLast(avis);
	}

	/**
	 * Renvoi l'avis de l'item pass� en parametre
	 * 
	 * @param item
	 *            Item de r�ference
	 * @return Avis concern� par l'item ou null si l'item n'a pas �t� not� par
	 *         ce membre
	 */
	public Avis getReview(Item item) {
		int i = 0;

		while (i < avis.size()) {
			if (avis.get(i).itemEquals(item))
				return avis.get(i);

			i++;
		}

		return null;
	}

	/**
	 * Renvoi l'opinion de l'avis pass� en paramettre
	 * 
	 * @param avis
	 *            Avis de r�ference
	 * @return Opinion concern� par l'avis ou null si l'avis n'a pas �t� not�
	 *         par ce membre
	 */
	public Opinion getOpinion(Avis avis) {
		int i = 0;

		while (i < opinions.size()) {
			if (opinions.get(i).avisEquals(avis))
				return opinions.get(i);

			i++;
		}

		return null;
	}

	/**
	 * Ajoute une nouvelle Opinion dans la liste des Opinion laiss�es sur le
	 * Membre
	 * 
	 * @param opinion
	 *            Nouvelle Opinion � ajouter
	 */
	public void addOpinion(Opinion opinion) {
		this.opinions.addLast(opinion);
	}

	/**
	 * Met � jour le Karma d'un Membre � partir des Opinion laiss�es sur ses
	 * Avis
	 * 
	 * @return Nouveau karma
	 */
	public float updateKarma() {
		int i = 0;
		int somme = 0;

		/*
		 * R�cuperation de la somme des opinions
		 */
		System.out.println(opinions.size());
		while (i < opinions.size()) {
			somme += opinions.get(i).getOpinionMark();
			i++;
		}

		/*
		 * Calcul du nouveau karma
		 */
		this.karma =
				(float) ((2 / Math.PI) * (Math.atan(somme / 18.0) + Math.PI / 2));

		/*
		 * Mise a jour de la pond�ration dans les avis
		 */
		i = 0;
		while (i < avis.size()) {
			avis.get(i).updatePonderation();
			i++;
		}

		return karma;
	}

	/**
	 * @return Karma du Membre
	 */
	public float getKarma() {
		return karma;
	}

	public String toString() {
		String retour =
				"Pseudo : " + pseudo + "\n" + "Profil : " + profil + "\n";

		return retour;
	}

}
