
package PL;

import java.util.*;

/*
 * @author DEI-ESINF
 * @param <E>
 */

public class TREE<E extends Comparable<E>> extends BST<E>{
 
   /*
   * @param element A valid element within the tree
   * @return true if the element exists in tree false otherwise
   */   
    public boolean contains(E element) {
        if (find(this.root, element) == null){
            return false;
        }else{
            return true;
        }
    }

 
    public boolean isLeaf(E element){
        Node leaf = find(this.root, element);
        if(leaf != null && leaf.getRight() == null && leaf.getLeft() == null){
                return true;
        }else
            {
                return false;
        }
    }

   /*
   * build a list with all elements of the tree. The elements in the 
   * left subtree in ascending order and the elements in the right subtree 
   * in descending order. 
   *
   * @return    returns a list with the elements of the left subtree 
   * in ascending order and the elements in the right subtree is descending order.
   */
    public Iterable<E> ascdes(){
        List<E> result = new ArrayList<>();
        if(root == null) {return result;}
        ascSubtree(root.getLeft(), result);
        result.add(root.getElement());
        desSubtree(root.getRight(),result);
        return result;
    }

    private void ascSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        ascSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        ascSubtree(node.getRight(), snapshot);
    }
    
    private void desSubtree(Node<E> node, List<E> snapshot) {
        if (node != null){
            desSubtree(node.getRight(), snapshot);
            snapshot.add(node.getElement());
            desSubtree(node.getLeft(), snapshot);

        }

    }
   
    /**
    * Returns the tree without leaves.
    * @return tree without leaves
    */
    public BST<E> autumnTree() {
        TREE<E> newTree = new TREE<>();
        newTree.root = copyRec(this.root()) ;

        return newTree;
    }
    
    private Node<E> copyRec(Node<E> node){
        if(node == null || isLeaf(node.getElement())){ return null;}

        return new Node<E>(node.getElement(), copyRec((node.getLeft())),copyRec(node.getRight())  );

    }
    
    /**
    * @return the the number of nodes by level.
    */
    public int[] numNodesByLevel(){

        int result[] = new int[nodesByLevel().size()];
        for (int i = 0; i < nodesByLevel().size(); i++){
            result[i] = nodesByLevel().get(i).size();
        }
        return result;
    }
    
    private void numNodesByLevel(Node<E> node, int[] result, int level){
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
