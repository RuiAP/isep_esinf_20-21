package ui;

import controller.CarregarFicheiroController;
import controller.*;
import model.Constantes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu {

    public Menu(){}

    public void runApp(){

        ArrayList<String> listaOpcoes = new ArrayList<>();
        listaOpcoes.add(Constantes.TEXTO_FUNCIONALIDADE1);
        listaOpcoes.add(Constantes.TEXTO_FUNCIONALIDADE2);
        listaOpcoes.add(Constantes.TEXTO_FUNCIONALIDADE3);
        listaOpcoes.add(Constantes.TEXTO_FUNCIONALIDADE4);
        listaOpcoes.add(Constantes.TEXTO_FUNCIONALIDADE5);
        listaOpcoes.add(1+listaOpcoes.size() + ". Sair da aplicação. ");


        int opcao = 0;
        do {

            opcao = displayAndReadOption(listaOpcoes);

            switch (opcao) {

                case 1:
                    CarregarFicheiroController c1 = new CarregarFicheiroController();

                    if (c1.carregarFicheiro()) {
                        System.out.println("\nFicheiro carregado com sucesso.");
                    } else {
                        System.out.println("\nErro ao carregar o ficheiro.");
                    }
                    break;

                case 2:
                    Ponto2Controller c2 = new Ponto2Controller();
                    System.out.println(Constantes.TEXTO_FUNCIONALIDADE2+":\n");
                    for (String resultado : c2.devolverResultados()) {
                        System.out.println(resultado);
                    }
                    break;

                case 3:
                    Ponto3Controller c3 = new Ponto3Controller();
                    System.out.println(Constantes.TEXTO_FUNCIONALIDADE3+":\n");
                    for (String resultado : c3.devolverResultados()) {
                        System.out.println(resultado);
                    }
                    break;

                case 4:
                    Ponto4Controller c4 = new Ponto4Controller();
                    System.out.println(Constantes.TEXTO_FUNCIONALIDADE4+":\n");
                    for (String resultado : c4.devolverResultados()) {
                        System.out.println(resultado);
                    }
                    break;

                case 5:
                    Ponto5Controller c5 = new Ponto5Controller();
                    System.out.println(Constantes.TEXTO_FUNCIONALIDADE5+":\n");
                    for (String resultado : c5.devolverResultados()) {
                        System.out.println(resultado);
                    }
                    break;

                default:
                    //alguma coisa?

            }
            System.out.println("\n\n");
        }while(opcao != listaOpcoes.size());

    }






    private int displayAndReadOption(ArrayList<String> listaOpcoes){


        for (String s : listaOpcoes){
            System.out.println(s);
        }
        System.out.println("Selecione a opção pretendida:");


        try
        {
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            int selecao = Integer.parseInt( in.readLine() );
            if (selecao > listaOpcoes.size() || selecao <=0){
                throw new NumberFormatException();
            }
            else
            return selecao;
        }
        catch (NumberFormatException e)
        {
            System.out.printf("\n\nErro: O valor introduzido deve ser um número entre 1 e %d.\nTente novamente.\n\n\n\n", listaOpcoes.size());
            //e.printStackTrace();
            return -1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }



}

