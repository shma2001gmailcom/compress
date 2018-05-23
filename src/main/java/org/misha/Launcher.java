package org.misha;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * author: misha
 * date: 4/19/18
 */
public class Launcher {
    private static final Logger log = Logger.getLogger(Launcher.class);
    private static final Properties properties = makeProperties();
    public static final String SEPARATOR = properties.getProperty("hash.encoded.text.delimiter");
    
    public static void main(String... args) {
        final Compressor compressor = new Compressor(readLiBo());
        compressor.compress();
        log.debug(compressor.compressed());
    }
    
    private static String readLiBo() {
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader("./resources/Li-Bo.txt"); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
        } catch (IOException e) {
            //
        }
        return sb.toString();
    }
    
    private static Properties makeProperties() {
        final Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            //
        }
        return properties;
    }
}
