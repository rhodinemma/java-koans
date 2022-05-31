package basics;

import io.fries.koans.Koan;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class EqualityKoans {

    @Koan
    void equals_equals_tests_if_two_objects_are_the_same() {
        Object object = new Object();
        Object sameObject = object;

        assertThat(object == sameObject).isEqualTo(true);
        assertThat(object == new Object()).isEqualTo(false);
    }

    @Koan
    void default_equals_method_tests_if_two_objects_are_the_same() {
        Object object = new Object();

        assertThat(object.equals(object)).isEqualTo(true);
        assertThat(object.equals(new Object())).isEqualTo(false);
    }

    @Koan
    void equals_method_can_be_overridden_to_test_if_two_objects_are_equal___1_of_2() {
        Object object = Integer.valueOf(1);

        assertThat(object.equals(object)).isEqualTo(true);
        assertThat(object.equals(Integer.valueOf(1))).isEqualTo(true);
    }

    @Koan
    void equals_method_can_be_overridden_to_test_if_two_objects_are_equal___2_of_2() {
        Integer value1 = Integer.valueOf(4);
        Integer value2 = Integer.valueOf(2 + 2);

        assertThat(value1.equals(value2)).isEqualTo(true);
        assertThat(value1).isEqualTo(value2);
    }

    @Koan
    void instantiated_objects_are_never_equal_to_null() {
        assertThat(new Object().equals(null)).isEqualTo(false);
    }

    @Koan
    void by_default_an_object_is_equal_to_itself() {
        Object object = new Object();
        assertThat(object.equals(object)).isEqualTo(true);
    }
}
