package org.example.app.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.app.model.Award;
import org.example.app.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.expression.spel.ast.ValueRef;
import org.springframework.stereotype.Repository;

@Repository
public class AwardRepository {
    private static final String COLLECTION_NAME = "awards";
    private static final String ID = "_id";
    private static final String USER_ID = "userId";
    private static final String TYPE = "type";
    private final MongoTemplate mongoTemplate;
    private final MongoCollection<Document> awardCollection;
    public AwardRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.awardCollection = mongoTemplate.getCollection(COLLECTION_NAME);
    }

    public void addNewAward(String userId, String type) throws Exception {
        Document query = new Document(USER_ID, userId).append(TYPE, type);
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

        return new Award(id, userId, type);
    }
}
