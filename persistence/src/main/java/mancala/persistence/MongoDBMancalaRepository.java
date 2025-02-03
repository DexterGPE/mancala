package mancala.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import mancala.domain.IMancala;
import mancala.domain.Mancala;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class MongoDBMancalaRepository implements IMancalaRepository {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;
    private String mongoUri;

    // Constructor that takes a URI
    public MongoDBMancalaRepository(String mongoUri) {
        this.mongoUri = mongoUri;
        this.mongoClient = MongoClients.create(mongoUri);  // Use the provided Mongo URI
        this.database = mongoClient.getDatabase("Games");
        this.collection = database.getCollection("savedGames");
    }

    @Override
    public void save(String key, IMancala game) {
        removeExistingEntryWithSameKey(key);

        String gameState = getGameState(game);

        Document document = new Document("key", key)
                .append("namePlayer1", game.getNameOfPlayerOne())
                .append("namePlayer2", game.getNameOfPlayerTwo())
                .append("gameState", gameState);

        collection.insertOne(document);
    }

    private void removeExistingEntryWithSameKey(String key) {
        collection.deleteOne(new Document("key", key));
    }

    private String getGameState(IMancala game) {
        StringBuilder gameStateBuilder = new StringBuilder();

        for (int i = 0; i < 14; i++) {
            gameStateBuilder.append(game.getStonesForPit(i) + " ");

        }
        gameStateBuilder.append(game.isPlayersTurn(game.getNameOfPlayerOne()) ? 1 : 0);

        return gameStateBuilder.toString();
    }

    @Override
    public IMancala get(String key) {
        Document document = collection.find(eq("key", key)).first();

        if (document != null) {
            IMancala game = convertDocumentToIMancala(document);
            return game;
        } else {
            System.out.println("Game not found!");
            return null;
        }
    }

    private IMancala convertDocumentToIMancala(Document document) {
        return new Mancala(document.getString("gameState"), document.getString("namePlayer1"), document.getString("namePlayer2"));
    }

}
