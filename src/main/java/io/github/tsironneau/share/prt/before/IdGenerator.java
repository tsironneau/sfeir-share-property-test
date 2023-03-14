package io.github.tsironneau.share.prt.before;

public class IdGenerator {
    public static final long PREFIX_LEFT_SHIFT = 10000000000000000L;

    private IdGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static Long newId(Integer prefix, Long sequenceValue) {
        return (prefix == null) ? null : prefix * PREFIX_LEFT_SHIFT + sequenceValue;
    }

}