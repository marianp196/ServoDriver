/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import domain.IStepMotor;
import domain.StepMotor;

/**
 *
 * @author marian
 */
public interface IStepMotorFactory {
    IStepMotor Build(int pin) throws Exception;
}
