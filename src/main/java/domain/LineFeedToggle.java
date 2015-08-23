package domain;

public class LineFeedToggle {
    private final boolean toggleValue;

    public LineFeedToggle(boolean toggleValue) {
        this.toggleValue = toggleValue;
    }

    public boolean toBoolean() {
        return toggleValue;
    }
}
