package tp3.model;

import PL.BST;
import PL.TREE_WORDS;
import PL.TextWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class CarregarFicheiro {

    private BST<S_AMass> bstAMass;
    private BST<S_ANumber> bstANumber;
    private BST<S_ElementName> bstElementName;
    private BST<S_Symbol> bstSymbol;
    private TREE_WORDS bstConfig;

    public static final String CSV_FILE_PATH = "src/main/resources/Periodic_Table_of_Elements.CSV";
    public static final int REPLACE_EMPTY = -1;

    private int atomicNumber;
    private double atomicWeight;
    private double atomicMass;
    private int period;
    private int group;
    private double ionicRadius;
    private double atomicRadius;
    private double electronegativity;
    private double firstIonizationPotential;
    private double density;
    private double meltingPoint;
    private double boilingPoint;
    private int isotopes;
    private int yearOfDiscovery;
    private double specificHeatCapacity;
    private int displayRow;
    private int displayColumn;


    public CarregarFicheiro() {
        bstAMass = new BST<>();
        bstANumber = new BST<>();
        bstElementName = new BST<>();
        bstSymbol = new BST<>();
        bstConfig = new TREE_WORDS();
    }


    public void carregarFicheiroCSV() {


        int lineBeingRead = 0;
        Scanner in = null;
        BufferedReader br = null;

        try {

                br = new BufferedReader(new FileReader(new File(CSV_FILE_PATH)));
                String line;
                br.readLine();
                while((line = br.readLine()) != null) {

                lineBeingRead++;
                String[] dados = line.split(",");

                atomicNumber = dados[0].isEmpty()? REPLACE_EMPTY : Integer.parseInt(dados[0]);
                atomicWeight = dados[3].isEmpty()? REPLACE_EMPTY : Double.parseDouble(dados[3]);
                atomicMass = dados[4].isEmpty()? REPLACE_EMPTY : Double.parseDouble(dados[4]);
                period = dados[5].isEmpty()? REPLACE_EMPTY : Integer.parseInt(dados[5]);
                group = dados[6].isEmpty()? REPLACE_EMPTY : Integer.parseInt(dados[6]);
                ionicRadius = dados[10].isEmpty()? REPLACE_EMPTY : Double.parseDouble(dados[10]);
                atomicRadius = dados[11].isEmpty()? REPLACE_EMPTY : Double.parseDouble(dados[11]);
                electronegativity = dados[12].isEmpty()? REPLACE_EMPTY : Double.parseDouble(dados[12]);
                firstIonizationPotential = dados[13].isEmpty()? REPLACE_EMPTY : Double.parseDouble(dados[13]);
                density = dados[14].isEmpty()? REPLACE_EMPTY : Double.parseDouble(dados[14]);
                meltingPoint = dados[15].isEmpty()? REPLACE_EMPTY : Double.parseDouble(dados[15]);
                boilingPoint = dados[16].isEmpty()? REPLACE_EMPTY : Double.parseDouble(dados[16]);
                isotopes = dados[17].isEmpty()? REPLACE_EMPTY : Integer.parseInt(dados[17]);
                yearOfDiscovery = dados[19].isEmpty()? REPLACE_EMPTY : Integer.parseInt(dados[19]);
                specificHeatCapacity = dados[20].isEmpty()? REPLACE_EMPTY : Double.parseDouble(dados[20]);
                displayRow = dados[22].isEmpty()? REPLACE_EMPTY : Integer.parseInt(dados[22]);
                displayColumn = dados[23].isEmpty()? REPLACE_EMPTY : Integer.parseInt(dados[23]);


                PTElement pte = new PTElement(
                        atomicNumber,
                        dados[1],
                        dados[2],
                        atomicWeight,
                        atomicMass,
                        period,
                        group,
                        dados[7],
                        dados[8],
                        dados[9],
                        ionicRadius,
                        atomicRadius,
                        electronegativity,
                        firstIonizationPotential,
                        density,
                        meltingPoint,
                        boilingPoint,
                        isotopes,
                        dados[18],
                        yearOfDiscovery,
                        specificHeatCapacity,
                        dados[21],
                        displayRow,
                        displayColumn
                );

                S_AMass s1 = new S_AMass(pte);
                bstAMass.insert(s1);

                S_ANumber s2 = new S_ANumber(pte);
                bstANumber.insert(s2);

                S_ElementName s3 = new S_ElementName(pte);
                bstElementName.insert(s3);

                S_Symbol s4 = new S_Symbol(pte);
                bstSymbol.insert(s4);

                TextWord t1 = new TextWord(dados[21], 1);
                bstConfig.insert(t1);
            }


        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de dados não encontrado.");
        } catch (NumberFormatException e) {
            System.out.println("Erro de formatação na linha " + lineBeingRead + ".");
        } catch (Exception e) {
            System.out.println("Erro genérico na linha " +lineBeingRead + ".");
        } finally {
            if (in != null) in.close();
        }

    }

    public BST<S_AMass> getBstAMass() {
        return bstAMass;
    }

    public BST<S_ANumber> getBstANumber() {
        return bstANumber;
    }

    public BST<S_ElementName> getBstElementName() {
        return bstElementName;
    }

    public BST<S_Symbol> getBstSymbol() {
        return bstSymbol;
    }

    public TREE_WORDS getBstConfig() {
        return bstConfig;
    }
}
