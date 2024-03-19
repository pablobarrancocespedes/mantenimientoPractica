package clubdeportivo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ClubDeportivoTest{

    @Test
    public void ClubDeportivoTest_grupoCeroONegativo_returnException(){
        assertThrows(ClubException.class, () -> new ClubDeportivo( "cd", 0));
    }
    
    @Test
    @DisplayName("plazas libres devuelve el numero de plazas libres para una actividad")
    public void plazasLibresTest_actividadExiste_returnTrue(){
        try {
            ClubDeportivo clubDeportivo = new ClubDeportivo("cd");
            Grupo grupo = new Grupo("codigo", "natacion", 3, 2, 1);
            clubDeportivo.anyadirActividad(grupo);
            assertEquals(1, clubDeportivo.plazasLibres("natacion"));
        } catch (ClubException e) {
            e.getMessage();
        }
    }

    @Test
    @DisplayName("plazas libres devuelve el numero de plazas libres para una actividad")
    public void plazasLibresTest_actividadNoExiste_returnTrue(){
        try {
            ClubDeportivo clubDeportivo = new ClubDeportivo("cd");
            Grupo grupo = new Grupo("codigo", "natacion", 3, 2, 1);
            clubDeportivo.anyadirActividad(grupo);
            assertEquals(0, clubDeportivo.plazasLibres("atletismos"));
        } catch (ClubException e) {
            e.getMessage();
        }
    }

    @Test
    @DisplayName("ingresos devuelve la cantidad de dinero que ingresa el club deportivo")
    public void ingresosTest_returnTrue(){
        try {
            ClubDeportivo clubDeportivo = new ClubDeportivo("cd");
            String[] datos1 = {"codigo", "actividad","3", "2", "1"};
            String[] datos2 = {"codigo", "actividad","3", "1", "1"};
            clubDeportivo.anyadirActividad(datos1);
            clubDeportivo.anyadirActividad(datos2);
            assertEquals(2, clubDeportivo.ingresos());
        } catch (ClubException e) {
            e.getMessage();
        }
    }

    @Test
    @DisplayName("Anyadir actividad lanza una excepcion al ser el formato de los datos incorrecto")
    public void anyadirActividadTest_DatosIncorrectos_returnTrue() throws ClubException{
        ClubDeportivo clubdeportivo = new ClubDeportivo("cd");
        String[] datos = {"codigo", "actividad","2.3", "1", "2"};
        assertThrows(ClubException.class, ()-> clubdeportivo.anyadirActividad(datos));
        
    }

    @Test
    @DisplayName("Anyadir actividad lanza una excepcion al ser nulo el grupo")
    public void anyadirActividadTest_GrupoNulo_returnTrue() throws ClubException{
        ClubDeportivo clubdeportivo = new ClubDeportivo("cd");
        Grupo grupo = null;
        assertThrows(ClubException.class, ()-> clubdeportivo.anyadirActividad(grupo));
        
    }

    @Test
    @DisplayName("ToString devuelve el club deportivo pasado a string")
    public void toStringTest_returnTrue() throws ClubException{
        ClubDeportivo clubdeportivo = new ClubDeportivo("cd");
        Grupo grupo = new Grupo("codigo", "natacion", 2, 1, 2);
        clubdeportivo.anyadirActividad(grupo);
        Grupo grupo1 = new Grupo("codigo", "atletismo", 2, 1, 2);
        clubdeportivo.anyadirActividad(grupo1);
        grupo.actualizarPlazas(3);
        clubdeportivo.anyadirActividad(grupo);
    }


    @Test
    @DisplayName("")
    public void matricularTest_plazasMayorQueNPersonas_returnThrowException() throws ClubException{
        ClubDeportivo clubdeportivo = new ClubDeportivo("cd");
        Grupo grupo = new Grupo("codigo", "natacion", 2, 1, 2);
        clubdeportivo.anyadirActividad(grupo);
        assertThrows(ClubException.class, ()-> clubdeportivo.matricular("natacion", 100));
    }

    @Test
    @DisplayName("")
    public void matricularTest_plazasMenorQueNPersonas_returnTrue() throws ClubException{
        ClubDeportivo clubdeportivo = new ClubDeportivo("cd");
        Grupo grupo1 = new Grupo("codigo", "natacion", 2, 1, 2);
        Grupo grupo2 = new Grupo("codigo", "atletismo", 2, 1, 2);
        Grupo grupo3 = new Grupo("codigo1", "atletismo", 2, 0, 2);
        clubdeportivo.anyadirActividad(grupo1);
        clubdeportivo.anyadirActividad(grupo2);
        clubdeportivo.anyadirActividad(grupo3);
        clubdeportivo.matricular("atletismo", 2);
        assertEquals(clubdeportivo.plazasLibres("atletismo"),1);
    }

}


