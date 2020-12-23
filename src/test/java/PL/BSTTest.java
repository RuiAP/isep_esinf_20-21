
package PL;

import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author DEI-ESINF
 */
public class BSTTest {
    Integer[] arr = {20,15,10,13,8,17,40,50,30,7};
    int[] height={0,1,2,3,3,3,3,3,3,4};
    Integer[] inorderT= {7,8,10,13,15,17,20,30,40,50};
    Integer[] preorderT= {20, 15, 10, 8, 7, 13, 17, 40, 30, 50};
    Integer[] posorderT = {7, 8, 13, 10, 17, 15, 30, 50, 40, 20};
    
    BST<Integer> instance;    
    
    public BSTTest() {
    }
    
    @Before
    public void setUp(){
        instance = new BST();
        for(int i :arr)
            instance.insert(i);        
    }    
/**
     * Test of size method, of class BST.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        assertEquals("size should be = 10",instance.size(), arr.length);
        
        BST<String> sInstance = new BST();
        assertEquals("size should be = 0",sInstance.size(), 0);
        sInstance.insert("A");
        assertEquals("size should be = 1",sInstance.size(), 1);
        sInstance.insert("B");
        assertEquals("size should be = 2",sInstance.size(), 2);
        sInstance.insert("A");
        assertEquals("size should be = 2",sInstance.size(), 2);
    }
   
    /**
     * Test of insert method, of class BST.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        int arr[] = {20,15,10,13,8,17,40,50,30,20,15,10};
        BST<Integer> instance = new BST();
        for (int i=0; i<9; i++){            //new elements
            instance.insert(arr[i]);
            assertEquals("size should be = "+(i+1),instance.size(), i+1);
        }
        for(int i=9; i<arr.length; i++){    //duplicated elements => same size
            instance.insert(arr[i]);
            assertEquals("size should be = 9",instance.size(), 9);
        }
    }
    /**
     * Test of remove method, of class BST.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");

        int qtd=arr.length;
        instance.remove(999);

        assertEquals("size should be = "+qtd, instance.size(), qtd);
        for (int i=0; i<arr.length; i++){
            instance.remove(arr[i]);
            qtd--;
            assertEquals("size should be = "+qtd, qtd,instance.size());
        }
        
        instance.remove(999);
        assertEquals("size should be = 0", 0,instance.size());
    }
/**
     * Test of isEmpty method, of class BST.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isempty");
        
        assertFalse("the BST should be NOT empty", instance.isEmpty());        
        instance = new BST();
        assertTrue("the BST should be empty",instance.isEmpty());        

        instance.insert(11);
        assertFalse("the BST should be NOT empty", instance.isEmpty());
        
        instance.remove(11);
        assertTrue("the BST should be empty", instance.isEmpty());
    }
/**
     * Test of height method, of class BST.
     */
    @Test
    public void testHeight() {
        System.out.println("height");

        instance = new BST();
        assertEquals("height should be = -1", instance.height(), -1);
        for(int idx=0; idx<arr.length; idx++){
            instance.insert(arr[idx]);
            assertEquals("height should be = "+height[idx], instance.height(), height[idx]);

        }
        instance = new BST();
        assertEquals("height should be = -1", instance.height(), -1);
    }
    /**
     * Test of smallestelement method, of class TREE.
     */
    @Test
    public void testSmallestElement() {
        System.out.println("smallestElement");
        assertEquals(new Integer(7), instance.smallestElement());
        instance.remove(7);
        assertEquals(new Integer(8), instance.smallestElement());
        instance.remove(8);
        assertEquals(new Integer(10), instance.smallestElement());
    }

