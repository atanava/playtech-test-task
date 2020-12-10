package com.atanava.playtech;

import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogRules {
    private static final Logger log = LoggerFactory.getLogger("com.atanava.playtech");
    private static String watchedLog;
    private static final StringBuilder results = new StringBuilder();

    @Rule(order = Integer.MIN_VALUE)
    public static final TestWatcher watchman = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            watchedLog = String.format("%-85s %7s", description.getDisplayName(), "fail!");
            results.append(watchedLog).append('\n');
            log.error(watchedLog);
        }

        @Override
        protected void succeeded(Description description) {
            watchedLog = String.format("%-85s %7s", description.getDisplayName(), "success!");
            results.append(watchedLog).append('\n');
            log.info(watchedLog);
        }
    };

    private static final String DELIM = "-".repeat(100);

    public static final ExternalResource SUMMARY = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            results.setLength(0);
        }

        @Override
        protected void after() {
            log.info("\n" + DELIM +
                    "\nTest" +
                    "\n" + DELIM + "\n" + results + DELIM + "\n");
        }
    };

}
