package main;

import grafo.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws Exception {
        File caminhoArquivo;
        Scanner conteudo;
        Scanner input = new Scanner(System.in);
        Scanner codigos = new Scanner(System.in);
        Grafo unicap = new Grafo();
        int op;
        String codigoInicio, codigoFim;
        LinkedList menorCaminho;
        ArrayList exibir;

        try{
            caminhoArquivo = new File("src/main/vertices.txt");
            conteudo = new Scanner(caminhoArquivo);
            while(conteudo.hasNextLine()){
                String info = conteudo.nextLine();
                String[] dividir = info.split(",");
                int id = Integer.parseInt(dividir[0]);
                String nome = dividir[1];

                Vertice vertice = new Vertice(id, nome);
                unicap.adicionarVertice(vertice);
            }
        } catch(FileNotFoundException e){
            System.out.println("Houve um erro enquanto eu lia os vertices");
            e.printStackTrace();
        }

        try{
            caminhoArquivo = new File("src/main/arestas.txt");
            conteudo = new Scanner(caminhoArquivo);
            while(conteudo.hasNextLine()){
                String info = conteudo.nextLine();
                String[] dividir = info.split(",");
                int id = Integer.parseInt(dividir[0]);
                int idVerticeInicio = Integer.parseInt(dividir[1]);
                int idVerticeFinal = Integer.parseInt(dividir[2]);
                double peso = Double.parseDouble(dividir[3]);

                Aresta aresta = new Aresta(id, idVerticeInicio, idVerticeFinal, peso);
                unicap.adicionarAresta(aresta);
            }
        } catch(FileNotFoundException e){
            System.out.println("Houve um erro enquanto eu lia as arestas");
            e.printStackTrace();
        }

        do{
            menu();
            op = input.nextInt();
            while(op < 0 || op > 3){
                System.out.println("Valor invalido, informe um valor de 0 a 3");
                op = input.nextInt();
            }

            switch(op){
                case 1:
                    System.out.println("Informe o codigo do local em que você se encontra: ");
                    codigoInicio = codigos.nextLine();
                    System.out.println("Informe o codigo do local em que você quer chegar: ");
                    codigoFim = codigos.nextLine();
                    menorCaminho = buscaExtensao(unicap, codigoInicio, codigoFim);
                    exibeCaminho(menorCaminho);
                    break;
                case 2:
                    System.out.println("Informe o codigo do local em que você se encontra: ");
                    codigoInicio = codigos.nextLine();
                    System.out.println("Informe o codigo do local em que você quer chegar: ");
                    codigoFim = codigos.nextLine();
                    menorCaminho = dijkstra(unicap, codigoInicio, codigoFim);
                    exibeCaminho(menorCaminho);
                    break;
                case 3:
                    exibir = new ArrayList<Vertice>();
                    exibir = unicap.getVertices();
                    for(int i = 0; i < exibir.size(); i++){
                        System.out.println(exibir.get(i));
                    }
                    break;
                case 0:
                    System.out.println("Adeus!");
                    break;
                default:
                    System.out.println("Pane no sistema alguem me desconfigurou.");
            }
        }while(op != 0);

    }

    public static void menu(){
        System.out.println("O que você deseja fazer?");
        System.out.println("1 - Achar melhor caminho por busca em extensão.");
        System.out.println("2 - Achar melhor caminho por Dijkstra");
        System.out.println("3 - Exibir todos os vertices.");
        System.out.println("0 - Encerrar o programa.");
    }

    public static LinkedList<Vertice> buscaExtensao(Grafo grafo, String codigoInicio, String codigoFim){
        LinkedList<Vertice> menorCaminho = new LinkedList<Vertice>();
        LinkedList<Vertice> naoVisitados = new LinkedList<Vertice>();
        ArrayList<Vertice> vertices = grafo.getVertices();
        Vertice inicio = grafo.getVerticeCodigo(codigoInicio);
        Vertice fim = grafo.getVerticeCodigo(codigoFim);
        Vertice atual;
        Vertice prox;

        for(int i = 0; i < vertices.size(); i++){
            vertices.get(i).setCor('b');
            vertices.get(i).setAnt(null);
        }

        naoVisitados.add(inicio);
        while(!naoVisitados.isEmpty()){
            atual = naoVisitados.pollFirst();
            ArrayList<Aresta> ligacoes = atual.getArestas();
            for(int i = 0; i < ligacoes.size(); i++){
                prox = grafo.getVertice(ligacoes.get(i).getIdVerticeFinal());
                if(prox.getCor() == 'b'){
                    prox.setCor('c');
                    prox.setAnt(atual);
                    naoVisitados.add(prox);
                }
            }
            atual.setCor('p');
        }

        menorCaminho.add(fim);
        Vertice adicionar = fim.getAnt();
        while(adicionar != null){
            menorCaminho.addFirst(adicionar);
            adicionar = adicionar.getAnt();
        }

        return menorCaminho;
    }


    public static LinkedList<Vertice> dijkstra(Grafo grafo, String codigoInicio, String codigoFim){
        LinkedList<Vertice> menorCaminho = new LinkedList<Vertice>();
        LinkedList<Vertice> naoVisitados = new LinkedList<Vertice>();
        ArrayList<Vertice> vertices = grafo.getVertices();
        Vertice inicio = grafo.getVerticeCodigo(codigoInicio);
        Vertice fim = grafo.getVerticeCodigo(codigoFim);
        Vertice atual;
        Vertice proximo;

        for(int i = 0; i < vertices.size(); i++){
            vertices.get(i).setDistancia(9999);
            vertices.get(i).setAnt(null);
        }

        inicio.setDistancia(0);
        naoVisitados.add(inicio);

        while(!naoVisitados.isEmpty()){
            atual = naoVisitados.pollFirst();
            for(int i = 0; i < atual.getArestas().size(); i++){
                proximo = grafo.getVertice(atual.getArestas().get(i).getIdVerticeFinal());
                if((atual.getDistancia() + atual.getArestas().get(i).getPeso()) < proximo.getDistancia()){
                    proximo.setAnt(atual);
                    proximo.setDistancia(atual.getDistancia() + atual.getArestas().get(i).getPeso());
                }

                if(proximo.getId() == fim.getId()){
                    break;
                }
            }

            Vertice adicionar = fim;
            while(adicionar != null){
                menorCaminho.addFirst(adicionar);
                adicionar = adicionar.getAnt();
            }
        }

        return menorCaminho;
    }

    public static void exibeCaminho(LinkedList<Vertice> caminho){
        System.out.print("O caminho que você deve seguir é: ");
        for(int i = 0; i < caminho.size(); i++){
            System.out.print(caminho.get(i) + " ");
            if(i < caminho.size() - 1){
                System.out.println("> ");
            }
        }
        System.out.println("");
    }
}
