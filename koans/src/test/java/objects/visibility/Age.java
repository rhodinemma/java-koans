package objects.visibility;

import java.util.Objects;

// This class is `package-private` (no visibility keyword is used before `class`), thus it can only be accessed by
// classes located inside the `objects.visibility` package.
class Age {

    // This attribute is `private`, thus it can only be accessed inside the `Age` class.
    // It is also `final` so that its reference (or "value") cannot be updated.
    private final int age;

    // This constructor is `private`: it can only be called from inside this class.
    private Age(final int age) {
        this.age = age;
    }

    // This `static` method is `package-private`, it can only be called from the other classes of the
    // `objects.visibility` package.
    // Using a "static factory" method to build your object instance allows you to locate all your validation code
    // (or "invariants") in one place and keep your constructor purely technical.
    static Age of(final int age) {
        if (age < 0) {
            throw new IllegalArgumentException("age cannot be negative");
        }

        return new Age(age);
    }

    // This method is also `package-private`. Its purpose is to hide the object state (the `age` attribute) from the
    // outside world, and locate the business logic associated to your object... inside your object.
    // This is called encapsulation.
    boolean isGreaterThanOrEqualTo(final int threshold) {
        return age >= threshold;
    }

    // Why would you need a `getAge` method? It will only encourage you to create implicit behaviors based on your
    // object state... outside of your object.
    // public int getAge() {
    //     return age;
    // }

    // A `setAge` method, and "setters" and mutability in general, is in most cases a questionable design choice.
    // Why would you need to arbitrarily change the state of an object from the outside?
    // With `final` attributes and invariants enforced before instantiation, you are assured to always manipulate
    // a consistent object instance.
    // public void setAge(final int age) {
    //     this.age = age;
    // }

    // Overriding the `toString` method can help for logging, debugging, testing...
    @Override
    public String toString() {
        return "Age{" +
                "age=" + age +
                '}';
    }

    // You should always override the `equals` method in order to compare the state of two objects
    // and not their references.
    // This is part of the Object Contract.
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Age age1 = (Age) o;
        return age == age1.age;
    }

    // You should always override the `hashCode` method in order to generate its checksum based on its state.
    // This is part of the Object Contract.
    @Override
    public int hashCode() {
        return Objects.hash(age);
    }
}
