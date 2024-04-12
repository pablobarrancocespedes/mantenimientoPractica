package org.mps.ronqi2;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mps.dispositivo.Dispositivo;
import org.mps.dispositivo.DispositivoSilver;



public class RonQI2SilverTest {

    
    /*
     * Analiza con los caminos base qué pruebas se han de realizar para comprobar que al inicializar funciona como debe ser. 
     * El funcionamiento correcto es que si es posible conectar ambos sensores y configurarlos, 
     * el método inicializar de ronQI2 o sus subclases, 
     * debería devolver true. En cualquier otro caso false. Se deja programado un ejemplo.
     */
    
    /*
     * Un inicializar debe configurar ambos sensores, comprueba que cuando se inicializa de forma correcta (el conectar es true), 
     * se llama una sola vez al configurar de cada sensor.
     */

    @Test
    @DisplayName("Comprobamos las 16 combinaciones para el inicializar")
    public void inicializarTest_SensoresConectados(){  
        for(int i = 0; i <= 1; i++){
            for(int j = 0; j <= 1; j++){
                for(int k = 0; k <= 1; k++){
                    for(int l = 0; l <= 1; l++){
                        Dispositivo mockDisp =  mock(Dispositivo.class);
                        when(mockDisp.conectarSensorPresion()).thenReturn(i != 0);
                        when(mockDisp.configurarSensorPresion()).thenReturn(j != 0);
                        when(mockDisp.conectarSensorSonido()).thenReturn(k != 0);
                        when(mockDisp.configurarSensorSonido()).thenReturn(l != 0);
                        RonQI2Silver ronqi2silver = new RonQI2Silver(); 
                        ronqi2silver.anyadirDispositivo(mockDisp);
                        
                        boolean resultadoEsperado = (i == 1 && j == 1 && k == 1 && l == 1)? true : false;
                        boolean resultado = ronqi2silver.inicializar();
                        assertEquals(resultadoEsperado,resultado);

                        if(resultado){
                            verify(mockDisp,times(1)).configurarSensorPresion();
                            verify(mockDisp,times(1)).configurarSensorSonido();
                        }
                        
                    }
                    
                }
            }
        }
    }
     

    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
    Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */


     @Test
     @DisplayName("Reconectar no conecta ambos dispostivos si estan conectados")
     public void reconectarTest_estanConectados_returnFalse(){
         Dispositivo mockDisp =  mock(Dispositivo.class);
         when(mockDisp.estaConectado()).thenReturn(true);
         RonQI2Silver ronqi2silver = new RonQI2Silver();
         ronqi2silver.anyadirDispositivo(mockDisp);
         boolean resultado = ronqi2silver.reconectar();
         assertFalse(resultado);
 
     }
 
     @Test
     @DisplayName("Reconectar conecta ambos dispositivos si no estan conectados")
     public void reconectarTest_noEstanConectados_returnTrue(){
         Dispositivo mockDisp =  mock(Dispositivo.class);
         when(mockDisp.conectarSensorPresion()).thenReturn(true);        
         when(mockDisp.conectarSensorSonido()).thenReturn(true);
         when(mockDisp.estaConectado()).thenReturn(false);
         RonQI2Silver ronqi2silver = new RonQI2Silver();
         ronqi2silver.anyadirDispositivo(mockDisp);
         boolean resultado = ronqi2silver.reconectar();
         assertTrue(resultado);
     }
 
     @Test
     @DisplayName("Reconectar no conecta ambos dispositivos si no estan conectados y si sus sensores no estan conectados")
     public void reconectarTest_noEstanConectadosYSensorPresionNoConectado_returnTrue(){
         Dispositivo mockDisp =  mock(Dispositivo.class);
         when(mockDisp.conectarSensorPresion()).thenReturn(false);        
         when(mockDisp.conectarSensorSonido()).thenReturn(true);
         when(mockDisp.estaConectado()).thenReturn(false);
         RonQI2Silver ronqi2silver = new RonQI2Silver();
         ronqi2silver.anyadirDispositivo(mockDisp);
         boolean resultado = ronqi2silver.reconectar();
         assertFalse(resultado);
     }
 
