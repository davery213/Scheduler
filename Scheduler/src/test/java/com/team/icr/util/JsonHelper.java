package com.team.icr.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonHelper {

	public static String convertObjectToJson(final Object obj) {
		final ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String jsonString = null;
		try {
			jsonString = ow.writeValueAsString(obj);
		} catch (final JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonString;
	}

}
