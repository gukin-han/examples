package state.hrsystem.state;

import state.hrsystem.context.Employee;

public class OffDutyState implements EmployeeState{

    private final Employee employee;

    public OffDutyState(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void markStartWork() {
        employee.changeToWorkingState();
    }

    @Override
    public void markEndWork() {
    }

    @Override
    public void takeBreak() {
    }

    @Override
    public void startLeave() {
        employee.changeToOnVacationState();
    }

    @Override
    public void returnFromLeave() {
    }
}
