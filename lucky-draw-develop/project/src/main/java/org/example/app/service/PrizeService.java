package org.example.app.service;

import org.example.app.repository.PrizeRepository;
import org.springframework.stereotype.Service;

@Service
public class PrizeService {
    private PrizeRepository prizeRepository;
    public PrizeService(PrizeRepository prizeRepository) {
        this.prizeRepository = prizeRepository;
    }
    public void addNewPrize(String type, int quantity) throws Exception {
        prizeRepository.addNewPrize(type, quantity);
    }
    public String rollPrize() throws Exception {
        return prizeRepository.rollPrize();
    }
    public void rollBack(String type) throws Exception {
        prizeRepository.rollBack(type);
    }
}
