import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class mini1 {
    public static void main(String[] args) throws InterruptedException {
        int ligne = 20, colonne = 20;

        // 0 : mort     1 : vie
        // état de départ des cellules
        int[][] tableau = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        //  TEST
        int grille[][] = new int[40][40];
        Arrays.stream(grille).forEach(i -> Arrays.fill(i, 0));
        //

        JFrame p = panneau(ligne);
        Afficher(tableau, ligne, colonne, p);
        int i = 1;
        while (i > 0) {
            TimeUnit.MILLISECONDS.sleep(120);
            //le jeu est le même qu'au tour précédent
            if(Arrays.deepEquals(tableau,nouvelEtat(tableau, ligne, colonne))){
                System.out.println("Le jeu est figé.");
                break;
            }
            else tableau = nouvelEtat(tableau, ligne, colonne);
            Afficher(tableau, ligne, colonne, p);
        }
    }


    //afficher la matrice (dans le terminal)
    static void afficherTableau(int tableau[][], int ligne, int colonne) {
        System.out.println("Etat des cellules");
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                if (tableau[i][j] == 0) //mort
                    System.out.print(".");
                else //vie
                    System.out.print("*");
            }
            System.out.println(); //ligne suivante
        }
    }

    //renvoie la somme des valeurs des voisins vivants sur la case [i][j].
    static int totalVoisins(int[][] tableau, int i, int j) {
        int voisins = 0;
        for(int l =-1; l<2; l++){
            for(int c=-1; c<2; c++){
                if(l != 0 || c !=0){
                    if ( i+l >=0 && i+l < tableau.length &&
                            j+c >=0 && j+c < tableau.length &&
                            tableau[i + l][j + c] == 1) {
                        voisins += 1;
                    }
                }
            }
        }
        return voisins;
    }

    //renvoie le nouvel état de la cellule tableau[i][j]
    static int resultatVoisin(int[][] tableau, int i, int j){
        //cellule morte ou vivante avec 3 voisins en vie
        if(totalVoisins(tableau,i,j) == 3){
            return 1;
        }
        //cellule en vie avec 2 voisins en vie
        if(tableau[i][j] == 1 && totalVoisins(tableau,i,j) ==2){
            return 1;
        }
        return 0 ;
    }

    //renvoie le nouveau tableau des états des cellules
    static int[][] nouvelEtat(int[][] tableau, int ligne, int colonne){
        int[][] nouveauTableau = new int[ligne][colonne];
        for(int i=0; i<ligne; i++){
            for(int j=0; j<colonne; j++){
                nouveauTableau[i][j] = resultatVoisin(tableau,i,j);
            }
        }
        return nouveauTableau;
    }

    //panneau d'affichage
    static JFrame panneau(int ligne){
        JFrame frame=new JFrame("Jeu de la Vie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    //(majoritairement copié d'internet)
    static void Afficher(int[][] tableau, int ligne, int colonne, JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(0,colonne));

        for( int i=0; i<ligne; i++) {
            for( int j=0; j<colonne; j++) {
                int valeur = tableau[i][j];
                JPanel cellule = new JPanel();
                cellule.setPreferredSize(new Dimension(32,32));
                // donne une taille de 32x32 pixels par défaut
                if ( valeur==0 ) {
                    cellule.setBackground(Color.WHITE);
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