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
    
    @PostMapping(value = "/prod/bulkUpdate")
    public String bulkUpdateForecastSlots() {
        for (int store = 1; store <= 10; store++) {
            for (int slothour = 1; slothour <= 22; slothour++) {
                for (int count = 0; count <= 59; count++) {
                    SlotForecastDetails slot = new SlotForecastDetails();
                    slot.setMessageType("BaseForecast");
                    slot.setStoreId(Long.valueOf(store));
                    slot.setB2bAlertBasketSize(9999);
                    slot.setB2bDeliveryCharge(5.95);
                    slot.setB2bMinBasketSize(150);
                    slot.setB2bRdcCharge(4.95);
                    slot.setB2cAlertBasketSize(9999);
                    slot.setB2cDeliveryCharge(5.95);
                    slot.setB2cMinBasketSize(150);
                    slot.setB2cRdcCharge(4.95);
                    slot.setDayOfWeek("SUNDAY");
                    slot.setDeliveryType("A");
                    slot.setMaxBookings(93);
                    slot.setServiceType("DUG");
                    slot.setCreatedBy("user1");
                    slot.setUpdatedBy("user2");
                    slot.setSlotPlan("STANDARD");
                    slot.setSlotStartTime(Long.valueOf(slothour * 100 + count));
                    slot.setSlotEndTime(Long.valueOf(slothour * 100 + 100 + count));
                    slot.setStartDate("2020-09-06");
                    slot.setEndDate("2020-09-06");
                    slot.setSlotExpiryTime(Long.valueOf(slothour * 100 + count));
                    kafkaSender.sendMSTMessage(slot);
                    // log.info("Slot --> " + slot.toString());
                }
            }

            for (int slothour = 1; slothour <= 22; slothour++) {
                for (int count = 0; count <= 59; count++) {
                    SlotForecastDetails slot = new SlotForecastDetails();
                    slot.setMessageType("BaseForecast");
                    slot.setStoreId(Long.valueOf(store));
                    slot.setB2bAlertBasketSize(9999);
                    slot.setB2bDeliveryCharge(5.95);
                    slot.setB2bMinBasketSize(150);
                    slot.setB2bRdcCharge(4.95);
                    slot.setB2cAlertBasketSize(9999);
                    slot.setB2cDeliveryCharge(5.95);
                    slot.setB2cMinBasketSize(150);
                    slot.setB2cRdcCharge(4.95);
                    slot.setDayOfWeek("MONDAY");
                    slot.setDeliveryType("A");
                    slot.setMaxBookings(94);
                    slot.setServiceType("DUG");
                    slot.setCreatedBy("user1");
                    slot.setUpdatedBy("user2");
                    slot.setSlotPlan("STANDARD");
                    slot.setSlotStartTime(Long.valueOf(slothour * 100 + count));
                    slot.setSlotEndTime(Long.valueOf(slothour * 100 + 100 + count));
                    slot.setStartDate("2020-09-07");
                    slot.setEndDate("2020-09-07");
                    slot.setSlotExpiryTime(Long.valueOf(slothour * 100 + count));
                    kafkaSender.sendMSTMessage(slot);
                    // log.info("Slot --> " + slot.toString());
                }
            }

            for (int slothour = 1; slothour <= 22; slothour++) {
                for (int count = 0; count <= 59; count++) {
                    SlotForecastDetails slot = new SlotForecastDetails();
                    slot.setMessageType("BaseForecast");
                    slot.setStoreId(Long.valueOf(store));
                    slot.setB2bAlertBasketSize(9999);
                    slot.setB2bDeliveryCharge(5.95);
                    slot.setB2bMinBasketSize(150);
                    slot.setB2bRdcCharge(4.95);
                    slot.setB2cAlertBasketSize(9999);
                    slot.setB2cDeliveryCharge(5.95);
                    slot.setB2cMinBasketSize(150);
                    slot.setB2cRdcCharge(4.95);
                    slot.setDayOfWeek("TUESDAY");
                    slot.setDeliveryType("A");
                    slot.setMaxBookings(94);
                    slot.setServiceType("DUG");
                    slot.setCreatedBy("user1");
                    slot.setUpdatedBy("user2");
                    slot.setSlotPlan("STANDARD");
                    slot.setSlotStartTime(Long.valueOf(slothour * 100 + count));
                    slot.setSlotEndTime(Long.valueOf(slothour * 100 + 100 + count));
                    slot.setStartDate("2020-09-08");
                    slot.setEndDate("2020-09-08");
                    slot.setSlotExpiryTime(Long.valueOf(slothour * 100 + count));
                    kafkaSender.sendMSTMessage(slot);
                    // log.info("Slot --> " + slot.toString());
                }
            }

            for (int slothour = 1; slothour <= 22; slothour++) {
                for (int count = 0; count <= 59; count++) {
                    SlotForecastDetails slot = new SlotForecastDetails();
                    slot.setMessageType("BaseForecast");
                    slot.setStoreId(Long.valueOf(store));
                    slot.setB2bAlertBasketSize(9999);
                    slot.setB2bDeliveryCharge(5.95);
                    slot.setB2bMinBasketSize(150);
                    slot.setB2bRdcCharge(4.95);
                    slot.setB2cAlertBasketSize(9999);
                    slot.setB2cDeliveryCharge(5.95);
                    slot.setB2cMinBasketSize(150);
                    slot.setB2cRdcCharge(4.95);
                    slot.setDayOfWeek("WEDNESDAY");
                    slot.setDeliveryType("A");
                    slot.setMaxBookings(94);
                    slot.setServiceType("DUG");
                    slot.setCreatedBy("user1");
                    slot.setUpdatedBy("user2");
                    slot.setSlotPlan("STANDARD");
                    slot.setSlotStartTime(Long.valueOf(slothour * 100 + count));
                    slot.setSlotEndTime(Long.valueOf(slothour * 100 + 100 + count));
                    slot.setStartDate("2020-09-09");
                    slot.setEndDate("2020-09-09");
                    slot.setSlotExpiryTime(Long.valueOf(slothour * 100 + count));
                    kafkaSender.sendMSTMessage(slot);
                    // log.info("Slot --> " + slot.toString());
                }
            }

            for (int slothour = 1; slothour <= 22; slothour++) {
                for (int count = 0; count <= 59; count++) {
                    SlotForecastDetails slot = new SlotForecastDetails();
                    slot.setMessageType("BaseForecast");
                    slot.setStoreId(Long.valueOf(store));
                    slot.setB2bAlertBasketSize(9999);
                    slot.setB2bDeliveryCharge(5.95);
                    slot.setB2bMinBasketSize(150);
                    slot.setB2bRdcCharge(4.95);
                    slot.setB2cAlertBasketSize(9999);
                    slot.setB2cDeliveryCharge(5.95);
                    slot.setB2cMinBasketSize(150);
                    slot.setB2cRdcCharge(4.95);
                    slot.setDayOfWeek("THURSDAY");
                    slot.setDeliveryType("A");
                    slot.setMaxBookings(94);
                    slot.setServiceType("DUG");
                    slot.setCreatedBy("user1");
                    slot.setUpdatedBy("user2");
                    slot.setSlotPlan("STANDARD");
                    slot.setSlotStartTime(Long.valueOf(slothour * 100 + count));
                    slot.setSlotEndTime(Long.valueOf(slothour * 100 + 100 + count));
                    slot.setStartDate("2020-09-10");
                    slot.setEndDate("2020-09-10");
                    slot.setSlotExpiryTime(Long.valueOf(slothour * 100 + count));
                    kafkaSender.sendMSTMessage(slot);
                    // log.info("Slot --> " + slot.toString());
                }
            }

            for (int slothour = 1; slothour <= 22; slothour++) {
                for (int count = 0; count <= 59; count++) {
                    SlotForecastDetails slot = new SlotForecastDetails();
                    slot.setMessageType("BaseForecast");
                    slot.setStoreId(Long.valueOf(store));
                    slot.setB2bAlertBasketSize(9999);
                    slot.setB2bDeliveryCharge(5.95);
                    slot.setB2bMinBasketSize(150);
                    slot.setB2bRdcCharge(4.95);
                    slot.setB2cAlertBasketSize(9999);
                    slot.setB2cDeliveryCharge(5.95);
                    slot.setB2cMinBasketSize(150);
                    slot.setB2cRdcCharge(4.95);
                    slot.setDayOfWeek("FRIDAY");
                    slot.setDeliveryType("A");
                    slot.setMaxBookings(94);
                    slot.setServiceType("DUG");
                    slot.setCreatedBy("user1");
                    slot.setUpdatedBy("user2");
                    slot.setSlotPlan("STANDARD");
                    slot.setSlotStartTime(Long.valueOf(slothour * 100 + count));
                    slot.setSlotEndTime(Long.valueOf(slothour * 100 + 100 + count));
                    slot.setStartDate("2020-09-11");
                    slot.setEndDate("2020-09-11");
                    slot.setSlotExpiryTime(Long.valueOf(slothour * 100 + count));
                    kafkaSender.sendMSTMessage(slot);
                    // log.info("Slot --> " + slot.toString());
                }
            }

            for (int slothour = 1; slothour <= 22; slothour++) {
                for (int count = 0; count <= 59; count++) {
                    SlotForecastDetails slot = new SlotForecastDetails();
                    slot.setMessageType("BaseForecast");
                    slot.setStoreId(Long.valueOf(store));
                    slot.setB2bAlertBasketSize(9999);
                    slot.setB2bDeliveryCharge(5.95);
                    slot.setB2bMinBasketSize(150);
                    slot.setB2bRdcCharge(4.95);
                    slot.setB2cAlertBasketSize(9999);
                    slot.setB2cDeliveryCharge(5.95);
                    slot.setB2cMinBasketSize(150);
                    slot.setB2cRdcCharge(4.95);
                    slot.setDayOfWeek("SATURDAY");
                    slot.setDeliveryType("A");
                    slot.setMaxBookings(94);
                    slot.setServiceType("DUG");
                    slot.setCreatedBy("user1");
                    slot.setUpdatedBy("user2");
                    slot.setSlotPlan("STANDARD");
                    slot.setSlotStartTime(Long.valueOf(slothour * 100 + count));
                    slot.setSlotEndTime(Long.valueOf(slothour * 100 + 100 + count));
                    slot.setStartDate("2020-09-12");
                    slot.setEndDate("2020-09-12");
                    slot.setSlotExpiryTime(Long.valueOf(slothour * 100 + count));
                    kafkaSender.sendMSTMessage(slot);
                    // log.info("Slot --> " + slot.toString());
                }
            }
        }

        return "Message sent to the Kafka Topic java_in_use_topic Successfully";
    }
}
