package com.cg.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.cg.bean.ScheduledFlight;
import com.cg.service.ScheduledFlightService;

@RestController
@RequestMapping("/scheduledflight")
@CrossOrigin(origins = "*")
public class ScheduledFlightController {

	@Autowired
	ScheduledFlightService scheduledFlightService;
	
	/*
	 URI : http://localhost:9001/scheduledflight/showAllSchFlights
	 METHOD : GET
	 */
	@GetMapping("/showAllSchFlights")
	public List<ScheduledFlight> showAllSchFlights()
	{
		return scheduledFlightService.viewScheduledFlight();
	}
	
	/*
	 URI : http://localhost:9001/scheduledflight/addSchFlight
	 METHOD : POST	 
	 */
	@PostMapping("/addSchFlight")
	public ScheduledFlight addSchFlights(@RequestBody ScheduledFlight newScheduledFlight)
	{
		scheduledFlightService.validateScheduledFlight(newScheduledFlight);
		return scheduledFlightService.scheduleFlight(newScheduledFlight);
	}
	
	/*
	 URI : http://localhost:9001/scheduledflight/showByFno/10001
	 METHOD : GET
	 */
	@GetMapping("/showByFno/{fno}")
	public List<ScheduledFlight> showByFlightNo(@PathVariable BigInteger fno)
	{
		
		return scheduledFlightService.viewScheduledFlights(fno);
	}
	
	/*
	 URI : http://localhost:9001/scheduledflight/modifySchFlight
	 METHOD : PUT
	 */
	@PutMapping("/modifySchFlight/{sfid}")
	public ScheduledFlight modifySchFlight(@PathVariable BigInteger sfid,@RequestBody ScheduledFlight newScheduledFlight)
	{
		scheduledFlightService.validateScheduledFlight(newScheduledFlight);
		return scheduledFlightService.modifyScheduledFlight(sfid,newScheduledFlight);
	}
	
	/*
	 URI : http://localhost:9001/scheduledflight/deleteSchFlight/100
	 METHOD : DELETE
	 */
	@DeleteMapping("/deleteSchFlight/{sfid}")
	public void deleteSchFlight(@PathVariable BigInteger sfid)
	{
		
		scheduledFlightService.deleteScheduledFlight(sfid);
	}
	
	/*
	 URI : http://localhost:9001/scheduledflight/showByAirport/Bangalore/Mumbai/2022-10-29
	 METHOD : GET
	 */
	@GetMapping("/showByAirport/{src}/{dsc}/{date}")
	public List<ScheduledFlight> showByAirport(@PathVariable String src,@PathVariable String dsc,@PathVariable String date)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(date, formatter);
		return scheduledFlightService.viewScheduledFlights(src,dsc,localDate);
	}
	
	@PatchMapping("/patchScheduledFlight/{code}")
	public ScheduledFlight patchScheduledFlight(@PathVariable BigInteger code, @RequestBody ScheduledFlight scheduledFlight) {
		return scheduledFlightService.patchScheduledFlight(code, scheduledFlight);
	}
	
	
}





/* POST REQUEST BODY
{
	  "availableSeats": 90,
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
	    "sid": 1,
	    "sourceAirport": {
	      "airportCode": "103",
	      "airportLocation": "Bangalore",
	      "airportName": "Kempegowda International Airport"
	    }
	  },
	  "sfid": 1
	}
*/	

/* UPDATE REQUEST BODY
{
  "availableSeats": 80,
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
      "airportLocation": "Bangalore",
      "airportName": "Kempegowda International Airport"
    }
  },
  "sfid": 100
}
*/
