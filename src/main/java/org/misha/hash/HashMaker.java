package org.misha.hash;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * author: misha
 * date: 2/20/18
 */
public abstract class HashMaker {
    private static final String ENCODED_TEXT_WORD_DELIMITER = " ";
    private final Ranges ranges;
    private final Map<Character, Range> hashes;
    
    protected HashMaker(final Ranges ranges, final String text) {
        this.ranges = ranges;
        this.hashes = hash(Alphabet.alphabet(text));
    }
    
    private Map<Character, Range> hash(final Alphabet alphabet) {
        checkArgument(alphabet != null, "alphabet is null");
        final Map<Character, Range> result = new HashMap<>();
        for (Character c : alphabet)
            result.put(c, applyRule(c));
        return result;
    }
    
    private char getCharByHash(final long reverseHash) {
        final boolean preCondition = ranges.min().getLower() <= reverseHash && reverseHash < ranges.max().getUpper();
        checkArgument(preCondition, "out of bounds");
        for (final Map.Entry<Character, Range> e : hashes.entrySet())
            if (e.getValue().contains(reverseHash)) return e.getKey();
        throw new IllegalStateException(reverseHash + " out of range");
    }
    
    /**
     * Define how to select a numeric range whose any number can be used for encode 'c'
     *
     * @param c char to encode
     * @return range for 'c'
     */
    protected abstract Range applyRule(Character c);
    
    public String encode(final String text) {
        checkArgument(StringUtils.isNotEmpty(text));
        final StringBuilder sb = new StringBuilder();
        for (final Character c : text.toCharArray())
            sb.append(hashes.get(c).getRandom()).append(ENCODED_TEXT_WORD_DELIMITER);
        return sb.toString();
    }
    
    public String decode(final String text) {
        checkArgument(StringUtils.isNotEmpty(text));
        final StringBuilder sb = new StringBuilder();
        String[] parts = text.split(ENCODED_TEXT_WORD_DELIMITER);
        for (String part : parts)
            sb.append(getCharByHash(Long.parseLong(part)));
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "HashMaker{" +
                "hashes=" + Objects.toString(hashes) +
                '}';
    }
}
