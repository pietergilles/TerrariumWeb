package be.vdab.TerrariumWeb.domain;

import java.util.List;

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
        Location toTheRight = new Location(getLocation().getX() +1, getLocation().getY());
        if(toTheRight.getX()>=Terrarium.INSTANCE.getSize()){
            toTheRight.setX(0);
        }
        boolean hasInteracted = false;
        for(Organism organism : organisms){
            //check if there's an organism to the right
            if(organism.getLocation().equals(toTheRight)){
                //eat it if Herbivore
//                if(organism.getClass().equals(Herbivore.class)){
//                    eat((Herbivore) organism);
//                    hasInteracted = true;
//                }
                if(organism.getClass().equals(Plant.class)) {
                    eat((Plant) organism);
                    hasInteracted = true;
                }
                else if(organism.getClass().equals(Carnivore.class)) {
                    fight((Carnivore) organism);
                    hasInteracted = true;
                }
                else if(organism.getClass().equals(Omnivore.class)){
                    if(((Animal) organism).getSex() != this.getSex()){
                        procreate();
                        System.out.println("Omnivores have procreated");
                    }
                    else{
                        fight(organism);
                        System.out.println("Omnivores fighting");
                    }
                    hasInteracted = true;
                }
            }
        }
        if(!hasInteracted){
            this.hasNotEaten();
            this.move();
            System.out.println("Omnivore has not eaten, it now has " + this.getLifeForce() + " HP");
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

