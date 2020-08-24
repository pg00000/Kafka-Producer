package com.example.demo.serializers;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.example.demo.vo.SlotForecastDetails;
import com.google.gson.Gson;

public class SlotForecastDeSerializer implements Deserializer  {

	 private static final Charset CHARSET = Charset.forName("UTF-8");
	    static private Gson gson;

	    static {
	        gson = new Gson();
	    }

	    @Override
	    public void configure(Map map, boolean b) {

	    }

	    @Override
	    public Object deserialize(String s, byte[] bytes) {
	        try {
	            // Transform the bytes to String
	            String masterSlots = new String(bytes, CHARSET);
	            // Return the Person object created from the String 'person'
	            return gson.fromJson(masterSlots, SlotForecastDetails.class);
	        } catch (Exception e) {
	            throw new IllegalArgumentException("Error reading bytes! Yanlış", e);
	        }
	    }

	    @Override
	    public void close() {

	    }
}