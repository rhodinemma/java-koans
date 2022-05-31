package basics;

import io.fries.koans.Koan;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class MethodPreferenceKoans {

    String doStuff(int i) {
        return "int";
    }

    String doStuff(Integer i) {
        return "Integer";
    }

    String doStuff(Object o) {
        return "Object";
    }

    String doStuff(int... i) {
        return "int vararg";
    }

    @Koan
    void method_preference_int() {
        assertThat(doStuff(1)).isEqualTo("int");
    }

    @Koan
    void method_preference_integer() {
        assertThat(doStuff(Integer.valueOf(1))).isEqualTo("Integer");
    }

    @Koan
    void method_preference_long() {
        long l = 1;
        assertThat(doStuff(l)).isEqualTo("Object");
    }

    @Koan
    void method_preference_boxed_long() {
        Long l = Long.valueOf(1);
        assertThat(doStuff(l)).isEqualTo("Object");
    }

    @Koan
    void method_preference_double() {
        Double d = Double.valueOf(1);
        assertThat(doStuff(d)).isEqualTo("Object");
    }

    @Koan
    void method_preference_more() {
        assertThat(doStuff(1, Integer.valueOf(2))).isEqualTo("int vararg");
    }
}
