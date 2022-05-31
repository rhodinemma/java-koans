package basics;

import io.fries.koans.Koan;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

class ArithmeticOperatorsKoans {

    @Koan
    void simple_operations() {
        assertThat(1).isEqualTo(1);
        assertThat(1 + 1).isEqualTo(2);
        assertThat(2 + 3 * 4).isEqualTo(14);
        assertThat((2 + 3) * 4).isEqualTo(20);
        assertThat(2 * 3 + 4).isEqualTo(10);
        assertThat(2 - 3 + 4).isEqualTo(3);
        assertThat(2 + 4 / 2).isEqualTo(4);
        assertThat((2 + 4) / 2).isEqualTo(3);
    }

    @Koan
    void more_operations() {
        assertThat(1 / 2).isEqualTo(0);
        assertThat(3 / 2).isEqualTo(1);
        assertThat(1 % 2).isEqualTo(1);
        assertThat(3 % 2).isEqualTo(1);
    }

    @Koan
    void decrement_variable() {
        int i = 1;
        assertThat(--i).isEqualTo(0);
        assertThat(i).isEqualTo(0);
        assertThat(i--).isEqualTo(0);
        assertThat(i).isEqualTo(-1);
    }

    @Koan
    void increment_variable() {
        int i = 1;
        assertThat(++i).isEqualTo(2);
        assertThat(i).isEqualTo(2);
        assertThat(i++).isEqualTo(2);
        assertThat(i).isEqualTo(3);
    }

    @Koan
    void in_place_operations() {
        int i = 3;
        i += 2;
        assertThat(i).isEqualTo(5);
        i -= 1;
        assertThat(i).isEqualTo(4);
        i *= 2;
        assertThat(i).isEqualTo(8);
        i /= 2;
        assertThat(i).isEqualTo(4);
    }
}