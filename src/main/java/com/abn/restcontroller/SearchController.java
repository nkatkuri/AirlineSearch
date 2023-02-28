package com.abn.restcontroller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abn.dto.FlightSearchResponseDto;
import com.abn.entity.Flight;
import com.abn.entity.SearchEntity;
import com.abn.service.FlightService;


@RestController
@RequestMapping("/search")
public class SearchController {

	public static Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private FlightService flightService;
	
	@GetMapping(value = "/getFlight/{id}")
	public ResponseEntity<?> getFlight(@PathVariable("id") Integer flightId) {
		logger.debug("** getFlight() - Execution started. **");
		Flight theFlight = flightService.getFlight(flightId);
		if (theFlight != null)
			return new ResponseEntity<>(theFlight, HttpStatus.OK);
		logger.debug("** getFlight() - Execution completed. **");
		return new ResponseEntity<>("There is no flight with id: " + flightId + " in this route.",
				HttpStatus.NO_CONTENT);
	}

	@PostMapping("/availableFlights")
	public ResponseEntity<?> getFilteredFlights(@RequestBody Map<String, String> body) {
		logger.debug("** getFilteredFlights() - Execution started. **");
		List<FlightSearchResponseDto> filteredFlights = null;
		String origin = null;
		String destination = null;
		String departureDate = null;
		try {
			origin = body.get("origin");
			destination = body.get("destination");
			departureDate = body.get("departureDate");
			logger.info("** getFilteredFlights() - Data successfully parsed **");
		}
		catch(Exception ex) {
			logger.info("** getFilteredFlights() - Exception occured **");
		}
		
		filteredFlights = flightService.getFilteredFlights(destination, origin, departureDate);

		logger.debug("** getFilteredFlights() - Execution completed. **");
		return new ResponseEntity<>(filteredFlights, HttpStatus.OK);

	}


}
