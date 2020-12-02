package TP2;

import TP2.controller.Controller;
import TP2.model.Country;
import TP2.model.User;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


class ControllerTest {


    Controller controller = new Controller();


    public ControllerTest(){
    }

/*

    @Test
    void testP1CriarGrafos() {
    }

    @Test
    void testP2CalcularAmigosComuns() {
    }

    @Test
    void testP3CalcularDiametroGrafo() {
    }

    @Test
    void testP4CalcularAmigosProximos() {
    }

    @Test
    void testP5() {
    }

    @Test
    void testP6CaminhoTerrestre() {
    }

*/
    @Test
    void testCheckVertexByUserId() {
        System.out.println("testCheckVertexByUserId");

        User user1 = new User ("u1", 27, "brasilia");
        assertEquals(user1, controller.checkVertexByUserId("u1"));

        User user33 = new User ("u33", 48, "brasilia");
        assertEquals(user33, controller.checkVertexByUserId("u33"));

        //userId "specialUser200" não corresponde a nenhum user
        assertNull(controller.checkVertexByUserId("specialUser200"));
    }

    @Test
    void testCheckVertexByCapitalName() {
        System.out.println("testCheckVertexByCapitalName");

        Country c1 = new Country("argentina","americasul",
                41.67,"buenosaires",-34.6131500,-58.3772300);
        assertEquals(c1, controller.checkVertexByCapitalName("buenosaires"));

        Country c13 = new Country("uruguai", "americasul",
                3.35, "montevideu", -34.9032800, -56.1881600);
        assertEquals(c13, controller.checkVertexByCapitalName("montevideu"));


        assertNull(controller.checkVertexByCapitalName("Porto"));

    }



}