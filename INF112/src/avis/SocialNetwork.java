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
	 * A la construction du social network, la listes des membres et des items sont instanci�es avec des listes vides.
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
	 * Le pseudo ne doit pas d�j� exister 
	 * 
	 * @param pseudo
	 * 			Pseudo du membre � ajouter
	 * 
	 * @param password
	 * 			Mot de passe du membre � ajouter
	 * 
	 * @param profil
	 * 			Descriptif du membre � ajouter
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instanci� ou a moins de 1 caract�re autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instanci� ou a moins de 4 caract�res autres que des leadings or trailing blanks. </li>
	 *  <li>  si le profil n'est pas instanci�.  </li>
	 * </ul><br>       
	 * 
	 * @throws MemberAlreadyExists 
	 * 			membre de m�me pseudo d�j� pr�sent dans le <i>SocialNetwork</i> (m�me pseudo : indiff�rent �  la casse  et aux leadings et trailings blanks)
	 * 
	 */
	
	public void addMember(String pseudo, String password, String profil) throws BadEntry, MemberAlreadyExists {
		
		// pseudo null OU taille du pseudo inferieur a 1 caractere OU pseudo
		// compos� uniquement de blancs
		if (pseudo == null || pseudo.length() < 1 || pseudo.matches("\\p{Space}+?")) {
			throw new BadEntry("Invalid username");
		}

		// password non instanci� OU taille inferieure a 4 (leading et trailing
		// blanks ignor�s)
		if (password == null || password.trim().length() < 4) {
			throw new BadEntry("Invalid password");
		}

		// profil non instanci�
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
	 * La dur�e doit �tre positive
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
	 * 			Dur�e du film
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instanci� ou a moins de 1 caract�re autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instanci� ou a moins de 4 caract�res autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instanci� ou a moins de 1 caract�re autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instanci�. </li>
	 *  <li>  si le r�alisateur n'est pas instanci�. </li>
	 *  <li>  si le sc�nariste n'est pas instanci�. </li>
	 *  <li>  si la dur�e n'est pas positive.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemFilmAlreadyExists : item film de m�me titre  d�j� pr�sent (m�me titre : indiff�rent �  la casse  et aux leadings et trailings blanks)
	 * 
	 */
	public void addItemFilm(String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree) throws BadEntry, NotMember, ItemFilmAlreadyExists {
		//Tests des parametres d'entr�s
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
		
		//Authentification
		if(authentication(pseudo, password)==null)
			throw new NotMember("Member does not exist");
		
		//Test de l'existance de l'item
		if (getFilm(titre) != null)
			throw new ItemFilmAlreadyExists();
				
		//Ajout du film
		items.add(new Film(titre, genre, realisateur, scenariste, duree));
		
	}

	/**
	 * Ajoute un livre au social network<br>
	 * <br>
	 * Le pseudo doit contenir au moins 1 caractere autre que des espaces<br>
	 * Le mot de passe doit contenir au moins 4 caractere autre que les leading et trailling blanks<br>
	 * Le titre doit contenir au moins 1 caractere autre que des espaces<br>
	 * La dur�e doit �tre positive
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
	 *  <li>  si le pseudo n'est pas instanci� ou a moins de 1 caract�re autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instanci� ou a moins de 4 caract�res autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instanci� ou a moins de 1 caract�re autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instanci�. </li>
	 *  <li>  si l'auteur n'est pas instanci�. </li>
	 *  <li>  si le nombre de pages n'est pas positif.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemBookAlreadyExists item livre de m�me titre  d�j� pr�sent (m�me titre : indiff�rent � la casse  et aux leadings et trailings blanks)
	 */
	public void addItemBook(String pseudo, String password, String titre, String genre, String auteur, int nbPages) throws BadEntry, NotMember, ItemBookAlreadyExists {
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
		if (getBook(titre) != null)
			throw new ItemBookAlreadyExists();
		
		//Ajout du film
		items.add(new Book(titre, genre, auteur, nbPages));

	}

	/**
	 * Retourne le toString des items correspondant � la recherche<br>
	 * <br>
	 * M�thode non sensible � la casse, le nom doit contenir plus d'un caractere autre que des espaces.
	 * 
	 * @param nom
	 * 			Nom de l'item recherch�
	 * 
	 * @return 
	 * 			Liste des items trouv� sous forme verbeuse
	 * 
	 * @throws BadEntry 
	 * 			si le nom n'est pas instanci� ou a moins de 1 caract�re autre que des espaces.
	 */
	public LinkedList<String> consultItems(String nom) throws BadEntry {
		LinkedList<String> returnList = new LinkedList<String>();
		
		if (getBook(nom) != null)
			returnList.add(getBook(nom).toString());
		
		if (getFilm(nom) != null)
			returnList.add(getFilm(nom).toString());
	
		return returnList;
		
	}

	/**
	 * D�pose un avis sur un film<br>
	 * <br>
	 * Le pseudo doit contenir au moins 1 caractere autre que des espaces<br>
	 * Le mot de passe doit contenir au moins 4 caractere autre que les leading et trailling blanks<br>
	 * Le titre doit contenir au moins un caractere<br>
	 * La note doit �tre comprise entre 0 et 5<br>
	 * Le pseudo et le password doivent d�signer un membre<br>
	 * Le film doit exister
	 * 
	 * @param pseudo
	 * 			Pseudo du membre qui d�pose l'avis
	 * 
	 * @param password
	 * 			Password du membre qui d�pose l'avis
	 * 
	 * @param titre
	 * 			Titre du film
	 * 
	 * @param note
	 * 			Note attribu�e par le membre au film
	 * 
	 * @param commentaire
	 * 			Commentaire du membre sur le film
	 * 
	 * @return 
	 * 			Moyenne des notes d�pos�es sur ce film
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instanci� ou a moins de 1 caract�re autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instanci� ou a moins de 4 caract�res autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instanci� ou a moins de 1 caract�re autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instanci�. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un film.
	 * 
	 * @return la note moyenne des notes sur ce film  
	 */
	public float reviewItemFilm(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
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
		
		//pseudo et password ne correspondent pas ou pseudo n'existe pas
		Member member = authentication(pseudo, password);
		if(member==null)
			throw new NotMember("Member does not exist");

		//Titre ne correspond pas a un film
		Film f = getFilm(titre);
		if(f == null){
			throw new NotItem("Film does not exist");
		}
		
		f.addReviewToCollection(new Avis(note, commentaire, f, member));
		
		return f.getMoyenne();
	}

	/**
	 * D�pose un avis sur un livre<br>
	 * <br>
	 * Le pseudo doit contenir au moins 1 caractere autre que des espaces<br>
	 * Le mot de passe doit contenir au moins 4 caractere autre que les leading et trailling blanks<br>
	 * Le titre doit contenir au moins 1 caractere<br>
	 * La note doit �tre comprise entre 0 et 5<br>
	 * Le pseudo et le password doivent d�signer un membre<br>
	 * Le livre doit exister
	 * 
	 * @param pseudo
	 * 			Pseudo du membre qui d�pose l'avis
	 * 
	 * @param password
	 * 			Password du membre qui d�pose l'avis
	 * 
	 * @param titre
	 * 			Titre du livre
	 * 
	 * @param note
	 * 			Note attribu�e par le membre au livre
	 * 
	 * @param commentaire
	 * 			Commentaire du membre sur le livre
	 * 
	 * @return 
	 * 			Moyenne des notes d�pos�es sur ce livre
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instanci� ou a moins de 1 caract�re autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instanci� ou a moins de 4 caract�res autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instanci� ou a moins de 1 caract�re autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instanci�. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un livre.
	 */
	public float reviewItemBook(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
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

				
		//pseudo et password ne correspondent pas ou pseudo n'existe pas
		Member member = authentication(pseudo, password);
		if(member==null)
			throw new NotMember("Member does not exist");


		//Titre ne correspond pas a un livre
		Book b = getBook(titre);
		if(b == null)
			throw new NotItem("Book does not exist");
						
		b.addReviewToCollection(new Avis(note, commentaire, b, member));
				
		return b.getMoyenne();
	}

	/**
	 * Authentifie un membre
	 *
	 * @param pseudo
	 * 			Pseudo du membre qui d�pose l'avis
	 * 
	 * @param password
	 * 			Password du membre qui d�pose l'avis
	 * 
	 * @return 
	 * 			Le membre d�sign� par le couple pseudo/password <br>
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
	
	private Book getBook(String titre){
		Book b = null;
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

	private Film getFilm(String titre){
		Film b = null;
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

}
