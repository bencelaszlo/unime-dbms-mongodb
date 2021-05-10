package hu.bencelaszlo.mongodb.database;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import hu.bencelaszlo.mongodb.utils.Constants;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DAO {
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public DAO(MongoClient mongoClient) {
        this.database = mongoClient.getDatabase(Constants.DATABASE_DATABASE_NAME);
        this.collection = this.database.getCollection(Constants.DATABASE_COLLECTION_NAME);
    }

    public void createSpecies(Ape ape) {
        this.collection.insertOne(ape.getDocument());
    }

    public void createSpecies(List<Ape> apes) {
        List<Document> mongoApes = apes.stream().map(ape -> ape.getDocument()).toList();
        this.collection.insertMany(mongoApes);
    }

    public void cleanDatabase() {
        this.collection.deleteMany(new Document());
    }

    public MongoCursor<Document> findCriticallyEndangered() {
        MongoCursor<Document> documents = this.collection.find(new Document(Constants.APE_CONSERVATION_STATUS_KEY,  Constants.CONSERVATION_STATUS_CRITICALLY_ENDANGERED)).iterator();
        return documents;
    }

    public MongoCursor<Document> findSpecies(String speciesName) {
        MongoCursor<Document> documents = this.collection.find(new Document(Constants.APE_SPECIES_KEY, speciesName)).iterator();
        return documents;
    }

    public void updateVulnerable() {
        List<Document> newVulnerableCriteria = new ArrayList<>();
        newVulnerableCriteria.add(new Document(Constants.APE_POPULATION_KEY, new Document("$gte", Constants.VULNERABLE_SPECIES_POPULATION_MIN_LIMIT)));
        newVulnerableCriteria.add(new Document(Constants.APE_POPULATION_KEY, new Document("$lte", Constants.VULNERABLE_SPECIES_POPULATION_MAX_LIMIT)));
        Document combinedQuery = new Document("$and", newVulnerableCriteria);
        Document updateObject = new Document("$set", new Document(Constants.APE_CONSERVATION_STATUS_KEY, Constants.CONSERVATION_STATUS_VULNERABLE));
        this.collection.updateMany(combinedQuery, updateObject);
    }

    public MongoCursor<Document> queryAll() {
        MongoCursor<Document> documents = this.collection.find(new Document()).iterator();
        return documents;
    }
}
