package io.fries.koans.cli;

import java.io.ByteArrayInputStream;

public final class Scanners {

    private Scanners() {
    }

    public static void fakeKeyboardInput(final String fakeInput) {
        System.setIn(new ByteArrayInputStream(fakeInput.getBytes()));
    }
}
