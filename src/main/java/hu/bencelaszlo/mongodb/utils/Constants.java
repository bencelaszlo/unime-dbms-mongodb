package hu.bencelaszlo.mongodb.utils;

public class Constants {
    // Database
    public static final String DATABASE_URL = "localhost";
    public static final int DATABASE_PORT = 27017;
    public static final String DATABASE_DATABASE_NAME = "apes";
    public static final String DATABASE_COLLECTION_NAME = "apes";

    // Keys of the Ape model's attributes
    public static final String APE_SPECIES_KEY = "species";
    public static final String APE_BINOMIAL_NAME_KEY = "binomialName";
    public static final String APE_POPULATION_KEY = "population";
    public static final String APE_CONSERVATION_STATUS_KEY = "conservationStatus";
    // Possible conservationStatus values
    public static final String CONSERVATION_STATUS_CRITICALLY_ENDANGERED = "Critically endangered";
    public static final String CONSERVATION_STATUS_ENDANGERED = "Endangered";
    public static final String CONSERVATION_STATUS_VULNERABLE = "Vulnerable";
    public static final String CONSERVATION_STATUS_LEAST_CONCERN = "Concern";

    // DAO
    public static final int VULNERABLE_SPECIES_POPULATION_MAX_LIMIT = 200000;
    public static final int VULNERABLE_SPECIES_POPULATION_MIN_LIMIT = 50000;
}
