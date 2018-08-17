/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import component.IComponent;
import component.IWriteableComponent;
import domain.dto.Movement;
import domain.dto.Rotation;

/**
 *
 * @author marian
 */
public interface IStepMotor{

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
