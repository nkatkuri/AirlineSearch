package com.abn.service;

import java.time.LocalDate;
import java.util.List;

import com.abn.dto.FlightSearchResponseDTO;
import com.abn.exceptionhandler.FlightSearchException;
/**
 * 
 * @author nkatkuri
 *
 */

public interface FlightService {

	public List<FlightSearchResponseDTO> getFilteredFlights(String destination, String origin, LocalDate departureDate,
			String sortColumn, String sortType) throws FlightSearchException;

}
