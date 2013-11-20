package ch.zhaw.mdp.lhb.citr.Logging;

public interface LoggingStrategy {
	void info(String message);
	void debug(String message);
	void error(String message);
	void trace(String message);
	void warning(String message);
}
