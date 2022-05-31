package objects;

import io.fries.koans.Koan;

import java.util.concurrent.atomic.AtomicInteger;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

@SuppressWarnings("all")
class GenericsKoans {

    @Koan
    void sometimes_you_may_create_duplicate_behaviors() {
        class IntegerList {

            private final Integer[] values;

            IntegerList(final Integer... values) {
                this.values = values;
            }

            Integer get(final int index) {
                return values[index];
            }
        }

        class DoubleList {

            private final Double[] values;

            DoubleList(final Double... values) {
                this.values = values;
            }

            Double get(final int index) {
                return values[index];
            }
        }

        IntegerList integers = new IntegerList(3, 5, 7);
        int firstInt = integers.get(0);
        assertThat(firstInt).isEqualTo(__);

        DoubleList doubles = new DoubleList(3.3, 5.5, 7.7);
        double thirdDouble = doubles.get(3);
        assertThat(thirdDouble).isEqualTo(__);
    }

    @Koan
    void and_you_may_think_that_polymorphism_can_help_you_solve_your_duplication_problem() {
        // `Integer` and `Double` both extend the `Number` class...
        class NumberList {

            private final Number[] values;

            NumberList(final Number... values) {
                this.values = values;
            }

            Number get(final int index) {
                return values[index];
            }
        }

        NumberList integers = new NumberList(3, 5, 7);
        int firstInt = (int) integers.get(0); // Try to remove the cast to int. Does it compile? Why?
        assertThat(firstInt).isEqualTo(__);

        NumberList doubles = new NumberList(3.3, 5.5, 7.7);
        double thirdDouble = (double) doubles.get(3); // Try to remove the cast to double. Does it compile? Why?
        assertThat(thirdDouble).isEqualTo(__);
    }

    @Koan
    void but_polymorphism_cannot_be_used_to_constrain_return_type() {
        class NumberList {

            private final Number[] values;

            NumberList(final Number... values) {
                this.values = values;
            }

            Number get(final int index) {
                return values[index];
            }
        }

        NumberList integers = new NumberList(1, 2, 3, 4, 5, 6., 7, 8, 9);
        assertThat(integers.get(5) instanceof Integer).isEqualTo(__);
    }

    @Koan
    void generic_types_can_be_used_to_completely_generify_a_behavior() {
        class GenericList<T> {

            private final T[] values;

            GenericList(final T... values) {
                this.values = values;
            }

            T get(final int index) {
                return values[index];
            }
        }

        GenericList<Integer> integers = new GenericList<Integer>(3, 5, 7);
        int firstInteger = integers.get(0); // You do not need to force cast the return type of the `get` method anymore!
        assertThat(firstInteger).isEqualTo(__);

        // When the generic type can be inferred by the compiler,
        GenericList<Double> doubles = new GenericList<>(3.3, 5.5, 7.7);
        double secondDouble = doubles.get(1); // ^-- This ("<>") is called the "Diamond Operator": it infers the generic type when possible.
        assertThat(secondDouble).isEqualTo(__);

        // You are not constraint to only use classes extending `Number` in this example.
        GenericList<String> strings = new GenericList<>("First", "Second", "Third");
        String thirdString = strings.get(2);
        assertThat(thirdString).isEqualTo(__);

        // When the Diamond Operator cannot infer the type, it uses `Object` as a default type.
        GenericList<Object> objects = new GenericList<>();

        // Without the generic type parameter, all generic types are considered as `Object`.
        GenericList integersOrObjects = new GenericList<>(3, 5, 7);
        Object isThisAnInt = integersOrObjects.get(1); // Try to change this variable type to `Integer`. Does it compile? Why?
        assertThat(isThisAnInt instanceof Integer).isEqualTo(__);

        // GenericList<int> otherIntegers = new GenericList<>(3, 5, 7); // Try to uncomment this line. Does it compile? Why?
    }

    @Koan
    void a_generic_type_can_be_constrained_by_its_parent_using_an_upper_bound() {
        // Our Bounded Generic `T` must extend `Number`. This is called an "Upper Bound".
        class NumberList<T extends Number> {

            private final T[] values;

            NumberList(final T... values) {
                this.values = values;
            }

            T get(final int index) {
                return values[index];
            }
        }

        // `Integer` extends `Number`.
        NumberList<Integer> integers = new NumberList<>(3, 5, 7);
        int thirdInteger = integers.get(2);
        assertThat(thirdInteger).isEqualTo(__);

        // `AtomicInteger` extends `Number`.
        NumberList<AtomicInteger> atomicIntegers = new NumberList<>(new AtomicInteger(1));
        AtomicInteger firstAtomicInteger = atomicIntegers.get(0);
        assertThat(firstAtomicInteger.get()).isEqualTo(__);

        // NumberList<String> strings = new NumberList<>("First", "Second", "Third"); // Try to uncomment this line. Does it compile? Why?
    }

    @Koan
    void a_generic_type_can_have_multiple_bounds() {
        class NumberList<T extends Number & Comparable> {

            private final T[] values;

            NumberList(final T... values) {
                this.values = values;
            }

            T get(final int index) {
                return values[index];
            }
        }

        // `Integer` extends `Number` and implements `Comparable`.
        NumberList<Integer> integers = new NumberList<>(3, 5, 7);
        int secondInteger = integers.get(1);
        assertThat(secondInteger).isEqualTo(__);

        // NumberList<AtomicInteger> atomicIntegers = new NumberList<>(new AtomicInteger(1)); // Try to uncomment this line. Does it compile? Why?
    }

    private <T extends Number> T getGenericValue(final T value) {
        return value;
    }

