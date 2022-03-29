package projet2;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Pandemie {
    static int CONTA = 0;
    static int CONTA_VACC = 1;

    public static void main(String[] args) throws InterruptedException {
        int ligne = 100, colonne = 100;

        Individu[][] monde = new Individu[ligne][colonne];
        for(int i =0; i<ligne; i++){
            for(int j =0; j<colonne; j++) {
                monde[i][j] = new Individu(0,0);
            }
        }

        int vaccine = 50;
        int contamine = 2;

        Random rand = new Random();
        for(int k =0; k< vaccine; k++){
            monde[rand.nextInt(ligne)][rand.nextInt(colonne)].vaccine =1;
        }
        for(int k =0; k< contamine; k++){
            monde[rand.nextInt(ligne)][rand.nextInt(colonne)].contamine =1;
        }

        JFrame p = panneau();

        afficherMonde(monde, ligne, colonne, p);
        while (true) {
            TimeUnit.MILLISECONDS.sleep(120);
            //le jeu est le même qu'au tour précédent
            // ............. à écrire

            monde = nouvelMonde(monde, ligne, colonne);
            afficherMonde(monde, ligne, colonne, p);
        }
    }

    //renvoie la somme des valeurs des voisins vivants sur la case [i][j].
    static int[] totalVoisins(Individu[][] monde, int i, int j) {
        int[] voisins = new int[2];
        Arrays.fill(voisins, 0);
        int a,b;
        for(int l =-1; l<2; l++){
            for(int c=-1; c<2; c++){
                if(l != 0 || c !=0){
                    a = i+l;
                    b = j+c;
                    //pas d'effet de bordure
                    if(a<0) a+=monde.length;
                    if(b<0) b+=monde.length;
                    Individu ami = monde[(a)%monde.length][(b)%monde.length];
                    if (ami.contamine == 1) {
                        if(ami.vaccine == 1) voisins[CONTA_VACC] += 1;
                        else voisins[CONTA]+=1;
                    }
                }
            }
        }
        return voisins;
    }

    //renvoie le nouvel état de la cellule tableau[i][j]
    static int resultatVoisin(Individu[][] monde, int i, int j){
        Random rand = new Random();
        int[] voisins = totalVoisins(monde,i,j);

        //l'individu est sain
        if(monde[i][j].contamine==0) {
            //voisins contaminé = 1/4 de l'être
            for (int k = 0; k < voisins[CONTA]; k++) {
                //si l'individu est vacciné :
                if (monde[i][j].vaccine == 1) {
                    if (rand.nextInt(8) == 0) return 1;
                }//sinon :
                else if (rand.nextInt(4) == 0) return 1;
            }
            //voisins contaminé et vacciné = 1/8 de l'être
            for (int k = 0; k < voisins[CONTA_VACC]; k++) {
                //si l'individu est vacciné
                if (monde[i][j].vaccine == 1) {
                    if (rand.nextInt(16) == 0) return 1;
                } else if (rand.nextInt(8) == 0) return 1;
            }
        }
        return monde[i][j].contamine;
    }

    //renvoie le nouveau tableau des états des cellules
    static Individu[][] nouvelMonde(Individu[][] monde, int ligne, int colonne){
        Individu[][] nouveauTableau = new Individu[ligne][colonne];

        for(int i=0; i<ligne; i++){
            for(int j=0; j<colonne; j++){
                nouveauTableau[i][j] = new Individu(monde[i][j].vaccine,resultatVoisin(monde,i,j));

            }
        }
        return nouveauTableau;
    }

    //panneau d'affichage
    static JFrame panneau(){
        JFrame frame = new JFrame("Jeu de la Vie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    static void afficherMonde(Individu[][] monde, int ligne, int colonne, JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(0,colonne));
        for( int i=0; i<ligne; i++) {
            for( int j=0; j<colonne; j++) {
                int contamination = monde[i][j].contamine;
                int vaccination = monde[i][j].vaccine;
                JPanel cellule = new JPanel();
                cellule.setPreferredSize(new Dimension(8,8));
                // donne une taille de 32x32 pixels par défaut
                if ( contamination == 0 && vaccination == 0) {
                    cellule.setBackground(Color.WHITE);
                } else if ( contamination==0 && vaccination == 1) {
                    cellule.setBackground(Color.BLUE);
                }
                else  if ( contamination==1 && vaccination == 1) {
                    cellule.setBackground(Color.RED);
                }
                else {
                    cellule.setBackground(Color.BLACK);
                }
                panel.add(cellule);
            }
        }

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
