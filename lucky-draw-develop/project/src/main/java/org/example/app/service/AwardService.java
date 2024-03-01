package org.example.app.service;

import org.example.app.model.Award;
import org.example.app.repository.AwardRepository;
import org.springframework.stereotype.Service;

@Service
public class AwardService {
    private AwardRepository awardRepository;
    public AwardService(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }
    public void addNewAward(String userId, String type, int numTurns) throws Exception {
        awardRepository.addNewAward(userId, type, numTurns);
    }
    public void deleteAward(String userId) throws Exception {
        awardRepository.deleteAward(userId);
    }
    public String getPrize(String userId) throws Exception {
        return awardRepository.getPrize(userId);
    }
    public Award getAward(String userId) throws Exception {
        return awardRepository.getAward(userId);
    }
    public boolean isAlreadyHasPrize(String userId) throws Exception {
        return awardRepository.isAlreadyHasPrize(userId);
    }
     public void acceptRoll(String userId) throws Exception {
        awardRepository.acceptRoll(userId);
     }
     public boolean isValidNumRoll(String userId) throws Exception {
        return awardRepository.isValidNumRoll(userId);
     }
     public void updateRoll(String userId, String type) throws Exception {
        awardRepository.updateRoll(userId, type);
     }
}
