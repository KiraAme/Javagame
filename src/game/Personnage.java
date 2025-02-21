package game;

import game.Niveau.dir;

public abstract class Personnage {
	protected final String name; //le nom n'est pas modifiable
	protected int vie;//nb de vie
	protected int x; // coordonnée x
	protected int y; // coordonée y
	
	/**
	 * constructeur de personnage
	 * @param n nom
	 * @param pv nb points de vies
	 * @param x1 coordonee x
	 * @param y1 coordonee y
	 */
	public Personnage(String n,int pv, int x1, int y1) {
		this.name=n;
		this.vie=pv;
		this.x=x1;
		this.y=y1;
		
	}
	/**
	 * pour bouger le personnage 
	 * @param n le niveau
	 * @param direction la direction choisie
	 * @return les conditions de victoire ou de défaite
	 */
	public abstract int move(Niveau n, dir direction);
	/**
	 * pour ajouter un personnage au niveau
	 * @param n niveau
	 */
	public abstract void appendp(Niveau n);
	/**
	 * Getter du nom du joueur
	 * @return le nom du joueur
	 */
	public String getName() { //getter du nom
        return this.name;
    }
	/**
	 * Getter du x du joueur
	 * @return le x du joueur
	 */
    public int getX() { //getter du x
        return this.x;
    }
    /**
	 * Getter du y du joueur
	 * @return le y du joueur
	 */
    public int getY() { //getter du y
        return this.y;
    }
    /**
     * getter des pv
     * @return les pv actuels
     */
    public int getVie() {
    	return this.vie;
    }
    /**
     * Setter des points de vie
     * @param hp sont les nouveaux points de vie
     */
    public void setVie(int hp) {
    	if(hp>=0) {
    		this.vie=hp;
    	}
    	else {
    		this.vie=0;
    	}
    }
    /**
	 * setter des coordonnées
	 * @param cx nouveau x du joueur et cy nouveau y du joueur
	 */
    public void setCoord(int cx,int cy) {
    	this.x=cx;
    	this.y=cy;
    }
    /**
     * pour bouger dans le niveau comme dans un tore
     * @param tablo est une tableau de tableau de cellule du niveau
     * @param jx2 prochaine coordonnées x du personnage
     * @param jy2 prochaine coordonnées y du personnage
     * @return tableau des nouvelles coordonnees
     */
    public int[] checktore(Cellule[][] tablo,int jx2,int jy2) {
    	int lt=tablo.length;
		int lt2=tablo[0].length;
		int[] tab = new int[2];
		if (jx2 == lt) {
			jx2 = 0;
		}
		if (jx2 == -1) {
			jx2 = lt-1;
		}
		if (jy2 == lt2) {
			jy2 = 0;
		}
		if (jy2 == -1) {
			jy2 = lt2-1;
		}
		tab[0]=jx2;
		tab[1]=jy2;
		return tab;
    }
    /**
     * pour prevoir la prochaine positions
     * @param direction N S E W
     * @return tableau de coordonnee avec les coordonees actuelles et celles prevues
     */
    public int[] checkdir(dir direction) {
    	int jx=this.getX();
		int jy=this.getY();
		int jx2=this.getX();
		int jy2=this.getY();
		switch(direction){	
		case N :
			jx2--;
			break;
		case S :
			jx2++;
			break;
		case W :
			jy2--;
			break;
		case E :
			jy2++;
			break;
		default:
			break;
		}
		int[] tab = new int[4];
		tab[0]=jx;
		tab[1]=jy;
		tab[2]=jx2;
		tab[3]=jy2;
		return tab;
		
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
        if (!(obj instanceof Personnage)){
        	return false; // Vérifie si obj est un Joueur
        }
        Personnage pers = (Personnage) obj;
        return this.getName().equalsIgnoreCase(pers.getName()); // Compare les noms sans tenir compte de la casse
    }
    /**
     * Redéfinition de la méthode toString
     * @return le nom du personnage et ses pvs au singulier s'il en a 0 ou 1 et au pluriel s'il en a plus de 1
     */
    @Override
    public String toString() {
    	String strpv="pvs";
    	if(this.getVie()<2) {
    		strpv="pv";
    	}
    	
    	return this.getName()+" : " + this.getVie()+ strpv;
    }
}
