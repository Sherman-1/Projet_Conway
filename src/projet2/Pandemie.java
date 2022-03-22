package projet2;

import java.util.Random;

public class Pandemie {

    Random rand = new Random();
    int rows, columns;
    Individu world[][];

    public Pandemie(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;
        this.world = new Individu[rows][columns];

        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                if (rand.nextFloat() < 0.1) { // 10% de contaminés
                    if (rand.nextFloat() < 0.1) { // 10% de vaccinés
                        this.world[x][y] = new Individu(x, y, true, true);
                    } else {
                        this.world[x][y] = new Individu(x, y, true, false);
                    }
                }

                else { // Les 90% de non contaminés restants
                    if (rand.nextFloat() < 0.1) { // Les non contaminés ont aussi 10% de chance d'être vaccinés
                        this.world[x][y] = new Individu(x, y, false, true);
                    } else {
                        this.world[x][y] = new Individu(x, y, false, false);
                    }
                }
            }
        }
    }
}
