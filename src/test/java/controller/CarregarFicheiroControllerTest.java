package controller;

import model.Leitura;
import model.RegistoLeituras;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.LinkedList;


public class CarregarFicheiroControllerTest {

    private CarregarFicheiroController controller;
    private LinkedList<Leitura> dadosCarregado;

    public CarregarFicheiroControllerTest(){
            controller = new CarregarFicheiroController();
            controller.carregarFicheiro();
            dadosCarregado = RegistoLeituras.getLeituras();
    }


    @Test
    public void carregarFicheiroTest1(){
        LocalDate dataDaLinha = LocalDate.parse("2020-01-01");
        Leitura leituraManualPrimeiraLinha = new Leitura("AFG",
                "Asia",
                "Afghanistan",
                dataDaLinha,
                0,
                0,
                0,
                0,
                -1,
                -1,
                38928341,
                2.581,
                597.029,
                9.59,
                -1,
                -1,
                0.5,
                64.83);

        Assert.assertEquals(dadosCarregado.getFirst(), leituraManualPrimeiraLinha);

    }

    @Test
    public void carregarFicheiroTest2(){
        LocalDate dataDaLinha = LocalDate.parse("2020-09-29");
        Leitura leituraManualUltimaLinha = new Leitura("ZWE",
                "Africa",
                "Zimbabwe",
                dataDaLinha,
                7816,
                4,
                228,
                1,
                -1,
                -1,
                14862927,
                2.822,
                307.846,
                1.82,
                1.6,
                30.7,
                1.7,
                61.49);

        Assert.assertEquals(dadosCarregado.getLast(), leituraManualUltimaLinha);
    }

    @Test
    public void carregarFicheiroTest3(){
        LocalDate dataDaLinha = LocalDate.parse("2020-01-16");
        Leitura leituraManualLinha20677 = new Leitura("IRL",
                "Europe",
                "Ireland",
                dataDaLinha,
                0,
                0,
                0,
                0,
                -1,
                -1,
                4937796,
                13.928,
                126.459,
                3.28,
                23,
                25.7,
                2.96,
                82.3);

        Assert.assertEquals(dadosCarregado.get(20677-2), leituraManualLinha20677);
        //-2 no indice devido à linha inicial do csv e por contar a partir do zero
    }
}