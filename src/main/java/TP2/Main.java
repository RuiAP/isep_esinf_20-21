package TP2;


import TP2.model.Country;
import TP2.model.User;
import graph.AdjacencyMatrixGraph;
import graph.EdgeAsDoubleGraphAlgorithms;
import graph.GraphAlgorithmsAdjMatrix;
import graphbase.Edge;
import graphbase.Graph;
import TP2.ui.Menu;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.display();


        CarregarFicheiros cf = new CarregarFicheiros(true);

        //Graph<Country, String> grafoTesteCountries = cf.carregarGrafoCountries();
        //System.out.println(grafoTesteCountries);

        AdjacencyMatrixGraph<User, Double> grafoTesteUsers = cf.carregarGrafoUsers();
        System.out.println("Carregado " +  LocalDateTime.now());

        ArrayList<User> newlist = (ArrayList<User>)grafoTesteUsers.vertices();
        System.out.println(newlist.get(328));
        LinkedList<User> lista = GraphAlgorithmsAdjMatrix.BFS(grafoTesteUsers,newlist.get(650));
        System.out.println(lista);
        System.out.println("acabou BFS " + LocalDateTime.now());

        grafoTesteUsers = GraphAlgorithmsAdjMatrix.transitiveClosure(grafoTesteUsers,1.0);
        System.out.println("acabou t" + LocalDateTime.now());
        //grafoTesteUsers = GraphAlgorithmsAdjMatrix.transitiveClosureMinDist(grafoTesteUsers);
        //System.out.println(grafoTesteUsers.toString2());


        

    }
}
