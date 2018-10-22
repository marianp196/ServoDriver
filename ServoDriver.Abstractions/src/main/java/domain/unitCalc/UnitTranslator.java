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
public class UnitTranslator implements IUnitTranslator  {

    public UnitTranslator(double inputValue, double factor) {
        _inputValue = inputValue;
        _factor = factor;
    }
    
     @Override
    public double GetValueInDegree() {
        return _inputValue * _factor;
    }
    
    private double _inputValue;
    private double _factor;
}
