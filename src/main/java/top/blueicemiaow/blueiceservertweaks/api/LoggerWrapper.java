package top.blueicemiaow.blueiceservertweaks.api;

import org.apache.logging.log4j.Logger;

public class LoggerWrapper {
    public LoggerWrapper(Logger logger, String prefix) {
        this.logger = logger;
        this.prefix = "%s: ".formatted(prefix);
    }
    private final Logger logger;
    private final String prefix;

    public void info(String log) {
        this.logger.info(this.prefix + log);
    }

    public void warn(String log) {
        this.logger.warn(this.prefix + log);
    }

    public void error(String log) {
        this.logger.error(this.prefix + log);
    }

    public void fatal(String log) {
        this.logger.fatal(this.prefix + log);
    }

    public void debug(String log) {
        this.logger.debug(this.prefix + log);
    }
}
