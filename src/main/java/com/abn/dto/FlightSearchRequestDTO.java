package com.abn.dto;

import org.springframework.lang.NonNull;

public class FlightSearchRequestDTO {
	@NonNull
	private String origin;
	@NonNull
	private String destination;
	
	@NonNull
	private String departureDate;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	
	

}
