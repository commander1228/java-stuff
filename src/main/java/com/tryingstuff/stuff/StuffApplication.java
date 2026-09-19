package com.tryingstuff.stuff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;

@SpringBootApplication
public class StuffApplication {

	private static final Logger logger = LoggerFactory.getLogger(StuffApplication.class);

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(StuffApplication.class);
		application.addListeners((ApplicationEnvironmentPreparedEvent event) ->
				logDataSourceConfiguration(event));
		application.run(args);
	}

	private static void logDataSourceConfiguration(ApplicationEnvironmentPreparedEvent event) {
		String configuredUrl = event.getEnvironment()
				.getProperty("spring.datasource.url", "");
		String resolvedUrl = event.getEnvironment().resolvePlaceholders(configuredUrl);

		logger.info(
				"Datasource configuration: SPRING_DATASOURCE_URL present={}, PGHOST={}, PGPORT={}, "
						+ "PGDATABASE={}, SPRING_DATASOURCE_USERNAME present={}, "
						+ "SPRING_DATASOURCE_PASSWORD present={}, resolved URL={}",
				hasText(event.getEnvironment().getProperty("SPRING_DATASOURCE_URL")),
				valueOrMissing(event.getEnvironment().getProperty("PGHOST")),
				valueOrMissing(event.getEnvironment().getProperty("PGPORT")),
				valueOrMissing(event.getEnvironment().getProperty("PGDATABASE")),
				hasText(event.getEnvironment().getProperty("SPRING_DATASOURCE_USERNAME")),
				hasText(event.getEnvironment().getProperty("SPRING_DATASOURCE_PASSWORD")),
				redactCredentials(resolvedUrl));
	}

	private static boolean hasText(String value) {
		return value != null && !value.isBlank();
	}

	private static String valueOrMissing(String value) {
		return hasText(value) ? value : "<missing>";
	}

	private static String redactCredentials(String url) {
		int schemeSeparator = url.indexOf("://");
		if (schemeSeparator < 0) {
			return "<unrecognized URL format>";
		}

		int credentialsEnd = url.indexOf('@', schemeSeparator + 3);
		if (credentialsEnd < 0) {
			return url.replaceFirst("[?#].*$", "");
		}

		return url.substring(0, schemeSeparator + 3)
				+ url.substring(credentialsEnd + 1).replaceFirst("[?#].*$", "");
	}
}
