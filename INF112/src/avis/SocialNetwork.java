package avis;

import java.util.LinkedList;
import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;

public class SocialNetwork {

	/**
	 * ATTRIBUTS
	 */
	private LinkedList<Member> members;
	private LinkedList<Item> items;

	/**
	 * CONSTRUCTEUR
	 */
	public SocialNetwork() {
	}

	/**
	 * METHODES
	 */
	public int nbMembers() {
		return members.size();
	}

	public int nbFilms() {
		int i=0;
		int cptFilms=0;
		
		while (i<nbFilms()){
			if(items.get(i) instanceof Film){
				cptFilms++;
			}
			i++;
		}
		return cptFilms;
	}

	public int nbBooks() {
		int i=0;
		int cptBooks=0;
		
		while (i<nbFilms()){
			if(items.get(i) instanceof Book){
				cptBooks++;
			}
			i++;
		}
		return cptBooks;
	}

	public void addMember(String pseudo, String password, String profil) throws BadEntry, MemberAlreadyExists {
		
	}

	public void addItemFilm(String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree) throws BadEntry, NotMember, ItemFilmAlreadyExists {
		//Variables
		boolean memberIsFound=false;
		boolean filmIsFound=false;
		int i=0;
		
		//Test des parametres
		if(pseudo==null || pseudo.trim().length()<1)
			throw new BadEntry("Invalid pseudo");
		
		if(password==null || password.trim().length()<4)
			throw new BadEntry("Invalid password");
		
		if(titre==null || titre.trim().length()<1)
			throw new BadEntry("Invalid title");
		
		if(genre==null)
			throw new BadEntry("Invalid movie genre");
		
		if(realisateur==null)
			throw new BadEntry("Invalid director");
		
		if(scenariste==null)
			throw new BadEntry("Invalid scriptwriter");
				
		if(duree == 0)
			throw new BadEntry("Invalid running time");
		
		
		//Test du couple pseudo/password
		while (!memberIsFound && i<nbMembers()){
			memberIsFound=members.get(i).exists(pseudo, password);
			i++;
		}
		if(!memberIsFound)
			throw new NotMember("Member does not exist");
		
		
		//Test de l'existance de l'item
		i=0;
		while (!filmIsFound && i<nbFilms()){
			if(items.get(i) instanceof Film){
				if(items.get(i).exists(titre)){
					throw new ItemFilmAlreadyExists();
				}
			}
		}
		
		//Ajout du film
		items.add(new Film(titre, genre, realisateur, scenariste, duree));
		
	}

	
	public void addItemBook(String pseudo, String password, String titre, String genre, String auteur, int nbPages) throws BadEntry, NotMember, ItemBookAlreadyExists {
		boolean memberIsFound=false;
		boolean filmIsFound=false;
		Book tmpBook;
		int i=0;
		
		//Test des parametres
		if(pseudo==null || pseudo.trim().length()<1)
			throw new BadEntry("Invalid pseudo");
		
		if(password==null || password.trim().length()<4)
			throw new BadEntry("Invalid password");
		
		if(titre==null || titre.trim().length()<1)
			throw new BadEntry("Invalid title");
		
		if(genre==null)
			throw new BadEntry("Invalid movie genre");
		
		if(auteur==null)
			throw new BadEntry("Invalid director");
		
		if(nbPages == 0)
			throw new BadEntry("Invalid running time");
		
		
		//Test du couple pseudo/password
		while (!memberIsFound && i<nbMembers()){
			memberIsFound=members.get(i).exists(pseudo, password);
			i++;
		}
		
		if(!memberIsFound)
			throw new NotMember("Member does not exist");
		
		
		//Test de l'existance de l'item
		i=0;
		while (!filmIsFound && i<nbFilms()){
			if(items.get(i) instanceof Book){
				tmpBook = (Book)items.get(i);
				if(!tmpBook.exists(titre)){
					throw new ItemBookAlreadyExists();
				}
			}
		}
		
		//Ajout du film
		tmpBook = new Book(titre, genre, auteur, nbPages);
		items.add(tmpBook);
	}

	
	public LinkedList<String> consultItems(String nom) throws BadEntry {
		LinkedList<String> returnList = new LinkedList<String>();
		int i=0;
		
		//Test des parametres
		if(nom==null || nom.trim().length()<1)
			throw new BadEntry("Invalid title");
		
		
		while (i<nbFilms()){
			if(items.get(i).exists(nom)){
				returnList.add(items.get(i).toString());
			}
		}
		
		return returnList;
		
	}

	public float reviewItemFilm(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		return 0.0f;
	}

	public float reviewItemBook(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		return 0.0f;
	}

	public String toString() {
		return "";
	}

}
