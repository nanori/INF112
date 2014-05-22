package avis;

import java.util.LinkedList;
/**
 * @author Yannick LUCET
 * @author Tom VEILLARD
 */
public class Member {

	/**
	 * Pseudo du membre
	 * 
	 * @see Member#exists(String)
	 * @see Member#exists(String, String)
	 */
	private String pseudo;
	
	/**
	 * Mot de passe du membre
	 * 
	 * @see Member#exists(String, String)
	 */
	private String password;
	
	/**
	 * Profil du membre
	 * 
	 */
	private String profil;
	
	/**
	 * Liste des avis déposés par le membre
	 */
	private LinkedList <Avis> avis;
	
	/**
	 * Liste des avis déposés par le membre
	 */
	private LinkedList <Opinion> opinions;
	
	/**
	 * Karma du membre. 
	 * <br>
	 * Flottant compris entre 0 et 2.<br>
	 * Plus les Opinions sur ses Avis sont positifs, plus le karma du membre est élevé
	 */
	private float karma;
	
	/**
	 * Initialise les attributs
	 * 
	 * @param pseudo
	 * 			Pseudo du membre
	 * @param password
	 * 			Mot de passe du membre
	 * @param profil
	 * 			Profil du membre
	 */
	public Member(String pseudo, String password, String profil){
		this.pseudo=pseudo;
		this.password=password;
		this.profil=profil;
		avis = new LinkedList<Avis>();
		opinions = new LinkedList<Opinion>();
		karma = 1.0f;
		//System.err.println("Constructeur Member : Taille opinions = "+ opinions.size());
	}
	
	/**
	 * Test si le couple pseudo mot de passe correspond au membre.<br>
	 * Les espaces avant et apres le titre en parametre sont ignorés. La methode n'est pas sensible à la casse.
	 * @param pseudo
	 * 			Pseudo à tester
	 * @param password
	 * 			Mot de passe à tester
	 * 
	 * @return
	 * 			Vrai si le couple pseudo mot de passe correspond au membre. <br>
	 * 			Faux si non
	 */
	public boolean exists(String pseudo, String password) {
	
		if(this.pseudo.equalsIgnoreCase(pseudo.trim()) && this.password.equals(password)){
			return true;
		}else{ 
			return false;
		}
	}
	
	/**
	 * Verifie si le pseudo est celui du membre.
	 * 
	 * @param pseudo
	 * 			Pseudo à tester
	 * @return
	 * 			Vrai si le pseudo correspond au membre. <br>
	 * 			Faux si non
	 */
	public boolean exists(String pseudo){
		return this.pseudo.equalsIgnoreCase(pseudo.trim());
	}

	/**
	 * Ajoute un avis à la fin de la linkedList avis
	 * 
	 * @param avis
	 * 			Avis à ajouter
	 */
	public void addReviewToCollection(Avis avis) {
		this.avis.addLast(avis);
	}
	
	/**
	 * Renvoi l'avis de l'item passé en parametre
	 * 
	 * @param item
	 * 			Item de réference
	 * @return
	 * 			Avis concerné par l'item ou null si l'item n'a pas été noté par ce membre
	 */
	public Avis getReview(Item item){
		int i=0;
		
		while (i<avis.size()){
			if (avis.get(i).itemEquals(item))
				return avis.get(i);
			
			i++;
		}
		
		return null;
	}
	
	/**
	 * Renvoi l'opinion de l'avis passé en paramettre
	 * 
	 * @param avis
	 * 			Avis de réference
	 * @return
	 * 			Opinion concerné par l'avis ou null si l'avis n'a pas été noté par ce membre
	 */
	public Opinion getOpinion(Avis avis){
		int i=0;
		
		while (i<opinions.size()){
			if (opinions.get(i).avisEquals(avis))
				return opinions.get(i);
			
			i++;
		}
		
		return null;
	}
	
	/**
	 * Ajoute une nouvelle Opinion dans la liste des Opinion laissées sur le Membre
	 * 
	 * @param opinion
	 * 			Nouvelle Opinion à ajouter
	 */
	public void addOpinion(Opinion opinion){
		this.opinions.addLast(opinion);
		//System.err.println("("+this.pseudo+")addOpinion Member : Taille opinions apres ajout = "+ opinions.size());
	}
	
	/**
	 * Met à jour le Karma d'un Membre à partir des Opinion laissées sur ses Avis
	 * 
	 * @return
	 * 			Nouveau karma
	 */
	public float updateKarma(){
		int i = 0;
		int somme = 0;
		
		/*
		 * Récuperation de la somme des opinions
		 */
		System.out.println(opinions.size());
		while (i<opinions.size()){
			somme += opinions.get(i).getOpinionMark();
			i++;
		}
		
		/*
		 * Calcul du nouveau karma
		 */
		this.karma = (float)((2/Math.PI)*(Math.atan(somme/4.0)+Math.PI/2));
		
		/*
		 * Mise a jour de la pondération dans les avis
		 */
		i=0;
		while (i<avis.size()){
			avis.get(i).updatePonderation();
			i++;
		}
		
		return karma;
	}
	
	/**
	 * @return
	 * 			Karma du Membre
	 */
	public float getKarma(){
		return karma;
	}
	
	public String toString(){
		String retour="Pseudo : " + pseudo + "\n" +
				"Profil : "+ profil + "\n";
		
		return retour;
	}

}
