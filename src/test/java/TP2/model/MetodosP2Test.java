package TP2.model;

import graph.AdjacencyMatrixGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MetodosP2Test {

    MetodosP1 cf = new MetodosP1(true);
    AdjacencyMatrixGraph<User, Double> usersGraphInstance;

    public MetodosP2Test(){
        usersGraphInstance = cf.carregarGrafoUsers();

    }


    @Test
    void testCalcularUtilizadoresPopulares() {
        System.out.println("testCalcularUtilizadoresPopulares");
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
        result.add( MetodosP2.calcularUtilizadoresPopulares(usersGraphInstance,1).get(0) );
        assertEquals(expectedResult,result);

        //users u33, u1 e u3 são os tres com mais amizades
        expectedResult.add(new User ("u1", 27, "brasilia"));
        expectedResult.add(new User ("u3", 20, "quito"));
    }


}