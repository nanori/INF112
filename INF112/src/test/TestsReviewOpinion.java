package test;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;
import avis.SocialNetwork;
import avis.SocialNetwork.itemsTypes;

public class TestsReviewOpinion {
	public static int cptErr;
	public static int cptOk;

	public static float reviewOpinionBadEntry(SocialNetwork sn, String pseudo, String password, String memberToReview, String titre, itemsTypes itemType, boolean opinion, String idTest, String messErreur) {
		float r = 0;
		try {
			r = sn.reviewOpinion(pseudo, password, memberToReview, titre, itemType, opinion);
			System.err.println("Test " + idTest + " : " + messErreur);
			cptOk++;
		} catch (BadEntry be){
			
		} catch (Exception e) {
			System.err.println("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			cptErr++;
		}
		return r;
	}
	
	public static float reviewOpinionOK(SocialNetwork sn, String pseudo, String password, String memberToReview, String titre, itemsTypes itemType, boolean opinion, String idTest, String messErreur) {
		float r = 0;
		try {
			r = sn.reviewOpinion(pseudo, password, memberToReview, titre, itemType, opinion);
			cptOk++;
		} catch (Exception e) {
			System.out.println("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			cptErr++;
		}
		return r;
	}

	public static void main(String[] args) {
		/*
		 * Mise en place de l'environnement de test
		 */
		SocialNetwork sn = new SocialNetwork();
		System.out.println("Tests : noter des livres");
		float karma;

		try {
			// Ajout de membres
			generateMembers(sn, 100);
			sn.addMember("geubeutreu", "123456", "psychopathe");
			sn.addMember("Jean", "123456", "schizophrene impulsif");

			// Ajout d'Items
			generateBooks(sn, 100, 2);
			sn.addItemBook("Jean", "123456", "Le Cidre", "Comique", "Corbeille", 42);
			sn.addItemFilm("Jean", "123456", "BoomBoom", "romance", "Michel BAY", "Michel BAY", 100);
			sn.addItemBook("geubeutreu", "123456", "En rouge et noir", "Dramatique", "Stendal Mas", 24);
			sn.addItemFilm("geubeutreu", "123456", "X-Men", "biopic", "Charle X", "Charle X", 200);
			
			// Review des Items
			reviewBook(sn, 30, "Le Cidre");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * Test 1 : Levée d'exeption BadEntry
		 */
		reviewOpinionBadEntry(sn, null, "123456", "pseudo5", "Le Cidre", itemsTypes.BOOK, true, "1.1", "L'ajout d'une opinion avec un pseudo non instancié est acceptée");
		reviewOpinionBadEntry(sn, " ", "123456", "pseudo5", "Le Cidre", itemsTypes.BOOK, true, "1.1", "L'ajout d'une opinion avec un pseudo de moins d'un caractère est acceptée");
		reviewOpinionBadEntry(sn, "geubeutreu", null, "pseudo5", "Le Cidre", itemsTypes.BOOK, true, "1.1", "L'ajout d'une opinion avec un password non instancié est acceptée");
		reviewOpinionBadEntry(sn, "geubeutreu", " y ", "pseudo5", "Le Cidre", itemsTypes.BOOK, true, "1.1", "L'ajout d'une opinion avec un password de moins de 4 caractère est acceptée");
		reviewOpinionBadEntry(sn, "geubeutreu", "123456", null, "Le Cidre", itemsTypes.BOOK, true, "1.1", "L'ajout d'une opinion sur un pseudo non instancié est acceptée");
		reviewOpinionBadEntry(sn, "geubeutreu", "123456", " ", "Le Cidre", itemsTypes.BOOK, true, "1.1", "L'ajout d'une opinion sur un pseudo de moins d'un caractère est acceptée");
		
		// reviewOpinionOK(sn, pseudo, password, memberToReview, titre,
		// itemType, opinion, idTest, messErreur)
		karma = reviewOpinionOK(sn, "geubeutreu", "123456", "Jean", "Le Cidre", itemsTypes.BOOK, true, "1.1", "Not good");
		System.out.println("Karma de Jean : " + karma);
		try {
			System.err.println(sn.consultItems("Le Cidre"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void generateMembers(SocialNetwork sn, int nbMembres) throws BadEntry, MemberAlreadyExists {
		for(int i= 0; i<nbMembres; i++){
			sn.addMember("pseudo" + i, "passwd", "N° membre : " + i);
		}
	}

	private static void generateBooks(SocialNetwork sn, int nbBooks, int booksByMember) throws BadEntry, NotMember, ItemBookAlreadyExists {
		int i = 0;
		int j = 0;
		int lastBook = 0;
		while(i<nbBooks/booksByMember && i <sn.nbMembers()){
			lastBook = j;
			while(j<lastBook+booksByMember){
				sn.addItemBook("pseudo" + i, "passwd", "Titre"+j, "genre", "auteur", 5);
				//System.err.println("Titre"+j);
				j++;
			}
			i++;
		}
	}
	private static float reviewBook (SocialNetwork sn, int nbReview, String bookTitle) throws BadEntry, NotMember, NotItem{
		int i = 0;
		float moy = 0;
		Random r = new Random();
		while(i <nbReview){
			moy = sn.reviewItemBook("pseudo" + i, "passwd", bookTitle, r.nextFloat(), "Note de pseudo" + i);
			i++;
		}
		return moy;
	}
	

}
