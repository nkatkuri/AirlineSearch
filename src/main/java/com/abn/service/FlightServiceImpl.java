package com.abn.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abn.dto.FlightSearchResponseDto;
import com.abn.entity.Flight;
import com.abn.exception.InvalidDateFormatException;
import com.abn.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	public static Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

	@Autowired
	private FlightRepository flightRepository;
			

	@Override
	public List<Flight> getAllFlights() {
		List<Flight> listOfFlights = new ArrayList<>();
		listOfFlights = flightRepository.findAll();
		return listOfFlights;
	}

	@Override
	public List<FlightSearchResponseDto> getFilteredFlights(String destination, String source, String departureDate) {
		logger.debug("** getFilteredFlights() - Execution started. **");
		LocalDate ldate = null;
		List<Flight> listOfFilteredFlights = null;
		List<FlightSearchResponseDto> resultSet = new ArrayList<>();
		try {
			ldate = LocalDate.parse(departureDate);
			logger.info("** getFilteredFlights() - Date successfully parsed. **");
		} catch (DateTimeParseException dtpe) {
			logger.info("** getFilteredFlights() - Date was not parsed successfully -> " + dtpe.getMessage() + " **");
			throw new InvalidDateFormatException("Entered date is invalid, please enter in YYYY-MM-DD format.");
		}
		catch (Exception ex) {
			logger.info("** getFilteredFlights() - Date was not parsed successfully -> " + ex.getMessage() + " **");
		}
		try {
			listOfFilteredFlights = flightRepository
					.findByDestinationIgnoreCaseAndOriginIgnoreCaseAndDepartureDate(destination, source, ldate);
			logger.info("** getFilteredFlights() - Retrieved listOfFilteredFlights. **");
		} catch (Exception e) {
			logger.info("** getFilteredFlights() - An exception occured while retrieving the list from db -> "
					+ e.getMessage() + " **");

		}
		if(Objects.nonNull(listOfFilteredFlights)) {
			resultSet=	listOfFilteredFlights.stream()
					.filter((Flight flight) -> Objects.nonNull(flight))
					.map(flight->mapFlight(flight)).collect(Collectors.toList());
		}

		logger.debug("** getFilteredFlights() - Execution completed. **");
		return resultSet;
	}

	private FlightSearchResponseDto mapFlight(Flight flight) {
		FlightSearchResponseDto responseDto= new FlightSearchResponseDto();
		
		responseDto.setFlightNumber(flight.getFlightName());
		responseDto.setOrigin(flight.getOrigin());
		responseDto.setDestination(flight.getDestination());
		responseDto.setDestinationTime(flight.getDepartureTime());
		responseDto.setArrivalTime(flight.getArrivalTime());
		responseDto.setFare(flight.getFare().toString());
		return responseDto;
	}

	@Override
	public Flight getFlight(Integer flightId) {
		logger.debug("** getFlight() - Execution started. **");
		Optional<Flight> optionalFlight = flightRepository.findById(flightId);
		if (optionalFlight.isPresent()) {
			return optionalFlight.get();
		}
		logger.debug("** getFlight() - Execution completed. **");
		return null;
	}

}
