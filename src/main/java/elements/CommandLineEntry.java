package elements;

import clock.CentralSystemClock;

public class CommandLineEntry {
    private String userName;
    private String otherUsersName;
    private TimeLineMessage timeLineMessage;

    private final CentralSystemClock centralSystemClock;

    public CommandLineEntry(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public String getUserName() {
        return userName;
    }

    public TimeLineMessage getTimeLineMessage() {
        return timeLineMessage;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTimeLineMessage(String userName, String message) {
        timeLineMessage = new TimeLineMessage(centralSystemClock);
        timeLineMessage.setMessage(message);
        timeLineMessage.setUserName(userName);
    }

    public String getOtherUsersName() {
        return otherUsersName;
    }

    public void setOtherUsersName(String otherUsersName) {
        this.otherUsersName = otherUsersName;
    }
}
