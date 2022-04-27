package grafo;

import java.util.ArrayList;

public class Grafo {
    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;

    public Grafo(){
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
    }

    public void adicionarVertice(int id, String nome){
        Vertice novoVertice = new Vertice(id, nome);
        this.vertices.add(novoVertice);
    }
    
    public void adicionarAresta(int id, int idVerticeOrigem, int idVerticeDestino, double peso){
        Vertice inicio = this.getVertice(idVerticeOrigem);
        Vertice fim = this.getVertice(idVerticeDestino);
        Aresta aresta = new Aresta(id, inicio, fim, peso);

        inicio.adicionarAresta(aresta);
        fim.adicionarAresta(aresta);
        this.arestas.add(aresta);
    }

    public Vertice getVertice(int id){
        Vertice vertice = null;

        for(int i = 0; i < this.vertices.size(); i++){
            if(this.vertices.get(i).getId() == id){
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }
}
