package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import static java.time.temporal.ChronoUnit.DAYS;

public class RegistoLeituras {


    private static ArrayList<Leitura> leituras;
    private static TreeSet<String> continentesNasLeituras;
    private static TreeSet<String> paisesNasLeituras;


    public static void carregarDadosFicheiro(ArrayList<Leitura> dados) {
        RegistoLeituras.leituras = dados;
    }

    public static ArrayList<Leitura> getLeituras() {
        return leituras;
    }

    public static void setContinentesNasLeituras(TreeSet<String> continentesNasLeituras) {
        RegistoLeituras.continentesNasLeituras = continentesNasLeituras;
    }

    public static void setPaisesNasLeituras(TreeSet<String> paisesNasLeituras) {
        RegistoLeituras.paisesNasLeituras = paisesNasLeituras;
    }

    public static TreeSet<String> getContinentesNasLeituras() {
        return continentesNasLeituras;
    }

    public static TreeSet<String> getPaisesNasLeituras() {
        return paisesNasLeituras;
    }

    public static Leitura getPrimeiraLeituraAcimaDe50KPorPais(String pais) { //era melhor isto ser por pais e passar o pais ou leitura por parametro??
        Map<Leitura, Integer> resultado = new TreeMap<>();

        for (Leitura l : leituras) {
            if (l.getCountry().equalsIgnoreCase(pais) && l.getTotalCases() >= 50000) {
                return l;
                }
            }
        return null;
        }

    public static int contarDiasDesdeInicio(Leitura l){
        LocalDate dataInicio = LocalDate.parse("2020-01-01");
        return (int) ChronoUnit.DAYS.between(dataInicio, l.getDate());
    }

}
