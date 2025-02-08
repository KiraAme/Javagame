package game;
/**
 * 
 * Classe Joueur permettant de gérer des joueurs avec chacun un nom et des points
 * @author Bantos-Arnaud Damien
 * @version 1.0
 */
public class Joueur extends Personnage{

	
	
	private int points;  // les points ne sont pas négatifs
	
	private static int compteurj =1; // compteur du nombre de joueur
	
	
	
	/**
	 * Constructeur permettant de créer un joueur sans spécifier de nom
	 */
	public Joueur() {
		this("Joueur"+compteurj);
	}
	/**
	 * On initialise un joueur avec comme nom le paramètre nm et les points à 0
	 *@param nm faites attention le nom n'est pas modifiable, nm doit être une String
	 */
	public Joueur(String nm) { //construction
		super(nm,3,0,0);
		this.points=0;
		
		compteurj++;
	}
	
	/**
	 * getter du compteur des joueurs
	 * @return nb compteur joueur -1
	 */
	public static int getCompteur() {
		return compteurj -1;
	}
	/**
	 * Getter des points du joueur
	 * @return les points du joueur
	 */
    public int getPoints() { //getter des points
        return this.points;
    }
    
    /**
     * Redéfinition de la méthode toString
     * @return le nom du joueur et ses points au singulier s'il en a 0 ou 1 et au pluriel s'il en a plus de 1
     */
    @Override
    public String toString() {
    	String strpt="pts";
    	String strpv="pvs";
    	if(this.getPoints()<2) {
    		strpt="pt";
    	}
    	if(this.getVie()<2) {
    		strpv="pv";
    	}
    	
    	return this.getName()+" : "+ this.getPoints() +strpt +" "+ this.getVie()+ strpv;
    }
    /**
     * Redéfinition de la méthode equals
     * @return True si les deux objets sont identiques
     * @return False si ce n'est pas le même type
     * @return True ou False en utilisant equalsIgnoreCase pour enlever la casse
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
        	return true; // Si les deux objets sont identiques
        }
        if (!(obj instanceof Joueur)){
        	return false; // Vérifie si obj est un Joueur
        }
        Joueur joueur = (Joueur) obj;
        return this.getName().equalsIgnoreCase(joueur.getName()); // Compare les noms sans tenir compte de la casse
    }
    /**
     * Setter des points du joueur si les points futur sont positifs pas de soucis, sinon on remet les points à 0
     * @param points ce sont les nouveaux points: pour en ajouter il faut faire getPoints(this.pointsactuels + points)
     */
    public void setPoints(int points) { //setter des points
        if (points >= 0) {
            this.points = points; //si c'est positif on remplace
        } else {
            this.points=0; //si cela devait être négatif on remplace par 0 qui est la valeur minimale
        }
    }
}


