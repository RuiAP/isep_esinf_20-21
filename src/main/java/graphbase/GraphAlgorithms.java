/*
* A collection of graph algorithms.
*/
package graphbase;

import java.util.*;


/**
 *
 * @author DEI-ESINF
 */

public class GraphAlgorithms {
    
   /**
   * Performs breadth-first search of a Graph starting in a Vertex 
   * @param g Graph instance
   * @param vInf information of the Vertex that will be the source of the search
   * @return qbfs a queue with the vertices of breadth-first search 
   */
    public static<V,E> LinkedList<V> BreadthFirstSearch(Graph<V,E> g, V vert){
    LinkedList<V> qbfs = new LinkedList<>();
    LinkedList<V> toVisitAfter = new LinkedList<>();

    qbfs.add(vert);
    toVisitAfter.add(vert);

    while (!toVisitAfter.isEmpty()){
        vert = toVisitAfter.removeFirst();
        if(g.adjVertices(vert) == null){return null;}
        for(V vertAdj : g.adjVertices(vert)){
            if (!qbfs.contains(vertAdj)){
                qbfs.add(vertAdj);
                toVisitAfter.add(vertAdj);
            }
        }
    }
        return qbfs;
    }
   
   /**
   * Performs depth-first search starting in a Vertex   
   * @param g Graph instance
   * @param vOrig Vertex of graph g that will be the source of the search
   * @param visited set of discovered vertices
   * @param qdfs queue with vertices of depth-first search
   */
    private static<V,E> void DepthFirstSearch(Graph<V,E> g, V vOrig, LinkedList<V> qdfs){
        qdfs.add(vOrig);
        for(V vertAdj : g.adjVertices(vOrig)){
            if(!qdfs.contains(vertAdj)){
                DepthFirstSearch(g,vertAdj, qdfs);
            }
        }
    }  
  
   /**
   * @param g Graph instance
   * @param vInf information of the Vertex that will be the source of the search
   * @return qdfs a queue with the vertices of depth-first search 
   */
    public static<V,E> LinkedList<V> DepthFirstSearch(Graph<V,E> g, V vert){

        if(g.adjVertices(vert) == null) return null;

        LinkedList<V> qdfs = new LinkedList<>();
        DepthFirstSearch(g,vert, qdfs);

        return qdfs;
    }
   
    /**
   * Returns all paths from vOrig to vDest
   * @param g Graph instance
   * @param vOrig Vertex that will be the source of the path
   * @param vDest Vertex that will be the end of the path
   * @param visited set of discovered vertices
   * @param path stack with vertices of the current path (the path is in reverse order)
   * @param paths ArrayList with all the paths (in correct order)
   */
    private static<V,E> void allPaths(Graph<V,E> g, V vOrig, V vDest,  
                                           LinkedList<V> path, ArrayList<LinkedList<V>> paths){

       path.push(vOrig);

       for(V vAdj : g.adjVertices(vOrig)){
           if(vAdj == vDest){
               path.push(vDest);
               paths.add(path);
               path.pop();
           }
           else {
               if(!path.contains(vAdj))
                    allPaths(g,vAdj, vDest, path, paths);
           }
       }
       path.pop();
    }
    
   /**
   * @param g Graph instance
   * @param voInf information of the Vertex origin
   * @param vdInf information of the Vertex destination 
   * @return paths ArrayList with all paths from voInf to vdInf 
   */
    public static<V,E> ArrayList<LinkedList<V>> allPaths(Graph<V,E> g, V vOrig, V vDest){

        if (!(g.validVertex(vOrig) && g.validVertex(vDest))) {
            return null;
        }

        LinkedList<V> path = new LinkedList<>();
        ArrayList<LinkedList<V>> paths = new ArrayList<>();

        allPaths(g,vOrig,vDest, path, paths);
        return paths;
    }
    
