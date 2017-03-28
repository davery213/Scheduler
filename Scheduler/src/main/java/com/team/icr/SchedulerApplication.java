package com.team.icr;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchedulerApplication {

	public static void main(final String[] args) {
		java.util.TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(SchedulerApplication.class, args);
	}
}
