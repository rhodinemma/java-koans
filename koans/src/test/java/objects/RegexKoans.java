package objects;

import io.fries.koans.Koan;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;

class RegexKoans {

    @Koan
    void simple_pattern_matching() {
        assertThat(Pattern.matches("cat", "cat")).isEqualTo(__);
    }

    @Koan
    void a_compiled_pattern_can_be_reused() {
        Pattern pattern = Pattern.compile("cat");

        Matcher validMatcher = pattern.matcher("cat");
        assertThat(validMatcher.matches()).isEqualTo(__);

        Matcher invalidMatcher = pattern.matcher("dog");
        assertThat(invalidMatcher.matches()).isEqualTo(__);
    }

    @Koan
    void multiple_occurrences_of_a_matching_pattern_can_be_found() {
        Pattern pattern = Pattern.compile("cat");
        Matcher matcher = pattern.matcher("catdogcat");

        assertThat(matcher.find()).isEqualTo(__);
        assertThat(matcher.find()).isEqualTo(__);
        assertThat(matcher.find()).isEqualTo(__);
    }

    @Koan
    void the_starting_index_of_each_occurrences_can_be_retrieved_using_start() {
        Pattern pattern = Pattern.compile("cat");
        Matcher matcher = pattern.matcher("catdogcat");

        assertThat(matcher.find()).isEqualTo(__);
        assertThat(matcher.start()).isEqualTo(__);
        assertThat(matcher.find()).isEqualTo(__);
        assertThat(matcher.start()).isEqualTo(__);
        assertThat(matcher.find()).isEqualTo(__);
    }

    @Koan
    void all_the_matched_results_can_be_collected() {
        Pattern pattern = Pattern.compile("[Cc]at[sz]?");
        Matcher matcher = pattern.matcher("One cat, two cats, three catz.");

        String[] results = matcher.results().map(MatchResult::group).toArray(String[]::new);

        assertThat(results.length).isEqualTo(__);
        assertThat(results[0]).isEqualTo(__);
        assertThat(results[1]).isEqualTo(__);
        assertThat(results[2]).isEqualTo(__);
    }

    @Koan
    void a_string_builder_or_string_buffer_can_hold_the_replacement_of_matched_values() {
        Pattern pattern = Pattern.compile("[Cc]at([sz])?");
        Matcher matcher = pattern.matcher("One cat, two cats, three catz.");

        StringBuilder replacedValue = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(replacedValue, "dog$1");
        }
        matcher.appendTail(replacedValue);

        assertThat(replacedValue.toString()).isEqualTo(__);
    }

    @Koan
    void a_regex_group_can_be_named() {
        Pattern pattern = Pattern.compile("[Cc]at(?<plural>[sz])?");
        Matcher matcher = pattern.matcher("One cat, two cats, three catz.");

        StringBuilder replacedValue = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(replacedValue, "dog${plural}");
        }
        matcher.appendTail(replacedValue);

        assertThat(replacedValue.toString()).isEqualTo(__);
    }

    @Koan
    void do_not_forget_to_append_the_tail_of_the_matched_string() {
        Pattern pattern = Pattern.compile("[Cc]at(?<plural>[sz])?");
        Matcher matcher = pattern.matcher("One cat, two cats, three catz.");

        StringBuilder replacedValue = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(replacedValue, "dog${plural}");
        }

        assertThat(replacedValue.toString()).isEqualTo(__);
    }

    @Koan
    void you_can_easily_replace_all_the_matched_expressions_in_a_string() {
        Pattern pattern = Pattern.compile("[Cc]at([sz])?");
        Matcher matcher = pattern.matcher("One cat, two cats, three catz.");

        String replacedValue = matcher.replaceAll("dog$1");

        assertThat(replacedValue).isEqualTo(__);
    }

    @Koan
    void use_the_match_result_to_apply_transformations_to_the_matched_value() {
        Pattern pattern = Pattern.compile("[Cc]at([sz])?");
        Matcher matcher = pattern.matcher("One cat, two cats, three catz.");

        String replacedValue = matcher.replaceAll(matchResult -> matchResult.group().toUpperCase());

        assertThat(replacedValue).isEqualTo(__);
    }

    @Koan
    void in_case_you_may_want_to_replace_the_first_occurrence_in_place() {
        Pattern pattern = Pattern.compile("[Cc]at([sz])?");
        Matcher matcher = pattern.matcher("One cat, two cats, three catz.");

        String replacedValue = matcher.replaceFirst("dog");

        assertThat(replacedValue).isEqualTo(__);
    }

    @Koan
    void in_case_you_may_want_to_replace_the_first_occurrence_with_modifications() {
        Pattern pattern = Pattern.compile("[Cc]at([sz])?");
        Matcher matcher = pattern.matcher("One cat, two cats, three catz.");

        String replacedValue = matcher.replaceFirst(matchResult -> matchResult.group().toUpperCase());

        assertThat(replacedValue).isEqualTo(__);
    }

    @Koan
    void you_can_replace_all_occurrences_of_a_regex_in_a_string() {
        String line = "One cat, two cats, three catz.";
        String replacedValue = line.replaceAll("[Cc]at([sz])+", "dog$1");

        assertThat(replacedValue).isEqualTo(__);
    }

    @Koan
    void you_can_replace_the_first_occurrence_of_a_regex_in_a_string() {
        String line = "One cat, two cats, three catz.";
        String replacedValue = line.replaceFirst("[Cc]at([sz])+", "dog$1");

        assertThat(replacedValue).isEqualTo(__);
    }

    @Koan
    void you_can_split_a_string_using_a_regex() {
        String line = "1,name.description";
        String[] data = line.split("[,.]");

        assertThat(data[0]).isEqualTo(__);
        assertThat(data[1]).isEqualTo(__);
        assertThat(data[2]).isEqualTo(__);
    }

    @Koan
    void you_should_really_learn_about_regex_matches() {
        Pattern pattern = Pattern.compile("^(?:(?:[\\w`~!#$%^&*\\-=+;:{}'|,?/]+(?:(?:\\.(?:\"(?:\\\\?[\\w`~!#$%^&*\\-=+;:{}'|,?/.()<>\\[\\] @]|\\\\\"|\\\\\\\\)*\"|[\\w`~!#$%^&*\\-=+;:{}'|,?/]+))*\\.[\\w`~!#$%^&*\\-=+;:{}'|,?/]+)?)|(?:\"(?:\\\\?[\\w`~!#$%^&*\\-=+;:{}'|,?/.()<>\\[\\] @]|\\\\\"|\\\\\\\\)+\"))@(?:[a-zA-Z\\d\\-]+(?:\\.[a-zA-Z\\d\\-]+)*|\\[\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}])$");

        assertThat(pattern.matcher("user@example.com").matches()).isEqualTo(__);
        assertThat(pattern.matcher("user.name@example.com").matches()).isEqualTo(__);
        assertThat(pattern.matcher("user.name.with.last.name@example.com").matches()).isEqualTo(__);
        assertThat(pattern.matcher("user.name+spam@example.com").matches()).isEqualTo(__);
        assertThat(pattern.matcher("user.last-name@example.com").matches()).isEqualTo(__);
        assertThat(pattern.matcher("user@example").matches()).isEqualTo(__);
    }

    @Koan
    void an_invalid_pattern_will_throw_a_pattern_syntax_exception_when_compiled() {
        try {
            Pattern.compile("][");
        } catch (final Exception exception) {
            assertThat(exception.getClass()).isEqualTo(__);
        }
    }
}
