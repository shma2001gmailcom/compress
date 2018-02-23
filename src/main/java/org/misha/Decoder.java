package org.misha;

import java.util.List;
import java.util.Map;

/**
 * author: misha
 * date: 2/20/18
 */

public class Decoder {
    private final Map<Character, List<Integer>> compressed;

    Decoder(final Map<Character, List<Integer>> compressed) {
        this.compressed = compressed;
    }

    String decode(final int size) {
        final char[] result = new char[size];
        for (Map.Entry<Character, List<Integer>> e : compressed.entrySet()) {
            for (int pos : e.getValue()) {
                result[pos] = beforeInsert(e.getKey(), pos);
            }
        }
        return new String(result);
    }

    /**
     * do something with 'c' and 'position' before
     * decompress char 'c' into place 'position'
     *
     * @param c        char
     * @param position where 'c' should be placed
     * @return another char
     */
    protected char beforeInsert(final char c, final int position) {
        return c;
    }
}
