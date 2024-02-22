package org.example.app.api.user;

import org.example.app.api.CommonAPI;
import org.example.app.request.user.AddNewUserRequest;
import org.example.app.response.user.AddNewUserResponse;
import org.example.app.service.SessionService;
import org.example.app.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class AddNewUserAPI extends CommonAPI<AddNewUserRequest, AddNewUserResponse> {
    private SessionService sessionService;
    private UserService userService;

    public AddNewUserAPI(SessionService sessionService, SessionService sessionService1, UserService userService) {
        super(sessionService);
        this.sessionService = sessionService1;
        this.userService = userService;
    }

    @Override
    public AddNewUserResponse createResponse(int code, String message) {
        return new AddNewUserResponse(code, message);
    }
    @Override
    public AddNewUserResponse doExecute(AddNewUserRequest requestData) throws Exception {
        String name = requestData.getName();
        String userId = userService.addUser(name);

        return new AddNewUserResponse(userId);
    }
}
