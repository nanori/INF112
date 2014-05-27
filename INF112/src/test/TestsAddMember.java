package test;

import avis.SocialNetwork;
import exception.BadEntry;
import exception.MemberAlreadyExists;

/** 
 * @author B. Prou, E. Cousin
 * @date mars 2014
 * @version V0.9
 */

public class TestsAddMember {

	public static int cptErr;
	public static int cptOk;

	public static void addMemberBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String profil, String idTest, String messErreur){
		// verifie que l'ajout d'un membre (pseudo, pwd, profil) est refusee (lev�e de l'exception BadEntry et  pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passe en parametre
		int nbMembres = sn.nbMembers();
		try {
			sn.addMember (pseudo, pwd, profil);
			System.out.println ("Test " + idTest + " : " + messErreur);
			cptErr++;
		}
		catch (BadEntry e) {
			if (sn.nbMembers() != nbMembres) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien �t� lev�e mais le nombre de membres a �t� modifi�");
				cptErr++;
			}
			cptOk++;
		}
		catch (Exception e) {
			cptErr++;
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); e.printStackTrace();}
	}

	public static void addMemberOKTest (SocialNetwork sn, String pseudo, String pwd, String profil, String idTest){
		int nbMembres = sn.nbMembers();
		try{
			sn.addMember (pseudo, pwd, profil);
			if (sn.nbMembers() != nbMembres+1) {
				System.out.println("Test " + idTest + " :  le nombre de membres n'a pas �t� correctement incr�ment�");
				cptErr++;
			}
			cptOk++;
		}
		catch (Exception e) {
			cptErr++;
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); e.printStackTrace();}
	}

	public static void addMemberAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String profil, String idTest, String messErreur){
		int nbMembres = sn.nbMembers();
		try {
			sn.addMember (pseudo, pwd, profil);
			System.out.println ("Test " + idTest + " : " + messErreur);
			cptErr++;
		}
		catch (MemberAlreadyExists e) {
			if (sn.nbMembers() != nbMembres) {
				cptErr++;
				System.out.println("Test " + idTest + " : l'exception MemberAlreadyExists a bien �t� lev�e mais le nombre de membres a �t� modifi�");
			}
			cptOk++;
		}
		catch (Exception e) {
			cptErr++;
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); e.printStackTrace();}
	}




	public static void main(String[] args) {

		int nbLivres = 0;
		int nbFilms = 0;


		SocialNetwork sn = new SocialNetwork();

		// tests de addMember
		nbFilms = sn.nbFilms();
		nbLivres = sn.nbBooks();

		// <=> fiche num�ro 1

		// tentative d'ajout de membres avec entr�es "incorrectes"

		addMemberBadEntryTest ( sn, null, "qsdfgh", "", "3.1", "L'ajout d'un membre dont le pseudo n'est pas instanci� est accept�");
		addMemberBadEntryTest ( sn, " ", "qsdfgh", "", "3.2", "L'ajout d'un membre dont le pseudo ne contient pas un caracteres, autre que des espaces, est accept�");
		addMemberBadEntryTest ( sn, "B", null, "", "3.3", "L'ajout d'un membre dont le password n'est pas instanci� est accept�");
		addMemberBadEntryTest ( sn, "B", "   qwd ", "", "3.4", "L'ajout d'un membre dont le password ne contient pas au moins 4 caracteres, autre que des espaces de d�but ou de fin, est accept�");
		addMemberBadEntryTest ( sn, "BBBB", "bbbb", null, "3.5", "L'ajout d'un membre dont le profil n'est pas instanci� est accept�");



		// <=> fiche num�ro 2

		// ajout de 3 membres avec entr�es "correctes"
		
		addMemberOKTest (sn, "Paul", "paul", "lecteur impulsif","3.6,1");
		addMemberOKTest (sn, "Antoine", "antoine", "grand amoureux de la litt�rature","3.6,2");
		addMemberOKTest (sn, "Alice", "alice", "20 ans, sexy","3.6,3");

		// tentative d'ajout de membre "existant"
		
		addMemberAlreadyExistsTest(sn, "Paul", "abcdefghij", "", "3.7", "L'ajout d'un membre avec le pseudo du premier membre ajout� est accept�");
		addMemberAlreadyExistsTest(sn, "Alice", "abcdefghij", "", "3.8", "L'ajout d'un membre avec le pseudo du dernier membre ajout� est accept�");
		addMemberAlreadyExistsTest(sn, "anToine", "abcdefghij", "", "3.9", "L'ajout d'un membre avec un pseudo existant (avec casse diff�rente) est accept�");
		addMemberAlreadyExistsTest(sn, " Antoine ", "abcdefghij", "", "3.10", "L'ajout d'un membre avec un pseudo existant (avec leading et trailing blanks) est accept�");		


		if (nbFilms != sn.nbFilms()) {
			System.out.println("Erreur 3.11 :  le nombre de films apr�s utilisation de addMember a �t� modifi�");
			cptErr++;
		}
		else{cptOk++;}
		if (nbLivres != sn.nbBooks()) {
			System.out.println("Erreur 3.12 :  le nombre de livres apr�s utilisation de addMember a �t� modifi�");
			cptErr++;
		}else{cptOk++;}

		System.out.println("***************************");
		System.out.println("Tests d'ajout de membres:");
		System.out.println("\tTests OK: " + cptOk);
		System.out.println("\tTests NOK: " + cptErr);
		System.out.println("***************************");


	}
}
