package com.sap.sensor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Motor {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToOne
	@JoinColumn(nullable = true)
	private TemperatureSensor sensor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TemperatureSensor getSensor() {
		return sensor;
	}

	public void setSensor(TemperatureSensor sensor) {
		this.sensor = sensor;
	}
	
}
