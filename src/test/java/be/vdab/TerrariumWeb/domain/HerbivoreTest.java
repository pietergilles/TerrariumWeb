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

    @Test
    public void checkIfHerbivoreEatsLeftwardPlantAndOnlyEatsOnePlant() {
        for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
            Terrarium.INSTANCE.remove(organism);
        }
        Herbivore herbivore = new Herbivore(new Location(1, 1), 16);
        Plant plantRight = new Plant(new Location(4, 1), 1);
        Plant plantSouth = new Plant(new Location(1, 2), 1);
        Plant plantLeft = new Plant(new Location(5, 1), 1);
        Plant plantNorth = new Plant(new Location(1, 0), 1);
        Terrarium.INSTANCE.addOrganism(herbivore);
        Terrarium.INSTANCE.addOrganism(plantRight);
        Terrarium.INSTANCE.addOrganism(plantSouth);
        Terrarium.INSTANCE.addOrganism(plantLeft);
        Terrarium.INSTANCE.addOrganism(plantNorth);
        GameController.INSTANCE.printTerrarium();
        herbivore.interactWithEnvironment();
        GameController.INSTANCE.printTerrarium();
        assertTrue((herbivore.getLifeForce() == 17) && (Terrarium.INSTANCE.getAllOrganisms().size() == 4));
    }

    @Test
    public void checkIfHerbivore () {
        for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
            Terrarium.INSTANCE.remove(organism);
        }
        Herbivore herbivore = new Herbivore(new Location(1, 1), 31);
        herbivore.setSex('M');
        Herbivore herbivoreOne = new Herbivore(new Location(2, 1), 21);
        herbivoreOne.setSex('V');
        Terrarium.INSTANCE.addOrganism(herbivore);
        Terrarium.INSTANCE.addOrganism(herbivoreOne);
        GameController.INSTANCE.printTerrarium();
        herbivore.interactWithEnvironment();
        GameController.INSTANCE.printTerrarium();
        assertEquals(Terrarium.INSTANCE.getAllOrganisms().size(), 4);
    }
}