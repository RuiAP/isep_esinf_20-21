package tp3.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class S_ElementNameTest extends TestCase {

    PTElement pte1 = new PTElement("elemento teste");
    PTElement pte2 = new PTElement("elemento teste");
    PTElement pte3 = new PTElement("elemento");
    PTElement pte4 = new PTElement("atómico");
    S_ElementName element1 = new S_ElementName(pte1);
    S_ElementName element2 = new S_ElementName(pte2);
    S_ElementName element3 = new S_ElementName(pte3);
    S_ElementName element4 = new S_ElementName(pte4);


    public void testTestEquals() {
        assertEquals(element1, element2);
        assertFalse(element2.equals(element3));
        assertFalse(element3.equals(element4));
    }

    public void testCompareTo() {
        List<S_ElementName> listElement = new ArrayList<>();
        listElement.add(element1);
        listElement.add(element4);
        listElement.add(element3);
        listElement.add(element2);
        Collections.sort(listElement);
        List<S_ElementName> expectedResult = new ArrayList<>();
        expectedResult.add(element4);
        expectedResult.add(element3);
        expectedResult.add(element1);
        expectedResult.add(element2);

        for(int i = 0; i<listElement.size(); i++){
            assertEquals(expectedResult.get(i),listElement.get(i));
        }

    }
}