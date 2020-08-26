package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.KafkaProducer;
import com.example.demo.util.ReadSlotForecastFromCsv;
import com.example.demo.vo.SlotForecastDetails;
import com.example.demo.vo.SlotForecastVO;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

	@Autowired
	KafkaProducer kafkaSender;

	/*
	 * @GetMapping(value = "/producer") public String
	 * producer(@RequestParam("message") String message) {
	 * kafkaSender.sendMessage(message); return
	 * "Message sent to the Kafka Topic java_in_use_topic Successfully"; }
	 */

	@PostMapping(value = "/prod/slotForecast")
	public String produceForecastSlots(@RequestBody(required = true) SlotForecastDetails slotForecastDetails) {
		kafkaSender.sendMSTMessage(slotForecastDetails);
		return "Message sent to the Kafka Topic java_in_use_topic Successfully";
	}

    @PostMapping(value = "/prod/slotForecastList")
    public String produceForecastSlotsList(@RequestBody(required = true) SlotForecastVO slotForecastDetailsList) {
        slotForecastDetailsList.getSlotForecastList().forEach(slotForecastDetail -> {
            kafkaSender.sendMSTMessage(slotForecastDetail);
        });
        
        return "Message sent to the Kafka Topic java_in_use_topic Successfully";
    }	

    @GetMapping(value = "/prod/readSlotForecastFromCsv")
    public String readSlotForecastFromCsv() throws IOException {
        File input = new File("C:\\Users\\858184\\Downloads\\slotforecastfile.csv");
        
        List<SlotForecastDetails> data = ReadSlotForecastFromCsv.readObjectsFromCsv(input);

        List<SlotForecastDetails> sortedList = data.stream()
                .sorted(Comparator.comparingLong(SlotForecastDetails::getStoreId))
                .collect(Collectors.toList());

        sortedList.forEach(slotForecast -> {
            kafkaSender.sendMSTMessage(slotForecast);
            //System.out.println(slotForecast);
        });       
        return "Message sent to the Kafka Topic java_in_use_topic Successfully";
    }
    
	@PostMapping(value = "/prod/bulkSend")
	public String bulkSendForecastSlots() {
		SlotForecastDetails slot = new SlotForecastDetails();
		for (int count = 1; count <= 100; count++) {
			slot.setMessageType("BaseForecast");
			slot.setStoreId(Long.valueOf(count));
			slot.setB2bAlertBasketSize(9999);
			slot.setB2bDeliveryCharge(5.95);
			slot.setB2bMinBasketSize(150);
			slot.setB2bRdcCharge(4.95);
			slot.setB2cAlertBasketSize(9999);
			slot.setB2cDeliveryCharge(5.95);
			slot.setB2cMinBasketSize(150);
			slot.setB2cRdcCharge(4.95);
			slot.setDayOfWeek("SATURDAY");
			slot.setDeliveryType("ATTENDED");
			slot.setMaxBookings(10);
			slot.setServiceType("DUG");
			slot.setCreatedBy("user1");
			slot.setUpdatedBy("user2");
			slot.setSlotPlan("STANDARD");
			slot.setSlotStartTime(800L);
			slot.setSlotEndTime(900L);
			slot.setStartDate("2020-08-24");
			slot.setEndDate("2020-08-24");
			slot.setSlotExpiryTime(500L);
			kafkaSender.sendMSTMessage(slot);
		}
		return "Message sent to the Kafka Topic java_in_use_topic Successfully";
	}
}