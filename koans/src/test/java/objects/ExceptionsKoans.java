package objects;

import io.fries.koans.Koan;

import java.io.IOException;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class ExceptionsKoans {

    private void doStuff() throws IOException {
        throw new IOException();
    }

    @Koan
    void catch_checked_exceptions_using_a_try_catch_block() {
        String result;

        try {
            doStuff();
            result = "try";
        } catch (IOException e) {
            result = "catch";
        }

        assertThat(result).isEqualTo(__);
    }

    @Koan
    void use_a_finally_block() {
        String result = "";

        try {
            doStuff();
            result += "try";
        } catch (IOException e) {
            result += "catch";
        } finally {
            result += ", finally";
        }

        assertThat(result).isEqualTo(__);
    }

    @Koan
    void use_a_finally_block_without_catch() {
        String result = "";

        try {
            result = "try";
        } finally {
            result += ", finally";
        }

        assertThat(result).isEqualTo(__);
    }

    private void doMoreStuff(StringBuilder stringBuilder) {
        try {
            stringBuilder.append("try");
            doStuff();
        } catch (IOException e) {
            stringBuilder.append("catch");
            return;
        } finally {
            stringBuilder.append(", finally");
        }
    }

    @Koan
    void finally_is_always_executed() {
        StringBuilder stringBuilder = new StringBuilder();

        doMoreStuff(stringBuilder);

        assertThat(stringBuilder.toString()).isEqualTo(__);
    }

    private String doNastyStuff(StringBuilder stringBuilder) {
        try {
            stringBuilder.append("try");
            doStuff();
            return "from try";
        } catch (IOException e) {
            stringBuilder.append(", catch");
            return "from catch";
        } finally {
            stringBuilder.append(", finally");
            return "from finally";
        }
    }

    @Koan
    void return_in_finally_block() {
        StringBuilder stringBuilder = new StringBuilder();

        assertThat(doNastyStuff(stringBuilder)).isEqualTo(__);
        assertThat(stringBuilder.toString()).isEqualTo(__);
    }

    private void doUncheckedStuff() {
        throw new RuntimeException();
    }

    @Koan
    void catch_unchecked_exceptions() {
        // __
        doUncheckedStuff();
        // __
    }

    class ParentException extends Exception {
    }

    class ChildException extends ParentException {
    }

    private void throwSomething() throws ParentException {
        throw new ChildException();
    }

    @Koan
    void catch_order() {
        String exceptionThrown = "";

        try {
            throwSomething();
        } catch (ChildException e) {
            exceptionThrown = "ChildException";
        } catch (ParentException e) {
            exceptionThrown = "ParentException";
        }

        assertThat(exceptionThrown).isEqualTo(__);
    }

    private int getStringLength(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value should not be null");
        }

        return value.length();
    }

    @Koan
    void fail_argument_validation_with_an_illegal_argument_exception() {
        String result = "";

        try {
            result += getStringLength(null);
        } catch (IllegalArgumentException e) {
            result += "IllegalArgumentException";
        }

        assertThat(result).isEqualTo(__);
    }

    @Koan
    void pass_argument_validation_with_an_illegal_argument_exception() {
        String result = "";

        try {
            result += getStringLength("valid");
        } catch (IllegalArgumentException e) {
            result += "IllegalArgumentException";
        }

        assertThat(result).isEqualTo(__);
    }
}
