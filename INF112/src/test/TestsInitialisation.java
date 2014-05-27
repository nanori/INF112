package test;

import avis.SocialNetwork;

public class TestsInitialisation {


	public static void main(String[] args) {
		
		int cptOk=0;
		int cptErr=0;
		
		try {

			// un réseau social créé ne doit avoir ni membres ni items
			SocialNetwork sn = new SocialNetwork();
			if (sn.nbMembers()!= 0) {
				System.out.println("Erreur 1.1 :  le nombre de membres après création du réseau est non nul");
				System.exit(1);
			}
			cptOk++;
			if (sn.nbBooks() != 0) {
				System.out.println("Erreur 1.2 : le nombre de livres après création du réseau est non nul");
				System.exit(1);
			}
			cptOk++;
			if (sn.nbFilms() != 0) {
				System.out.println("Erreur 1.2 : le nombre de films après création du réseau est non nul");
				System.exit(1);
			}
			cptOk++;

			System.out.println("***************************");
			System.out.println("Tests affichage SocialNetwork vide :\n");
			System.out.println(sn);

		}
		catch (Exception e) {
			System.out.println("Exception non prévue : " + e);
			e.printStackTrace();
		}
		
		System.out.println("***************************");
		System.out.println("Tests initialisation réseau social  ");
		System.out.println("\tTests OK: " + cptOk);
		System.out.println("\tTests NOK: " + cptErr);
		System.out.println("***************************");
	}

	
}
