package projet2;


public class Individu {
    protected int vaccine;
    protected int contamine;

    public Individu(int vaccine, int contamine){
        this.vaccine = vaccine;
        this.contamine = contamine;
    }

    public int getVaccine(){
        return this.vaccine;
    }

    public int getContamine(){
        return this.contamine;
    }

}

