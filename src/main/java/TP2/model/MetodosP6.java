package TP2.model;

import graph.AdjacencyMatrixGraph;
import graphbase.Graph;

import java.util.*;

public class MetodosP6 {


    public MetodosP6(){
    }



    /**
     * Seleciona o caminho mais curto a partir de uma lista de caminhos possiveis
     * @param graph grafo ao qual pertencem os caminhos
     * @param possiblePaths lista de possiveis caminhos
     * @param <V> vertice
     * @return Lista contendo o caminho mais curto (dos caminhos possiveis)
     */
    public static <V> LinkedList<V> selecionaCaminhomaisCurto(AdjacencyMatrixGraph<V,Double> graph,
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
    public static <V> double calcularDistanciaCaminho(AdjacencyMatrixGraph<V,Double> graph, LinkedList<V> path){

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
    public static <E> List<List<E>> calcularPermutacoesPossiveis(List<E> original) {
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
     * Devolve lista, de tamanho "numCidades", ordenada por paises onde há mais amigos do user que foi passado por parametro
     * @param user
     * @param numCidades quantidade de cidades(aka objetos Country) a incluir na lista
     * @return lista com os n paises (ordem decrescente) que têm mais amigos do User
     */
    public static LinkedList<Country> paisesComMaisAmigosPorUser (AdjacencyMatrixGraph<User,Double> usersGraph, Graph<Country, String> countriesGraph, User user, int numCidades){

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
    public static LinkedList<User> filtrarUsersPorCidade(Iterable<User> iterableUserList, String strCidade){
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

}
