package domain;

import java.io.IOException;
import java.io.OutputStream;

public class Screen {
    private OutputStream outputStream;

    public Screen(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public OutputStream get() {
        return outputStream;
    }

    public void write(byte[] bytes) throws IOException {
        outputStream.write(bytes);
    }
}
