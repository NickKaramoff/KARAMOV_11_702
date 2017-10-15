package ru.karamoff;

public class Channel {
    private Show showSet[];
    private String channelName;

    public Channel(Show[] showSet, String channelName) {
        this.showSet = showSet;
        this.channelName = channelName;
    }

    public Show[] getShowSet() {
        return showSet;
    }

    public void setShowSet(Show[] showSet) {
        this.showSet = showSet;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public void printShowSet() {
        for (int i=0;i<showSet.length;i++) {
            System.out.println(showSet[i].getStartTime().getHour() + ":" + showSet[i].getStartTime().getMinute() + " " + showSet[i].getShowName());
        }
    }
}
