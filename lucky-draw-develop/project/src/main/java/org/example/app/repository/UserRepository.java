package org.example.app.repository;

import static org.example.app.constant.NumRoll.*;

import static org.example.app.constant.ExceptionCode.*;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.example.app.exception.BusinessException;
import org.example.app.model.Session;
import org.example.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import javax.print.Doc;
import static org.example.app.constant.NumRoll.*;

@Repository
public class UserRepository {
    private static final String COLLECTION_NAME = "users";
    private static final String USER_ID = "_id";
    private static final String NAME = "name";
    private static final String NUM_TURNS = "numTurns";
    private final MongoTemplate mongoTemplate;
    private final MongoCollection<Document> userCollection;

    public UserRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.userCollection = mongoTemplate.getCollection(COLLECTION_NAME);
    }
    public String addUser(String name) throws Exception {
        int numTurns = LuckyDrawTurns.getAmount();
        Document filter = new Document(NAME, name).append(NUM_TURNS, numTurns);
        userCollection.insertOne(filter);
        return filter.getObjectId(USER_ID).toString();
    }
    public boolean isNullUser(String userId) throws Exception {
        Document query = new Document(USER_ID, new ObjectId(userId));
        return userCollection.find().first() == null;
    }
    public void executeRoll(String userId) throws Exception {
        Document query = new Document(USER_ID, new ObjectId(userId));
        Document userDoc = userCollection.find(query).limit(1).first();
        assert userDoc != null;
        User user = convertFromDocument(userDoc);
        if(user.getNumTurns() >= LuckyDrawTurns.getAmount()){
            throw new BusinessException(INVALID.getCode(), "The player's has ended!");
        }
        Document update = new Document("$inc", new Document(NUM_TURNS, +1));
        userCollection.updateOne(query, update);
    }
    public boolean isValidNumRoll(String userId) throws Exception {
        Document query = new Document(USER_ID, new ObjectId(userId));
        Document userDoc = userCollection.find(query).limit(1).first();
        assert userDoc != null;
        return userDoc.getInteger(NUM_TURNS) < LuckyDrawTurns.getAmount();
    }
    public void acceptRoll(String userId) throws Exception {
        Document query = new Document(USER_ID, new ObjectId(userId));
        Document update = new Document("$set", new Document(NUM_TURNS, LuckyDrawTurns.getAmount()));

        userCollection.updateOne(query, update);
    }
    public int getNumRoll(String userId) throws Exception {
        Document query = new Document(USER_ID, new ObjectId(userId));
        Document userDoc = userCollection.find(query).limit(1).first();
        assert userDoc != null;
        return userDoc.getInteger(NUM_TURNS);
    }

    public User convertFromDocument(Document document) throws Exception{
        String id = document.getObjectId(USER_ID).toString();
        String name = document.getString(NAME);
        int numTurns = document.getInteger(NUM_TURNS);

        return new User(id, name, numTurns);
    }

}
