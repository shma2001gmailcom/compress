package org.misha.hash;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.Objects;
import java.util.SortedSet;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * author: misha
 * date: 2/20/18
 */
public class Ranges implements Iterable<Range> {
    private final SortedSet<Range> raw;

    public Ranges(final SortedSet<Range> raw) {
        checkArgument(raw != null);
        this.raw = raw;
    }

    @Override
    @Nonnull
    public Iterator<Range> iterator() {
        return raw.iterator();
    }

    Range min() {
        return raw.first();
    }

    Range max() {
        return raw.last();
    }

    @Override
    public String toString() {
        return "Ranges{" + Objects.toString(raw) + '}';
    }
}
