package com.cg.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bean.Airport;
import com.cg.dao.AirportDao;
import com.cg.exception.AirportNotFoundException;


@Service("airportService")
public class AirportServiceImpl implements AirportService {
	@Autowired
	AirportDao airportDao;
	
	@Override
	public List<Airport> viewAirport() {
		return airportDao.findAll();
	}
	
	@Override
	public Airport viewAirport(BigInteger code) {
		Optional<Airport> a = airportDao.findById(code);
		if (a.isEmpty()) {
			
			//throw exception if no airport found
			throw new AirportNotFoundException("No airport found with code "+code);
		}
		Airport airport = a.get();
		return airport;
	}

	@Override
	public Airport viewAirportByName(String name) {
		Airport a = airportDao.findByAirportName(name);
		if (a==null) {
			
			//throw exception if no airport found
			throw new AirportNotFoundException("No airport found with name "+name);
		}
		return a;
	}

	@Override
	public List<Airport> viewAirportByLocation(String location) {
		List<Airport> a = airportDao.findByAirportLocation(location);
		if (a.size()==0) {
			
			//throw exception if no airport found
			throw new AirportNotFoundException("No airport found with location "+location);
		}
		return a;
	}
	
	@Transactional
	@Override
	public Airport addAirport(Airport airport) {
		
		return airportDao.save(airport);
	}
	
	@Transactional
	@Override
	public Airport modifyAirport(BigInteger code, Airport airport) {
		Optional<Airport> a = airportDao.findById(code);
		if (a.isEmpty()) {
			
			//throw exception if no airport found
			throw new AirportNotFoundException("No airport found with code "+code);
		}
		Airport ap = a.get();
		ap.setAirportLocation(airport.getAirportLocation());
		ap.setAirportName(airport.getAirportName());
		return airportDao.save(ap);
	}

	@Transactional
	@Override
	public void deleteAirport(BigInteger code) {
		Optional<Airport> a = airportDao.findById(code);
		if (a.isEmpty()) {
			
			//throw exception if no airport found
			throw new AirportNotFoundException("No airport found with code "+code);
		}
		airportDao.deleteById(code);
	}
	
	@Transactional
	@Override
	public Airport patchAirport(BigInteger code, Airport airport) {
		Optional<Airport> a = airportDao.findById(code);
		if (a.isEmpty()) {
			
			//throw exception if no airport found
			throw new AirportNotFoundException("No airport found with code "+code);
		}
		Airport ap = a.get();
		if (!airport.getAirportLocation().equals("empty")) {
			ap.setAirportLocation(airport.getAirportLocation());
		}
		if (!airport.getAirportName().equals("empty")) {
			ap.setAirportName(airport.getAirportName());
		}
		return airportDao.save(ap);
	}

}
