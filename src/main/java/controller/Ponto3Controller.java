package controller;

import model.Leitura;
import model.RegistoLeituras;

import java.util.ArrayList;
import java.util.TreeSet;


public class Ponto3Controller {

    public ArrayList<Leitura> leituras;

    public Ponto3Controller(){
        leituras = RegistoLeituras.getLeituras();
    }


    public String[][] devolverResultados(){

        TreeSet<String> continentes = RegistoLeituras.getContinentesNasLeituras();
        int novosCasosTotaisDoMes;
        int novasMortesTotaisDoMes;
        String resultadoFinal[][] = new String[continentes.size()*12][4];
        int indice =0;


        for (String country : continentes){
            for (int j = 0; j<12; j++){
                novosCasosTotaisDoMes=0;
                novasMortesTotaisDoMes=0;
                for(Leitura l :  leituras ){
                   if(l.getContinent().equalsIgnoreCase(country) && l.getMonth() == j+1 ){
                        if(l.getNewCases() != -1)
                        novosCasosTotaisDoMes += l.getNewCases();
                        if(l.getNewDeaths() != -1)
                        novasMortesTotaisDoMes += l.getNewDeaths();
                    }
                }
                resultadoFinal[indice][0] = country;
                resultadoFinal[indice][1] = ""+(j+1);
                resultadoFinal[indice][2] = ""+novosCasosTotaisDoMes;
                resultadoFinal[indice][3] = ""+novasMortesTotaisDoMes;
                indice++;
            }
        }
        return resultadoFinal;

    }
}
