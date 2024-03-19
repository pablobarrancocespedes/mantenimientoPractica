import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import clubdeportivo.ClubDeportivo;
import clubdeportivo.ClubDeportivoAltoRendimiento;
import clubdeportivo.ClubException;
import clubdeportivo.Grupo;

public class ClubDeportivoAltoRendimientoTest {
    @Test
    @DisplayName("El constructor lanza una excepcion si incremento es menor que 0")
    public void ClubDeportivoAltoRendimientoTest_incrementoMenorQueCero_throwException(){
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Vals", 0, -1));
    }

    @Test
    @DisplayName("El constructor lanza una excepcion si maximo es menor que 0")
    public void ClubDeportivoAltoRendimientoTest_maximoMenorQueCero_throwException(){
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Vals", -1, 0));
    }

    @Test
    @DisplayName("El constructor lanza una excepcion si incremento es menor que 0")
    public void ClubDeportivoAltoRendimientoTest_incrementoMenorQueCero2_throwException(){
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Vals", 3, 0, -1));
    }

    @Test
    @DisplayName("El constructor lanza una excepcion si maximo es menor que 0")
    public void ClubDeportivoAltoRendimientoTest_maximoMenorQueCero2_throwException(){
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Vals",3, -1, 0));
    }

    @Test
    @DisplayName("El constructor lanza una excepcion si ambos son menor que 0")
    public void ClubDeportivoAltoRendimientoTest_ambosMenorQueCero2_throwException(){
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Vals",3, -1, -1));
    }

    @Test
    @DisplayName("Ingresos devuelve los ingresos del club deportivo de alto rendimiento")
    public void ingresosTest_returnTrue(){
        try{
            ClubDeportivoAltoRendimiento clubDeportivo = new ClubDeportivoAltoRendimiento("cd",2,7);
            Grupo grupo1 = new Grupo("codigo", "natacion", 3, 2, 1);
           // Grupo grupo2 = new Grupo("codigo", "natacion", 3, 2, 1);
            clubDeportivo.anyadirActividad(grupo1);
            //clubDeportivo.anyadirActividad(grupo2);
            double ingreso = 2*1+2*0.07;
            assertEquals(clubDeportivo.ingresos(),ingreso);
        } catch (ClubException ex){
            ex.getMessage();
        }
        
    }

}
