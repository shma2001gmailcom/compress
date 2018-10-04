package org.misha.hash;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * author: misha
 * date: 2/22/18
 */
public class Alphabet implements Iterable<Character> {
    private final Set<Character> characters;

    private Alphabet() {
        this.characters = new HashSet<>();
    }

    static Alphabet alphabet(final String text) {
        checkArgument(StringUtils.isNotEmpty(text), "text is empty");
        final Alphabet result = new Alphabet();
        for (final char c : text.toCharArray()) {
            result.characters.add(c);
        }
        return result;
    }

    @Override
    @Nonnull
    public Iterator<Character> iterator() {
        return characters.iterator();
    }
}
