/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.squareup.okhttp.OkHttpClient;
import domain.HTTPStepMotor;
import domain.IStepMotor;

/**
 *
 * @author marian
 */
public class HTTPStepMotorFactory implements IStepMotorFactory {

    public HTTPStepMotorFactory(String baseUrl) {
        this._baseUrl = baseUrl;
    }
       
    @Override
    public IStepMotor Build(int pin) throws Exception {
        OkHttpClient http = new OkHttpClient();
        return new HTTPStepMotor(pin, http, _baseUrl);
    }
    
    private String _baseUrl;
}
