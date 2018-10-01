/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author marian
 */
public interface IServoMotor{

    double GetMaxRotation();

    /**
     * 
     * @param endDegree
     * @param step
     * @param wait milliseconds
     */
    void Move(double endDegree, double step, int wait) throws Exception;

    void SetRotation(double degree) throws Exception;
    
    double GetRotation() throws Exception;    
}