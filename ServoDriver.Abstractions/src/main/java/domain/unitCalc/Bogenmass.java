/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.unitCalc;

/**
 *
 * @author marian
 */
public class Bogenmass extends UnitTranslator {
    
    public Bogenmass(double inputValue) {
        super(inputValue, (2*Math.PI) / 360);
    }
    
}
