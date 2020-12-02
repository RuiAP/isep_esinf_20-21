package TP2;

import TP2.model.User;
import graph.AdjacencyMatrixGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;


class ControllerTest {


    Controller controller = new Controller();


    public ControllerTest(){
    }



    @Test
    void p1CriarGrafosTest() {
    }

    @Test
    void p2CalcularAmigosComunsTest() {
    }

    @Test
    void p3CalcularDiametroGrafoTest() {
    }

    @Test
    void p4CalcularAmigosProximosTest() {

    }
    @Test
    void p6CaminhoTerrestreTest() {
    }

    @Test
    void breadthFirstSearchTest() {
    }

    @Test
    void checkVertexByUserIdTest() {
    }

    @Test
    void checkVertexByCapitalNameTest() {
    }

    @Test
    void checkVertexExistsTest() {
    }

    @Test
    void testCheckVertexExistsTest() {
    }


    @Test
    void testP1CriarGrafosTest() {
    }

    @Test
    void testP2CalcularAmigosComunsTest() {
    }

    @Test
    void calcularUtilizadoresPopularesTest() {
        System.out.println("calcularUtilizadoresPopularesTest");
        //utiliza o grafo de users gerado, a partir de ficheiro susers.txt, no construtor do controller
        /*
        0,1,1,1,1,1,1,1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,    9       u1
        1,0,1,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,    6       u2
        1,1,0,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,    9       u3
        1,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    4       u4
        1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    2       u5
        1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    2       u6
        1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    3       u7
        1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,    4       u9
        0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    1       u10
        1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    4       u14
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,    1       u15
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,    1       u16
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,    1       u19
        1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    2       u20
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,    1       u21
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,    1       u23
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,1,    4       u24
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,    3       u25
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,    3       u26
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,    1       u27
        0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,    3       u28
        0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,    2       u29
        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,    3       u30
        0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,    3       u31
        1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,0,0,1,    5       u32
        0,0,1,0,0,0,0,1,0,0,1,1,1,0,1,1,1,0,0,0,0,0,1,1,1,0,    11      u33
         */

        ArrayList<User> result = new ArrayList<>();
        ArrayList<User> expectedResult = new ArrayList<>();

        //user com mais amizades é o u33
        User u33 = new User ("u33", 48, "brasilia");
        expectedResult.add(u33);
        result.add( controller.calcularUtilizadoresPopulares(1).get(0) );
        assertEquals(expectedResult,result);

        //users u33, u1 e u3 são os tres com mais amizades
        expectedResult.add(new User ("u1", 27, "brasilia"));
        expectedResult.add(new User ("u3", 20, "quito"));
    }



    @Test
    void testP4CalcularAmigosProximosTest() {
    }



    @Test
    void selecionaCaminhomaisCurtoTest() {
        System.out.println("selecionaCaminhomaisCurtoTest");

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
        LinkedList<String> result = controller.selecionaCaminhomaisCurto(instance, possiblePaths);
        assertIterableEquals(path1,result);

        //aumenta o peso do ramo, volta a testar, e retorna o peso do ramo ao valor original
        instance.removeEdge("Vert 4", "Vert 5");
        instance.insertEdge("Vert 4", "Vert 5", 3.0);
        result = controller.selecionaCaminhomaisCurto(instance, possiblePaths);
        assertIterableEquals(path2,result);
        instance.removeEdge("Vert 4", "Vert 5");
        instance.insertEdge("Vert 4", "Vert 5", 1.0);


        //Se existir alguma lista com origem diferente devolve null
        LinkedList<String> path3 = new LinkedList<>();
        path3.add("Vert 2");
        path3.add("Vert 4");
        path3.add("Vert 5");
        possiblePaths.add(path3);
        result = controller.selecionaCaminhomaisCurto(instance, possiblePaths);
        assertNull(result);

        //Se existir alguma lista com destino diferente devolve null
        path3.addFirst("Vert 1");
        path3.remove("Vert 5");
        result = controller.selecionaCaminhomaisCurto(instance, possiblePaths);
        assertNull(result);

        //se for passado uma lista vazia de caminhos devolve null
        path3.clear();
        result = controller.selecionaCaminhomaisCurto(instance, possiblePaths);
        assertNull(result);

    }

    @Test
    void calcularDistanciaCaminhoTest() {
        System.out.println("calcularDistanciaCaminhoTest");

        AdjacencyMatrixGraph<String, Double> instance = new AdjacencyMatrixGraph<String,Double>();

        for(int i = 1 ; i < 5; i++)
            instance.insertVertex("Vert "+i);

        //distancia com caminho vazio deve ser 0
        LinkedList<String> path = new LinkedList<>();
        assertEquals(0,controller.calcularDistanciaCaminho(instance, path));

        instance.insertEdge("Vert 1", "Vert 2", 1.0);
        instance.insertEdge("Vert 1", "Vert 3", 2.0);
        instance.insertEdge("Vert 2", "Vert 4", 3.0);


        path.add("Vert 1");
        path.add("Vert 3");

        double distancia = controller.calcularDistanciaCaminho(instance, path);
        assertEquals(2,distancia, 0.01);

        path.add("Vert 2");
        distancia = controller.calcularDistanciaCaminho(instance, path);
        assertEquals(5, distancia, 0.01);

    }

    @Test
    void calcularPermutacoesPossiveisTest() {
        System.out.println("calcularPermutacoesPossiveisTest");
        LinkedList<String> original = new LinkedList<>();
        List<List<String>> expectedResult = new LinkedList<>();
        expectedResult.add(original);
        List<List<String>> result = new LinkedList<>();

        //se a lista passado está vazia, devolve lista vazia dentro da lista total
        result = controller.calcularPermutacoesPossiveis(original);
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

        result = controller.calcularPermutacoesPossiveis(original);
        boolean actualResutl =  result.containsAll(expectedResult) && expectedResult.containsAll(result);
        assertTrue(actualResutl);

    }

    @Test
    void paisesComMaisAmigosPorUserTest() {
    }

    @Test
    void filtrarUsersPorCidadeTest() {
    }



    @Test
    void testCheckVertexByUserId() {
    }

    @Test
    void testCheckVertexByCapitalName() {
    }

    @Test
    void testCheckVertexExists1() {
    }

    @Test
    void testCheckVertexExists2() {
    }
}