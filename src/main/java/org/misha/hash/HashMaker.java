package org.misha.hash;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static org.misha.Launcher.SEPARATOR;

/**
 * author: misha
 * date: 2/20/18
 */
public class HashMaker {
    private static final Logger log = Logger.getLogger(HashMaker.class);
    private final Ranges ranges;
    private final Map<Character, Range> hashes;

    public HashMaker(final Ranges ranges, final String text) {
        this.ranges = ranges;
        this.hashes = hash(Alphabet.alphabet(text));
    }

    private Map<Character, Range> hash(final Alphabet alphabet) {
        checkArgument(alphabet != null, "alphabet is null");
        final Map<Character, Range> result = new HashMap<>();
        final Iterator<Range> it = ranges.iterator();
        checkArgument(it.hasNext(), "iterator has ended unexpectedly");
        for (final Character c : alphabet) {
            result.put(c, it.next());
        }
        log.debug(Objects.toString(result));
        return result;
    }

    private char getCharByHash(final long reverseHash) {
        final boolean preCondition = ranges.min().getLower() <= reverseHash && reverseHash < ranges.max().getUpper();
        checkArgument(preCondition, "out of bounds");
        for (final Map.Entry<Character, Range> e : hashes.entrySet()) {
            if (e.getValue().contains(reverseHash)) {
                return e.getKey();
            }
        }
        throw new IllegalStateException(reverseHash + " out of range");
    }

    public String encode(final String text) {
        checkArgument(StringUtils.isNotEmpty(text));
        final StringBuilder sb = new StringBuilder();
        for (final Character c : text.toCharArray()) {
            sb.append(hashes.get(c).getRandom()).append(SEPARATOR);
        }
        return sb.toString();
    }

    public String decode(final String text) {
        checkArgument(StringUtils.isNotEmpty(text));
        final StringBuilder sb = new StringBuilder();
        String[] parts = text.split(SEPARATOR);
        for (final String part : parts) {
            sb.append(getCharByHash(Long.parseLong(part)));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "HashMaker{" + "hashes=" + Objects.toString(hashes) + '}';
    }
}
