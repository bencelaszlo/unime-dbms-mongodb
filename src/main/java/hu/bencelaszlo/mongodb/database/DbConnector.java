package hu.bencelaszlo.mongodb.database;

import hu.bencelaszlo.mongodb.utils.Constants;

import com.mongodb.*;

public class DbConnector {
    private MongoClient mongoClient;

    public MongoClient getConnection() {
        if (this.mongoClient == null) {
            this.mongoClient = new MongoClient(Constants.DATABASE_URL, Constants.DATABASE_PORT);
        }

        return this.mongoClient;
    }
}
