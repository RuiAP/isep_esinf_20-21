package tp3.ui;

import PL.BST;
import tp3.model.CarregarFicheiro;
import tp3.model.*;
import tp3.model.S_ANumber;
import tp3.model.S_ElementName;

public class UserInterface {

    CarregarFicheiro cf;
    public UserInterface(){
        cf = new CarregarFicheiro();
        cf.carregarFicheiroCSV();
    }


    public void AtomicNumber_1a() {
        int aNumber = UtilsUI.readIntFromConsole("Introduza o número atómico do elemento a procurar:");
        BST<S_ANumber> tree = cf.getBstANumber();

        S_ANumber elementFound = tree.findElement(new S_ANumber(aNumber));

        validateAndPrintElement(elementFound);
    }

    public void Element_1a() {
        String elementName_str = UtilsUI.readLineFromConsole("Introduza o nome do elemento a procurar:");
        BST<S_ElementName> tree =  cf.getBstElementName();

        S_ElementName elementFound = tree.findElement(new S_ElementName(elementName_str));

       validateAndPrintElement(elementFound);
    }


    public void Symbol_1a() {
        String symbol_str = UtilsUI.readLineFromConsole("Introduza o símbolo do elemento a procurar:");
        BST<S_Symbol> tree =  cf.getBstSymbol();

        S_Symbol elementFound = tree.findElement(new S_Symbol(symbol_str));
        validateAndPrintElement(elementFound);
    }

    public void AtomicMass_1a() {
        double atomic = UtilsUI.readDoubleFromConsole("Introduza a massa atómica do elemento a procurar:");
        BST<S_AMass> tree = cf.getBstAMass();

        S_AMass elementFound = tree.findElement(new S_AMass(atomic));
        validateAndPrintElement(elementFound);
    }

    public void intervaloAtomicMass_1b() {
        double lowerLimit = UtilsUI.readDoubleFromConsole("Introduza o valor inferior do intervalo:");
        double upperLimit = UtilsUI.readDoubleFromConsole("Introduza o valor superior do intervalo:");

        throw new UnsupportedOperationException();


    }

    public void configuracoesRepetidas_2a() {


    }

    public void BSTconfiguracoesRepetidas_2b() {


    }

    public void configuracoesMaisDistantes_2c() {


    }

    public void ABCConfiguracoes_2d() {


    }


    public <E extends tagSearchable>  void validateAndPrintElement(E elementFound){
        if (elementFound == null){
            System.out.println("Elemento não encontrado.");
        }else{
            System.out.println(elementFound.getPTElement().toString2());
        }
    }
}
