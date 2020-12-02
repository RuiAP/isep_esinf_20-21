package TP2;

import TP2.model.Country;
import TP2.model.User;
import graph.AdjacencyMatrixGraph;
import graph.GraphAlgorithmsAdjMatrix;
import graphbase.Graph;
import graphbase.GraphAlgorithms;

import java.util.*;

public class Controller {
    private CarregarFicheiros cf;
    private Graph<Country, String> countriesGraph;
    private AdjacencyMatrixGraph<User, Double> usersGraph;

    public Controller(){

        //carrega um grafo small ao entrar no Menu. Menos eficiente mas evita nullpointerExceptions
        cf = new CarregarFicheiros(true);
        countriesGraph = cf.carregarGrafoCountries();
        usersGraph = cf.carregarGrafoUsers();
    }



    public boolean p1CriarGrafos(boolean selectSmallFiles) {

        if(selectSmallFiles){
            cf = new CarregarFicheiros(true);
        }else{
            cf = new CarregarFicheiros(false);
        }
        countriesGraph = cf.carregarGrafoCountries();
        usersGraph = cf.carregarGrafoUsers();
       return true; //falta validação para não dar um erro e uma confirmação ao mesmo tempo
    }


    public LinkedHashSet<User> p2CalcularAmigosComuns(ArrayList<User> utilizadoresPopulares) {

        LinkedHashSet<User> amigosComuns = new LinkedHashSet<>();
        ArrayList<User> listTemp1, listTemp2;

        for (User popUser : utilizadoresPopulares){
            for(User popUser2 : utilizadoresPopulares){
                if(popUser != popUser2){
                    listTemp1 = (ArrayList<User>)usersGraph.directConnections(popUser);
                    listTemp2 = (ArrayList<User>)usersGraph.directConnections(popUser2);
                    for (User u3 : listTemp1){
                        if(listTemp2.contains(u3)){
                            amigosComuns.add(u3);
                        }
                    }
                }
            }
        }

        return amigosComuns;
    }

    /**
     * Seleciona e devolve os n vertices(utilizadores) com mais vertides adjacentes.
     * @param nUtilizadoresPopulares quantidade de utilizadores a selecionar
     * @return Lista contendo os n utilizadores mais populares
     *         Lista pode ter size()< n se existirem menos de n vertices.
     */
    public ArrayList<User> calcularUtilizadoresPopulares(int nUtilizadoresPopulares){
        ArrayList<User> usersList = (ArrayList<User>)usersGraph.vertices();
        ArrayList<User> result = new ArrayList<>();

        //melhor ou pior que um BFS por vertice?
        usersList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return usersGraph.outDegree(o2) - usersGraph.outDegree(o1); //suficiente porque grafo é não direcionado
            }
        });

        for (int i = nUtilizadoresPopulares -1; i>=0; i--){
            if(usersList.get(i) != null)
            result.add(usersList.get(i));
        }
        return result;
    }

    /**
     * Verifica se o grafo é conexo e devolve o diametro do grafo
     * Calculado a partir do máximo da matriz de custos minimos (Floyd-Warshall)
     * @return double com o a distância mínima para atravessar o grafo
     *          se -1: o  grafo não é conexo
     */
    public double p3CalcularDiametroGrafo() {
        //ArrayList<User> usersList = (ArrayList<User>)usersGraph.vertices();

        //seleciona o primeiro vertice do grafo
        Iterator<User> it = usersGraph.vertices().iterator();
        User vOrig;
        if(it.hasNext()){
            vOrig = it.next();
        }else{
            return -1;
        }

        //verifica se o grafo é conexo
        LinkedList<User> resultBFS = GraphAlgorithmsAdjMatrix.BFS(usersGraph,vOrig);
        if(resultBFS == null || resultBFS.size() != usersGraph.numVertices()){
            return -1;
        }

        //matriz de custo/distância dos caminhos mínimos
        AdjacencyMatrixGraph<User, Double>graphTransitiveClosure =
                GraphAlgorithmsAdjMatrix.transitiveClosureWithEdgeWeights(usersGraph);

        double max = 0;
        Double edgeViVj;
        //encontra o máximo de todos os caminhos mínimos
        for (User vi : graphTransitiveClosure.vertices()) {
            for (User vj : graphTransitiveClosure.vertices()) {
                edgeViVj = graphTransitiveClosure.getEdge(vi, vj);
                if (edgeViVj != null && edgeViVj > max) {
                    max = edgeViVj;
                }
            }
        }
        return max;
    }



    public LinkedList<User> p4CalcularAmigosProximos(User userSelecionado, int nFronteiras){
        Country userCountry = checkVertexByCapitalName(userSelecionado.getCity());
        if (userCountry == null) {
            return null;
        }
        LinkedList<Country> paisesProximos = BreadthFirstSearch(countriesGraph, userCountry, nFronteiras);
        System.out.println(paisesProximos);
        LinkedList<User> usersProximos = new LinkedList<>();
        for(User u : usersGraph.vertices()){
            for(Country c : paisesProximos){
                if(u.getCity().equalsIgnoreCase(c.getCapital())){
                    usersProximos.add(u);
                }
            }
        }


        //sort users by City
        usersProximos.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getCity() != null && o2.getCity() != null) {
                   return o1.getCity().compareTo(o2.getCity());
                }else
                    return 0;
            }
        });

        return usersProximos;

    }


    /**
     * Performs breadth-first search of a Graph starting in a Vertex and stops after numVertLimit vertices
     * @param g Graph instance
     * @param vert information of the Vertex that will be the source of the search
     * @param numVertLimit int that limits the number of vertices the BFS will visit
     * @return qbfs a queue with the vertices of breadth-first search
     */
    //equivalente ao BFS de GraphAlgorithms mas com um limitador do número de ciclos a executar
    public static<V,E> LinkedList<V> BreadthFirstSearch(Graph<V,E> g, V vert, int numVertLimit){
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


    /**
     * Valida se existe um vértice no grafo dos Users com este userId
     * @param userId String com o nome/id do utilizador
     * @return Retorna o vértice User, caso exista. Retorna null se não existir no grafo carregado.
     */
    public User checkVertexByUserId(String userId){
        for(User u : usersGraph.vertices()){
            if (u.getUserId().equalsIgnoreCase(userId)){
                return u;
            }
        }
        return null;
    }
    /**
     * Valida se existe um vértice no grafo de Countries com esta capital
     * @param capitalName String com a designação do país
     * @return Retorna o vértice Country, caso exista. Retorna null se não existir.
     */
    public Country checkVertexByCapitalName(String capitalName){
        for(Country c : countriesGraph.vertices()){
            if (c.getCapital().equalsIgnoreCase(capitalName)){
                return c;
            }
        }
        return null;
    }


    /**
     * Valida se o vértice existe no grafo atualmente carregado
     * @param user vertice a verificar
     * @return true se existir no grafo
     *          false se não existir no grafo
     */
    public boolean checkVertexExists (User user){
        return usersGraph.checkVertex(user);
    }

    /**
     * Valida se o vértice existe no grafo atualmente carregado
     * @param country vertice a verificar
     * @return true se existir no grafo
     *         false se não existir no grafo
     */
    public boolean checkVertexExists (Country country){
        return countriesGraph.validVertex(country);
    }


    //only to be used by unit tests
    private void setCountriesGraph(Graph<Country, String> countriesGraph) {
        this.countriesGraph = countriesGraph;
    }

    private void setUsersGraph(AdjacencyMatrixGraph<User, Double> usersGraph) {
        this.usersGraph = usersGraph;
    }
}
