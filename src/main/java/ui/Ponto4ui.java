package ui;

import controller.Ponto4Controller;
import model.Constantes;
import model.Leitura;
import model.RegistoLeituras;


import java.util.*;

public class Ponto4ui {

    private Ponto4Controller controller;
    private ArrayList<String> continentesCarregados;

    public Ponto4ui(){
        controller = new Ponto4Controller();
        continentesCarregados = new ArrayList<>(RegistoLeituras.getContinentesNasLeituras());
    }


    public void display() {

        if(continentesCarregados.isEmpty()){
            System.out.println("Não há continentes disponiveis para selecionar.\nCarregue ficheiro de dados Primeiro.");
        }
        else{
            System.out.println(Constantes.TEXTO_FUNCIONALIDADE4+":\n");

            String[] input = solicitarMesEContinente();
            String continenteSelecionado = input[0];
            int mesSelecionado = Integer.parseInt(input[1]);

            System.out.println(input[0] +"   " + input[1]);

            LinkedList<Leitura> resultados = controller.devolverResultados(mesSelecionado, continenteSelecionado);

            imprimirResultados(resultados);
        }

    }



    private String[] solicitarMesEContinente() {

        String[] inputUtilizador = new String[2];

        Scanner in = null;
        try {
            in = new Scanner(System.in);


            for (int i = 1; i <= continentesCarregados.size(); i++) {
                System.out.println(i + ". " + continentesCarregados.get(i - 1) + ".");
            }
            System.out.println("Escolha o número do continente a selecionar:");
            inputUtilizador[0] = continentesCarregados.get(Integer.parseInt(in.nextLine()) - 1);

            ArrayList<String> listaMeses = new ArrayList<>();
            listaMeses.add("Janeiro");
            listaMeses.add("Fevereiro");
            listaMeses.add("Março");
            listaMeses.add("Abril");
            listaMeses.add("Maio");
            listaMeses.add("Junho");
            listaMeses.add("Julho");
            listaMeses.add("Agosto");
            listaMeses.add("Setembro");
            listaMeses.add("Oubtubro");
            listaMeses.add("Novembro");
            listaMeses.add("Dezembro");


            for (int i = 1; i <= listaMeses.size(); i++) {
                System.out.println(i + ". " + listaMeses.get(i - 1) + ".");
            }
            System.out.println("Escolha o número do mês a selecionar:");
            inputUtilizador[1] =""+ in.nextInt();

            return inputUtilizador;

        }catch(Exception e){
            e.printStackTrace();
        }finally{
        }
        return inputUtilizador;
    }

    private void imprimirResultados(LinkedList<Leitura> resultados) {
        int dayBefore = 0;
        String novosCasos;
        for(Leitura l : resultados){

            if(l.getNewCases() == Constantes.REPLACEMENT_FOR_NA){
                novosCasos = "NA";
            }else{
                novosCasos = ""+l.getNewCases();
            }

            if (l.getDate().getDayOfMonth() > dayBefore){
                System.out.printf("%10s %s (%s)\n", "Day "+l.getDate().getDayOfMonth()+ " -->", l.getCountry(), novosCasos);
            }else{
                System.out.printf("%10s %s (%s)\n", "", l.getCountry(), novosCasos);
            }

            dayBefore = l.getDate().getDayOfMonth();
        }

    }
}
