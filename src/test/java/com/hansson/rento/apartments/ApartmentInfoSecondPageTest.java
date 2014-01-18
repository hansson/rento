package com.hansson.rento.apartments;

import junit.framework.Assert;

import org.junit.Test;

public class ApartmentInfoSecondPageTest {
	
	private final static String PRICE_DESCRIPTION_1 = "Lägenheten ligger på andra våning med balkong. Hyran är 10 000:-/mån. I hyran ingår kabel-TV samt tvättmaskin. Varmhyra. Uppsagd till 1 maj, kan lämnas tidigare.";
	private final static String PRICE_DESCRIPTION_2 = "Fin lägenhet i centrala Bromölla. Den ligger på andra våning med balkong. Hiss finns i fastigheten. Hyran är 10000:-/mån. Varmhyra. Carport finns att hyra. Ledig från 1 mars.";
	private final static String[] PRICE_DESCRIPTION_ARRAY = {PRICE_DESCRIPTION_1, PRICE_DESCRIPTION_2};

	@Test
	public void getRentFromDescriptionTest() {
		ApartmentInfoSecondPage handler = new ApartmentInfoSecondPage();
		for(String description : PRICE_DESCRIPTION_ARRAY) {
			Assert.assertEquals("10000", handler.getRentFromDescription(description));
		}
	}

}
