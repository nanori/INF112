package test;

import exception.BadEntry;
import exception.NotItem;
import exception.NotMember;
import avis.SocialNetwork;

public class TestsReviewItemFilm {
	public static int cptErr;
	public static int cptOk;

	public static void reviewItemFilmNotItem(SocialNetwork sn, String pseudo,
												String password, String titre,
												float note, String commentaire,
												String idTest, String messErreur) {

		try {
			sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
			System.out.println("Test " + idTest + " : " + messErreur);
			// Exception non levée
			cptErr++;
		} catch (NotItem e) {
			// Exception NotItem levée
			cptOk++;
		} catch (Exception e) {
			// Mauvaise exception levée
			System.out.println("Test " + idTest + " : exception non prévue. "
					+ e);
			e.printStackTrace();
			cptErr++;
		}

	}

	public static void reviewItemFilmNotMember(SocialNetwork sn, String pseudo,
												String password, String titre,
												float note, String commentaire,
												String idTest, String messErreur) {

		try {
			sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
			System.out.println("Test " + idTest + " : " + messErreur);
			// Exception non levée
			cptErr++;
		} catch (NotMember e) {
			// Exception NotMember levée

			cptOk++;
		} catch (Exception e) {
			// Mauvaise exception levée
			System.out.println("Test " + idTest + " : exception non prévue. "
					+ e);
			e.printStackTrace();
			cptErr++;
		}

	}

	public static void reviewItemFilmBadEntry(SocialNetwork sn, String pseudo,
												String password, String titre,
												float note, String commentaire,
												String idTest, String messErreur) {

		try {
			sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
			System.out.println("Test " + idTest + " : " + messErreur);
			// Exception non levée
			cptErr++;
		} catch (BadEntry e) {
			// Exception BadEntry levée

			cptOk++;
		} catch (Exception e) {
			// Mauvaise exception levée
			System.out.println("Test " + idTest + " : exception non prévue. "
					+ e);
			e.printStackTrace();
			cptErr++;
		}

	}

	public static float reviewItemFilmOK(SocialNetwork sn, String pseudo,
											String password, String titre,
											float note, String commentaire,
											String idTest, String messErreur) {
		float r = 0;
		try {
			r = sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
			cptOk++;
			return r;
		} catch (Exception e) {
			System.out.println("Test " + idTest + " : exception non prévue. "
					+ e);
			e.printStackTrace();
			cptErr++;
		}
		return r;
	}

	public static void main(String[] args) {
		// Mise en place de l'environement de test
		SocialNetwork sn = new SocialNetwork();

		try {
			// Ajout de membres
			sn.addMember("geubeutreu", "123456", "psychopathe");
			sn.addMember("Jean", "123456", "schizophrene impulsif");

			// Ajout de film
			sn.addItemFilm(	"Jean", "123456", "BoomBoom", "romance",
							"Michel BAY", "Michel BAY", 100);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Tests avec levée de BadEntry attendue
		reviewItemFilmBadEntry(	sn, null, "123456", "Le Cidre", 4.5f, "Hic!",
								"1.1",
								"L'ajout d'un avis avec un pseudo non instancie est accepte");
		reviewItemFilmBadEntry(	sn,
								"",
								"123456",
								"Le Cidre",
								4.5f,
								"Hic!",
								"1.2",
								"L'ajout d'un avis avec un pseudo contenant moins d'un caracteres, autre que des espaces, est accepte");

		reviewItemFilmBadEntry(	sn, "Jean", null, "Le Cidre", 4.5f, "Hic!",
								"1.3",
								"L'ajout d'un avis avec un password non instancie est accepte");
		reviewItemFilmBadEntry(	sn,
								"Jean",
								" ",
								"Le Cidre",
								4.5f,
								"Hic!",
								"1.4",
								"L'ajout d'un avis avec un password contenant moins d'un caracteres, autre que des espaces, est accepte");

		reviewItemFilmBadEntry(	sn, "Jean", "123456", null, 4.5f, "Hic!",
								"1.5",
								"L'ajout d'un avis avec un titre non instancie est accepte");
		reviewItemFilmBadEntry(	sn,
								"Jean",
								"123456",
								" ",
								4.5f,
								"Hic!",
								"1.6",
								"L'ajout d'un avis avec un titre contenant moins d'un caracteres, autre que des espaces, est accepte");

		reviewItemFilmBadEntry(	sn, "Jean", "123456", "Le Cidre", -0.3f,
								"Hic!",
								"1.7",
								"L'ajout d'un avis avec une note < 0.0 est accepte");
		reviewItemFilmBadEntry(	sn, "Jean", "123456", "Le Cidre", 10.0f,
								"Hic!",
								"1.8",
								"L'ajout d'un avis avec une note > 5.0 est accepte");

		reviewItemFilmBadEntry(	sn, "Jean", "123456", "Le Cidre", 4.5f, null,
								"1.9",
								"L'ajout d'un avis avec un commentaire non instancie est accepte");

		// Tests avec levée de NotMember attendue
		reviewItemFilmNotMember(sn, "JeanBon", "123456", "Le Cidre", 4.5f,
								"Hic!", "2.1",
								"L'ajout d'un avis avec un membre n'existant pas est accepte");
		reviewItemFilmNotMember(sn,
								"Jean",
								"brabra",
								"Le Cidre",
								4.5f,
								"Hic!",
								"2.1",
								"L'ajout d'un avis avec un password ne correspondant pas au membre associe est accepte");

		// Tests avec levée de NotItem attendue
		reviewItemFilmNotItem(	sn, "Jean", "123456", "Le Cid", 4.5f, "Hic!",
								"3.1",
								"L'ajout d'un avis avec un titre n'existant pas est accepte");

		// Tests sans levée d'exception attendue
		reviewItemFilmOK(	sn, "Jean", "123456", "BoomBoom", 4.5f, "Hic!",
							"4.1",
							"");
		float moy =
				reviewItemFilmOK(	sn, "Jean", "123456", "BoomBoom", 2.0f,
									"Hic!", "4.2", "");
		if (moy != 2.0)
			System.out
				.println("4.2 : la moyenne n'es pas corecte apres deux notations du même membre sur un item ( moy = "
						+ moy + ")");

		moy =
				reviewItemFilmOK(	sn, "geubeutreu", "123456", "BoomBoom",
									1.0f,
									"Ahou!", "4.3", "");
		if (moy != 1.5)
			System.out
				.println("4.3 : la moyenne n'es pas corecte apres deux notations du même membre sur un item ( moy = "
						+ moy + ")");

		System.out.println("***************************");
		System.out.println("Tests de notation de films:");
		System.out.println("\tTests OK: " + cptOk);
		System.out.println("\tTests NOK: " + cptErr);
		System.out.println("***************************");

	}

}
