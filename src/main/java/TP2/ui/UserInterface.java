package TP2.ui;

import TP2.controller.Controller;
import TP2.model.Country;
import TP2.model.User;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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
        ArrayList<User> listautilizadoresPopulares = c1.calcularUtilizadoresPopulares(nUtilizadoresPopulares);
        LinkedHashSet<User> amigosComuns = c1.p2CalcularAmigosComuns(listautilizadoresPopulares);

        System.out.printf("Os %d utilizadores mais populares são:\n",nUtilizadoresPopulares);
        listautilizadoresPopulares.forEach(System.out::println);
        System.out.println("\nEstes utilizadores têm os seguintes amigos em comum:");
        amigosComuns.forEach(System.out::println);
    }

    /**
     * Solicita e processa o input do utilizador, necessário para realizar o Ponto3
     */
    public void ponto3() {
        double caminhoMax = c1.p3CalcularDiametroGrafo();
        if (caminhoMax == -1) {
            System.out.println("A rede não é conectada.");
            return;
        }
        System.out.printf("A rede é conectada e são necessárias, no mínimo, %.0f ligações.\n", caminhoMax);

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
            System.out.println("User selecionado:\n " + userSelecionado+"\n");

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
    public boolean ponto6() {

        String strPrompt = "Introduza o primeiro utilizador a selecionar(formato \"ux\" onde x é o numero do utilizador):";

        String userId = UtilsUI.readLineFromConsole(strPrompt);
        User userSelecionado1 = c1.checkVertexByUserId(userId);
        if( userSelecionado1 == null){
            System.out.println("Utilizador não encontrado no grafo.");
            return false;
        }
        else{
            System.out.println("Primeiro utilizador selecionado.");
            String strPrompt2 = "Introduza o segundo utilizador a selecionar(formato \"ux\" onde x é o numero do utilizador):";

            String user2Id = UtilsUI.readLineFromConsole(strPrompt2);
            User userSelecionado2 = c1.checkVertexByUserId(user2Id);
            if( userSelecionado2 == null) {
                System.out.println("Utilizador não encontrado no grafo.");
                return false;
            }else{
                System.out.println("Segundo utilizador selecionado");
                String strPromptCidades = "Quantas cidades intermédias, por utilizador, deseja incluir?";
                int numCidades = UtilsUI.readIntFromConsole(strPromptCidades);

                LinkedList<Country> caminho = new LinkedList<>();
                Object[] resultados = c1.p6CaminhoTerrestre(userSelecionado1, userSelecionado2, numCidades, caminho);
                caminho = (LinkedList<Country>) resultados[0];
                double distTotal = (Double) resultados[1];

                if ((int)distTotal == -1){
                    System.out.println("Impossivel gerar caminho entre os dois utilizadores.");
                    return false;
                }

                StringBuilder sb = new StringBuilder();
                System.out.println("O caminho mais curto é:");
                for (int i = 0; i<caminho.size(); i++){
                    if(i==0){
                        System.out.print(caminho.get(i).getCapital());
                    }
                    else{
                        System.out.print(", "+ caminho.get(i).getCapital());
                    }
                }
                System.out.println(".\n");
                System.out.printf("Distância aproximada: %.0f Km.",distTotal);
                return true;
            }
        }


    }



}

