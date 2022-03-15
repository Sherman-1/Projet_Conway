
public class mini1 {

    void run(int[] self, int nombreTours, int delai) {
        /*Méthode principale du jeu.
        Fait tourner le jeu de la vie pendant nombreTours.
        Elle rafraichit l'affichage à chaque tour et attend delai enter chaque tour.
        :param nombreTours: nomre de tours à effectuer
        :param delai: temps d'attente en secondes entre chaque tour*/
        for(int k=0;k<nombreTours;k++){ //on va faire nombreTours tours
            /*JeuDelaVie([]) //à définir le JeuDeLaVie
            PAS DU TOUT FINI*/

        }
    }

    void tour(int[] self) {
        /*Met à jour toutes les cellules du tableau en respectant les règles du jeu de la vie.*/
    }

    void valeurCase(int[] self, int i, int j) {
        /*renvoie la valeur de la case [i][j] ou 0 si la case n'existe pas.*/
    }

    void totalVoisins(int[] self, int i, int j) {
        /*renvoie la somme des valeurs des voisins sur la case [i][j].*/
    }

    void resultat(int[] self, int valeurCase, int totalVoisins) {
        /*Renvoie la valeur suivante d'une la cellule
        :param valeurCase: la valeur de la cellule (0 ou 1)
        :param totalVoisins: la somme des valeurs des voisins
        :return: la valeur de la cellule au tour suivant
        >>> a= JeuDeLaVie([])
        >>> a.resultat(0,3)
        1
        >>> a= JeuDelaVie([])
        >>> a.resultat(0,1)
        0 */
    }
}