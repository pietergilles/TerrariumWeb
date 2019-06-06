package be.vdab.TerrariumWeb.domain;

import java.util.List;

public enum GameController {

    INSTANCE;

    public void spawnPlants(){
        List<Location> emptyLocations = Terrarium.INSTANCE.getEmptyLocations();
        if (emptyLocations.size() > 0) {
            int randomNumber = (int) Math.ceil(Math.random() * emptyLocations.size())-1;
            Plant plant = new Plant(emptyLocations.get(randomNumber), 1); //one lifeforce
            Terrarium.INSTANCE.addOrganism(plant);
        }
    }
    public void activateOrganisms(){
        //let Carnivores interact with environment
        for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
            if(organism instanceof Carnivore){
                ((Carnivore) organism).interactWithEnvironment();
            }
        }
        //then let Herbivores interact with environment
        for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
            if(organism instanceof Herbivore){
                ((Herbivore) organism).interactWithEnvironment();
            }
        }
    }
    public void printTerrarium(){
        //not very efficient but temporary, size of Terrarium should be variable
        System.out.println(" - - - - - - - - - - - - - - ");
        for(int y=0;y<=5;y++){
            System.out.print("|\t");
            for(int x=0;x<=5;x++){

                //create new Location for every point on grid
                Location location = new Location(x, y);
                //check if there's an organism on this location and what kind
                boolean organismFound = false;
                for(Organism organism : Terrarium.INSTANCE.getAllOrganisms()){
                    if(organism.getLocation().equals(location)){
                        organismFound = true;
                        if(organism instanceof Plant){
                            System.out.print("P\t");
                        }
                        else if(organism instanceof Herbivore){
                            System.out.print("H\t");
                        }
                        else if(organism instanceof Carnivore){
                            System.out.print("C\t");
                        }
                    }
                }
                //if not print "."
                if(!organismFound){
                    System.out.print(".\t");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println(" - - - - - - - - - - - - - -");
    }
}
