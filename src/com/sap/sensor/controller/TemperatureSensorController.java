package com.sap.sensor.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sap.sensor.dao.TemperatureSensorDAO;
import com.sap.sensor.model.TemperatureSensor;

@Controller
@RequestMapping("/temperatureSensors")
public class TemperatureSensorController {
	
	@Autowired
	private TemperatureSensorDAO TemperatureSensorDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<TemperatureSensor> findAll() {
		return TemperatureSensorDao.getAllTemperatureSensors();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TemperatureSensor findById(@PathVariable long id) {
		return TemperatureSensorDao.getTemperatureSensorById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody TemperatureSensor create(@RequestBody TemperatureSensor temperatureSensor) {
		return TemperatureSensorDao.createTemperatureSensor(temperatureSensor);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody void delete(@PathVariable long id) {
		TemperatureSensorDao.deleteTemperatureSensor(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TemperatureSensor setOccupied(@PathVariable long id, @RequestBody TemperatureSensor temperatureSensor) {
		return TemperatureSensorDao.updateTemperatureSensor(id, temperatureSensor);
	}

}
