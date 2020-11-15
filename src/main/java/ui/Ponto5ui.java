package ui;

import controller.Ponto5Controller;
import model.Constantes;
import model.Leitura;
import model.RegistoLeituras;

public class Ponto5ui {

    private Ponto5Controller controller;

    public Ponto5ui(){
        controller = new Ponto5Controller();
    }

    public void display() {

        System.out.println(Constantes.TEXTO_FUNCIONALIDADE5+":\n");

        for(Leitura r :  controller.devolverResultados()){
            System.out.printf("%23s\t\t %.1f \t\t %d\n",
                    r.getCountry(),
                    r.getFemaleSmokers()+r.getMaleSmokers(),
                    RegistoLeituras.totalNovasMortesPorPais(r.getCountry()));
        }
    }
}
