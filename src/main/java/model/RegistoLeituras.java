package model;

import java.util.ArrayList;
import java.util.TreeSet;

public class RegistoLeituras {



    private static ArrayList<Leitura> leituras;


    public static void carregarDadosFicheiro(ArrayList<Leitura> dados){
        leituras = dados;
    }

    public static ArrayList<Leitura> getLeituras(){
        return leituras;
    }

 

}
