package ui;

import controller.CarregarFicheiroController;

public class Ponto1ui {

    private CarregarFicheiroController controller;

    public Ponto1ui(){
        controller = new CarregarFicheiroController();
    }

    public void display() {

        if (controller.carregarFicheiro()) {
            System.out.println("\nFicheiro carregado com sucesso.");
        } else {
            System.out.println("Ficheiro não carregado.");
        }
    }


}
