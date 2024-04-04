/*
 * Autores:
 * Pablo Barranco Cespedes
 * Raquel Ferreira Macayo
 */
package deque;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class DoubleLinkedListTest {

    @Test
    @DisplayName("El constructor crea una cola doble correctamente")
    public void doubleLinkedListTest_returnTrue(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        assertThrows( DoubleLinkedQueueException.class, ()-> colaDoble.first());
        assertThrows( DoubleLinkedQueueException.class, ()-> colaDoble.last());
        assertEquals(colaDoble.size(), 0);
    }

    @Test
    @DisplayName("prepend devuelve una cola con un solo elemento si la cola esta vacia")
    public void prependTest_colaVacia_returnTrue(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        colaDoble.prepend(str);
        assertEquals(str,colaDoble.first());
        assertEquals(str,colaDoble.last());
        assertEquals(1,colaDoble.size());

    }

    @Test
    @DisplayName("prepend inserta un elemento al principio de la lista")
    public void prependTest_colaNoVacia_returnTrue(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        String str2 = "adios";
        colaDoble.prepend(str);
        colaDoble.prepend(str2);
        assertEquals(str2,colaDoble.first());
        assertEquals(2, colaDoble.size());
    }

    @Test
    @DisplayName("append devuelve una cola con un solo elemento si la cola esta vacia")
    public void appendTest_colaVacia_returnTrue(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        colaDoble.append(str);
        assertEquals(str,colaDoble.first());
        assertEquals(str,colaDoble.last());
        assertEquals(1,colaDoble.size());
    }

    @Test
    @DisplayName("prepend inserta un elemento al principio de la lista")
    public void appendTest_colaVaNocia_returnTrue(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        String str2 = "adios";
        colaDoble.append(str);
        colaDoble.append(str2);
        assertEquals(str,colaDoble.first());
        assertEquals(2, colaDoble.size());
    }

    @Test
    @DisplayName("delete first lanza excepcion si se hace en una cola vacia")
    public void deleteFirstTest_colaVacia_throwException(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        assertThrows(DoubleLinkedQueueException.class, () -> colaDoble.deleteFirst());
    }

    @Test
    @DisplayName("delete last lanza una excepcion si se hace en una cola vacia")
    public void deleteLastTest_colaVacia_throwException(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        assertThrows(DoubleLinkedQueueException.class, () -> colaDoble.deleteLast());
    }

    @Test
    @DisplayName("delete first borra el primero de la cola")
    public void deleteFirstTest_colaNoVacia_throwException(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        String str2 = "adios";
        colaDoble.append(str);
        colaDoble.append(str2);
        colaDoble.deleteFirst();
        assertEquals(colaDoble.first(), str2);
    }

    @Test
    @DisplayName("delete last borra el ultimo de la cola")
    public void deleteLastTest_colaNoVacia_throwException(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        String str2 = "adios";
        colaDoble.append(str);
        colaDoble.append(str2);
        colaDoble.deleteLast();
        assertEquals(colaDoble.last(), str);
    }
    @Test
    @DisplayName("delete first borra el primero de la cola")
    public void deleteFirstTest_colaNoVaciaUnElemento_throwException(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        String str2 = "adios";
        colaDoble.append(str);
        colaDoble.deleteFirst();
        assertEquals(0, colaDoble.size());
        assertThrows(DoubleLinkedQueueException.class,() -> colaDoble.first());
        assertThrows(DoubleLinkedQueueException.class,() -> colaDoble.last());
    }

    @Test
    @DisplayName("delete last borra el ultimo de la cola")
    public void deleteLastTest_colaNoVaciaUnElemento_throwException(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        String str2 = "adios";
        colaDoble.append(str);
        colaDoble.deleteLast();
        assertEquals(0, colaDoble.size());
        assertThrows(DoubleLinkedQueueException.class,() -> colaDoble.first());
        assertThrows(DoubleLinkedQueueException.class,() -> colaDoble.last());
    }

    @Test
    @DisplayName("get lanza una excepcion si index < 0")
    public void getTest_indexMenorQueCero_throwsException(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        assertThrows(DoubleLinkedQueueException.class,() -> colaDoble.get(-1));
    }

    @Test
    @DisplayName("get lanza una excepcion si index >= size")
    public void getTest_indexMayorIgualQueSize_throwsException(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        colaDoble.append(str);
        assertThrows(DoubleLinkedQueueException.class,() -> colaDoble.get(2));
    }

    @Test
    @DisplayName("get lanza una excepcion si index >= size")
    public void getTest_indexMayorQueCeroyMenorQueSize_returnTrue(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        String str2 = "adios";
        colaDoble.append(str);
        colaDoble.append(str2);
        assertEquals(str2,colaDoble.get(1));
    }

    @Test
    @DisplayName("contains lanza una excepcion si value es nulo")
    public void containsTest_valueNull_throwsException(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        assertThrows(DoubleLinkedQueueException.class,() -> colaDoble.contains(null));
    }

    @Test
    @DisplayName("contains devuelve false si value no esta en la cola")
    public void containsTest_noContieneAValue_returnFalse(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        assertFalse(colaDoble.contains(str));
    }

    @Test
    @DisplayName("contains devuelve true si value esta en la cola")
    public void containsTest_contieneAValue_returnTrue(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        String str2 = "adios";
        colaDoble.append(str);
        colaDoble.append(str2);
        assertTrue(colaDoble.contains(str2));
    }

    @Test
    @DisplayName("Remove elimina el primer item de la lista")
    public void removeTest_EliminarPrimero_returnTrue(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        String str2 = "adios";
        colaDoble.append(str);
        colaDoble.append(str2);
        colaDoble.remove(str);
        assertEquals(colaDoble.first(), str2);
    }

    @Test
    @DisplayName("Remove elimina el ultimo item de la lista")
    public void removeTest_EliminarUltimo_returnTrue(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        String str2 = "adios";
        colaDoble.append(str);
        colaDoble.append(str2);
        colaDoble.remove(str2);
        assertEquals(colaDoble.last(), str);
    }

    @Test
    @DisplayName("Remove elimina un item que no es ni primero ni ultimo de la lista")
    public void removeTest_EliminarMedio_returnTrue(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        String str2 = "adios";
        String str3 = "zzz";
        colaDoble.append(str);
        colaDoble.append(str2);
        colaDoble.append(str3);
        colaDoble.remove(str2);
        assertEquals(colaDoble.first(), str);
        assertEquals(colaDoble.last(), str3);
        assertEquals(colaDoble.size(), 2);
    }


    @Test
    @DisplayName("Remove no hace nada lista vacia")
    public void removeTest_ListaVacia_returnTrue(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        colaDoble.remove("hola");
        assertEquals(colaDoble.size(), 0);
    }


    
}
