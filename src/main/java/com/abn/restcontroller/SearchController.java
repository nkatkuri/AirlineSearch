package com.abn.restcontroller;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abn.dto.FlightSearchResponseDTO;
import com.abn.service.FlightService;

@RestController
@RequestMapping("/flights")
public class SearchController {

	public static Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private FlightService flightService;

	@GetMapping("/")
	public ResponseEntity<?> getSearchFlights(@RequestParam String origin, @RequestParam String destination,
			@RequestParam(value = "departureDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
			@RequestParam(value = "sortColumn", required = false) String sortColumn,
			@RequestParam(value = "sortType", required = false) String sortType) throws Exception {

		List<FlightSearchResponseDTO> filteredFlights = null;

		filteredFlights = flightService.getFilteredFlights(destination, origin, departureDate, sortColumn, sortType);
		return new ResponseEntity<>(filteredFlights, HttpStatus.OK);
	}

}
