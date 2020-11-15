package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class RegistoLeituras {



    private static LinkedList<Leitura> leituras;
    private static TreeSet<String> continentesNasLeituras;
    private static TreeSet<String> paisesNasLeituras;



    public static void carregarDadosFicheiro(LinkedList<Leitura> dados) {
        RegistoLeituras.leituras = dados;
    }

    public static LinkedList<Leitura> getLeituras() {
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

    /**
     * Devolve o numero de dias entre 2020-01-01 (inicio da pandemia) e a data da Leitura passada por parametro.
     * @param l Leitura que contém a data a comparar com 2020-01-01
     * @return int contendo o numero de dias entre as datas
     */
    public static int contarDiasDesdeInicio(Leitura l){
        LocalDate dataInicio = LocalDate.parse("2020-01-01");
        return (int) ChronoUnit.DAYS.between(dataInicio, l.getDate());
    }


    public static LinkedList<Leitura> selecionaLeiturasMesContinente(int mes, String continente) {
        LinkedList<Leitura> resultado = new LinkedList<>();
        for(Leitura l : leituras){
            if(l.getContinent().equalsIgnoreCase(continente) && l.getDate().getMonthValue() == mes){
                resultado.add(l);
            }
        }
        return resultado;
    }

    public static int totalNovasMortesPorPais(String country) {
        int somatorioNovasMortes = 0;
        for (Leitura l : leituras){
            if(l.getCountry().equalsIgnoreCase(country)){
                somatorioNovasMortes += l.getNewDeaths();
            }
        }
        return somatorioNovasMortes;
    }
}
