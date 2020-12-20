
package PL;

import java.util.List;

/**
 *
 * @author DEI-ESINF
 */
public class Utils {
    public static <E extends Comparable<E>> Iterable<E> sortByBST(List<E> listUnsorted){
        AVL<E> newTree = new AVL<>();

        for (E e : listUnsorted){
            newTree.insert(e);
        }

        return newTree.inOrder();
    }    
}
