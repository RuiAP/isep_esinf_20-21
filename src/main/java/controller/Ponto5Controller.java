package controller;

import model.Leitura;
import model.RegistoLeituras;
import java.util.*;

public class Ponto5Controller {


    public LinkedList<Leitura> leituras;

    public Ponto5Controller(){
        leituras = RegistoLeituras.getLeituras();
    }


    public LinkedList<Leitura> devolverResultados(){

        LinkedList<Leitura> resultados = new LinkedList<>();
        TreeMap<String, Integer> paisesCopiados = new TreeMap<>();

        for (Leitura l : leituras){
            boolean jaCopiado = paisesCopiados.containsKey(l.getCountry());

            if(!jaCopiado  && ((l.getMaleSmokers() + l.getFemaleSmokers()) > 70 )){
                resultados.add(l);
                paisesCopiados.put(l.getCountry(), RegistoLeituras.totalNovasMortesPorPais(l.getCountry()));
            }
        }

        resultados.sort(new Comparator<Leitura>() {
            @Override
            public int compare(Leitura l1, Leitura l2) {
                return paisesCopiados.get(l2.getCountry()) - paisesCopiados.get(l1.getCountry());
            }
        });

        return resultados;
    }
}
