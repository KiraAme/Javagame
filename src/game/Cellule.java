package game;

public class Cellule {
	private int x;
	private int y;
	private int id;
	/**
	 * constructeur d'une cellule
	 * @param i position x
	 * @param j position y
	 * @param n caractere dans la cellule
	 */
	public Cellule(int i, int j, char n) {
		this.x=i;
		this.y=j;
		switch(n) {
		case ' ': 
			this.id=0;
			break;
		case '#':
			this.id=1;
			break;
		case '.':
			this.id=2;
			break;
		case '*':
			this.id=3;
			break;
		case '1':
			this.id=4;
			break;
			
		default:
			this.id=0;
			break;
		}	
	}
	/**
	 * getter de x
	 * @return la position x
	 */
	public int getX() {
		return this.x;
	}
	/**
	 * getter de y 
	 * @return la position y
	 */
	public int getY() {
		return this.y;
	}
	/**
	 * getter du id
	 * @return le id de ce qu'il y a dans la cellule
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * setter de l'id
	 * @param n caractere à mettre dans la cellule
	 */
	public void setId(char n) {
		switch(n) {
		case ' ': 
			this.id=0;
			break;
		case '#':
			this.id=1;
			break;
		case '.':
			this.id=2;
			break;
		case '*':
			this.id=3;
			break;
		case '1':
			this.id=4;
			break;
			
		default:
			this.id=0;
			break;
		}
		
	}
	/**
	 * Réecriture de toString 
	 * @return renvoie le caractère lié à l'id
	 */
	@Override
	public String toString() {
		switch(this.getId()) {
			case 0: 
				return " ";
		case 1:
				return "#";
		case 2:
				return ".";
		case 3:
				return "*";
		case 4:
				return "1";
		default:
				return " ";
		}	
	}
	
	
	/**
	 * Réecriture de equals
	 * @param objet à comparer avec la cellule
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
        	return true; // Si les deux Cellules sont identiques
        }
        if (!(obj instanceof Cellule)){
        	return false; // Vérifie si obj est une cellule
        }
        Cellule object = (Cellule) obj;
        return  ((this.getId() == object.getId() ) && (this.getX()==object.getX()) && (this.getY()==object.getY())); 
	}
}
