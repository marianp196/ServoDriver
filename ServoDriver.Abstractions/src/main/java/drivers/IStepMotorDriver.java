/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drivers;

/**
 *
 * @author marian
 */
public interface IStepMotorDriver {
    /**
     * Zahl zwische 0 und 1
     * 
     * 0.5 -> 180°
     * @return 
     */
    public double GetMaxRotation();
    
     /**
     * Zahl zwische 0 und 1.
     * Tatsächliche Rotation im Bereich der MaxRotation
     * 
     * Wenn maxRotation 180:
     * 
     * 0.5 -> 90°
     * @return 
     */
    public void SetRotation(double rotation) throws Exception;
    public double GetRotation() throws Exception;
}
