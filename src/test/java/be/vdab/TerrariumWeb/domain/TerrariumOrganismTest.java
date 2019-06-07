package be.vdab.TerrariumWeb.domain;

import org.junit.Test;

public class TerrariumOrganismTest {
    @Test
    public void testVanPrintTerrarium() {
        Terrarium terrarium = Terrarium.INSTANCE;
        terrarium.setTerrarium(20,2,2,2,2);
        GameController.INSTANCE.printTerrarium();
    }
}
