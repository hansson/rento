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

import com.hansson.rentit.appartments.AppartmentsInterface;
import com.hansson.rentit.appartments.TrossoWamoAppartments;
import com.hansson.rentit.entitys.Appartment;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private List<AppartmentsInterface> landlords = new LinkedList<AppartmentsInterface>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2411798345463453006L;
		{
			add(new TrossoWamoAppartments());
		}
	};

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		List<Appartment> appartmentList = new LinkedList<Appartment>();
		for (AppartmentsInterface currentLandlord : landlords) {
			appartmentList.addAll(currentLandlord.getAvailableAppartments());
		}
		model.addAttribute("appartments", appartmentList);
		return "home";
	}

	@RequestMapping(value = "/flat", method = RequestMethod.GET)
	public String flat(Locale locale, Model model) {
		return "flat";
	}
}
