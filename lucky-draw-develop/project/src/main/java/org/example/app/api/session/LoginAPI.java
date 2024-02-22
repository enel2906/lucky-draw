package org.example.app.api.session;

import org.example.app.api.CommonAPI;
import org.example.app.exception.BusinessException;
import org.example.app.request.session.LoginRequest;
import org.example.app.response.session.LoginResponse;
import org.example.app.service.SessionService;
import org.example.app.service.UserService;
import org.springframework.stereotype.Component;

import static org.example.app.constant.ExceptionCode.*;

@Component
public class LoginAPI extends CommonAPI<LoginRequest, LoginResponse> {
    private SessionService sessionService;
    private UserService userService;

    public LoginAPI(SessionService sessionService, SessionService sessionService1, UserService userService) {
        super(sessionService);
        this.sessionService = sessionService1;
        this.userService = userService;
    }

    @Override
    public LoginResponse createResponse(int code, String message) {
        return new LoginResponse(code, message);
    }
    @Override
    public LoginResponse doExecute(LoginRequest requestData) throws Exception {
        String userId = requestData.getUserId();
        if(userService.isNullUser(userId)){
            throw new BusinessException(INVALID.getCode(), "Invalid userId!");
        }
        String ipLogin = requestData.getIpLogin();
        String token = sessionService.createSession(userId, ipLogin);

        return new LoginResponse(token);
    }
}
