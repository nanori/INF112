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
		members = new LinkedList<Member>();
		items = new LinkedList<Item>();
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
		
		while (i<items.size()){
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
		
		while (i<items.size()){
			if(items.get(i) instanceof Book){
				cptBooks++;
			}
			i++;
		}
		return cptBooks;
	}

	public void addMember(String pseudo, String password, String profil) throws BadEntry, MemberAlreadyExists {
		
		// pseudo null OU taille du pseudo inferieur a 1 caractere OU pseudo
		// composé uniquement de blancs
		if (pseudo == null || pseudo.length() < 1 || pseudo.matches("\\p{Space}+?")) {
			throw new BadEntry("Invalid username");
		}

		// password non instancié OU taille inferieure a 4 (leading et trailing
		// blanks ignorés)
		if (password == null || password.trim().length() < 4) {
			throw new BadEntry("Invalid password");
		}

		// profil non instancié
		if (profil == null) {
			throw new BadEntry("Invalid profile");
		}

		int i = 0;
		boolean memberIsFound = false;
		while (!memberIsFound && i < nbMembers()) {
			memberIsFound = members.get(i).exists(pseudo);
			i++;
		}

		// si membre de meme pseudo existant
		if (memberIsFound) {
			throw new MemberAlreadyExists();
		}

		Member membre = new Member(pseudo, password, profil, this);
		members.addLast(membre);
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
				
		if(duree <= 0)
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
			i++;
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
		
		if(nbPages <= 0)
			throw new BadEntry("Invalid page number");
		
		
		//Test du couple pseudo/password
		while (!memberIsFound && i<nbMembers()){
			memberIsFound=members.get(i).exists(pseudo, password);
			i++;
		}
		
		if(!memberIsFound)
			throw new NotMember("Member does not exist");
		
		
		//Test de l'existance de l'item
		i=0;
		while (!filmIsFound && i<nbBooks()){
			if(items.get(i) instanceof Book){
				tmpBook = (Book)items.get(i);
				if(tmpBook.exists(titre)){
					throw new ItemBookAlreadyExists();
				}
			}
			i++;
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
			i++;
		}
		
		return returnList;
		
	}

	public float reviewItemFilm(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {

		// pseudo null OU taille du pseudo inferieur a 1 caractere OU pseudo
		// composé uniquement de blancs
		if (pseudo == null || pseudo.length() < 1 || pseudo.matches("\\p{Space}+?")) {
			throw new BadEntry("Invalid username");
		}

		// password non instancié OU taille inferieure a 4 (leading et trailing
		// blanks ignorés)
		if (password == null || password.trim().length() < 4) {
			throw new BadEntry("Invalid password");
		}
		
		
		//titre non instancié ou ou moins de 1 caracteres autres que espaces
		if (titre==null || titre.trim().length() < 1) {
			throw new BadEntry("Invalid title");
		}
		
		//note en dehors de [0.0, 5.0]
		if (note < 0.0 || note > 5.0) {
			throw new BadEntry("Invalid grade");
		}
		
		//commentaire non instancié
		if (commentaire == null) {
			throw new BadEntry("Invalid comment");
		}
		
		//pseudo et password ne correspondent pas ou pseudo n'existe pas
		int i = 0;
		boolean memberIsFound = false;
		Member member = null;
		while (!memberIsFound && i < members.size()) {
			// si membre inconnu
			memberIsFound = members.get(i).exists(pseudo, password);
			member = members.get(i);
			i++;
		}
		if(!memberIsFound){
			throw new NotMember("Member does not exist");
		}

		//Titre ne correspond pas a un film
		i = 0;
		boolean filmIsFound = false;
		Item item = null;
		while (!filmIsFound && i < nbFilms()) {
			if (items.get(i) instanceof Film) {
				filmIsFound=items.get(i).exists(titre);				
				item = items.get(i);
			}
			i++;
		}
		if(!filmIsFound){
			throw new NotItem("Film does not exist");
		}
		
		
		
		Avis avis = new Avis(note, commentaire, item, member);
		item.addReviewToCollection(avis);
		
		return item.getMoyenne();
	}

	public float reviewItemBook(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		// pseudo null OU taille du pseudo inferieur a 1 caractere OU pseudo
		// composé uniquement de blancs
		if (pseudo == null || pseudo.length() < 1 || pseudo.matches("\\p{Space}+?")) {
			throw new BadEntry("Invalid username");
		}

		// password non instancié OU taille inferieure a 4 (leading et trailing
		// blanks ignorés)
		if (password == null || password.trim().length() < 4) {
			throw new BadEntry("Invalid password");
		}
				
				
		//titre non instancié ou ou moins de 1 caracteres autres que espaces
		if (titre==null || titre.trim().length() < 1) {
			throw new BadEntry("Invalid title");
		}
				
		//note en dehors de [0.0, 5.0]
		if (note < 0.0 || note > 5.0) {
			throw new BadEntry("Invalid grade");
		}
				
		//commentaire non instancié
		if (commentaire == null) {
			throw new BadEntry("Invalid comment");
		}
				
		//pseudo et password ne correspondent pas ou pseudo n'existe pas
		int i = 0;
		boolean memberIsFound = false;
		Member member = null;
		while (!memberIsFound && i < members.size()) {
			// si membre inconnu
			memberIsFound = members.get(i).exists(pseudo, password);
			member = members.get(i);
			i++;
		}
		if(!memberIsFound){
			throw new NotMember("Member does not exist");
		}

		//Titre ne correspond pas a un livre
		i = 0;
		boolean bookIsFound = false;
		Item item = null;
		while (!bookIsFound && i < nbBooks()) {
			if (items.get(i) instanceof Book) {
				bookIsFound=items.get(i).exists(titre);				
				item = items.get(i);
			}
			i++;
		}
		if(!bookIsFound){
			throw new NotItem("Book does not exist");
		}
						
		Avis avis = new Avis(note, commentaire, item, member);
		item.addReviewToCollection(avis);
				
		return item.getMoyenne();
	}

	public String toString() {
		String retour;
		
		retour="Social Network : \n" +
				"Les membres: \n";
		for(int i=0; i<nbMembers(); i++) {
			retour += members.get(i).toString();
		}
		retour += "Les films : \n";		
		for(int i=0; i<items.size(); i++) {
			if(items.get(i) instanceof Film){
				retour += items.get(i).toString();
			}
		}
		retour += "Les livres : \n";		
		for(int i=0; i<items.size(); i++) {
			if(items.get(i) instanceof Book){
				retour += items.get(i).toString();
			}
		}
		return retour;
	}

}
