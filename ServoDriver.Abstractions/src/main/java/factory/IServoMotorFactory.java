/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import domain.IServoMotor;

/**
 *
 * @author marian
 */
public interface IServoMotorFactory {
    IServoMotor Build(int pin) throws Exception;
}
