package game;

import java.util.Random;
import java.util.Scanner;

import game.Niveau.dir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe pour créer deux joueurs de la classe Joueur
 * @author Bantos-Arnaud Damien
 * @version 1.0
 */
public class Gestion {
	
	public static int jouer(Niveau n) {
		Joueur j=n.getJoueurTab()[0];
		Ennemi [] e=n.getEnnmiTab();
        System.out.println(n.toString());
        System.out.println(j.toString());
        Scanner inp= new Scanner(System.in);
        
        
        boolean jouer=true;
        do {
            System.out.println("Dans quelle direction voulez-vous le déplacer");
            char dire = inp.next().charAt(0);
            dire= Character.toUpperCase(dire);
            Niveau.dir direction = null;
            
            
            switch (dire) {
                case 'Z': direction = Niveau.dir.N; break;
                case 'S': direction = Niveau.dir.S; break;
                case 'D': direction = Niveau.dir.E; break;
                case 'Q': direction = Niveau.dir.W; break;
                case 'E': jouer=false; System.out.println("Merci d'avoir joué"); System.exit(0);
                default:
                    System.out.println("Direction invalide");
                    continue;
            }
            if(jouer) {
            	for(int i=0;i<e.length;i++) {
            		dir[] direc=dir.values();
                	Random rand = new Random();
                	int randomIndex = rand.nextInt(direc.length);
                	e[i].move(n, direc[randomIndex]);
            	}
            	int res=j.move(n, direction);  	
            	if(res==1) {
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
            	else if(res==2){
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
            		return 2;
            	}
            }
            
        } while (jouer);

        return 0;
	}
	
	/**
	 * main servant à initialiser deux joueurs 
	 * @param args vide
	 * @throws OutOfBorderException 
	 */
	public static void main(String[] args) {
		if(args.length>0) {
			Scanner inp_nom = new Scanner(System.in);
			System.out.println("Entrez votre nom de joueur");
			String nom = inp_nom.next();
			Joueur joueur1 = new Joueur(nom);
			for(int i=0; i< args.length;i++) {
				Niveau n= new Niveau(Paths.get(args[i]),joueur1);
				int res=jouer(n);
				if(res==2) {
					System.out.println("Voulez-vous rejouer Y/N");
					Scanner inp_rj = new Scanner(System.in);
					char rj= inp_rj.next().charAt(0);
					rj=Character.toUpperCase(rj);
					switch (rj) {
		                case 'Y': i--;
		                	break;
		                case 'N': 
		                	System.out.println("Merci d'avoir joué");
		                	System.exit(0); 
		                	break;
		                
		                default:
		                    System.out.println("Input invalide Y/N");
		                    continue;
					}
				}
				
			}
			System.out.println("Merci d'avoir joué");
			
			
			
			
			
			
			/*for(int i =1; i<11;i++) {
				Alice.setPoints(Alice.getPoints() + i * 100); 	// on ajoute aux points d'Alice 100 puis 200 ... puis 1000	       
			}
			Bob.setPoints(Bob.getPoints()-100); // on enlève 100 points à Bob
			//on affiche les noms et les points de Alice et Bob
			System.out.println(Alice.toString());
			System.out.println(Bob.toString());
			System.out.println(BOB.toString());
			System.out.println("Alice == \"Alice\" ? " + (Alice.equals("Alice"))); 
	        System.out.println("Alice == Bob ? " + Alice.equals(Bob));  
	        System.out.println("Bob = BOB ? " + Bob.equals(BOB)); 
	        System.out.println("Bob == BOB (avec ==) ? " + (Bob == BOB)); 
	        System.out.println("Bob == b ? " + (Bob == b)); 
	        System.out.println(j.toString());
	        System.out.println(jj.toString());
	        System.out.println(jjj.toString());
	        System.out.println(Joueur.getCompteur());*/
	        
	        //Niveau n2= new Niveau(10,15,2);
	        //System.out.println(n1.equals(n2));
	        
			
		}
		else {
			System.err.println("Erreur le format attendu est : java -jar path_to_fichier.jar path_to_niveau.txt");
			System.exit(2);
		}
		
		
		

        	
        
        	
        /*n1.moveJoueur(Alice, Niveau.dir.W);
        n1.moveJoueur(Alice, Niveau.dir.W);
        n1.moveJoueur(Alice, Niveau.dir.W);
        n1.moveJoueur(Alice, Niveau.dir.N);
        n1.moveJoueur(Alice, Niveau.dir.E);
        n1.moveJoueur(Alice, Niveau.dir.E);
        n1.moveJoueur(Alice, Niveau.dir.E);
        n1.moveJoueur(Alice, Niveau.dir.E);*/
        
        
	}
}
