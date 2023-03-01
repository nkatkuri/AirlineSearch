package com.abn.restcontroller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abn.dto.FlightSearchRequestDTO;
import com.abn.dto.FlightSearchResponseDTO;
import com.abn.entity.Flight;
import com.abn.entity.SearchEntity;
import com.abn.service.FlightService;


@RestController
@RequestMapping("/flights")
public class SearchController {

	public static Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private FlightService flightService;		
	
	@GetMapping("/")
	public ResponseEntity<?> getSearchFlights( @RequestParam String origin,@RequestParam  String destination,
			@RequestParam (value="departureDate", required=false) 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	LocalDate departureDate,
	@RequestParam (value ="price",required=false) String price) throws Exception{
		
		List<FlightSearchResponseDTO> filteredFlights = null;
		
		filteredFlights = flightService.getFilteredFlights(destination, origin, departureDate,price);
		
		return new ResponseEntity<>(filteredFlights, HttpStatus.OK);		
	}


}
