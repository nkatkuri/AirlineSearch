package com.abn.entity;

public class SearchEntity {
	
	private Flight flight;
	

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}


	@Override
	public String toString() {
		return "SearchEntity [flight=" + flight + "]";
	}
	
}
