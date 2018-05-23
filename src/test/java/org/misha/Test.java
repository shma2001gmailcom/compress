package org.misha;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.misha.hash.HashMaker;
import org.misha.hash.Range;
import org.misha.hash.Ranges;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 * author: misha
 * date: 2/20/18
 */
public class Test {
    private static final Logger log = Logger.getLogger(Test.class);
    private static final String LI_BO = readLiBo();
    
    private static String readLiBo() {
        try {
            return FileUtils.readFileToString(new File("src/test/resources/Li-Bo.txt"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return e.getMessage();
        }
    }
    
    @org.junit.Test
    public void main() throws ClassNotFoundException {
        Compressor encoder = new Compressor(LI_BO);
        Decoder decoder = new Decoder(encoder.compress()) {

            @Override
            protected char beforeInsert(final char c, final int position) {
                return c; //can cipher or something here
            }
        };
        log.error('\n' + decoder.decode(encoder.size()));
        final TreeSet<Range> raw = new TreeSet<>();
        long lastRight = new SecureRandom().nextInt(600000);
        for (char c = 0; c < Character.MAX_VALUE; ++c) {
            long right = lastRight + new SecureRandom().nextInt(5000000);
            raw.add(new Range(lastRight, right));
            lastRight = right;
        }
        final Ranges ranges = new Ranges(raw);
        HashMaker hashMaker = new HashMaker(ranges, LI_BO);
        assertEquals(LI_BO, hashMaker.decode(hashMaker.encode(LI_BO)));
    }
}
