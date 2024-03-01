package org.example.app.repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.example.app.constant.Prize;
import org.example.app.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static org.example.app.constant.Prize.*;

import javax.print.Doc;
import java.util.Arrays;
import java.util.Collections;

@Repository
public class PrizeRepository {
    private static final String COLLECTION_NAME = "prizes";
    private static final String ID = "_id";
    private static final String QUANTITY = "quantity";
    private static final String TYPE = "type";
    private final MongoTemplate mongoTemplate;
    private final MongoCollection<Document> prizeCollection;

    @Autowired
    public PrizeRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.prizeCollection = mongoTemplate.getCollection(COLLECTION_NAME);
    }
    public void addNewPrize(String type, int quantity) throws Exception {
        Document filter = new Document(TYPE, type).append(QUANTITY, quantity);
        prizeCollection.insertOne(filter);
    }

    public String rollPrize() throws Exception {
        Prize prize = Util.getRandomPrize(PRIZE_LIST);
        String type = prize.getType();
        Document query = new Document(TYPE, type);
        Document prizeDoc = prizeCollection.find(query).limit(1).first();
        assert prizeDoc != null;
        int quantity = prizeDoc.getInteger(QUANTITY);
        if(quantity <= 0){
            PRIZE_LIST.remove(prize);
            return rollPrize();
        }
        Document update = new Document("$inc", new Document(QUANTITY, -1));
        prizeCollection.updateOne(query, update);
        return prizeDoc.getString(TYPE);
    }

    public void rollBack(String type) throws Exception {
        Prize prize = Prize.fromString(type);
        if(!isPrizeInList(prize)){
            PRIZE_LIST.add(prize);
        }
        Document query = new Document(TYPE, type);
        Document update = new Document("$inc", new Document(QUANTITY, 1));

        prizeCollection.updateOne(query, update);
    }
}
