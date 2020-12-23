
package PL;

import javax.xml.soap.Text;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author DEI-ESINF
 */
public class TREE_WORDS extends BST<TextWord> {
    
    public void createTree() throws FileNotFoundException{
        Scanner readfile = new Scanner(new File("src/main/java/PL/xxx.xxx"));
        while(readfile.hasNextLine()){
            String[] pal = readfile.nextLine().split("(\\,)|(\\s)|(\\.)");
            for(String word : pal)
                if (word.length() > 0 )
                    insert(new TextWord(word, 1));
        }
        readfile.close();
    }

    /**
     * Inserts a new word in the tree, or increments the number of its occurrences.
       * @param element
     */
    @Override
    //Igual ao método super.insert mas necessário, caso contrário o metodo super.insert vai invocar o metodo "insert" da BST e nao o insert da TREE_WORDS
    public void insert(TextWord element){
        if (element == null) {
            return;
        }
        //se a BST estiver vazia coloca-se o element na raiz
        if (this.isEmpty()) {
            root = new Node(element, null, null);
        } else {
            this.insert(element, root);
        }
    }


    /**
     *
     * @param element
     * @param node
     * @return
     */
    //a unica diferença face ao super.insert é que aumenta a ocurrencia em vez de devolver null
    private Node<TextWord> insert(TextWord element, Node<TextWord> node){
        if (node.getElement().compareTo(element) == 0) {
            node.getElement().incOcorrences();
            return node;
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
     * Returns a map with a list of words for each occurrence found.
     * @return a map with a list of words for each occurrence found.
     */
    public Map<Integer,List<String>> getWordsOccurrences(){
        HashMap<Integer, List<String>> results = new HashMap<>();

        getWordsOccurrencesRec(this.root(), results);
        return results;

    }

    /**
     * Itereatres through the tree (starting at "node") and fills the map with the occurrences and respective list of words
     * @param node root of the subtree to be searched
     * @param results map with a list of words for each occurrence found
     */
    private void getWordsOccurrencesRec(Node<TextWord> node, Map<Integer,List<String>> results){

        if (node == null || results == null){ return; }

        List<String>listWords;
        int ocorrences = node.getElement().getOcorrences();

        //Se for a primeira vez que se econtra esta quantidade de occurencias cria uma lista nova e coloca-a no mapa.
        // Caso contrario recupera a lista respectiva a este numero de ocurrencias
        if (results.get(ocorrences) == null){
            listWords = new ArrayList<>();
            results.put(ocorrences, listWords);
        }
        else{
            listWords = results.get(ocorrences);
        }

        getWordsOccurrencesRec(node.getLeft(), results);
        //addiciona o elemento à lista de palavras
        listWords.add(node.getElement().getWord());
        //percorre a subàrvore esquerda e direita

        getWordsOccurrencesRec(node.getRight(), results);
    }

    /**
     * Creates a tree with all the possible electronic configurations and their number of repetitions
     * @return return the tree with possible configuration as elements
     */
    public TREE_WORDS countConfigRepetitions(){

        TREE_WORDS repeatedConfig = new TREE_WORDS();

        countConfigRepetitions(this.root(), repeatedConfig);
        return repeatedConfig;

    }

    /**
     * Recursive method to iterate through the tree and count repeated configurations
     * @param node
     * @param repeatedConfig
     */
    private void countConfigRepetitions(Node<TextWord> node, TREE_WORDS repeatedConfig){

        if (node == null){ return; }


        String[] partes = node.getElement().getWord().trim().split(" ");
        ArrayList<String> padraoConfig = new ArrayList<>(Arrays.asList(partes));
        padraoConfig.add (node.getElement().getWord());


        for(String str : padraoConfig){

            if( repeatedConfig.find(repeatedConfig.root(), new TextWord(str,0) )== null ){
                repeatedConfig.insert(new TextWord(str, 0));
            }else{
                repeatedConfig.find(repeatedConfig.root(), new TextWord(str,0)).getElement().incOcorrences(); //se ja existir aumentamos o numero de occurrencias
            }
        }

        countConfigRepetitions(node.getLeft(), repeatedConfig);
        countConfigRepetitions(node.getRight(), repeatedConfig);


    }

}
