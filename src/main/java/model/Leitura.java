package model;

import java.time.LocalDate;

public class Leitura {

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

    public Leitura(String isoCode, String continent, String country, LocalDate date, int totalCases, int newCases,
                   int totalDeaths, int newDeaths, int newTests, int totalTests, int population, double aged65orOLder,
                   double cardiovascDeathRate, double diabetesPrevalence, double femaleSmokers, double maleSmokers,
                   double hospitalBedsPerThousand, double lifeExpectancy) {

        this.isoCode = isoCode;
        this.continent = continent;
        this.country = country;
        this.date = date;
        this.totalCases = totalCases;
        this.newCases = newCases;
        this.totalDeaths = totalDeaths;
        this.newDeaths = newDeaths;
        this.newTests = newTests;
        this.totalTests = totalTests;
        this.population = population;
        this.aged65orOLder = aged65orOLder;
        this.cardiovascDeathRate = cardiovascDeathRate;
        this.diabetesPrevalence = diabetesPrevalence;
        this.femaleSmokers = femaleSmokers;
        this.maleSmokers = maleSmokers;
        this.hospitalBedsPerThousand = hospitalBedsPerThousand;
        this.lifeExpectancy = lifeExpectancy;
    }

    public String getContinent(){
        return this.continent;
    }
    public int getMonth(){
        return date.getMonthValue();
    }

    public int getNewCases() {
        return newCases;
    }

    public int getNewDeaths() {
        return newDeaths;
    }
    public int getTotalCases(){
        return totalCases;
    }

    public String getCountry() {
        return country;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getNewTests() {
        return newTests;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public int getPopulation() {
        return population;
    }

    public double getAged65orOLder() {
        return aged65orOLder;
    }

    public double getCardiovascDeathRate() {
        return cardiovascDeathRate;
    }

    public double getDiabetesPrevalence() {
        return diabetesPrevalence;
    }

    public double getFemaleSmokers() {
        return femaleSmokers;
    }

    public double getMaleSmokers() {
        return maleSmokers;
    }

    public double getHospitalBedsPerThousand() {
        return hospitalBedsPerThousand;
    }

    public double getLifeExpectancy() {
        return lifeExpectancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leitura leitura = (Leitura) o;
        return getTotalCases() == leitura.getTotalCases() &&
                getNewCases() == leitura.getNewCases() &&
                getTotalDeaths() == leitura.getTotalDeaths() &&
                getNewDeaths() == leitura.getNewDeaths() &&
                getNewTests() == leitura.getNewTests() &&
                getTotalTests() == leitura.getTotalTests() &&
                getPopulation() == leitura.getPopulation() &&
                Double.compare(leitura.getAged65orOLder(), getAged65orOLder()) == 0 &&
                Double.compare(leitura.getCardiovascDeathRate(), getCardiovascDeathRate()) == 0 &&
                Double.compare(leitura.getDiabetesPrevalence(), getDiabetesPrevalence()) == 0 &&
                Double.compare(leitura.getFemaleSmokers(), getFemaleSmokers()) == 0 &&
                Double.compare(leitura.getMaleSmokers(), getMaleSmokers()) == 0 &&
                Double.compare(leitura.getHospitalBedsPerThousand(), getHospitalBedsPerThousand()) == 0 &&
                Double.compare(leitura.getLifeExpectancy(), getLifeExpectancy()) == 0 &&
                getIsoCode().equals(leitura.getIsoCode()) &&
                getContinent().equals(leitura.getContinent()) &&
                getCountry().equals(leitura.getCountry()) &&
                getDate().equals(leitura.getDate());
    }

}
