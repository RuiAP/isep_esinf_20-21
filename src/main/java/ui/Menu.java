package ui;

import model.Constantes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

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
                    Ponto1ui u1 = new Ponto1ui();
                    u1.display();
                    break;

                case 2:
                    Ponto2ui u2 = new Ponto2ui();
                    u2.display();
                    break;

                case 3:
                    Ponto3ui u3 = new Ponto3ui();
                    u3.display();
                    break;

                case 4:
                    Ponto4ui u4 = new Ponto4ui();
                    u4.display();
                    break;

                case 5:
                    Ponto5ui u5 = new Ponto5ui();
                    u5.display();
                    break;

                default:

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

