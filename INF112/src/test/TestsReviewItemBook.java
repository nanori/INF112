package test;

import exception.BadEntry;
import exception.NotItem;
import exception.NotMember;
import avis.SocialNetwork;

public class TestsReviewItemBook {
	public static int cptErr;
	public static int cptOk;

	public static void reviewItemBookNotItem(SocialNetwork sn, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur) {

		try {
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			System.out.println("Test " + idTest + " : " + messErreur);
			// Exception non levée
			cptErr++;
		} catch (NotItem e) {
			// Exception NotItem levée
			cptOk++;
		} catch (Exception e) {
			// Mauvaise exception levée
			System.out.println("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			cptErr++;
		}

	}

	public static void reviewItemBookNotMember(SocialNetwork sn, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur) {

		try {
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			System.out.println("Test " + idTest + " : " + messErreur);
			// Exception non levée
			cptErr++;
		} catch (NotMember e) {
			// Exception NotMember levée
			
			cptOk++;
		} catch (Exception e) {
			// Mauvaise exception levée
			System.out.println("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			cptErr++;
		}

	}

	public static void reviewItemBookBadEntry(SocialNetwork sn, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur) {

		try {
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			System.out.println("Test " + idTest + " : " + messErreur);
			// Exception non levée
			cptErr++;
		} catch (BadEntry e) {
			// Exception BadEntry levée
			
			cptOk++;
		} catch (Exception e) {
			// Mauvaise exception levée
			System.out.println("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			cptErr++;
		}

	}

	public static float reviewItemBookOK(SocialNetwork sn, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur) {
		float r = 0;
		try {
			r = sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			cptOk++;
			return r;
		} catch (Exception e) {
			System.out.println("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			cptErr++;
		}
		return r;
	}

	public static void main(String[] args) {
		// Mise en place de l'environement de test
		SocialNetwork sn = new SocialNetwork();
		System.out.println("Tests : noter des livres");

		try {
			// Ajout de membres
			sn.addMember("geubeutreu", "123456", "psychopathe");
			sn.addMember("Jean", "123456", "schizophrene impulsif");

			// Ajout de livre
			sn.addItemBook("Jean", "123456", "Le Cidre", "Comique", "Corbeille", 42);
			sn.addItemBook("geubeutreu", "123456", "En rouge et noir", "Dramatique", "Stendal Mas", 24);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Tests avec levée de BadEntry attendue
		reviewItemBookBadEntry(sn, null, "123456", "Le Cidre", 4.5f, "Hic!", "1.1", "L'ajout d'un avis avec un pseudo non instancie est accepte");
		reviewItemBookBadEntry(sn, "", "123456", "Le Cidre", 4.5f, "Hic!", "1.2", "L'ajout d'un avis avec un pseudo contenant moins d'un caracteres, autre que des espaces, est accepte");

		reviewItemBookBadEntry(sn, "Jean", null, "Le Cidre", 4.5f, "Hic!", "1.3", "L'ajout d'un avis avec un password non instancie est accepte");
		reviewItemBookBadEntry(sn, "Jean", " ", "Le Cidre", 4.5f, "Hic!", "1.4", "L'ajout d'un avis avec un password contenant moins d'un caracteres, autre que des espaces, est accepte");

		reviewItemBookBadEntry(sn, "Jean", "123456", null, 4.5f, "Hic!", "1.5", "L'ajout d'un avis avec un titre non instancie est accepte");
		reviewItemBookBadEntry(sn, "Jean", "123456", " ", 4.5f, "Hic!", "1.6", "L'ajout d'un avis avec un titre contenant moins d'un caracteres, autre que des espaces, est accepte");

		reviewItemBookBadEntry(sn, "Jean", "123456", "Le Cidre", -0.3f, "Hic!", "1.7", "L'ajout d'un avis avec une note < 0.0 est accepte");
		reviewItemBookBadEntry(sn, "Jean", "123456", "Le Cidre", 10.0f, "Hic!", "1.8", "L'ajout d'un avis avec une note > 5.0 est accepte");

		reviewItemBookBadEntry(sn, "Jean", "123456", "Le Cidre", 4.5f, null, "1.9", "L'ajout d'un avis avec un commentaire non instancie est accepte");

		// Tests avec levée de NotMember attendue
		reviewItemBookNotMember(sn, "JeanBon", "123456", "Le Cidre", 4.5f, "Hic!", "2.1", "L'ajout d'un avis avec un membre n'existant pas est accepte");
		reviewItemBookNotMember(sn, "Jean", "brabra", "Le Cidre", 4.5f, "Hic!", "2.1", "L'ajout d'un avis avec un password ne correspondant pas au membre associe est accepte");

		// Tests avec levée de NotItem attendue
		reviewItemBookNotItem(sn, "Jean", "123456", "Le Cid", 4.5f, "Hic!", "3.1", "L'ajout d'un avis avec un titre n'existant pas est accepte");

		// Tests sans levée d'exception attendue
		reviewItemBookOK(sn, "Jean", "123456", "Le Cidre", 4.5f, "Hic!", "4.1", "");
		float moy = reviewItemBookOK(sn, "Jean", "123456", "Le Cidre", 2.0f, "Hic!", "4.3", "");
		if (moy != 2.0)
			System.out.println("4.3 : la moyenne n'es pas corecte apres deux notations du même membre sur un item ( moy = " + moy + ")");
		
		moy = reviewItemBookOK(sn, "geubeutreu", "123456", "Le Cidre", 5f, "Good ;)", "4.1", "");
		if (moy != 3.5)
			System.out.println("4.4 : la moyenne n'es pas corecte apres deux notations de deux membres sur un item ( moy = " + moy + ")");
		
		
		
		System.out.println("***************************");
		System.out.println("***************************");
		System.out.println("Tests de notation de livres:");
		System.out.println("\tTests OK: " + cptOk);
		System.out.println("\tTests NOK: " + cptErr);
		System.out.println("***************************");
		System.out.println("***************************");

	}

}
