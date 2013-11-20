package ch.zhaw.mdp.lhb.citr.Logging.impl;

import ch.zhaw.mdp.lhb.citr.Logging.LoggingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLoggingStrategy implements LoggingStrategy {

	private Logger log;

	public Slf4jLoggingStrategy() {
		log = LoggerFactory.getLogger(Slf4jLoggingStrategy.class);
	}

	@Override
	public void info(String message) {
		log.info(message);
	}

	@Override
	public void debug(String message) {
		log.debug(message);
	}

	@Override
	public void error(String message) {
		log.error(message);
	}

	@Override
	public void trace(String message) {
		log.trace(message);
	}

	@Override
	public void warning(String message) {
		log.warn(message);
	}
}
