package ru.karamoff;

import java.time.LocalTime;

class Show {
    private LocalTime startTime, endTime;
    private String showName;

    public Show(LocalTime startTime, LocalTime endTime, String showName) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.showName = showName;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}
