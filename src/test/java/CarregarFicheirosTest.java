import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//@DisplayName("CarregarFicheirosTeste")
class CarregarFicheirosTest {

    CarregarFicheiros carregarFicheiro = new CarregarFicheiros();


    @Test
    @DisplayName("UploadUsersSmall - First rows")
    public void uploadUsersTest(){
        LinkedList<User> expectedList = new LinkedList<>();
        User u1 = new User ("u1",27,"brasilia");
        User u2 = new User ("u2",18,"lapaz");
        User u3 = new User ("u3",20,"quito");
        expectedList.add(u1);
        expectedList.add(u2);
        expectedList.add(u3);

        LinkedList<User> uploadedList = carregarFicheiro.uploadUsers();
        List<User> resultList = uploadedList.subList(0,3);

    assertIterableEquals(expectedList,resultList);
    }

    @Test
    @DisplayName("uploadUsersSmall - Last row")
    public void uploadUsersTest2(){
        User expectedUser = new User ("u33",48,"brasilia");

        LinkedList<User> uploadedList = carregarFicheiro.uploadUsers();
        User resultUser = uploadedList.getLast();

        assertEquals(expectedUser, resultUser);
    }


    @Test
    @DisplayName("UploadCountriesSmall - First rows")
    public void uploadCountriesTest(){
        LinkedList<Country> expectedList = new LinkedList<>();
        Country c1 = new Country ("argentina" ,"americasul",41.67,"buenosaires",-34.6131500,-58.3772300);
        Country c2 = new Country ("bolivia","americasul",9.70,"lapaz",-16.5000000,-68.1500000);
        Country c3 = new Country ("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);

        expectedList.add(c1);
        expectedList.add(c2);
        expectedList.add(c3);

        LinkedList<Country> uploadedList = carregarFicheiro.uploadCountries();
        List<Country> resultList = uploadedList.subList(0,3);

        assertIterableEquals(expectedList,resultList);
    }


    @Test
    @DisplayName("uploadCountriesSmall - Last row")
    public void uploadCountriesTest2(){
        Country expectedCountry = new Country ("uruguai", "americasul", 3.35, "montevideu", -34.9032800, -56.1881600);


        LinkedList<Country> uploadedList = carregarFicheiro.uploadCountries();
        Country resultCountry = uploadedList.getLast();

        assertEquals(expectedCountry, resultCountry);
    }
}