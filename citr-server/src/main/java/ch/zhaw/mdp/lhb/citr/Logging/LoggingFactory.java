package ch.zhaw.mdp.lhb.citr.Logging;

import ch.zhaw.mdp.lhb.citr.Logging.impl.Slf4jLoggingStrategy;

public class LoggingFactory {
	public static LoggingStrategy get() {
		return new Slf4jLoggingStrategy();
	}
}
