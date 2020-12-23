package PL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author DEI-ESINF
*/

public class BST<E extends Comparable<E>> implements BSTInterface<E> {
  
    
      /** Nested static class for a binary search tree node. */
    
      protected static class Node<E> {
        private E element;          // an element stored at this node
        private Node<E> left;       // a reference to the left child (if any)
        private Node<E> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e  the element to be stored
         * @param leftChild   reference to a left child node
         * @param rightChild  reference to a right child node
         */
        public Node(E e, Node<E> leftChild, Node<E> rightChild) {
          element = e;
          left = leftChild;
          right = rightChild;
        }

        // accessor methods
        public E getElement() { return element; }
        public Node<E> getLeft() { return left; }
        public Node<E> getRight() { return right; }

        // update methods
        public void setElement(E e) { element = e; }
        public void setLeft(Node<E> leftChild) { left = leftChild; }
        public void setRight(Node<E> rightChild) { right = rightChild; }
      } 

    //----------- end of nested Node class -----------

    protected Node<E> root = null;     // root of the tree

    /* Constructs an empty binary search tree. */
    public BST() {
        root = null;
    }

    /*
     * @return root Node of the tree (or null if tree is empty)
     */
    protected Node<E> root() {
        return root;
    }

    /*
     * Verifies if the tree is empty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /*
     * Inserts an element in the tree.
     */
    public void insert(E element) {
        if (element == null) {
            return;
        }
        //se a BST estiver vazia coloca-se o element na raiz
        if (this.isEmpty()) {
            root = new Node(element, null, null);
        } else {
            insert(element, root);
        }
    }

    private Node<E> insert(E element, Node<E> node) {
        //retorna o node criado
        if (node.getElement().compareTo(element) == 0) {
            return null;
        }

        if (node.getElement().compareTo(element) > 0) {
            if (node.getLeft() == null) {
                node.setLeft(new Node(element, null, null));
                return node.getLeft();
            }
            insert(element, node.getLeft());
        } else {
            if (node.getRight() == null) {
                node.setRight(new Node(element, null, null));
                return node.getRight();
            }
            insert(element, node.getRight());
        }
        return null;
    }

    /**
     * Removes an element from the tree maintaining its consistency as a Binary Search Tree.
     */
    public void remove(E element) {
        root = remove(element, root());
    }

    private Node<E> remove(E element, Node<E> node) {

        if (node == null) {
            return null;    //throw new IllegalArgumentException("Element does not exist");
        }
        if (element.compareTo(node.getElement()) == 0) {
            // node is the Node to be removed
            if (node.getLeft() == null && node.getRight() == null) { //node is a leaf (has no childs)
                return null;
            }
            if (node.getLeft() == null) {   //has only right child
                return node.getRight();
            }
            if (node.getRight() == null) {  //has only left child
                return node.getLeft();
            }
            E min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove(min, node.getRight()));
        } else if (element.compareTo(node.getElement()) < 0)
            node.setLeft(remove(element, node.getLeft()));
        else
            node.setRight(remove(element, node.getRight()));

        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     * @return number of nodes in the tree
     */
    public int size() {

        if(this.isEmpty()){
            return 0;
        }
        else{
            return size(root);
        }
    }
    
    private int size(Node<E> node){
        int size = 1;

        if(node.getLeft() != null){
            size += size(node.getLeft());
        }
        if(node.getRight() !=null){
            size +=size(node.getRight());
        }
        return size;
    }
    
    /**
    * Returns the height of the tree
    * @return height 
    */
    public int height(){
            if (this.isEmpty()) {
                return -1;
            }else{
               return height(root);
            }
    }

    /**
    * Returns the height of the subtree rooted at Node node.
    * @param node A valid Node within the tree
    * @return height 
    */  
    protected int height(Node<E> node){
        if(node == null){ return 0;}
        int leftHeight = 0;
        int rightHeight = 0;

        if (node.getLeft()!= null) {
            leftHeight++;
            leftHeight += height(node.getLeft());
        }

        if (node.getRight()!= null) {
           rightHeight++;
           rightHeight += height(node.getRight());
        }

            return Math.max(rightHeight, leftHeight);
    }


