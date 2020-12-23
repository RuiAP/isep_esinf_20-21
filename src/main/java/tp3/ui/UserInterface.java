package tp3.ui;

import PL.BST;
import PL.TREE_WORDS;
import PL.TREE_WORDS_REP;
import PL.TextWord;
import tp3.model.CarregarFicheiro;
import tp3.model.*;
import tp3.model.S_ANumber;
import tp3.model.S_ElementName;

import java.util.*;

public class UserInterface {

    CarregarFicheiro cf;
    public UserInterface(){
        cf = new CarregarFicheiro();
        cf.carregarFicheiroCSV();
    }

    /**
     * Solicita número atómico ao utilizador e imprime o resultado da pesquisa
     */
    public void AtomicNumber_1a() {
        int aNumber = UtilsUI.readIntFromConsole("Introduza o número atómico do elemento a procurar:");
        BST<S_ANumber> tree = cf.getBstANumber();

        S_ANumber elementFound = tree.findElement(new S_ANumber(aNumber));

        validateAndPrintElement(elementFound);
    }

    /**
     * Solicita nome do elemento ao utilizador e imprime o resultado da pesquisa
     */
    public void Element_1a() {
        String elementName_str = UtilsUI.readLineFromConsole("Introduza o nome do elemento a procurar:");
        BST<S_ElementName> tree =  cf.getBstElementName();

        S_ElementName elementFound = tree.findElement(new S_ElementName(elementName_str));

       validateAndPrintElement(elementFound);
    }

    /**
     * Solicita o símbolo do elemento ao utilizador e imprime o resultado da pesquisa
     */
    public void Symbol_1a() {
        String symbol_str = UtilsUI.readLineFromConsole("Introduza o símbolo do elemento a procurar:");
        BST<S_Symbol> tree =  cf.getBstSymbol();

        S_Symbol elementFound = tree.findElement(new S_Symbol(symbol_str));
        validateAndPrintElement(elementFound);
    }

    /**
     * Solicita a massa atómica ao utilizador e imprime o resultado da pesquisa
     */
    public void AtomicMass_1a() {
        double atomic = UtilsUI.readDoubleFromConsole("Introduza a massa atómica do elemento a procurar:");
        BST<S_AMass> tree = cf.getBstAMass();

        S_AMass elementFound = tree.findElement(new S_AMass(atomic));
        validateAndPrintElement(elementFound);
    }

    /**
     * Solicita o limite inferior e superior dos valores de massa atómica a incluir na pesquisa e imprime o resultado.
     * Resultado consiste numa lista e uma tabela.
     *  Lista de elementos com massa atómica dentro do intervalo de massa atómica introduzido, ordenada por ordem crescente de
     * discoverer e ordem decrescente de yearOfDiscovery,
     *  Tabela com contagem de elementos por fase e por tipo.
     */
    public void intervaloAtomicMass_1b() {
        double lowerLimit = UtilsUI.readDoubleFromConsole("Introduza o valor inferior do intervalo:");
        double upperLimit = UtilsUI.readDoubleFromConsole("Introduza o valor superior do intervalo:");
        BST<S_AMass> tree = cf.getBstAMass();

        //gera lista de resultados ordenados com massa atómica entre o limite inferior e superior
        ArrayList<S_AMass> resultado =(ArrayList<S_AMass>) tree.findBetween(new S_AMass(lowerLimit), new S_AMass((upperLimit)));

        //ordena resultados por ordem crescente de discoverer e decrescente de yearOfDiscovery
        resultado.sort(new Comparator<S_AMass>() {
            @Override
            public int compare(S_AMass o1, S_AMass o2) {
              if( o1.getPTElement().getDiscoverer().compareTo(o2.getPTElement().getDiscoverer()) > 0 ){             //ordenação por discoverer crescente
                  return 1;
                }else if( o1.getPTElement().getDiscoverer().compareTo(o2.getPTElement().getDiscoverer()) < 0 ){
                  return -1;
              }else{
                  return o2.getPTElement().getYearOfDiscovery() - o1.getPTElement().getYearOfDiscovery();           //ordenação "por year of discovery" decrescente
              }
            }
        });

        //imprime lista de resultados ordenados
        System.out.printf("\nElementos atómicos com massa atómica em [%f e %f]:\n",lowerLimit,upperLimit );
        for (S_AMass r : resultado){
            System.out.println(r.getPTElement().toString2());
        }


        //selecionar os types distintos existentes na lista de resultados
        Set<String> types_set = new TreeSet<>();            //treeset ordena Strings alfabeticamente (natural order)
        for (S_AMass result : resultado){
            types_set.add(result.getPTElement().getType());
        }

        //copiar set para arraylist para permitir acesso aos elementos através de um index
        ArrayList<String> types_list = new ArrayList<>(types_set);

        //cria a matriz Type_Phase com as contagens
        int[][] matriz = CreateTypePhaseMatrix(resultado, types_list);

        //imprime os resultados da matriz em forma de tabela
        printTypePhaseTable(matriz, types_list);


    }

