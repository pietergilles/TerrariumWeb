package be.vdab.TerrariumWeb.domain;

import java.util.ArrayList;
import java.util.List;

public enum Terrarium {
	INSTANCE;

	List<Organism> grid;
	long size;
	long numCarnivores;
	long numHerbivores;
	long numOmnivores;
	long numPlants;
	long numPlantsPerDay;

	public void addOrganism(Organism organism) {
		if (grid.size() <= size*size) {
			if (!grid.contains(organism)) {
				grid.add(organism);

			} else if (!this.getEmptyLocations().contains(organism.getLocation())) {
				throw new IllegalArgumentException(" er is al een organisme op die plek aanwezig");
			}


		} else if (grid.contains(organism)) {

			throw new IllegalArgumentException();
		}
	}

	public void addNewHerbivore() {
		if(getEmptyLocations().size() > 0){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			grid.add(new Herbivore(getEmptyLocations().get(randomNumber), 10));
		}
	}
	public void addNewCarnivore() {//sex of the carnivore is initialised in constructor
		if(getEmptyLocations().size() > 0){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			grid.add(new Carnivore(getEmptyLocations().get(randomNumber), 10));
		}
	}
	public void addNewOmnivore() {
		if(getEmptyLocations().size() > 0){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			grid.add(new Omnivore(getEmptyLocations().get(randomNumber), 10));
		}
	}
	Terrarium() {
		this.size = 6;
		this.numCarnivores = 2;
		this.numPlants = 1;
		this.numHerbivores = 4;
		this.numOmnivores = 2;
		reset();
	}
	public List<Location> getEmptyLocations() {


		List<Location> occupiedPlaces = new ArrayList<>();
		for(Organism organism : grid){
			occupiedPlaces.add(organism.getLocation());
		}
		List<Location> emptyPlaces = new ArrayList<>();
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				Location location = new Location(i, j);
//				System.out.println("Location: " + i + " " + j);
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

		for(int i = 1;i<=numCarnivores;i++){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			this.addOrganism(new Carnivore(getEmptyLocations().get(randomNumber), 10));
		}
		for(int i = 1;i<=numHerbivores;i++){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			this.addOrganism(new Herbivore(getEmptyLocations().get(randomNumber), 10));
		}
		for(int i = 1;i<=numOmnivores;i++){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			this.addOrganism(new Omnivore(getEmptyLocations().get(randomNumber), 10));
		}
		for(int i = 1;i<=numPlants;i++){
			int randomNumber = (int) Math.ceil(Math.random() * getEmptyLocations().size())-1;
			int randomLifeForce = (int) Math.ceil(Math.random() * 10);
			this.addOrganism(new Plant(getEmptyLocations().get(randomNumber), randomLifeForce));
		}
	}

	public void setTerrarium(long sizeTerrarium, long numCarnivores, long numHerbivores, long numOmnivores, long numPlants){
		this.size = sizeTerrarium;
		this.numCarnivores = numCarnivores;
		this.numHerbivores = numHerbivores;
		this.numOmnivores = numOmnivores;
		this.numPlants = numPlants;
		reset();
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getSize() {
		return size;
	}

	public void setNumbersOfOrganisms(long numCarnivores, long numHerbivores, long numOmnivores, long numPlants, long numPlantsPerDay) {
		this.numCarnivores = numCarnivores;
		this.numHerbivores = numHerbivores;
		this.numOmnivores = numOmnivores;
		this.numPlants = numPlants;
		this.numPlantsPerDay = numPlantsPerDay;
		reset();
	}

	public long getNumPlantsPerDay() {
		return numPlantsPerDay;
	}
}
