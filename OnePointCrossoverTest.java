package org;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.EvolutionaryAlgorithmException;
import static org.junit.jupiter.api.Assertions.*;
import org.mps.crossover.*;

public class OnePointCrossoverTest {
    @Test
    @DisplayName("Si parent1 == null crossover lanza una excepcion")
    public void crossoverTest_parent1Null_throwException() throws EvolutionaryAlgorithmException{
        OnePointCrossover opc = new OnePointCrossover();
        int[] parent2 = {1,2,3};
        assertThrows(EvolutionaryAlgorithmException.class, () -> opc.crossover(null,parent2));
    }

    @Test
    @DisplayName("Si parent2 == null crossover lanza una excepcion")
    public void crossoverTest_parent2Null_throwException() throws EvolutionaryAlgorithmException{
        OnePointCrossover opc = new OnePointCrossover();
        int[] parent1 = {1,2,3};
        assertThrows(EvolutionaryAlgorithmException.class, () -> opc.crossover(parent1,null));
    }

    @Test
    @DisplayName("Si parent1.length == 0 crossover lanza excepcion")
    public void crossoverTest_parent1LengthIgualA0_throwException() throws EvolutionaryAlgorithmException{
        OnePointCrossover opc = new OnePointCrossover();
        int[] parent1 = {};
        int[] parent2 = {1,2,3};
        assertThrows(EvolutionaryAlgorithmException.class, () -> opc.crossover(parent1,parent2));
    }

    @Test
    @DisplayName("Si parent1.length > parent2.length crossover lanza excepcion")
    public void crossoverTest_parent1LengthMayorAParent2Length_throwException() throws EvolutionaryAlgorithmException{
        OnePointCrossover opc = new OnePointCrossover();
        int[] parent1 = {2,1,3,4};
        int[] parent2 = {1,2,3};
        assertThrows(EvolutionaryAlgorithmException.class, () -> opc.crossover(parent1,parent2));
    }

    @Test
    @DisplayName("Si parent1.length < parent2.length crossover lanza excepcion")
    public void crossoverTest_parent1LengthMenorAParent2Length_throwException() throws EvolutionaryAlgorithmException{
        OnePointCrossover opc = new OnePointCrossover();
        int[] parent1 = {2,1};
        int[] parent2 = {1,2,3};
        assertThrows(EvolutionaryAlgorithmException.class, () -> opc.crossover(parent1,parent2));
    }

    @Test
    @DisplayName("Si parent1.length == parent2.length offspring no sera null")
    public void crossoverTest_parent1LengthIgualParent2Length_returnNotNull() throws EvolutionaryAlgorithmException{
        OnePointCrossover opc = new OnePointCrossover();
        int[] parent1 = {2,4};
        int[] parent2 = {1,6};
        int[][] offspring = opc.crossover(parent1, parent2);
        assertNotNull(offspring);
    }

    @Test
    @DisplayName("Si parent1.length == parent2.length crossover offspring[0].length == parent1.length && offspring[1].length == parent2.length")
    public void crossoverTest_parent1LengthIgualParent2Length_parentLengthEqualsOffspringLength() throws EvolutionaryAlgorithmException{
        OnePointCrossover opc = new OnePointCrossover();
        int[] parent1 = {2,4};
        int[] parent2 = {1,6};
        int[][] offspring = opc.crossover(parent1, parent2);
        //Las filas de offspring corresponderan a parent1 (fila 0) y parent2 (fila 1)
        assertEquals(parent2.length,offspring[1].length);
        assertEquals(parent1.length,offspring[0].length);
    }

    @Test
    @DisplayName("Si ambos parents son válidos entonces sed cambian las filas, basicamente lo que hace crossover es cambiar las filas de la matriz segun un random")
    public void crossoverTest_parent1LengthIgualParent2Length_offSpringMutado() throws EvolutionaryAlgorithmException{
        OnePointCrossover opc = new OnePointCrossover();
        int[] parent1 = {2,3,1};
        int[] parent2 = {1,2,3};
        int[][] offspring = opc.crossover(parent1, parent2);

        assertNotEquals(parent1,offspring[0]);
        assertNotEquals(parent2, offspring[1]);
    }


}
