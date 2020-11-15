package controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.Leitura;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class Ponto2ControllerTest {

    private Ponto2Controller controller;
    private CarregarFicheiroController controllerFicheiro;
    private LinkedList<Leitura> dadosCarregado;

    public Ponto2ControllerTest(){
        controller = new Ponto2Controller();
        controllerFicheiro= new CarregarFicheiroController();
        controllerFicheiro.carregarFicheiro();
    }

    @Test
    public void sortByNumMinDiasTest(){

        LocalDate data = LocalDate.now();
        Leitura leitura1 = new Leitura("",
                "Russia", // Russia demorou 112 dias
                "",
                data,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        Leitura leitura2 = new Leitura("",
                "China", // Russia demorou 43 dias
                "",
                data,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        Leitura leitura3 = new Leitura("",
                "Romania", // Russia demorou 213 dias
                "",
                data,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        Leitura leitura4 = new Leitura("",
                "Uzbekistan", // Russia demorou 262 dias
                "",
                data,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        LinkedList<Leitura> listaTesteExpected = new LinkedList<>();
        listaTesteExpected.add(leitura1);
        listaTesteExpected.add(leitura2);
        listaTesteExpected.add(leitura3);
        listaTesteExpected.add(leitura4);


        LinkedList<Leitura> listaTesteresult = new LinkedList<>();
        listaTesteresult.add(leitura1);
        listaTesteresult.add(leitura2);
        listaTesteresult.add(leitura3);
        listaTesteresult.add(leitura4);

        controller.sortBynumMinDias(listaTesteresult);

        Assert.assertEquals(listaTesteExpected,listaTesteresult);
    }

}