     @Test
     @DisplayName("Reconectar no conecta ambos dispositivos si no estan conectados y si sus sensores no estan conectados")
     public void reconectarTest_noEstanConectadosYSensorSonidoNoConectado_returnTrue(){
         Dispositivo mockDisp =  mock(Dispositivo.class);
         when(mockDisp.conectarSensorPresion()).thenReturn(true);        
         when(mockDisp.conectarSensorSonido()).thenReturn(false);
         when(mockDisp.estaConectado()).thenReturn(false);
         RonQI2Silver ronqi2silver = new RonQI2Silver();
         ronqi2silver.anyadirDispositivo(mockDisp);
         boolean resultado = ronqi2silver.reconectar();
         assertFalse(resultado);
     }
 
     @Test
     @DisplayName("estaConectado devuelve true si disp esta conectado")
     public void estaConectadoTest_returnTrue(){
         Dispositivo mockDisp =  mock(Dispositivo.class);
         when(mockDisp.estaConectado()).thenReturn(true);
         RonQI2Silver ronqi2silver = new RonQI2Silver();
         ronqi2silver.anyadirDispositivo(mockDisp);
         boolean resultado = ronqi2silver.estaConectado();
         assertTrue(resultado);
     }
 
     @Test
     @DisplayName("estaConectado devuelve false si disp no esta conectado")
     public void estaConectadoTest_returnFalse(){
         Dispositivo mockDisp =  mock(Dispositivo.class);
         when(mockDisp.estaConectado()).thenReturn(false);
         RonQI2Silver ronqi2silver = new RonQI2Silver();
         ronqi2silver.anyadirDispositivo(mockDisp);
         boolean resultado = ronqi2silver.estaConectado();
         assertFalse(resultado);
     }
    
    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
     */
     
    @Test
    @DisplayName("evaluarApneaSuenyo devuelve true si las medias son mayores que los threshold")
    public void evaluarApneaSuenyoTest_avgMayorQueThreshold_returnTrue(){
        Dispositivo mockDisp =  mock(Dispositivo.class);
        RonQI2Silver ronqi2silver = new RonQI2Silver();
        when(mockDisp.leerSensorPresion()).thenReturn(50f);
        when(mockDisp.leerSensorSonido()).thenReturn(50f);
        ronqi2silver.anyadirDispositivo(mockDisp);
        ronqi2silver.obtenerNuevaLectura();
        assertTrue(ronqi2silver.evaluarApneaSuenyo());
    }

    @Test
    @DisplayName("evaluarApneaSuenyo devuelve false si la media del SensorS es menor que su threshold")
    public void evaluarApneaSuenyoTest_avgSMenorQueThresholdS_returnFalse(){
        Dispositivo mockDisp =  mock(Dispositivo.class);
        RonQI2Silver ronqi2silver = new RonQI2Silver();
        when(mockDisp.leerSensorPresion()).thenReturn(30f);
        when(mockDisp.leerSensorSonido()).thenReturn(20f);
        ronqi2silver.anyadirDispositivo(mockDisp);
        ronqi2silver.obtenerNuevaLectura();
        assertFalse(ronqi2silver.evaluarApneaSuenyo());
    }

    @Test
    @DisplayName("evaluarApneaSuenyo devuelve false si la media del SensorP es menor que su threshold")
    public void evaluarApneaSuenyoTest_avgPMenorQueThresholdP_returnFalse(){
        Dispositivo mockDisp =  mock(Dispositivo.class);
        RonQI2Silver ronqi2silver = new RonQI2Silver();
        when(mockDisp.leerSensorPresion()).thenReturn(10f);
        when(mockDisp.leerSensorSonido()).thenReturn(50f);
        ronqi2silver.anyadirDispositivo(mockDisp);
        ronqi2silver.obtenerNuevaLectura();
        assertFalse(ronqi2silver.evaluarApneaSuenyo());
    }

