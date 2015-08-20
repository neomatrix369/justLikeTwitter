package elements;

import processors.DateTimeCentral;

public class CommandLineEntry {
    private String userName;
    private String otherUsersName;
    private TimeLineMessage timeLineMessage;

    private final DateTimeCentral dateTimeCentral;

    public CommandLineEntry(DateTimeCentral dateTimeCentral) {
        this.dateTimeCentral = dateTimeCentral;
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

    public void setTimeLineMessage(String message) {
        timeLineMessage = new TimeLineMessage(dateTimeCentral);
        timeLineMessage.setMessage(message);
    }

    public String getOtherUsersName() {
        return otherUsersName;
    }

    public void setOtherUsersName(String otherUsersName) {
        this.otherUsersName = otherUsersName;
    }
}
