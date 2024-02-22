package org.example.app.api;

import org.example.app.constant.ApiName;
import org.example.app.exception.BusinessException;
import org.example.app.request.RequestData;
import org.example.app.response.ResponseData;
import org.example.app.service.SessionService;

import static org.example.app.constant.ApiName.UNAUTHEN_API;
import static org.example.app.constant.ExceptionCode.*;


public abstract class CommonAPI<T extends RequestData, V extends ResponseData> implements InterfaceAPI<T, V> {
    private final SessionService sessionService;
    public CommonAPI(SessionService sessionService){
        this.sessionService = sessionService;
    }
    public V execute(T requestData){
            try{
                requestData.checkValidation();
                String apiNameString = requestData.getApiName();
                ApiName apiName =ApiName.fromString(apiNameString);
                if(!UNAUTHEN_API.contains(apiName)){
                    String token = requestData.getToken();
                    boolean check = sessionService.isValidToken(token);
                    if(!check){
                        throw new BusinessException(INVALID.getCode(), "Invalid token");
                    }
                }
                return doExecute(requestData);
            }catch (BusinessException e){
                return createResponse(e.getCode(), e.getMessage());
            }catch (Exception e){
                return createResponse(UNKNOWN.getCode(), e.getMessage());
            }
    }
}
