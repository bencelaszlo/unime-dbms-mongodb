package hu.bencelaszlo.mongodb.database;

import hu.bencelaszlo.mongodb.utils.Constants;

import org.bson.Document;

public class Ape {
    private String species;
    private String binomialName;
    private long population;
    private String conservationStatus;

    public Ape(String species, String binomialName, long population, String conservationStatus) {
        this.species = species;
        this.binomialName = binomialName;
        this.population = population;
        this.conservationStatus = conservationStatus;
    }

    public Document getDocument() {
        Document doc = new Document();
        doc.append(Constants.APE_SPECIES_KEY, this.species);
        doc.append(Constants.APE_BINOMIAL_NAME_KEY, this.binomialName);
        doc.append(Constants.APE_POPULATION_KEY, this.population);
        doc.append(Constants.APE_CONSERVATION_STATUS_KEY, this.conservationStatus);

        return doc;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBinomialName() {
        return binomialName;
    }

    public void setBinomialName(String binomialName) {
        this.binomialName = binomialName;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getConservationStatus() {
        return conservationStatus;
    }

    public void setConservationStatus(String conservationStatus) {
        this.conservationStatus = conservationStatus;
    }
}
