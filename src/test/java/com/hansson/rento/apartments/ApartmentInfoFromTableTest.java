package com.hansson.rento.apartments;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;


public class ApartmentInfoFromTableTest {

	@Test
	public void testCheckIsValidCityOrArea() {
		String[] validArea = {"TestOmråde", "Test Område"};
		ApartmentInfoFromTable handler = new ApartmentInfoFromTable();
		for(String area : validArea) {
			Assert.assertEquals(true, handler.checkIsValidCityOrArea(area));
		}
	}
	
	@Test
	public void testCheckIsInvalidCityOrArea() {
		String[] validArea = {"TestOmråde2", "asdasd.", "asdasd!", "asdasd?"};
		ApartmentInfoFromTable handler = new ApartmentInfoFromTable();
		for(String area : validArea) {
			Assert.assertEquals(false, handler.checkIsValidCityOrArea(area));
		}
	}

	@Test
	public void testCheckIsValidRent() {
		String[] validRent = {"1000 kr", "1000 SEK", "1000 SEK", "10 000", "10.000"};
		ApartmentInfoFromTable handler = new ApartmentInfoFromTable();
		for(String rent : validRent) {
			Assert.assertEquals(true, handler.checkIsValidRent(rent));
		}
	}

	@Test
	public void testCheckIsValidSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckIsValidRooms() {
		fail("Not yet implemented");
	}

}
