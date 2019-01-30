 package com.cg.test.accountservice;

import org.junit.Assert;
import org.junit.Test;

import com.cg.test.accountservice.Reservation;
 
public class ReservationPOJOTest {
	
	@Test
	public void testShouldConstruct() {
		Reservation reservation =new Reservation("1","Deep");
		Assert.assertEquals("1",reservation.getId());
	}

}
 