package com.hansson.rento.apartments;

import junit.framework.Assert;

import org.junit.Test;

public class ApartmentDescriptionHandlerTest {
	
	private final static String DESCRIPTION_1 = "Lägenheten ligger på andra våning med balkong. Hyran är 10 000:-/mån. I hyran ingår kabel-TV samt tvättmaskin. Varmhyra. Uppsagd till 1 maj, kan lämnas tidigare.";
	private final static String DESCRIPTION_2 = "Fin lägenhet i centrala Bromölla. Den ligger på andra våning med balkong. Hiss finns i fastigheten. Hyran är 10000:-/mån. Varmhyra. Carport finns att hyra. Ledig från 1 mars.";
	private final static String DESCRIPTION_3 = "Marstrand 4-5 Hantverkaregatan 23 9 lägenheter 3 rum och kök på 100m2. Radhuslägenhet med egen ingång samt egen gräsmatta utanför ytterdörren. Lägenheten är i två plan. Möjlighet till att hyra P plats och garage på gården. Hyra: 10000kr/månad 2013 års hyressättning Ledig from 1/3-2014";
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
