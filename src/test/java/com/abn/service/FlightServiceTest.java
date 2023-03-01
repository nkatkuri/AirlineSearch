package com.abn.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.abn.dto.FlightSearchResponseDTO;
import com.abn.entity.Flight;
import com.abn.repository.FlightRepository;

class FlightServiceTest {

	@InjectMocks
	private FlightRepository flightRepository;

	@InjectMocks
	private FlightService flightService;

	private Flight flight;

	@Ignore
	void FlightserviceResponseTest() {

		flight = new Flight();
		flight.setId(1);
		flight.setOrigin("AMS");
		flight.setDestination("DEL");
		flight.setDepartureDate(LocalDate.now());
		flight.setFare(Double.valueOf("800"));

		Mockito.when(flightRepository.findByDestinationIgnoreCaseAndOriginIgnoreCase("AMS", "DEL"))
				.thenReturn(List.of(flight));

		List<FlightSearchResponseDTO> flightSeacrhList = flightService.getFilteredFlights("AMS", "DEL", LocalDate.now(),
				"850");
		assertThat(flightSeacrhList).isNotNull();
		assertThat(flightSeacrhList.size()).isEqualTo(1);
	}

}
