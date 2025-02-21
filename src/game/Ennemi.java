package game;

import game.Niveau.dir;

public  class Ennemi extends Personnage{
	protected int numEnn;
	/**
	 * constructeur ennemi
	 * @param name nom
	 * @param vie points de vie
	 * @param x1 co x
	 * @param y1 co y
	 */
	
	public Ennemi(String name,int vie,int x1,int y1,int n) {
		super(name,vie,x1,y1);
		this.numEnn=n;
	}
	 /**
		 * ajoute un Ennmi dans le tableau d'ennemi et le pose sur la carte à ses coordonées
		 * @param n niveau
		 * @throws OutOfBorderException
		 */
	@Override
	public void appendp(Niveau n) {
		n.verification(this.getX(),this.getY());
		Ennemi[] t=n.getEnnmiTab();
		t[numEnn]=this;
		Cellule[][] tablo= n.getTab();
		tablo[this.getX()][this.getY()].setId('R');//(char) (nbJoueur +'0');
		n.setCoDeBase(this.getX(),2+2*numEnn);
		n.setCoDeBase(this.getY(),3+2*numEnn);
	}
	 /**
		 * déplacement d'un ennemi
		 * @param n niveau
		 * @param direction N S E W pour bouger les personnages de 1 
		 * @throws OutOfBorderException
		 */
	@Override
	public int move(Niveau n, dir direction) {
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
			
			
			if(tablo[jx2][jy2].getId()==3|| tablo[jx2][jy2].getId()==1|| tablo[jx2][jy2].getId()==5) {
			}
			else {
				this.setCoord(jx2,jy2);
				tablo[jx2][jy2].setId('R');
				if(tablo[jx][jy].getIsPiece()==1) {
					tablo[jx][jy].setId('.');
				}
				else {
					tablo[jx][jy].setId(' ');
				}
			}
			
			
		}catch(OutOfBorderException e) {
			
		}
		return 0;
	}
	/**
     * Redéfinition de la méthode toString
     * @return le nom du Ennemi et ses pvs au singulier s'il en a 0 ou 1 et au pluriel s'il en a plus de 1
     */
	@Override
    public String toString() {
    	String strpv="pvs";
    	if(this.getVie()<2) {
    		strpv="pv";
    	}
    	
    	return this.getName()+" : " + this.getVie()+ strpv;
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
        if (!(obj instanceof Ennemi)){
        	return false; // Vérifie si obj est un Joueur
        }
        Ennemi ennemi = (Ennemi) obj;
        return this.getName().equalsIgnoreCase(ennemi.getName()); // Compare les noms sans tenir compte de la casse
    }
}
