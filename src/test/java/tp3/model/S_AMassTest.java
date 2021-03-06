package tp3.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class S_AMassTest extends TestCase {

    S_AMass element1 = new S_AMass(18.151515);
    S_AMass element2 = new S_AMass(18.151515);
    S_AMass element3 = new S_AMass(18.151516);
    S_AMass element4 = new S_AMass(26.2525252);
    S_AMass element5 = new S_AMass(4.555555555);


    public void testEquals() {
        System.out.println("testEquals S_AMass");
        assertEquals(element1, element2);
        assertFalse(element2.equals(element3));
        assertFalse(element3.equals(element4));
        assertFalse(element5.equals(element1));
    }

    public void testCompareTo() {
        System.out.println("testCompareTo S_AMass");
        List<S_AMass> listElement = new ArrayList<>();
        listElement.add(element3);
        listElement.add(element4);
        listElement.add(element5);
        listElement.add(element2);
        listElement.add(element1);
        Collections.sort(listElement);
        List<S_AMass> expectedResult = new ArrayList<>();
        expectedResult.add(element5);
        expectedResult.add(element2);
        expectedResult.add(element1);
        expectedResult.add(element3);
        expectedResult.add(element4);

        for(int i = 0; i<listElement.size(); i++){
            assertEquals(expectedResult.get(i),listElement.get(i));
        }
    }
}