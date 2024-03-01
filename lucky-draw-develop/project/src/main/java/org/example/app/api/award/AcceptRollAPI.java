package org.example.app.api.award;

import org.example.app.api.CommonAPI;
import org.example.app.exception.BusinessException;
import org.example.app.request.award.AcceptRollRequest;
import org.example.app.response.award.AcceptRollResponse;
import org.example.app.service.AwardService;
import org.example.app.service.PrizeService;
import org.example.app.service.SessionService;
import org.example.app.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.example.app.constant.ExceptionCode.*;

@Component
public class AcceptRollAPI extends CommonAPI<AcceptRollRequest, AcceptRollResponse> {
    private SessionService sessionService;
    private UserService userService;
    private PrizeService prizeService;
    private AwardService awardService;

    public AcceptRollAPI(SessionService sessionService, SessionService sessionService1, UserService userService, PrizeService prizeService, AwardService awardService) {
        super(sessionService);
        this.sessionService = sessionService1;
        this.userService = userService;
        this.prizeService = prizeService;
        this.awardService = awardService;
    }

    @Override
    public AcceptRollResponse createResponse(int code, String message) {
        return new AcceptRollResponse(code, message);
    }
    @Override
    public AcceptRollResponse doExecute(AcceptRollRequest requestData) throws Exception {
        String token = requestData.getToken();
        String userId = sessionService.getUserId(token);
        String type = awardService.getPrize(userId);
        if(type == null){
            throw new BusinessException(INVALID.getCode(), "You haven't roll yet");
        }
        awardService.acceptRoll(userId);
        return new AcceptRollResponse(type);
    }
}
