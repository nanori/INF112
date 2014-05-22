package test;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;
import exception.NotReview;
import avis.SocialNetwork;
import avis.SocialNetwork.itemsTypes;

public class TestsReviewOpinion {
	public static int cptErr;
	public static int cptOk;

	public static float reviewOpinionNotReview(SocialNetwork sn, String pseudo, String password, String memberToReview, String titre, itemsTypes itemType, boolean opinion, String idTest, String messErreur) {
		float r = 0;
		try {
			r = sn.reviewOpinion(pseudo, password, memberToReview, titre, itemType, opinion);
			System.err.println("Test " + idTest + " : " + messErreur);
			cptOk++;
		} catch (NotReview e){
			
		} catch (Exception e) {
			System.err.println("Test " + idTest + " : exception non pr�vue. " + e);
			e.printStackTrace();
			cptErr++;
		}
		return r;
	}
	
	public static float reviewOpinionNotItem(SocialNetwork sn, String pseudo, String password, String memberToReview, String titre, itemsTypes itemType, boolean opinion, String idTest, String messErreur) {
		float r = 0;
		try {
			r = sn.reviewOpinion(pseudo, password, memberToReview, titre, itemType, opinion);
			System.err.println("Test " + idTest + " : " + messErreur);
			cptOk++;
		} catch (NotItem e){
			
		} catch (Exception e) {
			System.err.println("Test " + idTest + " : exception non pr�vue. " + e);
			e.printStackTrace();
			cptErr++;
		}
		return r;
	}
	
	public static float reviewOpinionNotMember(SocialNetwork sn, String pseudo, String password, String memberToReview, String titre, itemsTypes itemType, boolean opinion, String idTest, String messErreur) {
		float r = 0;
		try {
			r = sn.reviewOpinion(pseudo, password, memberToReview, titre, itemType, opinion);
			System.err.println("Test " + idTest + " : " + messErreur);
			cptOk++;
		} catch (NotMember e){
			
		} catch (Exception e) {
			System.err.println("Test " + idTest + " : exception non pr�vue. " + e);
			e.printStackTrace();
			cptErr++;
		}
		return r;
	}
	
	public static float reviewOpinionBadEntry(SocialNetwork sn, String pseudo, String password, String memberToReview, String titre, itemsTypes itemType, boolean opinion, String idTest, String messErreur) {
		float r = 0;
		try {
			r = sn.reviewOpinion(pseudo, password, memberToReview, titre, itemType, opinion);
			System.err.println("Test " + idTest + " : " + messErreur);
			cptOk++;
		} catch (BadEntry be){
			
		} catch (Exception e) {
			System.err.println("Test " + idTest + " : exception non pr�vue. " + e);
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
			System.out.println("Test " + idTest + " : exception non pr�vue. " + e);
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
			reviewBook(sn, 5, "Le Cidre");
			sn.reviewItemBook("Jean", "123456", "Le Cidre", 0, "Vive le jus de raisin");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * Test 1 : Lev�e d'exeption BadEntry
		 */
		reviewOpinionBadEntry(sn, null, "123456", "pseudo5", "Le Cidre", itemsTypes.BOOK, true, "1.1", "L'ajout d'une opinion avec un pseudo non instanci� est accept�e");
		reviewOpinionBadEntry(sn, " ", "123456", "pseudo5", "Le Cidre", itemsTypes.BOOK, true, "1.2", "L'ajout d'une opinion avec un pseudo de moins d'un caract�re est accept�e");
		reviewOpinionBadEntry(sn, "geubeutreu", null, "pseudo5", "Le Cidre", itemsTypes.BOOK, true, "1.3", "L'ajout d'une opinion avec un password non instanci� est accept�e");
		reviewOpinionBadEntry(sn, "geubeutreu", " y ", "pseudo5", "Le Cidre", itemsTypes.BOOK, true, "1.4", "L'ajout d'une opinion avec un password de moins de 4 caract�re est accept�e");
		reviewOpinionBadEntry(sn, "geubeutreu", "123456", null, "Le Cidre", itemsTypes.BOOK, true, "1.5", "L'ajout d'une opinion sur un pseudo non instanci� est accept�e");
		reviewOpinionBadEntry(sn, "geubeutreu", "123456", " ", "Le Cidre", itemsTypes.BOOK, true, "1.6", "L'ajout d'une opinion sur un pseudo de moins d'un caract�re est accept�e");
		
		/*
		 * Test 2 : Lev�es d'exeptions NotItem
		 */
		reviewOpinionNotItem(sn, "geubeutreu", "123456", "Jean", "Le Vin", itemsTypes.BOOK, true, "2.1", "L'ajout d'une opinion sur un item n'existant pas est accept�e");

		/*
		 * Test 3 : Lev�es d'exeptions NotReview
		 */
		reviewOpinionNotReview(sn, "geubeutreu", "123456", "Jean", "En rouge et noir", itemsTypes.BOOK, true, "3.1", "L'ajout d'une opinion sur un avis n'existant pas est accept�e (Mauvais livres)");
		reviewOpinionNotReview(sn, "geubeutreu", "123456", "geubeutreu", "Le Cidre", itemsTypes.BOOK, true, "3.2", "L'ajout d'une opinion sur un avis n'existant pas est accept�e (Mauvais membre)");

		/*
		 * Test 4 : Lev�es d'exeptions NotMember
		 */
		reviewOpinionNotMember(sn, "gubutru", "123456", "Jean", "Le Cidre", itemsTypes.BOOK, true, "4.1", "L'ajout d'une opinion avec un membre n'existant pas est accept�e");
		reviewOpinionNotMember(sn, "geubeutreu", "1234", "Jean", "Le Cidre", itemsTypes.BOOK, true, "4.2", "L'ajout d'une opinion avec un password ne correspondant pas au membre est accept�e");
		reviewOpinionNotMember(sn, "geubeutreu", "123456", "Bon", "Le Cidre", itemsTypes.BOOK, true, "4.3", "L'ajout d'une opinion sur un membre n'existant pas est accept�e");
		
		/*
		 * Test 5 : D�roulement normal
		 */
		try {
			System.out.println(sn.consultItems("Le Cidre"));
			karma = reviewOpinionOK(sn, "geubeutreu", "123456", "Jean", "Le Cidre", itemsTypes.BOOK, false, "5.1", "Not good");
			System.out.println("Karma de Jean : " + karma);
			
			System.out.println(sn.consultItems("Le Cidre"));
			
			karma = reviewOpinionOK(sn, "geubeutreu", "123456", "Jean", "Le Cidre", itemsTypes.BOOK, true, "5.2", "Not good");
			System.out.println("Karma de Jean : " + karma);
			System.out.println(sn.consultItems("Le Cidre"));
		} catch (BadEntry e1) {
			e1.printStackTrace();
		}
	}

	private static void generateMembers(SocialNetwork sn, int nbMembres) throws BadEntry, MemberAlreadyExists {
		for(int i= 0; i<nbMembres; i++){
			sn.addMember("pseudo" + i, "passwd", "N� membre : " + i);
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
			moy = sn.reviewItemBook("pseudo" + i, "passwd", bookTitle, 5*r.nextFloat(), "Note de pseudo" + i);
			i++;
		}
		return moy;
	}
	

}
