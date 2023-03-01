package com.abn.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightSearchResponseDTO {
	
	
	private String flightNumber;
	private String origin;
	private String destination;
	
	private LocalTime arrivalTime;
	
	private LocalTime destinationTime;
	private String fare;
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
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
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public LocalTime getDestinationTime() {
		return destinationTime;
	}
	public void setDestinationTime(LocalTime destinationTime) {
		this.destinationTime = destinationTime;
	}
	

}
