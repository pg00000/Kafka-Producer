package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SlotForecastDetails {

	private String messageType;
	private Long storeId;
	private String dayOfWeek;
	private String slotPlan;
	private String serviceType;
	private String deliveryType;
	private Long slotStartTime;
	private Long slotEndTime;
	private String startDate;
	private String endDate;
	private Integer maxBookings;
	private Double b2cDeliveryCharge;
	private Double b2cRdcCharge;
	private Integer b2cMinBasketSize;
	private Integer b2cAlertBasketSize;
	private Double b2bDeliveryCharge;
	private Double b2bRdcCharge;
	private Integer b2bMinBasketSize;
	private Integer b2bAlertBasketSize;
	private Long slotExpiryTime;
	private String createdBy;
	private String updatedBy;

}