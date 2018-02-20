package org.misha.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * author: misha
 * date: 2/20/18
 */
public abstract class HashMaker {
    private static final String SPACE = " ";
    private final Ranges ranges;
    private final Map<Character, Range> hashes;
    
    protected HashMaker(final Ranges ranges) {
        this.ranges = ranges;
        this.hashes = hash();
    }
    
    private Map<Character, Range> hash() {
        final Map<Character, Range> result = new HashMap<>();
        for (Character c = (char)0; c < Character.MAX_VALUE; c++) {
            result.put(c, applyRule(c));
        }
        return result;
    }
    
    private char getCharByHash(final long hash) {
        if (hash < ranges.min().getLower() || hash >= ranges.max().getUpper())
            throw new IllegalArgumentException("out of bounds");
        for (final Map.Entry<Character, Range> e: hashes.entrySet()) {
            if (e.getValue().contains(hash)) return e.getKey();
        }
        throw new IllegalStateException(hash + " out of range");
    }
    
    protected abstract Range applyRule(Character c);
    
    public String encode(String text) {
        StringBuilder sb = new StringBuilder();
        for (Character c : text.toCharArray()) {
            sb = sb.append((hashes.get(c).getLower() + hashes.get(c).getUpper() - 1)/2).append(SPACE);
        }
        return sb.toString();
    }
    
    public String decode(String text) {
        StringBuilder sb = new StringBuilder();
        String[] parts = text.split(SPACE);
        for (String part : parts) {
           sb.append(getCharByHash(Long.parseLong(part)));
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "HashMaker{" +
                "hashes=" + Objects.toString(hashes) +
                '}';
    }
}
