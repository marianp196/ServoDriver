/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.dto.Movement;
import domain.dto.Rotation;
import drivers.IStepMotorDriver;
/**
 *
 * @author marian
 */
public class StepMotor implements IStepMotor {

    public StepMotor(IStepMotorDriver driver) {
        _driver = driver;
    }
     
    
    @Override
    public void SetRotation(double degree) throws Exception{
        checkDegree(degree);        
        _driver.SetRotation(degree / GetMaxRotation());
    }
    
    @Override
    public double GetRotation() throws Exception{
        return _driver.GetRotation() * GetMaxRotation();
    }
   
    @Override
    public synchronized void Move(double endDegree, double stepDegree, int wait) throws Exception{
        checkDegree(endDegree);
        
        if(stepDegree <= 0)
            throw new IllegalArgumentException("steps must be higher than 0");
        if(wait < 0)
            throw new IllegalArgumentException("wait must be higher than 0");
        
        double actualDegree = GetRotation();
        
        if(actualDegree == endDegree)
            return;
        else if(actualDegree > endDegree)        
            decrease(actualDegree, stepDegree, endDegree, wait);
        else
            increase(actualDegree, stepDegree, endDegree, wait);
        
        SetRotation(endDegree);
    }
    
    @Override
    public double GetMaxRotation(){
        double r = _driver.GetMaxRotation();
        return r * 360;
    }
   
    
    /*
    Hier könnte man wohl mit Dlegaten arbeiten 
    */
    private void decrease(double actualDegree, double stepDegree, double endDegree, int wait) 
            throws Exception {
        while((actualDegree - stepDegree) > endDegree){
            actualDegree-= stepDegree;
            SetRotation(actualDegree);
            Thread.sleep(wait);
        }
    }
    
     /*
    Hier könnte man wohl mit Dlegaten arbeiten 
    */
    private void increase(double actualDegree, double stepDegree, double endDegree, int wait) 
            throws Exception {
        while((actualDegree + stepDegree) < endDegree){
            actualDegree+= stepDegree;
            SetRotation(actualDegree);
            Thread.sleep(wait);
        }
    }
    
     private void checkDegree(double degree) throws IllegalArgumentException {
        if(degree < 0)
            throw new IllegalArgumentException("degree must be higher than 0");
        if(degree > GetMaxRotation())
            throw new IllegalArgumentException("degree higher than max-rotation");
    }
    
    
    private IStepMotorDriver _driver;
}
