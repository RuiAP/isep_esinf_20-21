package tp3;


import tp3.model.CarregarFicheiro;
import tp3.ui.Menu;

/**
 *
 */
public class Main
{

    public static void main( String[] args )
    {
        CarregarFicheiro cf = new CarregarFicheiro();
        cf.carregarFicheiroCSV();

       Menu menu = new Menu();
        //menu.display();

    }
}
