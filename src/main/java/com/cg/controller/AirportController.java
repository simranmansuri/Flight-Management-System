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
import com.cg.bean.Airport;
import com.cg.service.AirportService;

@RestController
@RequestMapping("/airport")
@CrossOrigin(origins = "*")
public class AirportController {
	@Autowired
	AirportService airportService;
	
	/*
	URI :  http://localhost:9001/airport/showAllAirports
	METHOD : GET
	 */
	@GetMapping("/showAllAirports")
	public List<Airport> showAllAirport() {
		List<Airport> alist = airportService.viewAirport();
		return alist;
	}
	
	/*
	URI : http://localhost:9001/airport/showById/106 || 108
	METHOD : GET
	 */
	
	@GetMapping("/showById/{code}")
	public Airport showById(@PathVariable BigInteger code){
		return airportService.viewAirport(code);
	}
	
	@GetMapping("/showByName/{name}")
	public Airport showByName(@PathVariable String name) {
		return airportService.viewAirportByName(name);
	}
	
	@GetMapping("/showByLocation/{location}")
	public List<Airport> showByLocation(@PathVariable String location) {
		return airportService.viewAirportByLocation(location);
	}
	
	@PostMapping("/addAirport")
	public Airport addAirport(@RequestBody Airport airport) {
		return airportService.addAirport(airport);
	}
	
	@PutMapping("/modifyAirport/{code}")
	public Airport modifyAirport(@PathVariable BigInteger code, @RequestBody Airport airport) {
		return airportService.modifyAirport(code, airport);
	}
	
	@DeleteMapping("/deleteAirport/{code}")
	public void deleteAirport(@PathVariable BigInteger code) {
		airportService.deleteAirport(code);
	}
	
	@PatchMapping("/patchAirport/{code}")
	public Airport patchAirport(@PathVariable BigInteger code, @RequestBody Airport airport) {
		return airportService.patchAirport(code, airport);
	}
}
