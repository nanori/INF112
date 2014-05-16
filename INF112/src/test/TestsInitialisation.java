package test;

import java.util.LinkedList;

import avis.SocialNetwork;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;

public class TestsInitialisation {


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int nbMembres = 0;
		int nbLivres = 0;
		int nbFilms = 0;
		
		System.out.println("Tests  initialisation  r�seau social  ");
	
		
		try {

			// un r�seau social cr�� ne doit avoir ni membres ni items
			SocialNetwork sn = new SocialNetwork();
			if (sn.nbMembers()!= 0) {
				System.out.println("Erreur 1.1 :  le nombre de membres apr�s cr�ation du r�seau est non nul");
				System.exit(1);
			}
			if (sn.nbBooks() != 0) {
				System.out.println("Erreur 1.2 : le nombre de livres apr�s cr�ation du r�seau est non nul");
				System.exit(1);
			}
			if (sn.nbFilms() != 0) {
				System.out.println("Erreur 1.2 : le nombre de films apr�s cr�ation du r�seau est non nul");
				System.exit(1);
			}

			
			System.out.println(sn);
			
		}
		catch (Exception e) {
			System.out.println("Exception non pr�vue : " + e);
			e.printStackTrace();
		}
	}

	
}
