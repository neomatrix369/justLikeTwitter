package domain;

public class MessageText {
    private final String message;

    public MessageText(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

