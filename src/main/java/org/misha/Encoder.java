package org.misha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: misha
 * date: 2/20/18
 */
public class Encoder {
    private final String text;
    
    Encoder(final String text) {
        this.text = text;
    }
    
    Map<Character, List<Integer>> encode() {
        final char[] chars = text.toCharArray();
        final Map<Character, List<Integer>> result = new HashMap<>();
        for (int position = 0; position < chars.length; ++position) {
            final char c = chars[position];
            if (!result.containsKey(c)) {
                result.put(c, new ArrayList<Integer>());
            }
            result.get(c).add(position);
        }
        return result;
    }
    
    int size() {
        return text.length();
    }
}

