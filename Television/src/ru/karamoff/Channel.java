package ru.karamoff;

import java.time.LocalTime;

public class Channel {

    private Show showSet[];
    private String channelName;

    public Channel(Show[] showSet, String channelName) {
        this.showSet = showSet;
        this.channelName = channelName;
    }


    public void printShowSet() {
        for (Show s : showSet) {
            System.out.println(s.getStartTime().getHour() + ":" + (s.getStartTime().getMinute() < 10 && s.getStartTime().getMinute() >= 0 ? ("0" + s.getStartTime().getMinute()) : s.getStartTime().getMinute()) + " " + s.getShowName());
        }
        System.out.println();
    }


    public Show[] getShowSet() {
        return showSet;
    }

    public void setShowSet(Show showSet[]) {
        this.showSet = showSet;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }


    static class Show {
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
}
