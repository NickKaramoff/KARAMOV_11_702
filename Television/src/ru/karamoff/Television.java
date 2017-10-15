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

    public static Television getTelevision(Channel channels[]) {
        instance.setChannelSet(channels);
        return instance;
    }

    public void setChannelSet(Channel[] channelSet) {
        this.channelSet = channelSet;
    }

    public Channel[] getChannelSet() {
        return channelSet;
    }

    public void runningNow(String channelName) {
        for (Channel c : channelSet) {
            if (c.getChannelName().equals(channelName)) {
                for (Show s : c.getShowSet()) {
                    if (s.getStartTime().isAfter(LocalTime.now()) && s.getEndTime().isBefore(LocalTime.now())) {
                        System.out.println("На канале " + c.getChannelName() + " идёт " + s.getShowName());
                        System.out.println("С " + s.getStartTime().getHour() + ":" + s.getStartTime().getMinute() + "до" + s.getEndTime().getHour() + ":" + s.getEndTime().getMinute());
                        System.out.println();
                    }
                }
            }
        }
    }

    public void printChannelSet() {
        for (int i = 0; i < channelSet.length; i++) {
            System.out.println((i + 1) + ". " + channelSet[i].getChannelName());
        }
        System.out.println();
    }

    public void displaySchedule(String channelName) {
        for (Channel c : channelSet) {
            if (c.getChannelName().equals(channelName)) {
                c.printShowSet();
                System.out.println();
            }
        }
        System.out.println();
    }
}
