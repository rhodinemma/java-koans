package basics;

import io.fries.koans.Koan;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class LoopsKoans {

    @Koan
    void basic_for_loop_1() {
        String result = "";

        for (int i = 0; i < 5; i++) {
            result += i + " ";
        }

        assertThat(result).isEqualTo("0 1 2 3 4 ");
    }

    @Koan
    void basic_for_loop_2() {
        String result = "";

        for (int i = -5; i < 1; i++) {
            result += i + " ";
        }

        assertThat(result).isEqualTo("-5 -4 -3 -2 -1 0 ");
    }

    @Koan
    void basic_for_loop_3() {
        String result = "";

        for (int i = 5; i > 0; i--) {
            result += i + " ";
        }

        assertThat(result).isEqualTo("5 4 3 2 1 ");
    }

    @Koan
    void basic_for_loop_4() {
        String result = "";

        for (int i = 0; i < 11; i += 2) {
            result += i + " ";
        }

        assertThat(result).isEqualTo("0 2 4 6 8 10 ");
    }

    @Koan
    void basic_for_loop_5() {
        String result = "";

        for (int i = 1; i <= 16; i *= 2) {
            result += i + " ";
        }

        assertThat(result).isEqualTo("1 2 4 8 16 ");
    }

    @Koan
    void basic_for_loop_with_two_variables() {
        String result = "";

        for (int i = 0, j = 10; i < 5 && j > 5; i++, j--) {
            result += i + " " + j + " ";
        }

        assertThat(result).isEqualTo("0 10 1 9 2 8 3 7 4 6 ");
    }

    @Koan
    void nested_loops() {
        String result = "";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result += "(" + i + ", " + j + ") ";
            }
            result += " - ";
        }

        assertThat(result).isEqualTo("(0, 0) (0, 1) (0, 2)  - (1, 0) (1, 1) (1, 2)  - (2, 0) (2, 1) (2, 2)  - ");
    }

    @Koan
    void foreach_loop() {
        int[] numbers = {1, 2, 3, 4};
        String result = "";

        for (int number : numbers) {
            result += number + " ";
        }

        assertThat(result).isEqualTo("1 2 3 4 ");
    }

    @Koan
    void while_loop() {
        int result = 0;

        while (result < 3) {
            result++;
        }

        assertThat(result).isEqualTo(3);
    }

    @Koan
    void do_while_loop_1() {
        int result = 0;

        do {
            result++;
        } while (false);

        assertThat(result).isEqualTo(1);
    }

    @Koan
    void do_while_loop_2() {
        int result = 0;

        do {
            result++;
        } while (result < 3);

        assertThat(result).isEqualTo(3);
    }

    @Koan
    void break_a_loop() {
        String[] animals = {"Dog", "Cat", "Tiger"};
        int count = 0;

        for (String current : animals) {
            if ("Cat".equals(current)) {
                break;
            }
            count++;
        }

        assertThat(count).isEqualTo(1);
    }

    @Koan
    void continue_a_loop() {
        String[] animals = {"Dog", "Cat", "Tiger"};
        int count = 0;

        for (String current : animals) {
            if ("Dog".equals(current)) {
                continue;
            } else {
                count++;
            }
        }

        assertThat(count).isEqualTo(2);
    }

    @Koan
    void continue_a_loop_with_label() {
        int count = 0;

        outerLabel:
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                count++;
                if (count > 2) {
                    continue outerLabel;
                }
            }
            count += 10;
        }

        assertThat(count).isEqualTo(8);
    }

    @Koan
    void break_a_loop_with_label() {
        int count = 0;

        outerLabel:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                count++;
                if (count > 2) {
                    break outerLabel;
                }
            }
            count += 10;
        }

        assertThat(count).isEqualTo(3);
    }
}