    /**
   * Computes shortest-path distance from a source vertex to all reachable 
   * vertices of a graph g with nonnegative edge weights
   * This implementation uses Dijkstra's algorithm
   * @param g Graph instance
   * @param vOrig Vertex that will be the source of the path
   * @param visited set of discovered vertices
   * @param pathkeys minimum path vertices keys  
   * @param dist minimum distances
   */
    protected static<V,E> void shortestPathLength(Graph<V,E> g, V vOrig, 
                                    boolean[] visited, V[] pathKeys, double[] dist){


        for (int i= 0; i<dist.length;i++){
            dist[i] =(double) Double.MAX_VALUE;
            pathKeys[i] = null;
        }

        pathKeys[g.getKey(vOrig)] = vOrig;
        dist[g.getKey(vOrig)] = 0;


        V verticeAtual = vOrig;

        while(verticeAtual != null) {
            visited[g.getKey(verticeAtual)] = true;

            for (V vAdj : g.adjVertices(verticeAtual)) {

                if (!visited[g.getKey(vAdj)] && dist[g.getKey(vAdj)] > dist[g.getKey(verticeAtual)] + g.getEdge(verticeAtual, vAdj).getWeight()) {
                    dist[g.getKey(vAdj)] = dist[g.getKey(verticeAtual)] + g.getEdge(verticeAtual, vAdj).getWeight();
                    pathKeys[g.getKey(vAdj)] = verticeAtual;
                }
            }

            double min = Double.MAX_VALUE;
            int minIndex = -1;
            for(int i = 0; i<dist.length; i++){
                if(!visited[i] && dist[i]< min){
                    min= dist[i];
                    minIndex = i;
                }
            }

            V[] relacao = pathKeys.clone();
            for(V v : g.vertices()){
                relacao[g.getKey(v)] = v;
            }

            if(minIndex == -1){
                verticeAtual = null;
            }else{
                verticeAtual = relacao[minIndex];

            }
            }
        }




    /**
    * Extracts from pathKeys the minimum path between voInf and vdInf
    * The path is constructed from the end to the beginning
    * @param g Graph instance
    * @param voInf information of the Vertex origin
    * @param vdInf information of the Vertex destination 
    * @param pathkeys minimum path vertices keys  
    * @param path stack with the minimum path (correct order)
    */
    protected static<V,E> void getPath(Graph<V,E> g, V vOrig, V vDest, V[] pathKeys, LinkedList<V> path) {


        if (vOrig.equals(vDest)) {
            path.add(vOrig);
        } else {

            path.add(vDest);
            V vAtual = vDest;
            while (vAtual != vOrig) {
                path.add(pathKeys[g.getKey(vAtual)]);
                vAtual = pathKeys[g.getKey(vAtual)];
            }

        }
    }

    //shortest-path between vOrig and vDest
    public static<V,E> double shortestPath(Graph<V,E> g, V vOrig, V vDest, LinkedList<V> shortPath){
        //Acho que ambos os metodos publicos são independentes (simplesmente ambos usam o metodo do dijkstra)
        //ambos criam os arrays distance, apthkeys, etc e chamam dijkstra
        //podem usar o getPath ou não


        //-------------------------------------
        //este deve ser para chamar o dijkstra e depois chamar o getPath

        throw new UnsupportedOperationException("Not supported yet.");


    }
   
    //shortest-path between voInf and all other
    public static<V,E> boolean shortestPaths(Graph<V,E> g, V vOrig, ArrayList<LinkedList<V>> paths, ArrayList<Double> dists){
        boolean[] visited = new boolean[g.numVertices()];
        double[] distances = new double[g.numVertices()];
        V[] pathkeys = (V[]) new Object[g.numVertices()];

        //invocas o metodo do dijkstra o que te atualiza os arrays com os dados
        shortestPathLength(g,vOrig,visited,pathkeys,distances);


        //atualiza o ArrayList dists com as distancias
        //ou percebi algo mal ou não faz sentido este metodo receber uma Arraylist quando o metodo do dijkstra recebe um array(de double primitivos ainda por cima)
        for (int i = 0; i<distances.length; i++){
                dists.add(distances[i]);

        }

        for (V vertice : g.vertices()) {

                LinkedList<V> caminho = new LinkedList<>();

                getPath(g, vOrig, vertice, pathkeys, caminho);

                //ordenar o caminho de vOrig até vDest
                caminho = revPath(caminho);
            System.out.println(caminho);
                //adiciona caminho à lista
                paths.add(caminho);

        }
            return true;
    }
    
    
    /**
     * Reverses the path
     * @param path stack with path
     */
    private static<V,E> LinkedList<V> revPath(LinkedList<V> path){ 
   
        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();
        
        while (!pathcopy.isEmpty())
            pathrev.push(pathcopy.pop());
        
        return pathrev ;
    }    
}