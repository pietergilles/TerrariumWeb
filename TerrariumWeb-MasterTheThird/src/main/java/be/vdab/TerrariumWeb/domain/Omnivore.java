package be.vdab.TerrariumWeb.domain;

import java.util.List;
import be.vdab.TerrariumWeb.domain.Organism;
import be.vdab.TerrariumWeb.domain.Terrarium;
import be.vdab.TerrariumWeb.domain.Location;

public class Omnivore extends Animal{

    private char sex;

    public Omnivore(Location location, int lifeForce) {

        super(location, lifeForce);
        int randomNumber = (int) (Math.ceil(Math.random()) * 2);
        if (randomNumber <2){
            this.sex ='M';
        } else {
            this.sex ='F';
        }
    }

    @Override
    public boolean interactWithEnvironment() {
        List<Organism> organisms = this.getTerrarium().getAllOrganisms();
        Location toTheRight = new Location(getLocation().getX() + 1, getLocation().getY());
        Location toTheRightPlusOne = new Location(getLocation().getX() + 2, getLocation().getY());
        Location toTheRightPlusTwo = new Location(getLocation().getX() + 3, getLocation().getY());

        boolean hasInteracted = false;


        for (Organism organism : organisms) {
            //check if there's an organism to the right

            if (this.getLifeForce() <= 20) {
                boolean one = eetEnVerteer(organism, toTheRight);
                if (one) {
                    hasInteracted = one;
                } else {
                    hasInteracted = false;
                }

            } else if ((this.getLifeForce() > 20) && (this.getLifeForce() <= 30)) {
                boolean one = eetEnVerteer(organism, toTheRight);
                boolean two = eetEnVerteer(organism, toTheRightPlusOne);
                if (one) {
                    hasInteracted = true;
                } else if (two) {
                    hasInteracted = true;
                } else {
                    hasInteracted = false;}

                } else if (this.getLifeForce() > 30) {
                    boolean one = eetEnVerteer(organism, toTheRight);
                    boolean two = eetEnVerteer(organism, toTheRightPlusOne);
                    boolean three = eetEnVerteer(organism, toTheRightPlusTwo);
                    if (one) {
                        hasInteracted = true;
                    } else if (two) {
                        hasInteracted = true;
                    } else if (three) {
                        hasInteracted = true;
                    } else {
                        hasInteracted = false;
                    }
                }

            }
        return hasInteracted;
        }

    private boolean eetEnVerteer(Organism organism, Location superRight){
        boolean hasInteracted = false;
                if (organism.getLocation().equals(superRight)) {
                    //eat it if Herbivore
                    if (organism.getClass().equals(Herbivore.class)) {
                        eat((Herbivore) organism);
                        hasInteracted = true;
                    } else if (organism.getClass().equals(Plant.class)) {
                        eat((Plant) organism);
                        hasInteracted = true;
                    } else if (organism.getClass().equals(Carnivore.class)) {
                        fight((Carnivore) organism);
                        hasInteracted = true;
                    } else if (organism.getClass().equals(Omnivore.class)) {
                        if (((Animal) organism).getSex() != this.getSex()) {
                            procreate();
                            System.out.println("Omnivores have procreated");
                        } else {
                            fight(organism);
                            System.out.println("Omnivores fighting");
                        }
                        hasInteracted = true;
                    }
                }

            if (!hasInteracted) {
                this.hasNotEaten();
                this.move();
                System.out.println("Omnivore has not eaten, it now has " + this.getLifeForce() + " HP");
                if (this.getLifeForce() == 0){
                    this.getTerrarium().remove(this); //is deze lijn echt nodig
                }
                return false;
            }
            return true;
        }


    private void eat(Herbivore herbivore){
        System.out.println("Omnivore preparing to eat herbivore, it now has " + this.getLifeForce() + " HP");

        this.setLifeForce(this.getLifeForce() + herbivore.getLifeForce());
        System.out.println("Omnivore has eaten herbivore, it now has " + this.getLifeForce() + " HP");
        this.getTerrarium().remove(herbivore);
    }

    private void eat(Plant plant){
        System.out.println("Omnivore preparing to eat plant, it now has " + this.getLifeForce() + " HP");

        this.setLifeForce(this.getLifeForce() + plant.getLifeForce());
        System.out.println("Omnivore has eaten plant, it now has " + this.getLifeForce() + " HP");
        this.getTerrarium().remove(plant);
    }


    private void fight(Organism enemy){
        if(enemy.getLifeForce() == this.getLifeForce()){
            //do nothing
        }
        else if(enemy.getLifeForce() > this.getLifeForce()){
            enemy.setLifeForce(enemy.getLifeForce() + this.getLifeForce());
            this.getTerrarium().remove(this);
        }
        else if(enemy.getLifeForce() < this.getLifeForce()){
            this.setLifeForce(enemy.getLifeForce() + this.getLifeForce());
            this.getTerrarium().remove(enemy);
        }
    }

    public char getSex(){
        return this.sex;
    }

    private void procreate(){
        Terrarium.INSTANCE.addNewOmnivore();
    }

}

