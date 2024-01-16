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
import com.cg.bean.Booking;
import com.cg.bean.Passenger;
import com.cg.service.BookingService;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "*")
public class BookingController 
{

	@Autowired
	BookingService bookingService;

	
	/*
	 * 
	 URI : http://localhost:9001/booking/showAllBookings
	 METHOD : GET
	 */
	@GetMapping("/showAllBookings")
	public List<Booking> showAllBookings()
	{
		return bookingService.viewBooking();
	}
	
	/*
	 URI : http://localhost:9001/booking/addBooking
	 METHOD : POST
	 */
	@PostMapping("/addBooking")
	public Booking addBooking(@RequestBody Booking newBooking)
	{
		bookingService.validateBooking(newBooking);
		Booking b = bookingService.addBooking(newBooking);
		return b;
	}
	
	/* 
	 URI : http://localhost:9001/booking/showById/1000
	 METHOD : GET
	 */
	@GetMapping("/showById/{bookingId}")
	public Booking showById(@PathVariable BigInteger bookingId)
	{
		return bookingService.viewBooking(bookingId);
	}
	
	/*
	 URI : http://localhost:9001/booking/modifyBooking
	 METHOD : PUT
	 */
	@PutMapping("/modifyBooking/{bookingId}")
	public Booking updateBooking(@PathVariable BigInteger bookingId,@RequestBody Booking updateBooking)
	{
		bookingService.validateBooking(updateBooking);
		return bookingService.modifyBooking(bookingId,updateBooking);
	}
	
	/*
	 URI : http://localhost:9001/booking/deleteBooking/1000
	 METHOD : DELETE
	 */
	@DeleteMapping("/deleteBooking/{bookingId}")
	public void deleteBooking(@PathVariable BigInteger bookingId)
	{
		bookingService.deleteBooking(bookingId);
	}
	
	@GetMapping("/showBySfid/{sfid}")
	public List<Passenger> showBySfid(@PathVariable BigInteger sfid)
	{
		return bookingService.viewBookingBySfid(sfid);
	}
	
	@PatchMapping("/patchBooking/{bookingId}")
	public Booking patchBooking(@PathVariable BigInteger bookingId,@RequestBody Booking booking)
	{
		bookingService.validatePatchBooking(booking);
		return bookingService.patchBooking(bookingId,booking);
	}
}




/*POST REQUEST BODY
{
"bookingDate": "2022-10-28 00:00:00",
"bookingId": 1,
"flight": {
  "availableSeats": 70,
  "flight": {
    "carrierName": "Indigo",
    "flightModel": "Jet",
    "flightNumber": 10001,
    "seatCapacity": 100
  },
  "schedule": {
    "arrivalTime": "2022-10-29 16:10:00",
    "departureTime": "2022-10-29 14:10:00",
    "destinationAirport": {
      "airportCode": "102",
      "airportLocation": "Mumbai",
      "airportName": "Chhatrapati Shivaji International Airport"
    },
    "sid": 100,
    "sourceAirport": {
      "airportCode": "103",
      "airportLocation": "Kempegowda International Airport",
      "airportName": "Bangalore"
    }
  },
  "sfid": 100
},
"noOfPassengers": 1,
"passengerList": [
  {
    "luggage": 12.0,
    "passengerAge": 22,
    "passengerName": "Hemant",
    "passengerUIN": 123456789012,
    "pnrNumber": 1
  }
],
"ticketCost": 123,
"user": {
  "email": "lakshya@gmail.com",
  "id": 15,
  "userName": "Lakshya",
  "userPassword": "lakshya",
  "userPhone": 1234567894,
  "userType": "customer"
}
}
*/


/*UPDATE REQUEST BODY
{
"bookingDate": "2022-10-28 00:00:00",
"bookingId": 1000,
"flight": {
  "availableSeats": 70,
  "flight": {
    "carrierName": "Indigo",
    "flightModel": "Jet",
    "flightNumber": 10001,
    "seatCapacity": 100
  },
  "schedule": {
    "arrivalTime": "2022-10-29 16:10:00",
    "departureTime": "2022-10-29 14:10:00",
    "destinationAirport": {
      "airportCode": "102",
      "airportLocation": "Mumbai",
      "airportName": "Chhatrapati Shivaji International Airport"
    },
    "sid": 100,
    "sourceAirport": {
      "airportCode": "103",
      "airportLocation": "Kempegowda International Airport",
      "airportName": "Bangalore"
    }
  },
  "sfid": 100
},
"noOfPassengers": 2,
"passengerList": [
  {
    "luggage": 12.0,
    "passengerAge": 22,
    "passengerName": "Hemant",
    "passengerUIN": 123456789012,
    "pnrNumber": 1000
  },
{
    "luggage": 10.0,
    "passengerAge": 22,
    "passengerName": "Ishika",
    "passengerUIN": 123456789013,
    "pnrNumber": 1
  }
],
"ticketCost": 123,
"user": {
  "email": "lakshya@gmail.com",
  "id": 15,
  "userName": "Lakshya",
  "userPassword": "lakshya",
  "userPhone": 1234567894,
  "userType": "customer"
}
}
*/