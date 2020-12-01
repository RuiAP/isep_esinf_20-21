package TP2;

import TP2.model.Country;
import TP2.model.User;
import com.bahadirarslan.jeodezi.Coordinate;
import com.bahadirarslan.jeodezi.GreatCircle;
import graph.AdjacencyMatrixGraph;
import graphbase.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static TP2.model.Constantes.*;

public class CarregarFicheiros {                            //refactor par eliminar partes dos metodos repetidos?

    private Graph<Country, String> countriesGraph;
    private AdjacencyMatrixGraph<User, Double> usersGraph;
    private String usersFilePath;
    private String relationshipsFilePath;
    private String countriesFilePath;
    private String bordersFilePath;


    /**
     * Construtor
     * De acordo com o parâmetro define os file paths para os ficheiros de texto a carregar.
     * @param ficheirosPequenos True para carregar ficheiro pequenos.
     *                          False para carregar os ficheiros grandes
     */
   public CarregarFicheiros(boolean ficheirosPequenos){

       countriesGraph = new Graph<Country, String>(false);

       if(ficheirosPequenos){
           usersGraph = new AdjacencyMatrixGraph<>();

           usersFilePath = S_USERS_PATH;
           relationshipsFilePath = S_RELATIONSHIP_PATH ;
           countriesFilePath = S_COUNTRIES_PATH ;
           bordersFilePath = S_BORDERS_PATH;
       }else{
           usersGraph = new AdjacencyMatrixGraph<>(1000);

           usersFilePath = BIG_USERS_PATH;
           relationshipsFilePath = BIG_RELATIONSHIP_PATH;
           countriesFilePath = BIG_COUNTRIES_PATH;
           bordersFilePath = BIG_BORDERS_PATH;
       }
   }



    /**
     * Devolve um grafo, que utiliza mapa de adjacências, para representar uma rede de cidades.
     * As capitais sao representadas como vertices e os ramos têm, como peso, as distâncias entre as capitais.
     * @return Graph<Country, String>
     */
    public Graph<Country, String> getCountriesGraph() {
        return countriesGraph;
    }

    /**
     * Devolve um grago, que utiliza matriz de adjacencias,para representar uma rede social.
     * Os utilizadores são representados como vertices e os ramos identificam uma relação de amizade entre utilizadores.
     * @return AdjacencyMatrixGraph<User, Double>
     */
    public AdjacencyMatrixGraph<User, Double> getUsersGraph() {
        return usersGraph;
    }




    /**
     * Carrega utilizadores(vertices) e relações() para o grafo a partir dos ficheiros .txt
     * @return AdjacencyMatrixGraph<User, Double> Grafo que utiliza matriz de adjacências
     */
   public AdjacencyMatrixGraph<User, Double> carregarGrafoUsers(){
       usersGraph = new AdjacencyMatrixGraph<>();
       uploadUsers();
       uploadRelationships();
       return getUsersGraph();
   }

    /**
     * Carrega paises(vertices) e fronteiras(ramos) para o grafo a partir dos ficheiros .txt
     * @return Graph<Country, String> Grafo que utiliza mapa de adjacências
     */
   public Graph<Country, String> carregarGrafoCountries(){
       countriesGraph = new Graph<Country, String>(false);
       uploadCountries();
       uploadBorders();
       return getCountriesGraph();
   }