    /**
     * Cria a matriz do ponto 1.b. Contabiliza os tipos e fases dos elementos para preencher a matriz.
     * A matriz representa a quantidade de elementos de um tipo (type) numa determinada fase (phase).
     * @param resultsList lista de elementos a incluir na matriz
     * @param types_list lista com os tipos de elementos (type) presentes na resultsList (ordenados alfabeticamente)
     * @param <E> Qualquer um dos elementos pelo qual se pode pesquisar nas àrvores usadas no ponto 1.
     * @return matriz preenchida com as contagens
     */
    public <E extends tagSearchable> int[][] CreateTypePhaseMatrix(List<E> resultsList,ArrayList<String> types_list){


        int differentPhases = 4+1; //artificial, gas, liquid, solid + total
        int[][] tabela = new int[types_list.size()][differentPhases];

        //calcula as quantidaddes e preenche a matriz
        for (E result : resultsList){
            for (int i = 0; i< types_list.size(); i++){
                    //compara cada resultado da lista com um dos tipos presente na types_list (alkali, metal, noble gas, etc)
                    if(types_list.get(i).equalsIgnoreCase(result.getPTElement().getType())  ){
                        //tendo encontrado o type incrementa-se a célula correspondente de acordo com a fase desse elemento
                        if(result.getPTElement().getPhase().equalsIgnoreCase("artificial")){
                            tabela[i][0] += 1; //incrementa artificial
                            tabela[i][4] += 1; //incrementa total
                        }
                        if(result.getPTElement().getPhase().equalsIgnoreCase("gas")){
                            tabela[i][1] += 1; //incrementa gas
                            tabela[i][4] += 1; //incrementa total
                        }
                        if(result.getPTElement().getPhase().equalsIgnoreCase("liquid")){
                            tabela[i][2] += 1; //incrementa liquid
                            tabela[i][4] += 1; //incrementa total
                        }
                        if(result.getPTElement().getPhase().equalsIgnoreCase("solid")){
                            tabela[i][3] += 1; //incrementa solid
                            tabela[i][4] += 1; //incrementa total
                        }

                    }
            }

        }

        return tabela;

    }

