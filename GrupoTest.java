import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import clubdeportivo.ClubDeportivo;
import clubdeportivo.ClubException;
import clubdeportivo.Grupo;

public class GrupoTest {
    
    public void lanzaExcepcion() throws ClubException{
        throw new ClubException();
    }

    @Test
    @DisplayName("Lanzar excepcion sin argumentos")
    public void ClubExceptionTest_noArgumentException_throwException(){
        assertThrows(ClubException.class,() -> lanzaExcepcion());
    }

    @Test
    @DisplayName("El numero de plazas de un grupo debe ser mayor que 0")
    public void grupoTest_nplazasMenorOIgualQue0_returnException(){
        assertThrows(ClubException.class, ()->new Grupo("codigo", "actividad", 0, 1, 2));
    }

    @Test
    @DisplayName("El numero de matriculado de un grupo debe ser mayor o igual que 0")
    public void grupoTest_matriculadorMenorQue0_returnException(){
        assertThrows(ClubException.class, ()->new Grupo("codigo", "actividad", 1, -1, 2));
    }

    @Test
    @DisplayName("La cantidad de tarifa de un grupo debe ser mayor que 0")
    public void grupoTest_cantidadMenorOIgualQue0_returnException(){
        assertThrows(ClubException.class, ()->new Grupo("codigo", "actividad", 1, 1, 0));
    }

    @Test
    @DisplayName("El numero de matriculas de un grupo debe ser menor que el de plazas")
    public void grupoTest_matriculasMayorQuenplazas_returnException(){
        assertThrows(ClubException.class, ()->new Grupo("codigo", "actividad", 1, 2, 3));
    }

    @Test
    @DisplayName("getCodigo devuelve el codigo del grupo")
    public void getCodigoTest_returnCodigo(){
        try{
            Grupo grupo = new Grupo("codigo", "actividad", 1, 1, 1);
            assertEquals("codigo", grupo.getCodigo());
        }catch( ClubException e){
            e.getMessage();
        }
    }

    @Test
    @DisplayName("getActividad devuelve la actividad del grupo")
    public void getActividadTest_returnActividad(){
        try{
            Grupo grupo = new Grupo("codigo", "actividad", 1, 1, 1);
            assertEquals("actividad", grupo.getActividad());
        }catch( ClubException e){
            e.getMessage();
        }
    }

    @Test
    @DisplayName("getPlazas devuelve las nplazas del grupo")
    public void getPlazasTest_returnnplazas(){
        try{
            Grupo grupo = new Grupo("codigo", "actividad", 1, 1, 1);
            assertEquals(1, grupo.getPlazas());
        }catch( ClubException e){
            e.getMessage();
        }
    }

    @Test
    @DisplayName("getTarifa devuelve la tarifa del grupo")
    public void getTarifaTest_returntarifa(){
        try{
            Grupo grupo = new Grupo("codigo", "actividad", 1, 1, 1);
            assertEquals(1, grupo.getTarifa());
        }catch( ClubException e){
            e.getMessage();
        }
    }

    @Test
    @DisplayName("plazasLibres devuelve nplazas-matriculados")
    public void plazasLibresTest_returnnplazasmenosmatriculados(){
        try{
            Grupo grupo = new Grupo("codigo", "actividad", 2, 1, 1);
            assertEquals(1, grupo.plazasLibres());
        }catch( ClubException e){
            e.getMessage();
        }
    }

    @Test
    @DisplayName("El numero de plazas nuevo debe ser mayor que 0")
    public void actualizarPlazasTest_nMenoroIgualQue0_returnException(){
        try{
            Grupo grupo = new Grupo("codigo", "actividad", 2, 1, 1);
            assertThrows(ClubException.class,()-> grupo.actualizarPlazas(0));
        }catch( ClubException e){
            e.getMessage();
        }
        
    }

    @Test
    @DisplayName("El numero de plazas nuevo debe ser mayor que el numero de matriculados")
    public void actualizarPlazasTest_nMenoroQuematriculados_returnException(){
        try{
            Grupo grupo = new Grupo("codigo", "actividad", 2, 2, 1);
            assertThrows(ClubException.class,()-> grupo.actualizarPlazas(1));
        }catch( ClubException e){
            e.getMessage();
        }
        
    }

