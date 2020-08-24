package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.demo.vo.SlotForecastDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;

//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { KafkaProducer.class})
public class KafkaProducerTest {
	
	@Autowired
	private KafkaTemplate<String, SlotForecastDetails> kafkaTemplate;
	
	@Autowired
	private com.example.demo.service.KafkaProducer kafkaProducer;
	
	@Test
	public void sendMSTMessageTest() throws Exception {    
		SlotForecastDetails slot = new SlotForecastDetails();
		for (int count = 200; count <= 300; count++) {
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
//			slot.setSlotStartTS(LocalDateTime.of(2020, Month.JULY, 9, 00, 00, 00));
//			slot.setSlotEndTS(LocalDateTime.of(2020, Month.JULY, 10, 00, 00, 00));
//			slot.setSlotExpiryTS(LocalDateTime.of(2020, Month.JULY, 10, 00, 00, 00));
			slot.setSlotPlan("STANDARD");
			kafkaProducer.sendMSTMessage(slot);
		}
}
}
