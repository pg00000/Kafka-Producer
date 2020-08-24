package com.example.demo.serializers;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.example.demo.vo.SlotForecastDetails;

public class SlotForecastTemplateSerde implements Serde<SlotForecastDetails> {

	private SlotForecastSerializer serializer;
	private SlotForecastDeSerializer deSerializer;

	@Override
	public void configure(Map<String, ?> map, boolean b) {
	}

	@Override
	public void close() {
		serializer.close();
		deSerializer.close();
	}

	@Override
	public Serializer<SlotForecastDetails> serializer() {
		return serializer;
	}

	@Override
	public Deserializer<SlotForecastDetails> deserializer() {
		return deSerializer;
	}

}
