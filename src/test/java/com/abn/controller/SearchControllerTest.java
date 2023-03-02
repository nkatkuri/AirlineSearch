package com.abn.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.abn.dto.FlightSearchResponseDTO;
import com.abn.restcontroller.SearchController;
import com.abn.service.FlightService;

/**
 * 
 * @author nkatkuri
 * This is controller test 
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
class SearchControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FlightService flightService;

	/**
	 * This is for successful data retrieval validation
	 * @throws Exception 
	 */
	
	@Test
	void GetFlightsTestForSuccessfulRetrieval() throws Exception {

		List<FlightSearchResponseDTO> flightData = new ArrayList<FlightSearchResponseDTO>();

		Mockito.when(flightService.getFilteredFlights("", "", LocalDate.now(), "", "")).thenReturn(flightData);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/flights/?origin=AMS&destination=DEL&departureDate=2023-01-24&sortColumn=departureDate&sortType=Desc")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(200, result.getResponse().getStatus());
	}
	
	/**
	 * This is for bad request data validation
	 * @throws Exception
	 */

	@Test
	void GetFlightsTestForBadRequest() throws Exception {

		List<FlightSearchResponseDTO> flightData = new ArrayList<FlightSearchResponseDTO>();

		Mockito.when(flightService.getFilteredFlights("", "", LocalDate.now(), "", null)).thenReturn(flightData);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flights/").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(400, result.getResponse().getStatus());
	}
	
	/**
	 * This is for no data found validation
	 * @throws Exception
	 */

	@Test
	void GetFlightsTestForNotFound() throws Exception {

		List<FlightSearchResponseDTO> flightData = new ArrayList<FlightSearchResponseDTO>();

		Mockito.when(flightService.getFilteredFlights("", "", LocalDate.now(), "", null)).thenReturn(flightData);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flights").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(404, result.getResponse().getStatus());
	}

	
}
