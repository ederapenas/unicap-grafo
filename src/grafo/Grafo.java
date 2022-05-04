package grafo;

import java.util.ArrayList;
import java.util.Collections;

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
    
     void resetaVertices(){
        for(int i = 0; i < this.vertices.size(); i++){
            this.vertices.get(i).setCor('b');
            this.vertices.get(i).setDistancia(100000);
        }
    }

    public void buscaExtensao(Vertice vertice){
        ArrayList<Vertice> marcados = new ArrayList<Vertice>();
        ArrayList<Vertice> fila = new ArrayList<Vertice>();
        marcados.add(vertice);
        System.out.println(vertice.getNome());
        fila.add(vertice);
        while(fila.size()>0){
            Vertice visitado = fila.get(0);
            for(int i =0; i < visitado.getArestas().size(); i++){
                int idProximo = visitado.getArestas().get(i).getIdVerticeFinal();
                Vertice proximo = this.getVertice(idProximo);

                if(marcados.contains(proximo)){
                    marcados.add(proximo);
                    System.out.println(proximo.getNome());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }


    }

    public ArrayList<Vertice> dijkstra(Vertice inicio, Vertice destino){
        ArrayList<Vertice> menorCaminho = new ArrayList<Vertice>();
        Vertice caminho = null;
        Vertice atual = null;
        Vertice vizinho = null;
        ArrayList<Vertice> naoVisitados = new ArrayList<Vertice>();

        menorCaminho.add(inicio);

        for(int i = 0; i < this.vertices.size(); i++){
            if(this.vertices.get(i).getId() == inicio.getId()){
                this.vertices.get(i).setDistancia(0);
            }
            else{
                this.vertices.get(i).setDistancia(9999);
            }

            naoVisitados.sort(null);

            while(!naoVisitados.isEmpty()){
                atual = naoVisitados.get(i);

                for(int i = 0; i < atual.getArestas().size(); i++){
                    vizinho = this.getVertice(atual.getArestas().get(i).getIdVerticeFinal());
                    if(!vizinho.getDistancia() > (atual.getDistancia() + atual.getArestas().get(i).getPeso()){
                        vizinho.setDistancia(atual.getDistancia() + atual.getArestas().get(i).getPeso());
                        
                        vizinho.setAnt(atual);

                        if(vizinho == destino){
                            menorCaminho.clear();
                            caminho = vizinho;
                            menorCaminho.add(vizinho);

                            while(caminho.getAnt() != null){
                                menorCaminho.add(caminho.getAnt());
                                caminho = caminho.getAnt();
                            }

                            menorCaminho.sort(null);
                        }
                    }
                }
            }

            naoVisitados.remove(atual);
            naoVisitados.sort(null);

        }

        return menorCaminho;
    }
}
