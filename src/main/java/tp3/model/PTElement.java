package tp3.model;

public class PTElement {

    private int atomicNumber;
    private String elementName;
    private String symbol;
    private double atomicWeight;
    private double atomicMass;
    private int period;
    private int group;
    private String phase;
    private String mostStableCrystal;
    private String type;
    private double atomicRadius;
    private double electronegativity;
    private double firstIonizationPotential;
    private double density;
    private double meltingPoint;
    private double boilingPoint;
    private int isotopes;
    private String discoverer;
    private int yearOfDiscovery;
    private double specificHeatCapacity;
    private String electronConfiguration;
    private int displayRow;
    private int displayColumn;


    /**
     * Constrói um PTElement com toda a informação do elemento da tabela periódica
     * @param atomicNumber
     * @param elementName
     * @param symbol
     * @param atomicWeight
     * @param atomicMass
     * @param period
     * @param group
     * @param phase
     * @param mostStableCrystal
     * @param type
     * @param atomicRadius
     * @param electronegativity
     * @param firstIonizationPotential
     * @param density
     * @param meltingPoint
     * @param boilingPoint
     * @param isotopes
     * @param discoverer
     * @param yearOfDiscovery
     * @param specificHeatCapacity
     * @param electronConfiguration
     * @param displayRow
     * @param displayColumn
     */
    public PTElement(int atomicNumber, String elementName, String symbol, double atomicWeight, double atomicMass,
                     int period, int group, String phase, String mostStableCrystal, String type, double atomicRadius,
                     double electronegativity, double firstIonizationPotential, double density, double meltingPoint,
                     double boilingPoint, int isotopes, String discoverer, int yearOfDiscovery, double specificHeatCapacity,
                     String electronConfiguration, int displayRow, int displayColumn) {

        this.atomicNumber = atomicNumber;
        this.elementName = elementName;
        this.symbol = symbol;
        this.atomicWeight = atomicWeight;
        this.atomicMass = atomicMass;
        this.period = period;
        this.group = group;
        this.phase = phase;
        this.mostStableCrystal = mostStableCrystal;
        this.type = type;
        this.atomicRadius = atomicRadius;
        this.electronegativity = electronegativity;
        this.firstIonizationPotential = firstIonizationPotential;
        this.density = density;
        this.meltingPoint = meltingPoint;
        this.boilingPoint = boilingPoint;
        this.isotopes = isotopes;
        this.discoverer = discoverer;
        this.yearOfDiscovery = yearOfDiscovery;
        this.specificHeatCapacity = specificHeatCapacity;
        this.electronConfiguration = electronConfiguration;
        this.displayRow = displayRow;
        this.displayColumn = displayColumn;
    }


    //overload de construtores vs construtor null + métodos set??

    /**
     * Overload do construtor só com elementName (testes)
     * @param elementName
     */
    public PTElement(String elementName){
        this.elementName = elementName;
    }


    /**
     * Overload do construtor só com symbol (testes)
     * @param symbol
     * @param dummy variavel sem significado utilizado para permitir overload do construtor
     */
    public PTElement(String symbol, boolean dummy){
        this.symbol = symbol;
    }

    /**
     * Overload do construtor só com atomic number (testes)
     * @param atomicNumber
     */
    public PTElement(int atomicNumber){
        this.atomicNumber = atomicNumber;
    }

    /**
     * Overload do construtor só com atomic mass (testes)
     * @param atomicMass
     */
    public PTElement(double atomicMass){
        this.atomicMass = atomicMass;
    }



//getters para os compareTo
    public int getAtomicNumber() {
        return atomicNumber;
    }

    public String getElementName() {
        return elementName;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getAtomicMass() {
        return atomicMass;
    }

    @Override
    public String toString() {
        return "PTElement{" +
                "atomicNumber=" + atomicNumber +
                ", elementName='" + elementName + '\'' +
                '}';
    }
}
