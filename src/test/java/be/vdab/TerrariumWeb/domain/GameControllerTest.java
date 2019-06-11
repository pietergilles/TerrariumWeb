package be.vdab.TerrariumWeb.domain;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameControllerTest {

    @Before
    public void before(){
        for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
            Terrarium.INSTANCE.remove(organism);
        }
    }

    GameController controller = GameController.INSTANCE;

    @Test
    public void spawnPlants() {
        int sizeBefore = Terrarium.INSTANCE.getAllOrganisms().size();
        Terrarium.INSTANCE.setNumbersOfOrganisms(0,0,1,0,0);
        controller.spawnPlants();
        int sizeAfter = Terrarium.INSTANCE.getAllOrganisms().size();
        assertEquals(sizeBefore +1, sizeAfter);
    }

    @Test
    public void organismsAreActivated() {
        Terrarium.INSTANCE.addOrganism(new Carnivore(new Location(0,0) ,1));
        Terrarium.INSTANCE.addOrganism(new Herbivore(new Location(1,0) ,1));
        controller.activateOrganisms();
        assertEquals(1, Terrarium.INSTANCE.getAllOrganisms().size());
    }

    @Test
    public void printTerrarium() {
        controller.printTerrarium();
    }

}