    /**
     * Test of smallestelement method, of class TREE.
     */
    @Test
    public void testBiggestElement() {
        System.out.println("biggestElement");
        assertEquals(new Integer(50), instance.biggestElement());
        instance.remove(50);
        assertEquals(new Integer(40), instance.biggestElement());
        instance.remove(40);
        assertEquals(new Integer(30), instance.biggestElement());
    }
/**
     * Test of processBstByLevel method, of class TREE.
     */
    @Test
    public void testProcessBstByLevel() {
        System.out.println("processbstbylevel");
        Map<Integer,List<Integer>> expResult = new HashMap();
        expResult.put(0, Arrays.asList(new Integer[]{20}));
        expResult.put(1, Arrays.asList(new Integer[]{15,40}));
        expResult.put(2, Arrays.asList(new Integer[]{10,17,30,50}));
        expResult.put(3, Arrays.asList(new Integer[]{8,13}));
        expResult.put(4, Arrays.asList(new Integer[]{7}));
        
        Map<Integer,List<Integer>> result = instance.nodesByLevel();
        
        for(Map.Entry<Integer,List<Integer>> e : result.entrySet())
            assertEquals(expResult.get(e.getKey()), e.getValue());
    }    
   

/**
     * Test of inOrder method, of class BST.
     */
    @Test
    public void testInOrder() {
        System.out.println("inOrder");
        List<Integer> lExpected = Arrays.asList(inorderT);
        assertEquals("inOrder should be "+lExpected.toString(), lExpected, instance.inOrder());
    }
/**
     * Test of preOrder method, of class BST.
     */
    @Test
    public void testpreOrder() {
        System.out.println("preOrder");
        List<Integer> lExpected = Arrays.asList(preorderT);
        assertEquals("preOrder should be "+lExpected.toString(), lExpected, instance.preOrder());
    }
/**
     * Test of posOrder method, of class BST.
     */
    @Test
    public void testposOrder() {
        System.out.println("posOrder");
        List<Integer> lExpected = Arrays.asList(posorderT);
        assertEquals("posOrder should be "+lExpected.toString(), lExpected, instance.posOrder());
    }
    @Test
    public void testFind() {
        System.out.println("testFind");

        //testar find na raiz
        Integer expectedResult = new Integer (20);
        assertEquals("Result should be:"+expectedResult,expectedResult,instance.find(instance.root, expectedResult).getElement());

        //testar find na subárvore esquerda
        expectedResult = new Integer (7);
        assertEquals("Result should be:"+expectedResult,expectedResult,instance.find(instance.root, expectedResult).getElement() );

        //testar find na subárvore direita
        expectedResult = new Integer (50);
        assertEquals("Result should be:"+expectedResult,expectedResult,instance.find(instance.root, expectedResult).getElement() );

        //testar find com elemento inexistente na árvore
        expectedResult = null;
        assertEquals("Result should be: "+expectedResult,expectedResult,instance.find(instance.root,new Integer(1111)) );
        assertEquals("Result should be: "+expectedResult,expectedResult,instance.find(instance.root,null) );
        assertEquals("Result should be: "+expectedResult,expectedResult,instance.find(null, new Integer(20)) );

    }

    @Test
    public void testMaxDistance() {
        System.out.println("testMaxDistance");
        int expectedResult = 6;
        assertEquals("Result should be:", expectedResult, instance.maxDistance());

        //diminuindo a altura do ramo direito
        instance.remove(50);
        instance.remove(30);
        expectedResult = 5;
        assertEquals("Result should be:", expectedResult, instance.maxDistance());

        //fazendo com que a maior distancia não inclua a raiz
        instance.insert(18);
        instance.insert(19);
        expectedResult = 6;
        assertEquals("Result should be:", expectedResult, instance.maxDistance());

        //se a àrvore estiver null
        instance = new BST<>();
        expectedResult = -1;
        assertEquals("Result should be:", expectedResult, instance.maxDistance());

        //se a árvore for constituida só pela raiz
        instance.insert(1);
        expectedResult = 0;
        assertEquals("Result should be:", expectedResult, instance.maxDistance());

        //se a árvore tiver raiz e um node
        instance.insert(2);
        expectedResult = 1;
        assertEquals("Result should be:", expectedResult, instance.maxDistance());

    }

    @Test
    public void testCommonAncestor() {
        System.out.println("testCommonAncestor");

        //7 pertence à subàrvore esquerda e 50 à subàrvore direita -> ancestor = root(20)
        int expectedResult=20;
        int result = instance.commonAncestor(instance.root(), new Integer(7),new Integer(50)).getElement();
        assertEquals("Result should be: ", expectedResult, result);

        //7 e 13 pertencem ambos à subàrvore esquerda -> ancestor = 10
        expectedResult=10;
        result = instance.commonAncestor(instance.root(), new Integer(7),new Integer(13)).getElement();
        assertEquals("Result should be: ", expectedResult, result);

        //10 é antecessor comum de 10 e 13. Um dos elementos é o próprio antecessor comum
        expectedResult=10;
        result = instance.commonAncestor(instance.root(), new Integer(13),new Integer(10)).getElement();
        assertEquals("Result should be: ", expectedResult, result);

        //dois valores iguais devem retornar o próprio valor como antecessor comum
        expectedResult=30;
        result = instance.commonAncestor(instance.root(), new Integer(30),new Integer(30)).getElement();
        assertEquals("Result should be: ", expectedResult, result);

        //se um dos valores não pertencer à arvore deve retornar null
        assertNull(instance.commonAncestor(instance.root(), new Integer(79),new Integer(125)) ); //2 elementos não pertencem
        assertNull(instance.commonAncestor(instance.root(), new Integer(79),new Integer(20)) ); //1º elemento nao pertence
        assertNull(instance.commonAncestor(instance.root(), new Integer(30),new Integer(79)) );  //2º elemento não pertence

        //se existem argumentos null deve retornar null
        assertNull(instance.commonAncestor(null, new Integer(7),new Integer(50)) );
        assertNull(instance.commonAncestor(instance.root(), new Integer(7),null) );
        assertNull(instance.commonAncestor(instance.root(), null,new Integer(50)) );


    }
    @Test
    public void testDistanceBetweenNodes() {
        System.out.println("testDistanceBetweenNodes");

        //se existirem argumentos null ou que não pertençam à àrvore deve retornar null
        int expectedResult = -1;
        int result = instance.distanceBetweenNodes(instance.root().getElement(), null);
        assertEquals("Result should be:", expectedResult, result);
        result = instance.distanceBetweenNodes(null, instance.root().getElement());
        assertEquals("Result should be:", expectedResult, result);
        result = instance.distanceBetweenNodes(99, instance.root().getElement());
        assertEquals("Result should be:", expectedResult, result);
        result = instance.distanceBetweenNodes(instance.root().getElement(),99);
        assertEquals("Result should be:", expectedResult, result);

        //distancia para a raiz deve ser zero
        expectedResult = 0;
        result = instance.distanceBetweenNodes(instance.root().getElement(), instance.root().getElement());
        assertEquals("Result should be:", expectedResult, result);

        //distancia entre a raiz e extremo direito
        expectedResult = 2;
        result = instance.distanceBetweenNodes( instance.root().getElement(), new Integer(50) );
        assertEquals("Result should be:", expectedResult, result);

        //distancia entre raiz e extremo esquerdo
        expectedResult = 4;
        result = instance.distanceBetweenNodes( instance.root().getElement(), new Integer(7) );
        assertEquals("Result should be:", expectedResult, result);

        //distancia entre extremo esquero e extremo direito
        expectedResult = 6;
        result = instance.distanceBetweenNodes( new Integer(7), new Integer(50) );
        assertEquals("Result should be:", expectedResult, result);

        //distancia entre dois nodes de uma subarvore
        expectedResult = 3;
        result = instance.distanceBetweenNodes( new Integer(7),new Integer(13) );
        assertEquals("Result should be:", expectedResult, result);
    }

