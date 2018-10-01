/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import domain.PVMBoard;
import domain.ServoMotor;
import drivers.ServoDriver;
import domain.IServoMotor;
import drivers.IServoMotorDriver;

/**
 *
 * @author marian
 */
public class StepMotorFactory implements IServoMotorFactory{

    public StepMotorFactory(IPvmBoardFactory pvmBoardFactory) throws Exception {
       _pvmBoard = pvmBoardFactory.Build(50);
    }    
    
    @Override
    public IServoMotor Build(int pin) throws Exception {
        IServoMotorDriver driver = new ServoDriver(_pvmBoard.GetLed(pin));
        return new ServoMotor(driver);
    }

    private PVMBoard _pvmBoard; 
}
