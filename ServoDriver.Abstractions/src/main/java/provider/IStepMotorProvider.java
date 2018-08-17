/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider;

import domain.IStepMotor;

/**
 *
 * @author marian
 */
public interface IStepMotorProvider {
    IStepMotor Get(int pin) throws Exception;
}
