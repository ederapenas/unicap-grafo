package main;

import grafo.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Grafo unicap = new Grafo();
        int op;
        String codigoInicio, codigoFim;
        LinkedList menorCaminho;

        do{
            menu();
            op = input.nextInt();
            while(op < 0 || op > 2){
                System.out.println("Valor invalido, informe um valor de 0 a 2");
                op = input.nextInt();
            }

            switch(op){
                case 1:
                    System.out.println("Informe o codigo do local em que você se encontra: ");
                    codigoInicio = input.nextLine();
                    System.out.println("Informe o codigo do local em que você quer chegar: ");
                    codigoFim = input.nextLine();
                    menorCaminho = buscaExtensao(unicap, codigoInicio, codigoFim);
                    exibeCaminho(menorCaminho);
                    break;
                case 2:
                    System.out.println("Informe o codigo do local em que você se encontra: ");
                    codigoInicio = input.nextLine();
                    System.out.println("Informe o codigo do local em que você quer chegar: ");
                    codigoFim = input.nextLine();
                     menorCaminho = dijkstra(unicap, codigoInicio, codigoFim);
                     exibeCaminho(menorCaminho);
                    break;
                case 0:
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
            vertices.get(i).setDistancia(9999);
        }

        naoVisitados.add(inicio);
        while(!naoVisitados.isEmpty()){
            atual = naoVisitados.getFirst();
            for(int i = 0; i < atual.getArestas().size(); i++){
                prox = grafo.getVertice(atual.getArestas().get(i).getIdVerticeFinal());
                if(prox.getCor() == 'b'){
                    prox.setCor('c');
                    prox.setAnt(atual);
                    naoVisitados.add(prox);
                }

                if(prox.getId() == fim.getId()){
                    break;
                }
            }

            naoVisitados.removeFirst();
            atual.setCor('p');
        }

        Vertice adicionar = fim;
        menorCaminho.addFirst(adicionar);
        while(adicionar != null){
            adicionar = adicionar.getAnt();
            menorCaminho.addFirst(adicionar);
        }

        return menorCaminho;
    }


    public static LinkedList<Vertice> dijkstra(Grafo grafo, String codigoInicio, String codigoFim){
        LinkedList<Vertice> menorCaminho;
        LinkedList<Vertice> naoVisitados;
        Vertice inicio = grafo.getVerticeCodigo(codigoInicio);
        Vertice fim = grafo.getVerticeCodigo(codigoFim);


        return menorCaminho;
    }

    public static void exibeCaminho(LinkedList<Vertice> caminho){
        System.out.print("O caminho que você deve seguir é: ");
        for(int i = 0; i < caminho.size(); i++){
            System.out.print(caminho.get(i) + " ");
            if(i != caminho.size()){
                System.out.println("> ");
            }

        }
    }
}
