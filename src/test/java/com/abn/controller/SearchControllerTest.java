package com.abn.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.hibernate.mapping.Array;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.abn.dto.FlightSearchResponseDTO;
import com.abn.restcontroller.SearchController;
import com.abn.service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
class SearchControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FlightService flightService;

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

	@Test
	void GetFlightsTestForBadRequest() throws Exception {

		List<FlightSearchResponseDTO> flightData = new ArrayList<FlightSearchResponseDTO>();

		Mockito.when(flightService.getFilteredFlights("", "", LocalDate.now(), "", null)).thenReturn(flightData);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flights/").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	void GetFlightsTestForNotFound() throws Exception {

		List<FlightSearchResponseDTO> flightData = new ArrayList<FlightSearchResponseDTO>();

		Mockito.when(flightService.getFilteredFlights("", "", LocalDate.now(), "", null)).thenReturn(flightData);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flights").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(404, result.getResponse().getStatus());
	}

	
}
