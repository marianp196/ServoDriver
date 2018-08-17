/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drivers;

import domain.Led;
import driver.IPVMBoardDriver;

/**
 *
 * @author marian
 */
public class ServoDriver implements IStepMotorDriver{

    public ServoDriver(Led pvmDriver) {
        _led = pvmDriver;
    }    
    
    @Override
    public double GetMaxRotation() {
        return 0.5;
    }

    @Override
    public void SetRotation(double rotation) throws Exception {
        if(rotation < 0 || rotation > 1)
            throw new IllegalArgumentException("rotation >= 0 and rotation <= 1");
        _led.SetPeriod(0, _signalMinLength + _signalLength * rotation);
    }

    @Override
    public double GetRotation() throws Exception {
        int highPeriod = (int)_led.GetEndHigh() - (int)_led.GetStartHigh();
        if(highPeriod < _signalMinLength || highPeriod > (_signalMinLength + _signalLength))
            throw new Exception("Not valid Signal. Length: " + highPeriod );
        return (highPeriod - _signalMinLength) / _signalLength;
    }
    
    private Led _led;    
    
    private int _signalMinLength = 200;
    private int _signalLength = 200;
}
