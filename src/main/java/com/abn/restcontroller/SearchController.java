package com.abn.restcontroller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

import com.abn.dto.FlightSearchRequestDto;
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

	@PostMapping("/availableFlights")
	public ResponseEntity<?> getFilteredFlights(@RequestBody FlightSearchRequestDto searchRequest) {
		logger.debug("** getFilteredFlights() - Execution started. **");
		List<FlightSearchResponseDto> filteredFlights = null;

		try {
			if(Objects.nonNull(searchRequest)) {
			String origin = searchRequest.getOrigin();
			String destination = searchRequest.getDestination();
			String departureDate = searchRequest.getDepartureDate();
			
			filteredFlights = flightService.getFilteredFlights(destination, origin, departureDate);
			return new ResponseEntity<>(filteredFlights, HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Invalid input data",HttpStatus.BAD_REQUEST);
			}
		}
		catch(Exception ex) {
			 logger.info("** getFilteredFlights() - Exception occured **");
			 return new ResponseEntity<>("Invalid input data", HttpStatus.BAD_REQUEST);
		}
		

	}


}
