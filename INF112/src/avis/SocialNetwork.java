package avis;

import java.util.LinkedList;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;
import exception.NotReview;

/**
 * @author Yannick LUCET
 * @author Tom VEILLARD
 */
public class SocialNetwork {

	public enum itemsTypes {
		BOOK, FILM
	}

	public enum inputsTypes {
		PASSWORD, PSEUDO, TITRE, GENRE, SCENARISTE, REALISATEUR, AUTEUR,
		PROFIL, DUREE, NBPAGES, COMMENTAIRE, NOTE
	}

	/**
	 * Liste contenant l'ensembe des membres du social network
	 */
	private LinkedList<Member> members;

	/**
	 * Liste contenant l'ensemble des items du social network
	 */
	private LinkedList<Book> books;

	/**
	 * Liste contenant l'ensemble des items du social network
	 */
	private LinkedList<Film> films;

	/**
	 * Constructeur SocialNetwork <br>
	 * A la construction du social network, la listes des membres et des items
	 * sont instanciées avec des listes vides.
	 */
	public SocialNetwork() {
		members = new LinkedList<Member>();
		books = new LinkedList<Book>();
		films = new LinkedList<Film>();
	}

	/**
	 * @return Nombre de membre du social network
	 */
	public int nbMembers() {
		return members.size();
	}

	/**
	 * @return Nombre de films du social network
	 */
	public int nbFilms() {
		return films.size();
	}

	/**
	 * @return Nombre de livres du social network
	 */
	public int nbBooks() {
		return books.size();
	}

