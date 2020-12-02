package TP2.controller;

import TP2.model.*;
import graph.AdjacencyMatrixGraph;
import graph.GraphAlgorithmsAdjMatrix;
import graphbase.Edge;
import graphbase.Graph;

import java.util.*;

public class Controller {
    private MetodosP1 cf;
    private Graph<Country, String> countriesGraph;
    private AdjacencyMatrixGraph<User, Double> usersGraph;

    public Controller(){
        //permite ao user ir direto para os outros pontos sem passar pelo 1
        cf = new MetodosP1(true);
        countriesGraph = cf.carregarGrafoCountries();
        usersGraph = cf.carregarGrafoUsers();
    }


    /**
     * Invoca métodos para carregar informação a partir dos ficheiros de texto e instanciar grafos para os users e países
     * @param selectSmallFiles boolean true para carregar ficheiros pequenos
     *                                  false para carregar ficheiros grandes.
     * @return boolean true se operação terminou com sucesso
     *                 false se existiu algum erro ou foi lançada alguma excepção
     */
    public boolean p1CriarGrafos(boolean selectSmallFiles) {

        if(selectSmallFiles){
            cf = new MetodosP1(true);
        }else{
            cf = new MetodosP1(false);
        }
        countriesGraph = cf.carregarGrafoCountries();
        usersGraph = cf.carregarGrafoUsers();
       return true; //falta validação para não dar um erro e uma confirmação ao mesmo tempo
    }

    /**
     * Calcula os amigos comuns a partir de uma lista de utilizadores
     * @param utilizadoresPopulares lista de utilizadores que possam ter amigos em comum
     * @return Set com os amigos que todos os utilizadores presentes na lista têm em comum
     */
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
        amigosComuns.removeAll(utilizadoresPopulares);
        return amigosComuns;
    }


    public ArrayList<User> calcularUtilizadoresPopulares(int nUtilizadoresPopulares) {
        return MetodosP2.calcularUtilizadoresPopulares(usersGraph, nUtilizadoresPopulares);
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
        //encontra o valor máximo na matriz
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


    /**
     * Devolve os amigos, do user passado por parametro, que se encontrem a menos de "nFronteiras" fronteiras de distancia
     * @param userSelecionado user a partir do qual se faz a pesquisa
     * @param nFronteiras quantidade de fronteiras que abranger a pesquisa
     * @return lista de users que se encontram a menos de "nFronteiras" fronteiras do "userSelecionado"
     */
    public LinkedList<User> p4CalcularAmigosProximos(User userSelecionado, int nFronteiras){
        Country userCountry = checkVertexByCapitalName(userSelecionado.getCity());
        if (userCountry == null) {
            return null;
        }
        LinkedList<Country> paisesProximos = MetodosP4.limitedBreadthFirstSearch(countriesGraph, userCountry, nFronteiras);
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
     * Não implementado. Trabalho individual.
     */
    public void p5(){
        //não implementado
    }


    /**
     *
     * @param user1
     * @param user2
     * @param numCidades
     * @param caminho
     * @return
     */
    public Object[] p6CaminhoTerrestre(User user1, User user2, int numCidades, LinkedList<Country> caminho) {
        caminho.clear();
        Object[] results = new Object[2];
        results[0] = caminho;
        results[1] = -1;

        //se algum dos users não pertencer ao grafo devolve distancia = -1
        if(user1 == null || user2 == null || user1.equals(user2)){
            return results;
        }

        //recupera o país de cada user
        Country countryUser1 = null, countryUser2 = null;
        for(Country c : countriesGraph.vertices()) {
            if (c.getCapital().equalsIgnoreCase(user1.getCity())) {
                countryUser1 = c;
            }if (c.getCapital().equalsIgnoreCase(user2.getCity())) {
                countryUser2 = c;
            }
        }
        //se o grafo de paises não tiver o pais dos users introduzidos, o método devolve distancia = -1
        if(countryUser1 == null || countryUser2 == null){
            return results;
        }


        //junta numa só lista as cidades intermédias para o user1 e user2
        LinkedHashSet<Country> cidadesAIncluir = new LinkedHashSet<>();
        cidadesAIncluir.addAll(MetodosP6.paisesComMaisAmigosPorUser(usersGraph, countriesGraph, user1,numCidades));     //valores sao muito repetidos. verificar teste unitario!
        cidadesAIncluir.addAll(MetodosP6.paisesComMaisAmigosPorUser(usersGraph, countriesGraph, user2,numCidades));



        //gera todas as permutações possiveis com as cidades a incluir
        LinkedList<Country> cidadesEmLista = new LinkedList<>(cidadesAIncluir);
        List<List<Country>> permutacoes =
                (List<List<Country>>) MetodosP6.calcularPermutacoesPossiveis(cidadesEmLista);


        //ver as permutações POSSIVEIS(têm que estar por ordem) desta lista e escolher a mais curta
        LinkedHashSet<LinkedList<Country>> allPossiblePaths = new LinkedHashSet<>();
        LinkedList<Country> path;

        //constroi todos os caminhos possiveis (Pais inicio + permutações + pais fim)
        for(List<Country> lc : permutacoes){
            path = new LinkedList<>();
            path.add(countryUser1);
            for(Country c : lc){
                if (c != countryUser1 && c!=countryUser2)
                    path.add(c);
            }
            path.addLast(countryUser2);
            allPossiblePaths.add(path);
        }


        //cria um grafo de matriz de adjacencias para os paises (a partir do grafo de mapa de adjacencias)
        AdjacencyMatrixGraph<Country, Double> countriesMatrixGraph = new AdjacencyMatrixGraph<>();
        for(Country c : countriesGraph.vertices()){
            countriesMatrixGraph.insertVertex(c);
        }
        for( Edge<Country,String> edge : countriesGraph.edges()){
            countriesMatrixGraph.insertEdge(edge.getVOrig(),edge.getVDest(),edge.getWeight());
        }

        //matriz de custos minimos
        countriesMatrixGraph = GraphAlgorithmsAdjMatrix.transitiveClosureWithEdgeWeights(countriesMatrixGraph);

        //caminho mais curto
        caminho = MetodosP6.selecionaCaminhomaisCurto(countriesMatrixGraph, allPossiblePaths);
        //distancia do caminho mais curto
        Double distance = MetodosP6.calcularDistanciaCaminho(countriesMatrixGraph, caminho);


        results[0] = caminho;
        results[1] = distance;
        return results;



        //----------------------------------------------------------------------------------------------------------
        //falta:
        //validar se as cidades estão no mesmo continente
        //depois, como valido a questão do terrestre? Têm que fazer fronteira?
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



}
