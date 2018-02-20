package org.misha.hash;

import java.util.Iterator;
import java.util.Objects;
import java.util.SortedSet;

/**
 * author: misha
 * date: 2/20/18
 */
public class Ranges implements Iterable<Range> {
    private final SortedSet<Range> raw;
    
    public Ranges(final SortedSet<Range> raw) {
        this.raw = raw;
    }
    
    @Override
    public Iterator<Range> iterator() {
        return raw.iterator();
    }
    
    void add(final Range range) {
        raw.add(range);
    }
    
    Range min() {
        return raw.first();
    }
    
    Range max() {
        return raw.last();
    }
    
    @Override
    public String toString() {
        return "Ranges{" +
                Objects.toString(raw) +
                '}';
    }
}
