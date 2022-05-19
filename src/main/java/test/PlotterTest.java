package test;

import mastermicro.functionplotter.GraphController;
import org.junit.Test;


import static org.junit.Assert.assertThrows;

public class PlotterTest {
    GraphController g = new GraphController();
    @Test
    public void testMinGreater(){
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("x^2",10,-10,100);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("x^2",101,100,100);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("x^2",-15,-20,100);
        });
    }

    @Test
    public void wrongNPoints(){
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("x^2",-10,10,-5);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("x^2",-10,10,0);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("x^2",-10,10,5001);
        });
    }
    @Test
    public void wrongsNPoints(){
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("3/x",-10,10,100);
        });
    }
    @Test
    public void wrongEquation(){
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("x2",-10,10,100);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("2x",-10,10,100);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("3x2+5",-10,10,100);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("^2*x",-10,10,100);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("2_x + 1",-10,10,100);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("3y + 5",-10,10,100);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("3**2",-10,10,100);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("14^2^2",-10,10,100);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("3x2+5",-10,10,100);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("14&2x5",-10,10,100);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("3/x",-10,10,100);
        });
        assertThrows(RuntimeException.class, () -> {
            g.plotFunction("12^^2",-10,10,100);
        });
    }

}
