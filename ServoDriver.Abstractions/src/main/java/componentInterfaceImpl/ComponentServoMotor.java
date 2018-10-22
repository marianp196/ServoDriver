/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentInterfaceImpl;

import component.IWriteableComponent;
import componentInterfaceImpl.dto.Movement;
import componentInterfaceImpl.dto.Rotation;
import domain.IServoMotor;
/**
 *
 * @author marian
 */

//ToDo Gehört eigentlich nicht AbstractionsOrdner
public class ComponentServoMotor implements IWriteableComponent<Movement, Rotation>{

    
    public ComponentServoMotor(IServoMotor stepMotor, String groupId, String id) {
        _stepMotor = stepMotor;
        _id = id;
        _groupId = groupId;
    }
    
    @Override
    public Rotation Write(Movement state) throws Exception {
        if(state.StepDegree == null)
            _stepMotor.SetRotation(state.EndDegree);
      
        _stepMotor.Move(state.EndDegree, state.StepDegree, 
                state.Wait == null ? 0 : state.Wait);
        
        Rotation rotation = new Rotation();
        rotation.Degree = state.EndDegree;
        rotation.MaxRotationDegree = _stepMotor.GetMaxRotationDegree();
        return rotation;
    }

    @Override
    public Rotation Read() throws Exception {
        Rotation rotation = new Rotation();
        rotation.Degree = _stepMotor.GetRotationDegree();
        rotation.MaxRotationDegree = _stepMotor.GetMaxRotationDegree();
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
    
    private IServoMotor _stepMotor;
    private String _id;
    private String _groupId;
}
