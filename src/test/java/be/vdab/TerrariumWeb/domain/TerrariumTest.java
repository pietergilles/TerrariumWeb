package be.vdab.TerrariumWeb.domain;

import org.junit.Test;

public class TerrariumTest
{
    @Test
    public void testVanPrintTerrarium() {
        Terrarium terrarium = Terrarium.INSTANCE;
//        terrarium.reset();
        GameController.INSTANCE.printTerrarium();
    }
}