package cli;

import io.fries.koans.Koan;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.regex.MatchResult;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;
import static io.fries.koans.cli.Scanners.fakeKeyboardInput;

class ScannerKoans {

    @Koan
    void scanner_is_initialized_with_an_input_stream_and_should_always_be_closed() {
        fakeKeyboardInput("input");
        // Use the System.in stream to read from the user keyboard in a console app.
        Scanner scanner = new Scanner(System.in);

        // __
        scanner.next("input");

        // You should always close your scanner in order to close and release the underlying stream.
        scanner.close();

    }

    @Koan
    void use_the_next_method_to_read_a_string() {
        // The fakeKeyboardInput(String) method will mock the keyboard input behavior of stdin.
        fakeKeyboardInput("input");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.next()).isEqualTo("input");

        scanner.close();
    }

    @Koan
    void next_only_reads_words_one_a_a_time() {
        fakeKeyboardInput("multiple words in stdin");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.next()).isEqualTo("multiple");
        assertThat(scanner.next()).isEqualTo("words");
        assertThat(scanner.next()).isEqualTo("in");
        assertThat(scanner.next()).isEqualTo("stdin");

        scanner.close();
    }

    @Koan
    void the_use_delimiter_method_changes_the_word_delimiter() {
        fakeKeyboardInput("multiple,words,in,stdin");
        Scanner scanner = new Scanner(System.in);

        scanner.useDelimiter(",");

        assertThat(scanner.next()).isEqualTo("multiple");
        assertThat(scanner.next()).isEqualTo("words");
        assertThat(scanner.next()).isEqualTo("in");
        assertThat(scanner.next()).isEqualTo("stdin");

        scanner.close();
    }

    @Koan
    void next_throws_a_no_such_element_exception_when_the_input_stream_is_empty() {
        fakeKeyboardInput("food");
        Scanner scanner = new Scanner(System.in);

        // __
        scanner.next();
        // __

        scanner.close();
    }

    @Koan
    void has_next_lets_you_know_if_there_is_more_data_to_scan() {
        fakeKeyboardInput("multiple");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.hasNext()).isEqualTo(true);

        scanner.close();
    }

    @Koan
    void use_next_line_to_read_until_next_carriage_return() {
        fakeKeyboardInput("multiple words in stdin\n");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.hasNextLine()).isEqualTo(true);
        assertThat(scanner.nextLine()).isEqualTo("multiple words in stdin");

        scanner.close();
    }

    @Koan
    void use_next_int_to_read_integers() {
        fakeKeyboardInput("3");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.hasNextInt()).isEqualTo(true);
        assertThat(scanner.nextInt()).isEqualTo(3);
        // Next methods exist for standard types (boolean, double, ...)

        scanner.close();
    }

    @Koan
    void next_can_take_a_regex_pattern() {
        fakeKeyboardInput("cat cats catz");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.next("cat[sz]?")).isEqualTo("cat");
        assertThat(scanner.next("cat[sz]?")).isEqualTo("cats");
        assertThat(scanner.next("cat[sz]?")).isEqualTo("catz");

        scanner.close();
    }

    @Koan
    void next_throws_an_input_mismatch_exception_when_the_pattern_is_not_respected() {
        fakeKeyboardInput("cat catch");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.next("cat[sz]?")).isEqualTo("cat");
        // __
        // assertThat(scanner.next("cat[sz]?")).isEqualTo("catch");
        // __

        scanner.close();
    }

    @Koan
    void use_has_next_with_a_pattern_to_ensure_that_the_next_element_matches_your_expectations() {
        fakeKeyboardInput("cat catch");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.next("cat[sz]?")).isEqualTo("cat");
        assertThat(scanner.hasNext("cat[sz]?")).isEqualTo(false);

        scanner.close();
    }

    @Koan
    void use_find_in_line_int_order_to_find_an_element_matching_your_pattern() {
        fakeKeyboardInput("One cat, two cats, three catz.");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.findInLine("cat[sz]?")).isEqualTo("cat");
        assertThat(scanner.findInLine("cat[sz]?")).isEqualTo("cats");
        assertThat(scanner.findInLine("cat[sz]?")).isEqualTo("catz");

        scanner.close();
    }

    @Koan
    void find_in_line_returns_null_when_no_element_matches_the_pattern() {
        fakeKeyboardInput("One cat, two cats, three catz.");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.findInLine("dog[sz]?")).isEqualTo(null);

        scanner.close();
    }

    @Koan
    void find_all_returns_all_occurrences_matching_the_pattern() {
        fakeKeyboardInput("One cat, two cats, three catz.");
        Scanner scanner = new Scanner(System.in);

        String[] cats = scanner.findAll("cat[sz]?").map(MatchResult::group).toArray(String[]::new);

        assertThat(cats[0]).isEqualTo("cat");
        assertThat(cats[1]).isEqualTo("cats");
        assertThat(cats[2]).isEqualTo("catz");

        scanner.close();
    }

    @Koan
    void a_scanner_can_consume_any_type_of_input_stream() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("This is a stream".getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);

        assertThat(scanner.nextLine()).isEqualTo("This is a stream");

        scanner.close();
    }
}
