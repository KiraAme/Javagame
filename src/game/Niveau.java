package game;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Classe permettant la création de niveau
 * @author Damien Bantos-Arnaud
 * @version v1.0
 */
public class Niveau {
	private Cellule[][] tab; // tableau de tableau de cellule
	private Joueur[] joueur; // tableau des joueurs
	private int nbJoueur; //nb des joueurs actuels sur la carte
	private int xBase;
	private int yBase;
	private int nbPieces =0; //nb de pieces actuellement sur le niveau
	private int nbPiecesBase=0;
	/**
	 * dir est un enum avec N S E W pour les quatres points cardinaux
	 */
	public enum dir{
		/**
		 * Nord
		 */
		N,
		/**
		 * Sud
		 */
		S,
		/**
		 * Est
		 */
		E,
		/**
		 * Ouest
		 */
		W};
	/**
	 * constructeur d'un niveau, 
	 * ce niveau la est composé de mur # aux extrémités et de vide partout ailleurs
	 * @param lt la taille de deux côtés 
	 * @param lt2 la taille des deux autres côtés 
	 */
	public Niveau(int lt, int lt2) {
		this.joueur = new Joueur[1];
		this.tab = new Cellule[lt][lt2];
		for(int i=0;i<lt;i++) {
			for(int j=0;j<lt2;j++) {
				if(i==j && ((i!=0 && j!=0)||(i!=lt-1)&&(j!=lt2-1))){
					this.tab[i][j]= new Cellule(i,j,'.'); //on créé des .
					this.nbPieces++;
					this.nbPiecesBase++;
				}
				else if(i==0 ||j==0||i==lt-1||j==lt2-1) {
					this.tab[i][j]=new Cellule(i,j,'#'); // on crée des #
				}
				else {
					this.tab[i][j]=new Cellule(i,j,' '); // on créé du vide à l'intérieur du niveau
				}
			}
		}
	}
	/**
	 * Constructeur à partir d'un fichier texte.
	 * @param chemin est le chemin du fichier texte
	 */
	public Niveau(Path chemin) {
		this.joueur = new Joueur[1];
		try {
			List<String> tablo = Files.readAllLines(chemin);
			
			int compt=tablo.get(0).length();
			for(int i=1; i<tablo.size(); i++) {	//Boucle pour vérifier la rectangularité de la grille 
				if(tablo.get(i).length() != compt) {
					throw new ArrayIndexOutOfBoundsException(); 
				}
			}
			this.tab= new Cellule[tablo.size()][tablo.get(0).length()]; // on initialise le tableau du niveau
			for(int i =0; i<tablo.size();i++) { // et on le remplit
				for(int j =0;j<tablo.get(i).length();j++) {
					this.tab[i][j]=new Cellule(i, j, tablo.get(i).charAt(j));
					if(this.tab[i][j].getId()==2) {
						this.nbPieces++;
						this.nbPiecesBase++;
					}
				}
			}
		}catch(IOException e) {
			System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage()); // On vérifie si le fichier existe
			System.exit(1);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Erreur lors de la lecture du fichier : Le fichier n'est pas rectangulaire"); // On vérifie si on dépasse l'index
			System.exit(3);
		}
		
	}
	/**
	 * getter de tab
	 * @return le tableau tab
	 */
	public Cellule[][] getTab() {
		return this.tab;
	}
	
	/**
	 * getter de joueur
	 * @return le tableau des joueurs
	 */
	public Joueur[] getJoueurTab() {
		return this.joueur;
	}
	/**
	 * getter de nbJoueur
	 * @return le nombre de joueur
	 */
	public int getNbJoueur() {
		return this.nbJoueur;
	}
	public int getXBase() {
		return this.xBase;
	}
	public int getYBase() {
		return this.yBase;
	}
	/**
	 * getter du nombre de pieces
	 * @return le nombre de pieces actuelles sur le niveau
	 */
	public int getNbPieces() {
		return this.nbPieces;
	}
	/**
	 * verifie la position d'un joueur est valide
	 * @param jx coordonées x du joueur
	 * @param jy coordonées y du joueur
	 * @throws OutOfBorderException
	 */
	private void verification(int jx, int jy) {
		Cellule[][] tablo= this.getTab();
		int lt=tablo.length;
		int lt2=tablo[0].length;
		if((jx>=lt || jx<0 )||(jy>=lt2 || jy<0 ) || tablo[jx][jy].getId()==1) {
			throw new OutOfBorderException("Le joueur est en dehors des limites ou dans un mur du niveau");
		}
	}
	/**
	 * ajoute un joueur dans le tableau de joueur et le pose sur la carte à ses coordonées
	 * @param j joueur
	 * @throws OutOfBorderException
	 */
	public void appendJoueur(Joueur j) {
		Joueur[] t=this.getJoueurTab();
		if (t.length >= nbJoueur) {
			t[nbJoueur]=j;
			nbJoueur+=1;
			Cellule[][] tablo= this.getTab();
			
				this.verification(j.getX(),j.getY());
				tablo[j.getX()][j.getY()].setId('1');//(char) (nbJoueur +'0');
				this.xBase=j.getX();
				this.yBase=j.getY();		
		}
	}
	/**
	 * déplacement d'un joueur
	 * @param j joueur
	 * @param direction N S E W pour bouger les personnages de 1 
	 * @throws OutOfBorderException
	 */
	public int moveJoueur(Joueur j,dir direction) {
			Cellule[][] tablo= this.getTab();
			int jx=j.getX();
			int jy=j.getY();
			int jx2=j.getX();
			int jy2=j.getY();
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
			try {
				int lt=tablo.length;
				int lt2=tablo[0].length;
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
				this.verification(jx2,jy2);
				
				if(tablo[jx2][jy2].getId()==2) {
					j.setCoord(jx2,jy2);
					j.setPoints(j.getPoints()+100);
					this.nbPieces--;
					tablo[jx2][jy2].setId('1');
				}
				else if(tablo[jx2][jy2].getId()==3) {
					j.setVie(j.getVie()-1);
					int xB=this.getXBase();
					int yB=this.getYBase();
					tablo[xB][yB].setId('1');
					j.setCoord(xB,yB);
				}
				else {
					j.setCoord(jx2,jy2);
					tablo[jx2][jy2].setId('1');
				}
				tablo[jx][jy].setId(' ');
				
			}catch(OutOfBorderException e) {
				
			}
			System.out.println(this.toString());
			System.out.println(j.toString());
			
			if(this.nbPieces==0) {
				System.out.println("Victoire\n"
						+ "\r\n"
						+ "      (_)    | |                  \r\n"
						+ "__   ___  ___| |_ ___  _ __ _   _ \r\n"
						+ "\\ \\ / / |/ __| __/ _ \\| '__| | | |\r\n"
						+ " \\ V /| | (__| || (_) | |  | |_| |\r\n"
						+ "  \\_/ |_|\\___|\\__\\___/|_|   \\__, |\r\n"
						+ "                             __/ |\r\n"
						+ "                            |___/ ");
				return 1;
			}
			if(j.getVie()==0) {
				System.out.println("GAME OVER\n"
						+"┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n"
						+"███▀▀▀██┼███▀▀▀███┼███▀█▄█▀███┼██▀▀▀\n"
						+"██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼█┼┼┼██┼██┼┼┼\n"
						+"██┼┼┼▄▄▄┼██▄▄▄▄▄██┼██┼┼┼▀┼┼┼██┼██▀▀▀\n"
						+"██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██┼┼┼\n"
						+"███▄▄▄██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██▄▄▄\n"
						+"┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n"
						+"███▀▀▀███┼▀███┼┼██▀┼██▀▀▀┼██▀▀▀▀██▄┼\n"
						+"██┼┼┼┼┼██┼┼┼██┼┼██┼┼██┼┼┼┼██┼┼┼┼┼██┼\n"
						+"██┼┼┼┼┼██┼┼┼██┼┼██┼┼██▀▀▀┼██▄▄▄▄▄▀▀┼\n"
						+"██┼┼┼┼┼██┼┼┼██┼┼█▀┼┼██┼┼┼┼██┼┼┼┼┼██┼\n"
						+"███▄▄▄███┼┼┼─▀█▀┼┼─┼██▄▄▄┼██┼┼┼┼┼██▄\n"
						+"┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n"
						+"┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼\n"
						+"┼┼┼┼┼┼████▄┼┼┼▄▄▄▄▄▄▄┼┼┼▄████┼┼┼┼┼┼┼\n"
						+"┼┼┼┼┼┼┼┼┼▀▀█▄█████████▄█▀▀┼┼┼┼┼┼┼┼┼┼\n"
						+"┼┼┼┼┼┼┼┼┼┼┼█████████████┼┼┼┼┼┼┼┼┼┼┼┼\n"
						+"┼┼┼┼┼┼┼┼┼┼┼██▀▀▀███▀▀▀██┼┼┼┼┼┼┼┼┼┼┼┼\n"
						+"┼┼┼┼┼┼┼┼┼┼┼██┼┼┼███┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼\n"
						+"┼┼┼┼┼┼┼┼┼┼┼█████▀▄▀█████┼┼┼┼┼┼┼┼┼┼┼┼\n"
						+"┼┼┼┼┼┼┼┼┼┼┼┼███████████┼┼┼┼┼┼┼┼┼┼┼┼┼\n"
						+"┼┼┼┼┼┼┼┼▄▄▄██┼┼█▀█▀█┼┼██▄▄▄┼┼┼┼┼┼┼┼┼\n"
						+"┼┼┼┼┼┼┼┼▀▀██┼┼┼┼┼┼┼┼┼┼┼██▀▀┼┼┼┼┼┼┼┼┼\n"
						+"┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼\n"
						+"┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n");
				j.setVie(3);
				j.setPoints(j.getPoints()-(this.nbPiecesBase-this.nbPieces)*100);
				return 2;
			}return 0;
		}
	
	/**
	 * Redéfinition de la méthode toString()
	 * @return elle renvoie une chaîne de caractères avec des \n aux extrémités du tableau .
	 *
	 */
	@Override
	public String toString(){
		Cellule[][] tablo= this.getTab();
		String str = "";
		int t=tablo.length;
		int t2=tablo[0].length;
		str+= this.nbPieces +"Pièces restantes\n";
		for(int i=0;i<t;i++) { //on parcours le tableau de haut en bas et de gauche à droite jusqu'à arriver à taille-1
        	for(int k=0;k<t2;k++) {
        		if(k==t2-1) {
 
        			str+= tablo[i][k].toString() +"\n";
        		}
        		else {
        			str+= tablo[i][k].toString();
 
        		}
        	}
        }
		return str;
	}
	/**
	 * Redéfinition de equals
	 * @return renvoie Vrai si:
	 * -il s'agit d'une référence
	 * -si les tableaux sont les mêmes
	 *  revoie faux si:
	 * -ce n'est pas la bonne classe
	 * -si les length sont différentes
	 * -si les tableaux sont différents
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
        	return true; // Si les deux niveaux sont identiques
        }
        if (!(obj instanceof Niveau)){
        	return false; // Vérifie si obj est un Niveau
        }
        Niveau nivo = (Niveau) obj;
        Cellule[][] tablo1=this.getTab();
        Cellule[][] tablo2=nivo.getTab();
        int t=tablo1.length;
        int tk=tablo1[0].length;
        int t2=tablo2.length;
        int tk2=tablo2[0].length;
        if(t!=t2 || tk!=tk2) {
        	return false;
        }
        for(int i=0;i<t;i++) { //on parcours le tableau de haut en bas et de gauche à droite jusqu'à arriver à taille-1
        	for(int k=0;k<tk;k++) {
        		if(tablo1[i][k]!=tablo2[i][k]) {
        			return false;
        		}
        		
        	}
        }
        return true;
	}
}
