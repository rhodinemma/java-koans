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

            assertThat(items.size()).isEqualTo(3);
            assertThat(items.get(0).getName()).isEqualTo("Chair", 20);
            assertThat(items.get(1).getName()).isEqualTo("Table", 50);
            assertThat(items.get(2).getName()).isEqualTo("Desk", 150);
        }

        @Koan
        void array_list_can_be_empty() {
            List<Item> items = new ArrayList<>();

            assertThat(items.isEmpty()).isEqualTo(true);
        }

        @Koan
        void array_list_can_be_initialized_using_another_collection_or_add_all_its_elements_at_once() {
            List<Item> original = new ArrayList<>();
            original.add(new Item("Table", 70));
            original.add(new Item("Desk", 129));
            original.add(new Item("Chair", 30));

            List<Item> items = new ArrayList<>(original);
            items.addAll(original);

            assertThat(items.size()).isEqualTo(6);
            assertThat(items.get(1).getName()).isEqualTo("Desk", 129);
            assertThat(items.get(3).getName()).isEqualTo("Table", 70);
            assertThat(items.get(5).getName()).isEqualTo("Chair", 30);
        }

        @Koan
        void use_contains_to_check_if_a_value_is_present_() {
            List<Item> items = new ArrayList<>();

            items.add(new Item("Chair", 20));
            items.add(new Item("Table", 50));
            items.add(new Item("Desk", 150));

            assertThat(items.size()).isEqualTo(3);
            assertThat(items.get(0).getName()).isEqualTo("Chair");
            assertThat(items.get(1).getName()).isEqualTo("Table");
            assertThat(items.get(2).getName()).isEqualTo("Desk");
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
