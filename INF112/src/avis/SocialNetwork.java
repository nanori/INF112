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
	 * Liste contenant l'ensembe des membres du social network
	 */
	private LinkedList<Member> members;
	
	/**
	 * Liste contenant l'ensemble des items du social network
	 */
	private LinkedList<Item> items;

	/**
	 * Constructeur SocialNetwork
	 * <br>
	 * A la construction du social network, la listes des membres et des items sont instanciées avec des listes vides.
	 */
	public SocialNetwork() {
		members = new LinkedList<Member>();
		items = new LinkedList<Item>();
	}

	/** 
	 * @return
	 * 			Nombre de membre du social network
	 */
	public int nbMembers() {
		return members.size();
	}

	/** 
	 * @return
	 * 			Nombre de films du social network
	 */
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
	
	/** 
	 * @return
	 * 			Nombre de livres du social network
	 */
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

	/**
	 * Ajoute un membre au social network<br>
	 * <br>
	 * Le pseudo doit contenir au moins 1 caractere autre que des espaces<br>
	 * Le mot de passe doit contenir au moins 4 caractere autre que les leading et trailling blanks<br>
	 * Le pseudo ne doit pas déjà exister 
	 * 
	 * @param pseudo
	 * 			Pseudo du membre à ajouter
	 * 
	 * @param password
	 * 			Mot de passe du membre à ajouter
	 * 
	 * @param profil
	 * 			Descriptif du membre à ajouter
	 * 
	 */
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

	/**
	 * Ajoute un film au social network<br>
	 * <br>
	 * Le pseudo doit contenir au moins 1 caractere autre que des espaces<br>
	 * Le mot de passe doit contenir au moins 4 caractere autre que les leading et trailling blanks<br>
	 * Le titre doit contenir au moins 1 caractere autre que des espaces<br>
	 * La durée doit être positive
	 * 
	 * @param pseudo
	 * 			Pseudo du membre qui ajoute le film
	 * 
	 * @param password
	 * 			Mot de passe du membre qui ajoute le film
	 * 
	 * @param titre
	 * 			Titre du film
	 * 
	 * @param genre
	 * 			Genre du film
	 * 
	 * @param realisateur
	 * 			Realisateur du film
	 * 
	 * @param scenariste
	 * 			Scenariste du film
	 * 
	 * @param duree
	 * 			Durée du film
	 * 
	 */
	public void addItemFilm(String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree) throws BadEntry, NotMember, ItemFilmAlreadyExists {
		//Variables
		boolean filmIsFound=false;
		int i=0;
		
		//Pseudo contient au moins 1 caractere autre que des espaces
		if(pseudo==null || pseudo.trim().length()<1)
			throw new BadEntry("Invalid pseudo");
		
		//Password superieur a 4 caractères autre que les leading et trailling banks
		if(password==null || password.trim().length()<4)
			throw new BadEntry("Invalid password");
		
		//Titre contient au moins 1 caractere autre que des espaces
		if(titre==null || titre.trim().length()<1)
			throw new BadEntry("Invalid title");
		
		if(genre==null)
			throw new BadEntry("Invalid movie type");
		
		if(realisateur==null)
			throw new BadEntry("Invalid director");
		
		if(scenariste==null)
			throw new BadEntry("Invalid scriptwriter");
				
		if(duree <= 0)
			throw new BadEntry("Invalid running time");
		
		//Authentification
		if(authentication(pseudo, password)==null)
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

	/**
	 * Ajoute un livre au social network<br>
	 * <br>
	 * Le pseudo doit contenir au moins 1 caractere autre que des espaces<br>
	 * Le mot de passe doit contenir au moins 4 caractere autre que les leading et trailling blanks<br>
	 * Le titre doit contenir au moins 1 caractere autre que des espaces<br>
	 * La durée doit être positive
	 * 
	 * @param pseudo
	 * 			Pseudo du membre qui ajoute le livre
	 * 
	 * @param password
	 * 			Mot de passe du membre qui ajoute le livre
	 * 
	 * @param titre
	 * 			Titre du livre
	 * 
	 * @param genre
	 * 			Genre du livre
	 * 
	 * @param auteur
	 * 			Realisateur du livre
	 * 
	 * @param nbPages
	 * 			Scenariste du livre
	 */
	public void addItemBook(String pseudo, String password, String titre, String genre, String auteur, int nbPages) throws BadEntry, NotMember, ItemBookAlreadyExists {
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
		
		//Authentification
		if(authentication(pseudo, password)==null)
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

	/**
	 * Retourne le toString des items correspondant à la recherche<br>
	 * <br>
	 * Méthode non sensible à la casse, le nom doit contenir plus d'un caractere autre que des espaces.
	 * 
	 * @param nom
	 * 			Nom de l'item recherché
	 * 
	 * @return 
	 * 			Liste des items trouvé sous forme verbeuse
	 */
	public LinkedList<String> consultItems(String nom) throws BadEntry {
		LinkedList<String> returnList = new LinkedList<String>();
		int i=0;
		
		//Test des parametres
		if(nom==null || nom.trim().length()<1)
			throw new BadEntry("Invalid title");
		
		
		while (i<items.size()){
			if(items.get(i).exists(nom)){
				returnList.add(items.get(i).toString());
			}
			i++;
		}
		
		return returnList;
		
	}

	/**
	 * Dépose un avis sur un film<br>
	 * <br>
	 * Le pseudo doit contenir au moins 1 caractere autre que des espaces<br>
	 * Le mot de passe doit contenir au moins 4 caractere autre que les leading et trailling blanks<br>
	 * Le titre doit contenir au moins un caractere<br>
	 * La note doit être comprise entre 0 et 5<br>
	 * Le pseudo et le password doivent désigner un membre<br>
	 * Le film doit exister
	 * 
	 * @param pseudo
	 * 			Pseudo du membre qui dépose l'avis
	 * 
	 * @param password
	 * 			Password du membre qui dépose l'avis
	 * 
	 * @param titre
	 * 			Titre du film
	 * 
	 * @param note
	 * 			Note attribuée par le membre au film
	 * 
	 * @param commentaire
	 * 			Commentaire du membre sur le film
	 * 
	 * @return 
	 * 			Moyenne des notes déposées sur ce film
	 */
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
		
		Member member = authentication(pseudo, password);
		if(member==null){
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

	/**
	 * Dépose un avis sur un livre<br>
	 * <br>
	 * Le pseudo doit contenir au moins 1 caractere autre que des espaces<br>
	 * Le mot de passe doit contenir au moins 4 caractere autre que les leading et trailling blanks<br>
	 * Le titre doit contenir au moins 1 caractere<br>
	 * La note doit être comprise entre 0 et 5<br>
	 * Le pseudo et le password doivent désigner un membre<br>
	 * Le livre doit exister
	 * 
	 * @param pseudo
	 * 			Pseudo du membre qui dépose l'avis
	 * 
	 * @param password
	 * 			Password du membre qui dépose l'avis
	 * 
	 * @param titre
	 * 			Titre du livre
	 * 
	 * @param note
	 * 			Note attribuée par le membre au livre
	 * 
	 * @param commentaire
	 * 			Commentaire du membre sur le livre
	 * 
	 * @return 
	 * 			Moyenne des notes déposées sur ce livre
	 */
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
		Member member = authentication(pseudo, password);
		if(member==null){
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

	/**
	 * Authentifie un membre
	 *
	 * @param pseudo
	 * 			Pseudo du membre qui dépose l'avis
	 * 
	 * @param password
	 * 			Password du membre qui dépose l'avis
	 * 
	 * @return 
	 * 			Le membre désigné par le couple pseudo/password <br>
	 * 			Null si le pseudo ou le password sont invalides
	 */
	private Member authentication(String pseudo, String password){
		boolean memberIsFound = false;
		Member m = null;
		int i=0;
		while (!memberIsFound && i<nbMembers()){
			memberIsFound=members.get(i).exists(pseudo, password);
			if (memberIsFound)
				m=members.get(i);
			
			i++;
		}
		
		return m;
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
