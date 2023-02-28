package com.abn.service;

import java.util.List;

import com.abn.dto.FlightSearchResponseDto;
import com.abn.entity.Flight;
import com.abn.entity.SearchEntity;

public interface FlightService {
	
		
	public List<FlightSearchResponseDto> getFilteredFlights(String destination, String source, String departureDate);
		
}
