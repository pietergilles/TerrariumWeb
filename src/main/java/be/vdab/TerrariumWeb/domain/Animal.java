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
        List<Location> removeList = new ArrayList<>();
        for (Location possibleLocation : possibleLocations) {
            if(possibleLocation.getX() > Terrarium.INSTANCE.getSize()) {
                removeList.add(possibleLocation);
            }
            if(possibleLocation.getX() < 0) {
                removeList.add(possibleLocation);
            }
            if(possibleLocation.getY() > Terrarium.INSTANCE.getSize()) {
                removeList.add(possibleLocation);
            }
            if(possibleLocation.getY() < 0) {
                removeList.add(possibleLocation);
            }
            if(!emptyLocations.contains(possibleLocation)) {
                removeList.add(possibleLocation);
            }
        }
        possibleLocations.removeAll(removeList);
        if (possibleLocations.isEmpty()) return false;
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
