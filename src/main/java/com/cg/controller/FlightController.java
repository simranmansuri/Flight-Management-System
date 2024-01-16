
package com.cg.controller;

import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.bean.Flight;
import com.cg.service.FlightService;



@RestController 
@RequestMapping("/flight")
@CrossOrigin(origins = "*")
public class FlightController {

	@Autowired
	FlightService flightService;
	
	/*
	 URI : http://localhost:9001/flight/showAllFlights
	 METHOD : GET
	 */
	@GetMapping("/showAllFlights")
	public List<Flight> showAllFlights()
	{
		return flightService.viewFlight();
	}
	
	/*
	 URI : http://localhost:9001/flight/addFlight
	 METHOD : POST
	 {
  		"flightNumber": 10000,
  		"carrierName": "Go Air",
  		"flightModel": "Boeing",
  		"seatCapacity": 200
	}
	 */
	@PostMapping ("/addFlight")
	public Flight addFlight(@RequestBody Flight newFlight) {
		return flightService.addFlight(newFlight);
	}
	
	/*
	 URI : http://localhost:9001/flight/showById/1001
	 METHOD : GET
	 */
	@GetMapping("/showById/{fno}")
	public Flight showById(@PathVariable BigInteger fno) 
	{
		return flightService.viewFlight(fno);
	}
	
	/*
	 URI : http://localhost:9001/flight/modifyFlight
	 METHOD : PUT
	 {
  		"flightNumber": 1003,
  		"carrierName": "Air India",
  		"flightModel": "Jet",
  		"seatCapacity": 120
	}
	 */
	@PutMapping("/modifyFlight/{fno}")
	public Flight modifyFlight(@PathVariable BigInteger fno, @RequestBody Flight  modifyFlight)
	{
		return flightService.modifyFlight(fno, modifyFlight);
	}
	
	/*
	 URI : http://localhost:9001/flight/deleteFlight/1003
	 METHOD : DELETE
	 */
	@DeleteMapping("/deleteFlight/{fno}")
	public void deleteFlight(@PathVariable BigInteger fno)
	{
		flightService.deleteFlight(fno);
	}
	
	@GetMapping("/showByCarrierName/{CarrierName}")
	public List<Flight> showByCarrierName(@PathVariable String CarrierName) 
	{
		return flightService.viewFlightByCarrierName(CarrierName);
	}
	
	@PatchMapping("/patchFlight/{fno}")
	public Flight patchFlight(@PathVariable BigInteger fno, @RequestBody Flight  modifyFlight)
	{
		return flightService.patchFlight(fno, modifyFlight);
	}
	
}
