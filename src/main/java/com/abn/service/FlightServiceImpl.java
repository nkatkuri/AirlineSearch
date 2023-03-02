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
import com.abn.exception.NoDataFoundException;
import com.abn.exceptionhandler.FlightSearchException;
import com.abn.repository.FlightRepository;

/**
 * 
 * @author nkatkuri
 * This class defined for all the business logic
 */
@Service
public class FlightServiceImpl implements FlightService {

	private static Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

	@Autowired
	private FlightRepository flightRepository;
	
	
    /**
     * This method is used retrieve the flights form back end 
     * and sorting based on request parameter
     *  
     */
	
	@Override
	public List<FlightSearchResponseDTO> getFilteredFlights(@Valid String destination, @Valid String origin,
			LocalDate departureDate, String sortColumn, String sortType) throws FlightSearchException {
		 
		logger.debug("** getFilteredFlights() - Execution Started. **");

		List<FlightSearchResponseDTO> resultSet = new ArrayList<>();
		
		List<Flight> listOfFilteredFlights = flightRepository
				.findByDestinationIgnoreCaseAndOriginIgnoreCaseAndDepartureDate(destination, origin,departureDate);
		
		/**
		 * sort by price or duration
		 */
		if (Objects.nonNull(sortColumn)) {
			if (sortColumn.equals("price")) {// sort the flights by price and ascending order
				listOfFilteredFlights.sort((h1, h2) -> h1.getFare().compareTo(Double.valueOf(sortColumn)));
				sortByType(sortType, listOfFilteredFlights);

			} else {// sort by departure date with descending type
				listOfFilteredFlights.sort((h1, h2) -> h1.getDepartureDate().compareTo(departureDate));

				sortByType(sortType, listOfFilteredFlights);

			}
		}

		/**
		 * Map the values with response dto
		 */

		if (Objects.nonNull(listOfFilteredFlights)) {
			resultSet = listOfFilteredFlights.stream().filter((Flight flight) -> Objects.nonNull(flight))
					.map(flight -> mapFlightData(flight)).collect(Collectors.toList());
		}

		/**
		 * Exception handled
		 */
		if (Objects.nonNull(resultSet) && resultSet.isEmpty()) {
			throw new NoDataFoundException(
					"No Data Found for given origin=" + origin + " and destination=" + destination);
		}

		logger.debug("** getFilteredFlights() - Execution completed. **");
		return resultSet;

	}
	
	//This method is sorting the result based on type

	private void sortByType(String sortType, List<Flight> listOfFilteredFlights) {
		if (Objects.nonNull(sortType) && sortType.equals("desc")) {
			Collections.reverse(listOfFilteredFlights);
		}
	}

	//This method is used to map response dto
	private FlightSearchResponseDTO mapFlightData(Flight flight) {
		
		logger.debug("** mapFlightData- Execution started. **");
		FlightSearchResponseDTO responseDto = new FlightSearchResponseDTO();

		responseDto.setFlightNumber(flight.getFlightName());
		responseDto.setOrigin(flight.getOrigin());
		responseDto.setDestination(flight.getDestination());
		responseDto.setDestinationTime(flight.getDepartureTime());
		responseDto.setArrivalTime(flight.getArrivalTime());
		responseDto.setFare(flight.getFare().toString());
		logger.debug("** mapFlightData- Execution completed. **");
		return responseDto;
		
		
	}

}
