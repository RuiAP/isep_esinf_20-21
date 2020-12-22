package tp3.ui;

import PL.BST;
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

    public void configuracoesRepetidas_2a() {


    }

    public void BSTconfiguracoesRepetidas_2b() {


    }

    public void configuracoesMaisDistantes_2c() {


    }

    public void ABCConfiguracoes_2d() {
        System.out.println("Trabalho realizado indivualmente. O ponto 2d não foi desenvolvido.");

    }

    /**
     * Valida se o element é null. Caso seja null informa que não encontrou. Caso nao seja null imprime info do elemento.
     * @param elementFound elemento a validar
     * @param <E> node das várias àrvores usadas nas pesquisas do ponto 1.
     */
    public <E extends tagSearchable>  void validateAndPrintElement(E elementFound){
        if (elementFound == null){
            System.out.println("Elemento não encontrado.");
        }else{
            System.out.println(elementFound.getPTElement().toString2());
        }
    }
}
