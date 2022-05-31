package io.fries.koans.basics;

public final class Primitives {

    private Primitives() {
    }

    public static Class<?> typeOf(int value) {
        return int.class;
    }

    public static Class<?> typeOf(long value) {
        return long.class;
    }

    public static Class<?> typeOf(float value) {
        return float.class;
    }

    public static Class<?> typeOf(double value) {
        return double.class;
    }

    public static Class<?> typeOf(byte value) {
        return byte.class;
    }

    public static Class<?> typeOf(char value) {
        return char.class;
    }

    public static Class<?> typeOf(short value) {
        return short.class;
    }

    public static Class<?> typeOf(Object value) {
        return value.getClass();
    }
}
