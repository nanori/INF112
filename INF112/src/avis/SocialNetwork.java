package avis;

import java.util.LinkedList;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;
import exception.NotReview;

public class SocialNetwork {
	public enum itemsTypes {
		BOOK,
		FILM
	}

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
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le profil n'est pas instancié.  </li>
	 * </ul><br>       
	 * 
	 * @throws MemberAlreadyExists 
	 * 			membre de même pseudo déjà  présent dans le <i>SocialNetwork</i> (même pseudo : indifférent à   la casse  et aux leadings et trailings blanks)
	 * 
	 */
	public void addMember(String pseudo, String password, String profil) throws BadEntry, MemberAlreadyExists {
		/*
		 * Tests des paramettres d'entrés
		 */
		if (pseudo == null || pseudo.length() < 1 || pseudo.matches("\\p{Space}+?"))
			throw new BadEntry("Invalid username");
		
		if (password == null || password.trim().length() < 4)
			throw new BadEntry("Invalid password");

		if (profil == null)
			throw new BadEntry("Invalid profile");

		int i = 0;
		boolean memberIsFound = false;
		while (!memberIsFound && i < nbMembers()) {
			memberIsFound = members.get(i).exists(pseudo);
			i++;
		}

		if (memberIsFound)
			throw new MemberAlreadyExists();
		
		/*
		 * Ajout du nouveau membre
		 */
		Member membre = new Member(pseudo, password, profil);
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
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si le réalisateur n'est pas instancié. </li>
	 *  <li>  si le scénariste n'est pas instancié. </li>
	 *  <li>  si la durée n'est pas positive.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemFilmAlreadyExists : item film de même titre  déjà  présent (même titre : indifférent à   la casse  et aux leadings et trailings blanks)
	 * 
	 */
	public void addItemFilm(String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree) throws BadEntry, NotMember, ItemFilmAlreadyExists {
		/*
		 * Tests des paramettres d'entrés
		 */
		if(pseudo==null || pseudo.trim().length()<1)
			throw new BadEntry("Invalid pseudo");
		
		if(password==null || password.trim().length()<4)
			throw new BadEntry("Invalid password");
		
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
		
		if(authentication(pseudo, password)==null)
			throw new NotMember("Member does not exist");
		
		if (getFilm(titre) != null)
			throw new ItemFilmAlreadyExists();
				
		/*
		 * Ajout du film
		 */
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
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si l'auteur n'est pas instancié. </li>
	 *  <li>  si le nombre de pages n'est pas positif.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemBookAlreadyExists item livre de même titre  déjà  présent (même titre : indifférent à  la casse  et aux leadings et trailings blanks)
	 */
	public void addItemBook(String pseudo, String password, String titre, String genre, String auteur, int nbPages) throws BadEntry, NotMember, ItemBookAlreadyExists {
		/*
		 * Tests des paramettres d'entrés
		 */
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
		
		if(authentication(pseudo, password)==null)
			throw new NotMember("Member does not exist");
		
		if (getBook(titre) != null)
			throw new ItemBookAlreadyExists();
		
		/*
		 * Ajout du livre
		 */
		items.add(new Book(titre, genre, auteur, nbPages));

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
	 * 
	 * @throws BadEntry 
	 * 			si le nom n'est pas instancié ou a moins de 1 caractère autre que des espaces.
	 */
	public LinkedList<String> consultItems(String nom) throws BadEntry {
		/*
		 * Test du paramettre d'entré
		 */
		if(nom==null || nom.trim().length()<1)
			throw new BadEntry("Invalid title");
		
		/*
		 * Recherche des Items
		 */
		LinkedList<String> returnList = new LinkedList<String>();
		if (getBook(nom) != null)
			returnList.add(getBook(nom).toString());
		
		if (getFilm(nom) != null)
			returnList.add(getFilm(nom).toString());
	
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
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un film.
	 * 
	 * @return la note moyenne des notes sur ce film  
	 */
	public float reviewItemFilm(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		/*
		 * Variables
		 */
		Member member;
		Film f;
		Avis avis;
		
		/*
		 * Tests des paramettres d'entrés
		 */
		if (pseudo == null || pseudo.length() < 1 || pseudo.matches("\\p{Space}+?"))
			throw new BadEntry("Invalid username");
		
		if (password == null || password.trim().length() < 4)
			throw new BadEntry("Invalid password");		
		
		if (titre==null || titre.trim().length() < 1)
			throw new BadEntry("Invalid title");
		
		if (note < 0.0 || note > 5.0)
			throw new BadEntry("Invalid grade");
		
		if (commentaire == null)
			throw new BadEntry("Invalid comment");
		
		member = authentication(pseudo, password);
		if(member == null)
			throw new NotMember("Member does not exist");

		f = getFilm(titre);
		if(f == null)
			throw new NotItem("Film does not exist");
		
		/*
		 * Ajout de l'avis
		 */
		avis = member.getReview(f);
		if(avis == null){
			//Si le membre n'a pas déjà noté cet item
			avis = new Avis(note, commentaire, f, member);
			member.addReviewToCollection(avis);
			f.addReviewToCollection(avis);
		} else {
			//Si le membre a déjà noté l'item
			avis.update(note, commentaire);
		}
		
		return f.getMoyenne();
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
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un livre.
	 */
	public float reviewItemBook(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		/*
		 * Variables
		 */
		Member member;
		Book b;
		Avis avis;

		/*
		 * Tests des paramettres d'entrés
		 */
		if (pseudo == null || pseudo.length() < 1 || pseudo.matches("\\p{Space}+?"))
			throw new BadEntry("Invalid username");
		
		if (password == null || password.trim().length() < 4)
			throw new BadEntry("Invalid password");

		if (titre==null || titre.trim().length() < 1)
			throw new BadEntry("Invalid title");

		if (note < 0.0 || note > 5.0)
			throw new BadEntry("Invalid grade");

		if (commentaire == null)
			throw new BadEntry("Invalid comment");

		member = authentication(pseudo, password);
		if(member == null)
			throw new NotMember("Member does not exist");
		b = getBook(titre); 
		if(b == null)
			throw new NotItem("Book does not exist");
		
		/*
		 * Ajout de l'avis
		 */
		avis = member.getReview(b);
		if(avis == null){
			//Si le membre n'a pas déjà noté cet item
			avis = new Avis(note, commentaire, b, member);
			member.addReviewToCollection(avis);
			b.addReviewToCollection(avis);
		} else {
			//Si le membre a déjà noté cet item
			avis.update(note, commentaire);
		}
		
		return b.getMoyenne();
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
	
	/**
	 * Retourne le livre dont le titre est passé en paramettre
	 * @param titre
	 * 			Titre à rechercher
	 * @return
	 * 			Book avec le titre correspondant ou null s'il n'est pas trouvé
	 */
	private Book getBook(String titre){
		Book b = null;
		titre = titre.trim();
		int i=0;
		int nbItems = nbBooks() + nbFilms();
		
		while (i<nbItems){
			if(items.get(i) instanceof Book){
				if(items.get(i).exists(titre)){
					b = (Book)items.get(i);
				}
			}
			i++;
		}
		return b;
	}
	
	/**
	 * Retourne le film dont le titre est passé en paramettre
	 * @param titre
	 * 			Titre à rechercher
	 * @return
	 *			Film avec le titre correspondant ou null s'il n'est pas trouvé
	 */
	private Film getFilm(String titre){
		Film b = null;
		titre = titre.trim();
		int i=0;
		int nbItems = nbBooks() + nbFilms();
		
		while (i<nbItems){
			if(items.get(i) instanceof Film){
				if(items.get(i).exists(titre)){
					b = (Film)items.get(i);
				}
			}
			i++;
		}
		return b;
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
	
	private float reviewOpinionBook(String pseudo, String password, String pseudoMemberToReview, String titre, boolean opinion) throws NotItem, BadEntry, NotMember, NotReview {
		/*
		 * Variables
		 */
		Member member, memberToMark;
		Book b;
		Opinion o;
		Avis avis;

		/*
		 * Tests des paramettres d'entrés
		 */
		if (pseudo == null || pseudo.length() < 1 || pseudo.matches("\\p{Space}+?"))
			throw new BadEntry("Invalid username");
		
		if (password == null || password.trim().length() < 4)
			throw new BadEntry("Invalid password");

		if (titre==null || titre.trim().length() < 1)
			throw new BadEntry("Invalid title");
		
		member = authentication(pseudo, password);
		if (member == null)
			throw new NotMember("Member " + pseudo + " does not exist or the password is wrong");
		
		memberToMark = memberExist(pseudoMemberToReview);
		if (memberToMark == null)
			throw new NotMember("Member " + pseudo + " does not exist");
			
		b = getBook(titre);
		if (b == null) 
			throw new NotItem("Book " + titre + " does not exist");
		
		avis = memberToMark.getReview(b);
		if (avis == null)
			throw new NotReview();
		
		/*
		 * Ajout de l'opinion
		 */
		o = memberToMark.getOpinion(avis);
		if(o == null){
			o = new Opinion(opinion, avis);
			memberToMark.addOpinion(o);
		} else {
			o.updateOpinion(opinion);
		}
		
		/*
		 * Mise a jour du karma
		 */
		return memberToMark.updateKarma();
	}

	private float reviewOpinionFilm(String pseudo, String password, String memberToReview, String titre, boolean opinion) throws NotItem, BadEntry, NotMember{
		return 0;
	}
	
	public float reviewOpinion(String pseudo, String password, String memberToReview, String titre, itemsTypes itemType, boolean opinion) throws NotItem, BadEntry, NotMember, NotReview{
		switch (itemType) {
		case BOOK :
			return reviewOpinionBook(pseudo, password, memberToReview, titre, opinion);
		
		case FILM :
			return reviewOpinionFilm(pseudo, password, memberToReview, titre, opinion);
		
		default :
			throw new BadEntry("Error while marking member");
		}
	}
	
	private Member memberExist (String pseudo) {
		int i = 0;
		while (i < nbMembers()) {
			if(members.get(i).exists(pseudo))
				return members.get(i);
			i++;
		}
		
		return null;
	}

}
