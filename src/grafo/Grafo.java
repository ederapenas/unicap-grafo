package grafo;

import java.util.ArrayList;

public class Grafo {
    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;

    public Grafo(){
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
    }

    public void adicionarVertice(Vertice novoVertice){
        this.vertices.add(novoVertice);
    }
    
    public void adicionarAresta(Aresta novaAresta){
        Vertice inicio = this.getVertice(novaAresta.getIdVerticeInicio());
        Vertice fim = this.getVertice(novaAresta.getIdVerticeFinal());

        inicio.adicionarAresta(novaAresta);
        fim.adicionarAresta(novaAresta);
        this.arestas.add(novaAresta);
    }

    public ArrayList<Vertice> getVertices(){
        return this.vertices;
    }

    public ArrayList<Aresta> getArestas(){
        return this.arestas;
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
    
    public Vertice getVerticeCodigo(String codigo){
        Vertice vertice = null;

        for(int i = 0; i < this.vertices.size(); i++){
            if(codigo.equalsIgnoreCase(this.vertices.get(i).getNome())){
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }

}
