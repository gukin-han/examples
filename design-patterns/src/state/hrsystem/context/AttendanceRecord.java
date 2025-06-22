package state.hrsystem.context;

import state.hrsystem.state.*;

import java.time.LocalDateTime;

public class AttendanceRecord {

    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Status status;

    public AttendanceRecord(LocalDateTime startAt, Status status) {
        this.startAt = startAt;
        this.status = status;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setEndAt(LocalDateTime now) {
        this.endAt = now;
    }

    public enum Status {
        ON_BREAK, WORKING, ON_VACATION, OFF_DUTY;
    }
}
