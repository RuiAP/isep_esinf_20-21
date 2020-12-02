package TP2.model;

import graphbase.Graph;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class MetodosP4Test {



    @Test
    void testlimitedBreadthFirstSearch() {
        System.out.println("testlimitedBreadthFirstSearch");


        Graph<String,String> completeMap = new Graph<>(false);
        Graph<String,String> incompleteMap = new Graph<>(false);

        completeMap.insertVertex("Porto");
        completeMap.insertVertex("Braga");
        completeMap.insertVertex("Vila Real");
        completeMap.insertVertex("Aveiro");
        completeMap.insertVertex("Coimbra");
        completeMap.insertVertex("Leiria");

        completeMap.insertVertex("Viseu");
        completeMap.insertVertex("Guarda");
        completeMap.insertVertex("Castelo Branco");
        completeMap.insertVertex("Lisboa");
        completeMap.insertVertex("Faro");

        completeMap.insertEdge("Porto","Aveiro","A1",75);
        completeMap.insertEdge("Porto","Braga","A3",60);
        completeMap.insertEdge("Porto","Vila Real","A4",100);
        completeMap.insertEdge("Viseu","Guarda","A25",75);
        completeMap.insertEdge("Guarda","Castelo Branco","A23",100);
        completeMap.insertEdge("Aveiro","Coimbra","A1",60);
        completeMap.insertEdge("Coimbra","Lisboa","A1",200);
        completeMap.insertEdge("Coimbra","Leiria","A34",80);
        completeMap.insertEdge("Aveiro","Leiria","A17",120);
        completeMap.insertEdge("Leiria","Lisboa","A8",150);

        completeMap.insertEdge("Aveiro","Viseu","A25",85);
        completeMap.insertEdge("Leiria","Castelo Branco","A23",170);
        completeMap.insertEdge("Lisboa","Faro","A2",280);

        incompleteMap = completeMap.clone();

        incompleteMap.removeEdge("Aveiro","Viseu");
        incompleteMap.removeEdge("Leiria","Castelo Branco");
        incompleteMap.removeEdge("Lisboa","Faro");


        System.out.println("Test BreadthFirstSearch");

        assertTrue("Should be null if vertex does not exist", MetodosP4.limitedBreadthFirstSearch(completeMap,"LX", Integer.MAX_VALUE)==null);

        LinkedList<String> path = MetodosP4.limitedBreadthFirstSearch(incompleteMap, "Faro",Integer.MAX_VALUE);

        assertTrue("Should be just one", path.size()==1);

        Iterator<String> it = path.iterator();
        assertTrue("it should be Faro", it.next().compareTo("Faro")==0);

        path = MetodosP4.limitedBreadthFirstSearch(incompleteMap, "Porto", Integer.MAX_VALUE);
        assertTrue("Should give seven vertices ", path.size()==7);
        path = MetodosP4.limitedBreadthFirstSearch(incompleteMap, "Viseu", Integer.MAX_VALUE);
        assertTrue("Should give 3 vertices", path.size()==3);



        //--------------------------------------------


        //if "serches" are limited to 3 then should return only 3+1 vertices (vOrig plus 3 found vertices)
        path = MetodosP4.limitedBreadthFirstSearch(incompleteMap, "Porto", 3);
        assertTrue("Should give only 4 vertices ", path.size()==4);

        //if "searches" are limited to 0 then it return only the origin vertice (size == 1)
        path = MetodosP4.limitedBreadthFirstSearch(incompleteMap, "Porto", 0);
        assertTrue("Should give only 4 vertices ", path.size()==1);


    }


}