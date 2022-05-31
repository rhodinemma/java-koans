package basics;

import io.fries.koans.Koan;

import java.text.MessageFormat;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SuppressWarnings("all")
class StringsKoans {

    @Koan
    void strings_are_implicit_objects() {
        assertThat("just a plain ole string".getClass()).isEqualTo(String.class);
    }

    @Koan
    void new_string_object() {
        String string = new String();
        String empty = "";

        assertThat(string.equals(empty)).isEqualTo(true);
    }

    @Koan
    void new_string_is_redundant_and_can_be_omitted() {
        String stringInstance = "zero";
        String stringReference = new String(stringInstance);

        assertThat(stringInstance.equals(stringReference)).isEqualTo(true);
    }

    @Koan
    void but_new_string_is_not_identical() {
        String stringInstance = "zero";
        String stringReference = new String(stringInstance);

        assertThat(stringInstance == stringReference).isEqualTo(false);
    }

    @Koan
    void string_is_empty() {
        assertThat("".isEmpty()).isEqualTo(true);
        assertThat("one".isEmpty()).isEqualTo(false);
        assertThat(new String().isEmpty()).isEqualTo(true);
        assertThat(new String("").isEmpty()).isEqualTo(true);
        assertThat(new String("one").isEmpty()).isEqualTo(false);
    }

    @Koan
    void string_length() {
        assertThat("".length()).isEqualTo(0);
        assertThat("one".length()).isEqualTo(3);
        assertThat("the number is one".length()).isEqualTo(17);
    }

    @Koan
    void string_trim() {
        assertThat("".trim()).isEqualTo("");
        assertThat("one".trim()).isEqualTo("one");
        assertThat(" one more time".trim()).isEqualTo("one more time");
        assertThat(" one more time         ".trim()).isEqualTo("one more time");
        assertThat(" and again\t".trim()).isEqualTo("and again");
        assertThat("\t\t\twhat about now?\t".trim()).isEqualTo("what about now?");
    }

    @Koan
    void string_concatenation() {
        String one = "one";
        String space = " ";
        String two = "two";

        assertThat(one + space + two).isEqualTo("one two");
        assertThat(space + one + two).isEqualTo(" onetwo");
        assertThat(two + space + one).isEqualTo("two one");
    }

    @Koan
    void string_upper_case() {
        String str = "I am a number one!";

        assertThat(str.toUpperCase()).isEqualTo("I AM A NUMBER ONE!");
    }

    @Koan
    void string_lower_case() {
        String str = "I AM a number ONE!";

        assertThat(str.toLowerCase()).isEqualTo("i am a number one!");
    }

    @Koan
    void string_compare() {
        String str = "I AM a number ONE!";

        assertThat(str.compareTo("I AM a number ONE!") == 0).isEqualTo(true);
        assertThat(str.compareTo("I am a number one!") == 0).isEqualTo(false);
        assertThat(str.compareTo("I AM A NUMBER ONE!") == 0).isEqualTo(false);
    }

    @Koan
    void string_compare_ignore_case() {
        String str = "I AM a number ONE!";

        assertThat(str.compareToIgnoreCase("I AM a number ONE!") == 0).isEqualTo(true);
        assertThat(str.compareToIgnoreCase("I am a number one!") == 0).isEqualTo(true);
        assertThat(str.compareToIgnoreCase("I AM A NUMBER ONE!") == 0).isEqualTo(true);
    }

    @Koan
    void string_starts_with() {
        assertThat("".startsWith("one")).isEqualTo(false);
        assertThat("one".startsWith("one")).isEqualTo(true);
        assertThat("one is the number".startsWith("one")).isEqualTo(true);
        assertThat("ONE is the number".startsWith("one")).isEqualTo(false);
    }

    @Koan
    void string_ends_with() {
        assertThat("".endsWith("one")).isEqualTo(false);
        assertThat("one".endsWith("one")).isEqualTo(true);
        assertThat("the number is one".endsWith("one")).isEqualTo(true);
        assertThat("the number is two".endsWith("one")).isEqualTo(false);
        assertThat("the number is One".endsWith("one")).isEqualTo(false);
    }

    @Koan
    void string_substring() {
        String str = "I AM a number ONE!";

        assertThat(str.substring(0)).isEqualTo("I AM a number ONE!");
        assertThat(str.substring(1)).isEqualTo(" AM a number ONE!");
        assertThat(str.substring(5)).isEqualTo("a number ONE!");
        assertThat(str.substring(14, 17)).isEqualTo("ONE");
        assertThat(str.substring(7, str.length())).isEqualTo("number ONE!");
    }

    @Koan
    void string_contains() {
        String str = "I AM a number ONE!";

        assertThat(str.contains("one")).isEqualTo(false);
        assertThat(str.contains("ONE")).isEqualTo(true);
    }

    @Koan
    void string_replace() {
        String str = "I am a number ONE!";

        assertThat(str.replace("ONE", "TWO")).isEqualTo("I am a number TWO!");
        assertThat(str.replace("I am", "She is")).isEqualTo("She is a number ONE!");
    }

    @Koan
    void string_builder_can_act_as_a_mutable_string() {
        final StringBuilder stringBuilder = new StringBuilder("one")
                .append(" ")
                .append("two");

        assertThat(stringBuilder.toString()).isEqualTo("one two");
    }

    @Koan
    void string_buffer_is_a_thread_safe_alternative_to_string_builder() {
        final StringBuffer stringBuffer = new StringBuffer("one")
                .append(" ")
                .append("two");

        assertThat(stringBuffer.toString()).isEqualTo("one two");
    }

    @Koan
    void readable_string_formatting_with_string_format() {
        assertThat(String.format("%s %s %s", "a", "b", "a")).isEqualTo("a b a");
    }

    @Koan
    void extra_arguments_to_string_format_get_ignored() {
        assertThat(String.format("%s %s %s", "a", "b", "c", "d")).isEqualTo("a b c");
    }

    @Koan
    void insufficient_arguments_to_string_format_causes_an_error() {
        try {
            String.format("%s %s %s", "a", "b");
            fail("This failure event is never reached!");
        } catch (Exception e) {
            assertThat(e.getClass()).isEqualTo(java.util.MissingFormatArgumentException.class);
            assertThat(e.getMessage()).isEqualTo("Format specifier '%s'");
        }
    }

    @Koan
    void readable_string_formatting_with_message_format() {
        assertThat(MessageFormat.format("{0} {1} {0}", "a", "b")).isEqualTo("a b a");
    }

    @Koan
    void extra_arguments_to_message_format_get_ignored() {
        assertThat(MessageFormat.format("{0} {1} {0}", "a", "b", "c")).isEqualTo("a b a");
    }

    @Koan
    void insufficient_arguments_to_message_format_does_not_replace_the_token() {
        assertThat(MessageFormat.format("{0} {1} {0}", "a")).isEqualTo("a {1} a");
    }
}
