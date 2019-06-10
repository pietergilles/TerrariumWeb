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
        List<Location> possibleLocation = new ArrayList<>();
        possibleLocation.add(new Location(currentX +1, currentY));
        possibleLocation.add(new Location(currentX -1, currentY));
        possibleLocation.add(new Location(currentX , currentY +1));
        possibleLocation.add(new Location(currentX , currentY -1));
        for (Location location : possibleLocation) {
            if(location.getX() >= Terrarium.INSTANCE.getSize()) location.setX(0);
            if(location.getX() < 0) location.setX((int)Terrarium.INSTANCE.getSize()-1);
            if(location.getY() >= Terrarium.INSTANCE.getSize()) location.setY(0);
            if(location.getY() < 0) location.setY((int)Terrarium.INSTANCE.getSize()-1);
        }
        List<Location> removeList = new ArrayList<>();
        for (Location location : possibleLocation) {
            if(!emptyLocations.contains(location)) {
                removeList.add(location);
            }
        }
        possibleLocation.removeAll(removeList);
        if (possibleLocation.isEmpty()) return false;
        int randomNumber = (int) Math.ceil(Math.random() * possibleLocation.size())-1;
        this.setLocation(possibleLocation.get(randomNumber));
        return true;
    }

    public abstract boolean interactWithEnvironment();

    public Terrarium getTerrarium() {
        return Terrarium.INSTANCE;
    }

    public void hasNotEaten() {
        setLifeForce(this.getLifeForce()-1);
        if (this.getLifeForce() == 0) {
            this.getTerrarium().remove(this);
            if(this instanceof Carnivore){
                System.out.println("Carnivore died of hunger");
            }
            else if(this instanceof Herbivore){
                System.out.println("Herbivore died of hunger");
            }
            else if(this instanceof Herbivore){
                System.out.println("Omnivore died of hunger");
            }
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
