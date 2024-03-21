package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("La longitud de datos debe ser mayor o igual que 5 ")
    public void anyadirActividadTest_datosLengthMenorQue5_throwsException() throws ClubException{
        ClubDeportivoAltoRendimiento clubDeportivo = new ClubDeportivoAltoRendimiento("cd",2,7);
        String[] datos = {"codigo"};
        assertThrows(ClubException.class,()-> clubDeportivo.anyadirActividad(datos));
    }

    @Test
    @DisplayName("El formato de datos debe ser correcto")
    public void anyadirActividadTest_datosFormatoIncorrecto_throwsException() throws ClubException{
        ClubDeportivoAltoRendimiento clubDeportivo = new ClubDeportivoAltoRendimiento("cd",2,7);
        String[] datos = {"codigo", "actividad", "2.3", "2", "3"};
        assertThrows(ClubException.class,()-> clubDeportivo.anyadirActividad(datos));
    }

    @Test
    @DisplayName("El formato de datos debe ser correcto")
    public void anyadirActividadTest_datosFormatoCorrecto_returnTrue() throws ClubException{
        ClubDeportivoAltoRendimiento clubDeportivo = new ClubDeportivoAltoRendimiento("cd",2,7);
        String[] datos1 = {"codigo", "natacion", "2", "2", "3"};
        clubDeportivo.anyadirActividad(datos1);
        String[] datos2 = {"codigo", "atletismo", "2", "2", "3"};
        clubDeportivo.anyadirActividad(datos2);
        grupo.actualizarPlazas(3);
        clubdeportivo.anyadirActividad(grupo);
        assertEquals("cd --> [ " + grupo.toString() + ", " + grupo1.toString() + " ]", clubdeportivo.toString());
    }


}
