package org.misha;

import org.apache.log4j.Logger;
import org.misha.hash.HashMaker;
import org.misha.hash.Range;
import org.misha.hash.Ranges;

import java.security.SecureRandom;
import java.util.TreeSet;

/**
 * author: misha
 * date: 2/20/18
 */
public class Test {
    private static final Logger log = Logger.getLogger(Test.class);
    private static final String LI_BO = "аааа \n ааааааааааааааааааааааааааааааааааааааааааааааааааа.";
    
    public static void main(String... args) {
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
        log.debug("\n  " + hashMaker.encode(LI_BO));
        log.debug('\n' + hashMaker.decode(hashMaker.encode(LI_BO)));
    }
}
