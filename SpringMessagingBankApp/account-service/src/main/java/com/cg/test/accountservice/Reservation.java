package com.cg.test.accountservice;

public class Reservation {
	
	private String id;
	private String reservationName;
	
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reservation(String id, String reservationName) {
		super();
		this.id = id;
		this.reservationName = reservationName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationName=" + reservationName + "]";
	}
}
