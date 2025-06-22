package state.hrsystem.state;

public interface EmployeeState {
    void markStartWork();

    void markEndWork();

    void takeBreak();

    void startLeave();

    void returnFromLeave();
}
