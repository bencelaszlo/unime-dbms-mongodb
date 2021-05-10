package hu.bencelaszlo.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import hu.bencelaszlo.mongodb.database.Ape;
import hu.bencelaszlo.mongodb.database.DAO;
import hu.bencelaszlo.mongodb.database.DbConnector;
import hu.bencelaszlo.mongodb.utils.Constants;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static void printQuery(String message, MongoCursor<Document> mongoCursor) {
        System.out.println(message + ":");

        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }
    }

    public static void main(String[] args) {
        DbConnector dbConnector = new DbConnector();
        MongoClient dbConnection = dbConnector.getConnection();
        DAO dao = new DAO(dbConnection);

        System.out.println("Insert new ape documents to the collection...");
        List<Ape> apes = new ArrayList<>();
        apes.add(new Ape("Tapanuli orangutan", "Pongo tapanuliensis", 800, Constants.CONSERVATION_STATUS_CRITICALLY_ENDANGERED));
        apes.add(new Ape("Eastern gorilla", "Gorilla beringei", 6000, Constants.CONSERVATION_STATUS_CRITICALLY_ENDANGERED));
        apes.add(new Ape("Sumatran orangutan", "Pongo abelii", 6667, Constants.CONSERVATION_STATUS_CRITICALLY_ENDANGERED));
        apes.add(new Ape("Bonobo", "Pan paniscus", 10000, Constants.CONSERVATION_STATUS_ENDANGERED));
        apes.add(new Ape("Bornean orangutan", "Pongo pygmaeus", 61234, Constants.CONSERVATION_STATUS_CRITICALLY_ENDANGERED));
        apes.add(new Ape("Chimpanzee", "Pan troglodytes", 100000, Constants.CONSERVATION_STATUS_ENDANGERED));
        apes.add(new Ape("Western gorilla", "Gorilla gorilla", 200000, Constants.CONSERVATION_STATUS_CRITICALLY_ENDANGERED));
        dao.createSpecies(apes);
        printQuery("Inserted species", dao.queryAll());

        System.out.println("Insert human species to the collection...");
        dao.createSpecies(new Ape("Human", "Homo Sapiens", 7812102600L, Constants.CONSERVATION_STATUS_LEAST_CONCERN));
        printQuery("Human species added", dao.queryAll());

        printQuery("Search for critically endangered species:", dao.findCriticallyEndangered());

        System.out.println("Introduce new 'vulnerable' conservation status for species with population between 50000 and 200000. Update collection...");
        dao.updateVulnerable();
        printQuery("Species after update", dao.queryAll());

        printQuery("Search for Bonobo species: ", dao.findSpecies("Bonobo"));

        System.out.println("Cleanup database, remove every document...");
        dao.cleanDatabase();
        printQuery("Collection after delete", dao.queryAll());
    }
}
