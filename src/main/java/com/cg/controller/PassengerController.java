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

import com.cg.bean.Passenger;
import com.cg.service.PassengerService;

@RestController
@RequestMapping("/passenger")
@CrossOrigin(origins = "*")
public class PassengerController {
	@Autowired
	PassengerService passengerService;
	
	@GetMapping("/showAllPassengers")
	public List<Passenger> showAllPassenger() {
		List<Passenger> plist = passengerService.viewPassenger();
		return plist;
	}
	//git try commit void file
	@GetMapping("/showByPnrNumber/{pnr}")
	public Passenger showById(@PathVariable BigInteger pnr) {
		return passengerService.viewPassenger(pnr);
	}
	
	@GetMapping("/showByPassengerName/{name}")
	public List<Passenger> showByPassengerName(@PathVariable String name) {
		return passengerService.viewPassengerByName(name);
	}
	
	@GetMapping("/showByPassengerUIN/{uin}")
	public List<Passenger> showByPassengeUIN(@PathVariable BigInteger uin) {
		return passengerService.viewPassengerByUIN(uin);
	}
	
	@GetMapping("/showByBookingId/{id}")
	public List<Passenger> showByBookingId(@PathVariable BigInteger id) {
		return passengerService.viewPassengerByBookingId(id);
	}
	
	@PostMapping("/addPassenger/{bookingId}")
	public Passenger addPassenger(@PathVariable BigInteger bookingId, @RequestBody Passenger passenger) {
		passengerService.validatePassenger(passenger);
		return passengerService.addPassenger(bookingId, passenger);
	}
	
	@PutMapping("/modifyPassenger/{pnr}")
	public Passenger modifyPassenger(@PathVariable BigInteger pnr, @RequestBody Passenger passenger) {
		passengerService.validatePassenger(passenger);
		return passengerService.modifyPassenger(pnr, passenger);
	}
	
	@DeleteMapping("/deletePassenger/{pnr}")
	public void deletePassenger(@PathVariable BigInteger pnr) {
		passengerService.deletePassenger(pnr);
	}
	
	@PatchMapping("/patchPassenger/{pnr}")
	public Passenger patchPassenger(@PathVariable BigInteger pnr, @RequestBody Passenger passenger) {
		return passengerService.patchPassenger(pnr, passenger);
	}
}
