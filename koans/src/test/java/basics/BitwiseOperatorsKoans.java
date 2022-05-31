package basics;

import io.fries.koans.Koan;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class BitwiseOperatorsKoans {

    @Koan
    void full_and() {
        int i = 1;

        if (true & (++i < 8)) {
            i = i + 1;
        }

        assertThat(i).isEqualTo(3);
    }

    @Koan
    void short_circuit_and() {
        int i = 1;

        if (true && (i < -28)) {
            i = i + 1;
        }
        assertThat(i).isEqualTo(1);
    }

    @Koan
    void xor_operator() {
        int i = 1;
        int a = 6;

        if ((a < 9) ^ false) {
            i = i + 1;
        }

        assertThat(i).isEqualTo(2);
    }

    @Koan
    void do_not_mistake_equals_and_equals_equals() {
        int i = 1;
        boolean a = false;

        if (a = true) {
            i++;
        }

        assertThat(a).isEqualTo(true);
        assertThat(i).isEqualTo(2);
    }

    @Koan
    void bit_shifting_right() {
        int rightShift = 8;

        rightShift = rightShift >> 1;

        assertThat(rightShift).isEqualTo(4);
    }

    @Koan
    void bit_shifting_left() {
        int leftShift = 0x80000000;

        leftShift = leftShift << 1;

        assertThat(leftShift).isEqualTo(0);
    }

    @Koan
    void bit_shifting_right_unsigned() {
        int rightShiftNegativeStaysNegative = 0x80000000;
        rightShiftNegativeStaysNegative = rightShiftNegativeStaysNegative >> 4;
        assertThat(rightShiftNegativeStaysNegative).isEqualTo(-134217728);

        int unsignedRightShift = 0x80000000;
        unsignedRightShift >>>= 4;
        assertThat(unsignedRightShift).isEqualTo(134217728);
    }
}
