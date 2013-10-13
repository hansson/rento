package com.hansson.rentit.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gag.annotation.remark.Win;
import com.google.gag.enumeration.Outcome;
import com.google.gson.Gson;
import com.hansson.rentit.apartments.ApartmentsInterface;
import com.hansson.rentit.apartments.BengtAkessonsApartments;
import com.hansson.rentit.apartments.CAFastigheterApartments;
import com.hansson.rentit.apartments.HeimstadenApartments;
import com.hansson.rentit.apartments.KSFastigheterApartments;
import com.hansson.rentit.apartments.KarlskronahemApartments;
import com.hansson.rentit.apartments.MagistratusFastigheterApartments;
import com.hansson.rentit.apartments.PBAApartments;
import com.hansson.rentit.apartments.SBFApartments;
import com.hansson.rentit.apartments.TrossoWamoApartments;
import com.hansson.rentit.apartments.UtklippanApartments;
import com.hansson.rentit.entitys.Apartment;

@Controller
public class HomeController {

	private static final Logger mLog = LoggerFactory.getLogger("RENTIT");

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
