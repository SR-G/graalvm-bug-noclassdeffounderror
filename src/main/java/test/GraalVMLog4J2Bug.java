package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GraalVMLog4J2Bug {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(final String[] argv) {
         LOGGER.info("Hello, bug");
    }
}
