package org.example.app.repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.example.app.model.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

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
        Document matchFilter = new Document(QUANTITY, new Document("$gt", 0));

        AggregateIterable<Document> randomResult = prizeCollection.aggregate(Arrays.asList(
                Aggregates.match(matchFilter),
                Aggregates.sample(1)
        ));
        Document prizeDoc = randomResult.first();
        assert prizeDoc != null;
        String id = prizeDoc.getObjectId(ID).toString();

        Document query = new Document(ID, new ObjectId(id));
        Document update = new Document("$inc", new Document(QUANTITY, -1));
        prizeCollection.updateOne(query, update);
        return prizeDoc.getString(TYPE);
    }
    public void rollBack(String type) throws Exception {
        Document query = new Document(TYPE, type);
        Document update = new Document("$inc", new Document(QUANTITY, 1));

        prizeCollection.updateOne(query, update);
    }
}
