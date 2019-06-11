package be.vdab.TerrariumWeb.domain;

import java.util.List;

public class Carnivore extends Animal{


    public Carnivore(Location location, int lifeForce) {
        super(location, lifeForce);
    }

    @Override
    public boolean interactWithEnvironment() {
        List<Organism> organisms = this.getTerrarium().getAllOrganisms();
        Location toTheRight = new Location(getLocation().getX() +1, getLocation().getY());
        Location upOne = new Location(this.getLocation().getX(), getLocation().getY() + 1); //erboven
        Location downOne = new Location(this.getLocation().getX(), getLocation().getY() - 1);  //er beneden
        boolean hasInteracted = false;
        for(Organism organism : organisms){

            if (this.getLifeForce() <= 20){

                if (eetEnVerteer(organism, toTheRight)){
                    hasInteracted = true;
                } else {
                    hasInteracted = false;
                }
            }

            if ((this.getLifeForce() > 20)&& (this.getLifeForce() <= 30)){
                boolean one = eetEnVerteer(organism, toTheRight);
                boolean two = eetEnVerteer(organism, downOne);

                if (one == true) {
                    hasInteracted = true;
                } else if (two == true){
                    hasInteracted = true;
                } else {
                    hasInteracted = false;
                }
            }

            if (this.getLifeForce() > 30){
                boolean one =  eetEnVerteer(organism, toTheRight);
                boolean two = eetEnVerteer(organism, downOne);
                boolean three =  eetEnVerteer(organism, upOne);
                //if (one ^ two ^ three = true) {
                if (one ==true) {
                    hasInteracted = true;

                } else if (two==true){
                     hasInteracted = true;
                } else if (three = true){
                     hasInteracted = true;
                } else {
                     hasInteracted = false ;
                }

            }
            }
        return hasInteracted;
    }

    private boolean eetEnVerteer(Organism organism, Location oneInThree) {
        boolean hasInteracted = false;

        if (organism.getLocation().equals(oneInThree)) {
            if (organism.getClass().equals(Herbivore.class)) {
                eat((Herbivore) organism);
                hasInteracted = true;
            } else if (organism.getClass().equals(Carnivore.class)) {
                if (((Animal) organism).getSex() != this.getSex()) {
                    procreate();
                    System.out.println("Carnivores have procreated");
                } else {
                    fight(organism);
                    System.out.println("Carnivores fighting");
                }
                if (organism.getClass().equals(Omnivore.class)) {
                    fight((Omnivore) organism);
                    hasInteracted = true;
                }

                /*if (organism.getClass().equals(MeatEatingPlant.class)) {
                    fight((MeatEatingPlant) organism);
                    hasInteracted = true;
                }*/
            }
            if (!hasInteracted) {
                this.hasNotEaten();
                this.move();
                return false;
            }

        }
        return true;
    }



    private void eat(Herbivore herbivore){
        this.setLifeForce(this.getLifeForce() + herbivore.getLifeForce());
        this.getTerrarium().remove(herbivore);
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
    private void procreate(){
        Terrarium.INSTANCE.addNewCarnivore();
    }
}


