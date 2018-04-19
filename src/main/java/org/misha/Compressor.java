package org.misha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * author: misha
 * date: 2/20/18
 */
class Compressor {
    private final String text;
    private Map<Character, List<Integer>> result;
    
    Compressor(final String text) {
        checkArgument(isNotEmpty(text));
        result = new HashMap<>();
        this.text = text;
    }

    Map<Character, List<Integer>> compress() {
        final char[] chars = text.toCharArray();
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
    
    String compressed() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        for (final Map.Entry<Character, List<Integer>> e : result.entrySet()) {
           sb.append(e.getKey()).append(' ');
           for (final int i : e.getValue()) {
               sb.append(i).append(' ');
           }
           sb.append('\n');
        }
        return sb.toString();
    }
}

