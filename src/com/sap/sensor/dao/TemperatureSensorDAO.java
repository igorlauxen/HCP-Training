package com.sap.sensor.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.sensor.model.TemperatureSensor;

@Repository
public class TemperatureSensorDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public Collection<TemperatureSensor> getAllTemperatureSensors(){

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TemperatureSensor> criteria = cb.createQuery(TemperatureSensor.class);
		Collection<TemperatureSensor> resultList = em.createQuery(criteria).getResultList();
		return resultList;
	}
	
	public TemperatureSensor getTemperatureSensorById(long id){
		TemperatureSensor TemperatureSensor = em.find(TemperatureSensor.class, id);
		return TemperatureSensor;
	}
	
	/*
	 * Transactional methods
	 */
	
	@Transactional
	public TemperatureSensor createTemperatureSensor(TemperatureSensor TemperatureSensor){
		em.persist(TemperatureSensor);
		return TemperatureSensor;
	}
	
	@Transactional
	public void deleteTemperatureSensor(long id) {
		TemperatureSensor TemperatureSensor = getTemperatureSensorById(id);

		if (TemperatureSensor != null) {
			em.remove(TemperatureSensor);
		}
	}
	
	@Transactional
	public TemperatureSensor updateTemperatureSensor(long id, TemperatureSensor newTemperature){
		TemperatureSensor temperature = getTemperatureSensorById(id);
		
		temperature.setName(newTemperature.getName());
		temperature.setTemperature(newTemperature.getTemperature());
		
		em.merge(temperature);
		return temperature;
	}
	
	@Transactional
	public TemperatureSensor randomTemperature(long id) {
		TemperatureSensor temperature = getTemperatureSensorById(id);
		
		temperature.setTemperature( -100 + (int) (Math.random() * (500 - (-100))+1 ));
		em.merge(temperature);
		
		return temperature;
	}

}
