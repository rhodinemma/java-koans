package objects;

import io.fries.koans.Koan;
import objects.visibility.User;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class ObjectsKoans {

    @Koan
    void new_object_instances_can_be_created_directly() {
        assertThat(new Object() instanceof Object).isEqualTo(true);
    }

    @Koan
    void all_classes_inherit_from_object() {
        class Animal {
        }

        assertThat(new Animal() instanceof Animal).isEqualTo(true);
        assertThat(new Animal() instanceof Object).isEqualTo(true);
    }

    @Koan
    void all_classes_have_a_default_to_string_method() {
        Object object = new Object();

        // Default toString() method uses the class full name as prefix and its memory address as suffix.
        assertThat(object.toString().startsWith("java.lang.Object@")).isEqualTo(true);
    }

    @Koan
    void to_string_can_be_overridden() {
        Object object = new Object() {
            @Override
            public String toString() {
                return "Custom toString";
            }
        };

        assertThat(object.toString()).isEqualTo("Custom toString");
    }

    @Koan
    void to_string_is_implicitly_called_for_string_concatenation() {
        final String string = "ha";

        Object object = new Object() {
            @Override
            public String toString() {
                return string;
            }
        };

        assertThat(string + object).isEqualTo("haha");
    }

    @Koan
    void to_string_can_be_used_with_null_references_when_invoked_implicitly() {
        String string = "string";

        assertThat(string + null).isEqualTo("stringnull");
    }

    @Koan
    void all_classes_have_a_default_hash_code_method() {
        Object object = new Object();

        assertThat(object.hashCode() > 0).isEqualTo(true);
    }

    @Koan
    void hash_code_can_be_overridden() {
        int fixedHashCode = 42;

        Object object = new Object() {
            @Override
            public int hashCode() {
                return fixedHashCode;
            }
        };

        assertThat(object.hashCode()).isEqualTo(42);
    }

    @Koan
    void system_hash_code_will_always_return_the_default_hash_code_value() {
        int fixedHashCode = 42;

        Object object = new Object() {
            @Override
            public int hashCode() {
                return fixedHashCode;
            }
        };

        assertThat(object.hashCode()).isEqualTo(42);
        assertThat(object.hashCode() == System.identityHashCode(object)).isEqualTo(false);
    }

    @Koan
    void system_hash_code_of_a_null_reference_is_always_zero() {
        Object object = null;

        assertThat(System.identityHashCode(object)).isEqualTo(0);
    }

    @Koan
    void an_object_state_is_composed_of_its_attributes() {
        class User {
            int age = 18;
        }

        User user = new User();

        assertThat(user.age).isEqualTo(18);
    }

    @Koan
    void an_object_state_should_be_initialized_through_its_constructor() {
        class User {
            int age;

            User(int age) {
                this.age = age;
            }
        }

        User user = new User(20);

        assertThat(user.age).isEqualTo(20);
    }

    @Koan
    void an_object_behavior_is_defined_by_its_methods_and_should_hide_the_object_state_to_the_outside_world() {
        class User {
            private int age;

            User(int age) {
                this.age = age;
            }

            boolean isAdult() {
                return age >= 18;
            }
        }

        User child = new User(7);
        User adult = new User(33);

        assertThat(child.isAdult()).isEqualTo(false);
        assertThat(adult.isAdult()).isEqualTo(true);
    }

    @Koan
    void an_object_should_always_implement_custom_behaviors_for_to_string_equals_and_hash_code() {
        class User {
            private int age;

            User(int age) {
                this.age = age;
            }

            @Override
            public String toString() {
                return "User{" +
                        "age=" + age +
                        '}';
            }

            @Override
            public boolean equals(final Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                final User user = (User) o;
                return age == user.age;
            }

            @Override
            public int hashCode() {
                return age;
            }
        }

        User child = new User(7);
        User adult = new User(33);

        assertThat(child.toString()).isEqualTo("User{age=7}");
        assertThat(adult.toString()).isEqualTo("User{age=33}");

        assertThat(child.equals(adult)).isEqualTo(false);
        assertThat(child.equals(child)).isEqualTo(true);

        assertThat(child.hashCode()).isEqualTo(7);
        assertThat(adult.hashCode()).isEqualTo(33);
    }

    @Koan
    void visibility_and_encapsulation_are_important_parts_of_object_oriented_programming() {
        // This `User` class is located under the `objects.visibility` package.
        // Read the content of this package and try to manipulate the classes in this method.
        // Some of the concepts demonstrated here are subject to their own koans.
        final User user = User.of(18);

        assertThat(user.isAdult()).isEqualTo(false);
    }
}