    /**
     * Preenche os vertices do grafo de utilizadores (matriz de adjacências).
     * Carrega, a partir de um ficheiro de texto, uma lista de utilizadores que vão ser adicionados como vertices.
     * @returnAdjacencyMatrixGraph<User,Double>
     */
    private AdjacencyMatrixGraph<User,Double> uploadUsers(){

        int lineBeingRead = 0;
        Scanner in = null;

        try {

            in = new Scanner(new File(this.usersFilePath));
            String line;
            while (in.hasNextLine()) {
                lineBeingRead++;
                line = in.nextLine();
                String[] dados = line.split(",");

                User newUser = new User(
                        dados[0].replace(" ", ""),
                        Integer.parseInt(dados[1]),
                        dados[2].replace(" ", ""));

                usersGraph.insertVertex(newUser);

            }

            return usersGraph;

        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de dados não encontrado.");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao ler a linha " +lineBeingRead+".");
        } catch (Exception e) {
            System.out.println("Erro ao ler o ficheiro.");
        }finally {
            if(in != null) in.close();
        }
        return usersGraph;
    }

    /**
     * Carrega as relações entre utilizadores (ramos do grafo) a partir de um ficheiro .txt
     */
    private void  uploadRelationships(){

        int initialEdges = usersGraph.numEdges();
        int lineBeingRead = 0;
        User vOrig;
        User vDest;
        Scanner in = null;
        try {

            in = new Scanner(new File(relationshipsFilePath));
            String line;
            while (in.hasNextLine()) {
                lineBeingRead++;
                line = in.nextLine();
                String[] dados = line.split(",");

                vOrig = null;
                vDest = null;

                //itera pelos vertices do grafo para verificar se os users existem no grafo
                for(User vertice : usersGraph.vertices()){
                    if(vertice.getUserId().equals(dados[0].replace(" ", ""))){
                        vOrig = vertice;
                    }
                    if (vertice.getUserId().equals(dados[1].replace(" ", ""))) {
                        vDest = vertice;
                    }
                }
                //se encontrar os dois utilizadores no grafo adiciona a relação entre eles
                if(vOrig != null && vDest != null){
                    usersGraph.insertEdge(vOrig, vDest,  1.0);
                }
            }


            if(usersGraph.numEdges() == initialEdges){
                throw new IllegalArgumentException();
            }


        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de dados não encontrado.");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao ler a linha " +lineBeingRead+".");
        } catch (IllegalArgumentException e) {
            System.out.println("Não foram carregadas nenhumas relações.");
        } catch (Exception e) {
            System.out.println("Erro ao ler o ficheiro.");
        }finally {
            if(in != null) in.close();
        }
    }

    /**
     * Preenche os vértices do grafo de cidades (mapa de adjacências).
     * Carrega,a partir de um ficheiro de texto, uma lista de países que vão ser adicionados como vertices.
     * @return Graph Graph<V,E>
     */
    private Graph<Country,String> uploadCountries(){

        int lineBeingRead = 0;
        Scanner in = null;

        try {

            in = new Scanner(new File(this.countriesFilePath));
            String line;
            while (in.hasNextLine()) {
                lineBeingRead++;
                line = in.nextLine();
                String[] dados = line.split(",");

                Country newCountry = new Country(
                        dados[0].replace(" ", ""),
                        dados[1].replace(" ", ""),
                        Double.parseDouble(dados[2]),
                        dados[3].replace(" ", ""),
                        Double.parseDouble(dados[4]),
                        Double.parseDouble(dados[5]));
                countriesGraph.insertVertex(newCountry);

            }

            return countriesGraph;

        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de dados não encontrado.");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao ler a linha " +lineBeingRead+".");
        } catch (Exception e) {
            System.out.println("Erro ao ler o ficheiro.");
        }finally {
            if(in != null) in.close();
        }
        return countriesGraph;
    }


    /**
     * Cria os ramos do grafo a partir de de uma lista de fronteiras presentes num ficheiro .txt
     * Ramos têm peso igual à distância, em Km, entre as capitais de cada país(Country).
     */
    private void  uploadBorders(){

        int initialEdges = countriesGraph.numEdges();
        int lineBeingRead = 0;
        Country vOrig;
        Country vDest;

        Scanner in = null;
        try {

            in = new Scanner(new File(bordersFilePath));
            String line;
            while (in.hasNextLine()) {
                lineBeingRead++;
                line = in.nextLine();
                String[] dados = line.split(",");

                vOrig = null;
                vDest = null;

                //itera pelos vertices do grafo para verifica se os paises existem no grafo
                for(Country pais : countriesGraph.vertices()){
                    if(pais.getCountryName().equals(dados[0].replace(" ", ""))){
                        vOrig = pais;
                    }
                    if (pais.getCountryName().equals(dados[1].replace(" ", ""))) {
                        vDest = pais;
                    }
                }
                //se encontrar os dois utilizadores(que estão na linha do ficheiro .txt) na lista de vertices do grafo
                //então calcula a distancia entre as coordenadas e insere o ramo no grafo
                if(vOrig != null && vDest != null){
                    double distance = calculateDistanceBetween(vOrig, vDest);
                    countriesGraph.insertEdge(vOrig, vDest,  "",distance);
                }
            }


            if(countriesGraph.numEdges() == initialEdges){
                throw new IllegalArgumentException();
            }


        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de dados não encontrado.");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao ler a linha " +lineBeingRead+".");
        } catch (IllegalArgumentException e) {
            System.out.println("Não foram carregadas nenhumas fronteiras.");
        } catch (Exception e) {
            System.out.println("Erro ao ler o ficheiro.");
        }finally {
            if(in != null) in.close();
        }
    }


    /**
     * Usa biblioteca externa para calcular a distância entre dois objetos Country
     * @param country1 Country origem
     * @param country2 Country destino
     * @return double com a distância, em Km, entre o country1 e country2
     */
    private double calculateDistanceBetween(Country country1, Country country2){
        GreatCircle globe = new GreatCircle();
        Coordinate c1 = new Coordinate(country1.getLatitude(), country1.getLongitude());
        Coordinate c2 = new Coordinate( country2.getLatitude(), country2.getLongitude());
        return globe.distance(c1, c2);
    }



}


