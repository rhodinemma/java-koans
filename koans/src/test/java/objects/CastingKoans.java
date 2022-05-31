package objects;

import io.fries.koans.Koan;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SuppressWarnings("all")
class CastingKoans {

    @Koan
    void long_plus_int() {
        int a = 6;
        long b = 10;
        Object c = a + b;

        assertThat(c).isEqualTo(__);
        assertThat(c instanceof Integer).isEqualTo(__);
        assertThat(c instanceof Long).isEqualTo(__);
    }

    @Koan
    void explicit_typecast() {
        long a = 2147483648L;
        int b = (int) a;

        assertThat(b).isEqualTo(__);
    }

    @Koan
    void implicit_typecast() {
        int a = 1;
        int b = Integer.MAX_VALUE;
        long c = a + b;

        assertThat(c).isEqualTo(__);
    }

    interface Sleepable {
        String sleep();
    }

    class Grandparent implements Sleepable {
        public String sleep() {
            return "zzzz";
        }
    }

    class Parent extends Grandparent {
        public String complain() {
            return "TPS reports don't even have a cover letter!";
        }
    }

    class Child extends Parent {
        public String complain() {
            return "Are we there yet!";
        }
    }

    @Koan
    void upcast_with_inheritance() {
        Child child = new Child();
        Parent parent = child;

        assertThat(child instanceof Child).isEqualTo(__);
        assertThat(parent instanceof Child).isEqualTo(__);
        assertThat(parent instanceof Parent).isEqualTo(__);
        assertThat(parent instanceof Grandparent).isEqualTo(__);
    }

    @Koan
    void upcast_and_polymorphism() {
        Child child = new Child();
        Parent parent = child;

        assertThat(parent.complain()).isEqualTo(__);
    }

    @Koan
    void downcast_with_inheritance() {
        Grandparent child = new Child();
        Parent parentReference = (Parent) child;
        Child childReference = (Child) parentReference;

        assertThat(childReference instanceof Child).isEqualTo(__);
        assertThat(childReference instanceof Parent).isEqualTo(__);
        assertThat(childReference instanceof Grandparent).isEqualTo(__);
    }

    @Koan
    void downcast_and_polymorphism() {
        Grandparent child = new Child();
        Parent parent = (Child) child;

        assertThat(parent.complain()).isEqualTo(__);
    }

    @Koan
    void class_casting() {
        try {
            Object o = new Object(); // __
            ((Sleepable) o).sleep();
        } catch (ClassCastException e) {
            fail("Object does not implement Sleepable, maybe one of the other classes do?");
        }
    }

    @Koan
    void complicated_cast() {
        Grandparent parent = new Parent();

        assertThat("TPS reports don't even have a cover letter!").isEqualTo(__);
    }
}