    /**
     * Distance between two nodes of the same tree
     * @param element1
     * @param element2
     * @return number of edges between two nodes
     *          or
     *         -1 if argument are null or element don't exist in the tree
     */
    public int distanceBetweenNodes(E element1, E element2){

        if (element1 == null || element2 == null
                || findElement(element1) == null
                || findElement(element2) == null){
            return -1;
        }
        int distance1 = depth(element1)-1; //distancia da root ao node1
        int distance2 = depth(element2)-1; //distancia da root ao node2

        Node<E> ancestor = commonAncestor(this.root(), element1, element2);

        //se estão em subàrvores diferentes: distance1+distance2
        if(ancestor.equals(this.root())){
            return distance1+distance2;
        }
        //se estão na mesma subarvore, temos que tirar a distancia do antecessor á raiz porque não faz parte do percurso
        else{
            int distanceAncestor = depth(ancestor.getElement()) -1;
            return distance1+distance2 - 2*distanceAncestor;
        }
    }

    /**
     * Return the maximum distance between any two nodes of the tree
     * @return
     */
    public int maxDistance() {

        if (this.isEmpty()) {
            return -1;
        }else
            return maxDistance(this.root());
    }

    /**
     * Returns the maximum distance between any two nodes of the subtree rooted at Node node (recursively)
     * @param node root of the subtree
     * @return int containing the maximum distance between any two nodes of the subtree
     */
    public int maxDistance(Node<E> node)
    {
        if (node == null){ return 0;}
        //caso a maxDistance inclua a root: maxDistance = leftHeight+rightHeight
        int leftHeight = 0;
        int rightHeight = 0;

        if(node.getLeft()!= null)
        leftHeight = height(node.getLeft())+1;

        if(node.getRight() != null)
        rightHeight = height(node.getRight())+1;

        //caso a maxDistance nao inclua a root, temos de procurar a maxDistance da subarvore direita e/ou na subarvore esquerda
        int leftMaxdDistance = maxDistance(node.getLeft());
        int rightMaxDistance = maxDistance(node.getRight());

        //retorna a maior das duas possibilidades
        return Math.max(leftHeight + rightHeight , Math.max(leftMaxdDistance, rightMaxDistance));
    }

    /**
     * Finds the max distance between any two nodes of the tree.
     * @return Map<Integer, ArrayList<E> containing only one key, representing the maxDistance, and the value of that key
     *          is a list of Nodes (in pairs) that have the maxDistance between them.
     */
    public HashMap<Integer, ArrayList<E>> nodesAtMaxDistance() {

        HashMap<Integer, ArrayList<E>> results = new HashMap<>();
        ArrayList<E> nodesAtMaxDist = new ArrayList<>();

        //se root for null, ou só existir raiz devolve null
        if (this.isEmpty() || this.size() == 1) {
            return null;
        }

        int distance = 0;
        int maxDistance = 0;


        //lista com todos os nodes da àrvore
        ArrayList<E> nodesInTree = (ArrayList<E>) this.inOrder();



        //Compara todos os nodes da arvore entre eles para verificar quais são os que estão mais distantes
        for (int i = 0; i < nodesInTree.size(); i++) {
            for (int j = i; j < nodesInTree.size(); j++) {

                distance = distanceBetweenNodes(nodesInTree.get(i), nodesInTree.get(j));

                //quando encontra distancia igual ou maior que a distancia máxima há 2 opções:
                // se for  igual à existente adiciona os 2 novos nodes ao resultado
                if (distance == maxDistance) {
                    nodesAtMaxDist.add(nodesInTree.get(i));
                    nodesAtMaxDist.add(nodesInTree.get(j));
                }
                //se for maior que a existente, elimina os resultados antigos e adiciona os 2 novos nodes
                else if (distance > maxDistance) {
                    maxDistance = distance;
                    nodesAtMaxDist.clear();
                    nodesAtMaxDist.add(nodesInTree.get(i));
                    nodesAtMaxDist.add(nodesInTree.get(j));
                }
            }
        }

        results.put(maxDistance, nodesAtMaxDist);
        return results;

    }

