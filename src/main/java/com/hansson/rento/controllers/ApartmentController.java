package com.hansson.rento.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.ServletContextResource;

import com.google.gson.Gson;
import com.hansson.rento.dao.ApartmentDAO;
import com.hansson.rento.utils.HtmlUtil;

@Controller
public class ApartmentController {

	private static final Logger mLog = LoggerFactory.getLogger("rento");

	@Autowired
	private ApartmentDAO mApartmentDAO;
	
	@Autowired
	private ServletContext context;

	@RequestMapping(value = "/apartments", method = RequestMethod.GET)
	@ResponseBody
	public String getApartments(Locale locale, Model model) {
		return new Gson().toJson(mApartmentDAO.findAllByCity("Karlskrona"));
	}

	@RequestMapping(value = "/apartments", method = RequestMethod.POST)
	@ResponseBody
	public String postApartments(Locale locale, Model model, @RequestParam("city") String city) {
		city = HtmlUtil.textToHtml(city);
		if (city.equals("all")) {
			return new Gson().toJson(mApartmentDAO.findAll());
		} else {
			return new Gson().toJson(mApartmentDAO.findAllByCity(city));
		}
	}
	
	@RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET, produces = "application/xml;charset=UTF-8")
	@ResponseBody
	public String getSitemap(HttpServletRequest request,Locale locale, Model model) throws IOException {
		ServletContextResource resource = new ServletContextResource(context, "/WEB-INF/spring/sitemap.xml");
		StringWriter writer = new StringWriter();
		IOUtils.copy(resource.getInputStream(), writer, "UTF-8");
		return writer.toString();
	}
	
	@RequestMapping(value = "/robots.txt", method = RequestMethod.GET)
	@ResponseBody
	public String getRobots(HttpServletRequest request,Locale locale, Model model) throws IOException {
		ServletContextResource resource = new ServletContextResource(context, "/WEB-INF/spring/robots.txt");
		StringWriter writer = new StringWriter();
		IOUtils.copy(resource.getInputStream(), writer, "UTF-8");
		return writer.toString();
	}
	
	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String getApartmentsForCity(HttpServletRequest request,Locale locale, Model model) throws UnsupportedEncodingException {
		String[] split = URLDecoder.decode(request.getRequestURI(), "UTF-8").split("/");
		String city;
		if(split.length > 0) {
			city = split[1];
			model.addAttribute("infoCity", city);
		} else {
			city = "Karlskrona";
			model.addAttribute("infoCity", "Sverige");
		}
		city = HtmlUtil.textToHtml(city);
		model.addAttribute("apartments", new Gson().toJson(mApartmentDAO.findAllByCity(city)));
		model.addAttribute("city", city);
		return "view_apartments";
	}
	
	// @RequestMapping(value = "/del", method = RequestMethod.GET)
	// public String del(Locale locale, Model model) {
	// List<Apartment> findAll = mApartmentDAO.findAll();
	// for(Apartment apartment : findAll) {
	// mApartmentDAO.delete(apartment);
	// }
	// return "home";
	// }

}
