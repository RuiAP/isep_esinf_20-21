package TP2;


import TP2.model.Country;
import TP2.model.User;
import graph.AdjacencyMatrixGraph;
import graph.GraphAlgorithmsAdjMatrix;
import graphbase.Edge;
import graphbase.Graph;
import TP2.ui.Menu;


public class Main {
    public static void main(String[] args) {

        //Menu menu = new Menu();
        //menu.display();


        CarregarFicheiros cf = new CarregarFicheiros(true);

        Graph<Country, String> grafoTesteCountries = cf.carregarGrafoCountries();
        //System.out.println(grafoTesteCountries);

        AdjacencyMatrixGraph<User, Double> grafoTesteUsers = cf.carregarGrafoUsers();
        System.out.println(grafoTesteUsers.toString2());

        //grafoTesteUsers = GraphAlgorithmsAdjMatrix.transitiveClosure(grafoTesteUsers,1.0);
        //System.out.println(grafoTesteUsers.toString2());






        

    }
}
