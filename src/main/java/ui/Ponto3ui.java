package ui;

import controller.CarregarFicheiroController;
import controller.Ponto3Controller;
import model.Constantes;

public class Ponto3ui {

    private Ponto3Controller controller;

    public Ponto3ui(){
        controller = new Ponto3Controller();
    }

    public void display() {

        System.out.println(Constantes.TEXTO_FUNCIONALIDADE3+":\n");

        System.out.printf("%-15s %-15s %-15s %-15s\n","continent", "month", "new_cases", "new_deaths");

        for (String[] resultado : controller.devolverResultados()) {
            for(int i = 0; i<4; i++){
                System.out.printf("%-15s ", resultado[i]);
            }
            System.out.println();
        }
    }
}