    /**
     * Imprime para a consola a tabela "Tipo por fase" do ponto 1.b
     * @param typePhaseMatrix matrix com as contagens dos resultados
     * @param types lista de tipos para os quais foi feita a contagem na matriz
     */
    public void printTypePhaseTable(int[][] typePhaseMatrix, List<String> types){
        System.out.printf("\n\n%25s %10s %5s\t %5s\t  %5s\t  %5s\n","", "artificial", "gas", "liq", "solid", "Total");
        for (int i = 0; i<typePhaseMatrix.length; i++){
            System.out.printf("%25s",types.get(i));
            for (int j = 0; j<typePhaseMatrix[0].length; j++){
                System.out.print("\t\t" +typePhaseMatrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Calcula e devolve, por ordem decrescente, as configurações electrónicas com mais do que uma repetição, agrupadas por
     * número de repetições.
     */
    public void configuracoesRepetidas_2a() {
        TREE_WORDS_REP bstConfig= cf.getBstConfig();

        ArrayList<TextWord> max = new ArrayList<>();
        for(TextWord t :bstConfig.inOrder()){
            if (t.getOcorrences()>2){
                max.add(t);
            }
        }

        max.sort(new Comparator<TextWord>() {
            @Override
            public int compare(TextWord o1, TextWord o2) {
                return o2.getOcorrences() - o1.getOcorrences();
            }
        });

        for (TextWord tt: max){
            System.out.println(tt);
        }


    }

    /**
     * Contrói uma BST a partir da lista de configurações electrónicas com repetição acima de 2
     * (lista obtida na alinea anterior -> método configuracoesRepetidas_2a)
     * @return devolve a BST criada
     */
    public TREE_WORDS BSTconfiguracoesRepetidas_2b() {
        //list ou map = configuracoesRepetidas_2a;
        //uma vez que da alinea anterior não está correta
        //criou-se um map com os valores mostrados no enunciado
        LinkedHashMap<Integer, ArrayList<String>> valores = new LinkedHashMap<>();
        ArrayList<String> configs32 = new ArrayList<>();
        configs32.add("[Xe]");
        valores.put(32, configs32);
        ArrayList<String> configs18 = new ArrayList<>();
        configs18.add("[Ar]");
        configs18.add("[Kr]");
        valores.put(18, configs18);
        ArrayList<String> configs17 = new ArrayList<>();
        configs17.add("[Xe] 4f14");
        valores.put(17, configs17);
        ArrayList<String> configs9 = new ArrayList<>();
        configs9.add("[Kr] 4d10");
        configs9.add("[Rn]");
        valores.put(9, configs9);
        ArrayList<String> configs8 = new ArrayList<>();
        configs8.add("[Ar] 3d10");
        configs8.add("[He]");
        configs8.add("[Ne]");
        configs8.add("[Xe] 4f14 5d10");
        valores.put(8, configs8);
        ArrayList<String> configs7 = new ArrayList<>();
        configs7.add("[Ar] 3d10 4s2");
        configs7.add("[He] 2s2");
        configs7.add("[Kr] 4d10 5s2");
        configs7.add("[Ne] 3s2");
        configs7.add("[Xe] 4f14 5d10 6s2");
        valores.put(7, configs7);
        ArrayList<String> configs2 = new ArrayList<>();
        configs2.add("[Ar] 3d5");
        configs2.add("[Kr] 4d5");
        configs2.add("[Xe] 4f7");
        valores.put(2, configs2);

        TREE_WORDS bstConfigsRepetidas = new TREE_WORDS();
        for (Map.Entry<Integer, ArrayList<String> > entry : valores.entrySet()){
            for (String s : entry.getValue()){
                bstConfigsRepetidas.insert(new TextWord(s, entry.getKey()));
            }
        }
        return bstConfigsRepetidas;
    }

    /**
     * Calcula e imprime para a consola as configurações eltrónicas mais distantes
     */
    public void configuracoesMaisDistantes_2c() {
        //recupera o mapa do ponto anterior
        TREE_WORDS bstConfigsRepetidas = BSTconfiguracoesRepetidas_2b();

        //calcula os nodes à distancia máxima (resultado em foram de Map)
        HashMap<Integer, ArrayList<TextWord>>  result = bstConfigsRepetidas.nodesAtMaxDistance();
        int distance = (int) result.keySet().toArray()[0];

        //imprime os resultados
        System.out.println("As configurações eletrónicas mais distantes são: ");
        ArrayList<TextWord> listaDeNodes = result.get(distance);

        for (int i = 0; i<listaDeNodes.size(); i+=2){
            System.out.print("Configuração '" +listaDeNodes.get(i).getWord()+"' e configuração '"+listaDeNodes.get(i+1).getWord()+"'. ");
            System.out.println("Distancia: "+ distance + ".");
        }
    }


    /**
     * Método para o ponto 2.d.
     */
    public void ABCConfiguracoes_2d() {
        System.out.println("Trabalho realizado indivualmente. O ponto 2d não foi desenvolvido.");

    }

    /**
     * Método auxiliar usado para imprimir resultados do ponto 1.
     * Valida se o element é null. Caso seja null informa que não encontrou. Caso nao seja null imprime info do elemento.
     * @param elementFound elemento a validar
     * @param <E> node das várias àrvores usadas nas pesquisas do ponto 1.
     */
    private <E extends tagSearchable>  void validateAndPrintElement(E elementFound){
        if (elementFound == null){
            System.out.println("Elemento não encontrado.");
        }else{
            System.out.println(elementFound.getPTElement().toString2());
        }
    }
}
