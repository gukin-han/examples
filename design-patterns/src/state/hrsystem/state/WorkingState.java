package state.hrsystem.state;

import state.hrsystem.context.Employee;

public class WorkingState implements EmployeeState {

    private final Employee employee;

    public WorkingState(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void markStartWork() {

    }

    @Override
    public void markEndWork() {
        employee.changeToOffDutyState();
    }

    @Override
    public void takeBreak() {
        employee.changeToOnBreakState();
    }

    @Override
    public void startLeave() {
        employee.changeToOnVacationState();
    }

    @Override
    public void returnFromLeave() {
    }
}