	/**
	 * Ajoute un membre au social network<br>
	 * <br>
	 * Le pseudo doit contenir au moins 1 caractere autre que des espaces<br>
	 * Le mot de passe doit contenir au moins 4 caractere autre que les leading
	 * et trailling blanks<br>
	 * Le pseudo ne doit pas déjà exister
	 * 
	 * @param pseudo
	 *            Pseudo du membre à ajouter
	 * 
	 * @param password
	 *            Mot de passe du membre à ajouter
	 * 
	 * @param profil
	 *            Descriptif du membre à ajouter
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instancié ou a moins de 1
	 *             caractère autre que des espaces .</li>
	 *             <li>si le password n'est pas instancié ou a moins de 4
	 *             caractères autres que des leadings or trailing blanks.</li>
	 *             <li>si le profil n'est pas instancié.</li>
	 *             </ul>
	 * <br>
	 * 
	 * @throws MemberAlreadyExists
	 *             membre de même pseudo déjà  présent dans le
	 *             <i>SocialNetwork</i> (même pseudo : indifférent à  la casse
	 *             et aux leadings et trailings blanks)
	 * 
	 */
	public void addMember(String pseudo, String password, String profil)
			throws BadEntry, MemberAlreadyExists {
		/*
		 * Tests des paramettres d'entrés
		 */
		checkInput(pseudo, inputsTypes.PSEUDO);
		checkInput(password, inputsTypes.PASSWORD);
		checkInput(profil, inputsTypes.PROFIL);

		/*
		 * Tests de l'existence d'un membre avec ce pseudo
		 */
		if (memberExists(pseudo) != null)
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
	 * Le mot de passe doit contenir au moins 4 caractere autre que les leading
	 * et trailling blanks<br>
	 * Le titre doit contenir au moins 1 caractere autre que des espaces<br>
	 * La durée doit être positive
	 * 
	 * @param pseudo
	 *            Pseudo du membre qui ajoute le film
	 * 
	 * @param password
	 *            Mot de passe du membre qui ajoute le film
	 * 
	 * @param titre
	 *            Titre du film
	 * 
	 * @param genre
	 *            Genre du film
	 * 
	 * @param realisateur
	 *            Realisateur du film
	 * 
	 * @param scenariste
	 *            Scenariste du film
	 * 
	 * @param duree
	 *            Durée du film
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instancié ou a moins de 1
	 *             caractère autre que des espaces .</li>
	 *             <li>si le password n'est pas instancié ou a moins de 4
	 *             caractères autres que des leadings or trailing blanks.</li>
	 *             <li>si le titre n'est pas instancié ou a moins de 1 caractère
	 *             autre que des espaces.</li>
	 *             <li>si le genre n'est pas instancié.</li>
	 *             <li>si le réalisateur n'est pas instancié.</li>
	 *             <li>si le scénariste n'est pas instancié.</li>
	 *             <li>si la durée n'est pas positive.</li>
	 *             </ul>
	 * <br>
	 * @throws NotMember
	 *             : si le pseudo n'est pas celui d'un membre ou si le pseudo et
	 *             le password ne correspondent pas.
	 * @throws ItemFilmAlreadyExists
	 *             : item film de même titre déjà  présent (même titre :
	 *             indifférent à  la casse et aux leadings et trailings blanks)
	 * 
	 */
	public void addItemFilm(String pseudo, String password, String titre,
							String genre, String realisateur,
							String scenariste, int duree)
			throws BadEntry, NotMember, ItemFilmAlreadyExists {
		/*
		 * Tests des paramettres d'entrés
		 */
		checkInput(pseudo, inputsTypes.PSEUDO);
		checkInput(password, inputsTypes.PASSWORD);
		checkInput(titre, inputsTypes.TITRE);
		checkInput(genre, inputsTypes.GENRE);
		checkInput(realisateur, inputsTypes.REALISATEUR);
		checkInput(scenariste, inputsTypes.SCENARISTE);
		checkInput(duree, inputsTypes.DUREE);

		/*
		 * Authentification du membre
		 */
		if (authentication(pseudo, password) == null)
			throw new NotMember("Member does not exist");

		/*
		 * Test de l'existence d'un film du même titre
		 */
		if (getItem(titre, itemsTypes.FILM) != null)
			throw new ItemFilmAlreadyExists();

		/*
		 * Ajout du film
		 */
		films.add(getIndexFilm(titre, 0, nbFilms()), new Film(titre, genre,
			realisateur, scenariste, duree));

	}

	/**
	 * Ajoute un livre au social network<br>
	 * <br>
	 * Le pseudo doit contenir au moins 1 caractere autre que des espaces<br>
	 * Le mot de passe doit contenir au moins 4 caractere autre que les leading
	 * et trailling blanks<br>
	 * Le titre doit contenir au moins 1 caractere autre que des espaces<br>
	 * La durée doit être positive
	 * 
	 * @param pseudo
	 *            Pseudo du membre qui ajoute le livre
	 * 
	 * @param password
	 *            Mot de passe du membre qui ajoute le livre
	 * 
	 * @param titre
	 *            Titre du livre
	 * 
	 * @param genre
	 *            Genre du livre
	 * 
	 * @param auteur
	 *            Realisateur du livre
	 * 
	 * @param nbPages
	 *            Scenariste du livre
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instancié ou a moins de 1
	 *             caractère autre que des espaces .</li>
	 *             <li>si le password n'est pas instancié ou a moins de 4
	 *             caractères autres que des leadings or trailing blanks.</li>
	 *             <li>si le titre n'est pas instancié ou a moins de 1 caractère
	 *             autre que des espaces.</li>
	 *             <li>si le genre n'est pas instancié.</li>
	 *             <li>si l'auteur n'est pas instancié.</li>
	 *             <li>si le nombre de pages n'est pas positif.</li>
	 *             </ul>
	 * <br>
	 * @throws NotMember
	 *             : si le pseudo n'est pas celui d'un membre ou si le pseudo et
	 *             le password ne correspondent pas.
	 * @throws ItemBookAlreadyExists
	 *             item livre de même titre déjà  présent (même titre :
	 *             indifférent à  la casse et aux leadings et trailings blanks)
	 */
	public void addItemBook(String pseudo, String password, String titre,
							String genre, String auteur, int nbPages)
			throws BadEntry, NotMember, ItemBookAlreadyExists {
		/*
		 * Tests des paramettres d'entrés
		 */
		checkInput(pseudo, inputsTypes.PSEUDO);
		checkInput(password, inputsTypes.PASSWORD);
		checkInput(titre, inputsTypes.TITRE);
		checkInput(genre, inputsTypes.GENRE);
		checkInput(auteur, inputsTypes.AUTEUR);
		checkInput(nbPages, inputsTypes.NBPAGES);

		/*
		 * Authentification du membre
		 */
		if (authentication(pseudo, password) == null)
			throw new NotMember("Member does not exist");

		/*
		 * Test de l'existence d'un livre du même nom
		 */
		if (getItem(titre, itemsTypes.BOOK) != null)
			throw new ItemBookAlreadyExists();

		/*
		 * Ajout du livre
		 */
		books.add(getIndexBook(titre, 0, nbBooks()), new Book(titre, genre,
			auteur, nbPages));

	}

	/**
	 * Retourne le toString des items correspondant à la recherche<br>
	 * <br>
	 * Méthode non sensible à la casse, le nom doit contenir plus d'un caractere
	 * autre que des espaces.
	 * 
	 * @param nom
	 *            Nom de l'item recherché
	 * 
	 * @return Liste des items trouvé sous forme verbeuse
	 * 
	 * @throws BadEntry
	 *             : si le nom n'est pas instancié ou a moins de 1 caractère
	 *             autre que des espaces.
	 */
	public LinkedList<String> consultItems(String nom) throws BadEntry {
		/*
		 * Test du paramettre d'entré
		 */
		checkInput(nom, inputsTypes.TITRE);

		/*
		 * Recherche des Items
		 */
		LinkedList<String> returnList = new LinkedList<String>();
		// Dans le social network, il n'y a qu'un seul livre qui peut être
		// retourné
		if (getItem(nom, itemsTypes.BOOK) != null)
			returnList.add(getItem(nom, itemsTypes.BOOK).toString());

		// Dans le social network, il n'y a qu'un seul film qui peut être
		// retourné
		if (getItem(nom, itemsTypes.FILM) != null)
			returnList.add(getItem(nom, itemsTypes.FILM).toString());

		return returnList;
	}

	/**
	 * Dépose un avis sur un film
	 * 
	 * @param pseudo
	 *            Pseudo du membre qui dépose l'avis
	 * 
	 * @param password
	 *            Password du membre qui dépose l'avis
	 * 
	 * @param titre
	 *            Titre du film
	 * 
	 * @param note
	 *            Note attribuée par le membre au film
	 * 
	 * @param commentaire
	 *            Commentaire du membre sur le film
	 * 
	 * @return Moyenne des notes déposées sur ce film
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instancié ou a moins de 1
	 *             caractère autre que des espaces .</li>
	 *             <li>si le password n'est pas instancié ou a moins de 4
	 *             caractères autres que des leadings or trailing blanks.</li>
	 *             <li>si le titre n'est pas instancié ou a moins de 1 caractère
	 *             autre que des espaces.</li>
	 *             <li>si la note n'est pas comprise entre 0.0 et 5.0.</li>
	 *             <li>si le commentaire n'est pas instancié.</li>
	 *             </ul>
	 * <br>
	 * @throws NotMember
	 *             : si le pseudo n'est pas celui d'un membre ou si le pseudo et
	 *             le password ne correspondent pas.
	 * @throws NotItem
	 *             : si le titre n'est pas le titre d'un film.
	 * 
	 * @return la note moyenne des notes sur ce film
	 */
	public float reviewItemFilm(String pseudo, String password, String titre,
								float note, String commentaire)
			throws BadEntry, NotMember, NotItem {
		/*
		 * Variables
		 */
		Member member;
		Film film;
		Avis avis;

		/*
		 * Tests des paramettres d'entrés
		 */
		checkInput(pseudo, inputsTypes.PSEUDO);
		checkInput(password, inputsTypes.PASSWORD);
		checkInput(titre, inputsTypes.TITRE);
		checkInput(note, inputsTypes.NOTE);
		checkInput(commentaire, inputsTypes.COMMENTAIRE);

		/*
		 * Authentification du membre
		 */
		member = authentication(pseudo, password);
		if (member == null)
			throw new NotMember("Member does not exist");

		/*
		 * Récupération du film
		 */
		film = (Film) getItem(titre, itemsTypes.FILM);
		if (film == null)
			throw new NotItem("Film does not exist");

		/*
		 * Ajout de l'avis
		 */
		avis = member.getReview(film);
		if (avis == null) {
			// Si le membre n'a pas déjà noté cet item, on crée un avis qui est
			// ajouté au membre et a l'item
			avis = new Avis(note, commentaire, film, member);
			member.addReviewToCollection(avis);
			film.addReviewToCollection(avis);
		} else {
			// Si le membre a déjà noté l'item, on met à jour ça note.
			avis.update(note, commentaire);
		}

		return film.getMoyenne();
	}

	/**
	 * Dépose un avis sur un livre
	 * 
	 * @param pseudo
	 *            Pseudo du membre qui dépose l'avis
	 * 
	 * @param password
	 *            Password du membre qui dépose l'avis
	 * 
	 * @param titre
	 *            Titre du livre
	 * 
	 * @param note
	 *            Note attribuée par le membre au livre
	 * 
	 * @param commentaire
	 *            Commentaire du membre sur le livre
	 * 
	 * @return Moyenne des notes déposées sur ce livre
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instancié ou a moins de 1
	 *             caractère autre que des espaces .</li>
	 *             <li>si le password n'est pas instancié ou a moins de 4
	 *             caractères autres que des leadings or trailing blanks.</li>
	 *             <li>si le titre n'est pas instancié ou a moins de 1 caractère
	 *             autre que des espaces.</li>
	 *             <li>si la note n'est pas comprise entre 0.0 et 5.0.</li>
	 *             <li>si le commentaire n'est pas instancié.</li>
	 *             </ul>
	 * <br>
	 * @throws NotMember
	 *             : si le pseudo n'est pas celui d'un membre ou si le pseudo et
	 *             le password ne correspondent pas.
	 * @throws NotItem
	 *             : si le titre n'est pas le titre d'un livre.
	 */
	public float reviewItemBook(String pseudo, String password, String titre,
								float note, String commentaire)
			throws BadEntry, NotMember, NotItem {
		/*
		 * Variables
		 */
		Member member;
		Book book;
		Avis avis;

		/*
		 * Tests des paramettres d'entrés
		 */
		checkInput(pseudo, inputsTypes.PSEUDO);
		checkInput(password, inputsTypes.PASSWORD);
		checkInput(titre, inputsTypes.TITRE);
		checkInput(note, inputsTypes.NOTE);
		checkInput(commentaire, inputsTypes.COMMENTAIRE);

		/*
		 * Authentification du membre
		 */
		member = authentication(pseudo, password);
		if (member == null)
			throw new NotMember("Member does not exist");

		/*
		 * Récupération du film
		 */
		book = (Book) getItem(titre, itemsTypes.BOOK);
		if (book == null)
			throw new NotItem("Book does not exist");

		/*
		 * Ajout de l'avis
		 */
		avis = member.getReview(book);
		if (avis == null) {
			// Si le membre n'a pas déjà noté cet item, on crée un avis qui est
			// ajouté au membre et a l'item
			avis = new Avis(note, commentaire, book, member);
			member.addReviewToCollection(avis);
			book.addReviewToCollection(avis);
		} else {
			// Si le membre a déjà noté cet item, on met à jour la note
			avis.update(note, commentaire);
		}

		return book.getMoyenne();
	}

	/**
	 * Authentifie un membre
	 * 
	 * @param pseudo
	 *            Pseudo du membre qui dépose l'avis
	 * 
	 * @param password
	 *            Password du membre qui dépose l'avis
	 * 
	 * @return Le membre désigné par le couple pseudo/password <br>
	 *         Null si le pseudo ou le password sont invalides
	 */
	private Member authentication(String pseudo, String password) {
		boolean memberIsFound = false;
		int i = 0;

		// Tant que le membre n'est pas trouvé et que la liste n'à pas été
		// entierement parcourue
		while (!memberIsFound && i < nbMembers()) {
			memberIsFound = members.get(i).exists(pseudo, password);
			if (memberIsFound)
				return members.get(i);

			i++;
		}

		return null;
	}

	/**
	 * Retourne l'item du type correspondant à celui passé en paramettre
	 * 
	 * @param titre
	 *            Titre à rechercher
	 * @param itemType
	 *            Type de l'item à retourner
	 * @return Book avec le titre correspondant ou null s'il n'est pas trouvé
	 * @throws BadEntry
	 * 			Erreur si le type nest ni un livre ni un film
	 */
	private Item getItem(String titre, itemsTypes itemType) throws BadEntry {
		int index;

		/*
		 * Choix selon le type d'item
		 */
		switch (itemType) {
		case FILM:
			index = getIndexFilm(titre, 0, nbFilms());
			// Si l'item recherché n'a pas été trouvé, on retourne null
			if (index == nbFilms() || films.get(index).exists(titre) != 0)
				return null;

			return films.get(index);

		case BOOK:
			index = getIndexBook(titre, 0, nbBooks());
			// Si l'item recherché n'a pas été trouvé, on retourne null
			if (index == nbBooks() || books.get(index).exists(titre) != 0)
				return null;

			return books.get(index);

		default:
			throw new BadEntry("Error while getting Item");
		}
	}

	/**
	 * Dépose une opinion sur l'avis laissé par un membre sur un item
	 * 
	 * @param pseudo
	 *            Pseudo du Membre qui donne une Opinion
	 * @param password
	 *            Mot de passe du Membre qui donne une Opinion
	 * @param pseudoMemberToReview
	 *            Pseudo du membre à noter
	 * @param titre
	 *            Titre de l'item sur lequel l'avis à noter à été laissé
	 * @param itemType
	 *            Type de l'item concerné par l'avis
	 * @param opinionMark
	 *            Opinion à laisser par le membre sur l'avis
	 * 
	 * @return Le nouveau karma du membre
	 * 
	 * @throws NotItem
	 *             : si le titre ne correspond pas a un item du type spécifié
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instancié ou a moins de 1
	 *             caractère autre que des espaces .</li>
	 *             <li>si le password n'est pas instancié ou a moins de 4
	 *             caractères autres que des leadings or trailing blanks.</li>
	 *             <li>si le pseudo du membre à noter n'est pas instancié ou a
	 *             moins de 1 caractère autre que des espaces .</li>
	 *             <li>si le titre n'est pas instancié ou a moins de 1 caractère
	 *             autre que des espaces.</li>
	 *             </ul>
	 * <br>
	 * @throws NotMember
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas celui d'un membre ou si le pseudo
	 *             et le password ne correspondent pas
	 *             <li>si le pseudo du membre à noter n'existe pas
	 *             </ul>
	 * <br>
	 * @throws NotReview
	 *             : si l'avis n'a pas été noté par le membre
	 */
	public float reviewOpinion(String pseudo, String password,
								String pseudoMemberToReview, String titre,
								itemsTypes itemType, boolean opinionMark)
			throws NotItem, BadEntry, NotMember, NotReview {
		/*
		 * Variables
		 */
		Member member, memberToMark;
		Item item;
		Opinion opinion;
		Avis avis;

		/*
		 * Tests des paramettres d'entrés
		 */
		checkInput(pseudo, inputsTypes.PSEUDO);
		checkInput(password, inputsTypes.PASSWORD);
		checkInput(titre, inputsTypes.TITRE);
		checkInput(pseudoMemberToReview, inputsTypes.PSEUDO);

		/*
		 * Authentification et récupération du membre
		 */
		member = authentication(pseudo, password);
		if (member == null)
			throw new NotMember("Member " + pseudo
					+ " does not exist or the password is wrong");

		/*
		 * Vérification de l'existance du membre à noter
		 */
		memberToMark = memberExists(pseudoMemberToReview);
		if (memberToMark == null)
			throw new NotMember("Member " + pseudoMemberToReview
					+ " does not exist");

		/*
		 * Récupération de l'item
		 */
		item = getItem(titre, itemType);
		if (item == null)
			throw new NotItem("Item " + titre + " does not exist");

		/*
		 * Récupération de l'avis
		 */
		avis = memberToMark.getReview(item);
		if (avis == null)
			throw new NotReview();

		/*
		 * Ajout de l'opinion
		 */
		opinion = memberToMark.getOpinion(avis);
		if (opinion == null) {
			// Si l'opinion n'existe pas, on la crée et on l'ajoute au membre
			// noté.
			opinion = new Opinion(opinionMark, avis);
			memberToMark.addOpinion(opinion);
		} else {
			// Si l'opinion existe, on la met à jour
			opinion.updateOpinion(opinionMark);
		}

		/*
		 * Mise a jour du karma
		 */
		return memberToMark.updateKarma();

	}

	/**
	 * Renvoie le Membre associé au pseudo passé en parametre
	 * 
	 * @param pseudo
	 *            Pseudo du membre à rechercher
	 * @return Le membre associé au pseudo passé en parametre Null si le membre
	 *         n'existe pas
	 */
	private Member memberExists(String pseudo) {
		int i = 0;

		// Tant que le membre n'est pas trouvé et que la liste n'à pas été
		// entierement parcourue
		while (i < nbMembers()) {
			if (members.get(i).exists(pseudo))
				return members.get(i);
			i++;
		}

		return null;
	}

	public String toString() {
		String retour;

		retour = "Social Network : \n" + "Les membres: \n";
		for (int i = 0; i < nbMembers(); i++) {
			retour += members.get(i).toString();
		}
		retour += "\n";
		retour += "Les films : \n";
		for (int i = 0; i < films.size(); i++) {
			retour += films.get(i).toString();
		}
		retour += "\n";
		retour += "Les livres : \n";
		for (int i = 0; i < books.size(); i++) {
			retour += books.get(i).toString();
		}
		return retour;
	}

	/**
	 * Vérifie si l'entrée correspond aux critères. Dans le cas contraire,
	 * l'exception BadEntry est levée
	 * 
	 * @param input
	 *            Objet à analyser (de type int, float ou String)
	 * @param inputType
	 *            Type d'entrée à traiter
	 * @throws BadEntry
	 * 			 Mauvaise entrée
	 */
	private void checkInput(Object input, inputsTypes inputType)
			throws BadEntry {
		/*
		 * Variables
		 */
		// Utilisé si l'objet input est un Integer
		int integerInput;
		// Utilisé si l'objet input est un Float
		float floatInput;
		// Utilisé si l'objet input est un String
		String stringInput;
		// Message d'erreur à mettre dans l'exception
		String errorMessage = "Invalid " + inputType.toString().toLowerCase();

		/*
		 * Test de la instanciation de l'objet input
		 */
		if (input == null)
			throw new BadEntry(errorMessage);

		/*
		 * Tests si input est un String (cas le plus fréquent)
		 */
		if (input instanceof String) {
			stringInput = (String) input;
			switch (inputType) {
			case PSEUDO:
				// Si le String est composé uniquement d'espaces
				if (stringInput.matches("\\p{Space}+?"))
					throw new BadEntry(errorMessage);
			case TITRE:
				// Si le String fait moins de 1 caractère (Hors espace au début
				// et à la fin)
				if (stringInput.trim().length() < 1)
					throw new BadEntry(errorMessage);
				break;

			case PASSWORD:
				// Si le String fait moins de 4 caractère (Hors espace au début
				// et à la fin)
				if (stringInput.trim().length() < 4)
					throw new BadEntry(errorMessage);
				break;

			case GENRE:
			case AUTEUR:
			case PROFIL:
			case REALISATEUR:
			case SCENARISTE:
			case COMMENTAIRE:
				break;
			default:
				throw new BadEntry(errorMessage);
			}
		}

		/*
		 * Tests si input est un Integer
		 */
		else if (input instanceof Integer) {
			integerInput = ((Integer) input).intValue();
			switch (inputType) {
			case DUREE:
			case NBPAGES:
				// Si l'Integer est inferieur à 1
				if (integerInput < 1)
					throw new BadEntry(errorMessage);
				break;

			default:
				throw new BadEntry(errorMessage);
			}
		}

		/*
		 * Tests si input est un Float
		 */
		else if (input instanceof Float) {
			floatInput = ((Float) input).floatValue();
			switch (inputType) {
			case NOTE:
				// Si le Float n'est pas compris dans l'intervale [0;5]
				if (floatInput < 0 || floatInput > 5)
					throw new BadEntry(errorMessage);

				break;
			default:
				throw new BadEntry(errorMessage);
			}

		}

		/*
		 * Si l'objet n'est pas un Integer, Float ou String
		 */
		else {
			throw new BadEntry(errorMessage);
		}
	}

	/**
	 * Obtient l'index d'un film dont le titre est passé en parametre. Dans le
	 * cas ou le film n'existe pas, c'est l'index ou doit etre posisionné ce
	 * film qui est renvoyé.
	 * 
	 * @param titre
	 *            Titre à rechercher
	 * @param start
	 *            Index de début de la recherche
	 * @param end
	 *            Index de fin de la recherche
	 * @return Index du film recherché ou index où le positionner
	 */
	private int getIndexFilm(String titre, int start, int end) {
		int middle = (start + end) / 2;
		// Si la zone de recherche est superieure à une case, on poursuit la
		// recherche
		if (end > start) {
			// Si la case annalysée correspond au film recherché, on retourne
			// l'index de celui ci
			if (films.get(middle).exists(titre) == 0)
				return middle;

			// Si non on recherche
			if (films.get(middle).exists(titre) < 0) {
				// Dans la partie gauche
				return getIndexFilm(titre, start, middle);
			} else {
				// Dans la partie droite
				return getIndexFilm(titre, middle + 1, end);
			}
		}
		// Si non, on retourne null
		else {
			return start;
		}
	}

	/**
	 * Obtient l'index d'un livre dont le titre est passé en parametre. Dans le
	 * cas ou le livre n'existe pas, c'est l'index ou doit etre posisionné ce
	 * livre qui est renvoyé.
	 * 
	 * @param titre
	 *            Titre à rechercher
	 * @param start
	 *            Index de début de la recherche
	 * @param end
	 *            Index de fin de la recherche
	 * @return Index du livre recherché ou index où le positionner
	 */
	private int getIndexBook(String titre, int start, int end) {
		int middle = (start + end) / 2;

		// Si la zone de recherche est superieure à une case, on poursuit la
		// recherche
		if (end > start) {
			// Si la case annalysée correspond au film recherché, on retourne
			// l'index de celui ci
			if (books.get(middle).exists(titre) == 0)
				return middle;

			// Si non on recherche
			if (books.get(middle).exists(titre) < 0) {
				// Dans la partie gauche
				return getIndexBook(titre, start, middle);
			} else {
				// Dans la partie droite
				return getIndexBook(titre, middle + 1, end);
			}
		} 
		// Si non, on retourne null
		else {
			return start;
		}
	}

}
