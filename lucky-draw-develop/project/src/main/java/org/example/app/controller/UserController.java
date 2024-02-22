package org.example.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.app.api.award.AcceptRollAPI;
import org.example.app.api.award.RollAPI;
import org.example.app.api.session.LoginAPI;
import org.example.app.api.user.AddNewUserAPI;
import org.example.app.request.award.AcceptRollRequest;
import org.example.app.request.award.RollRequest;
import org.example.app.request.session.LoginRequest;
import org.example.app.request.user.AddNewUserRequest;
import org.example.app.response.award.AcceptRollResponse;
import org.example.app.response.award.RollResponse;
import org.example.app.response.session.LoginResponse;
import org.example.app.response.user.AddNewUserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.app.constant.ApiName.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final String TOKEN = "token";
    private final LoginAPI loginAPI;
    private final AddNewUserAPI addNewUserAPI;
    private final RollAPI rollAPI;
    private final AcceptRollAPI acceptRollAPI;

    public UserController(LoginAPI loginAPI, AddNewUserAPI addNewUserAPI, RollAPI rollAPI, AcceptRollAPI acceptRollAPI) {
        this.loginAPI = loginAPI;
        this.addNewUserAPI = addNewUserAPI;
        this.rollAPI = rollAPI;
        this.acceptRollAPI = acceptRollAPI;
    }

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        loginRequest.setApiName(LOGIN_API_NAME.getName());
        return loginAPI.execute(loginRequest);
    }
    @PostMapping("/roll")
    public RollResponse roll(HttpServletRequest request){
        String token = request.getHeader(TOKEN);
        RollRequest rollRequest = new RollRequest(ROLL_API_NAME.getName(), token);
        return rollAPI.execute(rollRequest);
    }

    @PostMapping("/accept-roll")
    public AcceptRollResponse acceptRoll(HttpServletRequest request){
        String token = request.getHeader(TOKEN);
        AcceptRollRequest acceptRollRequest = new AcceptRollRequest(ACCEPT_API_NAME.getName(), token);
        return acceptRollAPI.execute(acceptRollRequest);
    }

    @PostMapping("/add-user")
    public AddNewUserResponse addNewUser(HttpServletRequest request, @RequestBody AddNewUserRequest addNewUserRequest){
        String token = request.getHeader(TOKEN);
        addNewUserRequest.setApiName(ADD_NEW_USER.getName());
        return addNewUserAPI.execute(addNewUserRequest);
    }
}
