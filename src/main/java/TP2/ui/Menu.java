package TP2.ui;

import TP2.model.Constantes;
import java.util.ArrayList;

public class Menu {

    private UserInterface ui;
    public  Menu(){
        ui = new UserInterface();
    }

    public void display(){

        ArrayList<String> listaOpcoes = new ArrayList<>();
        listaOpcoes.add(1+listaOpcoes.size() +". " +Constantes.TEXTO_FUNCIONALIDADE1);
        listaOpcoes.add(1+listaOpcoes.size() +". " +Constantes.TEXTO_FUNCIONALIDADE2);
        listaOpcoes.add(1+listaOpcoes.size() +". " +Constantes.TEXTO_FUNCIONALIDADE3);
        listaOpcoes.add(1+listaOpcoes.size() +". " +Constantes.TEXTO_FUNCIONALIDADE4);
        listaOpcoes.add(1+listaOpcoes.size() +". " +Constantes.TEXTO_FUNCIONALIDADE5);
        listaOpcoes.add(1+listaOpcoes.size() +". " +Constantes.TEXTO_FUNCIONALIDADE6);
        listaOpcoes.add(1+listaOpcoes.size() +". " +"Sair da aplicação.");


        int opcao = 0;
        do {
            String header = "Menu Inicial: ";
            opcao = UtilsUI.displayAndReadOption(listaOpcoes, header);

            switch (opcao) {
                case 1:
                    ui.ponto1();
                    break;

                case 2:
                    ui.ponto2();
                    break;

                case 3:
                    ui.ponto3();
                    break;

                case 4:
                    ui.ponto4();
                    break;

                case 5:
                    ui.ponto5();
                    break;

                case 6:
                    ui.ponto6();
                    break;

                default:
            }
            System.out.println("\n\n");
        }while(opcao != listaOpcoes.size());


    }


}
