package com.hansson.rento.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private static final Logger mLog = LoggerFactory.getLogger("rento");

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
//		List<Apartment> apartmentList = new LinkedList<Apartment>();
//		for (ApartmentsInterface currentLandlord : landlords) {
//			apartmentList.addAll(currentLandlord.getAvailableApartments());
//		}
//		model.addAttribute("apartments", new Gson().toJson(apartmentList));
		return "home";
	}

	@RequestMapping(value = "/flat", method = RequestMethod.GET)
	public String flat(Locale locale, Model model) {
		return "flat";
	}
}
