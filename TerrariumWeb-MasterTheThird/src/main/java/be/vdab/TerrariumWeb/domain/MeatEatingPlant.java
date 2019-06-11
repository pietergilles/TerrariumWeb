package be.vdab.TerrariumWeb.domain;

import java.util.List;


public class MeatEatingPlant extends Animal{



    public MeatEatingPlant(Location location, int lifeForce) {
        super(location, lifeForce);
        this.setLifeForce(19);
        Location spotInTheMiddle = new Location(3,3);
        this.setLocation(spotInTheMiddle);

    }

    @Override
    public boolean interactWithEnvironment() {
        List<Organism> organisms = this.getTerrarium().getAllOrganisms();
        Location toTheRight = new Location(getLocation().getX() + 1, getLocation().getY());
        Location toTheLeft = new Location(getLocation().getX() - 1, getLocation().getY()); // om links te kunnen kijken
        Location upOne = new Location(this.getLocation().getX(), getLocation().getY() + 1); //1 boven
        Location downOne = new Location(this.getLocation().getX(), getLocation().getY() - 1);  //1 beneden

        if (toTheRight.getX() > this.getTerrarium().getSize()) {
            toTheRight.setX(0);
        }
        boolean hasInteracted = false;
        for (Organism organism : organisms) {
            //check if there's an organism to the right

            boolean one = eetEnVerteer(organism, toTheRight);
            boolean two = eetEnVerteer(organism, toTheLeft);
            boolean three = eetEnVerteer(organism, upOne);
            boolean four = eetEnVerteer(organism, downOne); //dit returnt booleans waar je niks mee doet

            if (one){
                hasInteracted = true;
            }else if (two){
                hasInteracted = true;
            }else if (three){
                hasInteracted = true;
            }else if (four){
                hasInteracted = true;
            } else {
                hasInteracted = false;
            }
    }
    return hasInteracted;
    }

    private boolean eetEnVerteer(Organism organism, Location oneInFour){
        boolean hasInteracted = false;

        if (organism.getLocation().equals(oneInFour)) {
            if (organism.getClass().equals(Herbivore.class)) {
                eat((Herbivore) organism);
                hasInteracted = true;
            }
            if (organism.getClass().equals(Carnivore.class)) {
                eat((Carnivore) organism);
                hasInteracted = true;
            }
            if (organism.getClass().equals(Omnivore.class)) {
                fight((Omnivore) organism);
                hasInteracted = true;
            }

            if (organism.getClass().equals(MeatEatingPlant.class)) {
                fight((MeatEatingPlant) organism);
                hasInteracted = true;
            }
        }
        if(!hasInteracted){
            //does not move, does not loose HP
            return false;

        }
        return true;
    }



    private void eat(Herbivore herbivore){
        System.out.println("Meat Eating Plant preparing to eat herbivore, it now has " + this.getLifeForce() + " HP");
        this.setLifeForce(this.getLifeForce() + herbivore.getLifeForce());
        System.out.println("Meat Eating Plant has eaten herbivore, it now has " + this.getLifeForce() + " HP");
        this.getTerrarium().remove(herbivore);
    }

    private void eat(Carnivore lunchIsServed){
        System.out.println("Meat Eating Plant preparing to eat Carnivore, it now has " + this.getLifeForce() + " HP");
        this.setLifeForce(this.getLifeForce() + lunchIsServed.getLifeForce());
        System.out.println("Meat Eating Plant has eaten Carnivore, it now has " + this.getLifeForce() + " HP");
        this.getTerrarium().remove(lunchIsServed);
    }

    /*private void eat(Omnivore moreLunchIsServed){
        System.out.println("Meat Eating Plant preparing to eat herbivore, it now has " + this.getLifeForce() + " HP");
        this.setLifeForce(this.getLifeForce() + herbivore.getLifeForce());
        System.out.println("Meat Eating Plant has eaten herbivore, it now has " + this.getLifeForce() + " HP");
        this.getTerrarium().remove(herbivore);


        this.setLifeForce(this.getLifeForce() + moreLunchIsServed.getLifeForce());
        this.getTerrarium().remove(moreLunchIsServed);
    }*/
    private void eat(Plant burp){
        System.out.println("Meat Eating Plant preparing to eat Plant, it now has " + this.getLifeForce() + " HP");
        this.setLifeForce(this.getLifeForce() + burp.getLifeForce());
        System.out.println("Meat Eating Plant has eaten herbivore, it now has " + this.getLifeForce() + " HP");
        this.getTerrarium().remove(burp);
    }

    private void fight(Omnivore enemy){
        if(enemy.getLifeForce() == this.getLifeForce()){
            //do nothing
        }
        else if(enemy.getLifeForce() > this.getLifeForce()){
            enemy.setLifeForce(enemy.getLifeForce() + this.getLifeForce());
            this.getTerrarium().remove(this);
            System.out.println("Meat Eating Plant killed by an omnivore");
        }
        else if(enemy.getLifeForce() < this.getLifeForce())
            this.setLifeForce(enemy.getLifeForce() + this.getLifeForce());
            this.getTerrarium().remove(enemy);
        System.out.println("Meat Eating Plant says: Hasta La Vista Baby: I killed an Omnivore");
    }




    private void fight(MeatEatingPlant enemy){
        if(enemy.getLifeForce() == this.getLifeForce()){
            this.setLifeForce(enemy.getLifeForce() + this.getLifeForce());
            this.getTerrarium().remove(enemy);
            System.out.println(" Meat Eating Plant: I eat everybody, especially my own kind!!");
        }
        else if(enemy.getLifeForce() > this.getLifeForce()){
            enemy.setLifeForce(enemy.getLifeForce() + this.getLifeForce());
            this.getTerrarium().remove(this);
            System.out.println("Meat Eating Plant : Ahh Kaan you killed me");
        }
        else if(enemy.getLifeForce() < this.getLifeForce()){
            this.setLifeForce(enemy.getLifeForce() + this.getLifeForce());
            this.getTerrarium().remove(enemy);
            //this.getTerrarium().addNewMeatEatingPlant();
            this.getTerrarium().addNewHerbivore();
            this.getTerrarium().addNewHerbivore();
            System.out.println("Meat Eating Plant: I Will have my revenge, You'll see");
            System.out.println("I´ll be back");
        }
    }

}
