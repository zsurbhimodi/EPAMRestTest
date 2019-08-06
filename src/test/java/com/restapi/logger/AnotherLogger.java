package com.restapi.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnotherLogger {

    private static final Logger logger = LogManager.getLogger(AnotherLogger.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        logger.trace("Entering application.");
        logger.debug("Entering application.");
        logger.error("Entering application. error");
        logger.fatal("Entering application. fatal");

	}

}
