package ui;

import controller.Ponto2Controller;
import model.Constantes;
import model.Leitura;
import model.RegistoLeituras;

import java.util.LinkedList;

public class Ponto2ui {

    private Ponto2Controller controller;

    public Ponto2ui(){
        controller = new Ponto2Controller();
    }

    public void display() {
        System.out.println(Constantes.TEXTO_FUNCIONALIDADE2+":\n");

        LinkedList<Leitura> resultadoPonto2 = controller.devolverResultados();

        System.out.printf("%-10s %-20s %-20s %-15s %-15s %-10s\n",
                "iso_code",
                "continent",
                "location",
                "date",
                "total_cases",
                "mindays");

        for (Leitura l : resultadoPonto2){
            System.out.printf("%-10s %-20s %-20s %-15s %-15s %-10s\n",
                    l.getIsoCode(),
                    l.getContinent(),
                    l.getCountry(),
                    l.getDate().toString(),
                    ""+l.getTotalCases(),
                    ""+ RegistoLeituras.contarDiasDesdeInicio(l));
        }
    }

}
