package projet2;

public class Individu {

    protected boolean contamine, etat_futur; // Noémie A1 en anglais
    protected boolean vaccine;
    protected int etatEntier;
    protected int x, y; // coordonnées

    public Individu(int x, int y, boolean cont, boolean vac) {

        this.x = x;
        this.y = y;
        this.contamine = cont;
        this.vaccine = vac;

    }

    void defConta(boolean state) {

        this.contamine = state;
    }

    void defFutur(boolean futur) {

        this.etat_futur = futur;
    }

    void changeEtatEntier() {

        if (this.contamine == false && this.vaccine == false) {

            this.etatEntier = 0;
        }

        else if (this.contamine == false && this.vaccine == true) {

            this.etatEntier = 1;
        }

        else if (this.contamine == true && vaccine == false) {

            this.etatEntier = 2;
        }

        else {

            this.etatEntier = 3;
        }
    }

}