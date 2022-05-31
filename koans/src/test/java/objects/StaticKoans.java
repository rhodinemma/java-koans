package objects;

import io.fries.koans.Koan;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;
import static objects.StaticKoans.StaticInner.y;

@SuppressWarnings("all")
class StaticKoans {

    static int x = 3;

    @Koan
    void a_static_variable_is_tied_to_a_class_and_not_its_instances() {
        assertThat(StaticKoans.x).isEqualTo(__);

        StaticKoans.x = 5;
        assertThat(new StaticKoans().x).isEqualTo(__);

        new StaticKoans().x = 7;
        assertThat(StaticKoans.x).isEqualTo(__);
    }

    static class StaticInner {
        static int y = 1;
    }

    @Koan
    void an_inner_class_can_be_declared_static() {
        assertThat(StaticKoans.StaticInner.y).isEqualTo(__);
    }

    @Koan
    void a_static_import_can_be_used_to_shorten_the_access_to_the_static_member_of_another_class() {
        // Check the 'import static' part of this file!
        assertThat(y).isEqualTo(__);
    }

    @Koan
    void a_static_member_can_be_referenced_directly() {
        assertThat(x).isEqualTo(__);
    }

    @Koan
    void a_method_cannot_declare_static_variables() {
        int z = 1; // Try to declare this variable 'static'. Does it compile? Why?
        assertThat(z).isEqualTo(__);
    }

    static class WithStaticBlock {
        static int x;

        static {
            x = 3;
        }
    }

    @Koan
    void the_static_block_is_called_once_when_the_class_is_intialized() {
        assertThat(WithStaticBlock.x).isEqualTo(__);
    }

    static class WithStaticValueAndStaticBlock {
        static int x = 3;

        static {
            x += 5;
        }
    }

    @Koan
    void the_static_block_is_called_after_static_attributes_are_initialized() {
        assertThat(WithStaticValueAndStaticBlock.x).isEqualTo(__);
    }

    static class WithNonStaticBlock {
        static int x = 3;

        {
            x += 5;
        }
    }

    @Koan
    void the_non_static_block_is_called_each_time_an_instance_of_the_class_is_initialized() {
        assertThat(new WithNonStaticBlock().x).isEqualTo(__);
        assertThat(new WithNonStaticBlock().x).isEqualTo(__);
        assertThat(new WithNonStaticBlock().x).isEqualTo(__);
    }
}
