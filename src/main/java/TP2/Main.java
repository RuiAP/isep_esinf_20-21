package TP2;


import graph.AdjacencyMatrixGraph;
import graphbase.Graph;


public class Main {
    public static void main(String[] args) {




        CarregarFicheiros cf = new CarregarFicheiros(true);

        Graph<Country, String> grafoTesteCountries = cf.carregarGrafoCountries();
        System.out.println(grafoTesteCountries);

        AdjacencyMatrixGraph<User, Double> grafoTesteUsers = cf.carregarGrafoUsers();
        System.out.println(grafoTesteUsers);



        

    }
}
