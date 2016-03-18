package com.sap.sensor.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.sensor.model.Motor;
import com.sap.sensor.model.TemperatureSensor;

@Repository
public class MotorDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public Motor getMotorById(long id){
		Motor motor = em.find(Motor.class, id);
		return motor;
	}
	
	public Collection<Motor> getAllMotors(){

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Motor> criteria = cb.createQuery(Motor.class);
		Collection<Motor> resultList = em.createQuery(criteria).getResultList();
		return resultList;
	}
	
	/*
	 * Transactional methods
	 */
	
	@Transactional
	public Motor createMotor(Motor motor){
		em.persist(motor);
		return motor;
	}
	
	@Transactional
	public void deleteMotor(long id) {
		Motor motor = getMotorById(id);

		if (motor != null) {
			em.remove(motor);
		}
	}
	
	@Transactional
	public Motor updateMotor(long id, Motor newMotor){
		Motor motor= getMotorById(id);
		
		newMotor.setName(newMotor.getName());
		newMotor.setSensor(newMotor.getSensor());
		
		em.merge(motor);
		return motor;
	}
	
	@Transactional
	public Motor addSensorToMotor(long id, TemperatureSensor sensor){
		Motor motor= getMotorById(id);
		
		motor.setSensor(sensor);
		
		em.merge(motor);
		return motor;
	}
	
	

}