    @Test
    public void testNodesAtMaxDistance() {
        System.out.println("testNodesAtMaxDistance");

        HashMap<Integer, ArrayList<Integer>> expectedMap = new HashMap<>();
        ArrayList<Integer> expectedNodesList = new ArrayList<>();
        int expectedMaxDist;

        HashMap<Integer, ArrayList<Integer>> resultMap;
        ArrayList<Integer> resultNodesList = new ArrayList<>();
        int resultMaxDist;


        //--------------------------------test distance---------------------------
        resultMap = instance.nodesAtMaxDistance();
        expectedMaxDist = 6;
        assertEquals("Max distance should be:", expectedMaxDist, resultMap.keySet().toArray()[0]);

        //removendo os 2 nodes mais à direita diminui a disntânca máxima
        instance.remove(30);
        instance.remove(50);
        expectedMaxDist = 5;
        resultMap = instance.nodesAtMaxDistance();
        assertEquals("Max distance should be:", expectedMaxDist, resultMap.keySet().toArray()[0]);

        //removendo o node mais à esquerda diminui a distancia máxima
        instance.remove(07);
        expectedMaxDist = 4;
        resultMap = instance.nodesAtMaxDistance();
        assertEquals("Max distance should be:", expectedMaxDist, resultMap.keySet().toArray()[0]);

        //inserindo nodes 18 e 19 (vão ser inseridos a partir do 17) só aumenta a distancia em 1 unidade
        instance.insert(18);
        instance.insert(19);
        expectedMaxDist = 5;
        resultMap = instance.nodesAtMaxDistance();
        assertEquals("Max distance should be:", expectedMaxDist, resultMap.keySet().toArray()[0]);


        //se a raiz for null deve retornar null
        instance = new BST<>();
        assertNull(instance.nodesAtMaxDistance() );

        //se a àrvore for só um node, deve retornar null
        instance = new BST<>();
        instance.insert(20);
        assertNull(instance.nodesAtMaxDistance() );


        //-------------------------------test nodes----------------------------

        instance = new BST();   //restores the BST instance
        for(int i :arr)
            instance.insert(i);

        expectedNodesList.add(7);
        expectedNodesList.add(30);
        expectedNodesList.add(7);
        expectedNodesList.add(50);
        expectedMaxDist = 6;
        expectedMap.put(expectedMaxDist,expectedNodesList);

        resultMap = instance.nodesAtMaxDistance();
        resultMaxDist = (int) resultMap.keySet().toArray()[0];
        resultNodesList = resultMap.get(resultMaxDist);

        for (int i=0; i<resultMap.values().size(); i++){
            assertEquals("Note at max distance should be:", expectedNodesList.get(i), resultNodesList.get(i));
        }
        //ao remover o 7 há 4 possiveis caminhos mais longos
        instance.remove(7);

        expectedNodesList.clear();
        expectedNodesList.add(8);
        expectedNodesList.add(30);
        expectedNodesList.add(8);
        expectedNodesList.add(50);
        expectedNodesList.add(13);
        expectedNodesList.add(30);
        expectedNodesList.add(13);
        expectedNodesList.add(50);
        expectedMaxDist = 5;
        expectedMap.put(expectedMaxDist,expectedNodesList);

        resultMap = instance.nodesAtMaxDistance();
        resultMaxDist = (int) resultMap.keySet().toArray()[0];
        resultNodesList = resultMap.get(resultMaxDist);

        for (int i=0; i<resultMap.values().size(); i++){
            assertEquals("Note at max distance should be:", expectedNodesList.get(i), resultNodesList.get(i));
        }

    }
}
