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

import com.sap.sensor.dao.MotorDAO;
import com.sap.sensor.dao.TemperatureSensorDAO;
import com.sap.sensor.model.Motor;

@Controller
@RequestMapping("/motors")
public class MotorController {
	
	@Autowired
	private MotorDAO MotorDao;
	
	@Autowired
	private TemperatureSensorDAO sensorDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<Motor> findAll() {
		return MotorDao.getAllMotors();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Motor findById(@PathVariable long id) {
		return MotorDao.getMotorById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Motor create(@RequestBody Motor motor) {
		return MotorDao.createMotor(motor);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody void delete(@PathVariable long id) {
		MotorDao.deleteMotor(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Motor updateMotor(@PathVariable long id, @RequestBody Motor motor) {
		return MotorDao.updateMotor(id, motor);
	}
	
	@RequestMapping(value = "/{id}/sensor/{sensorid}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Motor updateMotor(@PathVariable long id, @PathVariable long sensorid) {
		return MotorDao.addSensorToMotor(id, sensorDao.getTemperatureSensorById(sensorid));
	}
	
	

}
