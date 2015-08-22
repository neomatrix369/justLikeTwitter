package domain;

import java.io.InputStream;

public class Keyboard {
    private final InputStream inputStream;

    public Keyboard(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream get() {
        return inputStream;
    }
}