    @Test
    @DisplayName("El numero de plazas nuevo debe ser mayor que el numero de matriculados")
    public void actualizarPlazasTest_returnTrue(){
        try{
            Grupo grupo = new Grupo("codigo", "actividad", 2, 2, 1);
            grupo.actualizarPlazas(3);
            assertEquals(3, grupo.getPlazas());
        }catch( ClubException e){
            e.getMessage();
        }
        
    }



    @Test
    @DisplayName("El nuevo numero de matriculas debe ser mayor que el de plazas libres")
    public void matricularTest_matriculasMenorOIgualQueplazasLibres_returnException(){
        try{
            Grupo grupo = new Grupo("codigo", "actividad", 2, 2, 1);
            assertThrows(ClubException.class,()-> grupo.matricular(1));
        }catch( ClubException e){
            e.getMessage();
        }
        
    }

    @Test
    @DisplayName("El nuevo numero de matriculas debe ser mayor que 0")
    public void matricularTest_matriculasMenorOIgualQue0_returnException(){
        try{
            Grupo grupo = new Grupo("codigo", "actividad", 2, 2, 1);
            assertThrows(ClubException.class,()-> grupo.matricular(0));
        }catch( ClubException e){
            e.getMessage();
        }
        
    }

    @Test
    @DisplayName("El  numero de matriculas aumenta en n")
    public void matricularTest_matriculasMayorQueplazasLibres_returnTrue(){
        try{
            Grupo grupo = new Grupo("codigo", "actividad", 2, 1, 1);
            grupo.matricular(1);
            assertEquals(2, grupo.getMatriculados());
        }catch( ClubException e){
            e.getMessage();
        }
        
    }

    @Test
    @DisplayName("El equals devuelve cuando son iguales")
    public void equalsTest_gruposDistintos_returnFalse(){
        try{
            Grupo g1 = new Grupo("codigo", "actividad", 2, 1, 1);
            Grupo g2 = new Grupo("codigo1", "actividad1", 2, 1, 1);
            assertFalse(g1.equals(g2));
        }catch(ClubException ex){
            ex.getMessage();
        }
    }

    @Test
    @DisplayName("El equals devuelve cuando son iguales")
    public void equalsTest_gruposIguales_returnTrue(){
        try{
            Grupo g1 = new Grupo("codigo", "actividad", 2, 1, 1);
            Grupo g2 = g1;
            assertTrue(g1.equals(g2));
        }catch(ClubException ex){
            ex.getMessage();
        }
    }

     @Test
    @DisplayName("El equals devuelve false cuando no es instancia de Grupo")
    public void equalsTest_noEsGrupo_returnFalse(){
        try{
            Grupo g1 = new Grupo("codigo", "actividad", 2, 1, 1);
            ClubDeportivo cd = new ClubDeportivo("hola");
            assertFalse(g1.equals(cd));
        }catch(ClubException ex){
            ex.getMessage();
        }
    }

    @Test
    @DisplayName("El equals devuelve cuando son iguales")
    public void equalsTest_gruposIguales2_returnTrue(){
        try{
            Grupo g1 = new Grupo("codigo", "actividad", 2, 1, 1);
            Grupo otro = g1;
            assertEquals(g1.equals(otro),g1.getCodigo().equalsIgnoreCase(otro.getCodigo()) && g1.getActividad().equalsIgnoreCase(otro.getActividad()));;
        }catch(ClubException ex){
            ex.getMessage();
        }
    }

    @Test
    @DisplayName("El hash code devuelve la suma del hash code del codigo y del hash code de la actividad")
    public void hashCodeTest_returnTrue(){
        try{
            Grupo g1 = new Grupo("codigo", "actividad", 2, 1, 1);
            int cod = g1.getCodigo().toUpperCase().hashCode();
            int act = g1.getActividad().toUpperCase().hashCode();
            assertEquals(g1.hashCode(),cod+act);
        }catch(ClubException ex){
            ex.getMessage();
        }
    }
    
    @Test
    @DisplayName("El toString devuelve la cadena con el grupo")
    public void toStringTest_returnTrue(){
        try{
            Grupo g1 = new Grupo("codigo", "actividad", 2, 1, 1);
            String cadena = "("+ g1.getCodigo() + " - "+g1.getActividad()+" - " + g1.getTarifa() + " euros "+ "- P:" + g1.getPlazas() +" - M:" +g1.getMatriculados()+")";

            assertEquals(g1.toString(),cadena);
        }catch(ClubException ex){
            ex.getMessage();
        }
    }

}
