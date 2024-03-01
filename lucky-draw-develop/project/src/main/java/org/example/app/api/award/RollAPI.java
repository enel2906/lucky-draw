package org.example.app.api.award;

import org.example.app.api.CommonAPI;
import org.example.app.exception.BusinessException;
import org.example.app.request.award.RollRequest;
import org.example.app.response.award.RollResponse;
import org.example.app.service.AwardService;
import org.example.app.service.PrizeService;
import org.example.app.service.SessionService;
import org.example.app.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.example.app.constant.NumRoll.*;
import static org.example.app.constant.ExceptionCode.*;

@Component
public class RollAPI extends CommonAPI<RollRequest, RollResponse> {
    private SessionService sessionService;
    private UserService userService;
    private PrizeService prizeService;
    private AwardService awardService;

    private final Map<String, Lock> userIdLocks = new ConcurrentHashMap<>();

    public RollAPI(SessionService sessionService, SessionService sessionService1, UserService userService, PrizeService prizeService, AwardService awardService) {
        super(sessionService);
        this.sessionService = sessionService1;
        this.userService = userService;
        this.prizeService = prizeService;
        this.awardService = awardService;
    }

    @Override
    public RollResponse createResponse(int code, String message) {
        return new RollResponse(code, message);
    }
    @Override
    @Transactional
    public RollResponse doExecute(RollRequest requestData) throws Exception {
        String token = requestData.getToken();
        String userId = sessionService.getUserId(token);
        if(awardService.isAlreadyHasPrize(userId)){
            if(!awardService.isValidNumRoll(userId)){
                throw new BusinessException(REQUEST.getCode(), "You have reached maximum turns");
            }
            String oldType = awardService.getPrize(userId);
            prizeService.rollBack(oldType);
        }
        String type = prizeService.rollPrize();
        awardService.updateRoll(userId, type);
        return new RollResponse(type);
    }
}
