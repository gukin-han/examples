package state.hrsystem.context;

import state.hrsystem.state.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static state.hrsystem.context.AttendanceRecord.Status.*;

public class Employee implements EmployeeState {

    private final EmployeeState offDutyState = new OffDutyState(this);
    private final EmployeeState onBreakState = new OnBreakState(this);
    private final EmployeeState onVacation = new OnVacation(this);
    private final EmployeeState workingState = new WorkingState(this);

    private EmployeeState currentState;

    public Employee() {
        this.changeToOffDutyState();
    }

    private List<AttendanceRecord> records = new ArrayList<>();

    public void changeToWorkingState() {
        processRecord(WORKING);
        this.currentState = workingState;
    }

    public void changeToOnVacationState() {
        processRecord(ON_VACATION);
        this.currentState = onVacation;
    }

    public void changeToOnBreakState() {
        processRecord(ON_BREAK);
        this.currentState = onBreakState;
    }

    public void changeToOffDutyState() {
        processRecord(OFF_DUTY);
        this.currentState = offDutyState;
    }


    private void processRecord(AttendanceRecord.Status status) {
        // 기록이 없는 경우
        if (records.isEmpty()) {
            records.add(new AttendanceRecord(LocalDateTime.now(), status));

        // 기록이 있는 경우
        } else {
            AttendanceRecord last = records.getLast();

            // 이전 레코드가 근무중이 아니면 이전 레코드의 종료시각 입력
            if (!status.equals(last.getStatus())) {
                last.setEndAt(LocalDateTime.now());
                records.add(new AttendanceRecord(LocalDateTime.now(), status));
            }
        }
    }

    @Override
    public void markStartWork() {
        this.currentState.markStartWork();
    }

    @Override
    public void markEndWork() {
        this.currentState.markEndWork();
    }

    @Override
    public void takeBreak() {
        this.currentState.takeBreak();
    }

    @Override
    public void startLeave() {
        this.currentState.startLeave();
    }

    @Override
    public void returnFromLeave() {
        this.currentState.returnFromLeave();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (AttendanceRecord record : records) {
            sb.append("상태 : ").append(record.getStatus());
            sb.append("\n");
            sb.append("기록 : ").append(record.getStartAt()).append(" - ").append(record.getEndAt());
            sb.append("\n");
            sb.append("------------\n");
        }
        return sb.toString();
    }
}
