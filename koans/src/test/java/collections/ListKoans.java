package collections;

import io.fries.koans.Koan;
import io.fries.koans.collections.Item;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.List;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

class ListKoans {

    @Nested
    class ArrayListKoans {

        @Koan
        void array_list_is_the_default_list_implementation_using_an_array() {
            List<Item> items = new ArrayList<>();

            items.add(new Item("Chair", 20));
            items.add(new Item("Table", 50));
            items.add(new Item("Desk", 150));

            assertThat(items.size()).isEqualTo(__);
            assertThat(items.get(0).getName()).isEqualTo(__);
            assertThat(items.get(1).getName()).isEqualTo(__);
            assertThat(items.get(2).getName()).isEqualTo(__);
        }

        @Koan
        void array_list_can_be_empty() {
            List<Item> items = new ArrayList<>();

            assertThat(items.isEmpty()).isEqualTo(__);
        }

        @Koan
        void array_list_can_be_initialized_using_another_collection_or_add_all_its_elements_at_once() {
            List<Item> original = new ArrayList<>();
            original.add(new Item("Table", 70));
            original.add(new Item("Desk", 129));
            original.add(new Item("Chair", 30));

            List<Item> items = new ArrayList<>(original);
            items.addAll(original);

            assertThat(items.size()).isEqualTo(__);
            assertThat(items.get(1).getName()).isEqualTo(__);
            assertThat(items.get(3).getName()).isEqualTo(__);
            assertThat(items.get(5).getName()).isEqualTo(__);
        }

        @Koan
        void use_contains_to_check_if_a_value_is_present_() {
            List<Item> items = new ArrayList<>();

            items.add(new Item("Chair", 20));
            items.add(new Item("Table", 50));
            items.add(new Item("Desk", 150));

            assertThat(items.size()).isEqualTo(__);
            assertThat(items.get(0).getName()).isEqualTo(__);
            assertThat(items.get(1).getName()).isEqualTo(__);
            assertThat(items.get(2).getName()).isEqualTo(__);
        }

        // contains
        // set
        // replaceAll
        // remove
        // removeAll
        // retainAll
        // indexOf
        // lastIndexOf
        // subList
        // equals
        // toArray
        // iterator
    }

    @Nested
    class LinkedListKoans {
    }

    @Nested
    class StackKoans {
        // push
        // pop
        // peek
        // empty
        // search
    }
}
