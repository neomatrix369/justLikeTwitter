package domain;

import java.util.Date;

public class MessageDate {
    private final Date date;

    public MessageDate(Date date) {
        this.date = new Date(date.getTime());
    }

    public Date toDate() {
        return new Date(date.getTime());
    }

    public int compareTo(MessageDate messageDate) {
        return date.compareTo(messageDate.toDate());
    }
}
