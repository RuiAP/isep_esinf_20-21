package controller;

import model.Leitura;
import model.RegistoLeituras;


public class Ponto3Controller {


    public Ponto3Controller(){

    }


    public String[][] devolverResultados(){

        //Substituir por: ler os continentes diretamente das leituras
        String continentes[] = {"\"Africa\"", "\"Asia\"", "\"Europe\"", "\"North America\"", "\"Oceania\"", "\"South America\""};
        int novosCasosTotaisDoMes;
        int novasMortesTotaisDoMes;
        String resultadoFinal[][] = new String[continentes.length*12][4];
        int indice =0;


        for (int i = 0; i<continentes.length; i++){
            for (int j = 0; j<12; j++){
                novosCasosTotaisDoMes=0;
                novasMortesTotaisDoMes=0;
                for(Leitura l : RegistoLeituras.getLeituras() ){
                   if(l.getContinent().equalsIgnoreCase(continentes[i]) && l.getMonth() == j+1 ){
                        if(l.getNewCases() != -1)
                        novosCasosTotaisDoMes += l.getNewCases();
                        if(l.getNewDeaths() != -1)
                        novasMortesTotaisDoMes += l.getNewDeaths();
                    }
                }
                resultadoFinal[indice][0] = continentes[i];
                resultadoFinal[indice][1] = ""+(j+1);
                resultadoFinal[indice][2] = ""+novosCasosTotaisDoMes;
                resultadoFinal[indice][3] = ""+novasMortesTotaisDoMes;
                indice++;

            }

        }
        return resultadoFinal;

    }
}
