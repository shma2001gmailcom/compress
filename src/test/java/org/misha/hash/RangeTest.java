package org.misha.hash;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * author: misha
 * date: 2/23/18
 * time: 6:45 PM
 */
public class RangeTest {
    private static final Logger log = Logger.getLogger(org.misha.Test.class);

    @Test
    public void getRandom() throws Exception {
        Range range = new Range(1111111L, 77777777L);
        for(int i = 0; i < 1000; ++i, log.debug(range.getRandom()));
    }
}