package org.example.app.repository;

import com.mongodb.Mongo;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.app.configuration.databaseconfig.MongoConfig;
import org.example.app.model.Session;
import org.example.app.model.User;
import org.example.app.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public class SessionRepository {
    private static final String COLLECTION_NAME = "sessions";
    private static final String ID = "_id";
    private static final String TOKEN = "token";
    private static final String USER_ID = "userId";
    private static final String IP_LOGIN = "ipLogin";

    private final MongoTemplate mongoTemplate;
    private final MongoCollection<Document> sessionCollection;

    @Autowired
    public SessionRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.sessionCollection = mongoTemplate.getCollection(COLLECTION_NAME);
    }

    public String createSession(String userId, String ipLogin) throws Exception {
        String token = Util.getRandomString();
        Document filter = new Document(USER_ID, userId).append(IP_LOGIN, ipLogin).append(TOKEN, token);
        sessionCollection.insertOne(filter);
        return token;
    }
    public void deleteAllSessions() throws Exception {
        Document filter = new Document();
        sessionCollection.deleteMany(filter);
    }
    public boolean isValidToken(String token) throws Exception {
        Document query = new Document(TOKEN, token);
        return sessionCollection.find(query).first() != null;
    }
    public String getUserId(String token) throws Exception {
        Document query = new Document(TOKEN, token);
        Document sessionDoc = sessionCollection.find(query).limit(1).first();
        assert sessionDoc != null;
        Session session = convertFromDocumentToTokenModel(sessionDoc);
        return session.getUserId();
    }
    public String getIpLogin(String token) throws Exception {
        Document query = new Document(TOKEN, token);
        Document sessionDoc = sessionCollection.find(query).limit(1).first();
        assert sessionDoc != null;
        Session session = convertFromDocumentToTokenModel(sessionDoc);
        return session.getIp();
    }

    public Session convertFromDocumentToTokenModel(Document tokenDoc) throws Exception {
        String tokenId = tokenDoc.get(TOKEN).toString();
        String userId = tokenDoc.get(USER_ID).toString();
        String ipLogin = tokenDoc.getString(IP_LOGIN);
        return new Session(tokenId, userId, ipLogin);
    }

}
