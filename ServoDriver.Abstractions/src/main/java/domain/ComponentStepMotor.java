/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import component.IWriteableComponent;
import domain.dto.Movement;
import domain.dto.Rotation;
import drivers.IStepMotorDriver;

/**
 *
 * @author marian
 */
public class ComponentStepMotor implements IStepMotor ,IWriteableComponent<Movement, Rotation>{

    
    public ComponentStepMotor(IStepMotor stepMotor, String groupId, String id) {
        _stepMotor = stepMotor;
        _id = id;
        _groupId = groupId;
    }
    
    @Override
    public double GetMaxRotation() {
        return _stepMotor.GetMaxRotation();
    }

    @Override
    public void Move(double endDegree, double step, int wait) throws Exception {
        _stepMotor.Move(endDegree, step, wait);
    }

    @Override
    public void SetRotation(double degree) throws Exception {
        _stepMotor.SetRotation(degree);
    }

    @Override
    public double GetRotation() throws Exception {
        return _stepMotor.GetRotation();
    }

    @Override
    public Rotation Write(Movement state) throws Exception {
        if(state.StepDegree == null)
            this.SetRotation(state.EndDegree);
      
        this.Move(state.EndDegree, state.StepDegree, state.Wait == null ? 0 : state.Wait);
        
        Rotation rotation = new Rotation();
        rotation.Degree = state.EndDegree;
        return rotation;
    }

    @Override
    public Rotation Read() throws Exception {
        Rotation rotation = new Rotation();
        rotation.Degree = this.GetRotation();
        rotation.MaxRotationDegree = this.GetMaxRotation();
        return rotation;
    }

    @Override
    public String GetID() {
        return _id;
    }

    @Override
    public String GetGroupID() {
        return _groupId;
    }
    
    private IStepMotor _stepMotor;
    private String _id;
    private String _groupId;
}
