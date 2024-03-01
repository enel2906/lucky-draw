package org.example.app.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.app.model.Award;
import org.example.app.model.User;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.expression.spel.ast.ValueRef;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

import static org.example.app.constant.NumRoll.LuckyDrawTurns;

@Repository
public class AwardRepository {
    private static final String COLLECTION_NAME = "awards";
    private static final String ID = "_id";
    private static final String USER_ID = "userId";
    private static final String TYPE = "type";
    private static final String NUM_TURNS = "numTurns";
    private final MongoTemplate mongoTemplate;
    private final MongoCollection<Document> awardCollection;
    public AwardRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.awardCollection = mongoTemplate.getCollection(COLLECTION_NAME);
    }

    public void addNewAward(String userId, String type, int numTurns) throws Exception {
        Document query = new Document(USER_ID, userId).append(TYPE, type).append(NUM_TURNS, numTurns);
        awardCollection.insertOne(query);
    }
    public void deleteAward(String userId) throws Exception {
        Document query = new Document(USER_ID, userId);
        awardCollection.deleteOne(query);
    }
    public String getPrize(String userId) throws Exception {
        Document query = new Document(USER_ID, userId);
        Document awardDoc = awardCollection.find(query).limit(1).first();
        assert awardDoc != null;
        return awardDoc.getString(TYPE);
    }
    public Award getAward(String userId) throws Exception {
        Document query = new Document(USER_ID, userId);
        Document awardDoc = awardCollection.find(query).limit(1).first();
        assert awardDoc != null;
        return convertFromDocument(awardDoc);
    }
    public boolean isAlreadyHasPrize(String userId) throws Exception {
        Document query = new Document(USER_ID, userId);
        return awardCollection.find(query).first() != null;
    }
    public Award convertFromDocument(Document document) throws Exception{
        String id = document.getObjectId(ID).toString();
        String userId = document.getString(USER_ID);
        String type = document.getString(TYPE);
        int numTurns = document.getInteger(NUM_TURNS);

        return new Award(id, userId, type, numTurns);
    }

    public boolean isValidNumRoll(String userId) throws Exception {
        Document query = new Document(USER_ID, userId);
        Document awardDoc = awardCollection.find(query).limit(1).first();
        assert awardDoc != null;
        return awardDoc.getInteger(NUM_TURNS) < LuckyDrawTurns.getAmount();
    }
    public void acceptRoll(String userId) throws Exception {
        Document query = new Document(USER_ID, userId);
        Document update = new Document("$set", new Document(NUM_TURNS, LuckyDrawTurns.getAmount()));

        awardCollection.updateOne(query, update);
    }
    public int getNumRoll(String userId) throws Exception {
        Document query = new Document(USER_ID, userId);
        Document awardDoc = awardCollection.find(query).limit(1).first();
        if(awardDoc == null){
            return 0;
        }
        return awardDoc.getInteger(NUM_TURNS);
    }

    public void updateRoll(String userId, String type) throws Exception {
        Document query = new Document(USER_ID, userId);
        if(awardCollection.find(query).limit(1).first() == null){
            addNewAward(userId, type, 1);
            return;
        }
        Document update = new Document("$set", new Document(TYPE, type)).append("$inc", new Document(NUM_TURNS, 1));
        awardCollection.updateOne(query, update);
    }
}
