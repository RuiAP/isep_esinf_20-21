package controller;

import static model.Constantes.*;
import model.Leitura;
import model.RegistoLeituras;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;


public class CarregarFicheiroController {

    private String isoCode;
    private String continent;
    private String country;
    private LocalDate date;
    private int totalCases;
    private int newCases;
    private int totalDeaths;
    private int newDeaths;
    private int newTests;
    private int totalTests;
    private int population;
    private double aged65orOLder;
    private double cardiovascDeathRate;
    private double diabetesPrevalence;
    private double femaleSmokers;
    private double maleSmokers;
    private double hospitalBedsPerThousand;
    private double lifeExpectancy;




    public CarregarFicheiroController(){
    }


    public boolean carregarFicheiro(){

        LinkedList<Leitura> dadosCarregados = new LinkedList<>();

        Scanner in = null;
        File teste;
        try {
            TreeSet<String> continentesCarregados = new TreeSet<>();
            TreeSet<String> paisesCarregados = new TreeSet<>();

            teste = new File(DATA_FILE_PATH);
            in = new Scanner(teste);

            String line = in.nextLine(); //remove a primeira linha com a designação dos valores

            while (in.hasNextLine()) {

                line = in.nextLine();
                String[] dadosCSV = line.split(",");

                //ou iterar só sobre os 3 primeiros indices
                for(int i = 0; i<dadosCSV.length; i++){
                    dadosCSV[i] = dadosCSV[i].replace("\"", "");
                }



                isoCode = dadosCSV[0].equalsIgnoreCase("NA")? ""+ REPLACEMENT_FOR_NA : dadosCSV[0];
                continent = dadosCSV[1].equalsIgnoreCase("NA")? ""+REPLACEMENT_FOR_NA : dadosCSV[1];
                country = dadosCSV[2].equalsIgnoreCase("NA")? ""+REPLACEMENT_FOR_NA : dadosCSV[2];
                date = LocalDate.parse(dadosCSV[3]);
                totalCases = dadosCSV[4].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Integer.parseInt(dadosCSV[4]);
                newCases = dadosCSV[5].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Integer.parseInt(dadosCSV[5]);
                totalDeaths = dadosCSV[6].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Integer.parseInt(dadosCSV[6]);
                newDeaths = dadosCSV[7].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Integer.parseInt(dadosCSV[7]);
                newTests = dadosCSV[8].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Integer.parseInt(dadosCSV[8]);
                totalTests = dadosCSV[9].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Integer.parseInt(dadosCSV[9]);
                population = dadosCSV[10].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Integer.parseInt(dadosCSV[10]);
                aged65orOLder = dadosCSV[11].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Double.parseDouble(dadosCSV[11]);
                cardiovascDeathRate = dadosCSV[12].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Double.parseDouble(dadosCSV[12]);
                diabetesPrevalence = dadosCSV[13].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Double.parseDouble(dadosCSV[13]);
                femaleSmokers = dadosCSV[14].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Double.parseDouble(dadosCSV[14]);
                maleSmokers = dadosCSV[15].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Double.parseDouble(dadosCSV[15]);
                hospitalBedsPerThousand = dadosCSV[16].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Double.parseDouble(dadosCSV[16]);
                lifeExpectancy = dadosCSV[17].equalsIgnoreCase("NA")? REPLACEMENT_FOR_NA : Double.parseDouble(dadosCSV[17]);


                Leitura dadosLeitura = new Leitura(
                  isoCode, continent, country, date, totalCases, newCases, totalDeaths, newDeaths,
                  newTests, totalTests,
                  population, aged65orOLder,
                  cardiovascDeathRate, diabetesPrevalence,
                  femaleSmokers, maleSmokers,
                  hospitalBedsPerThousand,
                  lifeExpectancy
                );

                 dadosCarregados.add(dadosLeitura);
                 continentesCarregados.add(continent);
                 paisesCarregados.add(country);


            }
            RegistoLeituras.carregarDadosFicheiro(dadosCarregados);
            RegistoLeituras.setContinentesNasLeituras(continentesCarregados);
            RegistoLeituras.setPaisesNasLeituras(paisesCarregados);
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de dados não encontrado.");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Erro ao ler a linha " + dadosCarregados.size()+".");
        } catch (Exception e) {
            System.out.println("Erro ao ler o ficheiro.");
        }finally {
            if(in != null) in.close();
        }
    return false;
    }
}
