package domain;

public class MessageText {
    private String messageText;

    public MessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public String toString() {
        return messageText;
    }
}

