package test;

import avis.SocialNetwork;
import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotMember;

public class TestsAddItemBook {
	
	private static int cptErr;
	private static int cptOk;
	
	public TestsAddItemBook(){
		cptErr=0;
		cptOk=0;
	}
	
//*****************************************************************
//*****************************************************************		
	public static void addItemBookBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, 
												int nbPage, String idTest, String messErreur){
		// v�rifie que l'ajout d'un livre est refus�e (lev�e de l'exception BadEntry et pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur pass� en param�tre
		int nbLivres = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPage);
			System.out.println ("Test " + idTest + " : " + messErreur);
			cptErr++;
		}
		catch (BadEntry e) {
			if (sn.nbBooks() != nbLivres){ 
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien �t� lev�e mais le nombre de livres a �t� modifi�");	
				cptErr++;
			}
			else{
				cptOk++;
			}
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); e.printStackTrace(); cptErr++;}
	}
	
	
	
//*****************************************************************
//*****************************************************************		
	public static void addItemBookOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, 
										  int nbPage, String idTest){
		
		int nbLivres = sn.nbBooks();
		try{
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPage);
			if (sn.nbBooks() != nbLivres+1){
				System.out.println("Test " + idTest + " :  le nombre de livres n'a pas �t� correctement incr�ment�");
				cptErr++;
			}
			else{
				cptOk++;
			}
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); e.printStackTrace(); cptErr++;}
	}
	
	
	
//*****************************************************************
//*****************************************************************		
	public static void addItemBookAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, 
													 int nbPage, String idTest, String messErreur){
		int nbLivres = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPage);
			System.out.println("Test " + idTest + " : " + messErreur);
			cptErr++;
		}
		catch (ItemBookAlreadyExists e) {
			if (sn.nbBooks() != nbLivres){
				System.out.println("Test " + idTest + " : l'exception ItemBookAlreadyExists a bien �t� lev�e mais le nombre de membres a �t� modifi�");
				cptErr++;
			}
			else{
				cptOk++;
			}
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); e.printStackTrace(); cptErr++;}
	}

	
	
