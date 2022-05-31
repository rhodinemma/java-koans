package objects;

import io.fries.koans.Koan;
import org.assertj.core.api.Assertions;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class LambdaKoans {

    interface Specification<T> {
        boolean isSatisfiedBy(final T candidate);
    }

    @Koan
    void you_can_implement_an_interface() {
        class IsOdd implements Specification<Integer> {
            @Override
            public boolean isSatisfiedBy(final Integer candidate) {
                return candidate % 2 != 0;
            }
        }

        final Specification<Integer> isOdd = new IsOdd();

        assertThat(isOdd.isSatisfiedBy(3)).isEqualTo(__);
    }

    @Koan
    void you_can_create_an_anonymous_instance_of_an_interface() {
        Specification<Integer> isOdd = new Specification<Integer>() {
            @Override
            public boolean isSatisfiedBy(final Integer candidate) {
                return candidate % 2 != 0;
            }
        };

        assertThat(isOdd.isSatisfiedBy(3)).isEqualTo(__);
    }

    @Koan
    void you_can_create_a_lambda_from_an_interface_with_one_method() {
        Specification<Integer> isOdd = candidate -> candidate % 2 != 0;

        assertThat(isOdd.isSatisfiedBy(3)).isEqualTo(__);
    }

    // This annotation will ensure that your interface has only one abstract method.
    @FunctionalInterface
    interface ComplexSpecification<T> {

        boolean isSatisfiedBy(final T candidate);

        // Try to remove the default keyword and the method body. Does it compile? Why?
        default ComplexSpecification<T> not() {
            return candidate -> !isSatisfiedBy(candidate);
        }
    }

    @Koan
    void you_can_call_default_methods_on_a_type_instance_created_through_a_lambda() {
        ComplexSpecification<Integer> isOdd = candidate -> candidate % 2 != 0;
        ComplexSpecification<Integer> isEven = isOdd.not();

        assertThat(isOdd.isSatisfiedBy(3)).isEqualTo(__);
        assertThat(isEven.isSatisfiedBy(3)).isEqualTo(__);
    }

    boolean isOdd(final int candidate) {
        return candidate % 2 != 0;
    }

    @Koan
    void a_method_can_be_called_from_a_lambda_body() {
        Specification<Integer> isOdd = candidate -> isOdd(candidate);

        assertThat(isOdd.isSatisfiedBy(3)).isEqualTo(__);
    }

    @Koan
    void a_method_reference_can_be_used_when_a_method_has_the_expected_argument_and_return_value() {
        Specification<Integer> isOdd = this::isOdd;

        assertThat(isOdd.isSatisfiedBy(3)).isEqualTo(__);
    }

    @Koan
    void a_method_reference_is_a_lambda_instance_in_itself() {
        assertThat(((Specification<Integer>) this::isOdd).isSatisfiedBy(3)).isEqualTo(__);
    }

    @Koan
    void a_lambda_instance_can_only_use_outer_final_variables_or_non_mutated_variables() {
        final int divideBy = 5;
        Specification<Integer> isMultipleOfFive = candidate -> candidate % divideBy == 0;

        assertThat(isMultipleOfFive.isSatisfiedBy(5)).isEqualTo(__);

        // Try to remove the 'final' keyword from the divideBy variable. Does it compile? Why?
        // Now uncomment the line below. Does it compile? Why?
        // divideBy = 3;

        assertThat(isMultipleOfFive.isSatisfiedBy(10)).isEqualTo(__);
    }

    interface FailWithMessage {
        void fail();
    }

    @Koan
    void a_lambda_body_is_lazily_initialized() {
        FailWithMessage failWithMessage = () -> Assertions.fail("If you comment the lambda call, the Assertions.fail(String) method will never be executed.");

        failWithMessage.fail();
    }
}
