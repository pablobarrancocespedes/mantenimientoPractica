/*
 * Autores:
 * Pablo Barranco Cespedes
 * Raquel Ferreira Macayo
 */

package deque;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LinkedNodeTest {

    @Test
    @DisplayName("El constructor funciona correctamente")
    public void linkedNodeTest_returnTrue(){
        LinkedNode<String> nodo = new LinkedNode<String>("hola", null, null);
        assertEquals("hola", nodo.getItem());
        assertEquals(null, nodo.getPrevious());
        assertEquals(null, nodo.getNext());
    }

    @Test
    @DisplayName("setItem cambia el valor del item")
    public void setItemTest_returnTrue(){
        LinkedNode<String> nodo = new LinkedNode<String>("hola", null, null);
        nodo.setItem("adios");
        assertEquals("adios", nodo.getItem());
    }

    @Test
    @DisplayName("setPrevious cambia el item previo")
    public void setPreviousTest_returnTrue(){
        LinkedNode<String> nodo = new LinkedNode<String>("hola", null, null);      
        LinkedNode<String> nodoPrevio = new LinkedNode<String>("adios", null, null);
        nodo.setPrevious(nodoPrevio);
        assertEquals(nodoPrevio, nodo.getPrevious());
    }

    @Test
    @DisplayName("setNext cambia el item siguiente")
    public void setNextTest_returnTrue(){
        LinkedNode<String> nodo = new LinkedNode<String>("hola", null, null);      
        LinkedNode<String> nodoSiguiente = new LinkedNode<String>("adios", null, null);
        nodo.setNext(nodoSiguiente);
        assertEquals(nodoSiguiente, nodo.getNext());
    }

    @Test
    @DisplayName("isFirstNode devuelve true al ser el primero")
    public void isFirstNodeTest_returnTrue(){
        LinkedNode<String> nodo = new LinkedNode<String>("hola", null, null); 
        assertEquals(true, nodo.isFirstNode());
    }

    @Test
    @DisplayName("isLastNode devuelve true al ser el ultimo")
    public void isLastNodeTest_returnTrue(){
        LinkedNode<String> nodo = new LinkedNode<String>("hola", null, null); 
        assertEquals(true, nodo.isLastNode());
    }

    @Test
    @DisplayName("isNotATerminalNode devuelve true al no ser ni el primero ni el ultimo")
    public void isNotATerminalNodeTest_returnTrue(){
        LinkedNode<String> nodoPrevio = new LinkedNode<String>("adios", null, null);
        LinkedNode<String> nodo = new LinkedNode<String>("hola", nodoPrevio, null); 
        nodoPrevio.setNext(nodo);
        LinkedNode<String> nodoSiguiente = new LinkedNode<String>("adios", nodo, null);
        nodo.setNext(nodoSiguiente);
        assertEquals(true, nodo.isNotATerminalNode());
    }

    @Test
    @DisplayName("isNotATerminalNode devuelve false al ser el primero y ultimo")
    public void isNotATerminalNodeTest_ultimoNodo_returnFalse(){
        LinkedNode<String> nodo = new LinkedNode<String>("hola", null, null); 
        assertEquals(false, nodo.isNotATerminalNode());
    }

    @Test
    @DisplayName("isNotATerminalNode devuelve false al no ser el primero pero ser el ultimo")
    public void isNotATerminalNodeTest_UltimoPeroNoPrimero_returnFalse(){
        LinkedNode<String> nodoPrevio = new LinkedNode<String>("adios", null, null);
        LinkedNode<String> nodo = new LinkedNode<String>("hola", nodoPrevio, null); 
        nodoPrevio.setNext(nodo);
        assertEquals(false, nodo.isNotATerminalNode());
    }

    @Test
    @DisplayName("isNotATerminalNode devuelve false al no ser el ultimo pero ser el primero")
    public void isNotATerminalNodeTest_PrimeroPeroNoUltimo_returnFalse(){
        LinkedNode<String> nodo = new LinkedNode<String>("hola", null, null); 
        LinkedNode<String> nodoSiguiente = new LinkedNode<String>("adios", nodo, null);
        nodo.setNext(nodoSiguiente);
        assertEquals(false, nodo.isNotATerminalNode());
    }

}
