package org.misha.hash;

import javax.annotation.Nonnull;
import java.security.SecureRandom;

/**
 * author: misha
 * date: 2/20/18
 */
public class Range implements Comparable<Range> {
    private final long lower;
    private final long upper;
    private final int hashCode;

    public Range(final long left, final long right) {
        lower = left;
        upper = right;
        hashCode = 31 * (int) (lower ^ (lower >>> 32)) + (int) (upper ^ (upper >>> 32));
    }

    long getLower() {
        return lower;
    }

    long getUpper() {
        return upper;
    }

    @Override
    public int compareTo(@Nonnull final Range o) {
        return Long.signum(upper - o.lower);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Range range = (Range) o;
        return lower == range.lower && upper == range.upper;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    boolean contains(final long value) {
        return lower <= value && value < upper;
    }

    long getRandom() {
        final long n = (long) new SecureRandom().nextInt(511111);
        final long m = (long) new SecureRandom().nextInt(511111);
        return (m * lower + n * (upper - 1)) / (m + n);
    }

    @Override
    public String toString() {
        return "Range{" + "lower=" + lower + ", upper=" + upper + '}';
    }
}
