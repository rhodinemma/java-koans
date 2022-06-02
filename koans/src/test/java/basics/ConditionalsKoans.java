package basics;

import io.fries.koans.Koan;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class ConditionalsKoans {

    @Koan
    void basic_if() {
        int x = 1;

        if (true) {
            x++;
        }

        assertThat(x).isEqualTo(2);
    }

    @Koan
    void basic_if_else() {
        int x = 1;
        boolean secretBoolean = false;

        if (secretBoolean) {
            x++;
        } else {
            x--;
        }

        assertThat(x).isEqualTo(0);
    }

    @Koan
    void basic_if_else_if_else() {
        int x = 1;
        boolean secretBoolean = false;
        boolean otherBooleanCondition = true;

        if (secretBoolean) {
            x++;
        } else if (otherBooleanCondition) {
            x = 10;
        } else {
            x--;
        }

        assertThat(x).isEqualTo(10);
    }

    @Koan
    void curly_braces_are_not_required_when_the_block_contains_a_single_instruction_but_can_be_misleading() {
        int x = 1;
        boolean secretBoolean = false;
        boolean otherBooleanCondition = true;

        if (secretBoolean)
            x++;
        if (otherBooleanCondition)
            x = 10;
        else
            x--;

        assertThat(x).isEqualTo(10);
    }

    @Koan
    void nested_conditions() {
        int x = 1;
        boolean secretBoolean = false;
        boolean otherBooleanCondition = true;

        if (secretBoolean) {
            x++;
            if (otherBooleanCondition) {
                x = 10;
            }
        } else {
            x--;
        }

        assertThat(x).isEqualTo(0);
    }

    @Koan
    void basic_switch_statement() {
        int i = 1;
        String result = "";

        switch (i) {
            case 1:
                result += "One";
                break;
            case 2:
                result += "Two";
                break;
            default:
                result += "Nothing";
        }

        assertThat(result).isEqualTo("One");
    }

    @Koan
    void switch_statement_fall_through() {
        int i = 1;
        String result = "";

        switch (i) {
            case 1:
                result += "One";
            case 2:
                result += "Two";
            default:
                result += "Nothing";
        }

        assertThat(result).isEqualTo("OneTwoNothing");
    }

    @Koan
    void switch_statement_with_an_unusual_case_ordering() {
        int i = 5;
        String result = "";

        switch (i) {
            case 1:
                result += "One";
            default:
                result += "Nothing";
            case 2:
                result += "Two";
        }

        assertThat(result).isEqualTo("NothingTwo");
    }

    @Koan
    void switch_statement_constants() {
        int i = 5;
        final int caseOne = 1;
        String result = "";

        switch (i) {
            case caseOne:
                result += "One";
                break;
            default:
                result += "Nothing";
        }

        assertThat(result).isEqualTo("Nothing");
    }

    @Koan
    void switch_statement_can_handle_multiple_types() {
        int c = 97;
        String result = "";

        switch (c) {
            case 'a':
                result += "The letter 'a'";
                break;
            default:
                result += "Nothing";
        }

        assertThat(result).isEqualTo("The letter 'a'");
    }

    class Counter {

        boolean returnValue;
        int count = 0;

        Counter(boolean returnValue) {
            this.returnValue = returnValue;
        }

        boolean increment() {
            count++;
            return returnValue;
        }
    }

    @Koan
    void conditional_or() {
        Counter trueCount = new Counter(true);
        Counter falseCount = new Counter(false);

        String result = "No";
        if (trueCount.increment() || falseCount.increment()) {
            result = "Yes";
        }

        assertThat(result).isEqualTo("Yes");
        assertThat(trueCount.count).isEqualTo(1);
        assertThat(falseCount.count).isEqualTo(0);
    }

    @Koan
    void bitwise_or() {
        Counter trueCount = new Counter(true);
        Counter falseCount = new Counter(false);

        String result = "No";
        if (trueCount.increment() | falseCount.increment()) {
            result = "Yes";
        }

        assertThat(result).isEqualTo("Yes");
        assertThat(trueCount.count).isEqualTo(1);
        assertThat(falseCount.count).isEqualTo(1);
    }

    @Koan
    void conditional_and() {
        Counter trueCount = new Counter(true);
        Counter falseCount = new Counter(false);

        String result = "No";
        if (falseCount.increment() && trueCount.increment()) {
            result = "Yes";
        }

        assertThat(result).isEqualTo("No");
        assertThat(trueCount.count).isEqualTo(0);
        assertThat(falseCount.count).isEqualTo(1);
    }

    @Koan
    void bitwise_and() {
        Counter trueCount = new Counter(true);
        Counter falseCount = new Counter(false);

        String result = "No";
        if (falseCount.increment() & trueCount.increment()) {
            result = "Yes";
        }

        assertThat(result).isEqualTo("No");
        assertThat(trueCount.count).isEqualTo(1);
        assertThat(falseCount.count).isEqualTo(1);
    }

    @Koan
    void xor_1() {
        Counter trueCount = new Counter(true);
        Counter falseCount = new Counter(false);

        String result = "No";
        if (trueCount.increment() ^ falseCount.increment()) {
            result = "Yes";
        }

        assertThat(result).isEqualTo("Yes");
        assertThat(trueCount.count).isEqualTo(1);
        assertThat(falseCount.count).isEqualTo(1);
    }

    @Koan
    void xor_2() {
        Counter trueCount = new Counter(true);
        Counter falseCount = new Counter(false);

        String result = "No";
        if (falseCount.increment() ^ trueCount.increment()) {
            result = "Yes";
        }

        assertThat(result).isEqualTo("Yes");
        assertThat(trueCount.count).isEqualTo(1);
        assertThat(falseCount.count).isEqualTo(1);
    }

    @Koan
    void xor_3() {
        Counter trueCount = new Counter(true);
        Counter falseCount = new Counter(false);

        String result = "No";
        if (trueCount.increment() ^ trueCount.increment()) {
            result = "Yes";
        }

        assertThat(result).isEqualTo("No");
        assertThat(trueCount.count).isEqualTo(2);
        assertThat(falseCount.count).isEqualTo(0);
    }

    @Koan
    void greater_than() {
        int x = 1;
        assertThat(x > 1).isEqualTo(false);
    }

    @Koan
    void greater_or_equal() {
        int x = 1;
        assertThat(x >= 1).isEqualTo(true);
    }

    @Koan
    void lower_than() {
        int x = 1;
        assertThat(x < 1).isEqualTo(false);
    }

    @Koan
    void lower_or_equal() {
        int x = 1;
        assertThat(x <= 1).isEqualTo(true);
    }

    @Koan
    void not_1() {
        assertThat(!true).isEqualTo(false);
    }

    @Koan
    void not_2() {
        int x = 1;
        assertThat(!(x == 1)).isEqualTo(false);
    }

    @Koan
    void ternary_operator_1() {
        int x = 1;
        String result = x >= 0 ? "Positive" : "Negative";

        assertThat(result).isEqualTo("Positive");
    }

    @Koan
    void ternary_operator_2() {
        int x = -1;
        String result = x >= 0 ? "Positive" : "Negative";

        assertThat(result).isEqualTo("Negative");
    }
}
