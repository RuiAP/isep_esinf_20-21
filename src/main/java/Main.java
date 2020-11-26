import com.bahadirarslan.jeodezi.Coordinate;
import com.bahadirarslan.jeodezi.GreatCircle;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        CarregarFicheiros cf = new CarregarFicheiros();

        LinkedList<User> teste = (LinkedList) cf.uploadUsers();
        System.out.println(teste);



          System.out.println("Hello");
        Coordinate c1 = new Coordinate( -0.2298500, -78.5249500);
        Coordinate c2 = new Coordinate( 5.8663800, -55.1668200);
        GreatCircle globe = new GreatCircle();
        double result = (double)globe.distance(c1, c2);
        System.out.println(result);
        System.out.printf("%.0f", result);
        

    }
}
