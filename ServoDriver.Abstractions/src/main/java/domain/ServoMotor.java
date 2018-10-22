/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.unitCalc.IUnitTranslator;
import drivers.IServoMotorDriver;
/**
 *
 * @author marian
 */
public class ServoMotor implements IServoMotor {

    public ServoMotor(IServoMotorDriver driver) {
        _driver = driver;
    }
    
    @Override
    public double GetMaxRotationDegree(){
        double r = _driver.GetMaxRotation();
        return r * 360;
    }
    
    @Override
    public void SetRotation(IUnitTranslator degree) throws Exception {
        SetRotation(degree.GetValueInDegree());
    }
    
    @Override
    public void SetRotation(double degree) throws Exception{
        checkDegree(degree);        
        _driver.SetRotation(degree / GetMaxRotationDegree());
    }
    
    @Override
    public double GetRotationDegree() throws Exception{
        return _driver.GetRotation() * GetMaxRotationDegree();
    }
   
    @Override
    public void Move(IUnitTranslator endDegree, double step, int wait) throws Exception {
        Move(endDegree.GetValueInDegree(), step, wait);
    }
    
    @Override
    public synchronized void Move(double endDegree, double stepDegree, int wait) throws Exception{
        checkDegree(endDegree);
        
        if(stepDegree <= 0)
            throw new IllegalArgumentException("steps must be higher than 0");
        if(wait < 0)
            throw new IllegalArgumentException("wait must be higher than 0");
        
        double actualDegree = GetRotationDegree();
        
        if(actualDegree == endDegree)
            return;
        else if(actualDegree > endDegree)        
            decrease(actualDegree, stepDegree, endDegree, wait);
        else
            increase(actualDegree, stepDegree, endDegree, wait);
        
        SetRotation(endDegree);
    }
   
    
    private void decrease(double actualDegree, double stepDegree, double endDegree, int wait) 
            throws Exception {
        while((actualDegree - stepDegree) > endDegree){
            actualDegree-= stepDegree;
            SetRotation(actualDegree);
            Thread.sleep(wait);
        }
    }
    
  
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
        if(degree > GetMaxRotationDegree())
            throw new IllegalArgumentException("degree higher than max-rotation");
    }
    
    
    private IServoMotorDriver _driver;
}
