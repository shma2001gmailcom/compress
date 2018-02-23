package org.misha;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * author: misha
 * date: 2/23/18
 * time: 7:03 PM
 */
public class CompressorTest {
    private static final Logger log = Logger.getLogger(CompressorTest.class);
    private static final String TEXT_TO_COMPRESS = "text to compress";

    @Test
    public void testCompress() throws Exception {
        Compressor compressor = new Compressor(TEXT_TO_COMPRESS);
        Map<Character, List<Integer>> compressed = compressor.compress();
        log.debug(compressed);
        assertEquals(TEXT_TO_COMPRESS, new Decoder(compressed).decode(TEXT_TO_COMPRESS.length()));
    }
}