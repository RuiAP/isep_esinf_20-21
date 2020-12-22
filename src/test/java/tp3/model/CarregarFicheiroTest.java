package tp3.model;

import PL.BST;
import PL.TREE_WORDS;
import PL.TextWord;
import junit.framework.TestCase;

public class CarregarFicheiroTest extends TestCase {

    PTElement hydrogen = new PTElement(1,"Hydrogen","H",1.00794,1.008,
            1,1,"gas","","Nonmetal",0.012,0.79,2.2,
            13.5984,0.00008988,14.175,20.28,3,"Cavendish",
            1766,14.304,"1s1",1,1);
    PTElement actinium = new PTElement(89,"Actinium","Ac",227,
            227.02775,7,3,"solid","fcc","Actinide",1.1,
            -1,1.1,5.17,10.07,1323.15,3471,
            11,"Debierne/Giesel",1899,0.12,"[Rn] 6d1 7s2",
            9,3);

    PTElement zirconium = new PTElement(40,"Zirconium","Zr",91.224,
            91.22,5,4,"solid","hex","Transition Metal",0.72,
            2.2,1.33,6.6339,6.506,2125.15,4682,
            20,"Klaproth",1789,0.278,"[Kr] 4d2 5s2",
            5,4);

    PTElement ununoctium = new PTElement(118,"Ununoctium" ,"Uuo" ,294,
            294.214,7,18,"artificial","","Noble Gas",-1,
            -1,-1,-1,-1,-1,-1,-1,"",
            -1,-1,"",7,18);

    CarregarFicheiro cf;
    private BST<S_AMass> bstAMass;
    private BST<S_ANumber> bstANumber;
    private BST<S_ElementName> bstElementName;
    private BST<S_Symbol> bstSymbol;
    private TREE_WORDS bstConfig;

    public CarregarFicheiroTest(){
        cf = new CarregarFicheiro();
        cf.carregarFicheiroCSV();
        bstAMass = cf.getBstAMass();
        bstANumber = cf.getBstANumber();
        bstElementName = cf.getBstElementName();
        bstSymbol = cf.getBstSymbol();
        bstConfig = cf.getBstConfig();
    }



    public void testCarregarFicheiroCSV() {
        System.out.println("testCarregarFicheiroCSV");

        //size
        int expectedSize = 118;
        assertEquals("O ficheiro csv tem 118 elementos.",expectedSize,bstAMass.size() );
        assertEquals("O ficheiro csv tem 118 elementos.",expectedSize,bstANumber.size() );
        assertEquals("O ficheiro csv tem 118 elementos.",expectedSize,bstElementName.size() );
        assertEquals("O ficheiro csv tem 118 elementos.",expectedSize,bstSymbol.size() );
        expectedSize = 96;//existem 1 elemento (vazio) repetido 23 vezes
        assertEquals("O ficheiro csv tem 118 elementos.",expectedSize,bstConfig.size() );

        //Extremos bstAMass
        S_AMass expectedResult = new S_AMass(hydrogen);
        assertEquals("O hidrogénio tem a massa atómica mais baixa. ",expectedResult , bstAMass.smallestElement() );

        expectedResult = new S_AMass(ununoctium);
        assertEquals("O Ununoctium tem a massa atómica mais alta. ",expectedResult , bstAMass.biggestElement() );


        //Extremos bstANumber
        S_ANumber expectedResult2 = new S_ANumber(hydrogen);
        assertEquals("O hidrogénio tem o número atómico mais baixo. ",expectedResult2, bstANumber.smallestElement() );

        expectedResult2 = new S_ANumber(ununoctium);
        assertEquals("O Ununoctium tem o número atómico mais alto. ",expectedResult2, bstANumber.biggestElement() );


        //Extremos bstElementName
        S_ElementName expectedResult3 = new S_ElementName(actinium);
        assertEquals("'actinium' é o primeiro nome de elemento, por ordem alfabética. ",expectedResult3, bstElementName.smallestElement() );
        expectedResult3 = new S_ElementName(zirconium);
        assertEquals("'Zirconium' é o último nome de elemento, por ordem alfabética. ",expectedResult3, bstElementName.biggestElement() );


        //Extremos bstSymbol
        S_Symbol expectedResult4 = new S_Symbol(actinium);
        assertEquals("'Actinium (Ac)' é o primeiro símbolo por ordem alfabética. ",expectedResult4, bstSymbol.smallestElement() );
        expectedResult4 = new S_Symbol(zirconium);
        assertEquals("'Zirconium (Zr)' é o último símolo por ordem alfabética. ",expectedResult4, bstSymbol.biggestElement() );

        //Extremos bstConfig
        //ordem alfabética -> "", 1s1, 1s2, [Ar]..., [Xe]..., [Xe] 6s2.
        TextWord expectedResult5 = new TextWord("", 1);
        assertEquals("'<Empty>(sem configuração)' é o primeiro valor de configuração eletrónica por ordem alfabética. ",expectedResult5.getWord(), bstConfig.smallestElement().getWord() );
        expectedResult5 = new TextWord("[Xe] 6s2", 1);
        assertEquals("'Barium (Ba) - [Xe] 6s2' é o último símolo por ordem alfabética. ",expectedResult5.getWord(), bstConfig.biggestElement().getWord() );


    }
}