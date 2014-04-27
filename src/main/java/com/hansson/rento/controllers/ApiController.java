package com.hansson.rento.controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.hansson.rento.dao.ApartmentDAO;
import com.hansson.rento.utils.HtmlUtil;

@Controller
public class ApiController {

	private static final Logger mLog = LoggerFactory.getLogger("rento");

	@Autowired
	private ApartmentDAO mApartmentDAO;
	
	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String getViewApartments(Locale locale, Model model) {
		model.addAttribute("apidata", "This be api root");
		return "api";
	}
	
	@RequestMapping(value = "/api/apartment", method = RequestMethod.POST)
	public String getApartment(Locale locale, Model model, @RequestParam("apartment") int apartment) {
		model.addAttribute("apidata", new Gson().toJson(mApartmentDAO.find(apartment)));
		return "api";
	}

	@RequestMapping(value = "/api/apartments", method = RequestMethod.GET)
	public String getApartments(Locale locale, Model model) {
		model.addAttribute("apidata", new Gson().toJson(mApartmentDAO.findAllByCity("Karlskrona")));
		return "api";
	}

	@RequestMapping(value = "/api/apartments", method = RequestMethod.POST)
	public String postApartments(Locale locale, Model model, @RequestParam("city") String city) {
		city = HtmlUtil.textToHtml(city);
		if (city.equals("all")) {
			model.addAttribute("apidata", new Gson().toJson(mApartmentDAO.findAll()));
		} else {
			model.addAttribute("apidata", new Gson().toJson(mApartmentDAO.findAllByCity(city)));
		}
		return "api";
	}

}
