/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import compImpl.dto.Movement;
import compImpl.dto.Rotation;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marian
 */
public class HTTPStepMotor implements IServoMotor{

    public HTTPStepMotor(int id, OkHttpClient http, String baseUrl) {
        this._id = id;
        this._http = http;
        this._baseUrl = baseUrl;
    }
       
    @Override
    public double GetMaxRotation() {
        try {
            return get(_baseUrl, _id).MaxRotationDegree;
        } catch (Exception ex) {
            Logger.getLogger(HTTPStepMotor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -100;
    }

    @Override
    public void Move(double endDegree, double step, int wait) throws Exception {
        Movement mv = new Movement();
        mv.EndDegree = endDegree;
        mv.StepDegree = step;
        mv.Wait = wait;
        post(_baseUrl, _id, mv);
    }

    @Override
    public void SetRotation(double degree) throws Exception {
        Movement mv = new Movement();
        mv.EndDegree = degree;
        post(_baseUrl, _id, mv);
    }

    @Override
    public double GetRotation() throws Exception {
        return get(_baseUrl, _id).Degree;
    }
    
    private Rotation post(String baseUrl, int id, Movement movement) throws Exception{
        Request request = createPost(getUrl(baseUrl, id), movement);
        Response response = _http.newCall(request).execute();
        
        if(!response.isSuccessful())
            throw new Exception(response.message());
        
        String body = response.body().string();
        
        if(body == null || body == "")
            throw new Exception("body is empty");
        
        return new ObjectMapper().readValue(body, Rotation.class);
    }
    
    private Rotation get(String baseUrl,int id) throws IOException, Exception{
        Response response = 
                _http.newCall(createGet(getUrl(baseUrl,id))).execute();
        
        if(!response.isSuccessful())
            throw new Exception(response.message());
        
        String body = response.body().string();
        
        if(body == null || body == "")
            throw new Exception("Body der Response war leer");
        
        return new ObjectMapper().readValue(body, Rotation.class);
    }
    
     private Request createGet(String url){
         Request request = new Request.Builder()
        .url(url)
        .get()
        .addHeader("Accept", "application/json")
        .build();

        return request;
    }
    
    private Request createPost(String url, Object body) throws JsonProcessingException{
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody bodyObj = RequestBody.create(mediaType, new ObjectMapper().writeValueAsString(body));
        
        Request request = new Request.Builder()
        .url(url)
        .post(bodyObj)
        .addHeader("Content-Type", "application/json")
        .addHeader("Accept", "application/json")
        .addHeader("Cache-Control", "no-cache")
        .build();

        return request;
    }
    
    private  String getUrl(String baseUrl, int id){
        String result = baseUrl;
        
        if(!result.endsWith("/"))
            result+= "/";
        
        result += "servo/pvmdriver/" + String.valueOf(id); //ToDo pvmDriver muss hier raus
        return result;
    }
    
    private int _id;
    private OkHttpClient _http;    
    private String _baseUrl;
}
