package TP2.model;

import graph.AdjacencyMatrixGraph;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class metodosP6Test {




    @Test
    void testCalcularDistanciaCaminho() {
        System.out.println("testCalcularDistanciaCaminho");

        AdjacencyMatrixGraph<String, Double> instance = new AdjacencyMatrixGraph<String,Double>();

        for(int i = 1 ; i < 5; i++)
            instance.insertVertex("Vert "+i);

        //distancia com caminho vazio deve ser 0
        LinkedList<String> path = new LinkedList<>();
        assertEquals(0, MetodosP6.calcularDistanciaCaminho(instance, path));

        instance.insertEdge("Vert 1", "Vert 2", 1.0);
        instance.insertEdge("Vert 1", "Vert 3", 2.0);
        instance.insertEdge("Vert 2", "Vert 4", 3.0);


        path.add("Vert 1");
        path.add("Vert 3");

        double distancia = MetodosP6.calcularDistanciaCaminho(instance, path);
        assertEquals(2,distancia, 0.01);

        path.add("Vert 2");
        distancia = MetodosP6.calcularDistanciaCaminho(instance, path);
        assertEquals(5, distancia, 0.01);

    }


    @Test
    void testSelecionaCaminhomaisCurto() {
        System.out.println("testSelecionaCaminhomaisCurto");

        AdjacencyMatrixGraph<String, Double> instance = new AdjacencyMatrixGraph<String,Double>();

        for(int i = 1 ; i < 6; i++)
            instance.insertVertex("Vert "+i);

        instance.insertEdge("Vert 1", "Vert 2", 2.0);
        instance.insertEdge("Vert 2", "Vert 4", 1.0);
        instance.insertEdge("Vert 4", "Vert 5", 1.0);
        instance.insertEdge("Vert 1", "Vert 3", 1.0);
        instance.insertEdge("Vert 3", "Vert 5", 4.0);


        LinkedHashSet<LinkedList<String>> possiblePaths = new LinkedHashSet<>();
        LinkedList<String> path1 = new LinkedList<>();
        path1.add("Vert 1");
        path1.add("Vert 2");
        path1.add("Vert 4");
        path1.add("Vert 5");
        LinkedList<String> path2 = new LinkedList<>();
        path2.add("Vert 1");
        path2.add("Vert 3");
        path2.add("Vert 5");
        possiblePaths.add(path1);
        possiblePaths.add(path2);

        //Path1 tem mais vertices mas o peso dos ramos é inferior a path2
        LinkedList<String> result = MetodosP6.selecionaCaminhomaisCurto(instance, possiblePaths);
        assertIterableEquals(path1,result);

        //aumenta o peso do ramo, volta a testar, e retorna o peso do ramo ao valor original
        instance.removeEdge("Vert 4", "Vert 5");
        instance.insertEdge("Vert 4", "Vert 5", 3.0);
        result = MetodosP6.selecionaCaminhomaisCurto(instance, possiblePaths);
        assertIterableEquals(path2,result);
        instance.removeEdge("Vert 4", "Vert 5");
        instance.insertEdge("Vert 4", "Vert 5", 1.0);


        //Se existir alguma lista com origem diferente devolve null
        LinkedList<String> path3 = new LinkedList<>();
        path3.add("Vert 2");
        path3.add("Vert 4");
        path3.add("Vert 5");
        possiblePaths.add(path3);
        result = MetodosP6.selecionaCaminhomaisCurto(instance, possiblePaths);
        assertNull(result);

        //Se existir alguma lista com destino diferente devolve null
        path3.addFirst("Vert 1");
        path3.remove("Vert 5");
        result = MetodosP6.selecionaCaminhomaisCurto(instance, possiblePaths);
        assertNull(result);

        //se for passado uma lista vazia de caminhos devolve null
        path3.clear();
        result = MetodosP6.selecionaCaminhomaisCurto(instance, possiblePaths);
        assertNull(result);

    }

    @Test
    void testCalcularPermutacoesPossiveis() {
        System.out.println("calcularPermutacoesPossiveisTest");
        LinkedList<String> original = new LinkedList<>();
        List<List<String>> expectedResult = new LinkedList<>();
        expectedResult.add(original);
        List<List<String>> result = new LinkedList<>();

        //se a lista passado está vazia, devolve lista vazia dentro da lista total
        result = MetodosP6.calcularPermutacoesPossiveis(original);
        assertIterableEquals(expectedResult, result);
        expectedResult.remove(original);

        //avalia todas as permutações de A B C
        original.add("A");
        original.add("B");
        original.add("C");

        LinkedList<String> result1 = new LinkedList<>();
        result1.add("A");
        result1.add("B");
        result1.add("C");
        LinkedList<String> result2 = new LinkedList<>();
        result2.add("A");
        result2.add("C");
        result2.add("B");
        LinkedList<String> result3 = new LinkedList<>();
        result3.add("B");
        result3.add("A");
        result3.add("C");
        LinkedList<String> result4 = new LinkedList<>();
        result4.add("B");
        result4.add("C");
        result4.add("A");
        LinkedList<String> result5 = new LinkedList<>();
        result5.add("C");
        result5.add("A");
        result5.add("B");
        LinkedList<String> result6 = new LinkedList<>();
        result6.add("C");
        result6.add("B");
        result6.add("A");

        expectedResult.add(result1);
        expectedResult.add(result2);
        expectedResult.add(result3);
        expectedResult.add(result4);
        expectedResult.add(result5);
        expectedResult.add(result6);

        result = MetodosP6.calcularPermutacoesPossiveis(original);
        boolean actualResutl =  result.containsAll(expectedResult) && expectedResult.containsAll(result);
        assertTrue(actualResutl);

    }

    @Test
    void testFiltrarUsersPorCidade() {
        System.out.println("testFiltrarUsersPorCidade");

        LinkedList<User> listaUsers = new LinkedList<>();
        LinkedList<User> expectedResult = new LinkedList<>();
        User user1 = new User ("u1", 27, "brasilia");
        User user33 = new User ("u33", 48, "brasilia");
        User user29 = new User ("u29",13,"santiago");
        User user6 = new User ("u6",51,"paramaribo");

        LinkedList<User> result = new LinkedList<>();

        //se a lista passado por parametro estiver vazia devolve uma lista vazia
        result = MetodosP6.filtrarUsersPorCidade(listaUsers, "brasilia");
        assertEquals(result, expectedResult); //expectedResult = emptyList

        //se não exister users dessa cidade devolve uma lista vazia
        listaUsers.add(user1);
        listaUsers.add(user33);
        listaUsers.add(user29);
        listaUsers.add(user6);
        result = MetodosP6.filtrarUsersPorCidade(listaUsers, "porto");
        assertEquals(expectedResult, result); //expectedResult = emptyList

        //devolve um user corretamente
        expectedResult.add(user29);
        result = MetodosP6.filtrarUsersPorCidade(listaUsers, "santiago");
        assertEquals(expectedResult, result);

        //devolve 2 users corretamente (pela ordem da lista inicial)
        expectedResult.remove(user29);
        expectedResult.add(user1);
        expectedResult.add(user33);
        result = MetodosP6.filtrarUsersPorCidade(listaUsers, "brasilia");
        assertEquals(expectedResult, result);
    }

    @Test
    void testPaisesComMaisAmigosPorUser() {


    }



}