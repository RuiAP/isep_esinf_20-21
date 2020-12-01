package TP2.ui;

import TP2.Controller;
import TP2.model.User;

import java.util.ArrayList;
import java.util.LinkedList;

public class UserInterface {

    private Controller c1;

    public UserInterface() {
        c1 = new Controller();
    }

    /**
     * Solicita e processa o input do utilizador, necessário para realizar o Ponto1
     */
    public void ponto1() {
        ArrayList<String> listaOpcoes = new ArrayList<>();
        listaOpcoes.add("1. Carregar ficheiros pequenos.");
        listaOpcoes.add("2. Carregar ficheiros grandes.");
        listaOpcoes.add("3. Voltar ao menu inicial.");

        int selecao = UtilsUI.displayAndReadOption(listaOpcoes, "Pretende:");

        if (selecao == 1 || selecao == 2) {
            boolean selectSmallFiles = selecao == 1 ? true : false;
            if (c1.p1CriarGrafos(selectSmallFiles))
                System.out.println("Ficheiros carregados corretamente.");
        }

    }


    /**
     * Solicita e processa o input do utilizador, necessário para realizar o Ponto2
     */
    public void ponto2() {
        String header = "Introduza o valor de n:";
        int nUtilizadoresPopulares = UtilsUI.readIntFromConsole(header);
        LinkedList<User> amigosComuns = c1.p2CalcularAmigosComuns(nUtilizadoresPopulares);
        System.out.printf("Os %d utilizadores mais populares são:\n",nUtilizadoresPopulares);
        System.out.printf("Os amigos comuns entre os %d utilizadores mais populares são:\n", nUtilizadoresPopulares);
        amigosComuns.forEach(System.out::println);
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
        String strPrompt = "Introduza o utilizador a selecionar(formato \"ux\" onde x é o numero do utilizador):";

        String userId = UtilsUI.readLineFromConsole(strPrompt);
        User userSelecionado = c1.checkVertexByUserId(userId);
        if( userSelecionado == null){
            System.out.println("Utilizador não encontrado.");
        }
        else{
            System.out.println("User selecionado:\n " + userSelecionado+".\n");

            String strPrompt2 = "Introduza o número de fronteiras que quer incluir na pesquisa:";
            int nFronteiras = UtilsUI.readIntFromConsole(strPrompt2);
            //System.out.println("A calcular...\n");

            LinkedList<User> amigosProximos = c1.p4CalcularAmigosProximos(userSelecionado,nFronteiras);

            if(amigosProximos == null){
                System.out.println("Não foi possivel relacionar grafo users com grafo cidades");
            }
            else{
                System.out.println("Os amigos à distância de "+nFronteiras+ " fronteira(s) são:");
                for(User u : amigosProximos){
                    System.out.printf("%s de %s.\n",u.getUserId(), u.getCity());
                }
            }
        }
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



}

