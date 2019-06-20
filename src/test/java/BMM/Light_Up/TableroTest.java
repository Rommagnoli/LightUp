/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BMM.Light_Up;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * Implementacion de tests para probar la clase Tablero. 
 * @author Boaglio, Agustin.
 * @author Menendez, Josue.
 * @author Magnoli, Roman.
 */
public class TableroTest{
   
    
    /**
     * Se fija si un tablero es valido.
     */
    @Test
    public void testTabValido(){
        Tablero tab = new Tablero();
        Boolean res = tab.tableroValido();
        assertEquals(true, res);
    }
    
    /**
     * Sirve para ver si tira una excepcion al tratar de poner una celda negra
     * en una posicion incorrecta.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testAgregarCeldaNegra(){
        Tablero tablero = new Tablero();
        int posInvalida = 50;
        tablero.nuevaCeldaNegra(posInvalida);
    }
    
    /**
     * Corrobora que la funcion nuevaCeldaNegra funcione correctamente al insertar
     * una celda negra en un tablero.
     */
    @Test
    public void nuevaCeldaNegra(){
        Tablero tablero = new Tablero();
        Tablero tab = new Tablero();
        Celdas celda = new Celdas();
        celda.setCeldaNegra();
        tab.getTablero()[5] = celda;
        tablero.nuevaCeldaNegra(5);
        Boolean res = tab.getTablero()[5].equals(tablero.getTablero()[5]);
        assertTrue(res);
    }
    
    /**
     * Corrobora que la funcion nuevaCeldaNegraValor funcione correctamente al 
     * insertar una celda negra con valor en el tablero en una posicion correcta.
     */
    @Test
    public void nuevaCeldaNegraValor(){
        Tablero tablero = new Tablero();
        Tablero tab = new Tablero();
        Celdas celda = new Celdas();
        celda.setCeldaNegra(3);
        tab.getTablero()[5] = celda;
        tablero.nuevaCeldaNegra(5, 3);
        Boolean res = tab.equals(tablero);
        assertTrue(res);
    }
    
    /**
     * Test que corrobora que cuando se ingrese una lampara en una posicion
     * invalida tire una excepcion.
     */
    @Test(expected = IllegalArgumentException.class)
    public void nuevaLamparaTest1(){
        Tablero tablero = new Tablero();
        tablero.nuevaLampara(50);
    }
    
    /**
     * Test que corrobora que cuando se ingrese una lampara en una posicion
     * donde ya es luz tire una excepcion.
     */
    @Test(expected = IllegalArgumentException.class)
    public void nuevaLamparaTest2(){
        Tablero tablero = new Tablero();
        tablero.nuevaLampara(0);
        tablero.nuevaLampara(5);
    }
    
    /**
     * Test que corrobora si al ingresar una lampara en una posicion donde ya
     * hay una celda negra tire una excepcion.
     */
    @Test(expected = IllegalArgumentException.class)
    public void nuevaLamparaTest3(){
        Tablero tablero = new Tablero();
        tablero.nuevaCeldaNegra(3);
        tablero.nuevaLampara(3);
    }
   
}
