package io.github.tsironneau.share.prt.after;

public class IdGenerator {

    private static final long PREFIX_LEFT_SHIFT = 1_000_000_000_000_000L; //10^15
    public static final int PREFIX_MAX_VALUE = 9223;
    public static final long SEQUENCE_MAX_VALUE = 372_036_854_775_807L;

    private IdGenerator() {
        throw new IllegalStateException("Utility class");
    }

    /*
     * Long.MAX_VALUE = 9_223_372_036_854_775_807<p>
     * PREFIX_LEFT_SHIFT =     1_000_000_000_000_000<p>
     * -> prefix max value is 9223<p>
     * -> sequenceValue maxValue is 372_036_854_775_807
     */
    public static Long newId(Integer prefix, Long sequenceValue) {
        if (prefix == null)
            return null;
        if (sequenceValue == null)
            throw new IllegalArgumentException("sequence value can not be null");
        if (prefix < 1)
            throw new IllegalArgumentException("prefix must be a positive integer " + prefix);
        if (sequenceValue < 1)
            throw new IllegalArgumentException("sequenceValue must be a postive integer " + sequenceValue);
        if (prefix > PREFIX_MAX_VALUE)
            throw new IllegalArgumentException("prefix can not be greater than " + PREFIX_MAX_VALUE + " : " + prefix);
        if (sequenceValue > SEQUENCE_MAX_VALUE)
            throw new IllegalArgumentException("sequenceValue can not be greater than " + SEQUENCE_MAX_VALUE + " : " + sequenceValue);

        return prefix * PREFIX_LEFT_SHIFT + sequenceValue;
    }

}