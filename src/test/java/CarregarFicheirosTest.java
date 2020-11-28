import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.LinkedList;
import java.util.List;


@DisplayName("CarregarFicheirosTeste")
public class CarregarFicheirosTest {

    CarregarFicheiros carregarFicheiro = new CarregarFicheiros();
    LinkedList<User> usersSCarregados = carregarFicheiro.uploadUsers();
    LinkedList<Country> countriesSCarregados = carregarFicheiro.uploadCountries();


    @Test
    @DisplayName("UploadUsersSmall")
    public void uploadUsersTest() {
        System.out.println("UploadUsersSmall");

        LinkedList<User> expectedList = new LinkedList<>();
        User u1 = new User("u1", 27, "brasilia");
        User u2 = new User("u2", 18, "lapaz");
        User u3 = new User("u3", 20, "quito");
        expectedList.add(u1);
        expectedList.add(u2);
        expectedList.add(u3);


        List<User> resultList = usersSCarregados.subList(0, 3);
        //testa os 3 primeiros elementos carregados
        assertIterableEquals(expectedList, resultList);

        User expectedUser = new User("u33", 48, "brasilia");
        User resultUser = usersSCarregados.getLast();
        //testa o ultimo elemento carregado
        assertEquals(expectedUser, resultUser);
    }


    @Test
    @DisplayName("UploadCountriesSmall")
    public void uploadCountriesTest() {
        System.out.println("UploadCountriesSmall");
        LinkedList<Country> expectedList = new LinkedList<>();
        Country c1 = new Country("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
        Country c2 = new Country("bolivia", "americasul", 9.70, "lapaz", -16.5000000, -68.1500000);
        Country c3 = new Country("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);

        expectedList.add(c1);
        expectedList.add(c2);
        expectedList.add(c3);

        List<Country> resultList = countriesSCarregados.subList(0, 3);
        //testa os 3 primeiros elementos
        assertIterableEquals(expectedList, resultList);


        Country expectedCountry = new Country("uruguai", "americasul", 3.35, "montevideu", -34.9032800, -56.1881600);
        Country resultCountry = countriesSCarregados.getLast();
        //testa o ultimo elemento carregado
        assertEquals(expectedCountry, resultCountry);
    }

}