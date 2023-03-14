package io.github.tsironneau.share.prt.before;

import org.junit.jupiter.api.Test;

import static io.github.tsironneau.share.prt.before.IdGenerator.newId;
import static org.assertj.core.api.Assertions.assertThat;

class IdGeneratorTest {

    @Test
    void newId_valid_example() {
        // Given
        int prefix = 72;
        long sequenceValue = 10000000000L;
        // When
        Long actual = newId(prefix, sequenceValue);
        // Then
        assertThat(actual).isEqualTo(720000010000000000L);
    }

}