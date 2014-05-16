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
		// vérifie que l'ajout d'un film est refusée (levée de l'exception BadEntry et pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passé en parametre
		int nbfilms = sn.nbFilms();
		try {
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			cptErr++;
		}
		catch (BadEntry e) {
			if (sn.nbFilms() != nbfilms){ 
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de films a été modifié");	
				cptErr++;
			}
			else{
				cptOk++;
			}
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non prévue. " + e); e.printStackTrace(); cptErr++;}
	}
	
	
	
//*****************************************************************
//*****************************************************************		
	public static void addItemFilmOKTest (SocialNetwork sn, String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree, String idTest){
		
		int nbfilms = sn.nbFilms();
		try{
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			if (sn.nbFilms() != nbfilms+1){
				System.out.println("Test " + idTest + " :  le nombre de films n'a pas été correctement incrémenté");
				cptErr++;
			}
			else{
				cptOk++;
			}
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non prévue. " + e); e.printStackTrace(); cptErr++;}
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
				System.out.println("Test " + idTest + " : l'exception ItemFilmAlreadyExists a bien été levée mais le nombre de membres a été modifié");
				cptErr++;
			}
			else{
				cptOk++;
			}
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non prévue. " + e); e.printStackTrace(); cptErr++;}
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
				System.out.println("Test " + idTest + " : l'exception ItemFilmAlreadyExists a bien été levée mais le nombre de films a été modifié");
				cptErr++;
			}
			else{
				cptOk++;
			}
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non prévue. " + e); e.printStackTrace(); cptErr++;}
	}



	
	
	
	public static void main(String[] args) {

		int nbFilms = 0;


		System.out.println("Tests: ajouter des films au réseau social");

		
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
		
		
		
		// <=> fiche numéro 1

		// tentative d'ajout de films avec entrées "incorrectes"
		addItemFilmBadEntryTest ( sn, null, "123", "titre", "genre", "realisateur", "scenariste", 42, "1.1",  "L'ajout d'un film avec un pseudo non instancié est accepté");
		addItemFilmBadEntryTest ( sn, " ", "yuio", "titre", "genre", "realisateur", "scenariste", 42, "1.2", "L'ajout d'un film avec un pseudo ne contient pas un caracteres, autre que des espaces, est accepté");
		addItemFilmBadEntryTest ( sn, "Jean", null, "titre", "genre", "realisateur", "scenariste", 42, "1.3", "L'ajout d'un film avec un password n'est pas instancié est accepté");
		addItemFilmBadEntryTest ( sn, "Jean", "123", "titre", "genre", "realisateur", "scenariste", 42, "1.4", "L'ajout d'un film avec un password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
		addItemFilmBadEntryTest ( sn, "Jean", "123456", null, "genre", "realisateur", "scenariste", 42, "1.5", "L'ajout d'un film dont le titre n'est pas instancié est accepté");
		addItemFilmBadEntryTest ( sn, "Jean", "123456", "", "genre", "realisateur", "scenariste", 42, "1.6", "L'ajout d'un film dont le titre contient moins de 1 caractÃ¨re est accepté");
		addItemFilmBadEntryTest ( sn, "Jean", "123456", "titre", null, "realisateur", "scenariste", 42, "1.7", "L'ajout d'un film dont le genre n'est pas instancié est accepté");
		addItemFilmBadEntryTest ( sn, "Jean", "123456", "titre", "genre", null, "scenariste", 42, "1.8", "L'ajout d'un film dont le réalisateur n'est pas instancié est accepté");
		addItemFilmBadEntryTest ( sn, "Jean", "123456", "titre", "genre", "realisateur", null, 42, "1.8", "L'ajout d'un film dont le scenariste n'est pas instancié est accepté");		
		addItemFilmBadEntryTest ( sn, "Jean", "123456", "titre", "genre", "realisateur", "scenariste",-42, "1.9", "L'ajout d'un film dont le nombre de page est négatif ou nul est accepté");

		
		

		// <=> fiche numéro 2

		// ajout de 2 films avec entrées "correctes"	
		addItemFilmOKTest (sn, "Jean", "123456", "BoomBoom", "romance", "Michel BAY", "Michel BAY", 100, "2.1");
		addItemFilmOKTest (sn, "Jean", "123456", "Troll 2", "art et essais", "Édouard Khil", "Édouard Khil", 100, "2.1");

		// tentative d'ajout de film "existant"		
		addItemFilmAlreadyExistsTest(sn, "Jean", "123456", "BoomBoom", "romance", "Michel BAY", "Michel BAY", 100, "3.1", "L'ajout d'un film avec le titre du premier film ajouté est accepté");
		addItemFilmAlreadyExistsTest(sn, "Jean", "123456", "BoOmbooM", "romance", "Michel BAY", "Michel BAY", 100, "3.2", "L'ajout d'un film avec un titre existant (avec casse différente) est accepté");
		addItemFilmAlreadyExistsTest(sn, "Jean", "123456", "    BoomBoom    ", "romance", "Michel BAY", "Michel BAY", 100, "3.3", "L'ajout d'un film avec un titre existant (avec leading et trailing blanks) est accepté");		

		
		
		// tentative d'ajout de film avec un membre invalide
		addItemFilmNotMemberTest(sn, "Jeannot", "123456", "titre", "genre", "realisateur", "scenariste", 42, "4.1", "L'ajout d'un film avec un pseudo invalide est accepté");
		addItemFilmNotMemberTest(sn, "Jean", "j'exilerai ma peur", "titre", "genre", "realisateur", "scenariste",42, "4.2", "L'ajout d'un film avec un password ne corrspondant pas au pseudo est accepté");
		
		
		
		//Verification finale
		if (nbFilms +2 != sn.nbFilms()) {
			System.out.println("Erreur 5.1 :  le nombre de films aprés utilisation de addMember a été modifié");				
		}

		
		System.out.println("\n***************************\n***************************\nTests d'ajout de films:\n\tTests OK: " + cptOk + "\n\tTests NOK: " + cptErr + "\n***************************\n***************************");
		
	}

}