    /**
     * Finds the first common ancestor of the nodes containing elementA and Element B (recursively)
     * @param node root of the tree/subtree to be searched
     * @param elementA
     * @param elementB
     * @return node of the first/lowest common ancestor of node A and node B,
     *          or
     *          null if element A and  element B are not present in the tree/subtree (or one of the arguments are null)
     */
    public Node<E> commonAncestor(Node<E> node,E elementA, E elementB){
        if (node == null ||elementA == null || elementB == null) { return null;}


        //se algum dos dois elementos for a raiz da subarvore a procurar (e outro elemento pertencer à àrvore),         //retirar findElement para fora do metodo recursivo para melhorar performance
        // então o elemento que é raiz é automaticamente antecessor comum
        if( (node.getElement().equals(elementA) && findElement(elementB) != null )
             || ( node.getElement().equals(elementB) && findElement(elementA) != null) )
        {
            return node;
        }

        //procura na subàrvove direita e esquerda do node
        Node<E> nodeLeft = commonAncestor(node.getLeft(), elementA,elementB);
        Node<E> nodeRight = commonAncestor(node.getRight(), elementA, elementB);

        //Se este nodo encontrar correspondencia tanto na direita como na esquerda então é antecessor dos dois
        if (nodeLeft != null && nodeRight != null){ return node; }
        //caso só econtre um passa os retorna o encontrado para manter a cadeia de recursividade
        if (nodeLeft != null){ return nodeLeft; }
        if (nodeRight != null){ return nodeRight; }

        return null;
    }


    /**
     * Return the depth of elementA in the tree starting at the root
     * @param elementA
     * @return number of edges between the root of the tree and the node with element A
     */
    public int depth(E elementA){
        if (this.root() == null){
            return -1;
        }else{
            return depth(this.root(), elementA);
        }
    }
    /**
     * Return the depth of elementA in the tree starting at node (root of the subtree)
     * @param node root of the subtree where the count starts
     * @param elementA
     * @return number of edges between the 'node' and the node with element A
     */
    public int depth(Node<E> node, E elementA){
        if (node == null){ return 0;}

            int x = 0;
            //se a depth esquerda for maior que zero então entra no if e o valor fica no x
            // se nao for, então é avaliada a depth da esquerda e o valor do x é sobrescristo
            if ((node.getElement().equals(elementA))
                    || (x = depth(node.getLeft(), elementA)) > 0
                    || (x = depth(node.getRight(), elementA)) > 0) {
                return x + 1;
            }
            return 0;
    }

    

        /**
        * Returns the smallest element within the tree.
        * @return the smallest element within the tree
        */
    public E smallestElement(){
        if (this.isEmpty()) {return null;}
        return smallestElement(root);
    } 
    
    protected E smallestElement(Node<E> node){

        if(node.getLeft() == null){
            return node.element;
        }else{
            return smallestElement(node.getLeft());
        }
    }


    /**
     * Returns the biggest element within the tree.
     * @return the biggest element within the tree
     */
    public E biggestElement(){
        if (this.isEmpty()) {return null;}
        return biggestElement(root);
    }

    protected E biggestElement(Node<E> node){

        if(node.getRight() == null){
            return node.element;
        }else{
            return biggestElement(node.getRight());
        }
    }

    /**
     * Method that finds and returns all the elements of the tree that are between 'lowerElement' and 'upperElement'
     * Calls the recursive method with the same name
     * @param lowerElement lower bound/limit
     * @param upperElement upper bound/limit
     * @return list containing all the elements that are between the upper and lower bounds.
     */
    public Iterable<E> findBetween(E lowerElement, E upperElement){
        if (lowerElement == null || upperElement == null){
            return null;
        }
        List<E> results = new ArrayList<>();
        findBetween(this.root(), lowerElement, upperElement, results);
        return results;
    }

    /**
     * Recursive method that fills the list 'results' with all the elements of the nodes of tree rooted at 'node' that
     * are between lowerElement and upperElement.
     * @param node roo of the tree/subtree to be searched
     * @param lowerElement lower limit or bound, based on which the elements are added do the list
     * @param upperElement upper limit or bound,  based on which the elements are added do the list
     * @param results List containing all the elements of the tree that are between lowerLimit and upperLimit
     */
  public void findBetween(Node<E> node, E lowerElement, E upperElement, List<E> results){
        if (node == null){
            return;
        }
        if(node.getElement().compareTo(lowerElement) >= 0 && node.getElement().compareTo(upperElement) <= 0){
            results.add(node.getElement());
        }
        //iterar por todos ou selecionar esquerda/direita consoante é maior/menor que um dos limites??
        findBetween(node.getLeft(), lowerElement, upperElement, results);
        findBetween(node.getRight(), lowerElement, upperElement, results);

    }


