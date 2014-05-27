package test;

import avis.SocialNetwork;

public class TestsRendementSocialNetwork {
	
	private static float addMembers(SocialNetwork sn, int nbMembers){
		String passwd = "test";
		String profil = "rendement";
		String pseudo = "testRendement";
			
		long t0, t1;
		float moyenne=0;
		
		/*
		 * Calcul du temps moyen d'ajout d'un membre
		 */
		try{
			t0 = System.currentTimeMillis();

			for(int i=0; i<nbMembers; i++){		
				sn.addMember(pseudo + i, passwd, profil);		
			}
			
			t1 = System.currentTimeMillis();
			moyenne = (t1 - t0)/(float)nbMembers;
		
		}catch(Exception e){
			System.err.println("Test rendement addMembers : exception non prévue. " + e);
			e.printStackTrace();
		}
		
		return moyenne;
	}
	
	private static float addItems(SocialNetwork sn, int nbMembres){
		String password = "test";
		String pseudo = "testRendement";
		String titre = "titre";
		String test = "test";
		int nbPages = 100;
			
		long t0, t1;
		float moyenne=0;
		/*
		 * Calcul du temps moyen d'ajout d'un item
		 */
		try{
			t0 = System.currentTimeMillis();
			
			//Ajout de 10 items par membres
			for(int i=0, j=0; i<nbMembres; i++, j=j+5){
				sn.addItemBook(pseudo + i, password, titre +j, test, test, nbPages);
				sn.addItemFilm(pseudo + i, password, titre +j, test, test, test, nbPages);	
				sn.addItemBook(pseudo + i, password, titre +j+1, test, test, nbPages);
				sn.addItemFilm(pseudo + i, password, titre +j+1, test, test, test, nbPages);
				sn.addItemBook(pseudo + i, password, titre +j+2, test, test, nbPages);
				sn.addItemFilm(pseudo + i, password, titre +j+2, test, test, test, nbPages);
				sn.addItemBook(pseudo + i, password, titre +j+3, test, test, nbPages);
				sn.addItemFilm(pseudo + i, password, titre +j+3, test, test, test, nbPages);
				sn.addItemBook(pseudo + i, password, titre +j+4, test, test, nbPages);
				sn.addItemFilm(pseudo + i, password, titre +j+4, test, test, test, nbPages);
			}
			
			t1 = System.currentTimeMillis();
			moyenne = (t1 - t0)/(float)nbMembres;
			
		}catch(Exception e){
			System.err.println("Test rendement addItems : exception non prévue. " + e);
			e.printStackTrace();
		}
		
		return moyenne;
	}
	
	private static float addAvis(SocialNetwork sn, int nbMembres){
		String password = "test";
		String pseudo = "testRendement";
		String titre = "titre";
		String test = "test";
		float note = 2.5f;
			
		long t0, t1;
		float moyenne=0;
		
		/*
		 * Calcul du temps moyen d'ajout d'un avis
		 */
		try{
			t0 = System.currentTimeMillis();
			
			//Ajout de 10 avis par membres
			for(int i=0, j=0; i<nbMembres; i++, j=j+5){
				sn.reviewItemBook(pseudo + i, password, titre + j, note, test);
				sn.reviewItemFilm(pseudo + i, password, titre + j, note, test);
				sn.reviewItemBook(pseudo + i, password, titre + j+1, note, test);
				sn.reviewItemFilm(pseudo + i, password, titre + j+1, note, test);
				sn.reviewItemBook(pseudo + i, password, titre + j+2, note, test);
				sn.reviewItemFilm(pseudo + i, password, titre + j+2, note, test);
				sn.reviewItemBook(pseudo + i, password, titre + j+3, note, test);
				sn.reviewItemFilm(pseudo + i, password, titre + j+3, note, test);
				sn.reviewItemBook(pseudo + i, password, titre + j+4, note, test);
				sn.reviewItemFilm(pseudo + i, password, titre + j+4, note, test);
			}
			
			t1 = System.currentTimeMillis();
			moyenne = (t1 - t0)/(float)nbMembres;
	
		}catch(Exception e){
			System.err.println("Test rendement addAvis : exception non prévue. " + e);
			e.printStackTrace();
		}
		
		return moyenne;
	}
	
	public static void main(String [] arg){
		float moyenne;
		int nbTest = 500;
		
		SocialNetwork sn = new SocialNetwork();
		
		System.out.println("***************************");
		System.out.println("Test de rendement du Social Network avec de " + nbTest + " membres, " + nbTest*10 + " items et " + nbTest*10 + " avis");
		System.out.println("Merci de patienter, test en cours...");
		moyenne=addMembers(sn, nbTest);
		System.out.println("Moyenne temps ajout membre: " + moyenne + " ms");
		moyenne=addItems(sn, nbTest);
		System.out.println("Moyenne temps ajout item: " + moyenne + " ms");
		moyenne=addAvis(sn, nbTest);
		System.out.println("Moyenne temps ajout avis: " + moyenne + " ms");
		System.out.println("***************************");

	}
	
}
