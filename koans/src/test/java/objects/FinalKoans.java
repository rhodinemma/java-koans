package objects;

import io.fries.koans.Koan;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class FinalKoans {

    @Koan
    void a_final_variable_cannot_be_muted() {
        final int x = 1;

        // x = 3; // Try to uncomment this line. Does it compile? Why?
        assertThat(x).isEqualTo(__);
    }

    class ValueHolder {
        int value = 3;
    }

    @Koan
    void only_the_reference_held_by_a_variable_is_considered_final() {
        final ValueHolder valueHolder = new ValueHolder();
        assertThat(valueHolder.value).isEqualTo(__);

        valueHolder.value = 5;
        assertThat(valueHolder.value).isEqualTo(__);
    }

    class FinalValueHolder {

        final int value;
        final int otherValue = 42;

        FinalValueHolder(final int value) {
            this.value = value;
        }
    }

    @Koan
    void a_final_attribute_must_be_initialized_either_directly_or_in_the_class_constructor() {
        FinalValueHolder finalValueHolder = new FinalValueHolder(3);

        assertThat(finalValueHolder.value).isEqualTo(__);
        assertThat(finalValueHolder.otherValue).isEqualTo(__);
    }

    @Koan
    void even_if_the_class_attributes_are_final_the_variable_reference_can_still_be_muted_if_the_variable_is_not_marked_final() {
        FinalValueHolder finalValueHolder = new FinalValueHolder(5);
        assertThat(finalValueHolder.value).isEqualTo(__);

        finalValueHolder = new FinalValueHolder(7);
        assertThat(finalValueHolder.value).isEqualTo(__);
    }

    class Dog {

        String bark() {
            return "Woof!";
        }

        final boolean isHungry() {
            return true;
        }
    }

    class Puppy extends Dog {

        @Override
        String bark() {
            return "Squeak!";
        }

        // Try to uncomment this method. Does it compile? Why?
        // @Override
        // boolean isHungry() {
        //     return false;
        // }
    }

    @Koan
    void a_method_marked_final_cannot_be_overloaded() {
        Dog dog = new Dog();
        assertThat(dog.bark()).isEqualTo(__);
        assertThat(dog.isHungry()).isEqualTo(__);

        Puppy puppy = new Puppy();
        assertThat(puppy.bark()).isEqualTo(__);
        assertThat(puppy.isHungry()).isEqualTo(__);
    }

    final class Atom {
    }

    // Can this class extend Atom? Does it compile? Why?
    final class LittleAtom {
    }

    @Koan
    void a_class_marked_final_cannot_be_extended() {
        Object atom = new Atom();
        assertThat(atom instanceof Atom).isEqualTo(__);

        Object littleAtom = new LittleAtom();
        assertThat(littleAtom instanceof LittleAtom).isEqualTo(__);
        assertThat(littleAtom instanceof Atom).isEqualTo(__);
    }

    // Can you mark this interface as final? Does it compile? Why?
    /* final */ interface Action {

        default String description() {
            return "Default action";
        }
    }

    @Koan
    void an_interface_cannot_be_marked_final_because_it_is_meant_to_be_implemented() {
        final Action action = new Action() {
        };

        assertThat(action).isEqualTo(__);
    }

    class ImmutableValueHolder {

        final int value;

        ImmutableValueHolder(final int value) {
            this.value = value;
        }

        ImmutableValueHolder add(final int valueToAdd) {
            return new ImmutableValueHolder(this.value + valueToAdd);
        }
    }

    @Koan
    void immutability_is_a_great_way_to_enforce_data_consistency() {
        ImmutableValueHolder originalValueHolder = new ImmutableValueHolder(3);
        assertThat(originalValueHolder.value).isEqualTo(__);

        ImmutableValueHolder updatedValueHolder = originalValueHolder.add(2);
        assertThat(updatedValueHolder.value).isEqualTo(__);

        assertThat(originalValueHolder == updatedValueHolder).isEqualTo(__);
    }
}
