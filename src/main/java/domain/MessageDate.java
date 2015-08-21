package domain;

import java.util.Date;

public class MessageDate {
    private final Date date;

    public MessageDate(Date date) {
        this.date = date;
    }

    public Date toDate() {
        return date;
    }

    public int compareTo(MessageDate messageDate) {
        return date.compareTo(messageDate.toDate());
    }

    public long getTime() {
        return date.getTime();
    }
}
