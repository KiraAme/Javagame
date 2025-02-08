package game;

public abstract class Personnage {
	protected final String name; //le nom n'est pas modifiable
	protected int vie;//nb de vie
	protected int x; // coordonnée x
	protected int y; // coordonée y
	
	public Personnage(String n,int pv, int x1, int y1) {
		this.name=n;
		this.vie=pv;
		this.x=x1;
		this.y=y1;
		
	}
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
}
