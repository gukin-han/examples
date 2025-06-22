package state.hrsystem.state;

import state.hrsystem.context.Employee;

public class OnVacation implements EmployeeState {

    private final Employee employee;

    public OnVacation(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void markStartWork() {

    }

    @Override
    public void markEndWork() {

    }

    @Override
    public void takeBreak() {

    }

    @Override
    public void startLeave() {

    }

    @Override
    public void returnFromLeave() {
        employee.changeToOffDutyState();
    }
}
