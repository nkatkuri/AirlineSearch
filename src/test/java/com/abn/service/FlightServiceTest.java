package com.abn.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.abn.dto.FlightSearchResponseDTO;
import com.abn.entity.Flight;
import com.abn.exceptionhandler.FlightSearchException;
import com.abn.repository.FlightRepository;
import com.abn.restcontroller.SearchController;

@RunWith(SpringRunner.class)
@DataJpaTest
class FlightServiceTest {

	@MockBean
	private FlightRepository flightRepository;

	@Mock
	private FlightService flightService;

	private Flight flight;

	@Before
	public void setUp() {
	
	}

	@Test
	void FlightserviceResponseTest() throws FlightSearchException {

		flight = new Flight();
		flight.setId(1);
		flight.setOrigin("AMS");
		flight.setDestination("DEL");
		flight.setDepartureDate(LocalDate.now());
		flight.setFare(Double.valueOf("800"));

		List<FlightSearchResponseDTO> flightSeacrhList = new ArrayList<>();
		flightSeacrhList.add(buildresponse());

		Mockito.when(flightRepository.findByDestinationIgnoreCaseAndOriginIgnoreCaseAndDepartureDate("AMS", "DEL",LocalDate.now()))
				.thenReturn(List.of(flight));

		assertThat(flightSeacrhList).isNotNull();
		assertThat(flightSeacrhList.size()).isEqualTo(1);
		assertEquals("AMS", flightSeacrhList.get(0).getOrigin());
		assertEquals("DEL", flightSeacrhList.get(0).getDestination());
		assertNotNull(flightSeacrhList.get(0).getFare());
	}

	private FlightSearchResponseDTO buildresponse() {
		// TODO Auto-generated method stub

		FlightSearchResponseDTO response = new FlightSearchResponseDTO();
		response.setOrigin("AMS");
		response.setDestination("DEL");
		response.setFare("800.00");
		return response;
	}

}
