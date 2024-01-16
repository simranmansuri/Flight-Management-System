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

import com.cg.bean.Schedule;
import com.cg.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
@CrossOrigin(origins = "*")
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;
	
	@GetMapping("/showAllSchedules")
	public List<Schedule> showAllSchedule() {
		List<Schedule> slist = scheduleService.viewSchedule();
		return slist;
	}
	
	@GetMapping("/showById/{sid}")
	public Schedule showById(@PathVariable BigInteger sid) {
		return scheduleService.viewSchedule(sid);
	}
	
	@GetMapping("/showBySourceName/{srcName}")
	public List<Schedule> showBySourceName(@PathVariable String srcName) {
		return scheduleService.viewScheduleBySourceName(srcName);
	}
	
	@GetMapping("/showByDestinationName/{dstName}")
	public List<Schedule> showByDestinationName(@PathVariable String dstName) {
		return scheduleService.viewScheduleByDestinationName(dstName);
	}
	
	@GetMapping("/showByArrivalDate/{arrival}")
	public List<Schedule> showByArrivalDate(@PathVariable String arrival) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(arrival, formatter);
		return scheduleService.viewScheduleByArrivalDate(localDate);
	}
	
	@GetMapping("/showByDepartureDate/{departure}")
	public List<Schedule> showByDepartureDate(@PathVariable String departure) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(departure, formatter);
		return scheduleService.viewScheduleByDepartureDate(localDate);
	}
	
	@PostMapping("/addSchedule")
	public Schedule addSchedule(@RequestBody Schedule schedule) {
		scheduleService.validateSchedule(schedule);
		return scheduleService.addSchedule(schedule);
	}
	
	@PutMapping("/modifySchedule/{sid}")
	public Schedule modifySchedule(@PathVariable BigInteger sid, @RequestBody Schedule schedule) {
		scheduleService.validateSchedule(schedule);
		return scheduleService.modifySchedule(sid, schedule);
	}
	
	@DeleteMapping("/deleteSchedule/{sid}")
	public void deleteSchedule(@PathVariable BigInteger sid) {
		scheduleService.deleteSchedule(sid);
	}
	
	@PatchMapping("/patchSchedule/{sid}")
	public Schedule patchSchedule(@PathVariable BigInteger sid, @RequestBody Schedule schedule) {
		return scheduleService.patchSchedule(sid, schedule);
	}
}
