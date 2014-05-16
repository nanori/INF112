package test;

import avis.SocialNetwork;
import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotMember;

public class TestsAddItemFilm {
	
	private static int cptErr;
	private static int cptOk;
	
	public TestsAddItemFilm(){
		cptErr=0;
		cptOk=0;
	}
	
//*****************************************************************
//*****************************************************************		
	public static void addItemFilmBadEntryTest (SocialNetwork sn, String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur){
		// v�rifie que l'ajout d'un film est refus�e (lev�e de l'exception BadEntry et pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur pass� en parametre
		int nbfilms = sn.nbFilms();
		try {
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			cptErr++;
		}
		catch (BadEntry e) {
			if (sn.nbFilms() != nbfilms){ 
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien �t� lev�e mais le nombre de films a �t� modifi�");	
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
	public static void addItemFilmOKTest (SocialNetwork sn, String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree, String idTest){
		
		int nbfilms = sn.nbFilms();
		try{
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			if (sn.nbFilms() != nbfilms+1){
				System.out.println("Test " + idTest + " :  le nombre de films n'a pas �t� correctement incr�ment�");
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
	public static void addItemFilmAlreadyExistsTest (SocialNetwork sn, String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur){
		int nbfilms = sn.nbFilms();
		try {
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			System.out.println("Test " + idTest + " : " + messErreur);
			cptErr++;
		}
		catch (ItemFilmAlreadyExists e) {
			if (sn.nbFilms() != nbfilms){
				System.out.println("Test " + idTest + " : l'exception ItemFilmAlreadyExists a bien �t� lev�e mais le nombre de membres a �t� modifi�");
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
	public static void addItemFilmNotMemberTest(SocialNetwork sn, String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur){

		int nbfilms = sn.nbFilms();
		try {
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			cptErr++;
		}
		catch (NotMember e) {
			if (sn.nbFilms() != nbfilms){
				System.out.println("Test " + idTest + " : l'exception ItemFilmAlreadyExists a bien �t� lev�e mais le nombre de films a �t� modifi�");
				cptErr++;
			}
			else{
				cptOk++;
			}
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); e.printStackTrace(); cptErr++;}
	}



	
	
	
	public static void main(String[] args) {

		int nbFilms = 0;


		System.out.println("Tests: ajouter des films au r�seau social");

		
		SocialNetwork sn = new SocialNetwork();

		// tests de addItemFilm
		nbFilms = sn.nbFilms();

		try {
			sn.addMember ("geubeutreu", "123456", "psychopathe");
			sn.addMember ("Jean", "123456", "schizophrene impulsif");
		} catch (BadEntry e) {
			e.printStackTrace();
		} catch (MemberAlreadyExists e) {
			e.printStackTrace();
		}
		
		
		
		// <=> fiche num�ro 1

		// tentative d'ajout de films avec entr�es "incorrectes"
		addItemFilmBadEntryTest ( sn, null, "123", "titre", "genre", "realisateur", "scenariste", 42, "1.1",  "L'ajout d'un film avec un pseudo non instanci� est accept�");
		addItemFilmBadEntryTest ( sn, " ", "yuio", "titre", "genre", "realisateur", "scenariste", 42, "1.2", "L'ajout d'un film avec un pseudo ne contient pas un caracteres, autre que des espaces, est accept�");
		addItemFilmBadEntryTest ( sn, "Jean", null, "titre", "genre", "realisateur", "scenariste", 42, "1.3", "L'ajout d'un film avec un password n'est pas instanci� est accept�");
		addItemFilmBadEntryTest ( sn, "Jean", "123", "titre", "genre", "realisateur", "scenariste", 42, "1.4", "L'ajout d'un film avec un password ne contient pas au moins 4 caracteres, autre que des espaces de d�but ou de fin, est accept�");
		addItemFilmBadEntryTest ( sn, "Jean", "123456", null, "genre", "realisateur", "scenariste", 42, "1.5", "L'ajout d'un film dont le titre n'est pas instanci� est accept�");
		addItemFilmBadEntryTest ( sn, "Jean", "123456", "", "genre", "realisateur", "scenariste", 42, "1.6", "L'ajout d'un film dont le titre contient moins de 1 caractère est accept�");
		addItemFilmBadEntryTest ( sn, "Jean", "123456", "titre", null, "realisateur", "scenariste", 42, "1.7", "L'ajout d'un film dont le genre n'est pas instanci� est accept�");
		addItemFilmBadEntryTest ( sn, "Jean", "123456", "titre", "genre", null, "scenariste", 42, "1.8", "L'ajout d'un film dont le r�alisateur n'est pas instanci� est accept�");
		addItemFilmBadEntryTest ( sn, "Jean", "123456", "titre", "genre", "realisateur", null, 42, "1.8", "L'ajout d'un film dont le scenariste n'est pas instanci� est accept�");		
		addItemFilmBadEntryTest ( sn, "Jean", "123456", "titre", "genre", "realisateur", "scenariste",-42, "1.9", "L'ajout d'un film dont le nombre de page est n�gatif ou nul est accept�");

		
		

		// <=> fiche num�ro 2

		// ajout de 2 films avec entr�es "correctes"	
		addItemFilmOKTest (sn, "Jean", "123456", "BoomBoom", "romance", "Michel BAY", "Michel BAY", 100, "2.1");
		addItemFilmOKTest (sn, "Jean", "123456", "Troll 2", "art et essais", "�douard Khil", "�douard Khil", 100, "2.1");

		// tentative d'ajout de film "existant"		
		addItemFilmAlreadyExistsTest(sn, "Jean", "123456", "BoomBoom", "romance", "Michel BAY", "Michel BAY", 100, "3.1", "L'ajout d'un film avec le titre du premier film ajout� est accept�");
		addItemFilmAlreadyExistsTest(sn, "Jean", "123456", "BoOmbooM", "romance", "Michel BAY", "Michel BAY", 100, "3.2", "L'ajout d'un film avec un titre existant (avec casse diff�rente) est accept�");
		addItemFilmAlreadyExistsTest(sn, "Jean", "123456", "    BoomBoom    ", "romance", "Michel BAY", "Michel BAY", 100, "3.3", "L'ajout d'un film avec un titre existant (avec leading et trailing blanks) est accept�");		

		
		
		// tentative d'ajout de film avec un membre invalide
		addItemFilmNotMemberTest(sn, "Jeannot", "123456", "titre", "genre", "realisateur", "scenariste", 42, "4.1", "L'ajout d'un film avec un pseudo invalide est accept�");
		addItemFilmNotMemberTest(sn, "Jean", "j'exilerai ma peur", "titre", "genre", "realisateur", "scenariste",42, "4.2", "L'ajout d'un film avec un password ne corrspondant pas au pseudo est accept�");
		
		
		
		//Verification finale
		if (nbFilms +2 != sn.nbFilms()) {
			System.out.println("Erreur 5.1 :  le nombre de films apr�s utilisation de addMember a �t� modifi�");				
		}

		
		System.out.println("\n***************************\n***************************\nTests d'ajout de films:\n\tTests OK: " + cptOk + "\n\tTests NOK: " + cptErr + "\n***************************\n***************************");
		
	}

}
