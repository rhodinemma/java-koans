package objects.visibility;

import java.util.Objects;

// This class is `public`, thus it can be accessed from anywhere in the classpath.
public class User {

    // This is a constant definition: the `static` keyword ties it to the class, not its implementations.
    // A `private static final` variable is not accessible outside of the class (private), initialized when
    // the class metadata is loaded in memory and not tied to the classes instances (static, thus it takes
    // only one "memory space"), and its reference cannot be updated (final).
    private static final int MAJORITY_THRESHOLD = 21;

    // This attribute is private, thus it can only be accessed inside the `User` class.
    // It is also `final` so that its reference (or "value") cannot be updated.
    private final Age age;

    // This constructor is `private`: it can only be called from inside this class.
    private User(final Age age) {
        this.age = age;
    }

    // This `static` method is `public`, so it can be called from anywhere in the classpath.
    // It delegates the creation and validation of the `Age` object, that cannot be seen from outside this package.
    // This enables you to explode your business logic in multiple unit classes and expose only a tiny bit
    // of usable interface to the client code.
    public static User of(final int age) {
        return new User(Age.of(age));
    }

    // This method is `public`, thus can be called from anywhere on a `User` instance.
    // It hides all the underlying state manipulation and returns only the desired result to the client code.
    // This is called encapsulation.
    public boolean isAdult() {
        return age.isGreaterThanOrEqualTo(MAJORITY_THRESHOLD);
    }

    // Overriding the `toString` method can help for logging, debugging, testing...
    // Here, the concatenation of the `age` attribute
    @Override
    public String toString() {
        return "User{" +
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
        final User user = (User) o;
        return Objects.equals(age, user.age);
    }

    // You should always override the `hashCode` method in order to generate its checksum based on its state.
    // This is part of the Object Contract.
    @Override
    public int hashCode() {
        return Objects.hash(age);
    }
}
