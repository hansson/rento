package com.hansson.rento.controllers;

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
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.apartments.BengtAkessonsApartments;
import com.hansson.rento.apartments.CAFastigheterApartments;
import com.hansson.rento.apartments.HeimstadenApartments;
import com.hansson.rento.apartments.KSFastigheterApartments;
import com.hansson.rento.apartments.KarlskronahemApartments;
import com.hansson.rento.apartments.MagistratusFastigheterApartments;
import com.hansson.rento.apartments.PBAApartments;
import com.hansson.rento.apartments.SBFApartments;
import com.hansson.rento.apartments.TrossoWamoApartments;
import com.hansson.rento.apartments.UtklippanApartments;
import com.hansson.rento.entities.Apartment;

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
