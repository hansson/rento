package com.hansson.rento.apartments;

import org.junit.Assert;
import org.junit.Test;

public class ApartmentInfoFromTableTest {

	@Test
	public void testCheckIsValidCityOrArea() {
		String[] validArea = { "TestOmråde", "Test Område" };
		ApartmentInfoFromTable handler = new ApartmentInfoFromTable();
		for (String area : validArea) {
			Assert.assertEquals(true, handler.checkIsValidCityOrArea(area));
		}
	}

	@Test
	public void testCheckIsInvalidCityOrArea() {
		String[] validArea = { "TestOmråde2", "asdasd.", "asdasd!", "asdasd?", "" };
		ApartmentInfoFromTable handler = new ApartmentInfoFromTable();
		for (String area : validArea) {
			Assert.assertEquals(false, handler.checkIsValidCityOrArea(area));
		}
	}

	@Test
	public void testCheckIsValidRent() {
		String[] validRent = { "1000 kr", "1000 SEK", "1000 SEK", "10 000", "10.000", "1000:-" };
		ApartmentInfoFromTable handler = new ApartmentInfoFromTable();
		for (String rent : validRent) {
			Assert.assertEquals(true, handler.checkIsValidRent(rent));
		}
	}

	@Test
	public void testCheckIsInvalidRent() {
		String[] invalidRent = { "1000 dk", "asdasdasd", "" };
		ApartmentInfoFromTable handler = new ApartmentInfoFromTable();
		for (String rent : invalidRent) {
			Assert.assertEquals(false, handler.checkIsValidRent(rent));
		}
	}

	@Test
	public void testCheckIsValidSize() {
		String[] validSize = { "15", "15 kvm", "15 m2" };
		ApartmentInfoFromTable handler = new ApartmentInfoFromTable();
		for (String size : validSize) {
			Assert.assertEquals(true, handler.checkIsValidSize(size));
		}
	}

	@Test
	public void testCheckIsInvalidSize() {
		String[] validSize = { "asdasd", "15 kdvm", "" };
		ApartmentInfoFromTable handler = new ApartmentInfoFromTable();
		for (String size : validSize) {
			Assert.assertEquals(false, handler.checkIsValidSize(size));
		}
	}

	@Test
	public void testCheckIsValidRooms() {
		String[] validRooms = { "2", "2 rok", "2 r.o.k", "2 r o k" };
		ApartmentInfoFromTable handler = new ApartmentInfoFromTable();
		for (String room : validRooms) {
			Assert.assertEquals(true, handler.checkIsValidRooms(room));
		}
	}
	
	@Test
	public void testCheckIsInvalidRooms() {
		String[] validRooms = { "", "asdasdsad" };
		ApartmentInfoFromTable handler = new ApartmentInfoFromTable();
		for (String room : validRooms) {
			Assert.assertEquals(false, handler.checkIsValidRooms(room));
		}
	}

}
