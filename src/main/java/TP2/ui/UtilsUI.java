package TP2.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class UtilsUI {



    /**
     * Apresenta uma lista de opções ao utilizador e recolhe e devole o número da seleção efetuada.
     * @param listaOpcoes Arraylist de Strings com a lista de opções a mostrar ao utilizador.
     * @return int com a seleção do utilizador (valor entre 1 e list.size())
     */
    static public int displayAndReadOption(ArrayList<String> listaOpcoes, String header) {
        int selecao = -1;

        if(header != null || !header.isEmpty()){
            System.out.println("\n"+header);
        }

        try {
            InputStreamReader br = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(br);

            for(String s: listaOpcoes){
                System.out.println(s);
            }

            do {
                selecao = Integer.parseInt(in.readLine());
            } while (selecao > listaOpcoes.size() || selecao <= 0);

        } catch (NumberFormatException e) {
            System.out.printf("\n\nErro: O valor introduzido deve ser um número entre 1 e %d." +
                    "\nTente novamente.\n\n\n\n", listaOpcoes.size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selecao;
    }


    /**
     * Apresenta a string passada por parâmetro e lê e devolve um número introduzido pelo utilizador.
     * @param strPrompt String a ser apresentada antes de receber o input do utilizador
     * @return int com o número introduzido pelo utilizador
     */
    static public int readIntFromConsole(String strPrompt)
    {
        try
        {
            System.out.println("\n" + strPrompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            int valor = Integer.parseInt(in.readLine());
            System.out.println();
            return valor;
        } catch (Exception e)
        {
            System.out.println("Erro ao ler numero da consola");
            return -1;
        }
    }

    /**
     * Apresenta a string passada por parâmetro e lê e devolve texto introduzido pelo utilizador.
     * @param strPrompt String a ser apresentada antes de receber o input do utilizador
     * @return String com o texto introduzido pelo utilizador
     */
    static public String readLineFromConsole(String strPrompt)
    {
        try
        {
            System.out.println("\n" + strPrompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e)
        {
            System.out.println("Erro ao ler texto da consola.");
            return null;
        }
    }

}

