package TP2.model;

import graphbase.Graph;

import java.util.LinkedList;

public class MetodosP4 {



    /**
     * Performs breadth-first search of a Graph starting in a Vertex and stops after numVertLimit vertices
     * @param g Graph instance
     * @param vert information of the Vertex that will be the source of the search
     * @param numVertLimit int that limits the number of vertices the BFS will visit
     * @return qbfs a queue with the vertices of breadth-first search
     */
    //equivalente ao BFS de GraphAlgorithms mas com um limitador do número de ciclos a executar
    public static<V,E> LinkedList<V> limitedBreadthFirstSearch(Graph<V,E> g, V vert, int numVertLimit){
        LinkedList<V> qbfs = new LinkedList<>();
        LinkedList<V> toVisitAfter = new LinkedList<>();

        qbfs.add(vert);
        if(numVertLimit == 0){return qbfs;}
        toVisitAfter.add(vert);

        while (!toVisitAfter.isEmpty()){
            vert = toVisitAfter.removeFirst();

            if(g.adjVertices(vert) == null){return null;}
            for(V vertAdj : g.adjVertices(vert)){
                if (!qbfs.contains(vertAdj)){
                    qbfs.add(vertAdj);
                    if(qbfs.size() == numVertLimit+1){ //a lista vai ter sempre +1 porque inclui a cidade origem
                        return qbfs;
                    }
                    toVisitAfter.add(vertAdj);
                }
            }
        }
        return qbfs;
    }





}
