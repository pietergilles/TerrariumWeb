package be.vdab.TerrariumWeb.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal extends Organism {

    char sex;

    public Animal(Location location, int lifeForce) {
        super(location, lifeForce);
        int randomNumber = (int) (Math.ceil(Math.random()*2));
        if (randomNumber < 2){
            this.setSex('M');
        } else {
            this.setSex('F');
        }
    }

    public boolean move() {
        int currentX = this.getLocation().getX();
        int currentY = this.getLocation().getY();
        List<Location> emptyLocations = Terrarium.INSTANCE.getEmptyLocations();
        List<Location> possibleLocations = new ArrayList<>();
        possibleLocations.add(new Location(currentX +1, currentY));
        possibleLocations.add(new Location(currentX -1, currentY));
        possibleLocations.add(new Location(currentX , currentY +1));
        possibleLocations.add(new Location(currentX , currentY -1));
        for (Location location : possibleLocations) {
            if(location.getX() >= Terrarium.INSTANCE.getSize()) location.setX(0);
            if(location.getX() < 0) location.setX((int)Terrarium.INSTANCE.getSize()-1);
            if(location.getY() >= Terrarium.INSTANCE.getSize()) location.setY(0);
            if(location.getY() < 0) location.setY((int)Terrarium.INSTANCE.getSize()-1);
        }
        List<Location> removeList = new ArrayList<>();
        for (Location location : possibleLocations) {
            if(!emptyLocations.contains(location)) {
                removeList.add(location);
            }
        }
        possibleLocations.removeAll(removeList);
        if (possibleLocations.isEmpty()) {
            return false;
        }
        int randomNumber = (int) Math.ceil(Math.random() * possibleLocations.size())-1;
        this.setLocation(possibleLocations.get(randomNumber));
        return true;
    }

    public abstract boolean interactWithEnvironment();

    public Terrarium getTerrarium() {
        return Terrarium.INSTANCE;
    }

    public void hasNotEaten() {
        setLifeForce(this.getLifeForce()-1);
        if (this.getLifeForce() == 0) {
            Location location = this.getLocation();
            Terrarium.INSTANCE.remove(this);
            int randomLifeForce = (int) Math.ceil(Math.random() * 10);
            Terrarium.INSTANCE.addOrganism(new Plant(location, randomLifeForce));
        }
    }

    public char getSex()
    {
        return sex;
    }

    public void setSex(char sex)
    {
        this.sex = sex;
    }
}
