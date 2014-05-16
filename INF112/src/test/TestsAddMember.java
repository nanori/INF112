package test;

import java.util.LinkedList;

import avis.SocialNetwork;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;

/** 
 * @author B. Prou, E. Cousin
 * @date mars 2014
 * @version V0.9
 */

public class TestsAddMember {

	

	public static void addMemberBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String profil, String idTest, String messErreur){
		// verifie que l'ajout d'un membre (pseudo, pwd, profil) est refusee (levée de l'exception BadEntry et  pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passe en parametre
		int nbMembres = sn.nbMembers();
		try {
			sn.addMember (pseudo, pwd, profil);
			System.out.println ("Test " + idTest + " : " + messErreur);
		}
		catch (BadEntry e) {
			if (sn.nbMembers() != nbMembres) System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de membres a été modifié");
			// else : OK
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non prévue. " + e); e.printStackTrace();}
	}

	public static void addMemberOKTest (SocialNetwork sn, String pseudo, String pwd, String profil, String idTest){
		int nbMembres = sn.nbMembers();
		try{
			sn.addMember (pseudo, pwd, profil);
			if (sn.nbMembers() != nbMembres+1) System.out.println("Test " + idTest + " :  le nombre de membres n'a pas été correctement incrémenté");
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non prévue. " + e); e.printStackTrace();}
	}

	public static void addMemberAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String profil, String idTest, String messErreur){
		int nbMembres = sn.nbMembers();
		try {
			sn.addMember (pseudo, pwd, profil);
			System.out.println ("Test " + idTest + " : " + messErreur);
		}
		catch (MemberAlreadyExists e) {
			if (sn.nbMembers() != nbMembres) System.out.println("Test " + idTest + " : l'exception MemberAlreadyExists a bien été levée mais le nombre de membres a été modifié");
			// else : OK
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non prévue. " + e); e.printStackTrace();}
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int nbMembres = 0;
		int nbLivres = 0;
		int nbFilms = 0;

		System.out.println("Tests  ajouter des membres au réseau social  ");


		SocialNetwork sn = new SocialNetwork();

		// tests de addMember
		nbFilms = sn.nbFilms();
		nbLivres = sn.nbBooks();

		// <=> fiche numéro 1

		// tentative d'ajout de membres avec entrées "incorrectes"

		addMemberBadEntryTest ( sn, null, "qsdfgh", "", "3.1", "L'ajout d'un membre dont le pseudo n'est pas instancié est accepté");
		addMemberBadEntryTest ( sn, " ", "qsdfgh", "", "3.2", "L'ajout d'un membre dont le pseudo ne contient pas un caracteres, autre que des espaces, est accepté");
		addMemberBadEntryTest ( sn, "B", null, "", "3.3", "L'ajout d'un membre dont le password n'est pas instancié est accepté");
		addMemberBadEntryTest ( sn, "B", "   qwd ", "", "3.4", "L'ajout d'un membre dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
		addMemberBadEntryTest ( sn, "BBBB", "bbbb", null, "3.5", "L'ajout d'un membre dont le profil n'est pas instancié est accepté");



		// <=> fiche numéro 2

		// ajout de 3 membres avec entrées "correctes"
		
		addMemberOKTest (sn, "Paul", "paul", "lecteur impulsif","3.6,1");
		addMemberOKTest (sn, "Antoine", "antoine", "grand amoureux de la littérature","3.6,2");
		addMemberOKTest (sn, "Alice", "alice", "20 ans, sexy","3.6,3");

		// tentative d'ajout de membre "existant"
		
		addMemberAlreadyExistsTest(sn, "Paul", "abcdefghij", "", "3.7", "L'ajout d'un membre avec le pseudo du premier membre ajouté est accepté");
		addMemberAlreadyExistsTest(sn, "Alice", "abcdefghij", "", "3.8", "L'ajout d'un membre avec le pseudo du dernier membre ajouté est accepté");
		addMemberAlreadyExistsTest(sn, "anToine", "abcdefghij", "", "3.9", "L'ajout d'un membre avec un pseudo existant (avec casse différente) est accepté");
		addMemberAlreadyExistsTest(sn, " Antoine ", "abcdefghij", "", "3.10", "L'ajout d'un membre avec un pseudo existant (avec leading et trailing blanks) est accepté");		


		if (nbFilms != sn.nbFilms()) {
			System.out.println("Erreur 3.11 :  le nombre de films après utilisation de addMember a été modifié");
		}
		if (nbLivres != sn.nbBooks()) {
			System.out.println("Erreur 3.12 :  le nombre de livres après utilisation de addMember a été modifié");				
		}

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

	}
}
