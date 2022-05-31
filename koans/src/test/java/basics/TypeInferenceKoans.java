package basics;

import io.fries.koans.Koan;

import java.util.ArrayList;
import java.util.List;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class TypeInferenceKoans {

    @Koan
    void var_allows_local_variable_type_inference() {
        var status = "SUCCESS";
        assertThat(status instanceof String).isEqualTo(true);
    }

    @Koan
    void var_can_infer_the_generic_type_using_the_affected_value() {
        var statuses = List.of("SUCCESS", "FAILURE");

        assertThat(statuses instanceof List).isEqualTo(true);
        assertThat(statuses.get(0) instanceof String).isEqualTo(true);
    }

    @Koan
    void when_the_generic_type_cannot_be_inferred_through_affectation_then_the_object_type_is_used() {
        var strings = new ArrayList<>();
        strings.add("SUCCESS");
        strings.add(42); // Whut?

        assertThat(strings.get(0) instanceof String).isEqualTo(true);
        assertThat(strings.get(1) instanceof String).isEqualTo(false);
    }

    @Koan
    void the_generic_type_can_be_inferred_using_the_diamond_operator() {
        var strings = new ArrayList<String>();
        strings.add("SUCCESS");
        // strings.add(42); // Try to uncomment this line. Does it compile? Why?

        assertThat(strings.get(0) instanceof String).isEqualTo(true);
    }

    @Koan
    void a_var_local_variable_is_mutable() {
        var x = 3;
        assertThat(x).isEqualTo(3);

        x = 5;
        assertThat(x).isEqualTo(5);
    }

    @Koan
    void use_final_var_for_immutable_type_inference() {
        final var x = 7;
        // x = 12; // Try to uncomment this line. Does it compile? Why?

        assertThat(x).isEqualTo(7);
    }

    @Koan
    void var_cannot_be_used_as_an_attribute_type() {
        class ValueHolder {
            int value = 3; // Try to use 'var' instead of 'int'. Does it compile? Why?
        }

        var valueHolder = new ValueHolder();
        assertThat(valueHolder.value).isEqualTo(3);
    }
}
