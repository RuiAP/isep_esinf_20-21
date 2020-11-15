package controller;

import model.Leitura;
import model.RegistoLeituras;

import java.util.*;

public class Ponto4Controller {


    private TreeSet<String> paises;
    private TreeSet<String> continentes;


    public Ponto4Controller() {
        paises = RegistoLeituras.getPaisesNasLeituras();
        continentes = RegistoLeituras.getContinentesNasLeituras();
    }


    public LinkedList<Leitura> devolverResultados(int mes, String continente) {

        LinkedList<Leitura> leiturasMesContinente = RegistoLeituras.selecionaLeiturasMesContinente(mes, continente);

        LinkedList<Leitura> resultado = new LinkedList<>();

        leiturasMesContinente.sort(new Comparator<Leitura>() {
            @Override
            public int compare(Leitura l1, Leitura l2) {
                return l2.getNewCases()-l1.getNewCases();
            }
        });

        for (int i = 1; i<= 31; i++){
            for (Leitura l : leiturasMesContinente) {
                if (l.getDate().getDayOfMonth() == i) {
                    resultado.add(l);
                }
            }
        }

        return resultado;
    }
}