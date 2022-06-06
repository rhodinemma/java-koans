package collections;

import io.fries.koans.Koan;

import java.util.NoSuchElementException;
import java.util.Optional;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class OptionalKoans {

    @Koan
    void an_object_reference_may_be_null() {
        String lastName = null;

        try {
            lastName.toUpperCase();
        } catch (final Exception e) {
            assertThat(e instanceof NullPointerException).isEqualTo(true);
        }
    }

    @Koan
    void an_optional_is_a_wrapper_of_any_type_allowing_safe_null_manipulation() {
        Optional<String> lastName = Optional.ofNullable(null);

        assertThat(lastName.isPresent()).isEqualTo(false);
        assertThat(lastName.isEmpty()).isEqualTo(true);
    }

    @Koan
    void an_optional_can_be_declared_empty() {
        Optional<String> lastName = Optional.empty();

        assertThat(lastName.isPresent()).isEqualTo(false);
        assertThat(lastName.isEmpty()).isEqualTo(true);
    }

    @Koan
    void an_optional_can_wrap_a_value() {
        Optional<String> lastName = Optional.of("lastname");

        assertThat(lastName.isPresent()).isEqualTo(true);
        assertThat(lastName.isEmpty()).isEqualTo(false);
        assertThat(lastName.get()).isEqualTo("lastname");
    }

    @Koan
    void a_null_pointer_exception_is_thrown_if_you_initialize_a_null_optional_without_using_of_nullable() {
        try {
            Optional.of(null);
        } catch (final Exception e) {
            assertThat(e instanceof NullPointerException).isEqualTo(true);
        }
    }

    @Koan
    void a_no_such_element_exception_is_thrown_when_you_try_to_get_the_value_of_an_empty_optional() {
        Optional<String> lastName = Optional.empty();

        try {
            lastName.get();
        } catch (final Exception e) {
            assertThat(e instanceof NoSuchElementException).isEqualTo(true);
        }
    }

    @Koan
    void you_can_map_the_optional_content() {
        String lastName = Optional.of("lastname")
                .map(String::toUpperCase)
                .get();

        assertThat(lastName).isEqualTo("LASTNAME");
    }

    @Koan
    void you_can_filter_the_optional_content() {
        Optional<String> lastName = Optional.of("")
                .filter(name -> !name.isBlank());

        assertThat(lastName.isPresent()).isEqualTo(false);
        assertThat(lastName.isEmpty()).isEqualTo(true);
    }

    @Koan
    void use_flat_map_to_map_to_another_optional() {
        Optional<String> lastName = Optional.of("lastname")
                .flatMap(name -> Optional.empty());

        assertThat(lastName.isPresent()).isEqualTo(false);
        assertThat(lastName.isEmpty()).isEqualTo(true);
    }

    @Koan
    void or_else_can_be_used_to_declare_a_default_value() {
        Optional<Object> lastName = Optional.empty();

        assertThat(lastName.orElse("null")).isEqualTo("null");
    }

    class HitCounter {

        private int hits = 0;

        String increment() {
            return String.valueOf(++hits);
        }
    }

    @Koan
    void or_else_is_eagerly_executed() {
        HitCounter hitCounter = new HitCounter();

        assertThat(Optional.of("last").orElse(hitCounter.increment())).isEqualTo("last");
        assertThat(Optional.empty().orElse(hitCounter.increment())).isEqualTo("2");
        assertThat(Optional.empty().orElse(hitCounter.increment())).isEqualTo("3");
        assertThat(Optional.of("name").orElse(hitCounter.increment())).isEqualTo("name");
        assertThat(Optional.empty().orElse(hitCounter.increment())).isEqualTo("5");
        assertThat(Optional.of("").orElse(hitCounter.increment())).isEqualTo("");
    }

    @Koan
    void or_else_get_can_be_used_to_supply_a_lazily_initialized_default_value() {
        HitCounter hitCounter = new HitCounter();

        assertThat(Optional.of("last").orElseGet(hitCounter::increment)).isEqualTo("last");
        assertThat(Optional.empty().orElseGet(hitCounter::increment)).isEqualTo("1");
        assertThat(Optional.empty().orElseGet(hitCounter::increment)).isEqualTo("2");
        assertThat(Optional.of("name").orElseGet(hitCounter::increment)).isEqualTo("name");
        assertThat(Optional.empty().orElseGet(hitCounter::increment)).isEqualTo("3");
        assertThat(Optional.of("").orElseGet(hitCounter::increment)).isEqualTo("");
    }
}