    @Koan
    void a_method_can_take_a_generic_type_as_parameter() {
        assertThat(getGenericValue(1) instanceof Integer).isEqualTo(__);
        assertThat(getGenericValue(1.) instanceof Double).isEqualTo(__);
        // assertThat(getGenericValue("Generic parameter") instanceof String).isEqualTo(__); // Try to uncomment this line. Does it compile? Why?
    }

    @Koan
    void there_is_no_limit_to_the_number_of_generic_types_a_class_or_method_can_use() {
        class Pair<T, U> {

            final T left;
            final U right;

            Pair(final T left, final U right) {
                this.left = left;
                this.right = right;
            }
        }

        Pair<Integer, String> firstPair = new Pair<>(1, "Two");
        assertThat(firstPair.left instanceof Integer).isEqualTo(__);
        assertThat(firstPair.right instanceof String).isEqualTo(__);

        Pair<String, Double> pair = new Pair<>("One", 2.);
        assertThat(pair.left instanceof String).isEqualTo(__);
        assertThat(pair.right instanceof Double).isEqualTo(__);
    }

    interface Animal {
        String noise();
    }

    class Dog implements Animal {
        @Override
        public String noise() {
            return "Woof!";
        }
    }

    class Puppy extends Dog {
        @Override
        public String noise() {
            return "Squeak!";
        }
    }

    class Cat implements Animal {
        @Override
        public String noise() {
            return "Meow!";
        }
    }

    class AnimalList<T extends Animal> {

        private final T[] values;

        AnimalList(final T... values) {
            this.values = values;
        }

        T get(final int index) {
            return values[index];
        }

        String noises() {
            return stream(values).map(Animal::noise).collect(joining(", "));
        }
    }

    String invariantAnimalNoises(final AnimalList<Animal> animals) {
        return animals.noises();
    }

    String covariantAnimalNoises(final AnimalList<? extends Animal> animals) {
        return animals.noises();
    }

    String covariantDogNoises(final AnimalList<? extends Dog> dogs) {
        return dogs.noises();
    }

    String covariantPuppyNoises(final AnimalList<? extends Puppy> puppies) {
        return puppies.noises();
    }

    String covariantCatNoises(final AnimalList<? extends Cat> cats) {
        return cats.noises();
    }

    String contravariantAnimalNoises(final AnimalList<? super Animal> animals) {
        return animals.noises();
    }

    String contravariantDogNoises(final AnimalList<? super Dog> dogs) {
        return dogs.noises();
    }

    String contravariantPuppyNoises(final AnimalList<? super Puppy> puppies) {
        return puppies.noises();
    }

    String contravariantCatNoises(final AnimalList<? super Cat> cats) {
        return cats.noises();
    }

    @Koan
    void what_are_the_variances_of_animal() {
        AnimalList<Animal> animals = new AnimalList<>(new Dog(), new Puppy(), new Cat());

        // Try to uncomment each line sequentially. Does it compile? Why?
        String noises = (String) __;
        // String noises = invariantAnimalNoises(animals);
        // String noises = covariantAnimalNoises(animals);
        // String noises = covariantDogNoises(animals);
        // String noises = covariantPuppyNoises(animals);
        // String noises = covariantCatNoises(animals);
        // String noises = contravariantAnimalNoises(animals);
        // String noises = contravariantDogNoises(animals);
        // String noises = contravariantPuppyNoises(animals);
        // String noises = contravariantCatNoises(animals);

        assertThat(noises).isEqualTo("Woof!, Squeak!, Meow!");
    }

    @Koan
    void what_are_the_variances_of_dog() {
        AnimalList<Dog> dogs = new AnimalList<>(new Dog(), new Puppy());

        // Try to uncomment each line sequentially. Does it compile? Why?
        String noises = (String) __;
        // String noises = invariantAnimalNoises(dogs);
        // String noises = covariantAnimalNoises(dogs);
        // String noises = covariantDogNoises(dogs);
        // String noises = covariantPuppyNoises(dogs);
        // String noises = covariantCatNoises(dogs);
        // String noises = contravariantAnimalNoises(dogs);
        // String noises = contravariantDogNoises(dogs);
        // String noises = contravariantPuppyNoises(dogs);
        // String noises = contravariantCatNoises(dogs);

        assertThat(noises).isEqualTo("Woof!, Squeak!");
    }

    @Koan
    void what_are_the_variances_of_puppy() {
        AnimalList<Puppy> puppies = new AnimalList<>(new Puppy());

        // Try to uncomment each line sequentially. Does it compile? Why?
        String noises = (String) __;
        // String noises = invariantAnimalNoises(puppies);
        // String noises = covariantAnimalNoises(puppies);
        // String noises = covariantDogNoises(puppies);
        // String noises = covariantPuppyNoises(puppies);
        // String noises = covariantCatNoises(puppies);
        // String noises = contravariantAnimalNoises(puppies);
        // String noises = contravariantDogNoises(puppies);
        // String noises = contravariantPuppyNoises(puppies);
        // String noises = contravariantCatNoises(puppies);

        assertThat(noises).isEqualTo("Squeak!");
    }

    @Koan
    void what_are_the_variances_of_cat() {
        AnimalList<Cat> cats = new AnimalList<>(new Cat());

        // Try to uncomment each line sequentially. Does it compile? Why?
        String noises = (String) __;
        // String noises = invariantAnimalNoises(cats);
        // String noises = covariantAnimalNoises(cats);
        // String noises = covariantDogNoises(cats);
        // String noises = covariantPuppyNoises(cats);
        // String noises = covariantCatNoises(cats);
        // String noises = contravariantAnimalNoises(cats);
        // String noises = contravariantDogNoises(cats);
        // String noises = contravariantPuppyNoises(cats);
        // String noises = contravariantCatNoises(cats);

        assertThat(noises).isEqualTo("Meow!");
    }
}
