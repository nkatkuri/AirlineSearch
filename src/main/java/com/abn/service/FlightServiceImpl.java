package com.abn.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.abn.dto.FlightSearchResponseDTO;
import com.abn.entity.Flight;
import com.abn.exception.InvalidDateFormatException;
import com.abn.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	private static Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

	@Autowired
	private FlightRepository flightRepository;
			
	

	

	@Override
	public List<FlightSearchResponseDTO> getFilteredFlights(String destination, @Valid String origin,
			LocalDate departureDate,String price) {
		// TODO Auto-generated method stub
		
		List<Flight> listOfFilteredFlights = null;
		List<FlightSearchResponseDTO> resultSet = new ArrayList<>();
				
		listOfFilteredFlights = flightRepository.findByDestinationIgnoreCaseAndOriginIgnoreCase(destination, origin);
		
		/**
		 * sort the flights by price
		 */
		        if(Objects.nonNull(price))
		        {
	    		listOfFilteredFlights.sort((h1, h2) -> h1.getFare().compareTo(Double.valueOf(price)));
		        }
		
		/**
		 * sort the flights by duration
		 */
				if(Objects.nonNull(departureDate)) {
	    		listOfFilteredFlights.sort((h1, h2) -> h1.getDepartureDate().compareTo(departureDate));
	    
				}
	    		
	    		if(Objects.nonNull(listOfFilteredFlights)) {
	    			resultSet=	listOfFilteredFlights.stream()
	    					.filter((Flight flight) -> Objects.nonNull(flight))
	    					.map(flight->mapFlightData(flight)).collect(Collectors.toList());
	    		}

	    		logger.debug("** getFilteredFlights() - Execution completed. **");
	    		return resultSet;
						
	}
	
	
	private FlightSearchResponseDTO mapFlightData(Flight flight) {
		FlightSearchResponseDTO responseDto= new FlightSearchResponseDTO();
		
		responseDto.setFlightNumber(flight.getFlightName());
		responseDto.setOrigin(flight.getOrigin());
		responseDto.setDestination(flight.getDestination());
		responseDto.setDestinationTime(flight.getDepartureTime());
		responseDto.setArrivalTime(flight.getArrivalTime());
		responseDto.setFare(flight.getFare().toString());
		return responseDto;
	}

}
