package be.vdab.TerrariumWeb.domain;

import java.util.ArrayList;
import java.util.List;

public enum Terrarium {

	INSTANCE;
	List<Organism> grid;
	long size;
	long numCarnivores;
	long numHerbivores;
	long numPlants;
	long numOmnivores;


	public void addOrganism(Organism organism) {
		//Location occupiedLocation = new Location(organism.getLocation().getX(), organism.getLocation().getY());
		if (grid.size() <= size*size) {
			if (!grid.contains(organism)) {
				grid.add(organism);

			} else if (!this.getEmptyLocations().contains(organism.getLocation())) {
				throw new IllegalArgumentException(" er is al een organisme op die plek aanwezig");
			}


		} else if (grid.contains(organism)) {

			throw new IllegalArgumentException(" de index is meer dan "+grid.size());
		}
	}

	public void addNewHerbivore() {
		if(getEmptyLocations().size() > 0){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			grid.add(new Herbivore(getEmptyLocations().get(randomNumber), 0));
		}
	}

	//adds new carnivore
	public void addNewCarnivore() {//sex of the carnivore is initialised in constructor
		if(getEmptyLocations().size() > 0){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			grid.add(new Carnivore(getEmptyLocations().get(randomNumber), 0));
		}
	}



	Terrarium() {
		this.size = 6;

		grid = new ArrayList<>(36);
		//minimum capcity max capacity kan je niet instellen


		//in het geval dat het randomnummer hetzelfde is, zal er een fout zijn
		int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
		this.addOrganism(new Plant(getEmptyLocations().get(randomNumber), 1)); //one lifeforce

		randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
		this.addOrganism(new Herbivore(getEmptyLocations().get(randomNumber), 0));

		randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
		this.addOrganism(new Herbivore(getEmptyLocations().get(randomNumber), 0));

		randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
		this.addOrganism(new Herbivore(getEmptyLocations().get(randomNumber), 0));

		randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
		this.addOrganism(new Carnivore(getEmptyLocations().get(randomNumber), 0));

		randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
		this.addOrganism(new Carnivore(getEmptyLocations().get(randomNumber), 0));
	}

	//grid heeft een fixed grote nodig nu

	public List<Location> getEmptyLocations() {


		List<Location> occupiedPlaces = new ArrayList<>();
		for(Organism organism : grid){
			occupiedPlaces.add(organism.getLocation());
		}
		List<Location> emptyPlaces = new ArrayList<>();
		for(int i=0;i<=this.getSize();i++){
			for(int j=0;j<=this.getSize();j++){ //this.getSize instead of size
				Location location = new Location(i, j);
				if(!occupiedPlaces.contains(location)){
					emptyPlaces.add(location);
				}
			}
		}
		return emptyPlaces;

	}

	public List<Organism> getAllOrganisms() {

		List<Organism> allOrganisms = new ArrayList<>();

		for(Organism organism : grid) {
			if (organism instanceof Organism) {
				allOrganisms.add(organism);
			}
		}
		return allOrganisms;
	}

	public void remove(Organism organism){

		grid.remove(organism);
	}

	public void reset(){
		grid = new ArrayList<>();
		long capacity = size*size;//ook niet beter met getter ?

		for(int i = 1;i<=numCarnivores;i++){
			int randomNumber = (int) Math.ceil(Math.random() * capacity)-1;
			this.addOrganism(new Carnivore(getEmptyLocations().get(randomNumber), 1));
		}
		for(int i = 1;i<=numHerbivores;i++){
			int randomNumber = (int) Math.ceil(Math.random() * capacity)-1;
			this.addOrganism(new Herbivore(getEmptyLocations().get(randomNumber), 1));
		}
		for(int i = 1;i<=numPlants;i++){
			int randomNumber = (int) Math.ceil(Math.random() * capacity)-1;
			this.addOrganism(new Plant(getEmptyLocations().get(randomNumber), 1));
		}

	}

	public void setTerrarium(long sizeTerrarium, long numCarnivores, long numHerbivores,
							 long numPlants, long numOmnivores){

		this.size = sizeTerrarium;
		this.numCarnivores = numCarnivores;
		this.numHerbivores = numHerbivores;
		this.numOmnivores = numOmnivores;
		this.numPlants = numPlants;
		long capacity = sizeTerrarium*sizeTerrarium;
		grid = new ArrayList<>();

		for(int i = 1;i<=numCarnivores;i++){
			int randomNumber = (int) Math.ceil(Math.random() * capacity)-1;
			this.addOrganism(new Carnivore(getEmptyLocations().get(randomNumber), 1));
		}
		for(int i = 1;i<=numHerbivores;i++){
			int randomNumber = (int) Math.ceil(Math.random() * capacity)-1;
			this.addOrganism(new Herbivore(getEmptyLocations().get(randomNumber), 1));
		}
		for(int i = 1;i<=numPlants;i++){
			int randomNumber = (int) Math.ceil(Math.random() * capacity)-1;
			this.addOrganism(new Plant(getEmptyLocations().get(randomNumber), 1));
		}


	}

	public long getSize() {
		return size;
	}

	public void setSize(long size){
		this.size = size;
	}
}