    @Test
    @DisplayName("evaluarApneaSuenyo devuelve false si las medias son menores que los threshold")
    public void evaluarApneaSuenyoTest_avgMenorQueThreshold_returnFalse(){
        Dispositivo mockDisp =  mock(Dispositivo.class);
        RonQI2Silver ronqi2silver = new RonQI2Silver();
        when(mockDisp.leerSensorPresion()).thenReturn( 6f);
        when(mockDisp.leerSensorSonido()).thenReturn( 1f);
        ronqi2silver.anyadirDispositivo(mockDisp);
        ronqi2silver.obtenerNuevaLectura();
        assertFalse(ronqi2silver.evaluarApneaSuenyo());
    }
    

     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */

    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    public class pruebaevaluarApneaSuenyo{
        @ParameterizedTest
        @DisplayName("obtenerNuevaLectura comprueba que no hay mas de 5 lecturas y evaluarApneaSuenyo devuelve false al ser la media Sonido menor que su threshold")
        @MethodSource("presionYSonidoProvider1")
        public void evaluarApneaSuenyoTest_5LecturasMediaSMenorQueThresholdS_returnFalse(List<Float> presiones, List<Float> sonidos){
            DispositivoSilver mockDisp =  mock(DispositivoSilver.class);
            RonQI2Silver ronqi2silver = new RonQI2Silver();
            ronqi2silver.disp = mockDisp;
            for (int i = 0; i < presiones.size(); i++) {
                when(mockDisp.leerSensorPresion()).thenReturn(presiones.get(i));
                when(mockDisp.leerSensorSonido()).thenReturn(sonidos.get(i));
                ronqi2silver.obtenerNuevaLectura();
            }

            boolean result = ronqi2silver.evaluarApneaSuenyo();

            assertFalse(result);
        }

        Stream<Arguments> presionYSonidoProvider1() {
            List<Float> lista1 = Arrays.asList( 22.0f, 24.0f, 26.0f, 23.0f, 21.0f);
            List<Float> lista2 = Arrays.asList( 31.0f, 32.0f, 33.0f, 31.0f, 0.0f);
            return Stream.of(
                Arguments.of(lista1, lista2)
            );
        }

        @ParameterizedTest
        @DisplayName("obtenerNuevaLectura comprueba que hay 10 lecturas y elimina las 4 primeras y evaluarApneaSuenyo devuelve false al ser la media Presion menor que su threshold")
        @MethodSource("presionYSonidoProvider2")
        public void evaluarApneaSuenyo_MasDe5LecturasMediaPMenorQueThresholdP_returnFalse(List<Float> presiones, List<Float> sonidos){
            DispositivoSilver mockDisp =  mock(DispositivoSilver.class);
            RonQI2Silver ronqi2silver = new RonQI2Silver();
            ronqi2silver.disp = mockDisp;
            for (int i = 0; i < presiones.size(); i++) {
                when(mockDisp.leerSensorPresion()).thenReturn(presiones.get(i));
                when(mockDisp.leerSensorSonido()).thenReturn(sonidos.get(i));
                ronqi2silver.obtenerNuevaLectura();
            }

            boolean result = ronqi2silver.evaluarApneaSuenyo();

            assertFalse(result);
        }

        Stream<Arguments> presionYSonidoProvider2() {
            List<Float> lista1 = Arrays.asList(0f, 0f, 0f, 0f, 0f, 22.0f, 24.0f, 26.0f, 23.0f, 0.0f);
            List<Float> lista2 = Arrays.asList(0f, 0f, 0f, 0f, 0f, 31.0f, 32.0f, 33.0f, 31.0f, 30.0f);
            return Stream.of(
                Arguments.of(lista1, lista2)
            );
        }

    }
}




