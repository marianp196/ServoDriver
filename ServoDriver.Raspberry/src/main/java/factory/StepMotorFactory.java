/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import domain.IStepMotor;
import domain.PVMBoard;
import domain.StepMotor;
import drivers.IStepMotorDriver;
import drivers.ServoDriver;

/**
 *
 * @author marian
 */
public class StepMotorFactory implements IStepMotorFactory{

    public StepMotorFactory(IPvmBoardFactory pvmBoardFactory, int device) throws Exception {
       _pvmBoard = pvmBoardFactory.Build(50);
    }    
    
    @Override
    public IStepMotor Build(int pin) throws Exception {
        IStepMotorDriver driver = new ServoDriver(_pvmBoard.GetLed(pin));
        return new StepMotor(driver);
    }

    private PVMBoard _pvmBoard; 
}
