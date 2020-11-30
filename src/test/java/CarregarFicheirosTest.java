
import TP2.*;
import graph.AdjacencyMatrixGraph;
import graphbase.Graph;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;



@DisplayName("CarregarFicheirosTeste")
public class CarregarFicheirosTest {

    CarregarFicheiros carregarFicheiro = new CarregarFicheiros(true);

    Graph<Country, String> countriesGraph = carregarFicheiro.carregarGrafoCountries();
    AdjacencyMatrixGraph<User,Double> usersGraph = carregarFicheiro.carregarGrafoUsers();


    @Test
    @DisplayName("UploadUsersSmall")
    public void uploadUsersTest() {
        System.out.println("UploadUsersSmall");

        User u1 = new User("u1", 27, "brasilia");
        assertTrue("checks if first user(first line from susers.txt) was uploaded",usersGraph.checkVertex(u1));

        User u33 = new User("u33",48,"brasilia");
        assertTrue("checks if last user(last line from susers.txt) was uploaded",usersGraph.checkVertex(u33));

        assertEquals("number of vertices loaded from susers.txt has to be 26",26,usersGraph.numVertices());
    }



    @Test
    @DisplayName("UploadCountriesSmall")
    public void uploadCountriesTest() {
        System.out.println("UploadCountriesSmall");

        Country c1 = new Country("argentina","americasul",41.67,"buenosaires",-34.6131500,-58.3772300);
        assertTrue("checks if first country(first line from scountries.txt) was uploaded",countriesGraph.validVertex(c1));

        Country c13 = new Country("uruguai", "americasul", 3.35, "montevideu", -34.9032800, -56.1881600);
        assertTrue("checks if first country(first line from scountries.txt) was uploaded",countriesGraph.validVertex(c1));

        assertEquals("number of vertices loaded from scountries.txt has to be 13",13,countriesGraph.numVertices());
    }

    @Test
    @DisplayName("UploadRelationships")
        public void uploadRelationships(){
            System.out.println("uploadRelationshipsSmall");

            User u1 = new User("u1", 27, "brasilia");
            User u2 = new User("u2",18,"lapaz");

            //'u2 ,u1' is the first line in srelationships so this Edge should already exist
            assertFalse("this edge should already exist, thus the method should return false",usersGraph.insertEdge(u2, u1, 1.0));


            User u33 = new User("u33",48,"brasilia");
            User u32 = new User("u32",55,"brasilia");

            //'u33 ,u32' is the last line(with a viable connection) in srelationships so this Edge should already exist
            assertFalse("this edge should already exist, thus the method should return false",usersGraph.insertEdge(u33, u32, 1.0));

            assertEquals("number of edges loaded from srelationships.txt has to be 45(out of 78 lines)",45,usersGraph.numEdges());
        }

    @Test
    @DisplayName("UploadBorders")
    public void uploadBorders(){
        System.out.println("uploadBordersSmall");

        assertEquals("number of edges loaded from sborders.txt has to be 50",50,countriesGraph.numEdges());


        Country c1 = new Country("argentina","americasul",41.67,"buenosaires",-34.6131500,-58.3772300);
        Country c2 = new Country("bolivia","americasul",9.70,"lapaz",-16.5000000,-68.1500000);

        //'argentina, bolivia' is the first line in sborders so this Edge should already exist
        assertTrue("The edge should already exist so the method should return true", countriesGraph.removeEdge(c1,c2));


        Country c10 = new Country("suriname", "americasul", 0.04, "paramaribo", 5.8663800, -55.1668200);
        Country c7 = new Country("guianafrancesa", "americasul", 2.88, "caiena", 4.9333300, -52.3333300);

        //'guianafrancesa, suriname' is the last line in sborders so this Edge should already exist
        assertTrue("The edge should already exist so the method should return true", countriesGraph.removeEdge(c7,c10));

        //repõe a instancia original do grafo para poder ser usada nos restantes testes
        carregarFicheiro.carregarGrafoCountries();
    }

    @Test
    public void calculateDistanceBetween(){
        //valores parecem divergir ligeiramente dos retornados no site
        //http://www.movable-type.co.uk/scripts/latlong.html
    }

}