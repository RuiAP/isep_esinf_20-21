import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

public class CarregarFicheiros {


   public CarregarFicheiros(){
   }


    public LinkedList<User> uploadUsers(){

        LinkedList<User> usersCarregados = new LinkedList<>();

        Scanner in = null;

        try {

            in = new Scanner(new File("src/main/resources/small-network/susers.txt"));
            String line;
            while (in.hasNextLine()) {
                line = in.nextLine();
                String[] dados = line.split(",");

                User tempUser = new User(dados[0], Integer.parseInt(dados[1]), dados[2]);
               usersCarregados.add(tempUser);
            }

            return usersCarregados;

        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de dados não encontrado.");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao ler a linha " + usersCarregados.size()+".");
        } catch (Exception e) {
            System.out.println("Erro ao ler o ficheiro.");
        }finally {
            if(in != null) in.close();
        }
        return usersCarregados;
    }


    public LinkedList<Country> uploadCountries(){

        LinkedList<Country> countriesCarregados = new LinkedList<>();

        Scanner in = null;

        try {

            in = new Scanner(new File("src/main/resources/small-network/scountries.txt"));
            String line;
            while (in.hasNextLine()) {
                line = in.nextLine();
                String[] dados = line.split(",");

                Country tempCountry = new Country(
                    dados[0].replace(" ",""),
                    dados[1].replace(" ",""),
                    Double.parseDouble(dados[2]),
                    dados[3].replace(" ",""),
                    Double.parseDouble(dados[4]),
                    Double.parseDouble(dados[5])
                );

                    countriesCarregados.add(tempCountry);
            }

            return countriesCarregados;

        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de dados não encontrado.");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao ler a linha " + countriesCarregados.size()+".");
        } catch (Exception e) {
            System.out.println("Erro ao ler o ficheiro.");
        }finally {
            if(in != null) in.close();
        }
        return countriesCarregados;
    }



}


