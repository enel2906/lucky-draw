package org.example.app.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.example.app.request.RequestData;
import org.example.app.response.ResponseData;
import org.springframework.web.bind.annotation.RestController;

public interface InterfaceAPI<T, V>{
    public V doExecute(T requestData) throws Exception;
    public  V execute(T r);
    public V createResponse(int code, String message);
}
