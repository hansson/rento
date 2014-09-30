package com.hansson.rento.apartments;

import junit.framework.Assert;

import org.junit.Test;

public class ApartmentDescriptionHandlerTest {
	
	private final static String DESCRIPTION_1 = "L\u00e4genheten ligger p\u00e5 andra v\u00e5ning med balkong. Hyran \u00e4r 10 000:-/m\u00e5n. I hyran ing\u00e5r kabel-TV samt tv\u00e4ttmaskin. Varmhyra. Uppsagd till 1 maj, kan l\u00e4mnas tidigare.";
	private final static String DESCRIPTION_2 = "Fin l\u00e4genhet i centrala Brom\u00f6lla. Den ligger p\u00e5 andra v\u00e5ning med balkong. Hiss finns i fastigheten. Hyran \u00e4r 10000:-/m\u00e5n. Varmhyra. Carport finns att hyra. Ledig fr\u00e5n 1 mars.";
	private final static String DESCRIPTION_3 = "Marstrand 4-5 Hantverkaregatan 23 9 l\u00e4genheter 3 rum och k\u00f6k p\u00e5 100m2. Radhusl\u00e4genhet med egen ing\u00e5ng samt egen gr\u00e4smatta utanf\u00f6r ytterd\u00f6rren. L\u00e4genheten \u00e4r i tv\u00e5 plan. M\u00f6jlighet till att hyra P plats och garage p\u00e5 g\u00e5rden. Hyra: 10000kr/m\u00e5nad 2013 \u00e5rs hyress\u00e4ttning Ledig from 1/3-2014";
	private final static String[] PRICE_DESCRIPTION_ARRAY = {DESCRIPTION_1, DESCRIPTION_2, DESCRIPTION_3};
	private final static String[] ADDRESS_DESCRIPTION_ARRAY = {DESCRIPTION_3};
	private final static String[] SIZE_DESCRIPTION_ARRAY = {DESCRIPTION_3};

	@Test
	public void getRentFromDescriptionTest() {
		ApartmentInfoSecondPage handler = new ApartmentInfoSecondPage();
		for(String description : PRICE_DESCRIPTION_ARRAY) {
			Assert.assertEquals("10000", handler.getRentFromDescription(description));
		}
	}
	
	@Test
	public void getAddressFromDescriptionTest() {
		ApartmentInfoSecondPage handler = new ApartmentInfoSecondPage();
		for(String description : ADDRESS_DESCRIPTION_ARRAY) {
			Assert.assertEquals("Hantverkaregatan 23", handler.getAddressFromDescription(description));
		}
	}
	
	@Test
	public void getSizeFromDescriptionTest() {
		ApartmentInfoSecondPage handler = new ApartmentInfoSecondPage();
		for(String description : SIZE_DESCRIPTION_ARRAY) {
			Assert.assertEquals("100", handler.getSizeFromDescription(description));
		}
	}
	
	@Test
	public void getRoomsFromDescriptionTest() {
		ApartmentInfoSecondPage handler = new ApartmentInfoSecondPage();
		for(String description : SIZE_DESCRIPTION_ARRAY) {
			Assert.assertEquals("3", handler.getRoomsFromDescription(description));
		}
	}

}
