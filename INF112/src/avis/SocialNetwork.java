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

		if (authentication(pseudo, password) == null)
			throw new NotMember("Member does not exist");

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

		if (authentication(pseudo, password) == null)
			throw new NotMember("Member does not exist");

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
		if (getItem(nom, itemsTypes.BOOK) != null)
			returnList.add(getItem(nom, itemsTypes.BOOK).toString());

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
		Film f;
		Avis avis;

		/*
		 * Tests des paramettres d'entrés
		 */
		checkInput(pseudo, inputsTypes.PSEUDO);
		checkInput(password, inputsTypes.PASSWORD);
		checkInput(titre, inputsTypes.TITRE);
		checkInput(note, inputsTypes.NOTE);
		checkInput(commentaire, inputsTypes.COMMENTAIRE);

		member = authentication(pseudo, password);
		if (member == null)
			throw new NotMember("Member does not exist");

		f = (Film) getItem(titre, itemsTypes.FILM);
		if (f == null)
			throw new NotItem("Film does not exist");

		/*
		 * Ajout de l'avis
		 */
		avis = member.getReview(f);
		if (avis == null) {
			// Si le membre n'a pas déjà noté cet item
			avis = new Avis(note, commentaire, f, member);
			member.addReviewToCollection(avis);
			f.addReviewToCollection(avis);
		} else {
			// Si le membre a déjà noté l'item
			avis.update(note, commentaire);
		}

		return f.getMoyenne();
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
		Book b;
		Avis avis;

		/*
		 * Tests des paramettres d'entrés
		 */
		checkInput(pseudo, inputsTypes.PSEUDO);
		checkInput(password, inputsTypes.PASSWORD);
		checkInput(titre, inputsTypes.TITRE);
		checkInput(note, inputsTypes.NOTE);
		checkInput(commentaire, inputsTypes.COMMENTAIRE);

		member = authentication(pseudo, password);
		if (member == null)
			throw new NotMember("Member does not exist");
		b = (Book) getItem(titre, itemsTypes.BOOK);
		if (b == null)
			throw new NotItem("Book does not exist");

		/*
		 * Ajout de l'avis
		 */
		avis = member.getReview(b);
		if (avis == null) {
			// Si le membre n'a pas déjà noté cet item
			avis = new Avis(note, commentaire, b, member);
			member.addReviewToCollection(avis);
			b.addReviewToCollection(avis);
		} else {
			// Si le membre a déjà noté cet item
			avis.update(note, commentaire);
		}

		return b.getMoyenne();
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
		Member m = null;
		int i = 0;

		while (!memberIsFound && i < nbMembers()) {
			memberIsFound = members.get(i).exists(pseudo, password);
			if (memberIsFound)
				m = members.get(i);

			i++;
		}

		return m;
	}

	/**
	 * Retourne l'item du type correspondant à celui passé en paramettre
	 * 
	 * @param titre
	 *            Titre à rechercher
	 * @param itemType
	 *            Type de l'item à retourner
	 * @return Book avec le titre correspondant ou null s'il n'est pas trouvé
	 */
	private Item getItem(String titre, itemsTypes itemType) throws BadEntry {
		int index;
		switch (itemType) {
		case FILM:
			index = getIndexFilm(titre, 0, nbFilms());
			if (index == -1 || index == nbFilms() || films.get(index).exists(titre) != 0 )
				return null;
			
			return films.get(index);
			

		case BOOK:
			index = getIndexBook(titre, 0, nbBooks());
			if (index == -1 || index == nbBooks() || books.get(index).exists(titre) != 0)
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
	 * @param opinion
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
								itemsTypes itemType, boolean opinion)
			throws NotItem, BadEntry, NotMember, NotReview {
		/*
		 * Variables
		 */
		Member member, memberToMark;
		Item i;
		Opinion o;
		Avis avis;

		/*
		 * Tests des paramettres d'entrés
		 */
		checkInput(pseudo, inputsTypes.PSEUDO);
		checkInput(password, inputsTypes.PASSWORD);
		checkInput(titre, inputsTypes.TITRE);
		checkInput(pseudoMemberToReview, inputsTypes.PSEUDO);

		member = authentication(pseudo, password);
		if (member == null)
			throw new NotMember("Member " + pseudo
					+ " does not exist or the password is wrong");

		memberToMark = memberExists(pseudoMemberToReview);
		if (memberToMark == null)
			throw new NotMember("Member " + pseudoMemberToReview
					+ " does not exist");

		i = getItem(titre, itemType);

		if (i == null)
			throw new NotItem("Book " + titre + " does not exist");

		avis = memberToMark.getReview(i);
		if (avis == null)
			throw new NotReview();

		/*
		 * Ajout de l'opinion
		 */
		o = memberToMark.getOpinion(avis);
		if (o == null) {
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

	private void checkInput(Object input, inputsTypes inputType)
			throws BadEntry {
		int integerInput;
		float floatInput;
		String stringInput;
		String errorMessage = "Invalid " + inputType.toString().toLowerCase();

		if (input == null)
			throw new BadEntry(errorMessage);

		if (input instanceof String) {
			stringInput = (String) input;
			switch (inputType) {
			case PSEUDO:
				if (stringInput.matches("\\p{Space}+?"))
					throw new BadEntry(errorMessage);
			case TITRE:
				if (stringInput.trim().length() < 1)
					throw new BadEntry(errorMessage);
				break;

			case PASSWORD:
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
		} else if (input instanceof Integer) {
			integerInput = ((Integer) input).intValue();
			switch (inputType) {
			case DUREE:
			case NBPAGES:
				if (integerInput < 1)
					throw new BadEntry(errorMessage);
				break;

			default:
				throw new BadEntry(errorMessage);
			}
		} else if (input instanceof Float) {
			floatInput = ((Float) input).floatValue();
			switch (inputType) {
			case NOTE:
				if (floatInput < 0 || floatInput > 5)
					throw new BadEntry(errorMessage);

				break;
			default:
				throw new BadEntry(errorMessage);
			}

		} else {
			throw new BadEntry(errorMessage);
		}
	}

	private int getIndexFilm(String titre, int start, int end) {
		int milieu = (start + end) / 2;
		if (end > start) {
			if (films.get(milieu).exists(titre) == 0)
				return milieu;

			if (films.get(milieu).exists(titre) < 0) {
				return getIndexFilm(titre, start, milieu);
			} else {
				return getIndexFilm(titre, milieu + 1, end);
			}
		} else {
			return start;
		}
	}

	private int getIndexBook(String titre, int start, int end) {
		int milieu = (start + end) / 2;
		if (end > start) {
			if (films.get(milieu).exists(titre) == 0)
				return milieu;

			if (films.get(milieu).exists(titre) < 0) {
				return getIndexBook(titre, start, milieu);
			} else {
				return getIndexBook(titre, milieu + 1, end);
			}
		} else {
			return start;
		}
	}


}
