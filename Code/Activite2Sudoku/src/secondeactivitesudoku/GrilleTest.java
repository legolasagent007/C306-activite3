/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package secondeactivitesudoku;

import junit.framework.TestCase;
//import static org.junit.Assert.fail;
import org.junit.Test;
/**
 *
 * @author Idjame
 */
public class GrilleTest extends TestCase {
    /**
     * attribut taille.
     */
    private final int taille = 16;
    /**
     * attribut instance.
     */
    private final GrilleImpl instance = new GrilleImpl(taille);
    /**
     * Test de getDimension.
     */
    @Test
    public final void testGetDimension() {
        System.out.println("Test de getDimension");
        final int expResult = 16;
        int result = instance.getDimension();
        assertEquals(expResult, result);
    }
    /**
     * Test de setValue.
     */
    @Test
    public final void testSetValue() {
        System.out.println("Test de setValue");
        int x = 0;
        int y = 0;
        char value = '1';
        instance.setValue(x, y, value);
    }

    /**
     * Test de setValue, cas de l'échec.
     * @throws IllegalArgumentException dans certains cas
     */
    @Test (expected = IllegalArgumentException.class)
    public final void testSetValueEchec() throws IllegalArgumentException {
        System.out.println("Test de setValue, cas de l'échec");
        final int x = 20;
        int y = 0;
        char value = '1';
        instance.setValue(x, y, value);
        fail("fsf");
    }
    /**
     * Test of getValue.
     * @throws IllegalArgumentException dans certains cas
     */
    @Test
    public final void testGetValue() {
        System.out.println("Test de getValue");
        int x = 0;
        int y = 0;
        char expResult = Grille.EMPTY;
        char result = instance.getValue(x, y);
        assertEquals(expResult, result);
    }
    /**
     * Test of getValue, cas de l'échec.
     * @throws IllegalArgumentException dans certains cas
     */
    @Test (expected = IllegalArgumentException.class)
    public final void testGetValueEchec() throws IllegalArgumentException {
        System.out.println("Test de getValue, cas de l'échec");
        final int x = 20;
        int y = 0;
        char expResult = '1';
        char result = instance.getValue(x, y);
    }
    /**
     * Test de complete.
     */
    @Test
    public final void testComplete() {
        System.out.println("Test de complete");
        boolean expResult = false;
        boolean result = instance.complete();
        assertEquals(expResult, result);
    }
     /**
     * Test of possible.
     */
    @Test
    public final void testPossible() {
        System.out.println("Test de possible");
        int x = 0;
        int y = 0;
        char value = '1';
        boolean expResult = true;
        boolean result = instance.possible(x, y, value);
        assertEquals(expResult, result);
    }
    /**
     * Test of possible, cas de l'échec.
     * @throws IllegalArgumentException dans certains cas
     */
    @Test (expected = IllegalArgumentException.class)
    public final void testPossibleEchec() throws IllegalArgumentException {
        System.out.println("Test de possible, cas de l'échec");
        final int x = 20;
        int y = 0;
        char value = '1';
        boolean expResult = true;
        boolean result = instance.possible(x, y, value);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        // TODO code application logic here
    }
}
