package tp3.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class S_SymbolTest extends TestCase {

    S_Symbol element1 = new S_Symbol("Pd");
    S_Symbol element2 = new S_Symbol("Pd");
    S_Symbol element3 = new S_Symbol("pd");
    S_Symbol element4 = new S_Symbol("Cf");
    S_Symbol element5 = new S_Symbol("Mg");


    public void testEquals() {
        System.out.println("testEquals S_Symbol");
        assertEquals(element1, element2);
        assertFalse(element2.equals(element3));
        assertFalse(element3.equals(element4));
        assertFalse(element5.equals(element1));
    }

    public void testCompareTo() {
        System.out.println("testCompareTo S_Symbol");


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