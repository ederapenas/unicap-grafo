package grafo;

import java.util.ArrayList;

public class Vertice {
    private final int id;
    private final String nome;
    private ArrayList<Aresta> arestas;
    private char cor;
    private double distancia;

    public Vertice(int id, String nome){
        this.id = id;
        this.nome = nome;
        this.arestas = new ArrayList<Aresta>();
        this.cor = 'b';
        this.distancia = 9999;
    }

    public int getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public char getCor(){
        return this.cor;
    }

    public double getDistancia(){
        return this.distancia;
    }

    public void adicionarAresta(Aresta aresta){
        this.arestas.add(aresta);
    }

    public ArrayList<Aresta> getArestas(){
        return this.arestas;
    }

    public void setCor(char cor){
        this.cor = cor;
    }

    public void setDistancia(double distancia){
        this.distancia = distancia;
    }

}
