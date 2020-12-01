package TP2.ui;

import TP2.CarregarFicheiros;
import TP2.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class UserInterface {

    public UserInterface() {

    }

    /**
     * Solicita e processa o input do utilizador, necessário para realizar o Ponto1
     */
    public void ponto1() {
        ArrayList<String> listaOpcoes = new ArrayList<>();
        listaOpcoes.add("1. Carregar ficheiros pequenos.");
        listaOpcoes.add("2. Carregar ficheiros grandes.");
        listaOpcoes.add("3. Voltar ao menu inicial.");

        int selecao = displayAndReadOption(listaOpcoes, "Pretende:");
        switch (selecao){
            case 1:
                CarregarFicheiros cf1 = new CarregarFicheiros(true);
                cf1.carregarGrafoCountries();
                cf1.carregarGrafoUsers();
                break;
            case 2:
                CarregarFicheiros cf2 = new CarregarFicheiros(false);
                cf2.carregarGrafoCountries();
                cf2.carregarGrafoUsers();
                break;
            default:
                return;
        }
        System.out.println("Ficheiros carregados corretamente."); //falta validação
    }


    /**
     * Solicita e processa o input do utilizador, necessário para realizar o Ponto2
     */
    public void ponto2() {
        String header = "Introduza o valor de n:";
        int nUtilizadoresPopulares = readIntFromConsole(header);
        //LinkedList<User> amigosComuns = calcularAmigosComuns(nUtilizadoresPopulares);
        //System.out.print("Os %d utilizadores mais populares são:\n");
        System.out.print("Os amigos comuns dos %d utilizadores mais populares são:\n");
        //amigosComuns.forEach(System.out::println);
    }

    /**
     * Solicita e processa o input do utilizador, necessário para realizar o Ponto3
     */
    public void ponto3() {

    }

    /**
     * Solicita e processa o input do utilizador, necessário para realizar o Ponto4
     */
    public void ponto4() {

    }

    /**
     * Solicita e processa o input do utilizador, necessário para realizar o Ponto5
     */
    public void ponto5() {
        System.out.println("Trabalho realizado individualmente. Ponto 5 não desenvolvido.");
    }

    /**
     * Solicita e processa o input do utilizador, necessário para realizar o Ponto6
     */
    public void ponto6() {

    }

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
            return valor;
        } catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

}
