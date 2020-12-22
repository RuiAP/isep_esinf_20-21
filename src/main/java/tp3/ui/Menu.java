package tp3.ui;

import java.util.ArrayList;

public class Menu {

    private UserInterface ui;
    public  Menu(){
        ui = new UserInterface();
    }

    public void display(){

        ArrayList<String> listaOpcoes = new ArrayList<>();
        listaOpcoes.add(1+listaOpcoes.size() +". Pesquisa por número atómico");
        listaOpcoes.add(1+listaOpcoes.size() +". Pesquisa por nome do elemento");
        listaOpcoes.add(1+listaOpcoes.size() +". Pesquisa por símbolo");
        listaOpcoes.add(1+listaOpcoes.size() +". Pesquisa por massa atómica");
        listaOpcoes.add(1+listaOpcoes.size() +". Pesquisa por intervalo de valores de massa atómica");
        listaOpcoes.add(1+listaOpcoes.size() +". Lista de configurações eletrónicas repetidas");
        listaOpcoes.add(1+listaOpcoes.size() +". BST com configurações repetidas");
        listaOpcoes.add(1+listaOpcoes.size() +". As duas configurações mais distantes");
        listaOpcoes.add(1+listaOpcoes.size() +". Árvore binária completa com configurações eletrónicas únicas");
        listaOpcoes.add(1+listaOpcoes.size() +". " +"Sair da aplicação.");


        int opcao = 0;
        do {
            String header = "Projeto Tabela Periódica.\n\nOpções: ";
            opcao = UtilsUI.displayAndReadOption(listaOpcoes, header);

            switch (opcao) {
                case 1:
                    ui.AtomicNumber_1a();
                    break;

                case 2:
                    ui.Element_1a();
                    break;

                case 3:
                    ui.Symbol_1a();
                    break;

                case 4:
                    ui.AtomicMass_1a();
                    break;

                case 5:
                    ui.intervaloAtomicMass_1b();
                    break;

                case 6:
                    ui.configuracoesRepetidas_2a();
                    break;
                case 7:
                    ui.BSTconfiguracoesRepetidas_2b();
                    break;
                case 8:
                    ui.configuracoesMaisDistantes_2c();
                    break;
                case 9:
                    ui.ABCConfiguracoes_2d();
                    break;

                default:
            }
            System.out.println("\n\n");
        }while(opcao != listaOpcoes.size());


    }


}