    /**
     * Searches for a specific element in the BST.
     * Returns the element if found, or null if not fund
     * @param element element to be searched for
     * @return the element if found, or null if not fund
     */
    public E findElement (E element){
        Node<E> result = find(this.root(),element);

        if (result != null){
            return result.getElement();
        }else
            return null;
    }

    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param element    the element to find
     * @return the Node that contains the Element, or null otherwise
     * 
     * This method despite not being essential is very useful. 
     * It is written here in order to be used by this class and its 
     * subclasses avoiding recoding.
     * So its access level is protected
     */
    protected Node<E> find(Node<E> node,E element){

        if (node == null || element == null){
            return null;
        }

        if(node.element.compareTo(element) == 0) {
            return node;
        }
        else if(node.element.compareTo(element)>0)
        {
            if(node.getLeft() != null) {
               return find(node.getLeft(), element);
            }
        }
        else if (node.element.compareTo(element)<0)
        {
            if(node.getRight() != null){
                return find(node.getRight(), element);
            }
        }
        return null;
    }
    

   /**
   * Returns an iterable collection of elements of the tree, reported in in-order.
   * @return iterable collection of the tree's elements reported in in-order
   */
    public Iterable<E> inOrder(){
        List<E> snapshot = new ArrayList<>();
        if (root!=null)
          inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;    
    }
  /**
   * Adds elements of the subtree rooted at Node node to the given
   * snapshot using an in-order traversal
   * @param node       Node serving as the root of a subtree
   * @param snapshot  a list to which results are appended
   */
    private void inOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }
  /**
   * Returns an iterable collection of elements of the tree, reported in pre-order.
   * @return iterable collection of the tree's elements reported in pre-order
   */
    public Iterable<E> preOrder(){
            List<E> snapshot = new ArrayList<E>();

            if(!this.isEmpty()){
                preOrderSubtree(root, snapshot);
            }

        return snapshot;
    }
  /**
   * Adds elements of the subtree rooted at Node node to the given
   * snapshot using an pre-order traversal
   * @param node       Node serving as the root of a subtree
   * @param snapshot  a list to which results are appended
   */
    private void preOrderSubtree(Node<E> node, List<E> snapshot) {

        if (node != null){
            snapshot.add(node.getElement());
            preOrderSubtree(node.getLeft(), snapshot);
            preOrderSubtree(node.getRight(), snapshot);
        }

    }
  /**
   * Returns an iterable collection of elements of the tree, reported in post-order.
   * @return iterable collection of the tree's elements reported in post-order
   */
    public Iterable<E> posOrder(){
        List<E> snapshot = new ArrayList<E>();

        if(!this.isEmpty()){
            posOrderSubtree(root, snapshot);
        }

        return snapshot;
    }
  /**
   * Adds positions of the subtree rooted at Node node to the given
   * snapshot using an post-order traversal
   * @param node       Node serving as the root of a subtree
   * @param snapshot  a list to which results are appended
   */
    private void posOrderSubtree(Node<E> node, List<E> snapshot) {

        if (node != null){
            posOrderSubtree(node.getLeft(), snapshot);
            posOrderSubtree(node.getRight(), snapshot);
            snapshot.add(node.getElement());
        }
    }
    
    /**
    * Returns a map with a list of nodes by each tree level.
    * @return a map with a list of nodes by each tree level
    */
    public Map<Integer,List<E>> nodesByLevel(){
        Map<Integer, List<E>> result = new HashMap<>();
        int level = 0;

        if (!this.isEmpty()) {
            List<E> levelResult = new ArrayList<>();
            levelResult.add(root.element);
            result.put(level, levelResult );

            processBstByLevel(root, result,level);
        }
        return result;
    }
    
    private void processBstByLevel(Node<E> node, Map<Integer,List<E>> result, int level){
        level++;
        List<E> levelResult;
        if (result.containsKey(level)){
            levelResult = result.get(level);
        }else{
            levelResult = new ArrayList<>();
        }

            if(node.getLeft() != null){
                levelResult.add(node.getLeft().element);
                processBstByLevel(node.getLeft(), result, level);
                result.put(level, levelResult);
            }

            if(node.getRight() != null){
                levelResult.add(node.getRight().element);
                processBstByLevel(node.getRight(), result, level);
                result.put(level, levelResult);
            }
    }

//#########################################################################
  
    /**
    * Returns a string representation of the tree.
    * Draw the tree horizontally 
    */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();    
    }

    private void toStringRec(Node<E> root, int level, StringBuilder sb){
        if(root==null)
             return;
        toStringRec(root.getRight(), level+1, sb);
        if (level!=0){
            for(int i=0;i<level-1;i++)
                sb.append("|\t");
            sb.append("|-------"+root.getElement()+"\n");
        }
        else
            sb.append(root.getElement()+"\n");
        toStringRec(root.getLeft(), level+1, sb);
    }
  
} //----------- end of BST class -----------




  

  