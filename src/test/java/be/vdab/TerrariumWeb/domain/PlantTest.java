package be.vdab.TerrariumWeb.domain;

import org.junit.Before;
import org.junit.Test;

public class PlantTest {
    Plant plant = new Plant(new Location(2,2), 1);

    @Before
    public void before(){
        Terrarium.INSTANCE.reset();
        Terrarium.INSTANCE.addOrganism(plant);
    }


    @Test(expected = IllegalArgumentException.class)
    public void plantCannotBeCreatedWithNullLocation(){
        Plant Plant = new Plant(null, 1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void plantCannotBeCreatedWithNegativeLifeForce(){
        Plant Plant = new Plant(new Location(2, 1), -1);
    }
}