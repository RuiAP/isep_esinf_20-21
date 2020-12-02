package TP2.model;

import graph.AdjacencyMatrixGraph;

import java.util.ArrayList;
import java.util.Comparator;

public class MetodosP2 {





    /**
     * Seleciona e devolve os n utilizadores (vértices) com mais amigos(vertices adjacentes).
     * @param nUtilizadoresPopulares quantidade de utilizadores a selecionar
     * @return Lista contendo os n utilizadores mais populares
     *         Lista pode ter size()< n se existirem menos de n vertices.
     */
    public static ArrayList<User> calcularUtilizadoresPopulares(AdjacencyMatrixGraph<User, Double> usersGraph, int nUtilizadoresPopulares){
        ArrayList<User> usersList = (ArrayList<User>)usersGraph.vertices();
        ArrayList<User> result = new ArrayList<>();

        //vs BFS por vértice?
        usersList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                //suficiente porque grafo é não direcionado
                return usersGraph.outDegree(o2) - usersGraph.outDegree(o1);
            }
        });

        for (int i = nUtilizadoresPopulares -1; i>=0; i--){
            if(usersList.get(i) != null)
                result.add(usersList.get(i));
        }
        return result;
    }








}
