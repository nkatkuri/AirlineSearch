package com.abn.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abn.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer>{

//	@Query(value = "SELECT * from FLIGHT f where f.destination = ?1 AND f.origin = ?2 AND f.departure_Date = ?3", nativeQuery = true)
	List<Flight> findByDestinationIgnoreCaseAndOriginIgnoreCaseAndDepartureDate(String destination, String origin, LocalDate departureDate);
		
}
