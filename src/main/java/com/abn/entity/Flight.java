package com.abn.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 
 * @author nkatkuri
 * Table for flight details
 */
@Entity
@Table(name = "flight")
public class Flight {

	// Defining attributes.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "flight_name")
	private String flightName;
	@Column(name = "origin")
	private String origin;
	@Column(name = "destination")
	private String destination;
	@Column(name = "arrival_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate arrivalDate;
	@Column(name = "departure_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate departureDate;
	@Column(name = "departure_time")
	private LocalTime departureTime;
	@Column(name = "arrival_time")
	private LocalTime arrivalTime;
		
	@Column(name = "fare")
	private Double fare;
	// Default Constructor.
	public Flight() {

	}

	// Getters and Setters.
	public Integer getId() {
		return id;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String fname) {
		this.flightName = fname;
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

	
	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightName=" + flightName + ", origin=" + origin + ", destination=" + destination
				+ ", arrivalDate=" + arrivalDate + ", departureDate=" + departureDate + ", departureTime="
				+ departureTime + ",fare='" +fare+ ", arrivalTime=" + arrivalTime + "]";
	}
	
}
