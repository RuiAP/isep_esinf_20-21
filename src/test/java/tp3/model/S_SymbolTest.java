package tp3.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class S_SymbolTest extends TestCase {


    PTElement pte1 = new PTElement("Pd", true);
    PTElement pte2 = new PTElement("Pd", true);
    PTElement pte3 = new PTElement("pd", true);
    PTElement pte4 = new PTElement("Cf", true);
    PTElement pte5 = new PTElement("Mg",true);
    S_Symbol element1 = new S_Symbol(pte1);
    S_Symbol element2 = new S_Symbol(pte2);
    S_Symbol element3 = new S_Symbol(pte3);
    S_Symbol element4 = new S_Symbol(pte4);
    S_Symbol element5 = new S_Symbol(pte5);


    public void testTestEquals() {
        assertEquals(element1, element2);
        assertFalse(element2.equals(element3));
        assertFalse(element3.equals(element4));
        assertFalse(element5.equals(element1));
    }

    public void testCompareTo() {

        List<S_Symbol> listElement = new ArrayList<>();
        listElement.add(element3);
        listElement.add(element2);
        listElement.add(element5);
        listElement.add(element1);
        listElement.add(element4);
        Collections.sort(listElement);
        List<S_Symbol> expectedResult = new ArrayList<>();
        expectedResult.add(element4);
        expectedResult.add(element5);
        expectedResult.add(element2);
        expectedResult.add(element1);
        expectedResult.add(element3);

        for(int i = 0; i<listElement.size(); i++){
            assertEquals(expectedResult.get(i),listElement.get(i));
        }
    }
}