//*****************************************************************
//*****************************************************************	
	public static void addItemBookNotMemberTest(SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, 
												int nbPage, String idTest, String messErreur){

		int nbLivres = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPage);
			System.out.println ("Test " + idTest + " : " + messErreur);
			cptErr++;
		}
		catch (NotMember e) {
			if (sn.nbBooks() != nbLivres){
				System.out.println("Test " + idTest + " : l'exception ItemBookAlreadyExists a bien �t� lev�e mais le nombre de livres a �t� modifi�");
				cptErr++;
			}
			else{
				cptOk++;
			}
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); e.printStackTrace(); cptErr++;}
	}



	
	
	
	public static void main(String[] args) {

		int nbLivres = 0;
		int nbFilms = 0;
		
		SocialNetwork sn = new SocialNetwork();

		// tests de addItemBook
		nbFilms = sn.nbFilms();
		nbLivres = sn.nbBooks();

		try {
			sn.addMember ("geubeutreu", "123456", "psychopathe");
			sn.addMember ("Jean", "123456", "schizophr�ne impulsif");
		} catch (BadEntry e) {
			e.printStackTrace();
		} catch (MemberAlreadyExists e) {
			e.printStackTrace();
		}
		
		
		
		// <=> fiche num�ro 1

		// tentative d'ajout de livres avec entr�es "incorrectes"
		addItemBookBadEntryTest ( sn, null, "123", "titre", "genre", "auteur", 42, "1.1",  "L'ajout d'un livre avec un pseudo non instanci� est accept�");
		addItemBookBadEntryTest ( sn, " ", "yuio", "titre", "genre", "auteur", 42, "1.2", "L'ajout d'un livre avec un pseudo ne contient pas un caracteres, autre que des espaces, est accept�");
		addItemBookBadEntryTest ( sn, "Jean", null, "titre", "genre", "auteur", 42, "1.3", "L'ajout d'un livre avec un password n'est pas instanci� est accept�");
		addItemBookBadEntryTest ( sn, "Jean", "123", "titre", "genre", "auteur", 42, "1.4", "L'ajout d'un livre avec un password ne contient pas au moins 4 caracteres, autre que des espaces de d�but ou de fin, est accept�");
		addItemBookBadEntryTest ( sn, "Jean", "123456", null, "genre", "auteur", 42, "1.5", "L'ajout d'un livre dont le titre n'est pas instanci� est accept�");
		addItemBookBadEntryTest ( sn, "Jean", "123456", "", "genre", "auteur", 42, "1.6", "L'ajout d'un livre dont le titre contient moins de 1 caract�re est accept�");
		addItemBookBadEntryTest ( sn, "Jean", "123456", "titre", null, "auteur", 42, "1.7", "L'ajout d'un livre dont le genre n'est pas instanci� est accept�");
		addItemBookBadEntryTest ( sn, "Jean", "123456", "titre", "genre", null, 42, "1.8", "L'ajout d'un livre dont le auteur n'est pas instanci� est accept�");
		addItemBookBadEntryTest ( sn, "Jean", "123456", "titre", "genre", "auteur", -42, "1.9", "L'ajout d'un livre dont le nombre de page est n�gatif ou nul est accept�");

		
		

		// <=> fiche num�ro 2

		// ajout de 3 livres avec entr�es "correctes"	
		addItemBookOKTest (sn, "Jean", "123456", "Le Cidre", "Comique", "Corbeille", 1, "2.1");
		addItemBookOKTest (sn, "Jean", "123456", "En rouge et noir", "Dramatique", "Stendal Mas", 1, "2.2");
		addItemBookOKTest (sn, "Jean", "123456", "L'assomoir", "Long", "Zola et Mile", 1, "2.3");

		
		
		// tentative d'ajout de livre "existant"		
		addItemBookAlreadyExistsTest(sn, "Jean", "123456", "Le Cidre", "Comique", "Corbeille", 1, "3.1", "L'ajout d'un livre avec le titre du premier livre ajout� est accept�");
		addItemBookAlreadyExistsTest(sn, "Jean", "123456", "Le CiDrE", "Comique", "Corbeille", 1, "3.2", "L'ajout d'un livre avec un titre existant (avec casse diff�rente) est accept�");
		addItemBookAlreadyExistsTest(sn, "Jean", "123456", "      Le Cidre     ", "Comique", "Corbeille", 1, "3.3", "L'ajout d'un livre avec un titre existant (avec leading et trailing blanks) est accept�");		

		
		
		// tentative d'ajout de livre avec un membre invalide
		addItemBookNotMemberTest(sn, "Jeannot", "123456", "En rouge et noir", "Dramatique", "Stendal Mas", 1, "4.1", "L'ajout d'un livre avec un pseudo invalide est accept�");
		addItemBookNotMemberTest(sn, "Jean", "j'exilerai ma peur", "En rouge et noir", "Dramatique", "Stendal Mas", 1, "4.2", "L'ajout d'un livre avec un password ne corrspondant pas au pseudo est accept�");
		
					
		
		//Verification finale
		
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Erreur 5.1 :  le nombre de films apr�s utilisation de addMember a �t� modifi�");
			cptErr++;
		}
		else{cptOk++;}
		if (nbLivres +3 != sn.nbBooks()) {
			System.out.println("Erreur 5.2 :  le nombre de livres apr�s utilisation de addMember a �t� modifi�");
			cptErr++;
		}
		else{cptOk++;}

		System.out.println("***************************");
		System.out.println("Tests d'ajout de livres:");
		System.out.println("\tTests OK: " + cptOk);
		System.out.println("\tTests NOK: " + cptErr);
		System.out.println("***************************");
	}

}
