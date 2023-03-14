package io.github.tsironneau.share.prt.after;

import static io.github.tsironneau.share.prt.after.IdGenerator.*;

import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.LongRange;
import net.jqwik.api.constraints.Positive;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class IdGeneratorPropertyTest {

    @Property
    void newId_returns_null_when_prefix_is_null(@ForAll Long __) {
        assertThat(newId(null, __)).isNull();
    }

    @Property
    void newId_throws_IAE_when_sequenceValue_is_null(@ForAll Integer __) {
        assertThatIllegalArgumentException().isThrownBy(() ->
                newId(__, null)
        ).withMessageContaining("sequence value can not be null");
    }

    @Property
    void newId_throws_IAE_when_prefix_is_not_positive(
            @ForAll @IntRange(min = Integer.MIN_VALUE, max = 0) Integer prefix,
            @ForAll Long __) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> newId(prefix, __)
        ).withMessageContaining("prefix", "negative");
    }

    @Property
    void newId_throws_IAE_when_sequence_is_not_positive(
            @ForAll @Positive Integer __,
            @ForAll @LongRange(min = Integer.MIN_VALUE, max = 0) Long sequenceValue) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> newId(__, sequenceValue)
        ).withMessageContaining("sequenceValue", "negative");
    }

    @Property
    void newId_throws_IAE_when_prefix_greater_than_max_value(
            @ForAll @IntRange(min = PREFIX_MAX_VALUE + 1) Integer prefix,
            @ForAll @Positive Long __) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> newId(prefix, __)
        ).withMessageContaining("prefix", "greater");
    }

    @Property
    void newId_throws_IAE_when_sequence_greater_than_max_value(
            @ForAll @ValidPrefix Integer __,
            @ForAll @LongRange(min = SEQUENCE_MAX_VALUE + 1) Long sequenceValue) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> newId(__, sequenceValue)
        ).withMessageContaining("sequenceValue", "greater");
    }

    @Property
    void generated_legacy_id_should_starts_with_prefix_and_ends_with_sequence(
            @ForAll @LongRange(min = 1, max = SEQUENCE_MAX_VALUE) Long nextVal,
            @ForAll @ValidPrefix Integer prefix) {
        //When
        Long actual = newId(prefix, nextVal);
        //Then
        assertThat(actual.toString())
                .startsWith(prefix.toString())
                .endsWith(nextVal.toString());
    }

    @Example
    void newId_valid_example() {
        // Given
        int prefix = 72;
        long sequenceValue = 10000000000L;
        // When
        Long actual = newId(prefix, sequenceValue);
        // Then
        assertThat(actual).isEqualTo(72000010000000000L);
    }
}