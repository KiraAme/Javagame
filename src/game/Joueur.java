package game;

import game.Niveau.dir;

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
	 * ajoute un joueur dans le tableau de joueur et le pose sur la carte à ses coordonées
	 * @param n niveau
	 * @throws OutOfBorderException
	 */
    @Override
	public void appendp(Niveau n) {
		Joueur[] t=n.getJoueurTab();
		if (t.length >= n.getNbJoueur()) {
			t[0]=this;
			Cellule[][] tablo= n.getTab();
			n.verification(this.getX(),this.getY());
			tablo[this.getX()][this.getY()].setId('1');//(char) (nbJoueur +'0');
			n.setCoDeBase(this.getX(),0);
			n.setCoDeBase(this.getY(),1);		
		}
	}
    
    /**
	 * déplacement d'un joueur
	 * @param n niveau
	 * @param direction N S E W pour bouger le personnage de 1 
	 * @throws OutOfBorderException
	 */
    @Override
	public int move(Niveau n ,dir direction) {
			Cellule[][] tablo= n.getTab();
			int[] coo=new int[2];
			coo=this.checkdir(direction);
			int jx=coo[0];
			int jy=coo[1];
			int jx2=coo[2];
			int jy2=coo[3];
			
			try {
				int[] newco=new int[2];
				newco=this.checktore(tablo, jx2, jy2);
				jx2=newco[0];
				jy2=newco[1];
				n.verification(jx2,jy2);
				
				if(tablo[jx2][jy2].getId()==5) {
					this.setVie(this.getVie()-1);
					int numEnnemi=0;
					int xB=n.getCoDeBase()[0];
					int yB=n.getCoDeBase()[1];
					for(int i=0;i<n.getEnnmiTab().length;i++) {
						if(jx2==n.getEnnmiTab()[i].getX() && jy2==n.getEnnmiTab()[i].getY() ) {
							numEnnemi= i;
						}
					}
					int xB2=n.getCoDeBase()[2*numEnnemi];
					int yB2=n.getCoDeBase()[2*numEnnemi+1];
					tablo[xB][yB].setId('1');
					this.setCoord(xB,yB);
					tablo[n.getEnnmiTab()[numEnnemi].getX()][n.getEnnmiTab()[numEnnemi].getY()].setId(' ');
					tablo[xB2][yB2].setId('R');
					n.getEnnmiTab()[numEnnemi].setCoord(xB2, yB2);
					if(tablo[jx2][jy2].getIsPiece()==1) {
						
						tablo[jx2][jy2].setId('.');
					}
					else {
						tablo[jx2][jy2].setId(' ');
					}
					
						
				}
				else if(tablo[jx2][jy2].getId()==2) {
					this.setCoord(jx2,jy2);
					this.setPoints(this.getPoints()+100);
					n.setNbPieces(n.getNbPieces()-1);
					tablo[jx2][jy2].setIsPiece(0);
					tablo[jx2][jy2].setId('1');
				}
				else if(tablo[jx2][jy2].getId()==3) {
					this.setVie(this.getVie()-1);
					int xB=n.getCoDeBase()[0];
					int yB=n.getCoDeBase()[1];
					tablo[xB][yB].setId('1');
					this.setCoord(xB,yB);
				}
				else {
					this.setCoord(jx2,jy2);
					tablo[jx2][jy2].setId('1');
				}
				if(tablo[jx][jy].getId()==5) {
					tablo[jx][jy].setId('R');
				}
				else {
					tablo[jx][jy].setId(' ');
				}
				
				
				
			}catch(OutOfBorderException e) {
				
			}
			System.out.println(n.toString());
			System.out.println(this.toString());
			
			if(n.getNbPieces()==0) {
				
				return 1;
			}
			if(this.getVie()==0 ) {
				
				this.setVie(3);
				this.setPoints(this.getPoints()-(n.getNbPiecesBase()-n.getNbPieces())*100);
				return 2;
			}return 0;
		}
    
    
    /**
     * Redéfinition de la méthode toString
     * @return le nom du joueur et ses points et ses pvs au singulier s'il en a 0 ou 1 et au pluriel s'il en a plus de 1
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


