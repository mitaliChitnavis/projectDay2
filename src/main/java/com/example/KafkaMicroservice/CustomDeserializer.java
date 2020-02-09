package com.example.KafkaMicroservice;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;


public class CustomDeserializer implements Deserializer<CSVRecord> {
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public CSVRecord deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		CSVRecord object = null;
		try {
			object = mapper.readValue(data, CSVRecord.class);
		} catch (Exception exception) {
			System.out.println("Error in deserializing bytes " + exception);
		}
		return object;
	}

	@Override
	public void close() {
	}
}