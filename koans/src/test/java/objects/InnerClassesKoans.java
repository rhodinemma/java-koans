package objects;

import io.fries.koans.Koan;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

@SuppressWarnings("all")
class InnerClassesKoans {

    private int x = 10;

    class Inner {

        String doStuff() {
            return "stuff";
        }

        int returnOuter() {
            return x;
        }
    }

    @Koan
    void creating_inner_class_instance() {
        Inner someObject = new Inner();
        assertThat(someObject.doStuff()).isEqualTo("stuff");
    }

    @Koan
    void creating_inner_class_instance_with_other_syntax() {
        InnerClassesKoans.Inner someObject = this.new Inner();
        assertThat(someObject.doStuff()).isEqualTo("stuff");
    }

    @Koan
    void inner_classes_can_access_outer_class_members() {
        Inner someObject = new Inner();
        assertThat(someObject.returnOuter()).isEqualTo(10);
    }

    @Koan
    void inner_classes_can_be_scoped_to_methods() {
        class InnerClassInMethod {
            private int oneHundred() {
                return 100;
            }
        } // No semicolon required here.

        assertThat(new InnerClassInMethod().oneHundred()).isEqualTo(100);
    }

    class AnotherInnerClass {

        int thousand() {
            return 1000;
        }

        AnotherInnerClass specialReturn() {
            class SpecialInnerClass extends AnotherInnerClass {
                int thousand() {
                    return 2000;
                }
            }

            return new SpecialInnerClass();
        }
    }

    @Koan
    void method_scoped_inner_classes_can_leak_instances_thanks_to_inheritance() {
        AnotherInnerClass innerClass = new AnotherInnerClass();
        assertThat(innerClass.thousand()).isEqualTo(1000);

        AnotherInnerClass specialInnerClass = innerClass.specialReturn();
        assertThat(specialInnerClass.thousand()).isEqualTo(2000);
    }

    int theAnswer() {
        return 42;
    }

    @Koan
    void creating_anonymous_inner_classes() {
        InnerClassesKoans anonymous = new InnerClassesKoans() {
            @Override
            int theAnswer() {
                return 23;
            }
        }; // Semicolon is required here because this is an affectation, not a declaration.

        assertThat(anonymous.theAnswer()).isEqualTo(23);
    }

    interface Ignorable {
        String ignoreAll();
    }

    @Koan
    void creating_anonymous_inner_classes_to_implement_interface() {
        Ignorable ignorable = new Ignorable() {
            public String ignoreAll() {
                return (String) __;
            }
        };

        assertThat(ignorable.ignoreAll()).isEqualTo(null);
    }

    @Koan
    void inner_class_and_inheritance() {
        Inner someObject = new Inner();
        assertThat(someObject instanceof Inner).isEqualTo(true);
        // assertThat(someObject instanceof InnerClassesKoans).isEqualTo(__); // Is it possible? Why? What does that imply for inheritance?
    }


    class OtherInner extends InnerClassesKoans {
    }

    @Koan
    void inner_class_extending_outer_class_and_inheritance() {
        OtherInner someObject = new OtherInner();
        assertThat(someObject instanceof InnerClassesKoans).isEqualTo(true);
    }
}
