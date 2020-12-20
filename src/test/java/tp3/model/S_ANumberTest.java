package tp3.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class S_ANumberTest extends TestCase {

    PTElement pte1 = new PTElement(5);
    PTElement pte2 = new PTElement(5);
    PTElement pte3 = new PTElement(15);
    PTElement pte4 = new PTElement(7);
    S_ANumber element1 = new S_ANumber(pte1);
    S_ANumber element2 = new S_ANumber(pte2);
    S_ANumber element3 = new S_ANumber(pte3);
    S_ANumber element4 = new S_ANumber(pte4);


    public void testEquals() {
        System.out.println("testEquals S_ANumber");

        assertEquals(element1, element2);
        assertFalse(element2.equals(element3));
        assertFalse(element3.equals(element4));
    }

    public void testCompareTo() {
        System.out.println("testCompareTo S_ANumber");

        List<S_ANumber> listElement = new ArrayList<>();
        listElement.add(element1);
        listElement.add(element4);
        listElement.add(element3);
        listElement.add(element2);
        Collections.sort(listElement);
        List<S_ANumber> expectedResult = new ArrayList<>();
        expectedResult.add(element1);
        expectedResult.add(element2);
        expectedResult.add(element4);
        expectedResult.add(element3);

        for(int i = 0; i<listElement.size(); i++){
            assertEquals(expectedResult.get(i),listElement.get(i));
        }

    }
}