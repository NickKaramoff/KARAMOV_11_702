package ru.karamoff;

import java.time.LocalTime;

public class Television {

    private Channel channelSet[];

    private static final Television instance;

    private Television() {

    }

    static {
        instance = new Television();
    }

    public static Television getInstance(Channel channels[]) {
        instance.setChannelSet(channels);
        return instance;
    }


    public void setChannelSet(Channel[] channelSet) {
        this.channelSet = channelSet;
    }

    public Channel[] getChannelSet() {
        return channelSet;
    }


    public void printChannelSet() {
        for (int i = 0; i < channelSet.length; i++) {
            System.out.println((i + 1) + ". " + channelSet[i].getChannelName());
        }
        System.out.println();
    }

    public void runningNow(String channelName) {
        for (Channel c : channelSet) {
            if (c.getChannelName().equals(channelName)) {
                for (Channel.Show s : c.getShowSet()) {
                    if (s.getStartTime().isBefore(LocalTime.now()) && s.getEndTime().isAfter(LocalTime.now())) {
                        System.out.println("На канале " + c.getChannelName() + " сейчас \"" + s.getShowName() + "\"");
                        System.out.println("С " + s.getStartTime().getHour() + ":" + (s.getStartTime().getMinute() < 10 && s.getStartTime().getMinute() >= 0 ? ("0" + s.getStartTime().getMinute()) : s.getStartTime().getMinute()) + " до " + s.getEndTime().getHour() + ":" + (s.getEndTime().getMinute() < 10 && s.getEndTime().getMinute() >= 0 ? ("0" + s.getEndTime().getMinute()) : s.getEndTime().getMinute()));
                        System.out.println();
                    }
                }
            }
        }
        System.out.println();
    }

    public void displaySchedule(String channelName) {
        for (Channel c : channelSet) {
            if (c.getChannelName().equals(channelName)) {
                c.printShowSet();
            }
        }
    }
}
