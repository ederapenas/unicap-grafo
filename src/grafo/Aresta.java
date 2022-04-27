package grafo;

public class Aresta {
    private final int id;
    private Vertice inicio;
    private Vertice fim;
    private double peso;

    public Aresta(int id, Vertice inicio, Vertice fim, double peso){
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.peso = peso;
    }

    public int getId(){
        return this.id;
    }

    public Vertice getInicio(){
        return this.inicio;
    }

    public Vertice getFim(){
        return this.fim;
    }

    public void setInicio(Vertice inicio){
        this.inicio = inicio;
    }

    public void setFim(Vertice fim){
        this.fim = fim;
    }

    public double getPeso(){
        return this.peso;
    }

    public void setPeso(double peso){
        this.peso = peso;
    }
}
