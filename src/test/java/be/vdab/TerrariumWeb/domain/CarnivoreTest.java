package be.vdab.TerrariumWeb.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarnivoreTest {

    Carnivore carnivore = new Carnivore(new Location(1, 1), 2);

    @Before
    public void before(){
        Terrarium.INSTANCE.reset();
        Terrarium.INSTANCE.addOrganism(carnivore);
    }

    @Test
    public void carnivoreEatsHerbivoresToTheRight(){
        Herbivore herbivore = new Herbivore(new Location(2, 1), 1);
        Terrarium.INSTANCE.addOrganism(herbivore);
        int numOrganismsBeforeMeal = Terrarium.INSTANCE.getAllOrganisms().size();
        carnivore.interactWithEnvironment();
        int numOrganismsAfterMeal = Terrarium.INSTANCE.getAllOrganisms().size();
        assertEquals(numOrganismsBeforeMeal -1, numOrganismsAfterMeal);
    }

    @Test
    public void carnivoreDoesNotEatPlants(){
        Plant plant = new Plant(new Location(2, 1), 1);
        Terrarium.INSTANCE.addOrganism(plant);
        int numOrganismsBeforeMeal = Terrarium.INSTANCE.getAllOrganisms().size();
        carnivore.interactWithEnvironment();
        int numOrganismsAfterMeal = Terrarium.INSTANCE.getAllOrganisms().size();
        assertEquals(numOrganismsBeforeMeal, numOrganismsAfterMeal);
    }

    @Test
    public void carnivoreDoesNotEatHerbivoresToTheLeftTopOrBottom(){
        Herbivore herbivore1 = new Herbivore(new Location(1, 2), 1);
        Herbivore herbivore2 = new Herbivore(new Location(0, 1), 1);
        Herbivore herbivore3 = new Herbivore(new Location(1, 0), 1);
        Terrarium.INSTANCE.addOrganism(herbivore1);
        Terrarium.INSTANCE.addOrganism(herbivore2);
        Terrarium.INSTANCE.addOrganism(herbivore3);

        int numOrganismsBeforeMeal = Terrarium.INSTANCE.getAllOrganisms().size();
        carnivore.interactWithEnvironment();
        int numOrganismsAfterMeal = Terrarium.INSTANCE.getAllOrganisms().size();
        assertEquals(numOrganismsBeforeMeal, numOrganismsAfterMeal);

    }

    @Test
    public void carnivoreDoesNotMoveIfThereIsAPlantToTheRight(){
        Plant plant = new Plant(new Location(2, 1), 1);
        Terrarium.INSTANCE.addOrganism(plant);
        boolean willMove = carnivore.interactWithEnvironment();
        assertTrue(!willMove);
    }

    @Test
    public void carnivoreTakesLifeForceOfHerbivore(){
        Herbivore herbivore = new Herbivore(new Location(2, 1), 1);
        Terrarium.INSTANCE.addOrganism(herbivore);
        int lifeForceBeforeMeal = carnivore.getLifeForce();
        carnivore.interactWithEnvironment();
        int lifeForceAfterMeal = carnivore.getLifeForce();
        assertNotEquals(lifeForceBeforeMeal, lifeForceAfterMeal);
        assertEquals(lifeForceAfterMeal, lifeForceBeforeMeal +1);
    }

    @Test
    public void carnivoreDefeatsWeakerCarnivoresToTheRightAndTakesTheirLifeForce(){
        Carnivore weakerCarnivore = new Carnivore(new Location(2, 1), 1);
        Terrarium.INSTANCE.addOrganism(weakerCarnivore);
        int lifeForceBeforeFight = carnivore.getLifeForce();
        int numOrganismsBeforeFight = Terrarium.INSTANCE.getAllOrganisms().size();
        carnivore.interactWithEnvironment();
        int lifeForceAfterFight = carnivore.getLifeForce();
        int numOrganismsAFterFight = Terrarium.INSTANCE.getAllOrganisms().size();
        assertEquals(lifeForceBeforeFight + 1, lifeForceAfterFight);
        assertEquals(numOrganismsBeforeFight -1, numOrganismsAFterFight);
    }
    @Test
    public void carnivoreIsDefeatedByStrongerCarnivoresToTheRightTheyTakeItsLifeForce(){
        Carnivore strongerCarnivore = new Carnivore(new Location(2, 1), 3);
        Terrarium.INSTANCE.addOrganism(strongerCarnivore);
        int lifeForceBeforeFight = strongerCarnivore.getLifeForce();
        int numOrganismsBeforeFight = Terrarium.INSTANCE.getAllOrganisms().size();
        carnivore.interactWithEnvironment();
        int lifeForceAfterFight = strongerCarnivore.getLifeForce();
        int numOrganismsAFterFight = Terrarium.INSTANCE.getAllOrganisms().size();
        assertEquals(lifeForceBeforeFight + 2, lifeForceAfterFight);
        assertEquals(numOrganismsBeforeFight -1, numOrganismsAFterFight);
    }

    @Test
    public void carnivoresEvenlyMatchedDontFight(){
        Carnivore evenlyMatchedCarnivore = new Carnivore(new Location(2, 1), 2);
        Terrarium.INSTANCE.addOrganism(evenlyMatchedCarnivore);
        int lifeForceBeforeFight = carnivore.getLifeForce();
        int numOrganismsBeforeFight = Terrarium.INSTANCE.getAllOrganisms().size();
        carnivore.interactWithEnvironment();
        int lifeForceAfterFight = carnivore.getLifeForce();
        int numOrganismsAFterFight = Terrarium.INSTANCE.getAllOrganisms().size();
        assertEquals(lifeForceBeforeFight, lifeForceAfterFight);
        assertEquals(numOrganismsBeforeFight, numOrganismsAFterFight);
    }

    @Test
    public void carnivoresOnLastColumnInteractWithOrganismsOnFirstColumn(){
        Carnivore carnivoreOnLastColumn = new Carnivore(new Location(5, 0), 2);
        Herbivore herbivore = new Herbivore(new Location(0, 0), 1);
        Terrarium.INSTANCE.addOrganism(carnivoreOnLastColumn);
        Terrarium.INSTANCE.addOrganism(herbivore);
        int numOrganismsBeforeMeal = Terrarium.INSTANCE.getAllOrganisms().size();
        carnivoreOnLastColumn.interactWithEnvironment();
        int numOrganismsAfterMeal = Terrarium.INSTANCE.getAllOrganisms().size();
        assertEquals(numOrganismsBeforeMeal -1, numOrganismsAfterMeal);

        Carnivore carnivoreOnFirstColumn = new Carnivore(new Location(0,0), 1 );
        Terrarium.INSTANCE.addOrganism(carnivoreOnFirstColumn);
        int numOrganismsBeforeFight = Terrarium.INSTANCE.getAllOrganisms().size();
        carnivoreOnLastColumn.interactWithEnvironment();
        int numOrganismsAfterFight = Terrarium.INSTANCE.getAllOrganisms().size();
        assertEquals(numOrganismsBeforeFight -1, numOrganismsAfterFight);


    }

    @Test(expected = IllegalArgumentException.class)
    public void carnivoreCannotBeCreatedWithNullLocation(){
        Carnivore carnivore = new Carnivore(null, 1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void carnivoreCannotBeCreatedWithNegativeLifeForce(){
        Carnivore carnivore = new Carnivore(new Location(2, 1), -1);
    }


}