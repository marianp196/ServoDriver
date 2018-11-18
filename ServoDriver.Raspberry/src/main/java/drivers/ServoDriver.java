/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drivers;

import domain.Led;

/**
 *
 * @author marian
 */
public class ServoDriver implements IServoMotorDriver{

    public ServoDriver(Led led) {
        _led = led;
    }    
    
    @Override
    public double GetMaxRotation() {
        return 0.5;
    }

    @Override
    public void SetRotation(double rotation) throws Exception {
        if(rotation < 0 || rotation > 1)
            throw new IllegalArgumentException("rotation >= 0 and rotation <= 1");
        
        System.out.println("Set rotation:  " + rotation);
        
        double signalLength = _signalMinLength + _signalLength * rotation;
        System.out.println("Signallaenge:  " + signalLength);
        
        _led.SetPeriod(0, signalLength);
    }

    @Override
    public double GetRotation() throws Exception {
        int highPeriod = (int)_led.GetEndHigh() - (int)_led.GetStartHigh();
        
        if(highPeriod < _signalMinLength || highPeriod > (_signalMinLength + _signalLength))
            throw new Exception("Not valid Signal. Length: " + highPeriod );
        
        return (highPeriod - _signalMinLength) / _signalLength;
    }
    
    private Led _led;    
    
    private double _signalMinLength = 1 / 20.0;
    private double _signalLength = 2 / 20.0;
}
