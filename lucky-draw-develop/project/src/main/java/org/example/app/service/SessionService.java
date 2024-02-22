package org.example.app.service;

import org.example.app.repository.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private SessionRepository sessionRepository;
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
    public String createSession(String userId, String ipLogin) throws Exception {
        return sessionRepository.createSession(userId, ipLogin);
    }
    public void deleteAllSessions() throws Exception {
        sessionRepository.deleteAllSessions();
    }
    public boolean isValidToken(String token) throws Exception {
        return sessionRepository.isValidToken(token);
    }
    public String getUserId(String token) throws Exception {
        return sessionRepository.getUserId(token);
    }
    public String getIpLogin(String token) throws Exception {
        return sessionRepository.getIpLogin(token);
    }

}
