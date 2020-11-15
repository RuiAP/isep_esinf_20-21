package controller;

import model.Leitura;
import model.RegistoLeituras;
import java.util.*;

public class Ponto2Controller {


    private TreeSet<String> paises;

    public Ponto2Controller(){
        paises = RegistoLeituras.getPaisesNasLeituras();
    }



    public LinkedList<Leitura> devolverResultados(){
        LinkedList<Leitura> resultado = new LinkedList<>();
        for(String pais : paises){
            if(RegistoLeituras.getPrimeiraLeituraAcimaDe50KPorPais(pais) != null){
             resultado.add(RegistoLeituras.getPrimeiraLeituraAcimaDe50KPorPais(pais));
            }
        }
    
        resultado.sort(new Comparator<Leitura>() {
            @Override
            public int compare(Leitura l1, Leitura l2) {
                return RegistoLeituras.contarDiasDesdeInicio(l1)-RegistoLeituras.contarDiasDesdeInicio(l2);

            }
        });
        return resultado;
    }


}
