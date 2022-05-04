package grafo;

public class Aresta {
    private final int id;
    private final int idVerticeInicio;
    private final int idVerticeFinal;
    private double peso;

    public Aresta(int id, int idVerticeInicio, int idVerticeFinal, double peso){
        this.id = id;
        this.idVerticeInicio = idVerticeInicio;
        this.idVerticeFinal = idVerticeFinal;
        this.peso = peso;
    }

    public int getId(){
        return this.id;
    }

    public int getIdVerticeInicio(){
        return this.idVerticeInicio;
    }

    public int getIdVerticeFinal(){
        return this.idVerticeFinal;
    }

    public double getPeso(){
        return this.peso;
    }

    public void setPeso(double peso){
        this.peso = peso;
    }
}
