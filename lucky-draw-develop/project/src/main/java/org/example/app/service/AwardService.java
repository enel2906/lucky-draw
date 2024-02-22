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
    public void addNewAward(String userId, String type) throws Exception {
        awardRepository.addNewAward(userId, type);
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
}
