package projet2;

public class Individu {

    protected boolean contamine, vaccine;
    protected int x,y; // coordonnées
    protected double coeff_contamination;

    public Individu(int x, int y,boolean cont, boolean vac) {

        this.x = x;
        this.y = y;
        this.contamine = cont;
        this.vaccine = vac;

    }




}
