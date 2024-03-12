package com.LeanPlatformTask.LPT.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import jakarta.persistence.Id;
@Entity
@Table(name="slot")
public class SlotModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endtime;
	private Long consultantId;
	private boolean booked;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndtime() {
		return endtime;
	}
	public void setEndtime(LocalTime endtime) {
		this.endtime = endtime;
	}
	public Long getConsultantId() {
		return consultantId;
	}
	public void  setConsultantId(Long consultantId) {
		this.consultantId = consultantId;
	}
	public boolean isBooked() {
		return booked;
	}
	public void setBooked(boolean booked) {
		this.booked = booked;
	}
	
	
}
