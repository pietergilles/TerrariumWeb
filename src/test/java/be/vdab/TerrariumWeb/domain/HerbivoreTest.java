package be.vdab.TerrariumWeb.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HerbivoreTest {


    @Before
    public void before(){
        Terrarium.INSTANCE.reset();
    }

    @Test
    public void checkIfHerbivorePairsWithHisRightwardHerbivore() {
        for (Organism organism : Terrarium.INSTANCE.getAllOrganisms()) {
            Terrarium.INSTANCE.remove(organism);
        }
        Herbivore herbivore = new Herbivore(new Location(0, 0), 1);
        Herbivore herbivoreTwo = new Herbivore(new Location(1, 0), 1);
        Terrarium.INSTANCE.addOrganism(herbivore);
        Terrarium.INSTANCE.addOrganism(herbivoreTwo);
        int organismsBefore = Terrarium.INSTANCE.getAllOrganisms().size();
//    gameController.activateOrganisms();
        herbivore.interactWithEnvironment();
        int organismsAfther = Terrarium.INSTANCE.getAllOrganisms().size();
        assertEquals(-1, Integer.compare(organismsBefore, organismsAfther));
//    assertTrue(-1 == (new Integer.compare(organismsBefore,organismsAfther)));
    }

    @Test
    public void checkIfHerbivoreMovesWhenHisRightwardspotIsEmpty() {
        for (Organism organism : Terrarium.INSTANCE.getAllOrganisms()) {
            Terrarium.INSTANCE.remove(organism);
        }
        Herbivore herbivore = new Herbivore(new Location(0, 0), 1);
        Herbivore herbivoreTwo = new Herbivore(new Location(2, 0), 1);
        Terrarium.INSTANCE.addOrganism(herbivore);
        Terrarium.INSTANCE.addOrganism(herbivoreTwo);
        Location locationHerbivoreBeforeActivation = herbivore.getLocation();
        herbivore.interactWithEnvironment();
        Location locationHerbivoreAftherActivation = herbivore.getLocation();
        assertNotEquals(locationHerbivoreBeforeActivation, locationHerbivoreAftherActivation);
    }

    @Test
    public void checkIfHerbivoreEatsRightwardPlantAndGetsItsLifeForce() {
        for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
            Terrarium.INSTANCE.remove(organism);
        }
        Herbivore herbivore = new Herbivore(new Location(0, 0), 1);
        Plant plant = new Plant(new Location(1, 0), 1);
        Terrarium.INSTANCE.addOrganism(herbivore);
        Terrarium.INSTANCE.addOrganism(plant);
        herbivore.interactWithEnvironment();
        assertTrue((herbivore.getLifeForce() == 2) && (Terrarium.INSTANCE.getAllOrganisms().size() == 1));
    }
}
