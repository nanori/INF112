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
	 * sont instanci�es avec des listes vides.
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
	 * Le pseudo ne doit pas d�j� exister
	 * 
	 * @param pseudo
	 *            Pseudo du membre � ajouter
	 * 
	 * @param password
	 *            Mot de passe du membre � ajouter
	 * 
	 * @param profil
	 *            Descriptif du membre � ajouter
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instanci� ou a moins de 1
	 *             caract�re autre que des espaces .</li>
	 *             <li>si le password n'est pas instanci� ou a moins de 4
	 *             caract�res autres que des leadings or trailing blanks.</li>
	 *             <li>si le profil n'est pas instanci�.</li>
	 *             </ul>
	 * <br>
	 * 
	 * @throws MemberAlreadyExists
	 *             membre de m�me pseudo d�j� pr�sent dans le
	 *             <i>SocialNetwork</i> (m�me pseudo : indiff�rent � la casse
	 *             et aux leadings et trailings blanks)
	 * 
	 */
	public void addMember(String pseudo, String password, String profil)
			throws BadEntry, MemberAlreadyExists {
		/*
		 * Tests des paramettres d'entr�s
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
	 * La dur�e doit �tre positive
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
	 *            Dur�e du film
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instanci� ou a moins de 1
	 *             caract�re autre que des espaces .</li>
	 *             <li>si le password n'est pas instanci� ou a moins de 4
	 *             caract�res autres que des leadings or trailing blanks.</li>
	 *             <li>si le titre n'est pas instanci� ou a moins de 1 caract�re
	 *             autre que des espaces.</li>
	 *             <li>si le genre n'est pas instanci�.</li>
	 *             <li>si le r�alisateur n'est pas instanci�.</li>
	 *             <li>si le sc�nariste n'est pas instanci�.</li>
	 *             <li>si la dur�e n'est pas positive.</li>
	 *             </ul>
	 * <br>
	 * @throws NotMember
	 *             : si le pseudo n'est pas celui d'un membre ou si le pseudo et
	 *             le password ne correspondent pas.
	 * @throws ItemFilmAlreadyExists
	 *             : item film de m�me titre d�j� pr�sent (m�me titre :
	 *             indiff�rent � la casse et aux leadings et trailings blanks)
	 * 
	 */
	public void addItemFilm(String pseudo, String password, String titre,
							String genre, String realisateur,
							String scenariste, int duree)
			throws BadEntry, NotMember, ItemFilmAlreadyExists {
		/*
		 * Tests des paramettres d'entr�s
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
		 * Test de l'existence d'un film du m�me titre
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
	 * La dur�e doit �tre positive
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
	 *             <li>si le pseudo n'est pas instanci� ou a moins de 1
	 *             caract�re autre que des espaces .</li>
	 *             <li>si le password n'est pas instanci� ou a moins de 4
	 *             caract�res autres que des leadings or trailing blanks.</li>
	 *             <li>si le titre n'est pas instanci� ou a moins de 1 caract�re
	 *             autre que des espaces.</li>
	 *             <li>si le genre n'est pas instanci�.</li>
	 *             <li>si l'auteur n'est pas instanci�.</li>
	 *             <li>si le nombre de pages n'est pas positif.</li>
	 *             </ul>
	 * <br>
	 * @throws NotMember
	 *             : si le pseudo n'est pas celui d'un membre ou si le pseudo et
	 *             le password ne correspondent pas.
	 * @throws ItemBookAlreadyExists
	 *             item livre de m�me titre d�j� pr�sent (m�me titre :
	 *             indiff�rent � la casse et aux leadings et trailings blanks)
	 */
	public void addItemBook(String pseudo, String password, String titre,
							String genre, String auteur, int nbPages)
			throws BadEntry, NotMember, ItemBookAlreadyExists {
		/*
		 * Tests des paramettres d'entr�s
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
		 * Test de l'existence d'un livre du m�me nom
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
	 * Retourne le toString des items correspondant � la recherche<br>
	 * <br>
	 * M�thode non sensible � la casse, le nom doit contenir plus d'un caractere
	 * autre que des espaces.
	 * 
	 * @param nom
	 *            Nom de l'item recherch�
	 * 
	 * @return Liste des items trouv� sous forme verbeuse
	 * 
	 * @throws BadEntry
	 *             : si le nom n'est pas instanci� ou a moins de 1 caract�re
	 *             autre que des espaces.
	 */
	public LinkedList<String> consultItems(String nom) throws BadEntry {
		/*
		 * Test du paramettre d'entr�
		 */
		checkInput(nom, inputsTypes.TITRE);

		/*
		 * Recherche des Items
		 */
		LinkedList<String> returnList = new LinkedList<String>();
		// Dans le social network, il n'y a qu'un seul livre qui peut �tre
		// retourn�
		if (getItem(nom, itemsTypes.BOOK) != null)
			returnList.add(getItem(nom, itemsTypes.BOOK).toString());

		// Dans le social network, il n'y a qu'un seul film qui peut �tre
		// retourn�
		if (getItem(nom, itemsTypes.FILM) != null)
			returnList.add(getItem(nom, itemsTypes.FILM).toString());

		return returnList;
	}

	/**
	 * D�pose un avis sur un film
	 * 
	 * @param pseudo
	 *            Pseudo du membre qui d�pose l'avis
	 * 
	 * @param password
	 *            Password du membre qui d�pose l'avis
	 * 
	 * @param titre
	 *            Titre du film
	 * 
	 * @param note
	 *            Note attribu�e par le membre au film
	 * 
	 * @param commentaire
	 *            Commentaire du membre sur le film
	 * 
	 * @return Moyenne des notes d�pos�es sur ce film
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instanci� ou a moins de 1
	 *             caract�re autre que des espaces .</li>
	 *             <li>si le password n'est pas instanci� ou a moins de 4
	 *             caract�res autres que des leadings or trailing blanks.</li>
	 *             <li>si le titre n'est pas instanci� ou a moins de 1 caract�re
	 *             autre que des espaces.</li>
	 *             <li>si la note n'est pas comprise entre 0.0 et 5.0.</li>
	 *             <li>si le commentaire n'est pas instanci�.</li>
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
		 * Tests des paramettres d'entr�s
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
		 * R�cup�ration du film
		 */
		film = (Film) getItem(titre, itemsTypes.FILM);
		if (film == null)
			throw new NotItem("Film does not exist");

		/*
		 * Ajout de l'avis
		 */
		avis = member.getReview(film);
		if (avis == null) {
			// Si le membre n'a pas d�j� not� cet item, on cr�e un avis qui est
			// ajout� au membre et a l'item
			avis = new Avis(note, commentaire, film, member);
			member.addReviewToCollection(avis);
			film.addReviewToCollection(avis);
		} else {
			// Si le membre a d�j� not� l'item, on met � jour �a note.
			avis.update(note, commentaire);
		}

		return film.getMoyenne();
	}

	/**
	 * D�pose un avis sur un livre
	 * 
	 * @param pseudo
	 *            Pseudo du membre qui d�pose l'avis
	 * 
	 * @param password
	 *            Password du membre qui d�pose l'avis
	 * 
	 * @param titre
	 *            Titre du livre
	 * 
	 * @param note
	 *            Note attribu�e par le membre au livre
	 * 
	 * @param commentaire
	 *            Commentaire du membre sur le livre
	 * 
	 * @return Moyenne des notes d�pos�es sur ce livre
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instanci� ou a moins de 1
	 *             caract�re autre que des espaces .</li>
	 *             <li>si le password n'est pas instanci� ou a moins de 4
	 *             caract�res autres que des leadings or trailing blanks.</li>
	 *             <li>si le titre n'est pas instanci� ou a moins de 1 caract�re
	 *             autre que des espaces.</li>
	 *             <li>si la note n'est pas comprise entre 0.0 et 5.0.</li>
	 *             <li>si le commentaire n'est pas instanci�.</li>
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
		 * Tests des paramettres d'entr�s
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
		 * R�cup�ration du film
		 */
		book = (Book) getItem(titre, itemsTypes.BOOK);
		if (book == null)
			throw new NotItem("Book does not exist");

		/*
		 * Ajout de l'avis
		 */
		avis = member.getReview(book);
		if (avis == null) {
			// Si le membre n'a pas d�j� not� cet item, on cr�e un avis qui est
			// ajout� au membre et a l'item
			avis = new Avis(note, commentaire, book, member);
			member.addReviewToCollection(avis);
			book.addReviewToCollection(avis);
		} else {
			// Si le membre a d�j� not� cet item, on met � jour la note
			avis.update(note, commentaire);
		}

		return book.getMoyenne();
	}

	/**
	 * Authentifie un membre
	 * 
	 * @param pseudo
	 *            Pseudo du membre qui d�pose l'avis
	 * 
	 * @param password
	 *            Password du membre qui d�pose l'avis
	 * 
	 * @return Le membre d�sign� par le couple pseudo/password <br>
	 *         Null si le pseudo ou le password sont invalides
	 */
	private Member authentication(String pseudo, String password) {
		boolean memberIsFound = false;
		int i = 0;

		// Tant que le membre n'est pas trouv� et que la liste n'� pas �t�
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
	 * Retourne l'item du type correspondant � celui pass� en paramettre
	 * 
	 * @param titre
	 *            Titre � rechercher
	 * @param itemType
	 *            Type de l'item � retourner
	 * @return Book avec le titre correspondant ou null s'il n'est pas trouv�
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
			// Si l'item recherch� n'a pas �t� trouv�, on retourne null
			if (index == nbFilms() || films.get(index).exists(titre) != 0)
				return null;

			return films.get(index);

		case BOOK:
			index = getIndexBook(titre, 0, nbBooks());
			// Si l'item recherch� n'a pas �t� trouv�, on retourne null
			if (index == nbBooks() || books.get(index).exists(titre) != 0)
				return null;

			return books.get(index);

		default:
			throw new BadEntry("Error while getting Item");
		}
	}

	/**
	 * D�pose une opinion sur l'avis laiss� par un membre sur un item
	 * 
	 * @param pseudo
	 *            Pseudo du Membre qui donne une Opinion
	 * @param password
	 *            Mot de passe du Membre qui donne une Opinion
	 * @param pseudoMemberToReview
	 *            Pseudo du membre � noter
	 * @param titre
	 *            Titre de l'item sur lequel l'avis � noter � �t� laiss�
	 * @param itemType
	 *            Type de l'item concern� par l'avis
	 * @param opinionMark
	 *            Opinion � laisser par le membre sur l'avis
	 * 
	 * @return Le nouveau karma du membre
	 * 
	 * @throws NotItem
	 *             : si le titre ne correspond pas a un item du type sp�cifi�
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instanci� ou a moins de 1
	 *             caract�re autre que des espaces .</li>
	 *             <li>si le password n'est pas instanci� ou a moins de 4
	 *             caract�res autres que des leadings or trailing blanks.</li>
	 *             <li>si le pseudo du membre � noter n'est pas instanci� ou a
	 *             moins de 1 caract�re autre que des espaces .</li>
	 *             <li>si le titre n'est pas instanci� ou a moins de 1 caract�re
	 *             autre que des espaces.</li>
	 *             </ul>
	 * <br>
	 * @throws NotMember
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas celui d'un membre ou si le pseudo
	 *             et le password ne correspondent pas
	 *             <li>si le pseudo du membre � noter n'existe pas
	 *             </ul>
	 * <br>
	 * @throws NotReview
	 *             : si l'avis n'a pas �t� not� par le membre
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
		 * Tests des paramettres d'entr�s
		 */
		checkInput(pseudo, inputsTypes.PSEUDO);
		checkInput(password, inputsTypes.PASSWORD);
		checkInput(titre, inputsTypes.TITRE);
		checkInput(pseudoMemberToReview, inputsTypes.PSEUDO);

		/*
		 * Authentification et r�cup�ration du membre
		 */
		member = authentication(pseudo, password);
		if (member == null)
			throw new NotMember("Member " + pseudo
					+ " does not exist or the password is wrong");

		/*
		 * V�rification de l'existance du membre � noter
		 */
		memberToMark = memberExists(pseudoMemberToReview);
		if (memberToMark == null)
			throw new NotMember("Member " + pseudoMemberToReview
					+ " does not exist");

		/*
		 * R�cup�ration de l'item
		 */
		item = getItem(titre, itemType);
		if (item == null)
			throw new NotItem("Item " + titre + " does not exist");

		/*
		 * R�cup�ration de l'avis
		 */
		avis = memberToMark.getReview(item);
		if (avis == null)
			throw new NotReview();

		/*
		 * Ajout de l'opinion
		 */
		opinion = memberToMark.getOpinion(avis);
		if (opinion == null) {
			// Si l'opinion n'existe pas, on la cr�e et on l'ajoute au membre
			// not�.
			opinion = new Opinion(opinionMark, avis);
			memberToMark.addOpinion(opinion);
		} else {
			// Si l'opinion existe, on la met � jour
			opinion.updateOpinion(opinionMark);
		}

		/*
		 * Mise a jour du karma
		 */
		return memberToMark.updateKarma();

	}

	/**
	 * Renvoie le Membre associ� au pseudo pass� en parametre
	 * 
	 * @param pseudo
	 *            Pseudo du membre � rechercher
	 * @return Le membre associ� au pseudo pass� en parametre Null si le membre
	 *         n'existe pas
	 */
	private Member memberExists(String pseudo) {
		int i = 0;

		// Tant que le membre n'est pas trouv� et que la liste n'� pas �t�
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
	 * V�rifie si l'entr�e correspond aux crit�res. Dans le cas contraire,
	 * l'exception BadEntry est lev�e
	 * 
	 * @param input
	 *            Objet � analyser (de type int, float ou String)
	 * @param inputType
	 *            Type d'entr�e � traiter
	 * @throws BadEntry
	 * 			 Mauvaise entr�e
	 */
	private void checkInput(Object input, inputsTypes inputType)
			throws BadEntry {
		/*
		 * Variables
		 */
		// Utilis� si l'objet input est un Integer
		int integerInput;
		// Utilis� si l'objet input est un Float
		float floatInput;
		// Utilis� si l'objet input est un String
		String stringInput;
		// Message d'erreur � mettre dans l'exception
		String errorMessage = "Invalid " + inputType.toString().toLowerCase();

		/*
		 * Test de la instanciation de l'objet input
		 */
		if (input == null)
			throw new BadEntry(errorMessage);

		/*
		 * Tests si input est un String (cas le plus fr�quent)
		 */
		if (input instanceof String) {
			stringInput = (String) input;
			switch (inputType) {
			case PSEUDO:
				// Si le String est compos� uniquement d'espaces
				if (stringInput.matches("\\p{Space}+?"))
					throw new BadEntry(errorMessage);
			case TITRE:
				// Si le String fait moins de 1 caract�re (Hors espace au d�but
				// et � la fin)
				if (stringInput.trim().length() < 1)
					throw new BadEntry(errorMessage);
				break;

			case PASSWORD:
				// Si le String fait moins de 4 caract�re (Hors espace au d�but
				// et � la fin)
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
				// Si l'Integer est inferieur � 1
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
	 * Obtient l'index d'un film dont le titre est pass� en parametre. Dans le
	 * cas ou le film n'existe pas, c'est l'index ou doit etre posisionn� ce
	 * film qui est renvoy�.
	 * 
	 * @param titre
	 *            Titre � rechercher
	 * @param start
	 *            Index de d�but de la recherche
	 * @param end
	 *            Index de fin de la recherche
	 * @return Index du film recherch� ou index o� le positionner
	 */
	private int getIndexFilm(String titre, int start, int end) {
		int middle = (start + end) / 2;
		// Si la zone de recherche est superieure � une case, on poursuit la
		// recherche
		if (end > start) {
			// Si la case annalys�e correspond au film recherch�, on retourne
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
	 * Obtient l'index d'un livre dont le titre est pass� en parametre. Dans le
	 * cas ou le livre n'existe pas, c'est l'index ou doit etre posisionn� ce
	 * livre qui est renvoy�.
	 * 
	 * @param titre
	 *            Titre � rechercher
	 * @param start
	 *            Index de d�but de la recherche
	 * @param end
	 *            Index de fin de la recherche
	 * @return Index du livre recherch� ou index o� le positionner
	 */
	private int getIndexBook(String titre, int start, int end) {
		int middle = (start + end) / 2;

		// Si la zone de recherche est superieure � une case, on poursuit la
		// recherche
		if (end > start) {
			// Si la case annalys�e correspond au film recherch�, on retourne
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
