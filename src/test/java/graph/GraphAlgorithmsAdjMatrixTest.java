
package graph;

import java.util.Iterator;
import java.util.LinkedList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author DEI-ESINF
 */
public class GraphAlgorithmsAdjMatrixTest {

    AdjacencyMatrixGraph <String, String> completeMap = new AdjacencyMatrixGraph<>();
    AdjacencyMatrixGraph <String, String> incompleteMap;
	
    public GraphAlgorithmsAdjMatrixTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
	
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
		
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

		completeMap.insertEdge("Porto", "Aveiro", "A1");
		completeMap.insertEdge("Porto", "Braga", "A3");
		completeMap.insertEdge("Porto", "Vila Real", "A4");
		completeMap.insertEdge("Viseu", "Guarda", "A25");
		completeMap.insertEdge("Guarda",  "Castelo Branco", "A23");
		completeMap.insertEdge("Aveiro", "Coimbra", "A1");
		completeMap.insertEdge("Coimbra", "Lisboa", "A1");
		completeMap.insertEdge("Coimbra",  "Leiria",  "A34");
		completeMap.insertEdge("Aveiro", "Leiria", "A17");
		completeMap.insertEdge("Leiria", "Lisboa", "A8");

		
		incompleteMap = (AdjacencyMatrixGraph<String, String>) completeMap.clone();
		
		completeMap.insertEdge("Aveiro", "Viseu", "A25");
		completeMap.insertEdge("Leiria", "Castelo Branco", "A23");
		completeMap.insertEdge("Lisboa", "Faro", "A2");

	}

	
	@Test
	public void testDFS() {
		System.out.println("Test of DFS");

		LinkedList<String> path;

		assertTrue("Should be null if vertex does not exist", GraphAlgorithmsAdjMatrix.DFS(completeMap, "LX")==null);

		path = GraphAlgorithmsAdjMatrix.DFS(incompleteMap, "Faro");

		assertTrue("Should be just one", path.size()==1);

		Iterator<String> it = path.iterator();

		assertTrue("it should be Faro", it.next().compareTo("Faro")==0);
		
		path = GraphAlgorithmsAdjMatrix.DFS(completeMap, "Porto");
		
		assertTrue("Should give all vertices ", path.size()==11);

		it = path.iterator();

		assertTrue("First in visit should be Porto", it.next().compareTo("Porto")==0);
		assertTrue("then Braga", it.next().compareTo("Braga")==0);
		assertTrue("then Vila Real", it.next().compareTo("Vila Real")==0);
		assertTrue("then Aveiro", it.next().compareTo("Aveiro")==0);

		assertTrue("then Coimbra", it.next().compareTo("Coimbra")==0);
		assertTrue("then Leiria", it.next().compareTo("Leiria")==0);
		assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco")==0);
		assertTrue("then Guarda", it.next().compareTo("Guarda")==0);
		assertTrue("then Viseu", it.next().compareTo("Viseu")==0);
		assertTrue("then Lisboa", it.next().compareTo("Lisboa")==0);
		assertTrue("then Faro", it.next().compareTo("Faro")==0);

		
		path = GraphAlgorithmsAdjMatrix.DFS(incompleteMap, "Viseu");
		
		assertTrue("Should give 3 vertices", path.size()==3);

		it = path.iterator();

		assertTrue("First in visit should be Viseu", it.next().compareTo("Viseu")==0);
		assertTrue("then Guarda", it.next().compareTo("Guarda")==0);
		assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco")==0);
	}

	@Test
	public void testBFS() {
		System.out.println("Test of BFS");

		LinkedList<String> path;

		assertTrue("Should be null if vertex does not exist", GraphAlgorithmsAdjMatrix.BFS(completeMap, "LX")==null);

		path = GraphAlgorithmsAdjMatrix.BFS(incompleteMap, "Faro");

		assertTrue("Should be just one", path.size()==1);

		Iterator<String> it = path.iterator();

		assertTrue("it should be Faro", it.next().compareTo("Faro")==0);

		
		path = GraphAlgorithmsAdjMatrix.BFS(completeMap, "Porto");
		
		assertTrue("Should give all vertices ", path.size()==11);

		it = path.iterator();

		assertTrue("First in visit should be Porto", it.next().compareTo("Porto")==0);
		assertTrue("then Braga", it.next().compareTo("Braga")==0);
		assertTrue("then Vila Real", it.next().compareTo("Vila Real")==0);
		assertTrue("then Aveiro", it.next().compareTo("Aveiro")==0);

		assertTrue("then Coimbra", it.next().compareTo("Coimbra")==0);
		assertTrue("then Leiria", it.next().compareTo("Leiria")==0);
		assertTrue("then Viseu", it.next().compareTo("Viseu")==0);
		assertTrue("then Lisboa", it.next().compareTo("Lisboa")==0);
		assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco")==0);
		assertTrue("then Guarda", it.next().compareTo("Guarda")==0);
		assertTrue("then Faro", it.next().compareTo("Faro")==0);

		
		path = GraphAlgorithmsAdjMatrix.BFS(incompleteMap, "Viseu");
		
		assertTrue("Should give 3 vertices", path.size()==3);

		it = path.iterator();

		assertTrue("First in visit should be Viseu", it.next().compareTo("Viseu")==0);
		assertTrue("then Guarda", it.next().compareTo("Guarda")==0);
		assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco")==0);
		
	}

	@Test
	public void testTransitiveClosure() {
		AdjacencyMatrixGraph <String, String> transitiveTestMap = new AdjacencyMatrixGraph<>();

		transitiveTestMap.insertVertex("Porto");
		transitiveTestMap.insertVertex("Braga");
		transitiveTestMap.insertVertex("Vila Real");
		transitiveTestMap.insertVertex("Aveiro");
		transitiveTestMap.insertVertex("Coimbra");

		transitiveTestMap.insertEdge("Porto", "Braga", "A1");
		transitiveTestMap.insertEdge("Braga", "Vila Real", "A1");
		transitiveTestMap.insertEdge("Vila Real", "Aveiro", "A1");
		transitiveTestMap.insertEdge("Vila Real", "Coimbra", "A1");
		transitiveTestMap.insertEdge("Aveiro", "Braga", "A1");
		transitiveTestMap.insertEdge("Coimbra", "Aveiro", "A1");

		System.out.println(transitiveTestMap);
		transitiveTestMap = GraphAlgorithmsAdjMatrix.transitiveClosure(transitiveTestMap,"A2");
		System.out.println(transitiveTestMap);



    	/*
		System.out.println(completeMap);
		//completeMap.insertEdge("Porto", "Lisboa", "A111");

		completeMap = GraphAlgorithmsAdjMatrix.transitiveClosure(completeMap, "A111");
		System.out.println(completeMap);

		 */
	}

	@Test
	public void testTransitiveClosureWithWeights() {


	}

}
