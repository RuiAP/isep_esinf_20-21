package TP2;

import TP2.model.Country;
import TP2.model.User;
import graph.AdjacencyMatrixGraph;
import graph.GraphAlgorithmsAdjMatrix;
import graphbase.Edge;
import graphbase.Graph;

import java.util.*;

public class Controller {
    private CarregarFicheiros cf;
    private Graph<Country, String> countriesGraph;
    private AdjacencyMatrixGraph<User, Double> usersGraph;

    public Controller(){
        //permite ao user ir direto para os outros pontos sem passar pelo 1
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
        amigosComuns.removeAll(utilizadoresPopulares);
        return amigosComuns;
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
     *
     * @param userSelecionado
     * @param nFronteiras
     * @return
     */
    public LinkedList<User> p4CalcularAmigosProximos(User userSelecionado, int nFronteiras){
        Country userCountry = checkVertexByCapitalName(userSelecionado.getCity());
        if (userCountry == null) {
            return null;
        }
        LinkedList<Country> paisesProximos = BreadthFirstSearch(countriesGraph, userCountry, nFronteiras);
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
        cidadesAIncluir.addAll(paisesComMaisAmigosPorUser(user1,numCidades));     //valores sao muito repetidos. verificar teste unitario!
        cidadesAIncluir.addAll(paisesComMaisAmigosPorUser(user2,numCidades));



        //gera todas as permutações possiveis com as cidades a incluir
        LinkedList<Country> cidadesEmLista = new LinkedList<>(cidadesAIncluir);
        List<List<Country>> permutacoes = (List<List<Country>>) calcularPermutacoesPossiveis(cidadesEmLista);


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
        caminho = selecionaCaminhomaisCurto(countriesMatrixGraph, allPossiblePaths);
        //distancia do caminho mais curto
        Double distance = calcularDistanciaCaminho(countriesMatrixGraph, caminho);


        results[0] = caminho;
        results[1] = distance;
        return results;



        //----------------------------------------------------------------------------------------------------------
        //falta:
        //validar se as cidades estão no mesmo continente
        //depois, como valido a questão do terrestre? Têm que fazer fronteira?
    }

    /**
     * Seleciona o caminho mais curto a partir de uma lista de caminhos possiveis
     * @param graph grafo ao qual pertencem os caminhos
     * @param possiblePaths lista de possiveis caminhos
     * @param <V> vertice
     * @return Lista contendo o caminho mais curto (dos caminhos possiveis)
     */
    public <V> LinkedList<V> selecionaCaminhomaisCurto(AdjacencyMatrixGraph<V,Double> graph,
                                                       LinkedHashSet<LinkedList<V>> possiblePaths){
        if(possiblePaths == null || possiblePaths.isEmpty() || graph == null) {return null;}
        V vOrig = null;
        V vDest = null;
        for(LinkedList<V> v : possiblePaths){
            vOrig = v.peekFirst();
            vDest = v.peekLast();
            break;
        }

        LinkedList<V> shortestPath = new LinkedList<>();
        double minDist = Double.MAX_VALUE;
        double pathDist = 0;
        for(LinkedList<V> path : possiblePaths){
            if(path.peekFirst() != vOrig || path.peekLast() != vDest){
                return null; //retorna null se existirem destinos ou origens diferentes
            }
            pathDist = calcularDistanciaCaminho(graph, path);
            if(pathDist < minDist){
                minDist = pathDist;
                shortestPath = path;
            }
        }

        return shortestPath;
    }

    /**
     * Devolve a distancia percorrida ao percorrer o caminho passado por parametro
     * @param graph grafo contendo a matriz com respetivos ramos pesados
     * @param path caminho a percorrer
     * @param <V> vertice
     * @return double com o distancia/peso total do caminho
     */
    public <V> double calcularDistanciaCaminho(AdjacencyMatrixGraph<V,Double> graph, LinkedList<V> path){

        double totalSize = 0;

        Iterator<V> it = path.iterator();
        V vAtual;
        V vSeguinte;
        while (it.hasNext()) {
            vAtual = it.next();
            if(!it.hasNext()){
                break;
            }
            vSeguinte = it.next();
            totalSize += graph.getEdge(vAtual, vSeguinte);
        }

        return totalSize;

    }

    /**
     * Gera uma lista com todas as permutações possiveis dos elementos passados por parametro
     * @param original
     * @param <E>
     * @return
     */
    public <E> List<List<E>> calcularPermutacoesPossiveis(List<E> original) {
        if (original.isEmpty()) {
            List<List<E>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        E firstElement = original.remove(0);
        List<List<E>> returnValue = new ArrayList<>();
        List<List<E>> permutations = calcularPermutacoesPossiveis(original);
        for (List<E> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    /**
     * Seleciona e devolve os n utilizadores (vértices) com mais amigos(vertices adjacentes).
     * @param nUtilizadoresPopulares quantidade de utilizadores a selecionar
     * @return Lista contendo os n utilizadores mais populares
     *         Lista pode ter size()< n se existirem menos de n vertices.
     */
    public ArrayList<User> calcularUtilizadoresPopulares(int nUtilizadoresPopulares){
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

    /**
     * Devolve lista, de tamanho "numCidades", ordenada por paises com mais amigos do user passado por parametro
     * @param user
     * @param numCidades quantidade de cidades(aka objetos Country) a incluir na lista
     * @return lista com os n paises (ordem decrescente) que têm mais amigos do User
     */
    public LinkedList<Country> paisesComMaisAmigosPorUser (User user,int numCidades){

        LinkedList<Country> paisesNoGrafo = new LinkedList<>();
        countriesGraph.vertices().forEach(paisesNoGrafo::add);

        paisesNoGrafo.sort(new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                double numberFriendsInC1 = filtrarUsersPorCidade(usersGraph.directConnections(user), o1.getCapital()).size();
                double numberFriendsInC2 = filtrarUsersPorCidade(usersGraph.directConnections(user), o2.getCapital()).size();

                if (numberFriendsInC2 > numberFriendsInC1){
                    return 1;
                }else if(numberFriendsInC2 < numberFriendsInC1){
                    return 1;
                }else{
                    return 0;
                }
            }
        });

        return new LinkedList<Country>(paisesNoGrafo.subList(0,numCidades));

    }

    /**
     * Seleciona, da lista recebida, os utilizadores que são de uma cidade especifica
     * @param iterableUserList lista de utilizadores a filtrar
     * @param strCidade cidade à qual os users devem pertencer para ser selecionados
     * @return lista com os users que residem na cidade escolhida
     */
    public LinkedList<User> filtrarUsersPorCidade(Iterable<User> iterableUserList, String strCidade){
        LinkedList<User> resultList = new LinkedList<>();

        Iterator<User> it = iterableUserList.iterator();
        while(it.hasNext()){
            User tempUser = it.next();
            if(tempUser.getCity().equalsIgnoreCase(strCidade)){
                resultList.add(tempUser);
            }
        }
        return resultList;
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


 




}
