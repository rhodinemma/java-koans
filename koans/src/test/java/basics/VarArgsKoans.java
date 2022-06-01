package basics;

import io.fries.koans.Koan;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class VarArgsKoans {

    boolean canBeTreatedAsArray(Integer... arguments) {
        return arguments instanceof Integer[];
    }

    int getLength(Integer... arguments) {
        return arguments.length;
    }

    String prefixedLength(String prefix, Object... arguments) {
        return prefix + arguments.length;
    }

    // *******
    // The following methods won't compile because varargs can only be used as last argument
    // *******
    // public String invalidMethodDeclaration(String... arguments, String... otherArguments) { return ""; }
    // public String otherInvalidMethodDeclaration(String... arguments, String otherArgument) { return ""; }

    @Koan
    void var_args_can_be_treated_as_arrays() {
        assertThat(canBeTreatedAsArray(1, 2, 3)).isEqualTo(true);
    }

    @Koan
    void you_can_pass_in_as_many_arguments_as_you_like() {
        assertThat(getLength(1, 2, 3)).isEqualTo(3);
        assertThat(getLength(1, 2, 3, 4, 5, 6, 7, 8)).isEqualTo(8);
    }

    @Koan
    void you_can_pass_in_zero_arguments_if_you_like() {
        assertThat(getLength()).isEqualTo(0);
    }

    @Koan
    void you_can_have_other_types_in_the_method_signature() {
        assertThat(prefixedLength("This is how many items were passed in: ", 1, 2, 3, 4)).isEqualTo("This is how many items were passed in: ", 4);
    }
}
