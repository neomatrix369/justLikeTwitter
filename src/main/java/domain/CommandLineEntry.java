package domain;

import clock.CentralSystemClock;

public class CommandLineEntry {
    private String userName;
    private String otherUsersName;
    private MessagePosted messagePosted;

    private final CentralSystemClock centralSystemClock;

    public CommandLineEntry(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public String getUserName() {
        return userName;
    }

    public MessagePosted getMessagePosted() {
        return messagePosted;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMessagePosted(String userName, String message) {
        messagePosted = new MessagePosted(centralSystemClock);
        messagePosted.setMessage(message);
        messagePosted.setUserName(userName);
    }

    public String getOtherUsersName() {
        return otherUsersName;
    }

    public void setOtherUsersName(String otherUsersName) {
        this.otherUsersName = otherUsersName;
    }
}
