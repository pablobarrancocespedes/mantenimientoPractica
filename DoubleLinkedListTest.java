import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.deque.DoubleLinkedList;
import org.mps.deque.DoubleLinkedQueueException;
public class DoubleLinkedListTest {
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
    public void deleteLasttTest_colaVacia_throwException(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        assertThrows(DoubleLinkedQueueException.class, () -> colaDoble.deleteLast());
    }

    @Test
    @DisplayName("delete first borra el primero de la cola")
    public void deleteFirstTest_colaVacia_throwException(){
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
    public void deleteFirstTest_colaVacia_throwException(){
        DoubleLinkedList<String> colaDoble = new DoubleLinkedList<>();
        String str = "hola";
        String str2 = "adios";
        colaDoble.append(str);
        colaDoble.append(str2);
        colaDoble.deleteLast();
        assertEquals(colaDoble.last(), str);
    }
}
