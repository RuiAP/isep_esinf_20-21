package TP2;

import TP2.model.Country;
import TP2.model.User;
import graph.AdjacencyMatrixGraph;
import graphbase.Graph;

import java.util.Comparator;
import java.util.LinkedList;

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
       return true; //falta validação para não dar erro e confirmação ao mesmo tempo
    }


    public LinkedList<User> p2CalcularAmigosComuns(int nUtilizadoresPopulares) {




        throw new UnsupportedOperationException();
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




}
